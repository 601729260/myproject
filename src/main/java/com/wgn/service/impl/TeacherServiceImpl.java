package com.wgn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wgn.bean.Teacher;
import com.wgn.bean.TeacherExample;
import com.wgn.dao.TeacherMapper;
import com.wgn.service.interfaces.ITeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService{
	@Resource
	private TeacherMapper TeacherMapper;
	public List<Teacher> getTeacherById(int id) {
		TeacherExample example=new TeacherExample();
		example.createCriteria().andIdEqualTo(1);
		List<Teacher> listteacher=this.TeacherMapper.selectByExample(example);
		return listteacher;
		
		
	}
}
