package br.ufc.quixada.cti.gin.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.ufc.quixada.npi.ldap.model.Usuario;
import br.ufc.quixada.npi.ldap.service.UsuarioService;

@Named
public class LdapAuthentication implements AuthenticationProvider {
	
	@Inject
	private UsuarioService usuarioService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = (String) auth.getCredentials();
		
		Usuario usuario = usuarioService.getByCpf(username);
		
		if (usuario == null || !usuarioService.autentica(username, password) || usuario.getAuthorities().isEmpty()) {
			
			throw new BadCredentialsException("Login inválido.");
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, usuario.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
