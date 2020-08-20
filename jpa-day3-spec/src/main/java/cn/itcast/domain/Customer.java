package cn.itcast.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 1、实体类和表的映射关系
 *
 * @Entity
 * @Table 2、类中属性和表中字段的映射关系
 * @Id
 * @GeneratedValue
 * @Column
 */

@Entity
@Table(name="cst_customer")
@Data
public class Customer {

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
