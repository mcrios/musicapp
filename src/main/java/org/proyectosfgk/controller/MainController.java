package org.proyectosfgk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	 @GetMapping("/")
	 public String bienvenido() {
		 return "WEB SERVICE APP HEAR";
	 }
}
