package com.work.dao;

import java.util.List;

import com.work.entity.Employee;
import com.work.entity.OwnReport;

/**
 * @author: jijiuxue
 * @date:2017-12-4 上午9:43:14
 * @version :1.0.0
 * 
 */
public interface OwnReportDao {
	void addOwnReport(OwnReport ownReport);
    List<OwnReport>  getAllOwnReportByAutoReport();
    List<OwnReport>  getAllOwnReportByAutoAsyn();
    //修改用户的标识
    void modifyWflag(String username);
	public OwnReport getAllOwnReportByUser(String username);
	public void updateOwnReportByUser(OwnReport report);
	public void deleteOwnReportByUser(int id);

	 //查询上班人员
		public List<Employee> getAllUserWork();
		public List<Employee> getAllUserUnWork();
}
