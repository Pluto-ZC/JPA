package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.*;
import sun.util.resources.cldr.gv.LocaleNames_gv;

import java.util.List;

/**
 * 符合SpringDataJpa的dao层接口规范
 *      JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *          封装了基本的CRUD操作
 *      JpaSpecificationExecutor<操作的实体类类型>
 *          封装了复杂查询（分页操作等。。。）
 */

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    //sql 、 jpql查询  、  方法名称规则查询

    /*   jpql   */

    /**
     * 根据客户名称查询
     *  使用jpql的形式查询
     *  jpql： from Customer where custName = ?
     *
     *  配置jpql语句  使用@Query注解
     */
    @Query(value = "from Customer where custName = ?")
    public Customer findJpql(String custName);

    /**
     * 根据客户名称和客户id查询客户
     *  jpql: from Customer where custName = ? and custId = ?
     *  对于多个占位符参数：
     *      赋值 的时候，默认情况下，占位符的位置需要和方法参数中的位置保持一致
     *   可以指定占位符参数的位置
     *      ？+索引 的方式决定此占位符的来源
     */
//    @Query(value = "from Customer where custName = ? and custId = ?")
    @Query(value = "from Customer where custId = ?2 and  custName = ?1 ")
    public Customer findCustNameAndId(String name, Long id);

    /**
     * 使用jpql完成更新操作
     *      根据id 更新用户的名称
     *      更新3号客户的名称

     *  sql : update cst_customer set cust_name = ? where cust_id = ?
     *  jpql: update Customer set csutName = ? where custId = ?
     *
     * @Query 代表的是查询
     *           声明此方法是用来进行更新操作
     * @Modifying
     *          当前执行的是一个更新操作
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateCustomer(Long custId,String custName);


    /*   sql   */
    /**
     * 使用sql的形式查询
     *      查询全部客户
     *      sql：select * from cst_customer;
     *  Query : 配置sql查询
     *      value:sql语句
     *      nativeQuery ： 查询方式
     *          true ： sql查询
     *          false ： hpql查询
     */
//    @Query(value = "select * from cst_customer",nativeQuery = true)
//    public List<Object []> findSql();

    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object []> findSql(String name);

    /**
     * 方法名的约定：  不需要写注解
     *      findBy : 查询
     *          对象中的属性名（首字母大写） ： 查询的条件
     *      findByCustName -- 根据客户名称查询
     *      默认情况使用等于的方式查询
     *
     *  在SpringDataJpa的运行阶段，会根据方法名进行解析
     *  findBy  from xxx(实体类)
     *        属性名称   where custName =
     *
     *      1、findBy + 属性名称 （根据属性名称进行完成匹配的查询=） findByCustName
     *      2、findBy + 属性名称 + 查询方式（like | isnull） findByCustNameLike
     *      3、多条件查询
     *          findBy + 属性名 + 查询方式 + 多条件的连接符（and、or） + 属性名 + 查询方式
     *
     */
    public Customer findByCustName(String custName);

    public List<Customer> findByCustNameLike(String custName);

    //使用客户名称模糊匹配和客户所属行业精准匹配的查询
    public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustry);

}


















