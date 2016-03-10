package br.ufc.quixada.cti.gin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {

	@RequestMapping(value = {"/", ""})
	public String index(){
		return "index";
	}
}