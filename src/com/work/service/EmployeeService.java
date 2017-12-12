package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.EmployeeDao;
import com.work.entity.Employee;
import com.work.util.Page;
@Service
public class EmployeeService {
	
	@Autowired private EmployeeDao employeedao;
	public String login(String username,String password){
		return employeedao.login(username, password);
	}
	public List<Employee> queryEmail(){
		return employeedao.queryEmail();
	}
	public String role(String username){
		return employeedao.role(username);
	}
	public Employee queryInfo(String userid){
		return employeedao.queryInfo(userid);
	}
	 //查询用户ip是否注册
	public String getUserIp(String ip){
		return employeedao.getUserIp(ip);
	}
	//自动生成考勤
	public List<Employee> queryIsAutoCreate(){
		return employeedao.queryIsAutoCreate();
	}
	//保存用户头像名称
	public void imagePath(String userid,String imagename){
		employeedao.imagePath(userid, imagename);
	}
	//查询所有用户数量
		public int findAllCount( ){
			return employeedao.findAllCount();
	}
		//增加用户
		public String AddEmployee(Employee employee){
			return employeedao.AddEmployee(employee);
	}
		//查询用户
		public List<Employee> queryEmployee(Page page){
			return employeedao.queryEmployee(page);
	}
		//更新用户信息
		public void updateEmployee(Employee employee){
			 employeedao.updateEmployee(employee);
	}
		//删除用户信息
		public void deleteEmployee(int id){
			 employeedao.deleteEmployee(id);
	}
		//修改密码
		public int modifyPass(String email,String password){
			return employeedao.modifyPass(email, password);
		}
		//查询用户邮箱是否注册
		public String getUserEmail(String email){
			return employeedao.getUserEmail(email);
		}
		//查询用户是否在线和离线
		public   List<Employee> queryEmployeeOnline(){
			return employeedao.queryEmployeeOnline();
		}
		//修改用户状态(离线和在线)
		public void modifyOnline(String userid,int online){
			employeedao.modifyOnline(userid, online);
		}
		//修改用户ip
		public void modifyUserIp(String ip,String userid){
			employeedao.modifyUserIp(ip, userid);
		}
		public void modifyUserSure(String userid){
			employeedao.modifyUserSure(userid);
		}
		//修改用户确认状态归零
		public void modifyUserSureToZero(){
			employeedao.modifyUserSureToZero();
		}
		//查询确认的人员
		public   List<String> queryEmployeeSure(){
			return employeedao.queryEmployeeSure();
		}
		//查询没有确认的人员
		public   List<Employee> queryEmployeeUnSure(){
			return employeedao.queryEmployeeUnSure();
		}
}
