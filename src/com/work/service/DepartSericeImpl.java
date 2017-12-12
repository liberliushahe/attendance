package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.DepartDao;
import com.work.entity.Department;
@Service
public class DepartSericeImpl implements DepartService {
    @Autowired DepartDao departdao;
	public void addDepart(Department depart) {
		// TODO Auto-generated method stub
		departdao.addDepart(depart);
	}

	public void updateDepart(Department depart) {
		// TODO Auto-generated method stub
		departdao.updateDepart(depart);
	}

	public void deleteDepart(int id) {
		// TODO Auto-generated method stub
		departdao.deleteDepart(id);
	}

	public List<Department> queryDepart() {
		// TODO Auto-generated method stub
		return departdao.queryDepart();
	}

}
