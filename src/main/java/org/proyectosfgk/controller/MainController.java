package org.proyectosfgk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
public class MainController {

	@GetMapping("/")
	public String bienvenido() {
		return "WEB SERVICE APP HEAR";
	}

	@GetMapping("/stream")
	public StreamingResponseBody prueba() {
		return new StreamingResponseBody() {

			@Override
			public void writeTo(OutputStream out) throws IOException {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100; i++) {
					out.write((Integer.toString(i) + "  -  ").getBytes());
					out.flush();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@GetMapping(value = "/otro", 
		    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBodyEmitter emiter() {
		final SseEmitter e = new SseEmitter();
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(() -> {
			for (int i = 0; i < 10000; i++) {
				try {
					e.send("/Fecha: " + " @ " + new Date(), MediaType.APPLICATION_JSON);
					
					Thread.sleep(1);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					e.completeWithError(e1);
					return;
				}
			}e.complete();
		});
		return e;
	}
}
