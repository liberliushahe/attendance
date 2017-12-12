package com.work.controll;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.work.entity.OwnReport;
import com.work.entity.OwnReportDetail;
import com.work.service.OwnReportDetailService;
import com.work.service.OwnReportService;


//有关上传和下载的控制器类
//上传方法upload()
//下载方法updown()
@Controller
public class UserAutoServiceAction {
	@Autowired
	private OwnReportService ownReportService;
	@Autowired
	private OwnReportDetailService ownReportDetailService;
	private static Logger log=Logger.getLogger(UserAutoServiceAction.class);
	@RequestMapping("userAutoService.do")
	public String autoService(HttpServletRequest request){
	log.info("跳转页面");
	String userid=(String) request.getSession().getAttribute("username");
	OwnReport report=ownReportService.getAllOwnReportByUser(userid);
	List<OwnReportDetail> reportlist=ownReportDetailService.getAllOwnReportDetailByUsername(userid);
	request.setAttribute("userreport", report);
	request.setAttribute("List", reportlist);
	return "WEB-INF/user/userreport";
	}
	@RequestMapping("updateUserReport.do")
	public String updateUserReport(HttpServletRequest request,OwnReport report){
		ownReportService.updateOwnReportByUser(report);
	   return "redirect:userAutoService.do";
	}
	@RequestMapping("addUserReport.do")
	public String addUserReport(HttpServletRequest request,OwnReport report){
		ownReportService.addOwnReport(report);
	return "redirect:userAutoService.do";
	}
	@RequestMapping("deleteUserReport.do")
	public String deleteUserReport(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		ownReportService.deleteOwnReportByUser(id);
	    return "redirect:userAutoService.do";
	}
}
