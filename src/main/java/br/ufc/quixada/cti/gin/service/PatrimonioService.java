package br.ufc.quixada.cti.gin.service;

import java.util.List;

import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.npi.service.GenericService;

public interface PatrimonioService extends GenericService<Patrimonio> {

	public abstract void salvarCategoria(Categoria categoria);

	public abstract List<Categoria> getCategorias();
}
