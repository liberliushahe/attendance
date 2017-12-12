package com.work.dao;

import java.util.List;

import com.work.entity.LinkMan;
import com.work.util.Page;

public interface LinkManDao {
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
  //通过编号获取联系人信息
  public LinkMan queryLinkManById(int id);
}
  

