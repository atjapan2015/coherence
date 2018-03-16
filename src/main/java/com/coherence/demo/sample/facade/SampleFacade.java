package com.coherence.demo.sample.facade;

import java.util.List;

import com.coherence.demo.common.entity.Employee;
import com.coherence.demo.common.entity.EmployeeExample;

public interface SampleFacade {

	List<Employee> selectByExample();

	List<Employee> selectByExample(EmployeeExample example);

	Employee selectByPrimaryKey(Long id);

}
