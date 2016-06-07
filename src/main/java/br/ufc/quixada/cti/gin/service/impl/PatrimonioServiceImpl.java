package br.ufc.quixada.cti.gin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public boolean isCategoriaCadastrada(String nomeCategoria) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nome", nomeCategoria);
		
		Categoria categoria = (Categoria) findFirst(QueryType.JPQL, " select c from Categoria c where c.nome = :nome", params);
	
		if (categoria == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isLocalizacaoCadastrada(String localizacao) {
		@SuppressWarnings("unchecked")
		List<Local> locais = find(QueryType.JPQL, "from Local as l where l.localizacao = :localizacao", 
				new SimpleMap<String, Object>("localizacao", localizacao));
		
		if (locais == null || locais.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public Categoria getCategoria(long id) {
		return categoriaRepository.find(Categoria.class, id);
	}

	@Override
	public Local getLocal(long id) {
		return localRepository.find(Local.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Patrimonio getPatrimonioComHistorico(Integer idPatrimonio) {
		return (Patrimonio) findFirst("Patrimonio.findPatrimonioComHistoricoById", new SimpleMap<String, Object>("idPatrimonio", idPatrimonio));
	}

	@Override
	public boolean isPatrimonioCadastrado(Integer tombamento) {
		if(tombamento == null) {
			return false;
		}
		
		@SuppressWarnings("unchecked")
		List<Patrimonio> patrimonios = find(QueryType.JPQL, "from Patrimonio as p where p.tombamento = :tombamento", 
				new SimpleMap<String, Object>("tombamento", tombamento));
		
		if (patrimonios == null || patrimonios.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Patrimonio> getPatrimonioByTombamento(Integer tombamento) {
		return find("Patrimonio.findPatrimonioByTombamento", new SimpleMap<String, Object>("tombamento", tombamento));
	}

}
