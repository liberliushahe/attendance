package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.LinkManDao;
import com.work.entity.LinkMan;
import com.work.util.Page;
@Service
public class LinkManServiceImpl implements LinkManService {
    @Autowired LinkManDao linkmandao;
    
	public void setLinkmandao(LinkManDao linkmandao) {
		this.linkmandao = linkmandao;
	}

	public void addLinkMan(LinkMan linkman) {
		// TODO Auto-generated method stub
		linkmandao.addLinkMan(linkman);
	}

	public void updateLinkMan(LinkMan linkman) {
		// TODO Auto-generated method stub
		linkmandao.updateLinkMan(linkman);
	}

	public void deleteLinkMan(int id) {
		// TODO Auto-generated method stub
		linkmandao.deleteLinkMan(id);
	}

	public List<LinkMan> queryLinkManByPage(Page page) {
		// TODO Auto-generated method stub
		return linkmandao.queryLinkManByPage(page);
	}

	public List<LinkMan> queryLinkManByName(String name,String depart,String key) {
		// TODO Auto-generated method stub
		return linkmandao.queryLinkManByName(name, depart, key);
	}

	public int queryLinkManCount() {
		// TODO Auto-generated method stub
		return linkmandao.queryLinkManCount();
	}

	public List<String> queryLinkManByLikeName(String name) {
		// TODO Auto-generated method stub
		return linkmandao.queryLinkManByLikeName(name);
	}
	//批量删除联系人记录
	public void batchDeleteLinkMan(String[] id) {
	linkmandao.batchDeleteLinkMan(id);
	}

	public LinkMan queryLinkManById(int id) {
		// TODO Auto-generated method stub
		return linkmandao.queryLinkManById(id);
	}
}
