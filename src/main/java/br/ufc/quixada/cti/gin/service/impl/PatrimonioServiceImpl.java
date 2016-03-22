package br.ufc.quixada.cti.gin.service.impl;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.PatrimonioService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;

@Named
public class PatrimonioServiceImpl extends GenericServiceImpl<Patrimonio> implements PatrimonioService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void update(Patrimonio patrimonio){
		if(patrimonio != null)
			em.merge(patrimonio);
		
	}
}
