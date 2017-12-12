package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.OwnReportDetailDao;
import com.work.entity.OwnReportDetail;

/**
 * @author: jijiuxue
 * @date:2017-12-8 ÉÏÎç11:44:43
 * @version :1.0.0
 * 
 */
@Service
public class OwnReportDetailServiceImpl implements OwnReportDetailService{
	@Autowired
    private OwnReportDetailDao ownReportDaoDetail;
	public void addOwnReportDetail(OwnReportDetail ownReportDetail) {
		// TODO Auto-generated method stub
		ownReportDaoDetail.addOwnReportDetail(ownReportDetail);
	}

	public List<OwnReportDetail> getAllOwnReportDetailByUsername(String username) {
		// TODO Auto-generated method stub
		return ownReportDaoDetail.getAllOwnReportDetailByUsername(username);
	}

	public void deleteOwnReportDetail() {
		// TODO Auto-generated method stub
		ownReportDaoDetail.deleteOwnReportDetail();
		
	}
	
}
