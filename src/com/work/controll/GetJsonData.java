package com.work.controll;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.entity.LinkMan;
import com.work.service.LinkManService;
import com.work.service.TimeTableService;

/*
 * @author JIJIUXUE
 * @param input
 * @return output JSON
 * @version 1.0.0 2017-7-17
 * @param create an interface of get data of user count.
 * 外界提供一个HTTP接口，可以获取系统数据
 * 给安卓客户端提供一个接口开获取数据
 * 通过json格式数据返回
 */
@Controller
public class GetJsonData {
	@Autowired private TimeTableService timetableservice;
	@Autowired private LinkManService linkmanservice;
	private static Logger log=Logger.getLogger(GetJsonData.class);
	@RequestMapping("getJson.do")
	public void getUserData(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		String username=request.getParameter("username");
		int status=0;
		byte[] bytes;
		String name=null;
		try {
			//解决get请求乱码问题
			bytes = username.getBytes("ISO-8859-1");
			 name=new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int totalCount=timetableservice.findCurrentAllCountByUsername(name);
		String messagedetail="当前考勤记录为:";
		String message=name+messagedetail+totalCount+"条";
		log.info(message);
		status=response.getStatus();
		if(status==200){
			map.put("code", 1);
			map.put("status", "success");
			map.put("message", message);
		}else{
			map.put("code", 0);
			map.put("status", "error");
			map.put("message", "接口异常");
		}
          //将map转JSON
		 ObjectMapper objMap = new ObjectMapper();
		 String mess=null;
		 try {
		 mess=	objMap.writeValueAsString(map);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// objMap.
		 try {
			response.getOutputStream().write(mess.getBytes("utf-8"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("文件流出错");
		}
	}
	//所有客户端接口开头为getClient*****.do
    //通过联系人编号来获取用户信息
	@RequestMapping("getClientLinkManInfo.do")
	public void getLinkManById(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("id"));
		LinkMan linkman=linkmanservice.queryLinkManById(id);	     	
		try {
			response.getWriter().print(JSON.toJSON(linkman));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
