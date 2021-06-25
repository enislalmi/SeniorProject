package com.spring.project.root.configs;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Enis
 */
@Configuration
@EnableJpaRepositories("com.spring.project.root")
@ComponentScan(basePackages = { "com.spring.project.root" })
@EnableTransactionManagement
@EnableCaching
public class DataAccessConfiguration implements SpringConfiguration {

	private static DataSource getDataSource() {
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDefaultCatalog("cat");
		basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDataSource.setInitialSize(2);
		basicDataSource.setMaxIdle(10);
		basicDataSource.setMaxTotal(50);
		basicDataSource.setMinIdle(2);
		basicDataSource.setPassword("enis1234enea");
		basicDataSource.setPoolPreparedStatements(Boolean.TRUE);
		basicDataSource.setTestOnBorrow(Boolean.TRUE);
		basicDataSource.setTestOnReturn(Boolean.TRUE);
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/cat"
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true");
		basicDataSource.setUsername("root");
		basicDataSource.setValidationQuery("SELECT 1");
		return basicDataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFacorty() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(DataAccessConfiguration.getDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(this.getJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.spring.project.root.");
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(Boolean.FALSE);
		jpaVendorAdapter.setShowSql(Boolean.FALSE);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return jpaVendorAdapter;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager getTransactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager jpaTransaction = new JpaTransactionManager();
		jpaTransaction.setEntityManagerFactory(entityManagerFactory);
		return jpaTransaction;
	}
}
