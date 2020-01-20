package com.xzq.webflux.controller;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FileRoute {

	@PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Mono<String> upload(@RequestPart("file") FilePart filePart) throws IOException {
		Path path = Files.createTempFile("xzq", filePart.filename());
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
		DataBufferUtils.write(filePart.content(), channel, 0).doOnComplete(() -> {
			System.out.println("finish");
		}).subscribe();
		// filePart.transferTo(path.toFile());
		return Mono.just(filePart.filename());
	}
	
	@GetMapping("/download")
	public Mono<Void> download(ServerHttpResponse response) throws IOException {
		ZeroCopyHttpOutputMessage message = (ZeroCopyHttpOutputMessage)response;
		response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.png");
        response.getHeaders().setContentType(MediaType.IMAGE_PNG);
        Resource resource = new ClassPathResource("G:\test.png");
        File file = resource.getFile();
		return message.writeWith(file, 0, file.length());
	}
}
