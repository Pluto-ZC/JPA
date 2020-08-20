package cn.itcast.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (Stu)实体类
 *
 * @author makejava
 * @since 2020-08-10 16:08:54
 */
@Entity
@Table(name = "stu")
@Data
public class Stu implements Serializable {
    private static final long serialVersionUID = 337462024749967581L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

}