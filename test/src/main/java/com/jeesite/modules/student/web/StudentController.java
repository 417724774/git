/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.web;

import javax.validation.ConstraintViolationException;

import com.jeesite.common.utils.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.student.entity.Student;
import com.jeesite.modules.student.service.StudentService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * studentController
 * @author test
 * @version 2021-03-25
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;

	/**
	 * 保存数据
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated @RequestBody Student student) {
		studentService.insert(student);
		return renderResult(Global.TRUE, text("保存student成功！"));
	}

	/**
	 * 更新数据
	 */
	@PostMapping(value = "update")
	@ResponseBody
	public String update(@Validated @RequestBody Student student) {
		studentService.save(student);
		return renderResult(Global.TRUE, text("保存student成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(@RequestBody Student student) {
		studentService.delete(student);
		return renderResult(Global.TRUE, text("删除student成功！"));
	}

	/**
	 * 通过studentId查询
	 */
	@RequestMapping(value = "getbystudentid")
	@ResponseBody
	public String getByStudentId(@RequestBody Student student) {
		Student stu = studentService.get(student.getId());
		return renderResult(Global.TRUE, stu.toString());
	}

	/**
	 * 分页查询
	 */
	@RequestMapping(value = "getbycurrentpage")
	@ResponseBody
	public Page<Student> getByCurrentPage(@RequestParam(defaultValue = "1") Integer currentPage) {
		Student student = new Student();
		student.setPage(new Page<>(currentPage,5));
		Page<Student> page = studentService.findPage(student);
		return page;
	}

	/**
	 * 导入excel数据
	 */
	@RequestMapping(value = "import", method=RequestMethod.POST)
	@ResponseBody
	public String importFile(@RequestBody MultipartFile file){
		int successNum=0;
		int failureNum=0;
		StringBuilder failureMsg=new StringBuilder();
		try {
			ExcelImport ei=new ExcelImport(file, 1, 0);
			List<Student> list=ei.getDataList(Student.class);

			for(Student student:list){
				try {
					studentService.insert(student);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("学生ID： "+student.getStudentId()+" 导入失败："+ex.getMessage());
				}catch (Exception ex) {
					failureMsg.append("学生ID： "+student.getStudentId()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
		} catch (Exception e) {
			return renderResult(Global.TRUE,"导入学生表失败！失败信息："+e.getMessage());
		}
		return renderResult(Global.TRUE,"导入结束！插入数据："+successNum+" 条；失败："+failureNum+" 条"+"/n"+failureMsg);
	}
}