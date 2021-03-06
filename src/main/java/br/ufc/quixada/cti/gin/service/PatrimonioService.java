package br.ufc.quixada.cti.gin.service;

import java.util.List;

import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Local;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.npi.service.GenericService;

public interface PatrimonioService extends GenericService<Patrimonio> {
	
	public abstract Patrimonio getPatrimonioComHistorico(Integer idPatrimonio);
	
	public abstract List<Patrimonio> getPatrimonioByTombamento(Integer tombamento);
	public abstract List<Patrimonio> getPatrimonioByDescricao(String descricao);

	public abstract void salvarCategoria(Categoria categoria);
	
	public abstract void salvarLocal(Local local);
	
	public abstract List<Local> getLocais();
	
	public abstract List<Categoria> getCategorias();
	
	public abstract boolean isPatrimonioCadastrado(Integer tombamento);
	
	public abstract boolean isCategoriaCadastrada(String nomeCategoria);
	
	public abstract Categoria getCategoria(long id);
	
	public abstract Local getLocal(long id);
	
}
