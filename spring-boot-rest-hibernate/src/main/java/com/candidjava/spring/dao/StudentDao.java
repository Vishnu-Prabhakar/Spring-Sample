package com.candidjava.spring.dao;

import java.util.List;

import com.candidjava.spring.bean.Student;

public interface StudentDao {
	public List<Student> getStudent();
	public Student findById(int id);
}
