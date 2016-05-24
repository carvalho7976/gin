package br.ufc.quixada.cti.gin.controllers;

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
	
	public String inicio(Authentication auth) {
		
		if (auth != null) {
			Usuario usuario = usuarioService.getByCpf(auth.getName());
			
			for (GrantedAuthority ga : usuario.getAuthorities()) {
				if (ga.getAuthority().equalsIgnoreCase("PREFEITO") || ga.getAuthority().equalsIgnoreCase("STA")) {
					return "redirect:/servidor/patrimonio/listar";
				}
			}
		}
		
		return "redirect:/servidor/patrimonio/listar";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "logout", required = false) String logout, 
								@RequestParam(value = "error", required = false) String error) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Error! Login inválido.");
		}
		
		if (logout != null) {
			model.addObject("info", "Logout realizado.");
		}
		
		model.setViewName("login");
		
		return model;
	}
	
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginError(ModelMap model) {

		model.addAttribute("error", "CPF ou senha inválidos.");
		
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
			model.addAttribute("message", "Olá " + user.getName()  + ", você não tem permissão para acessar essa funcionalidade.");
		
		} else {
			model.addAttribute("message", "Permissão negada.");
		}
		
		return "403";
	}
}
