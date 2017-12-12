package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.work.entity.Department;
import com.work.entity.LinkMan;
import com.work.util.DBconnection;
import com.work.util.Page;
@Repository
public class LinkManDaoImpl implements LinkManDao {
	
    
	public void addLinkMan(LinkMan linkman) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		PreparedStatement prep = null;	
		try {
			String sql="insert into linkman(name,phone,qq,matter,address,email,job,departid) values (?,?,?,?,?,?,?,?)";
			prep = con.prepareStatement(sql);
			prep.setString(1,linkman.getName() );
			prep.setString(2, linkman.getPhone());
			prep.setString(3, linkman.getQq());
			prep.setString(4, linkman.getMatter());
			prep.setString(5, linkman.getAddress());
			prep.setString(6,linkman.getEmail() );
			prep.setString(7, linkman.getJob());
			prep.setInt(8,linkman.getDepartid());
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		
	}

	public void updateLinkMan(LinkMan linkman) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		PreparedStatement prep = null;	
		try {
			String sql="update linkman set name=?,phone=?,qq=?,matter=?,address=?,email=?,job=?,departid=? where id=?";
			prep = con.prepareStatement(sql);
			prep.setString(1,linkman.getName() );
			prep.setString(2, linkman.getPhone());
			prep.setString(3, linkman.getQq());
			prep.setString(4, linkman.getMatter());
			prep.setString(5, linkman.getAddress());
			prep.setString(6,linkman.getEmail() );
			prep.setString(7, linkman.getJob());
			prep.setInt(8,linkman.getDepartid());
			prep.setInt(9, linkman.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	}

	public void deleteLinkMan(int id) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		String sql = "delete from linkman where id=?";
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

	public List<LinkMan> queryLinkManByPage(Page page) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		String findSQL = "select t.id,t.name,t.qq,t.matter,t.address,t.email,t.job,t.phone,(select departname from department  b where b.departid =t.departid )  as department from linkman t order by t.id asc  limit ?,? ";
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		List<LinkMan> list = new ArrayList<LinkMan>();  
		try {
			pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, page.getBeginIndex());		//查询起始点
			pstmt.setInt(2, page.getEveryPage());		//查询记录数
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				LinkMan linkman = new LinkMan();
				Department department=new Department();
				linkman.setId(rs.getInt(1));
				linkman.setName(rs.getString(2));
				linkman.setQq(rs.getString(3));
				linkman.setMatter(rs.getString(4));
				linkman.setAddress(rs.getString(5));
				linkman.setEmail(rs.getString(6));
				linkman.setJob(rs.getString(7));
				linkman.setPhone(rs.getString(8));
				linkman.setDepartname(rs.getString(9));
				department.setDepartname(rs.getString(9));
				linkman.setDepart(department);
				list.add(linkman);	
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

	public List<LinkMan> queryLinkManByName(String name,String depart,String key){
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		String findSQL = "select t.id,t.name,t.qq,t.matter,t.address,t.email,t.job,t.phone,(select departname from department  b where b.departid =t.departid )  as department from linkman t ";
		if(name!="" && name.length()!=0 && !name.equals("")){
			findSQL=findSQL+" where t.name='"+name+"'";
		}
		if(!depart.equals("0")){
			int departid=Integer.parseInt(depart);
			if(name!="" && name.length()!=0 && !name.equals("")){
				depart=" and t.departid="+departid+"";
			}else{
				depart=" where t.departid="+departid+"";
			}
			
			findSQL=findSQL+depart;
		}
		if(key!=null&& key.length()!=0 && key!=" "){
			if(name!="" && name.length()!=0 && !name.equals("") || !depart.equals("0")){
				key=" and t.matter like '%"+key+"%'";
			}else{
				key=" where t.matter like '%"+key+"%'";
			}
			
			findSQL=findSQL+key;
		}	
		PreparedStatement pstmt = null;			
		ResultSet rs = null; 
		List<LinkMan> list = new ArrayList<LinkMan>(); 
		try {
			pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				//执行查询
			
			while(rs.next()) {	
				LinkMan linkman = new LinkMan();
				linkman.setId(rs.getInt(1));
				linkman.setName(rs.getString(2));
				linkman.setQq(rs.getString(3));
				linkman.setMatter(rs.getString(4));
				linkman.setAddress(rs.getString(5));
				linkman.setEmail(rs.getString(6));
				linkman.setJob(rs.getString(7));
				linkman.setPhone(rs.getString(8));
				linkman.setDepartname(rs.getString(9));	
				list.add(linkman);
				
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



	public int queryLinkManCount() {
		// TODO Auto-generated method stub
		Connection conn = DBconnection.getConnection();	//获得连接对象			
		String findSQL = "select count(*) from linkman";
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
   //获取所有模糊姓名
	public List<String> queryLinkManByLikeName(String name){
		// TODO Auto-generated method stub
		
		Connection con = DBconnection.getConnection();
		String findSQL = "select t.name from linkman t where t.name like '"+name+"%' ";			
		PreparedStatement pstmt = null;	
		ResultSet rs = null; 
		List<String> list = new ArrayList<String>(); 
		try {
			pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				//执行查询
			
			while(rs.next()) {												
				list.add(rs.getString(1));				
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
	//批量删除联系人记录
	public void batchDeleteLinkMan(String[] id) {
				Connection con = DBconnection.getConnection();
				String sql="delete from linkman where id in(0"; 
				
				for(int i=0;i<id.length;i++) 
				{ 
				  sql+=","+id[i]; 
				} 
				sql+=")"; 						
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

	public LinkMan queryLinkManById(int id) {
		// TODO Auto-generated method stub
		Connection con = DBconnection.getConnection();
		String findSQL = "select t.id,t.name,t.qq,t.matter,t.address,t.email,t.job,t.phone,(select departname from department  b where b.departid =t.departid )  as department from linkman t where t.id=? ";
		PreparedStatement pstmt = null;			
		ResultSet rs = null; 
		LinkMan linkman = new LinkMan();
		try {
			pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();				//执行查询			
			while(rs.next()) {			
				linkman.setId(rs.getInt(1));
				linkman.setName(rs.getString(2));
				linkman.setQq(rs.getString(3));
				linkman.setMatter(rs.getString(4));
				linkman.setAddress(rs.getString(5));
				linkman.setEmail(rs.getString(6));
				linkman.setJob(rs.getString(7));
				linkman.setPhone(rs.getString(8));
				linkman.setDepartname(rs.getString(9));	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(pstmt);
			DBconnection.close(rs);
		}
		return linkman;
	}
}
