package br.ufc.quixada.cti.gin.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.PatrimonioService;

@Controller
@RequestMapping("patrimonio")
public class PatrimonioController {

	@Inject
	private PatrimonioService patrimonioService;
	
	@RequestMapping(value = {"listar"})
	public String getPatrimonios(Model model) {
		
		model.addAttribute("patrimonios", patrimonioService.find(Patrimonio.class));
		
		// TODO - definir jsp de retorno que irá listar todos os patrimônios.
		
		return "";
	}
}
