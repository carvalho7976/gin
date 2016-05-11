package br.ufc.quixada.cti.gin.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping(value = { "/", "/listar" }, method = RequestMethod.GET)
	public String getPatrimonios(Model model) {

		model.addAttribute("patrimonios", patrimonioService.find(Patrimonio.class));

		return "patrimonio/listar-patrimonios";
	}

	@RequestMapping(value = { "/cadastrar" }, method = RequestMethod.GET)
	public String cadastrarPatrimonio(Model model) {

		model.addAttribute("action", "cadastrar");
		
		model.addAttribute("patrimonio", new Patrimonio());
		
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("categorias", patrimonioService.getCategorias());
		
		model.addAttribute("local", new Local());
		model.addAttribute("locais", patrimonioService.getLocais());

		return "patrimonio/cadastrar-patrimonio";
	}

	@RequestMapping(value = { "/cadastrar" }, method = RequestMethod.POST)
	public String cadastrarPatrimonio(Model model, @Valid @ModelAttribute("patrimonio") Patrimonio patrimonio,
			BindingResult result, RedirectAttributes redirect) {

		model.addAttribute("action", "cadastrar");

		if (patrimonio != null) {
			if (patrimonioService.isPatrimonioCadastrado(patrimonio)) {
				result.rejectValue("tombamento", "patrimonio.tombamento", "Número de tombamento já existe.");
			}
			
			if (patrimonio.getCategoria().getId() == null) {
				result.rejectValue("categoria", "patrimonio.categoria", "Selecione uma categoria.");
			}
			
			if (patrimonio.getLocal().getId() == null) {
				result.rejectValue("local", "patrimonio.local", "Selecione um local.");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("patrimonio", patrimonio);
			
			model.addAttribute("categoria", new Categoria());
			model.addAttribute("categorias", patrimonioService.getCategorias());
			
			model.addAttribute("local", new Local());
			model.addAttribute("locais", patrimonioService.getLocais());

			return "patrimonio/cadastrar-patrimonio";
		}

		patrimonioService.save(patrimonio);

		redirect.addFlashAttribute("info", "Patrimônio cadastrado com sucesso.");
		return "redirect:/patrimonio/listar";
	}

	@RequestMapping(value = { "/editar/{idPatrimonio}" }, method = RequestMethod.GET)
	public String editarPatrimonio(@PathVariable("idPatrimonio") Integer idPatrimonio, Model model) {

		Patrimonio patrimonio = patrimonioService.find(Patrimonio.class, idPatrimonio);

		model.addAttribute("action", "editar");
		model.addAttribute("patrimonio", patrimonio);

		model.addAttribute("idPatrimonio", patrimonio.getId());
		
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("categorias", patrimonioService.getCategorias());
		
		model.addAttribute("local", new Local());
		model.addAttribute("locais", patrimonioService.getLocais());

		return "patrimonio/cadastrar-patrimonio";
	}

	@RequestMapping(value = { "/editar" }, method = RequestMethod.POST)
	public String editarPatrimonio(@Valid @ModelAttribute("patrimonio") Patrimonio patrimonio, Model model,
			BindingResult result, RedirectAttributes redirect) {

		model.addAttribute("action", "editar");
		
		if (patrimonio != null) {
			if (patrimonio.getCategoria().getId() == null) {
				result.rejectValue("categoria", "patrimonio.categoria", "Selecione uma categoria.");
			}
			
			if (patrimonio.getLocal().getId() == null) {
				result.rejectValue("local", "patrimonio.local", "Selecione um local.");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("idPatrimonio", patrimonio.getId());
			
			model.addAttribute("patrimonio", patrimonio);
			
			model.addAttribute("categoria", new Categoria());
			model.addAttribute("categorias", patrimonioService.getCategorias());
			
			model.addAttribute("local", new Local());
			model.addAttribute("locais", patrimonioService.getLocais());

			return "patrimonio/cadastrar-patrimonio";
		}

		Patrimonio antigo = patrimonioService.find(Patrimonio.class, patrimonio.getId());
		patrimonioService.update(patrimonio);

		// preenche o objeto completo categoria e local pois os mesmo vem apenas
		// com idPatrimonio na chamada do controller;
		patrimonio.setCategoria(patrimonioService.getCategoria(patrimonio.getCategoria().getId()));
		patrimonio.setLocal(patrimonioService.getLocal(patrimonio.getLocal().getId()));

		Historico historico = PatrimonioLog.editar(antigo, patrimonio);
		if (historico != null) {
			historicoService.save(historico);
		}

		redirect.addFlashAttribute("info", "Patrimônio atualizado com sucesso.");
		return "redirect:/patrimonio/listar";
	}

	@RequestMapping(value = { "/excluir/{idPatrimonio}" }, method = RequestMethod.GET)
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
	
	@RequestMapping(value = { "detalhe/{idPatrimonio}" }, method = RequestMethod.GET)
	public @ResponseBody Patrimonio getPatrimonio(@PathVariable("idPatrimonio") Integer idPatrimonio){
		return patrimonioService.getPatrimonioComHistorico(idPatrimonio);
	}

	@RequestMapping(value = { "/cadastrar/categoria" }, method = RequestMethod.POST)
	public String addCategoria(@RequestParam("action") String action, @RequestParam("idPatrimonio") Integer idPatrimonio,
			Model model, @Valid @ModelAttribute("categoria") Categoria categoria,
			BindingResult result, RedirectAttributes redirect) {

		if (categoria != null) {
			if (patrimonioService.isCategoriaCadastrada(categoria.getNome())) {
				result.rejectValue("nome", "categoria.nome", "Categoria já cadastrada.");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("categoria", categoria);
			model.addAttribute("patrimonio", new Patrimonio());
			model.addAttribute("categorias", patrimonioService.getCategorias());
			model.addAttribute("locais", patrimonioService.getLocais());
			
			if (action.equals("editar")) {
				return "redirect:/patrimonio/editar/" + idPatrimonio;
			}
			
			return "redirect:/patrimonio/cadastrar";
		}

		patrimonioService.salvarCategoria(categoria);
		redirect.addFlashAttribute("info", "Nova categoria adicionada.");
		
		if (action.equals("editar")) {
			return "redirect:/patrimonio/editar/" + idPatrimonio;
		}
		
		return "redirect:/patrimonio/cadastrar";
	}

	@RequestMapping(value = { "/cadastrar/local" }, method = RequestMethod.POST)
	public String addLocal(@RequestParam("action") String action, @RequestParam("idPatrimonio") Integer idPatrimonio,
			Model model, @Valid @ModelAttribute("local") Local local, BindingResult result,
			RedirectAttributes redirect) {

		if (local != null) {

			if (patrimonioService.isLocalizacaoCadastrada(local.getLocalizacao())) {
				result.rejectValue("localizacao", "local.localizacao", "Localização já está cadastrada.");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("local", local);
			model.addAttribute("patrimonio", new Patrimonio());
			model.addAttribute("categorias", patrimonioService.getCategorias());
			model.addAttribute("locais", patrimonioService.getLocais());
			
			if (action.equals("editar")) {
				return "redirect:/patrimonio/editar/" + idPatrimonio;
			}
			
			return "redirect:/patrimonio/cadastrar";
		}

		patrimonioService.salvarLocal(local);
		redirect.addFlashAttribute("info", "Novo local adicionado.");
		
		if (action.equals("editar")) {
			return "redirect:/patrimonio/editar/" + idPatrimonio;
		}

		return "redirect:/patrimonio/cadastrar";
	}

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		List<Patrimonio> listaPatrimonios = patrimonioService.find(Patrimonio.class);

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "listaPatrimonios", listaPatrimonios);
	}
	
	@RequestMapping(value = { "checkCategoria" }, method = RequestMethod.POST)
	public @ResponseBody boolean checkCategoria(String nomeCategoria) {
		System.out.println(nomeCategoria);
		return patrimonioService.isCategoriaCadastrada(nomeCategoria);
	}
	
	@RequestMapping(value = { "checkLocalizacao" }, method = RequestMethod.POST)
	public @ResponseBody boolean checkLocalizacao(String localizacao) {
		return patrimonioService.isLocalizacaoCadastrada(localizacao);
	}
}
