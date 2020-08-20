package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL(){
        Customer customer = customerDao.findJpql("cccc");
        System.out.println(customer);
    }

    @Test
    public void testFindCustNameAndId(){
        Customer customer = customerDao.findCustNameAndId("cccc", 1l);
        System.out.println(customer);
    }

    /**
     * jpql 更新
     *  springDataJpa中使用jpql完成 更新、删除操作
     *      需要手动添加事务的支持
     *      默认会执行结束后，回滚事务
     *  @Rollback(value = false  设置是否回滚
     */
    @Test
    @Transactional  //添加事务的支持
    @Rollback(value = false)
    public void testUpdateCustomer(){
        customerDao.updateCustomer(3l, "wwwww");
    }

    @Test
    public void testFindSql(){
        List<Object[]> list = customerDao.findSql("w%");
        for (Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }

    //测试方法命名规则的查询
    @Test
    public void testFindName(){
        Customer cu = customerDao.findByCustName("cccc");
        System.out.println(cu);
    }

    @Test
    public void testFindByCustNameLike(){
        List<Customer> list = customerDao.findByCustNameLike("w%");
        for (Customer cu: list             ) {
            System.out.println(cu);
        }
    }

    @Test
    public void testFindByCustNameLikeAAndCustIndustry(){
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("w%", "it");
        for (Customer cu: list             ) {
            System.out.println(cu);
        }
    }

}

















