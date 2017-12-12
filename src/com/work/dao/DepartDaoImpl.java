package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.work.entity.Department;
import com.work.util.DBconnection;
@Repository
public class DepartDaoImpl implements DepartDao {

	public void addDepart(Department depart) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		PreparedStatement prep = null;	
		try {
			String sql="insert into department(departname,content) values (?,?)";
			prep = con.prepareStatement(sql);
			prep.setString(1, depart.getDepartname());
			prep.setString(2, depart.getContent());
			
			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	}

	public void updateDepart(Department depart) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		PreparedStatement prep = null;	
		try {
			String sql="update department set departname=?,content=?";
			prep = con.prepareStatement(sql);
			prep.setString(1, depart.getDepartname());
			prep.setString(2, depart.getContent());
			
			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	}

	public void deleteDepart(int id) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		String sql = "delete from department where id=?";
		PreparedStatement prep = null;		
		try {
			prep = con.prepareStatement(sql);
			prep.setInt(1,id);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	}

	public List<Department> queryDepart() {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		String findSQL = "select * from department ";
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();  
		try {
			pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				
				Department department=new Department();
				department.setDepartid(rs.getInt(1));
				department.setDepartname(rs.getString(2));
				department.setContent(rs.getString(3));
				list.add(department);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(pstmt);
			DBconnection.close(rs);
		}
		return list;
	}

}
