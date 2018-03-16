package com.coherence.demo.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coherence.demo.common.entity.Employee;
import com.coherence.demo.common.entity.EmployeeExample;
import com.coherence.demo.common.mapper.EmployeeMapper;
import com.coherence.demo.common.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public long countByExample(EmployeeExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByExample(EmployeeExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Employee record) {

		return employeeMapper.insert(record);
	}

	@Override
	public int insertSelective(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> selectByExample(EmployeeExample example) {

		return employeeMapper.selectByExample(example);
	}

	@Override
	public Employee selectByPrimaryKey(Long id) {

		return employeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Employee record, EmployeeExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(Employee record, EmployeeExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
