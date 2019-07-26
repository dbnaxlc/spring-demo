package com.xzq.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
//		return new Class[]{Config.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MyWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//将DispatcherServlet映射到 /
		return new String[] {"/"};
	}

}
