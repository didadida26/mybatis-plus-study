package com.zjn.mybatisplusstudy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjn.mybatisplusstudy.domain.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zjn
 * @date 2022/10/14 下午11:35
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {
}
