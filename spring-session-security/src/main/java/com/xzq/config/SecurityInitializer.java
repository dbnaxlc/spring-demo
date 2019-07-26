package com.xzq.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * security初始化类
 */
@Order(100)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
