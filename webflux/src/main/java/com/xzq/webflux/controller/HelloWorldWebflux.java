package com.xzq.webflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.xzq.webflux.handler.HelloWorldHandler;

/**
 * 1、Router Functions是Spring 5新引入的一套Reactive风格（基于Flux和Mono）的函数式接口，
 * 		主要包括RouterFunction，HandlerFunction和HandlerFilterFunction，
 * 		分别对应Spring MVC中的@RequestMapping，@Controller和HandlerInterceptor（或者Servlet规范中的Filter）。
 * 2、RouterFunctions.route 对针具体 url 进行映射，可以是多个url，多种请求方式：get、post等。匹配到对应的 url 就会路由，否则不会执行路由。
 * 3、路由器功能映射的路由可以通过调用RouterFunction.filter(HandlerFilterFunction)进行过滤，
 * 		其中HandlerFilterFunction本质上是一个占用ServerRequest和HandlerFunction的函数，并返回一个ServerResponse
 * 4、如果要被多个 HandlerFilterFunction 进行拦截，可以链式调用多次 .filter 方法
 * @date 2020年1月20日 下午3:12:44
 */
@Configuration
public class HelloWorldWebflux {

	@Bean
	public RouterFunction<ServerResponse> helloWorld(HelloWorldHandler handler) {
		return RouterFunctions.route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::hello);
	}
	
	@Bean
	public RouterFunction<ServerResponse> helloWorldFlter() {
		return RouterFunctions.route(RequestPredicates.GET("/helloFilter")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), 
					request -> {
						System.out.println(request.attribute("tips"));
						return ServerResponse.ok().body(BodyInserters.fromValue("hello world filter"));
					})
				.filter((request, handler) -> {
					return ServerResponse.status(HttpStatus.OK)
							.header("Content-Type","text/plain; charset=utf-8")
                            .body(BodyInserters.fromValue(" 夏志强"));
				})
				.filter((request, handler) -> {
					if("/upload".equals(request.path())){
			            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
			        }
			        return handler.handle(request);
				});
	}
}
