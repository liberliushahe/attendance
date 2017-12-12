package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.work.entity.Employee;
import com.work.entity.OwnReport;
import com.work.entity.OwnReportDetail;
import com.work.util.BASE64;
import com.work.util.DBconnection;

/**
 * @author: jijiuxue
 * @date:2017-12-4 上午9:43:14
 * @version :1.0.0
 * 
 */
@Repository
public class OwnReportDaoDetailImpl implements OwnReportDetailDao {

	public void addOwnReportDetail(OwnReportDetail ownReportDetail) {
		Connection con = DBconnection.getConnection();
		String sql = "INSERT INTO ownreportdetail(username,btime,wtime,wdesc,wlog,wtask) values(?,?,?,?,?,?)";

		PreparedStatement prep = null;
		try {						
			prep = con.prepareStatement(sql);
			prep.setString(1, ownReportDetail.getUsername());
			prep.setString(2, ownReportDetail.getBtime());
			prep.setString(3, ownReportDetail.getWtime());
			prep.setString(4, ownReportDetail.getWdesc());
			prep.setString(5, ownReportDetail.getWlog());
			prep.setString(6, ownReportDetail.getWtask());
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		
	}

    /**
     * 删除日志记录
     */
	public void deleteOwnReportDetail() {
		Connection con = DBconnection.getConnection();
		String sql = "delete from  ownreportdetail";

		PreparedStatement prep = null;
		try {						
			prep = con.prepareStatement(sql);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		
	}

	public List<OwnReportDetail> getAllOwnReportDetailByUsername(String username) {
		// TODO Auto-generated method stub
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "SELECT username,btime,wtime,wdesc,wlog,wtask FROM ownreportdetail where username=?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<OwnReportDetail> list = new ArrayList<OwnReportDetail>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();				    //执行查询
			while(rs.next()) {
			OwnReportDetail reportdetail=new OwnReportDetail();
			
			reportdetail.setUsername(rs.getString(1));
			reportdetail.setBtime(rs.getString(2));
			reportdetail.setWtime(rs.getString(3));
			reportdetail.setWdesc(rs.getString(4));
			reportdetail.setWlog(rs.getString(5));
			reportdetail.setWtask(rs.getString(6));
			
			list.add(reportdetail);//添加
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

	
}
