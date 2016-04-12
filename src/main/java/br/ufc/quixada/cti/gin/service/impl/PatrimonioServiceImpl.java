package br.ufc.quixada.cti.gin.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.cti.gin.model.Categoria;
import br.ufc.quixada.cti.gin.model.Local;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.PatrimonioService;
import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class PatrimonioServiceImpl extends GenericServiceImpl<Patrimonio> implements PatrimonioService{

	@Inject
	private GenericRepository<Categoria> categoriaRepository;
	
	@Inject
	private GenericRepository<Local> localRepository;
	
	@Override
	public void salvarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
		
	}

	@Override
	public List<Categoria> getCategorias() {
		return categoriaRepository.find(Categoria.class);
	}

	@Override
	public void salvarLocal(Local local) {
		localRepository.save(local);
		
	}

	@Override
	public List<Local> getLocais() {
		return localRepository.find(Local.class);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isPatrimonioCadastrado(Patrimonio patrimonio) {
		@SuppressWarnings("unchecked")
		List<Patrimonio> patrimonios = find(QueryType.JPQL, "from Patrimonio as p where p.tombamento = :tombamento", 
				new SimpleMap<String, Object>("tombamento", patrimonio.getTombamento()));
		
		if (patrimonios == null || patrimonios.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isCategoriaCadastrada(Categoria categoria) {
		@SuppressWarnings("unchecked")
		List<Categoria> categorias = find(QueryType.JPQL, "from Categoria as c where c.nome = :nome", 
				new SimpleMap<String, Object>("nome", categoria.getNome()));
		
		if (categorias == null || categorias.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isLocalNomeCadastrado(Local local) {
		@SuppressWarnings("unchecked")
		List<Categoria> locais = find(QueryType.JPQL, "from Local as l where l.nome = :nome", 
				new SimpleMap<String, Object>("nome", local.getNome()));
		
		if (locais == null || locais.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isLocalPavimentoCadastrado(Local local) {
		@SuppressWarnings("unchecked")
		List<Categoria> locais = find(QueryType.JPQL, "from Local as l where l.pavimento = :pavimento", 
				new SimpleMap<String, Object>("pavimento", local.getPavimento()));
		
		if (locais == null || locais.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isLocalBlocoCadastrado(Local local) {
		@SuppressWarnings("unchecked")
		List<Categoria> locais = find(QueryType.JPQL, "from Local as l where l.bloco = :bloco", 
				new SimpleMap<String, Object>("bloco", local.getBloco()));
		
		if (locais == null || locais.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
}
