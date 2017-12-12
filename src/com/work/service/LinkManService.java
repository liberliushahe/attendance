package com.work.service;

import java.util.List;

import com.work.entity.LinkMan;
import com.work.util.Page;

public interface LinkManService {
	  //查询联系人总人数
	  public int queryLinkManCount();	
	 //增加
	  public void addLinkMan(LinkMan linkman);
	  //修改
	  public void updateLinkMan(LinkMan linkman);
	  //删除
	  public void deleteLinkMan(int id);
	  //分页查询
	  public List<LinkMan> queryLinkManByPage(Page page);
	  //姓名模糊查询
	  public List<LinkMan> queryLinkManByName(String name,String depart,String key);
	   //获取所有模糊姓名
	public List<String> queryLinkManByLikeName(String name);
	//批量删除联系人记录
	public void batchDeleteLinkMan(String[] id);
	public LinkMan queryLinkManById(int id);
}
