package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {
    /**
     * 测试jpa保存
     *
     * jpa的操作步骤
     *  1、加载配置文件，创建工厂对象（实体管理类对象）
     *  2、通过实体管理器工厂获取实体管理器
     *  3、获取事务对象，开启事务
     *  4、完成增删改查操作
     *  5、提交事务（回滚事务）
     *  6、释放资源
     */
    @Test
    public void testSave(){
        /*
        //1、加载配置文件，创建工厂对象（实体管理类对象）
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2、通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
         */

        EntityManager em = JpaUtils.getEntityManager();

        //3、获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //4、完成增删改查操作 保存客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("wzwww");
        customer.setCustIndustry("教育");
        //保存操作
        em.persist(customer);
        //5、提交事务（回滚事务）
        tx.commit();
        //6、释放资源
        em.close();
//        factory.close();
    }

    /**
     * 根据id查询用户
     */
    @Test
    public void testFind(){
        //1、通过工具类获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、增删改查
            /**
             * find 根据id查询用户
             *      class ：查询数据的结果需要包装的实体类类型的字节码
             *      id：查询的主键值
             *      1、查询的对象就是当前客户对象
             *      2、在调用find方法的时候，就会发送sql语句查询数据库
             *      立即加载
             *
             * getReference  根据用户id查询  一般用这个方法
             *      class ：查询数据的结果需要包装的实体类类型的字节码
             *      id：查询的主键值
             *      1、获取的对象是一个动态代理对象
             *      2、调用getReference方法不会立即发送sql语句查询数据库
             *          当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询
             *      延迟加载、懒加载
             *          得到的是一个动态代理对象
             *          什么时候用，什么时候在加载
             */
        Customer customer = em.find(Customer.class, 2l);
//        Customer customer = em.getReference(Customer.class, 2l);
        System.out.println(customer);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

    /**
     * 删除客户
     */
    @Test
    public void testRemove(){
        //1、通过工具类获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、增删改查
        /**
         * 1、根据id查询用户
         * 2、调用remove方法删除
         */
        Customer customer = em.find(Customer.class, 1l);
        em.remove(customer);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

    @Test
    public void testUpdate(){
        //1、通过工具类获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2、开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3、增删改查
        /**
         * 1、查询客户
         * 2、更新客户
         */
        Customer customer = em.find(Customer.class, 2l);
        customer.setCustIndustry("it");
        em.merge(customer);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }

}


















