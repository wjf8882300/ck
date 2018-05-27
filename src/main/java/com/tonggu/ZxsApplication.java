package com.tonggu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.tonggu.component.MetaDataManager;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({"classpath:/applicationContext-task.xml", "classpath:/spring/application-security.xml"}) //
public class ZxsApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(ZxsApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// TODO Auto-generated method stub
		return application.sources(ZxsApplication.class);
	}
	
    @Bean 
    MetaDataManager metaDataManager() {
    	return new MetaDataManager(new String[] {"com.tonggu.controller"});
    }
}
