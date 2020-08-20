package cn.itcast.entity;

import java.io.Serializable;

/**
 * (Stu)实体类
 *
 * @author makejava
 * @since 2020-08-19 14:13:59
 */
public class Stu implements Serializable {
    private static final long serialVersionUID = -73107573891295422L;

    private String id;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}