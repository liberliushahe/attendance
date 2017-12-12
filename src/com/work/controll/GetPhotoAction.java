package com.work.controll;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.work.entity.Employee;
import com.work.entity.Photo;
import com.work.service.PhotoService;
import com.work.util.Page;
import com.work.util.PageUtil;

@Controller
public class GetPhotoAction {
	//格式化日期
		public String formatdate(){
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String currentdate=format.format(date);
			return currentdate;
		}
	@Autowired PhotoService photoservice;
	@RequestMapping("getPhoto.do")
	public String photoManage(HttpServletRequest request){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
		
		int totalCount=photoservice.photoAllCount();
		Page page=PageUtil.createPage(5, currentpage, totalCount);
		List<Photo> list=photoservice.selectPhoto(page);
		request.setAttribute("List", list);
		request.setAttribute("page", page);
				
	   return "WEB-INF/admin/photo";
	}
	//查询相册照片地址
	@RequestMapping("getUserPhoto.do")
	public String getPhoto(HttpServletRequest request){		
		int begin=0;
		List<Photo> list=photoservice.selectPhoto(begin);
		request.setAttribute("List", list);
		return "WEB-INF/user/photo";
	}
	//点击获取更多
	@RequestMapping("getMore.do")
	public void getMore(HttpServletRequest request,HttpServletResponse response){
		String current=request.getParameter("current");
		int begin=Integer.parseInt(current);
		List<Photo> list=photoservice.selectPhoto(begin);
		List<Object> list_more=new ArrayList<Object>();
		for(Photo photo:list){
			list_more.add(photo);
		}
		JSONArray array=new JSONArray(list_more);
		try {
			response.getWriter().print(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping("deletePhoto.do")
	public String deletePhoto(HttpServletRequest request){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
        String ID=request.getParameter("id");
        int id=Integer.parseInt(ID);
		photoservice.deletePhoto(id);
		return "redirect:getPhoto.do?currentPage="+currentpage;
	}
	//数据库增加图片
	@RequestMapping("updatePhoto.do")
	 public String addPhoto(@RequestParam("addphoto-id") int id,@RequestParam("update-title") String title,@RequestParam("update-content") String content,@RequestParam("update-author") String author,@RequestParam("update-date") String date,@RequestParam("update-wisdom") String wisdom,HttpServletRequest request){
		   Photo photo=new Photo();
		   photo.setId(id);
		   photo.setTitle(title);
		   photo.setContent(content);
		   photo.setAuthor(author);
		   photo.setPublishdate(date);
		   photo.setWisdom(wisdom);
		   photoservice.updatePhoto(photo);
		return "redirect:getPhoto.do";
	 }
	
	//上墙操作
	//数据库增加图片
		@RequestMapping("upWall.do")
		 public String addPhoto(@RequestParam("wall_username") String title,@RequestParam("wall_mingyan") String wisdom,@RequestParam("wall_textarea") String content,HttpServletRequest request){
			  
			   Photo photo=new Photo();
			   photo.setTitle(title);
			   photo.setWisdom(wisdom);
			   photo.setContent(content);
               Employee employee=(Employee)request.getSession().getAttribute("employee");
               String author=employee.getUsername();
               String path=employee.getImage();
               photo.setPath(path);
               photo.setAuthor(author);
               photo.setPublishdate(formatdate());
			   photoservice.addPhoto(photo);
			   
			return "redirect:getUserPhoto.do";
		 }
		
		
		
		
		
	   //上传图片
	   @RequestMapping("uploadPhoto.do")
	   @ResponseBody
	   public String uploadPhoto(@RequestParam("file") MultipartFile uploadfile,HttpSession session,HttpServletRequest request){
		   String imagename=uploadfile.getOriginalFilename();
		   Photo photo=new Photo();
		   photo.setTitle("待编辑");
		   photo.setContent("待编辑");
		   photo.setAuthor("待编辑");
		   photo.setPublishdate("待编辑");
		   photo.setWisdom("待编辑");
		   photo.setPath(imagename);	 
		   photoservice.addPhoto(photo);		   
		   String path=  session.getServletContext().getRealPath("userimage");
		   
		   File file=new File(path,imagename); 
	  	 try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  	try {
			uploadfile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "1";	
		   
	   }
}
