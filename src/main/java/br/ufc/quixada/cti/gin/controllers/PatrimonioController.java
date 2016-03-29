package br.ufc.quixada.cti.gin.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.PatrimonioService;

@Controller
@RequestMapping("patrimonio")
public class PatrimonioController {

	@Inject
	private PatrimonioService patrimonioService;
	
	@RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
	public String getPatrimonios(Model model) {
		
		model.addAttribute("patrimonios", patrimonioService.find(Patrimonio.class));
		
		return "patrimonio/listar-patrimonio";
	}
	
	@RequestMapping(value = {"/cadastrar"}, method = RequestMethod.GET)
	public String cadastrarPatrimonio(Model model) {
		
		model.addAttribute("action", "cadastrar");
		model.addAttribute("patrimonio", new Patrimonio());
		model.addAttribute("categorias", patrimonioService.getCategorias());
		
		return "patrimonio/cadastrar-patrimonio";
	}
	
	@RequestMapping(value = {"/cadastrar"}, method = RequestMethod.POST)
	public String cadastrarPatrimonio(Model model, @Valid @ModelAttribute("patrimonio") Patrimonio patrimonio, 
			BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "cadastrar");
		
		if (result.hasErrors()) {
			model.addAttribute("patrimonio", patrimonio);
			model.addAttribute("categorias", patrimonioService.getCategorias());
			return "patrimonio/cadastrar-patrimonio";
		}
		
		patrimonioService.save(patrimonio);
		
		redirect.addFlashAttribute("info", "Patrimônio cadastrado com sucesso.");
		return "redirect:/patrimonio/listar";
	}
	
	@RequestMapping(value = {"/editar/{idPatrimonio}"}, method = RequestMethod.GET)
	public String editarPatrimonio(@PathVariable("idPatrimonio") Integer idPatrimonio, Model model) {
		
		Patrimonio patrimonio = patrimonioService.find(Patrimonio.class, idPatrimonio);
		
		model.addAttribute("action", "editar");
		model.addAttribute("patrimonio", patrimonio);
		
		return "patrimonio/cadastrar-patrimonio";
	}
	
	@RequestMapping(value = {"/editar"}, method = RequestMethod.POST)
	public String editarPatrimonio(Model model, @Valid @ModelAttribute("patrimonio") Patrimonio patrimonio, 
			BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "editar");
		
		if (result.hasErrors()) {
			model.addAttribute("patrimonio", patrimonio);
			return "patrimonio/cadastrar-patrimonio";
		}
		
		redirect.addFlashAttribute("info", "Patrimônio atualizado com sucesso.");
		return "redirect:/patrimonio/listar";
	}
	
	@RequestMapping(value = {"/excluir/{idPatrimonio}"}, method = RequestMethod.GET)
	public String deletarPatrimonio(@PathVariable("idPatrimonio") Integer idPatrimonio, RedirectAttributes redirect) {
		
		Patrimonio patrimonio = patrimonioService.find(Patrimonio.class, idPatrimonio);
		
		if (patrimonio != null) {
			patrimonioService.delete(patrimonio);
			redirect.addFlashAttribute("info", "Patrimônio excluído com sucesso.");
			
		} else {
			redirect.addFlashAttribute("erro", "Patrimônio inexistente.");
			
		}
		
		return "redirect:/patrimonio/listar";
	}
	
	@RequestMapping(value = {"/cadastrar/categoria"}, method = RequestMethod.GET)
	public String addCategoria(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		
		return "patrimonio/cadastrar-categoria";
	}
	
	@RequestMapping(value = {"/cadastrar/categoria"}, method = RequestMethod.POST)
	public String addCategoria(Model model, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, RedirectAttributes redirect) {
		
		if (result.hasErrors()) {
			model.addAttribute("categoria", categoria);
			return "patrimonio/cadastrar-categoria";
		}
		
		patrimonioService.salvarCategoria(categoria);
		redirect.addFlashAttribute("info", "Nova categoria adicionada.");
		return "redirect:/patrimonio/cadastrar";
	}
}
