package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.work.entity.Notice;
import com.work.util.DBconnection;
import com.work.util.Page;
@Repository
public class NoticeDao {
	//显示最新公告
	public List<Notice> queryNotice(){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "SELECT * FROM notice order by id desc limit 0,6";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				    //执行查询
			while(rs.next()) {
			Notice notice=new Notice();
			notice.setId(rs.getInt(1));
			notice.setTitle(rs.getString(2));
			notice.setContent(rs.getString(3));
			notice.setAuthor(rs.getString(4));
			notice.setPublishdate(rs.getString(5));
			list.add(notice);//添加
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
	
	//查询公告总数
	public int noticeAllCount(){
		Connection conn = DBconnection.getConnection();	//获得连接对象			
		String findSQL = "select count(*) from notice";
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
	
	//查询公告列表
	public List<Notice> queryNotice(Page page){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select * from notice " +
					 "order by id asc limit ?,?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, page.getBeginIndex());		//查询起始点
			pstmt.setInt(2, page.getEveryPage());		//查询记录数
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
			Notice notice=new Notice();
			notice.setId(rs.getInt(1));
			notice.setTitle(rs.getString(2));
			notice.setContent(rs.getString(3));
			notice.setAuthor(rs.getString(4));
			notice.setPublishdate(rs.getString(5));
			list.add(notice);//添加
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
	
	
	
	//增加公告
    public void addNotice(Notice notice){
    	Connection con = DBconnection.getConnection();
    	String sql = "insert into notice(title,content,author,publishdate) values(?,?,?,?)";
    	
    	PreparedStatement prep = null;		
    	try {
    		prep = con.prepareStatement(sql);
    		prep.setString(1, notice.getTitle());
    		prep.setString(2, notice.getContent());
    		prep.setString(3,notice.getAuthor());
    		prep.setString(4, notice.getPublishdate() );

    		prep.executeUpdate();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		DBconnection.close(con);
    		DBconnection.close(prep);
    	}
    }
	//更新公告
    public void updateNotice(Notice notice){
    	Connection con = DBconnection.getConnection();
    	String sql = "update  notice set title=?,content=?,author=?,publishdate=?  where id=?";
    	PreparedStatement prep = null;		
    	try {
    		prep = con.prepareStatement(sql);

    		prep.setString(1, notice.getTitle());
    		prep.setString(2, notice.getContent());
    		prep.setString(3,notice.getAuthor());
    		prep.setString(4, notice.getPublishdate() );
    		prep.setInt(5, notice.getId());
    		prep.executeUpdate();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		DBconnection.close(con);
    		DBconnection.close(prep);
    	}	
    }
    //删除公告
  public void deleteNotice(int id){
	  Connection con = DBconnection.getConnection();
		String sql = "delete from notice where id=?";
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
