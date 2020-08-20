package cn.itcast.dao;


import cn.itcast.domain.Stu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.persistence.Id;
import java.util.List;

public interface StuDao extends Repository<Stu,String> {

    public List<Stu> findAll();

    public Stu findById(String id);

    public Stu findAllByName(String name);

    public List<Stu> findAllByNameEndingWith(String end);

    public Page<Stu> findAll(Pageable pageable);

    public Stu findByIdAndName(Specification spec,Pageable pageable);

}
