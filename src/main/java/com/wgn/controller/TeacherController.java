package com.wgn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wgn.bean.Teacher;
import com.wgn.service.interfaces.ITeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Resource
	private ITeacherService teacherService;
	@RequestMapping("/showTeacher")
	public String toIndex(HttpServletRequest request,Model  model){
		int id=Integer.parseInt(request.getParameter("id"));
		List<Teacher> listTeacher=this.teacherService.getTeacherById(id);
		model.addAttribute("teacher", listTeacher.get(0));
		return "showTeacher";
	}
	
	
}
