package com.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.OperationDao;
import com.work.entity.OperationLog;

//操作日志
@Service
public class OperationService {
	@Autowired
	private OperationDao operationdao;
	//分页查询日志
	//条件查询日志
	//单条删除日志
	//清空日志
    //增加日志
	public void addLog(OperationLog log){
		operationdao.addLog(log);
	}
}
