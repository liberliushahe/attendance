package com.work.controll;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONArray;
import com.work.entity.Department;
import com.work.entity.LinkMan;
import com.work.service.DepartService;
import com.work.service.LinkManService;
import com.work.util.Page;
import com.work.util.PageUtil;


//有关上传和下载的控制器类
//上传方法upload()
//下载方法updown()
@Controller
public class QuickQueryAction {
	private static Logger log=Logger.getLogger(QuickQueryAction.class);
    @Autowired LinkManService linkmanservice;
    @Autowired DepartService departservice;
	@RequestMapping("quickQuery.do")
	public String quickquery(HttpServletRequest request){
	int count=linkmanservice.queryLinkManCount();
	request.setAttribute("count", count);
	return "WEB-INF/user/quickquery";
	}
	//删除联系人
	
	@RequestMapping("deleteLinkMan.do")
	public String deleteLinkMan(HttpServletRequest request){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}				
		String ID=request.getParameter("id");
		int id=Integer.parseInt(ID);
		linkmanservice.deleteLinkMan(id);
		return "redirect:quickAdminQuery.do?currentPage="+currentpage;
	}
	//增加联系人
	@RequestMapping("addLinkMan.do")
	public String addLinkMan(Model model, LinkMan linkman){
		linkmanservice.addLinkMan(linkman);
		return "redirect:quickAdminQuery.do";
	}
	//更新联系人
	@RequestMapping("updateLinkMan.do")
	public String updateLinkMan(Model model,LinkMan linkman,HttpServletRequest request){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}				
		linkmanservice.updateLinkMan(linkman);
		return "redirect:quickAdminQuery.do?currentPage="+currentpage;
	}
	
	
	
	
	@RequestMapping("quickAdminQuery.do")
	public String quickAdminquery(HttpServletRequest request){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
		//查询当前注册用户数
		int totalCount=linkmanservice.queryLinkManCount();
		Page page=PageUtil.createPage(10, currentpage, totalCount);
		List<LinkMan> list=linkmanservice.queryLinkManByPage(page);
		request.setAttribute("List", list);
		request.setAttribute("page", page);
	    return "WEB-INF/admin/quickquery";
	}
	   //根据条件查询联系人
	@RequestMapping("queryLinkMan.do")
	public void queryLinkMan(@RequestParam("name") String name,@RequestParam("department-key") String depart,@RequestParam("key") String keys,HttpServletResponse response,HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name_new=null;
		String key_new=null;
		try {
			name_new = new String(name.getBytes("iso8859-1"),"UTF-8");
			key_new = new String(keys.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			log.error("编码不支持");
			e1.printStackTrace();
		}
		List<LinkMan> list=linkmanservice.queryLinkManByName(name_new, depart, key_new);
		List<Object> list_array=new ArrayList<Object>();
		for(LinkMan item:list){
			list_array.add(item);
		}
		JSONArray array=new JSONArray(list_array);
		try {
			response.getWriter().print(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   //获取所有联系人姓名模糊查询
	@RequestMapping("getNameLike.do")
	public void getNameLike(@RequestParam("name") String name,HttpServletResponse response){
		String name_new=null;
		try {
			 name_new = new String(name.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list=linkmanservice.queryLinkManByLikeName(name_new);
		List<Object> list_array=new ArrayList<Object>();
		for(String item:list){
			list_array.add(item);
		}
		JSONArray array=new JSONArray(list_array);
		try {
			response.getWriter().print(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   //获取部门和部门id
	@RequestMapping("getDepart.do")
	public void getDepart(HttpServletResponse response){
		List<Department> list=departservice.queryDepart();
		List<Object> list_array=new ArrayList<Object>();
		for(Department item:list){
			list_array.add(item);
		}
		JSONArray array=new JSONArray(list_array);
		try {
			response.getWriter().print(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//批量删除考勤记录
		@RequestMapping("batchlinkmandelete.do")
		public String batchDelete(HttpServletRequest request, Map<Object,String> model) {
			String currentPage=request.getParameter("currentPage");
			int currentpage=0;
			if(currentPage==null ||"".equals(currentPage)){
				currentpage=1;
			}else{
				currentpage=Integer.parseInt(currentPage);
			}
	        String allid=request.getParameter("allid");
	        String[] id=allid.split(",");
	        linkmanservice.batchDeleteLinkMan(id);
			return "redirect:quickAdminQuery.do?currentPage="+currentpage; 			
		}	
 
}
