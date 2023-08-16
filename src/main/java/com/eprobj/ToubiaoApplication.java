package com.eprobj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@MapperScan ("com.eprobj.mapper")
@SpringBootApplication
@ComponentScan (basePackages = "com.eprobj.*")
public class ToubiaoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ToubiaoApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ToubiaoApplication.class);
	}


}
