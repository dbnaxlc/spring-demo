package com.xzq.webflux.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * WebFilter过滤器没有servlet filter中的url pattern功能。也就是说它会对所有的 URL 请求进行链接，所以效率就比较低。
 * @date 2020年1月20日 下午3:09:32
 */
@Configuration
@Order(1)
public class HelloFilter implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String token = request.getHeaders().getFirst("token");
		System.out.println(token);
		if("false".equals(token)) {
			ServerHttpRequest errorReq = request.mutate().path("/errorPage").build();
			ServerWebExchange errorExchange = exchange.mutate().request(errorReq).build();
			return chain.filter(errorExchange);
		}
		exchange.getAttributes().put("tips", "hello");
		//在 WebFilterChain 的 filter 方法后面加入了部分逻辑处理,可以接doFinally、then、thenEmpty、thenMany、map、flatMap等用法。
		//只要保证这些链式用法后面最终返回 Mono<Void> 即可。
		return chain.filter(exchange).doFinally(message -> System.out.println(message));
	}

}
