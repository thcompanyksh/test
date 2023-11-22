package com.thecompany.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 스프링이 돌아가는데 있어서 설정을하는 관리하는 객체
@Configuration
// 스프링 제공 인터페이스 클래스를 구현하거나 상속할 필요없이 mvc구성정보를 제어할수 있게해줌
public class WebConfig implements WebMvcConfigurer{
	// 리소스 경로를 지정
	private String resourcePath = "/upload/**";
	// 저장 경로를 지정
	private String savePath = "file:///C:/springboot_img/";
	
	//오버라이드
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourcePath)
				.addResourceLocations(savePath);
	}
}
