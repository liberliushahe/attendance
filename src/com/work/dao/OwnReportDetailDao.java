package com.work.dao;

import java.util.List;

import com.work.entity.OwnReport;
import com.work.entity.OwnReportDetail;

/**
 * @author: jijiuxue
 * @date:2017-12-4 ионГ9:43:14
 * @version :1.0.0
 * 
 */
public interface OwnReportDetailDao {
	void addOwnReportDetail(OwnReportDetail ownReportDetail);
    List<OwnReportDetail>  getAllOwnReportDetailByUsername(String username);
    void deleteOwnReportDetail();
}
