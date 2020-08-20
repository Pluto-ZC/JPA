package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

//动态查询

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件查询单个对象
     */
    @Test
    public void testSpec1() {
        //匿名内部类
        /**
         * 自定义查询条件
         *  1、实现Specification接口（提供泛型：查询对象类型）
         *  2、实现toPredicate方法（构造查询条件）
         *  3、需要借助方法参数中的两个参数（
         *          root:获取需要查询的对象属性
         *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件--模糊匹配，精准匹配
         *     ）
         *     案例：根据客户名称查询，查询客户名为wwww的客户
         *          查询条件：
         *              1、查询方式 cb对象中
         *              2、比较的属性名称 root对象中
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //1、获取比较的属性
                Path<Object> custName = root.get("custName");
                //2、构造查询条件  :  select * from cst_customer where cust_name = 'wwwww'
                /**
                 * 第一个参数：需要比较的属性
                 * 第二个参数：当前需要比较的值
                 */
                Predicate predicate = cb.equal(custName, "wwwww");//进行精准的匹配 （比较的属性，比较的属性的取值）
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     * 案例：根据客户名（wwwww）和客户所属行业查询（教育）
     */

    @Test
    public void testSpec2() {
        /**
         * root ： 获取属性
         *          客户名
         *           所属行业
         * cb ： 构造查询
         *      1、构造客户名的精准匹配查询
         *      2、构造所属行业的精准匹配查询
         *      3、将以上两个查询关联起来
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                
                //构造查询
                //1、构造客户名的精准匹配查询
                Predicate p1 = cb.equal(custName, "wwwww");//属性  属性的取值
                //2、构造所属行业的精准匹配查询
                Predicate p2 = cb.equal(custIndustry, "教育");
                //3、将以上两个查询关联起来：组合（满足条件一和条件二；满足条件一或条件二）
                Predicate predicate = cb.and(p1, p2);//and 以与的形式拼接多个查询条件
//                cb.or();  以或的形式拼接多个查询条件

                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 案例：完成根据客户名称的模糊匹配，返回客户列表
     *
     * equal : 直接得到path对象，直接进行比较即可
     * gt,lt,ge,le,like ： 得到path对象，根据path指定比较的参数类型，再去进行比较
     *              指定的方式  path.as(类型的字节码对象)
     */
    @Test
    public void testSpec3(){
        //构造查询条件
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //查询属性：客户名   查询方式：模糊查询
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "w%");
                return predicate;
            }
        };
        List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    //添加排序
    @Test
    public void testSpec4(){
        //构造查询条件
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //查询属性：客户名   查询方式：模糊查询
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "w%");
                return predicate;
            }
        };
        //创建排序对象  需要调用构造方法实例化对象
        //第一个参数：排序的顺序（倒叙  正序）
        //第二个参数：排序的属性名
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     *      findAll(Specification,Pageable)  带有条件的分页
     *          Specification:查询条件
     *          Pageable：分页参数：查询的页码，每页查询的参数
     *      findAll(Pageable)  没有条件的分页
     *  返回 Page（SpringDataJpa为我们封装好的pageBean对象，数据列表，总条数）
     */
    @Test
    public void testSpec5(){
        Specification spec = null;
        //PageRequest对象是Pageable接口的实现类
        /**
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *      第一个参数：当前查询的页数（从0开始）
         *      第二个参数：每页查询的数量
         *      
         *  排序后分页：在page中可以添加sort
         */
        Pageable pageable = new PageRequest(0,2 );
        Page<Customer> page = customerDao.findAll(spec, pageable);
        System.out.println(page.getTotalElements());//得到总条数
            System.out.println(page.getContent()); //得到数据集合列表
        System.out.println(page.getTotalPages()); //得到总页数
    }
}










