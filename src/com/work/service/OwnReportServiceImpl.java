package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.OwnReportDao;
import com.work.entity.Employee;
import com.work.entity.OwnReport;

/**
 * @author: jijiuxue
 * @date:2017-12-4 ÉÏÎç9:43:14
 * @version :1.0.0
 * 
 */
@Service
public class OwnReportServiceImpl implements OwnReportService{
	@Autowired
	OwnReportDao ownReportDao;

	public List<OwnReport> getAllOwnReportByAutoReport() {
		// TODO Auto-generated method stub
		return ownReportDao.getAllOwnReportByAutoReport();
	}

	public List<OwnReport> getAllOwnReportByAutoAsyn() {
		// TODO Auto-generated method stub
		return ownReportDao.getAllOwnReportByAutoAsyn();
	}

	public void modifyWflag(String username) {
		// TODO Auto-generated method stub
		ownReportDao.modifyWflag(username);
	}

	public void addOwnReport(OwnReport ownReport) {
		// TODO Auto-generated method stub
		ownReportDao.addOwnReport(ownReport);
	}

	public OwnReport getAllOwnReportByUser(String username) {
		// TODO Auto-generated method stub
		return ownReportDao.getAllOwnReportByUser(username);
	}

	public void updateOwnReportByUser(OwnReport report) {
		// TODO Auto-generated method stub
		ownReportDao.updateOwnReportByUser(report);
	}

	public void deleteOwnReportByUser(int id) {
		// TODO Auto-generated method stub
		ownReportDao.deleteOwnReportByUser(id);
	}

	public List<Employee> getAllUserWork() {
		// TODO Auto-generated method stub
		return ownReportDao.getAllUserWork();
	}

	public List<Employee> getAllUserUnWork() {
		// TODO Auto-generated method stub
		return ownReportDao.getAllUserUnWork();
	}

}
