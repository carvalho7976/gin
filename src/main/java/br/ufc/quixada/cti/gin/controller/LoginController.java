package br.ufc.quixada.cti.gin.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Controller
public class LoginController {

	@Inject
	private UsuarioService usuarioService;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String inicio(Authentication auth) {
		
		if (auth != null) {
			Usuario usuario = usuarioService.getByCpf(auth.getName());
			
			for (GrantedAuthority ga : usuario.getAuthorities()) {
				if (ga.getAuthority().equalsIgnoreCase("ADMIN_GIN") || ga.getAuthority().equalsIgnoreCase("STA")) {
					return "redirect:/patrimonio/listar";
				}
			}
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "logout", required = false) String logout, 
								@RequestParam(value = "error", required = false) String error) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Usuário e/ou senha inválidos!");
		}
		
		if (logout != null) {
			model.addObject("info", "Logout realizado com sucesso.");
		}
		
		model.setViewName("login");
		
		return model;
	}
	
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginError(ModelMap model) {

		model.addAttribute("error", "Usuário e/ou senha inválidos!");
		
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		
		session.invalidate();

		return "login";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String acessoNegado(ModelMap model, Principal user) {
		if (user != null) {
			model.addAttribute("message", "Olá " + user.getName()  + ", você não tem permissão para acessar essa página.");
		
		} else {
			model.addAttribute("message", "Você não tem permissão para acessar essa página.");
		}
		
		return "403";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String paginaInexistente(ModelMap model, Principal user) {
		model.addAttribute("message", "Ops, página não encontrada.");
		return "404";
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String erroServidor(ModelMap model, Principal user) {
		model.addAttribute("message", "Ops, o site teve um erro técnico.");
		return "500";
	}
}
