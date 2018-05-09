package com.liuhe.redpacket.apidoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Component
@Configuration
@EnableSwagger
public class SwaggerConfig {
	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	public SwaggerConfig() {
		System.out.println(springSwaggerConfig);
	}

	/**
	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	 * framework - allowing for multiple swagger groups i.e. same code base
	 * multiple swagger resource listings.
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
				apiInfo()).includePatterns(".*?");
	}

	/*
	 * "标题 title", "描述 description", "termsOfServiceUrl", "联系邮箱 contact email",
	 * "许可证的类型 license type", "许可证的链接 license url"
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("批发记账宝API文档", "My Apps API Description",
				"My Apps API terms of service", "My Apps API Contact Email",
				"My Apps API Licence Type", "My Apps API License URL");
		return apiInfo;
	}
}