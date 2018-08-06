package com.coherence.demo.sample.facade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.coherence.demo.common.entity.Employee;
import com.coherence.demo.common.entity.EmployeeExample;

public interface SampleFacade {

	List<Employee> selectByExample();

	List<Employee> selectByExample(EmployeeExample example);

	Employee selectByPrimaryKey(BigDecimal id);
	
	Employee selectByPrimaryKeyUseUnion(Map<String, List<BigDecimal>> map);

}
