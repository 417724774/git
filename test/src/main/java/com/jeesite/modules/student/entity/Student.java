/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.entity;

import com.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

import javax.validation.constraints.NotNull;

/**
 * studentEntity
 * @author test
 * @version 2021-03-25
 */

@Table(name="student", alias="a", label="student信息", columns={
		@Column(name="student_id", attrName="studentId", label="id", isPK=true),
		@Column(name="student_name", attrName="studentName", label="姓名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="student_number", attrName="studentNumber", label="student_number"),
	}, orderBy="a.student_id DESC"
)
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;


	/**
	 * id
	 */
	private String studentId;		// id

	/**
	 * 姓名
	 */
	private String studentName;		// 姓名

	/**
	 * 性别
	 */
	private String sex;		// 性别

	/**
	 * student_number
	 */
	private Long studentNumber;		// student_number
	
	public Student() {
		this(null);
	}

	public Student(String id){
		super(id);
	}

	@NotNull(message = "学生ID不能为空")
	@ExcelField(title="id")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@ExcelField(title="姓名")
	@Length(min=0, max=10, message="姓名长度不能超过 10 个字符")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@ExcelField(title="性别")
	@Length(min=0, max=2, message="sex长度不能超过 2 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ExcelField(title="student_number")
	public Long getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Long studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentId='" + studentId + '\'' +
				", studentName='" + studentName + '\'' +
				", sex='" + sex + '\'' +
				", studentNumber=" + studentNumber +
				'}';
	}
}