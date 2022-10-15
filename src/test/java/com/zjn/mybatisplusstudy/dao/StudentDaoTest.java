package com.zjn.mybatisplusstudy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.mybatisplusstudy.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zjn
 * @date 2022/10/14 下午11:35
 */
@SpringBootTest
public class StudentDaoTest {

    @Resource
    private StudentDao studentDao;

    @Test
    void SelectAllTest() {
        List<Student> students = studentDao.selectList(null);
        System.out.println("students = " + students);

    }

    @Test
    void InsertTest() {
        Student student = new Student();
        student.setName("zj11");
        student.setAge(18);
        student.setTel("1234567890");
        student.setPassword("1234567890");
        int insert = studentDao.insert(student);
        System.out.println("student = " + student);
    }

    @Test
    void UpdateTest() {
        Student student = new Student();
        student.setId(1581094014604705794L);
        student.setName("xiao ming");
        student.setAge(25);
        studentDao.updateById(student);
        System.out.println("student = " + student);

    }

    @Test
    void DeleteTest() {

        int i = studentDao.deleteById(1581109269057634307L);
        System.out.println("i = " + i);

    }

    @Test
    void PageTest() {
        // 分页查询
        IPage<Student> page = new Page<>(1, 3);
        studentDao.selectPage(page, null);
        System.out.println("page.getCurrent() = " + page.getCurrent());
        System.out.println("page.getRecords() = " + page.getRecords());
        System.out.println("page.getPages() = " + page.getPages());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println("page.getSize() = " + page.getSize());

    }

    @Test
    void SelectByAgeTest1() {
        // 范围查询
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lt("age", 18);
        List<Student> students = studentDao.selectList(wrapper);
        System.out.println("students = " + students);
    }

    @Test
    void SelectByAgeTest2() {
        // 等值查询
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getAge, 16);
        List<Student> students = studentDao.selectList(wrapper);
        students.forEach(System.out::println);
    }

    @Test
    void SelectByAgeTest3() {
        // 查询投影
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Student::getId, Student::getAge);
        wrapper.lt(Student::getAge, 16);
        List<Student> students = studentDao.selectList(wrapper);
        students.forEach(System.out::println);
    }

    @Test
    void SelectByAgeTest4() {
        // 查询投影
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.select("age","count(*) as count");
        wrapper.groupBy("age");
        List<Map<String, Object>> maps = studentDao.selectMaps(wrapper);
        maps.forEach(stringObjectMap -> System.out.println("stringObjectMap = " + stringObjectMap));

    }

    @Test
    void SelectBatch1(){
        List<Long> ids = new ArrayList<>();
        // 批量查询
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        List<Student> students = studentDao.selectBatchIds(ids);
        students.forEach(System.out::println);
    }

    @Test
    void SelectBatch2(){
        List<Long> ids = new ArrayList<>();
        // 批量删除
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        int i = studentDao.deleteBatchIds(ids);
        System.out.println("i = " + i);
    }

    @Test
    void OptimisticLockerTest(){
        Student s1 = studentDao.selectById(1581109269057634306L);
        Student s2 = studentDao.selectById(1581109269057634306L);
        s2.setName("S21 name");
        studentDao.updateById(s2);

        // 更新失败
        s1.setName("S11 name");
        studentDao.updateById(s1);

    }
}