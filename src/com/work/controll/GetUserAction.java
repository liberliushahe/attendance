package com.work.controll;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.work.entity.Employee;
import com.work.service.EmployeeService;
import com.work.util.Page;
import com.work.util.PageUtil;
import com.work.util.SendEmail;
@Controller
public class GetUserAction {

	/*设置日志对象，方便使用
	 * private static Logger log=Logger.getLogger(GetUserAction.class);
	 * 通过log对象配置不同的日志级别例如：log.info(),log.debug()等
	 * 如有日志输出需要可以放开
	 */
	@Autowired EmployeeService employeeservice;
	@RequestMapping("getUser.do")
     public String getUserList(HttpServletRequest request , Map<Object,String> model){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
		//查询当前注册用户数
		int totalCount=employeeservice.findAllCount();
		Page page=PageUtil.createPage(10, currentpage, totalCount);
		List<Employee> list=employeeservice.queryEmployee(page);
		request.setAttribute("List", list);
		request.setAttribute("page", page);
	    return "WEB-INF/admin/usermanager";
   }
	@RequestMapping("addUser.do") 
	@ResponseBody
	public String addUser(HttpServletRequest request){
	    String ip = request.getParameter("ip");
		String username= request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String userid = request.getParameter("name");
		String ro = request.getParameter("ro");
		int role=Integer.parseInt(ro);
		String st = request.getParameter("zt");
		int status =Integer.parseInt(st);
		String phone = request.getParameter("tel");
		String email = request.getParameter("eml");
		String iss = request.getParameter("js");
		int isswitch=Integer.parseInt(iss);
		String isf = request.getParameter("zd");
		int isflag=Integer.parseInt(isf);
		Employee employee=new Employee();
		employee.setIp(ip);;
		employee.setUsername(username);
		employee.setPassword(pwd);
		employee.setUserid(userid);
		employee.setRole(role);
		employee.setStatus(status);
		employee.setPhone(phone);
		employee.setEmail(email);
		employee.setIsswitch(isswitch);
		employee.setIsflag(isflag);
	    String info = employeeservice.AddEmployee(employee);
	    return info;
	}
	
	
	@RequestMapping("updateUser.do")
	public String edit(HttpServletRequest request, Map<Object,String> model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		String ID=request.getParameter("id");
		int id=Integer.parseInt(ID);
		String ip = request.getParameter("ip");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String userid=request.getParameter("userid");
		String ro=request.getParameter("role");
		int role=Integer.parseInt(ro);
		String st=request.getParameter("status");
		int status =Integer.parseInt(st);
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String iss=request.getParameter("isswitch");
		int isswitch=Integer.parseInt(iss);
		String isf=request.getParameter("isflag");
		int isflag=Integer.parseInt(isf);
		//创建emloyee
		Employee employee = new Employee();
		employee.setId(id);
		employee.setIp(ip);;
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setUserid(userid);
		employee.setRole(role);
		employee.setStatus(status);
		employee.setPhone(phone);
		employee.setEmail(email);
		employee.setIsswitch(isswitch);
		employee.setIsflag(isflag);
		employeeservice.updateEmployee(employee);
	    return "redirect:getUser.do"; 
	
	}
	//删除用户
		@RequestMapping("deleteuser.do")
		public String delete(HttpServletRequest request, Map<Object,String> model) {
	        String ID=request.getParameter("id");
	        int id=Integer.parseInt(ID);
			employeeservice.deleteEmployee(id);
			return "redirect:getUser.do"; 
		}
	//修改密码modify.do
		@RequestMapping("modify.do")
		public String modify(HttpServletRequest request, Map<Object,String> model) {
	        String email=request.getParameter("email");
	        String pass=request.getParameter("password");	        
			int i=employeeservice.modifyPass(email, pass);
			if(i==1){
				return "WEB-INF/user/modifysuccess";
				
			}else{
				return "redirect:modifypass.jsp"; 
			}
			
		}
	//点击发送验证码
		@RequestMapping("sendCode.do")
		@ResponseBody 
		public String sendCode(HttpServletRequest request, Map<Object,String> model) {
	       String flag="";
			String email=request.getParameter("email");
	       int code_new=(int) (Math.random()*1000000);
	       String code=String.valueOf(code_new);
	       request.getSession().setAttribute("code", code);
	       //将验证码放入session 中
	     
	       //启动定时任务10分钟后code失效
	       //showTimer(request);
	       
	       try{
		   SendEmail.sendCode(email, code);
		   flag="1";	
	       }catch(Exception e){
	    	   e.printStackTrace();
	       flag="0";
	       }
	       return flag+code;
		}
		//检测邮箱是否被注册
		@RequestMapping("checkEmail.do")
		@ResponseBody 
		public String checkEmail(HttpServletRequest request, Map<Object,String> model) {
	        String flag="";
			String email=request.getParameter("email");
			flag=employeeservice.getUserEmail(email);
		    return flag;
		}		
		//用户确认
		@RequestMapping("userSure.do")
		@ResponseBody 
		public String userSure(HttpServletRequest request) {
			 String userid=request.getParameter("username");
		     employeeservice.modifyUserSure(userid);
		     return "1";
				}	
		
	   
}
