package com.wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wang.dao.imp.StudentDaoImp;
import com.wang.db.DbHelper;
import com.wang.object.Student;

public class StudentDao implements StudentDaoImp{
	
	// 定义数据库连接
	private static Connection conn = DbHelper.getConn();

	@Override
	public List<Student> queryAllStudent() {
		
		List<Student> sList = new ArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from student";
			ResultSet rSet = stmt.executeQuery(sql);
			while(rSet.next()) {
				// 封装模型(数据表的记录映射对象)
				Student student = new Student();
				student.setSno(rSet.getString("sno"));
				student.setSname(rSet.getString("sname"));
				student.setSsex(rSet.getString("ssex"));
				student.setSbirthday(rSet.getString("sbirthday"));
				student.setSclass(rSet.getString("sclass"));
				sList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
	}

	@Override
	public boolean insertAStudent(Student stu) {
		// 使用占位符
		String sql = "insert into student values(?,?,?,?,?)";
		try {
			PreparedStatement pStatement = conn.prepareStatement(sql);
			// 填充占位符
			pStatement.setString(1, stu.getSno());
			pStatement.setString(2, stu.getSname());
			pStatement.setString(3, stu.getSsex());
			pStatement.setString(4, stu.getSbirthday());
			pStatement.setString(5, stu.getSclass());
			
			// 执行sql语句
			pStatement.executeUpdate();
			System.out.println("插入成功！");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入失败！");
			e.printStackTrace();
			return false;
		}
//		try {
//			Statement statement = conn.createStatement();
//			String sql = String.format("insert into student values('%s','%s','%s','%s','%s')", 
//					stu.getSno(),stu.getSname(),stu.getSsex(),stu.getSbirthday(),stu.getSclass());
//			System.out.println(sql);
//			statement.executeUpdate(sql);
//			return true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
	}

	@Override
	public boolean updateAStudent(String sno, Student stu) {
		String sql = "update student set sname=?,ssex=?,sbirthday=?,sclass=? where sno=?";
		try {
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(5, sno);
			pStatement.setString(1, stu.getSname());
			pStatement.setString(2, stu.getSsex());
			pStatement.setString(3, stu.getSbirthday());
			pStatement.setString(4, stu.getSclass());
			
			pStatement.executeUpdate();
			System.out.println("更新成功！");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
//		try {
//			Statement statement = conn.createStatement();
//			String sql = String.format("update student set sname='%s',ssex='%s',sbirthday='%s',sclass='%s' where sno='%s'", 
//					stu.getSname(),stu.getSsex(),stu.getSbirthday(),stu.getSclass(),sno);
//			System.out.println(sql);
//			statement.executeUpdate(sql);
//			return true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
	}

	@Override
	public boolean deleteAStudent(String sno) {
		String sql="delete from student where sno=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sno);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			// 使用finally代码释放资源
		}finally {
            /*
             * close any jdbc instances here that weren't
             * explicitly closed during normal code path, so
             * that we don't 'leak' resources...
             */

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                conn = null;
            }
        }
//		try {
//			Statement statement = conn.createStatement();
//			String sql = String.format("delete from student where sno='%s'", sno);
//			System.out.println(sql);
//			statement.executeUpdate(sql);
//			return true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
	}
	
}