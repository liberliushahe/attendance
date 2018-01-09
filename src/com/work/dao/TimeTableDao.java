package com.work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.work.entity.Report;
import com.work.entity.TimeTable;
import com.work.util.DBconnection;
import com.work.util.Page;

@Repository
public class TimeTableDao {
	//格式化日期
	public String formatdate(){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy/M/d");
		String currentdate=format.format(date);
		return currentdate;
	}
	
	//查询用户打卡记录
		public List<TimeTable> queryTimeTableByUserName(String username){
			Connection conn = DBconnection.getConnection();	//获得连接对象
			
			String findSQL = "select * from timetable " +
						" where username='"+username+"' order by  substr( substr(date,6),position('/',substr(date,6))+1)+0  asc";
			PreparedStatement pstmt = null;					//声明预处理对象
			ResultSet rs = null;
			List<TimeTable> list = new ArrayList<TimeTable>();
			try {
				pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
				rs = pstmt.executeQuery();				//执行查询
				while(rs.next()) {
					TimeTable table = new TimeTable();
					table.setId(rs.getInt(1));
					table.setUsername(rs.getString(2));
					table.setDate(rs.getString(3));
					table.setStarttime(rs.getString(4));
					table.setEndtime(rs.getString(5));
					table.setWeekend(rs.getString(6));
					table.setHoliday(rs.getString(7));
					table.setExplain(rs.getString(8));
					list.add(table);//添加
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
	//查询用户某个月记录所有的打卡记录
	public List<TimeTable> queryTimeTable(String username,String start,Page page){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String	sqlstring="";
		if(!start.equals("")){
		 sqlstring=" AND date like '"+start+"%'";
		}
		
		String findSQL = "select * from timetable " +
					" where username='"+username+"'"+sqlstring+" order by  substr( substr(date,6),position('/',substr(date,6))+1)+0 asc limit ?,?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<TimeTable> list = new ArrayList<TimeTable>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, page.getBeginIndex());		//查询起始点
			pstmt.setInt(2, page.getEveryPage());		//查询记录数
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				TimeTable table = new TimeTable();
				table.setId(rs.getInt(1));
				table.setUsername(rs.getString(2));
				table.setDate(rs.getString(3));
				table.setStarttime(rs.getString(4));
				table.setEndtime(rs.getString(5));
				table.setWeekend(rs.getString(6));
				table.setHoliday(rs.getString(7));
				table.setExplain(rs.getString(8));
				list.add(table);//添加
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
	//查询iP地址对应用户名
	public String findNameByIp(String ip) {
		// TODO Auto-generated method stub
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select username from employee where ip='"+ip+"'";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		String name=null;
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();					//执行查询
			if(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBconnection.close(rs);						//关闭结果集对象
			DBconnection.close(pstmt);					//关闭预处理对象
			DBconnection.close(conn);					//关闭连接对象
		}
		return name;
	}
	
	//查询用户所属记录数
	public int findAllCountByUser(String username,String start) {
		// TODO Auto-generated method stub
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String sqlstring="";
		if(start!=""){
			 sqlstring=" AND date like '"+start+"%'";
			}
		String findSQL = "select count(*) from timetable where username='"+username+"'"+sqlstring+"";
		System.out.println("总数"+findSQL);
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
	//查询用户当月所属记录数
		public int findCurrentAllCountByUser(String username) {
			// TODO Auto-generated method stub
			Connection conn = DBconnection.getConnection();	//获得连接对象			
			String findSQL = "select count(*) from timetable where username=(select username from employee where userid='"+username+"')";
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
		
		//查询用户当月所属记录数通过姓名
				public int findCurrentAllCountByUsername(String username) {
					// TODO Auto-generated method stub
					Connection conn = DBconnection.getConnection();	//获得连接对象			
					String findSQL = "select count(*) from timetable where username='"+username+"'";
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
	//查询所有记录数
	public int findAllCount() {
		// TODO Auto-generated method stub
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select count(*) from timetable";
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
	
	public List<TimeTable> queryTimeTable(Page page){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select * from timetable " +
					"order by  substr( substr(date,6),position('/',substr(date,6))+1)+0 asc limit ?,?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<TimeTable> list = new ArrayList<TimeTable>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, page.getBeginIndex());		//查询起始点
			pstmt.setInt(2, page.getEveryPage());		//查询记录数
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				TimeTable table = new TimeTable();
				table.setId(rs.getInt(1));
				table.setUsername(rs.getString(2));
				table.setDate(rs.getString(3));
				table.setStarttime(rs.getString(4));
				table.setEndtime(rs.getString(5));
				table.setWeekend(rs.getString(6));
				table.setHoliday(rs.getString(7));
				table.setExplain(rs.getString(8));
				list.add(table);//添加
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
	//查询用户当月记录
	public List<TimeTable> queryUserTimeTable(String username,Page page){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select * from timetable " +
					" where username=(select username from employee where userid='"+username+"')  order by  substr( substr(date,6),position('/',substr(date,6))+1)+0 asc limit ?,?";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<TimeTable> list = new ArrayList<TimeTable>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, page.getBeginIndex());		//查询起始点
			pstmt.setInt(2, page.getEveryPage());		//查询记录数
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				TimeTable table = new TimeTable();
				table.setId(rs.getInt(1));
				table.setUsername(rs.getString(2));
				table.setDate(rs.getString(3));
				table.setStarttime(rs.getString(4));
				table.setEndtime(rs.getString(5));
				table.setWeekend(rs.getString(6));
				table.setHoliday(rs.getString(7));
				table.setExplain(rs.getString(8));
				list.add(table);//添加
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
	//判断当日打卡是否已经完成
	public String isOverTimeTable(String ip){
		SimpleDateFormat format=new SimpleDateFormat("yyyy/M/d");
		Date date=new Date();
		String currentdate=format.format(date);
		Connection con=DBconnection.getConnection();
		String sql="select count(*) from timetable where username=(select username from employee where ip='"+ip+"') and date in ('"+currentdate+"') and starttime!='' and endtime!=''";		
		PreparedStatement prep=null;
		ResultSet result=null;
		String flag=null;
		try {
			prep=con.prepareStatement(sql);					
			result=prep.executeQuery();
			if(result.next()){
				if(result.getInt(1)>0){
					flag="Yes";
					
				}else{
					flag="No";
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
	//查询表中是否已经有记录
	public String isHasTimeTable(String ip){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy/M/d");
		String currentdate=format.format(date);
		Connection con=DBconnection.getConnection();
		String sql="select count(*) from timetable where username=(select username from employee where ip='"+ip+"') and date in ('"+currentdate+"')";		
		PreparedStatement prep=null;
		ResultSet result=null;
		String flag=null;
		try {
			prep=con.prepareStatement(sql);					
			result=prep.executeQuery();
			if(result.next()){
				if(result.getInt(1)>0){
					flag="Y";
					
				}else{
					flag="N";
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

    //开始上班打卡
	public void startwork(String ip) {
		Connection con = DBconnection.getConnection();
		String sql = "insert into timetable(username,date,starttime) values((select username from employee where ip='"
				+ ip + "'),?,?)";
		PreparedStatement prep = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat format1 = new SimpleDateFormat("H:mm:ss");
		String time = format.format(date);
		String start = format1.format(date);

		try {
			prep = con.prepareStatement(sql);

			prep.setString(1, time);
			prep.setString(2, start);

			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	}
    //下班打卡
	public void endwork(String ip) {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat format1=new SimpleDateFormat("H:mm:ss");		
		String time=format.format(date);
		String endtime=format1.format(date);
		Connection con=DBconnection.getConnection();
		String sql="update timetable set endtime='"+endtime+"' where username=(select username from employee where ip='"+ip+"') and date in ('"+time+"')";
		String sql1 = "insert into timetable(username,date,starttime) values((select username from employee where ip='"
				+ ip + "'),?,?)";
		
		PreparedStatement prep=null;
		
		
		try {
			
			if( isHasTimeTable(ip).equals("N")){
				prep=con.prepareStatement(sql1);
				prep.setString(1, time);
				prep.setString(2, "8:56:12");
				prep.executeUpdate();
				prep=con.prepareStatement(sql);
				prep.executeUpdate();
			}else{
				 prep=con.prepareStatement(sql);
				 prep.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	
	}
	//增加打卡记录
    public void addTimeTable(TimeTable timetable){
    	Connection con = DBconnection.getConnection();
		String sql = "insert into timetable(username,date,starttime,endtime,weekend,holiday,explain1) values(?,?,?,?,?,?,?)";
		
		PreparedStatement prep = null;		
		try {
			prep = con.prepareStatement(sql);
			prep.setString(1, timetable.getUsername());
			prep.setString(2, timetable.getDate());
			prep.setString(3, timetable.getStarttime());
			prep.setString(4, timetable.getEndtime());
			prep.setString(5, timetable.getWeekend());
			prep.setString(6, timetable.getHoliday());
			prep.setString(7, timetable.getExplain());
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
    }
	//更新记录
	public void updateTimeTable(TimeTable timetable) {
		Connection con = DBconnection.getConnection();
		String sql = "update  timetable set username=?,date=?,starttime=?,endtime=?,weekend=?,holiday=?,explain1=?  where id="+timetable.getId()+"";
		PreparedStatement prep = null;		
		try {
			prep = con.prepareStatement(sql);

			prep.setString(1, timetable.getUsername());
			prep.setString(2, timetable.getDate());
			prep.setString(3, timetable.getStarttime());
			prep.setString(4, timetable.getEndtime());
			prep.setString(5, timetable.getWeekend());
			prep.setString(6, timetable.getHoliday());
			prep.setString(7, timetable.getExplain());
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}
	}
	
	//批量删除记录
		public void batchDeleteTimeTable(String[] id) {
			Connection con = DBconnection.getConnection();
			String sql="delete from timetable where id in(0"; 
			
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
	
	
   //删除记录
	public void deleteTimeTable(int id) {
		Connection con = DBconnection.getConnection();
		String sql = "delete from timetable where id=?";
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
	public List<TimeTable> queryTimeTableByDate(String date){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select * from timetable where date like '"+date+"%'";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<TimeTable> list = new ArrayList<TimeTable>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			rs = pstmt.executeQuery();				//执行查询
			while(rs.next()) {
				TimeTable table = new TimeTable();
				table.setId(rs.getInt(1));
				table.setUsername(rs.getString(2));
				table.setDate(rs.getString(3));
				table.setStarttime(rs.getString(4));
				table.setEndtime(rs.getString(5));
				table.setWeekend(rs.getString(6));
				table.setHoliday(rs.getString(7));
				table.setExplain(rs.getString(8));
				list.add(table);//添加
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
	//查询用户迟到次数
	public int findAllCountByLate(String username,String late) {
		// TODO Auto-generated method stub
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL=null;
		if(late.equals("1")){
			 findSQL = "select count(*) from timetable where username=(select username from employee where userid='"+username+"') AND    str_to_date(starttime, '%h:%i:%s')>str_to_date('09:00:00', '%h:%i:%s')";
		}else{
			 findSQL = "select count(*) from timetable where  str_to_date(starttime, '%h:%i:%s')>str_to_date('09:00:00', '%h:%i:%s')";
		}
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
	//查询用户早退次数
	public int findAllCountByEarly(String username,String early) {
			// TODO Auto-generated method stub
			Connection conn = DBconnection.getConnection();	//获得连接对象
			String findSQL=null;
			if(early.equals("1")){
				 findSQL = "select count(*) from timetable where username=(select username from employee where userid='"+username+"') AND    str_to_date(endtime, '%h:%i:%s')<str_to_date('18:00:00', '%h:%i:%s')  ";
			}else{
				 findSQL = "select count(*) from timetable where  str_to_date(endtime, '%h:%i:%s')<str_to_date('18:00:00', '%h:%i:%s') ";
			}
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

	/*一键生成用户记录,该方法用于批量生成用户记录
	 * @param username date
	 * @return none
	 */
	public void createUser(String username,String date){
		Connection con = DBconnection.getConnection();
		String sql = "insert into timetable(username,date,starttime,endtime,weekend,holiday,explain1) values(?,?,?,?,?,?,?)";
		//获取当月多少天
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(5));
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR,year); 
		cal.set(Calendar.MONTH, month - 1);//Java月份从0开始算 
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		String str="";
		String str1="";//周日加班标识
		PreparedStatement prep = null;		
		try {
			prep = con.prepareStatement(sql);
			for(int i=1;i<=dateOfMonth;i++){
				str1=getWeek(month,i);
				prep.setString(1, username);
				prep.setString(2, date+"/"+i);
				prep.setString(3, "8:"+String.valueOf((int)(Math.random()*29+26))+":"+String.valueOf((int)(Math.random()*20+32)));
				prep.setString(4, "18:"+String.valueOf((int)(Math.random()*44+10))+":"+String.valueOf((int)(Math.random()*37+10)));
				prep.setString(5, str1);
				prep.setString(6, str);
				prep.setString(7, str);
				prep.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}	
	}
	/* 该方法用于同步用户数据
	 * @param username date
	 * @return none
	 */
	public void createUserReportBySynchro(String username,List<String> date){
		System.out.println();
		Connection con = DBconnection.getConnection();
		String sql = "insert into timetable(username,date,starttime,endtime,weekend,holiday,explain1) values(?,?,?,?,?,?,?)";
		String str="";
		String str1="";//周日加班标识
		PreparedStatement prep = null;		
		try {
			prep = con.prepareStatement(sql);
			for(int i=0;i<date.size();i++){
				String tempDate=date.get(i);
	            int month=Integer.parseInt(tempDate.substring(5,tempDate.lastIndexOf("/")));
	 			int day=Integer.parseInt(tempDate.substring(tempDate.lastIndexOf("/")+1));
				str1=getWeek(month,day);
				prep.setString(1, username);
				prep.setString(2, date.get(i));
				prep.setString(3, "8:"+String.valueOf((int)(Math.random()*29+26))+":"+String.valueOf((int)(Math.random()*20+32)));
				prep.setString(4, "18:"+String.valueOf((int)(Math.random()*44+10))+":"+String.valueOf((int)(Math.random()*37+10)));
				prep.setString(5, str1);
				prep.setString(6, str);
				prep.setString(7, str);
				prep.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(con);
			DBconnection.close(prep);
		}	
	}
   //清除数据库所有数据，序列号置0
   public String deleteData(){
	   Connection conn = DBconnection.getConnection();	//获得连接对象
		String deleteinfo=null;		
		String findSQL = "delete from timetable ";	
		//mysql数据库语法
		//String findSQL1="alter table timetable AUTO_INCREMENT=1";
		//h2数据库语法
		String findSQL1="ALTER sequence  SYSTEM_SEQUENCE_41649BB9_0259_40FC_BA91_222E3D82BF3A  RESTART WITH 1";
		PreparedStatement pstmt = null;					//声明预处理对象
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象
			pstmt.executeUpdate();	
			pstmt = conn.prepareStatement(findSQL1);	
			pstmt.executeUpdate();
			deleteinfo="1";
		} catch (SQLException e) {
			e.printStackTrace();
			deleteinfo="0";
		} finally{
			DBconnection.close(pstmt);					//关闭预处理对象
			DBconnection.close(conn);					//关闭连接对象
		}
		return deleteinfo;
   }
 //判断某一天是否是周末	
	public String getWeek(int month,int day){
		
		String str="";
		Calendar c=Calendar.getInstance();
		 if((c.MONTH-2)==0){
	        	c.set(Calendar.YEAR,c.get(Calendar.YEAR) - 1);
	        }
		c.set(Calendar.MONTH, month-1);	//设置月份
		c.set(Calendar.DAY_OF_MONTH,day);
		if(c.get(Calendar.DAY_OF_WEEK)==7){
			 str="是";
		}else if(c.get(Calendar.DAY_OF_WEEK)==1){
			 str="是";
		}else{
			 str="";
		}
		return str;
	}
	
	/**
	 * 报表展示方法
	 * @param username
	 * @return
	 */
	//返回用户当月每一天的上班打卡时间和下班打卡时间 线性图表显示
	public Report getReport(String username){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "select date,starttime,endtime from timetable  where username=(select username from employee where userid=?) order by  substr( substr(date,6),position('/',substr(date,6))+1)+0 asc";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<String> datelist=new ArrayList<String>();
		List<String> startlist=new ArrayList<String>();
		List<String> endlist=new ArrayList<String>();
		Report report=new Report();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值	
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();				//执行查询
			String date=null;
			String start=null;
			String end=null;
			int index=0;
			int index1=0;
			int index2=0;
			
			while(rs.next()) {
				
				 date=rs.getString(1);
				 start=rs.getString(2);
				 end=rs.getString(3);
				 index=date.lastIndexOf("/");
			     index1=start.lastIndexOf(":");
				 index2=end.lastIndexOf(":"); 
				 datelist.add(date.substring(index+1)+"号");
				 startlist.add(start.substring(0, index1).replace(":", "."));
				 endlist.add(end.substring(0, index2).replace(":", "."));				
				 
			}
			report.setDate(datelist);
			report.setStart(startlist);
			report.setEnd(endlist);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBconnection.close(rs);								//关闭结果集对象
			DBconnection.close(pstmt);							//关闭预处理对象
			DBconnection.close(conn);							//关闭连接对象
		}
		return report;
	}
	
	/**
	 * 报表展示方法展示每个用户当月上班次数
	 * @param null
	 * @return
	 */
	//返回用户当月所有打卡记录 线性图表显示
	public List<Report> getAllUserReport(){
		Connection conn = DBconnection.getConnection();	//获得连接对象
		String findSQL = "SELECT username,count(*) FROM TIMETABLE  group by username";
		PreparedStatement pstmt = null;					//声明预处理对象
		ResultSet rs = null;
		List<Report> list=new ArrayList<Report>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值	
			rs = pstmt.executeQuery();				//执行查询			
			while(rs.next()) {							
				Report report=new Report();
				report.setName(rs.getString(1));
				report.setCount(rs.getString(2));
				list.add(report);
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