package br.ufc.quixada.cti.gin.service.impl;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy;
	
	@Inject
	private UsuarioService usuarioService;
	
	public AuthenticationSuccessHandlerImpl() {
		redirectStrategy = new DefaultRedirectStrategy();
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		handle(request, response, auth);
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
		usuarioLogado(request, auth);
		redirectStrategy.sendRedirect(request, response, determineUrl(auth));
	}
	
	private String determineUrl(Authentication authentication) {
		Usuario usuario = usuarioService.getByCpf(authentication.getName());
		
		for (GrantedAuthority ga : usuario.getAuthorities()) {
			if (ga.getAuthority().equalsIgnoreCase("ADMIN_GIN") || ga.getAuthority().equalsIgnoreCase("STA")) {
				return "/patrimonio/listar";
			}
		}
		
		return "/403";
	}
	
	private void usuarioLogado(HttpServletRequest request, Authentication auth) {
		if (request.getSession().getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.getByCpf(auth.getName());
			
			String nome = usuario.getNome();
			String id = usuario.getSiape();
			
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("usuario", nome);
			
		} else {
			Usuario usuario = usuarioService.getByCpf(auth.getName());
			
			String nome = usuario.getNome();
			String id = usuario.getSiape();
			
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("usuario", nome);
		}

	}

}
