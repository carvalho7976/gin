package br.ufc.quixada.cti.gin.service;

import br.ufc.quixada.cti.gin.model.Pessoa;
import br.ufc.quixada.npi.service.GenericService;

public interface PessoaService extends GenericService<Pessoa> {

	public abstract Pessoa getPessoaByCpf(String cpf);
}
