package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.NoticeDao;
import com.work.entity.Notice;
import com.work.util.Page;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao noticedao;

	// 显示最新公告
	public List<Notice> queryNotice() {
		return noticedao.queryNotice();
	}

	// 查询公告总数
	public int noticeAllCount() {
		return noticedao.noticeAllCount();
	}

	// 查询公告列表
	public List<Notice> queryNotice(Page page) {
		return noticedao.queryNotice(page);
	}

	// 增加公告
	public void addNotice(Notice notice) {
		noticedao.addNotice(notice);
	}

	// 更新公告
	public void updateNotice(Notice notice) {
		noticedao.updateNotice(notice);
	}

	// 删除公告
	public void deleteNotice(int id) {
		noticedao.deleteNotice(id);
	}
}
