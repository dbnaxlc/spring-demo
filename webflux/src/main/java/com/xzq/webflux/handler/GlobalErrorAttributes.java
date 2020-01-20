package com.xzq.webflux.handler;

import java.util.Map;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;

/**
 * 全局异常处理类
 * 如果有多个 DefaultErrorAttributes 实现。可以使用 @Order() 来定义优先级
 * @date 2020年1月20日 下午4:24:13
 */
public class GlobalErrorAttributes extends DefaultErrorAttributes{

	public GlobalErrorAttributes() {
        super(false);
    }

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		Map<String, Object> errorAttr = super.getErrorAttributes(request, includeStackTrace);
		Throwable th = getError(request);
		if(th instanceof RuntimeException) {
			errorAttr.put("cause", th.getCause());
		} else {
			errorAttr.put("code", "666");
		}
		return errorAttr;
	}
	
	
}
