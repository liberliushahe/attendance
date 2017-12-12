package com.work.controll;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.work.service.EmployeeService;
import com.work.service.PhotoService;
import com.work.util.FileUpLoad;


//有关上传和下载的控制器类
//上传方法upload()
//下载方法updown()
@Controller
public class FileAction {
	private static Logger log=Logger.getLogger(FileAction.class);
	@Autowired EmployeeService employeeservice;
	@Autowired PhotoService photoservice;
	@RequestMapping("upload.do")
	@ResponseBody
	public  String upload(@RequestParam("file") MultipartFile uploadfile, HttpSession session){
	 String filename= uploadfile.getOriginalFilename();
	 String leftPath= session.getServletContext().getRealPath("file");
	  String message=null;
	      File file=new File(leftPath,filename); 	     	     
	      if (!file.exists()) {
	    	  file.mkdirs();
	        }
	      try {
			uploadfile.transferTo(file);
			String path=file.getAbsolutePath();
			FileUpLoad.readExcel(path);
			if(file.isFile()){
				file.delete();
			}
			message="1";
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("文件导入出错");
			message="0";
		}
	      return message;
	}
	/*
	 * 文件下载方法
	 * @param filename month
	 * @output file.xls
	 * @version 1.1.1
	 * @author JIJIUXUE
	 */
   @RequestMapping("download.do")
	public void updown(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
      String fileName=request.getParameter("fileName");
      String month=request.getParameter("month");
      //需要修改
      //String submonth=month.substring(5, 6);
      String headfilename="甘肃考勤"+month+"月份.xls";
      try {
		fileName= new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	   response.setCharacterEncoding("utf-8");
	   response.setContentType("multipart/form-data");
	   response.setHeader("Content-Disposition", "attachment;filename="+new String(headfilename.getBytes("utf-8"),"iso-8859-1"));
	   String leftPath= session.getServletContext().getRealPath("file");
	   File file=new File(leftPath,fileName); 
	   FileUpLoad.writeExcel(leftPath, month);

	  try {
		InputStream input=new FileInputStream(file);
		OutputStream os=response.getOutputStream();
		byte[] b=new byte[2048];
		int length;
		while((length=input.read(b))>0){
			os.write(b, 0, length);
		}
		os.close();//关闭流
		input.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	//用户头像上传
   @RequestMapping("uploadImage.do")
   @ResponseBody
   public String uploadImage(@RequestParam("imagefile") MultipartFile uploadfile,HttpSession session,HttpServletRequest request ){
	  //获取用户id
	String userid=(String) request.getSession().getAttribute("username");
	  String info="";            //用来返回上传结果 
	  boolean isimage=false;     //判断是否是图片
	  boolean filesizeisOK=false; //判断文件大小是否符合要求
	 String imagename=uploadfile.getOriginalFilename();
	 String path=  session.getServletContext().getRealPath("userimage");
     String type=uploadfile.getContentType();
     long size=uploadfile.getSize();
     //判断文件类型是否属于图片类型.gif",".png",".jpeg",".jpg",".bmp"
     String[] allowtype={"gif","png","jpeg","jpg","bmp"};
     for(int i=0;i<allowtype.length;i++){
    	 if(type.contains(allowtype[i])){
    		 isimage=true;
    	 }
     }
     //判断文件大小不能大于500k
     if((size/1024)<500){
    	 filesizeisOK=true;
     }
     if(isimage==true && filesizeisOK==true){
    	 
    	 //修改头像的名称为用户名
    	 String oldimagename=imagename.substring(0,imagename.lastIndexOf("."));
    	 //替换文件名为用户登录名
    	 imagename= imagename.replace(oldimagename, userid);
    	 //保存用户名到数据库
    	 employeeservice.imagePath(userid, imagename);
    	 request.getSession().setAttribute("imagepath", imagename);
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
    			log.error("照片上传失败");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    	  info="1";
     }else if(isimage==false){
    	 info="2";//文件类型不符合
     }else if(filesizeisOK==false){
    	 info="3";//长度超过500K
    	 
     }else{
    	 info="4";//文件上传失败
     }

	   return info;
   }
  
}
