package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.work.entity.Photo;
import com.work.util.DBconnection;
import com.work.util.Page;

//相册管理实体类
@Repository
public class PhotoDao {
  //增加照片
	public void addPhoto(Photo photo){
		Connection con = DBconnection.getConnection();
    	String sql = "insert into photo(title,content,path,author,publishdate,wisdom) values(?,?,?,?,?,?)";
    	
    	PreparedStatement prep = null;		
    	try {
    		prep = con.prepareStatement(sql);
    		prep.setString(1,photo.getTitle());
    		prep.setString(2,photo.getContent() );
    		prep.setString(3,photo.getPath());
    		prep.setString(4,photo.getAuthor());
    		prep.setString(5,photo.getPublishdate());
    		prep.setString(6,photo.getWisdom());
    		prep.executeUpdate();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		DBconnection.close(con);
    		DBconnection.close(prep);
    	}
	}	
	//修改照片
		public void updatePhoto(Photo photo){
			Connection con = DBconnection.getConnection();
	    	String sql = "update photo set title=?,content=?,author=?,publishdate=?,wisdom=? where id=?";
	    	
	    	PreparedStatement prep = null;		
	    	try {
	    		prep = con.prepareStatement(sql);
	    		prep.setString(1,photo.getTitle());
	    		prep.setString(2,photo.getContent() );
	    		prep.setString(3,photo.getAuthor());
	    		prep.setString(4,photo.getPublishdate());
	    		prep.setString(5,photo.getWisdom());
	    		prep.setInt(6,photo.getId());
	    		prep.executeUpdate();
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	} finally {
	    		DBconnection.close(con);
	    		DBconnection.close(prep);
	    	}
		}	

//查询公告总数
	public int photoAllCount(){
		Connection conn = DBconnection.getConnection();	//获得连接对象			
		String findSQL = "select count(*) from photo";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();					//执行查询
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBconnection.close(rs);						//关闭结果集对象
			DBconnection.close(pstmt);					//关闭预处理对象
			DBconnection.close(conn);					//关闭连接对象
		}
		return count;
	}
  //查询照片
public List<Photo> selectPhoto(Page page){
	Connection conn = DBconnection.getConnection();	//获得连接对象
	String findSQL = "select * from photo " +
				 "order by id asc limit ?,?";
	PreparedStatement pstmt = null;					//声明预处理对象
	ResultSet rs = null;
	List<Photo> list = new ArrayList<Photo>();
	try {
		pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
		pstmt.setInt(1, page.getBeginIndex());		//查询起始点
		pstmt.setInt(2, page.getEveryPage());		//查询记录数
		rs = pstmt.executeQuery();				    //执行查询
		while(rs.next()) {
		Photo photo=new Photo();
		photo.setId(rs.getInt(1));
		photo.setTitle(rs.getString(2));
		photo.setWisdom(rs.getString(3));
		photo.setContent(rs.getString(4));
		photo.setPath(rs.getString(5));
		photo.setPublishdate(rs.getString(6));
		photo.setAuthor(rs.getString(7));
		list.add(photo);//添加
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		DBconnection.close(rs);								//关闭结果集对象
		DBconnection.close(pstmt);							//关闭预处理对象
		DBconnection.close(conn);							//关闭连接对象
	}
	return list;
	
}
//查询照片
public List<Photo> selectPhoto(int begin){
	Connection conn = DBconnection.getConnection();	//获得连接对象
	String findSQL = "select * from photo " +
				 "order by id desc limit ?,6";
	PreparedStatement pstmt = null;					//声明预处理对象
	ResultSet rs = null;
	List<Photo> list = new ArrayList<Photo>();
	try {
		pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
		pstmt.setInt(1, begin);
		rs = pstmt.executeQuery();				    //执行查询
		while(rs.next()) {
		Photo photo=new Photo();
		photo.setId(rs.getInt(1));
		photo.setTitle(rs.getString(2));
		photo.setWisdom(rs.getString(3));
		photo.setContent(rs.getString(4));
		photo.setPath(rs.getString(5));
		photo.setPublishdate(rs.getString(6));
		photo.setAuthor(rs.getString(7));
		list.add(photo);//添加
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		DBconnection.close(rs);								//关闭结果集对象
		DBconnection.close(pstmt);							//关闭预处理对象
		DBconnection.close(conn);							//关闭连接对象
	}
	return list;
	
}
//删除照片
public void deletePhoto(int id){
	  Connection con = DBconnection.getConnection();
		String sql = "delete from photo where id=?";
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
}
