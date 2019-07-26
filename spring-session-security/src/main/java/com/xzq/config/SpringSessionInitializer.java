package com.xzq.config;

import org.springframework.core.annotation.Order;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
@Order(99)
public class SpringSessionInitializer 
extends
		AbstractHttpSessionApplicationInitializer
{

}
