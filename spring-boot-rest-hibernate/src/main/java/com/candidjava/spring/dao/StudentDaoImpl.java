package com.candidjava.spring.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.candidjava.spring.bean.Student;
@Repository
public class StudentDaoImpl implements StudentDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Student> getStudent() {
		 Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Student> list= session.createCriteria(Student.class).list();
		return list;
	}

	public Student findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Student stud=(Student) session.get(Student.class,id);
		Hibernate.initialize(stud.getMarksDetails());
		return stud;
	}

}
