package com.work.dao;

import java.util.List;

import com.work.entity.WebSite;

/**
 * @author: jijiuxue
 * @date:2017-12-4 ионГ9:43:14
 * @version :1.0.0
 * 
 */
public interface WebSiteDao {
	void addWebSite(WebSite webSite);
    List<WebSite>  getAllWebSite();
}
