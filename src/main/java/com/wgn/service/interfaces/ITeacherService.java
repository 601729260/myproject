package com.wgn.service.interfaces;

import java.util.List;

import com.wgn.bean.Teacher;

public interface ITeacherService {
	public List<Teacher> getTeacherById(int id);
}
