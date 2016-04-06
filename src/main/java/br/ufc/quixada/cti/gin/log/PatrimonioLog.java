package br.ufc.quixada.cti.gin.log;

import javax.inject.Inject;

import br.ufc.quixada.cti.gin.model.Historico;
import br.ufc.quixada.cti.gin.model.Patrimonio;
import br.ufc.quixada.cti.gin.service.HistoricoService;
import br.ufc.quixada.cti.gin.service.impl.HistoricoImpl;

public class PatrimonioLog {
	
	@Inject
	HistoricoService historicoService;
			
	public  void editar(Patrimonio antigo, Patrimonio novo){
		String comment = "";
				
		if(!antigo.getTombamento().equals(novo.getTombamento())){
			comment = "Tombamento alterado de " + antigo.getTombamento() + " para " + novo.getTombamento() + "/n";
		}else if(!antigo.getDescricao().equals(novo.getDescricao())){
			comment += "Descrição alterada de " + antigo.getDescricao() + " para " + novo.getDescricao() + "/n";
		}else if(!antigo.getCategoria().getNome().equals(novo.getCategoria().getNome())){
			comment += "Categoria alterada de " + antigo.getCategoria().getNome() + " para " + novo.getCategoria().getNome() + "/n";
		}else if(!antigo.getSituacao().getTipo().equals(novo.getSituacao().getTipo())){
			comment += "Situação alterada de " + antigo.getSituacao().getTipo() + " para " + novo.getSituacao().getTipo() + "/n";
		}else if(!antigo.getList_de_lotacao().getTipo().equals(novo.getList_de_lotacao().getTipo())){
			comment += "Lista de alterada mudada de " + antigo.getList_de_lotacao().getTipo() + " para " + novo.getList_de_lotacao().getTipo() + "/n";
		}else if(!antigo.getConservacao().getTipo().equals(novo.getConservacao().getTipo())){
			comment += "Conservação alterada de " + antigo.getConservacao().getTipo() + " para " + novo.getConservacao().getTipo() + "/n";		
		}else if(!antigo.getLocal().getId().equals(novo.getLocal().getId())){
			comment += "Local alterado de " + antigo.getLocal().getFullLocal() + " para " + novo.getLocal().getFullLocal() + "/n";	
		}else if(!antigo.getData_incorporacao().equals(novo.getData_incorporacao())){
			comment += "Data incoporação alterada de " + antigo.getData_incorporacao() + " para " + novo.getData_incorporacao() + "/n";
		}else if(!antigo.getData_chegada_campus().equals(novo.getData_chegada_campus())){
			comment += "Data de chegada no campus alterada de " + antigo.getData_chegada_campus() + " para " + novo.getData_chegada_campus() + "/n";
		}
		
		Historico historico = new Historico();
		historico.setComentario(comment);
		historico.setPatrimonio(novo);		
		
		historicoService.save(historico);
		
		
		
		
			
	}

}
