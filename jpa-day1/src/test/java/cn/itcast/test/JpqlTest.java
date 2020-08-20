package cn.itcast.test;

import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */

public class JpqlTest {
    /**
     * 查询全部
     *      jpql:from cn.itcast.domain.Customer
     *      sql：select * from cst_customer
     */
    @Test
    public void testFindAll(){
        //1、获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、完成查询全部
//        String jpql = "from cn.itcast.domain.Customer";
        String jpql = "from Customer";
        //创建query对象，query对象才是执行jqpl的对象
        Query query = em.createQuery(jpql);
        //发送查询并封装结果集
        List list = query.getResultList();
        for (Object obj: list) {
            System.out.println(obj);
        }
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

    /**
     * 排序查询：倒叙查询全部客户（根据id倒叙）
     *      sql ：select * from cst_customer order by cust_id desc;
     *      jpql：from Customer order by custId desc
     *
     *  进行jpql查询
     *      1、创建query对象
     *      2、对参数进行赋值
     *      3、查询并返回结果
     */

    @Test
    public void testOrder(){
        //1、获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、完成查询全部
        String jpql = "from Customer order by custId desc";
        //创建query对象，query对象才是执行jqpl的对象
        Query query = em.createQuery(jpql);
        //发送查询并封装结果集
        List list = query.getResultList();
        for (Object obj: list) {
            System.out.println(obj);
        }
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql:select count(cust_id) from cst_customer
     *      jpql:select count(custId) from Customer
     */
    @Test
    public void testCount(){
        //1、获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、完成查询全部
        //根据jpql创建Query查询对象
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);
        //对参数进行赋值
        //发送查询，并封装结果  getSingleResult 得到唯一的结果集
        Object result = query.getSingleResult();
        System.out.println(result);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

    /**
     * 分页查询
     *      sql：select * from cst_customer limit ?,?
     *      jpql: from Customer
     */

    @Test
    public void testPaged(){
        //1、获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、完成查询全部
        //根据jpql创建Query查询对象
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        //对参数进行赋值  分页参数
            //起始索引  0 从0开始查 不包含0 结果 1 2  从1开始查 结果2 3
        query.setFirstResult(0);
            //每页查询的条数
        query.setMaxResults(2);
        //发送查询，并封装结果  getSingleResult 得到唯一的结果集
        List list = query.getResultList();
        for (Object obj:list)
            System.out.println(obj);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

    /**
     * 条件查询：
     *      查询客户名称 以wz开头的客户
     *      sql：select * from cst_customer where cust_name like 'wzc%'
     *      jpql: from Customer where custName like ?
     */
    @Test
    public void testCondition(){
        //1、获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、完成查询全部
        //根据jpql创建Query查询对象  ?1 jap的占位符方式
        String jpql = " from Customer where custName like ?1 ";
        Query query = em.createQuery(jpql);
        //对参数进行赋值  占位符参数
        //第一个参数是占位符的索引（从1开始）  第二个参数：取值
        query.setParameter(1, "wz%");
        //发送查询，并封装结果  getSingleResult 得到唯一的结果集
        List list = query.getResultList();
        for (Object obj:list)
            System.out.println(obj);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }
}












