package com.xzq.webflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class HelloWorldHandler {

	public Mono<ServerResponse> hello(ServerRequest request) {
//		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromValue("hello world! 夏志强"));
		return ServerResponse.ok().header("Content-Type","text/plain; charset=utf-8").body(BodyInserters.fromValue("hello world! 夏志强"));
	}
}
