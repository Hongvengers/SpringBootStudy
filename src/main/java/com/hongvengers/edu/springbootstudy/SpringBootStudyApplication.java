package com.hongvengers.edu.springbootstudy;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class SpringBootStudyApplication {

	public static void main(String[] args) {
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // Servlet 기반 Web Server를 띄우기 위한 인스턴스를 취득하는 Factory 구현체
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			HelloController helloController = new HelloController();
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				String respContext;
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // Servlet service() 동작 정의
					// 공통 기능(인증, 보안, 다국어)
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						String hello = helloController.hello(name);
						respContext = hello;

						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(hello);
					} else if (req.getRequestURI().equals("/user")) {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					} else {
					}
					System.out.println(respContext);
				}

				private String makeResponse(String name) {
					return String.format("Hello Servlet %s", name);
				}
			}).addMapping("/*"); // 생성한 Servlet Context를 routing uriPattern과 Mapping 시킴


		}); // ServletWebServerFactory를 통해 WebServer 인스턴스를 생성.
		webServer.start(); // 생성한 Web Server를 구동
	}

}
