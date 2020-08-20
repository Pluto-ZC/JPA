package cn.itcast.domain;

import lombok.Data;

import javax.persistence.*;

/**
 *  客户的实体类
 *      配置映射关系
 *          1、实体类和表的映射关系
 *              @Entity：声明实体类
 *              @Table：配置实体类和表的映射关系
 *                  name：数据库表的名称
 *          2、实体类中属性和表中字段的映射关系
 */
@Data
@Entity
@Table(name = "cst_customer")
public class Customer {

    /**
     * @Id  声明主键的配置
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     *      配置主键的生成策略  主键自增
     *      strategy
     *          GenerationType.IDENTITY 自增  底层数据库必须支持自动增长对id自增  mysql
     *          GenerationType.SEQUENCE 序列  底层数据库支持序列  oracle
     *          GenerationType.TABLE  jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
     *          GenerationType.AUTO  程序自动的帮助我们选择主键生成策略
     *
     *
     * @Column(name = "cust_id") 配置属性和字段的映射关系
     *      name：数据库表中字段的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_source")
    private String custSource;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_address")
    private String custAddress;

}
