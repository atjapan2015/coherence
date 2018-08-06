package com.coherence.demo.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "com.coherence.demo.common.mapper")
public class AppConfig {

	@Autowired
	DataSourceProperties dataSourceProperties;

	@Bean(destroyMethod = "close")
	@Primary
	DataSource realDataSource() throws Exception {

		HikariDataSource ds = new HikariDataSource();
		// use jndi
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = dataSourceLookup.getDataSource(this.dataSourceProperties.getJndiName());
		ds.setDataSource(dataSource);
		// use jdbc
		// ds.setJdbcUrl(this.dataSourceProperties.getUrl());
		// ds.setUsername(this.dataSourceProperties.getUsername());
		// ds.setPassword(this.dataSourceProperties.getPassword());
		// ds.setDriverClassName(this.dataSourceProperties.getDriverClassName());
		// ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
		return ds;
	}

	@Bean
	public SqlSessionFactory getSqlSessionFactory() throws Exception {

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		// factoryBean.setDataSource(new Log4jdbcProxyDataSource(realDataSource()));
		factoryBean.setDataSource(realDataSource());
		factoryBean.setTypeAliasesPackage("com.coherence.demo.common.entity");
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:com/coherence/demo/common/mapper/*.xml"));

		return factoryBean.getObject();
	}

}
