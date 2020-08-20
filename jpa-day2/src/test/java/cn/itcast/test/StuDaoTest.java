package cn.itcast.test;

import cn.itcast.dao.StuDao;
import cn.itcast.domain.Stu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StuDaoTest {

    @Autowired
    private StuDao stuDao;

    @Test
    public void test(){
//        Iterable<Stu> stus = stuDao.findAll();
//        for (Stu stu : stus) {
//            System.out.println(stu);
//        }
//        System.out.println(stuDao.findById("1"));
//        System.out.println(stuDao.findAllByName("aaa"));
//        System.out.println(stuDao.findAllByNameEndingWith("a"));

        Page<Stu> all = stuDao.findAll(new PageRequest(1, 2));
        for (Stu stu : all) {
            System.out.println(stu);
        }
    }
}
