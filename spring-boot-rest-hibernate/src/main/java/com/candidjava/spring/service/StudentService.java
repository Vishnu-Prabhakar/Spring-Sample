package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Student;

public interface StudentService {
	public List<Student> getStudent();
	public Student findById(int id);
}
