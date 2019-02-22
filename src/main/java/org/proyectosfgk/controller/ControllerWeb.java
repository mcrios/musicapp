package org.proyectosfgk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class ControllerWeb {

	@GetMapping("/index")
	public ModelAndView index () {
		return new ModelAndView("index");
	}
}
