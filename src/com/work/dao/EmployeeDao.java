package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import com.work.entity.Employee;
import com.work.util.DBconnection;
import com.work.util.Page;
@Repository
public class EmployeeDao {
	//查询用户状态记录
	public List<Employee> queryUserStatusIsUse(){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		
		String findSQL = "select username from employee where status=1 ";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setUsername(rs.getString(1));
				
				
				list.add(employee);//添加
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
	//查询有效账户姓名和邮箱，邮件提醒需要
	public List<Employee> queryEmail(){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		
		String findSQL = "select username,email from employee where username!='' and email!='' and isswitch=1  and role!=1 and status!=0";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				Employee employ = new Employee();
				employ.setUsername(rs.getString(1));
				employ.setEmail(rs.getString(2));
				list.add(employ);//添加
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
	
	
	public String login(String username,String password){
		Connection con=DBconnection.getConnection();
		String sql="select count(*),status from employee where userid=? and password =?";		
		PreparedStatement prep=null;
		ResultSet result=null;
		String flag=null;
		try {
			 prep=con.prepareStatement(sql);
			 
			 prep.setString(1, username);
			 prep.setString(2, password);
			 result=prep.executeQuery();
			 if(result.next()){
				if(result.getInt(1)>0 && result.getInt(2)==1){
					flag="YES";
					
				}else if(result.getInt(1)>0 && result.getInt(2)==0){
					flag="ineffect";
				}								
				else{
					flag="NO";
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		return flag;
	}
    //查询用户权限
	public String role(String username){
		Connection con=DBconnection.getConnection();
		String sql="select role from employee where userid=?";		
		PreparedStatement prep=null;
		ResultSet result=null;
		String flag=null;
		try {
			 prep=con.prepareStatement(sql);
			 prep.setString(1, username);
			result=prep.executeQuery();
			if(result.next()){
				if(result.getString(1).equals("1")){
					flag="1";
					
				}else{
					flag="0";
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		return flag;
	}
	//查询用户信息
		public Employee queryInfo(String userid){
			Connection con=DBconnection.getConnection();
			String sql="select username,userid,role,status,phone,email,image from employee where userid=?";		
			PreparedStatement prep=null;
			ResultSet rs=null;
			Employee employ = new Employee();
			try {
				 prep=con.prepareStatement(sql);
				 prep.setString(1, userid);
				rs=prep.executeQuery();
				while(rs.next()) {
					
					employ.setUsername(rs.getString(1));
					employ.setUserid(rs.getString(2));
					employ.setRole(rs.getInt(3));
					employ.setStatus(rs.getInt(4));
					employ.setPhone(rs.getString(5));
					employ.setEmail(rs.getString(6));
					employ.setImage(rs.getString(7));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBconnection.close(con);
				DBconnection.close(prep);
			}
			return employ;
		}
		 //查询用户ip是否注册
		public String getUserIp(String ip){
			Connection con=DBconnection.getConnection();
			String sql="select count(*) from employee where ip=?";		
			PreparedStatement prep=null;
			ResultSet result=null;
			String flag=null;
			try {
				 prep=con.prepareStatement(sql);	
				 prep.setString(1, ip);
				result=prep.executeQuery();
				if(result.next()){
					if(result.getInt(1)==1){
						flag="1";
						
					}else{
						flag="0";
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBconnection.close(con);
				DBconnection.close(prep);
			}
			return flag;
		}
		/*  查询是否需要自动生成考勤的用户
		 * @param username
		 * @return list
		 */

	public List<Employee> queryIsAutoCreate(){
		Connection con=DBconnection.getConnection();
		List<Employee> list=new ArrayList<Employee>();
		String sql="select username from employee where isflag=1";		
		PreparedStatement prep=null;
		ResultSet rs=null;
		
		try {
			 prep=con.prepareStatement(sql);					
			rs=prep.executeQuery();
			while(rs.next()) {
				Employee employ = new Employee();
				employ.setUsername(rs.getString(1));
				list.add(employ);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		return list;
		
	}
	//保存用户头像名称
	public void imagePath(String userid,String imagename){
		Connection con=DBconnection.getConnection();
		String sql="update employee set image=? where userid=?";		
		PreparedStatement prep=null;
		try {
			 prep=con.prepareStatement(sql);	
			 prep.setString(1, imagename);
			 prep.setString(2, userid);
			prep.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBconnection.close(con);
			DBconnection.close(prep);
		}
		
	}
	//用户信息添加
		public String AddEmployee(Employee employee){
			Connection con = DBconnection.getConnection();
			String insertinfo = null;
			PreparedStatement prep = null;	
			try {
				String sql="insert into employee(ip,username,password,userid,role,status,phone,email,isswitch,isflag,image) values (?,?,?,?,?,?,?,?,?,?,?)";
				prep = con.prepareStatement(sql);
				prep.setString(1, employee.getIp());
				prep.setString(2, employee.getUsername());
				prep.setString(3, employee.getPassword());
				prep.setString(4, employee.getUserid());
				prep.setInt(5, employee.getRole());
				prep.setInt(6, employee.getStatus());
				prep.setString(7, employee.getPhone());
				prep.setString(8, employee.getEmail());
				prep.setInt(9, employee.getIsswitch());
				prep.setInt(10, employee.getIsflag());
				prep.setString(11,"headimage.jpg");
				prep.executeUpdate();
				insertinfo="1";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				insertinfo="0";
			} finally {
				DBconnection.close(con);
				DBconnection.close(prep);
			}
			return insertinfo;
		}
		//查询用户信息
		public   List<Employee> queryEmployee(Page page){
			Connection con = DBconnection.getConnection();
			String findSQL = "select * from employee  order by id asc limit ?,?";
			PreparedStatement pstmt = null;	
			ResultSet rs = null;
			List<Employee> list = new ArrayList<Employee>();  
			try {
				pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
				pstmt.setInt(1, page.getBeginIndex());		//查询起始点
				pstmt.setInt(2, page.getEveryPage());		//查询记录数
				rs = pstmt.executeQuery();				//执行查询
				while(rs.next()) {
					Employee employ = new Employee();
					employ.setId(rs.getInt(1));
					employ.setIp(rs.getString(2));;
					employ.setUsername(rs.getString(3));
					employ.setPassword(rs.getString(4));
					employ.setUserid(rs.getString(5));
					employ.setRole(rs.getInt(6));
					employ.setStatus(rs.getInt(7));
					employ.setPhone(rs.getString(8));
					employ.setEmail(rs.getString(9));
					employ.setIsswitch(rs.getInt(10));
					employ.setIsflag(rs.getInt(11));
					list.add(employ);	
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
		
		//查询用户是否在线和离线
		public   List<Employee> queryEmployeeOnline(){
			Connection con = DBconnection.getConnection();
			String findSQL = "select image,username,online,id from employee where role!=1";
			PreparedStatement pstmt = null;	
			ResultSet rs = null;
			List<Employee> list = new ArrayList<Employee>();  
			try {
				pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
				rs = pstmt.executeQuery();				//执行查询
				while(rs.next()) {
					Employee employ = new Employee();
					employ.setImage(rs.getString(1));
					employ.setUsername(rs.getString(2));
					employ.setOnline(rs.getInt(3));
					employ.setId(rs.getInt(4));
					list.add(employ);	
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
		
		
		//查询当前所有用户数量
		public int findAllCount() {
			// TODO Auto-generated method stub
			Connection conn = DBconnection.getConnection();	//获得连接对象			
			String findSQL = "select count(*) from employee";
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
		//更新用户
		public void updateEmployee(Employee employee) {
			Connection con = DBconnection.getConnection();
			String sql = "update employee set ip=?,username=?,password=?,userid=?,role=?,status=?,phone=?,email=?,isswitch=?,isflag=?  where id="+employee.getId()+"";
			PreparedStatement prep = null;	
			try {
				prep = con.prepareStatement(sql);
				prep.setString(1, employee.getIp());
				prep.setString(2, employee.getUsername());
				prep.setString(3, employee.getPassword());
				prep.setString(4, employee.getUserid());
				prep.setInt(5, employee.getRole());
				prep.setInt(6, employee.getStatus());
				prep.setString(7, employee.getPhone());
				prep.setString(8, employee.getEmail());
				prep.setInt(9, employee.getIsswitch());
				prep.setInt(10, employee.getIsflag());
				prep.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBconnection.close(con);
				DBconnection.close(prep);
			}
			
		}
		//删除记录
			public void deleteEmployee(int id) {
				Connection con = DBconnection.getConnection();
				String sql = "delete from employee where id=?";
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
		//修改用户密码
			public int modifyPass(String email,String password){
				int flag;
				Connection con = DBconnection.getConnection();
				String sql = "update employee set password=? where email=?";
				PreparedStatement prep = null;		
				try {
					prep = con.prepareStatement(sql);
					prep.setString(1, password);
					prep.setString(2, email);
					prep.executeUpdate();
					flag=1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag=0;
				} finally {
					DBconnection.close(con);
					DBconnection.close(prep);
				}
				return flag;
			}
			//修改用户状态(离线和在线)
			public void modifyOnline(String userid,int online){
				System.out.println(userid);
				Connection con = DBconnection.getConnection();
				String sql = "update employee set online=? where userid=?";
				PreparedStatement prep = null;		
				try {
					prep = con.prepareStatement(sql);
					prep.setInt(1, online);
					prep.setString(2, userid);
					prep.executeUpdate();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				} finally {
					DBconnection.close(con);
					DBconnection.close(prep);
				}
				
			}
			
			
			//查询用户邮箱是否注册
			public String getUserEmail(String email){
				Connection con=DBconnection.getConnection();
				String sql="select count(*) from employee where email=?";		
				PreparedStatement prep=null;
				ResultSet result=null;
				String flag=null;
				try {
					 prep=con.prepareStatement(sql);	
					 prep.setString(1, email);
					result=prep.executeQuery();
					if(result.next()){
						if(result.getInt(1)!=0){
							flag="1";
							
						}else{
							flag="0";
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DBconnection.close(con);
					DBconnection.close(prep);
				}
				return flag;
			}
			//查询用户ip地址
			public String getUserOwnIp(String userid){
				Connection con=DBconnection.getConnection();
				String sql="select ip from employee where userid=?";		
				PreparedStatement prep=null;
				ResultSet result=null;
				String ip=null;
				
				try {
					 prep=con.prepareStatement(sql);	
					 prep.setString(1, userid);
					 result=prep.executeQuery();
					 while(result.next()){
						ip= result.getString(1);
					 }
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DBconnection.close(con);
					DBconnection.close(prep);
				}
				return ip;
			}
			//实时更新用户ip地址
			public void modifyUserIp(String ip,String userid){
				Connection con = DBconnection.getConnection();
				String sql = "update employee set ip=? where userid=?";
				PreparedStatement prep = null;		
				try {
					prep = con.prepareStatement(sql);
					prep.setString(1, ip);
					prep.setString(2, userid);
					prep.executeUpdate();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				} finally {
					DBconnection.close(con);
					DBconnection.close(prep);
				}
				
			}
			//修改用户确认状态
			public void modifyUserSure(String userid){
				Connection con = DBconnection.getConnection();
				String sql = "update employee set sure=1 where userid=?";
				PreparedStatement prep = null;		
				try {
					prep = con.prepareStatement(sql);
					prep.setString(1, userid);
					prep.executeUpdate();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				} finally {
					DBconnection.close(con);
					DBconnection.close(prep);
				}
				
			}
			//修改用户确认状态归零
			public void modifyUserSureToZero(){
				Connection con = DBconnection.getConnection();
				String sql = "update employee set sure=0 ";
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
			//查询确认的人员
			public   List<String> queryEmployeeSure(){
				Connection con = DBconnection.getConnection();
				String findSQL = "select username from employee where sure=1 and role!=1";
				PreparedStatement pstmt = null;	
				ResultSet rs = null;
				List<String> list = new ArrayList<String>();  
				try {
					pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
					rs = pstmt.executeQuery();				//执行查询
					while(rs.next()) {
                    String username=new String();
                    username =rs.getString(1);
					list.add(username);	
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
			//没有确认的人员名字
			public   List<Employee> queryEmployeeUnSure(){
				Connection con = DBconnection.getConnection();
				String findSQL = "select username,email from employee where sure=0 and role!=1";
				PreparedStatement pstmt = null;	
				ResultSet rs = null;
				List<Employee> list = new ArrayList<Employee>();  
				try {
					pstmt = con.prepareStatement(findSQL);		//获得预处理对象并赋值
					rs = pstmt.executeQuery();				//执行查询
					while(rs.next()) {
                    Employee employee=new Employee();
                    employee.setUsername(rs.getString(1));
                    employee.setEmail(rs.getString(2));
					list.add(employee);	
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

	
		

