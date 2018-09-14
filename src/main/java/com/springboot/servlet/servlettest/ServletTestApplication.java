package com.springboot.servlet.servlettest;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServletTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletTestApplication.class, args);
	}


	@RestController
	public static class MyController{

		@GetMapping("/message")
		public String message(){
			return "hello world";
		}
	}

	@Bean
	public static WebServerFactoryCustomizer webServerFactoryCustomizer(){
		return new WebServerFactoryCustomizer() {
			@Override
			public void customize(WebServerFactory factory) {
				if(factory instanceof TomcatServletWebServerFactory){
					TomcatServletWebServerFactory factory1=TomcatServletWebServerFactory.class.cast(factory);
					factory1.addContextCustomizers(new TomcatContextCustomizer() {
						@Override
						public void customize(Context context) {
							context.setPath("/spring-boot");
						}
					});
					factory1.addConnectorCustomizers(new TomcatConnectorCustomizer() {
						@Override
						public void customize(Connector connector) {
							connector.setPort(8888);

						}
					});
				}
			}
		};
	}
}
