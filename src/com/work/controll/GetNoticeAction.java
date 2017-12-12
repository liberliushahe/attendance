package com.work.controll;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.work.entity.Notice;
import com.work.service.NoticeService;
import com.work.util.Page;
import com.work.util.PageUtil;
@Controller
public class GetNoticeAction {
	@Autowired private NoticeService noticeservice;
	/*设置日志对象，方便使用
	 * 
	 * 通过log对象配置不同的日志级别例如：log.info(),log.debug()等
	 * 如有日志输出需要可以放开
	 */
	private static Logger log=Logger.getLogger(GetNoticeAction.class);
	@RequestMapping("getNotice.do")
    public String getNoticeList(HttpServletRequest request){
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
		
		int totalCount=noticeservice.noticeAllCount();
		Page page=PageUtil.createPage(10, currentpage, totalCount);
		List<Notice> list=noticeservice.queryNotice(page);
		request.setAttribute("List", list);
		request.setAttribute("page", page);
	   return "WEB-INF/admin/notice";
    }
	@RequestMapping("addNoticeList.do")
	public String addNoticeList(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title=request.getParameter("add-title");
		String content=request.getParameter("add-content");
		String author=request.getParameter("add-author");
		String date=request.getParameter("add-date");
	    Notice notice=new Notice();
	    notice.setTitle(title);
	    notice.setContent(content);
	    notice.setAuthor(author);
	    notice.setPublishdate(date);
	    noticeservice.addNotice(notice);
	    return "redirect:getNotice.do";
	}
	@RequestMapping("updateNoticeList.do")
	public String updateNoticeList(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ID=request.getParameter("update-id");
		int id=Integer.parseInt(ID);
		String title=request.getParameter("update-title");
		String content=request.getParameter("update-content");
		String author=request.getParameter("update-author");
		String date=request.getParameter("update-date");
	    Notice notice=new Notice();
	    notice.setId(id);
	    notice.setTitle(title);
	    notice.setContent(content);
	    notice.setAuthor(author);
	    notice.setPublishdate(date);
	    noticeservice.updateNotice(notice);
	    return "redirect:getNotice.do";
	}
	@RequestMapping("deleteNotice.do")
	public String deleteNoticeList(HttpServletRequest request){
		String ID=request.getParameter("id");
	    int id=Integer.parseInt(ID);
		noticeservice.deleteNotice(id);
		log.info("删除编号为"+id+"的工号");
		return "redirect:getNotice.do";
	}
}
