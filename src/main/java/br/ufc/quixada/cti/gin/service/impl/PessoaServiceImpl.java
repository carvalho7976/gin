package br.ufc.quixada.cti.gin.service.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.cti.gin.model.Pessoa;
import br.ufc.quixada.cti.gin.service.PessoaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {

	@Override
	@Transactional(readOnly = true)
	public Pessoa getPessoaByCpf(String cpf) {
		return (Pessoa) findFirst("Pessoa.findPessoaByCpf", new SimpleMap<String, Object>("cpf", cpf));
	}

}
