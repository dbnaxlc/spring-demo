package com.xzq.webflux.handler;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * 每当发生错误时，我们可以使用 onErrorReturn() 返回静态默认值：, onErrorReturn 的范围更大一些而已。
 * onErrorResume 返回一个动态值，并且可以捕获，包装和重新抛出错误，例如作为自定义业务异常等。
 * @date 2020年1月20日 下午4:02:30
 */
public class ErrorHandler {
	
	public Mono<ServerResponse> errorResume(ServerRequest request) {
		String param = request.queryParam("name").orElse("李四");
		return paraCheck(param).flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(s))
                .onErrorResume(e -> Mono.just("Error: " + e.getMessage()).
                        flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(s))
				);
	}

	public Mono<ServerResponse> errorReturn(ServerRequest request) {
		String param = request.queryParam("name").orElse("李四");
		return paraCheck(param)
                .onErrorReturn("error return")
                .flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(s));
	}
	
	private Mono<String> paraCheck(String param) {
        String type = Optional.ofNullable(param).orElse(
                "李四"
        );
        switch (type) {
            case "李四":
                throw new NullPointerException("我抛出一个空异常了");
            default:
                return Mono.just(type);
        }
    }
}
