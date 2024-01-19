package com.ivanzkyanto.vivlio;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class VivlioApplication {

	public static void main(String[] args) {
		SpringApplication.run(VivlioApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource resourceBundle = new ReloadableResourceBundleMessageSource();
		resourceBundle.setBasename("classpath:messages");
		resourceBundle.setDefaultEncoding("UTF-8");
		resourceBundle.setCacheSeconds(10);
		return resourceBundle;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		return mapper;
	}

}
