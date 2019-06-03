package com.candidjava.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Student;
import com.candidjava.spring.bean.User;
import com.candidjava.spring.dao.StudentDao;

@Service
@Transactional
public class StudentServiceImp implements StudentService {
	@Autowired
	StudentDao studentDao;

	@Override
	public List<Student> getStudent() {
		return studentDao.getStudent();
	}

	@Override
	public Student findById(int id) {
		return studentDao.findById(id);
	}

}