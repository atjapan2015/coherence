package com.coherence.demo.sample.facade.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coherence.demo.common.entity.Employee;
import com.coherence.demo.common.entity.EmployeeExample;
import com.coherence.demo.common.service.EmployeeService;
import com.coherence.demo.sample.facade.SampleFacade;

@Service
public class SampleFacadeImpl implements SampleFacade {

	private static final Logger logger = LoggerFactory.getLogger(SampleFacadeImpl.class);

	@Autowired
	EmployeeService employeeService;

	@Override
	public List<Employee> selectByExample() {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-selectByExample start######");

		try {

			EmployeeExample example = new EmployeeExample();
			return employeeService.selectByExample(example);
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-selectByExample end######");
		}
	}

	@Override
	public List<Employee> selectByExample(EmployeeExample example) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-selectByExample start######");

		try {

			return employeeService.selectByExample(example);
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-selectByExample end######");
		}
	}

	@Override
	public Employee selectByPrimaryKey(Short id) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-selectByExample start######");

		try {

			return employeeService.selectByPrimaryKey(id);
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-selectByExample end######");
		}

	}
	
	@Override
	public Employee selectByPrimaryKeyUnion(Map<String, List<Short>> searchMap) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-selectByExample start######");

		try {

			return employeeService.selectByPrimaryKeyUnion(searchMap);
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-selectByExample end######");
		}

	}

}
