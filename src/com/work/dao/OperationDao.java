package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.work.entity.OperationLog;

import com.work.util.DBconnection;

//操作日志
@Repository
public class OperationDao {
	//分页查询日志
	//条件查询日志
	//单条删除日志
	//清空日志
    //增加日志
	public void addLog(OperationLog log){
		Connection con = DBconnection.getConnection();
    	String sql = "insert into operlog(type,content,username,ip,time) values(?,?,?,?,?)";
    	
    	PreparedStatement prep = null;		
    	try {
    		prep = con.prepareStatement(sql);
    		prep.setInt(1, log.getType());
    		prep.setString(2,log.getContent() );
    		prep.setString(3,log.getUsername());
    		prep.setString(4, log.getIp() );
    		prep.setString(5, log.getTime());
    		prep.executeUpdate();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		DBconnection.close(con);
    		DBconnection.close(prep);
    	}
	}
}
