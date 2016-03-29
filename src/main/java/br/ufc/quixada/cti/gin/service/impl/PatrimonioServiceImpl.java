package br.ufc.quixada.cti.gin.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.PatrimonioService;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;

@Named
public class PatrimonioServiceImpl extends GenericServiceImpl<Patrimonio> implements PatrimonioService{

	@Inject
	private GenericRepository<Categoria> categoriaRepository;
	
	@Override
	public void salvarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
		
	}

	@Override
	public List<Categoria> getCategorias() {
		return categoriaRepository.find(Categoria.class);
	}
	
}
