package com.xzq.webflux.test;

import java.time.Duration;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class MonoTest {

	public static void main(String[] args) {
		Mono.empty().subscribe(System.out::println);
		Mono.just("wahah").subscribe(System.out::println);
		Mono.error(new RuntimeException("error")).subscribe(System.out::println, System.err::println);
		Mono.just("wahah").subscribe(System.out::println, System.err::println);
		Disposable dis = Mono.delay(Duration.ofSeconds(5)).subscribe(i -> {
			System.out.println(i + "," + Thread.currentThread().getName());
		});
		while(!dis.isDisposed()) { }
	}

}
