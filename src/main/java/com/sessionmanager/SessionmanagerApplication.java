package com.sessionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.activation.DataSource;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@SpringBootApplication
public class SessionmanagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SessionmanagerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SessionmanagerApplication.class);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, Properties additionalProperties) {
		LocalContainerEntityManagerFactoryBean factoryBean =
				new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("br.com.alura.listavip");
		factoryBean.setDataSource(dataSource);

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setJpaProperties(additionalProperties);

		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
