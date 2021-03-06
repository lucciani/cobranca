package com.lucciani.cobranca;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class CobrancaApplication {

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/titulos");
		SpringApplication.run(CobrancaApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	/*
	@Configuration
	public static class MvcConfig extends WebMvcConfigurationSupport  {
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/titulos");
		}
		
	}*/
	
}
