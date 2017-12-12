package com.work.service;

import java.util.List;

import com.work.entity.OwnReportDetail;

/**
 * @author: jijiuxue
 * @date:2017-12-8 ионГ11:44:43
 * @version :1.0.0
 * 
 */

public interface OwnReportDetailService {
	void addOwnReportDetail(OwnReportDetail ownReportDetail);
    List<OwnReportDetail>  getAllOwnReportDetailByUsername(String username);
    void deleteOwnReportDetail();

}
