package com.work.dao;

import java.util.List;

import com.work.entity.Department;

public interface DepartDao {
	public void addDepart(Department depart);
	public void updateDepart(Department depart);
	public void deleteDepart(int id);
	public List<Department> queryDepart();
}
