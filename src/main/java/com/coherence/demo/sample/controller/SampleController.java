package com.coherence.demo.sample.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coherence.demo.common.entity.Employee;
import com.coherence.demo.common.entity.EmployeeExt;
import com.coherence.demo.sample.facade.SampleFacade;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

@RestController
@RequestMapping("/sample")
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	SampleFacade sampleFacade;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello World!";
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employee> employees() {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employees start######");

		try {

			List<Employee> resultList = sampleFacade.selectByExample();
			return resultList;
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employees end######");
		}

	}

	@RequestMapping(value = "/employee/coherence/{id}", method = RequestMethod.GET)
	public Employee employeeWithCoherence(@PathVariable("id") Short id) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		try {

			logger.info("######Method-employee 尝试从缓存中取得对象######");
			NamedCache<String, EmployeeExt> employeeCache = CacheFactory.getCache("employee");

			EmployeeExt employeeExt = (EmployeeExt) employeeCache.get(String.valueOf(id));
			if (employeeExt != null && employeeExt.getId() != null) {

				logger.info("######Method-employee 从缓存中取得对象成功######");
				Employee result = new Employee();
				BeanUtils.copyProperties(employeeExt, result);
				return result;
			} else {

				logger.info("######Method-employee 从缓存中取得对象失败######");
				logger.info("######Method-employee 尝试从数据库中取得对象######");
				Employee result = sampleFacade.selectByPrimaryKey(id);
				EmployeeExt employeeExt2 = new EmployeeExt();
				BeanUtils.copyProperties(result, employeeExt2);
				employeeCache.put(String.valueOf(result.getId()), employeeExt2);
				return result;
			}
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee employee(@PathVariable("id") Short id) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		try {

			logger.info("######Method-employee 尝试从数据库中取得对象######");
			Employee result = sampleFacade.selectByPrimaryKey(id);
			EmployeeExt employeeExt2 = new EmployeeExt();
			BeanUtils.copyProperties(result, employeeExt2);
			return result;
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/coherence/union/{id}", method = RequestMethod.GET)
	public Employee employeeWithCoherenceUnion(@PathVariable("id") Short id) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Short> searchList = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			searchList.add(id);
		}

		try {

			logger.info("######Method-employee 尝试从缓存中取得对象######");
			NamedCache<String, EmployeeExt> employeeCache = CacheFactory.getCache("employee");

			EmployeeExt employeeExt = (EmployeeExt) employeeCache.get(String.valueOf(id));
			if (employeeExt != null && employeeExt.getId() != null) {

				logger.info("######Method-employee 从缓存中取得对象成功######");
				Employee result = new Employee();
				BeanUtils.copyProperties(employeeExt, result);
				return result;
			} else {

				logger.info("######Method-employee 从缓存中取得对象失败######");
				logger.info("######Method-employee 尝试从数据库中取得对象######");
				Map<String, List<Short>> searchMap = new HashMap<>();
				searchMap.put("searchList", searchList);
				Employee result = sampleFacade.selectByPrimaryKeyUnion(searchMap);
				EmployeeExt employeeExt2 = new EmployeeExt();
				BeanUtils.copyProperties(result, employeeExt2);
				employeeCache.put(String.valueOf(result.getId()), employeeExt2);
				return result;
			}
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/union/{id}", method = RequestMethod.GET)
	public Employee employeeUnion(@PathVariable("id") Short id) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Short> searchList = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			searchList.add(id);
		}

		try {

			logger.info("######Method-employee 尝试从数据库中取得对象######");
			Map<String, List<Short>> searchMap = new HashMap<>();
			searchMap.put("searchList", searchList);
			Employee result = sampleFacade.selectByPrimaryKeyUnion(searchMap);
			EmployeeExt employeeExt2 = new EmployeeExt();
			BeanUtils.copyProperties(result, employeeExt2);
			return result;
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}
}
