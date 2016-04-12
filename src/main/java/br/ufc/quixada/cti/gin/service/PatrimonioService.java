package br.ufc.quixada.cti.gin.service;

import java.util.List;

import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Local;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.npi.service.GenericService;

public interface PatrimonioService extends GenericService<Patrimonio> {

	public abstract void salvarCategoria(Categoria categoria);
	
	public abstract void salvarLocal(Local local);
	
	public abstract List<Local> getLocais();
	
	public abstract List<Categoria> getCategorias();
	
	public abstract boolean isPatrimonioCadastrado(Patrimonio patrimonio);
	
	public abstract boolean isCategoriaCadastrada(Categoria categoria);
	
	public abstract boolean isLocalNomeCadastrado(Local local);
	
	public abstract boolean isLocalPavimentoCadastrado(Local local);
	
	public abstract boolean isLocalBlocoCadastrado(Local local);
}
