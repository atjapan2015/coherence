package com.coherence.demo.sample.controller;

import java.math.BigDecimal;
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
	
	@RequestMapping(value = "/employee/nodb/{id}/{times}", method = RequestMethod.GET)
	public List<Employee> employeenodb(@PathVariable("id") BigDecimal id, @PathVariable("times") Integer times) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Employee> resultList = new ArrayList<>();

		try {

			logger.info("######Method-employee 尝试从数据库中取得对象######");
			Employee result = new Employee();
			result.setId(id);
			result.setFirstname("Hugh");
			result.setLastname("Jast");
			result.setEmail("Hugh.Jast@example.com");
			result.setPhone("730-715-4446");
			result.setBirthdate("1970-11-28T08:28:48.078Z");
			result.setTitle("National Data Strategist");
			result.setDepartment("Mobility");
			if (times == 0) {
				resultList.add(result);
			} else {
				for (int i = 0; i < times; i++) {
					for (int j = 0; j < 5; j++) { // 200bytes
						resultList.add(result);
					}
				}
			}
			return resultList;
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/coherence/{id}/{times}", method = RequestMethod.GET)
	public List<Employee> employeeWithCoherence(@PathVariable("id") BigDecimal id,
			@PathVariable("times") Integer times) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Employee> resultList = new ArrayList<>();

		try {

			logger.info("######Method-employee 尝试从缓存中取得对象######");
			NamedCache<String, EmployeeExt> employeeCache = CacheFactory.getCache("employee");

			EmployeeExt employeeExt = (EmployeeExt) employeeCache.get(String.valueOf(id));
			if (employeeExt != null && employeeExt.getId() != null) {

				logger.info("######Method-employee 从缓存中取得对象成功######");
				Employee result = new Employee();
				BeanUtils.copyProperties(employeeExt, result);
				if (times == 0) {
					resultList.add(result);
				} else {
					for (int i = 0; i < times; i++) {
						for (int j = 0; j < 5; j++) { // 200bytes
							resultList.add(result);
						}
					}
				}
				return resultList;
			} else {

				logger.info("######Method-employee 从缓存中取得对象失败######");
				logger.info("######Method-employee 尝试从数据库中取得对象######");
				Employee result = sampleFacade.selectByPrimaryKey(id);
				EmployeeExt employeeExt2 = new EmployeeExt();
				BeanUtils.copyProperties(result, employeeExt2);
				employeeCache.put(String.valueOf(result.getId()), employeeExt2);
				if (times == 0) {
					resultList.add(result);
				} else {
					for (int i = 0; i < times; i++) {
						for (int j = 0; j < 5; j++) { // 200bytes
							resultList.add(result);
						}
					}
				}
				return resultList;
			}
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/{id}/{times}", method = RequestMethod.GET)
	public List<Employee> employee(@PathVariable("id") BigDecimal id, @PathVariable("times") Integer times) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Employee> resultList = new ArrayList<>();

		try {

			logger.info("######Method-employee 尝试从数据库中取得对象######");
			Employee result = sampleFacade.selectByPrimaryKey(id);
			if (times == 0) {
				resultList.add(result);
			} else {
				for (int i = 0; i < times; i++) {
					for (int j = 0; j < 5; j++) { // 200bytes
						resultList.add(result);
					}
				}
			}
			return resultList;
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/coherence/union/{id}/{times}", method = RequestMethod.GET)
	public List<Employee> employeeWithCoherenceUnion(@PathVariable("id") BigDecimal id,
			@PathVariable("times") Integer times) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Employee> resultList = new ArrayList<>();

		List<BigDecimal> searchList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			searchList.add(id);
		}

		try {

			logger.info("######list size:" + searchList.size() + "######");
			logger.info("######Method-employee 尝试从缓存中取得对象######");
			NamedCache<String, EmployeeExt> employeeCache = CacheFactory.getCache("employee");

			EmployeeExt employeeExt = (EmployeeExt) employeeCache.get(String.valueOf(id));
			if (employeeExt != null && employeeExt.getId() != null) {

				logger.info("######Method-employee 从缓存中取得对象成功######");
				Employee result = new Employee();
				BeanUtils.copyProperties(employeeExt, result);
				if (times == 0) {
					resultList.add(result);
				} else {
					for (int i = 0; i < times; i++) {
						for (int j = 0; j < 5; j++) { // 200bytes
							resultList.add(result);
						}
					}
				}
				return resultList;
			} else {

				logger.info("######Method-employee 从缓存中取得对象失败######");
				logger.info("######Method-employee 尝试从数据库中取得对象######");
				Map<String, List<BigDecimal>> searchMap = new HashMap<>();
				searchMap.put("searchList", searchList);
				Employee result = sampleFacade.selectByPrimaryKeyUseUnion(searchMap);
				EmployeeExt employeeExt2 = new EmployeeExt();
				BeanUtils.copyProperties(result, employeeExt2);
				employeeCache.put(String.valueOf(result.getId()), employeeExt2);
				if (times == 0) {
					resultList.add(result);
				} else {
					for (int i = 0; i < times; i++) {
						for (int j = 0; j < 5; j++) { // 200bytes
							resultList.add(result);
						}
					}
				}
				return resultList;
			}
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}

	@RequestMapping(value = "/employee/union/{id}/{times}", method = RequestMethod.GET)
	public List<Employee> employeeUnion(@PathVariable("id") BigDecimal id, @PathVariable("times") Integer times) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-employee start######");

		List<Employee> resultList = new ArrayList<>();

		List<BigDecimal> searchList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			searchList.add(id);
		}

		try {

			logger.info("######list size:" + searchList.size() + "######");
			logger.info("######Method-employee 尝试从数据库中取得对象######");
			Map<String, List<BigDecimal>> searchMap = new HashMap<>();
			searchMap.put("searchList", searchList);
			Employee result = sampleFacade.selectByPrimaryKeyUseUnion(searchMap);
			if (times == 0) {
				resultList.add(result);
			} else {
				for (int i = 0; i < times; i++) {
					for (int j = 0; j < 5; j++) { // 200bytes
						resultList.add(result);
					}
				}
			}
			return resultList;
		} finally {

			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-employee end######");
		}

	}
}
