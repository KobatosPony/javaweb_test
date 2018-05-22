package com.wang.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.wang.dao.StudentDao;
import com.wang.db.DbHelper;
import com.wang.object.Student;

public class Application {
	// 使用自定义的工具类
	static StudentDao sdao = new StudentDao();
	public static void main(String[] args) {
		
		// 查询
		select();
	
		// 新增
		add();
		
//		if (result) {
//			System.out.println("插入成功！");
//		}
//		else {
//			System.out.println("插入失败！");
//		}
		
		// 删除
		delete();
		
//		if (result) {
//			System.out.println("删除成功！");
//		}
//		else {
//			System.out.println("删除失败！");
//		}
//		
		// 修改
		update();
		
//		if (result) {
//			System.out.println("修改成功！");
//		}
//		else {
//			System.out.println("修改失败！");
//		}
	}

	private static void delete() {
		String sno = "110";
		boolean result = sdao.deleteAStudent(sno);
	}

	private static void add() {
		Student adStu = new Student("110", "大张伟", "男", "1975-03-02", "95031");
		boolean result = sdao.insertAStudent(adStu);
	}

	private static void update() {
		String sno = "110";
		Student upStu = new Student("110", "小张伟", "女", "1975-03-02", "95031");
		boolean result = sdao.updateAStudent(sno, upStu);
	}

	private static void select() {
		List<Student> sList = sdao.queryAllStudent();
		for (Student student : sList) {
			System.out.println(student);
		}
	}
}
