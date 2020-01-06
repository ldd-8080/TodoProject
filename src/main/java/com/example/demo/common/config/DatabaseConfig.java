package com.example.demo.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages="com.example")
@EnableTransactionManagement
public class DatabaseConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:/**/xml/*.xml"));
		//sessionFactory.setTypeAliasesPackage(SpringBootVueApplication.class.getPackage().getName());
		sessionFactory.setTypeAliasesPackage("com.example");
		
		SqlSessionFactory factory = sessionFactory.getObject();
		factory.getConfiguration().setMapUnderscoreToCamelCase(true); //_가 포함된 column을 CamelCase로 변환하는 듯
		
		return factory;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}
}
