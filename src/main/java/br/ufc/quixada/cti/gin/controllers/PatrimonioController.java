package br.ufc.quixada.cti.gin.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.cti.gin.log.PatrimonioLog;
import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Historico;
import br.ufc.quixada.cti.gin.model.Local;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.HistoricoService;
import br.ufc.quixada.cti.gin.service.PatrimonioService;


@Controller
@RequestMapping("patrimonio")
public class PatrimonioController {

	@Inject
	private PatrimonioService patrimonioService;
	
	@Inject
	private HistoricoService historicoService;
	
	@RequestMapping(value = {"/","/listar"}, method = RequestMethod.GET)
	public String getPatrimonios(Model model) {
		
	model.addAttribute("patrimonios", patrimonioService.find(Patrimonio.class));
		
		return "patrimonio/listar-patrimonio";
	}
	
	@RequestMapping(value = {"/cadastrar"}, method = RequestMethod.GET)
	public String cadastrarPatrimonio(Model model) {
		
		model.addAttribute("action", "cadastrar");
		model.addAttribute("id", -1);
		model.addAttribute("patrimonio", new Patrimonio());
		model.addAttribute("categorias", patrimonioService.getCategorias());
		model.addAttribute("locais", patrimonioService.getLocais());

		
		return "patrimonio/cadastrar-patrimonio";
	}
	
	@RequestMapping(value = {"/cadastrar"}, method = RequestMethod.POST)
	public String cadastrarPatrimonio(Model model, @Valid @ModelAttribute("patrimonio") Patrimonio patrimonio, 
			BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "cadastrar");
		
		if (patrimonio != null) {
			if (patrimonioService.isPatrimonioCadastrado(patrimonio)) {
				result.rejectValue("tombamento", "patrimonio.tombamento", "Número de tombamento já existe.");
			}
		}
		
		if (result.hasErrors()) {
			
			model.addAttribute("patrimonio", patrimonio);
			model.addAttribute("id", -1);
			model.addAttribute("categorias", patrimonioService.getCategorias());
			model.addAttribute("locais", patrimonioService.getLocais());
			
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
		model.addAttribute("id", patrimonio.getId());
		model.addAttribute("patrimonio", patrimonio);
		model.addAttribute("categorias", patrimonioService.getCategorias());
		model.addAttribute("locais", patrimonioService.getLocais());
		
		return "patrimonio/cadastrar-patrimonio";
	}
	
	@RequestMapping(value = {"/editar"}, method = RequestMethod.POST)
	public String editarPatrimonio(Model model, @Valid @ModelAttribute("patrimonio") Patrimonio patrimonio, 
			BindingResult result, RedirectAttributes redirect) {
		
		model.addAttribute("action", "editar");
		model.addAttribute("id", patrimonio.getId());
		
		if (result.hasErrors()) {
			model.addAttribute("patrimonio", patrimonio);
			model.addAttribute("categorias", patrimonioService.getCategorias());
			model.addAttribute("locais", patrimonioService.getLocais());

			return "patrimonio/cadastrar-patrimonio";
		}
		
		Patrimonio antigo = patrimonioService.find(Patrimonio.class, patrimonio.getId());
		patrimonioService.update(patrimonio);

		//preenche o objeto completo categoria e local pois os mesmo vem apenas com id na chamada do controller;
		patrimonio.setCategoria(patrimonioService.getCategoria(patrimonio.getCategoria().getId()));
		patrimonio.setLocal(patrimonioService.getLocal(patrimonio.getLocal().getId()));
		
		Historico historico = PatrimonioLog.editar(antigo, patrimonio);
		if(historico != null)
			historicoService.save(historico);
		
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
	
	@RequestMapping(value = {"/cadastrar/categoria/{acao}/{id}"}, method = RequestMethod.GET)
	public String addCategoria(Model model, @PathVariable("acao") String acao, @PathVariable("id") Integer id) {
		
		if(acao.equals("editar")){
			model.addAttribute("acao", "editar");
			model.addAttribute("id", id);
			
		}else{
			model.addAttribute("acao", "cadastrar");
			model.addAttribute("id", id);
		}
		
		model.addAttribute("categoria", new Categoria());
		
		return "patrimonio/cadastrar-categoria";
	}
	
	@RequestMapping(value = {"/cadastrar/categoria"}, method = RequestMethod.POST)
	public String addCategoria(Model model, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, RedirectAttributes redirect, @RequestParam("acao") String acao, @RequestParam("idd") Integer id) {
		
		if (categoria != null) {
			if (patrimonioService.isCategoriaCadastrada(categoria)) {
				result.rejectValue("nome", "categoria.nome", "Categoria já cadastrada.");
			}
		}
		
		if (result.hasErrors()) {
			
			model.addAttribute("categoria", categoria);
			
			if (acao.equals("editar")){
				model.addAttribute("acao", "editar");
				model.addAttribute("id", id);
				
			} else {
				model.addAttribute("acao", "cadastrar");
				model.addAttribute("id", id);
			}
			
			return "patrimonio/cadastrar-categoria";
		}
		
		patrimonioService.salvarCategoria(categoria);
		redirect.addFlashAttribute("info", "Nova categoria adicionada.");
		
		if(acao.equals("editar")){
			
			return "redirect:/patrimonio/editar/"+id;
		}
		
		return "redirect:/patrimonio/cadastrar";
	}
	
	@RequestMapping(value = {"/cadastrar/local/{acao}/{id}"}, method = RequestMethod.GET)
	public String addLocal(Model model, @PathVariable("acao") String acao, @PathVariable("id") long id) {
		
		if(acao.equals("editar")){
			model.addAttribute("acao", "editar");
			model.addAttribute("id", id);
		}else{
			model.addAttribute("acao", "cadastrar");
			model.addAttribute("id", id);
		}
		
		model.addAttribute("local", new Local());
		
		return "patrimonio/cadastrar-local";
	}
	
	@RequestMapping(value = {"/cadastrar/local"}, method = RequestMethod.POST)
	public String addLocal(Model model, @Valid @ModelAttribute("local") Local local, BindingResult result, RedirectAttributes redirect,  @RequestParam("acao") String acao, @RequestParam("idd") long id) {
		
		if (local != null) {
			
			if (patrimonioService.isLocalNomeCadastrado(local)) {
				result.rejectValue("nome", "local.nome", "Nome do local já cadastrado.");
			}
			
			if (patrimonioService.isLocalPavimentoCadastrado(local)) {
				result.rejectValue("pavimento", "local.pavimento", "Pavimento do local já cadastrado.");
			}
			
			if (patrimonioService.isLocalBlocoCadastrado(local)) {
				result.rejectValue("bloco", "local.bloco", "Bloco do local já cadastrado.");
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("local", local);
			
			if (acao.equals("editar")) {
				model.addAttribute("acao", "editar");
				model.addAttribute("id", id);
				
			} else {
				model.addAttribute("acao", "cadastrar");
				model.addAttribute("id", id);
			}
			
			return "patrimonio/cadastrar-local";
		}
		
		patrimonioService.salvarLocal(local);
		redirect.addFlashAttribute("info", "Novo local adicionado.");
		
		if (acao.equals("editar")) {
			
			return "redirect:/patrimonio/editar/"+id;
		}
		
		return "redirect:/patrimonio/cadastrar";
	}
	
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		List<Patrimonio> listaPatrimonios = patrimonioService.find(Patrimonio.class);

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "listaPatrimonios", listaPatrimonios);
	}
}
