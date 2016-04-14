package br.ufc.quixada.cti.gin.log;

import java.util.Date;
import br.ufc.quixada.cti.gin.model.Historico;
import br.ufc.quixada.cti.gin.model.Patrimonio;

public class PatrimonioLog {
	
				
	public static Historico editar(Patrimonio antigo, Patrimonio novo){
		String comment = "";
				
		if(!antigo.getTombamento().equals(novo.getTombamento())){
			comment = "Tombamento alterado de " + antigo.getTombamento() + " para " + novo.getTombamento() + "# ";
		}if(!antigo.getDescricao().equals(novo.getDescricao())){
			comment += "Descrição alterada de " + antigo.getDescricao() + " para " + novo.getDescricao() + "# ";
		}if(!antigo.getCategoria().getNome().equals(novo.getCategoria().getNome())){
			comment += "Categoria alterada de " + antigo.getCategoria().getNome() + " para " + novo.getCategoria().getNome() + " # ";
		}if(!antigo.getSituacao().getTipo().equals(novo.getSituacao().getTipo())){
			comment += "Situação alterada de " + antigo.getSituacao().getTipo() + " para " + novo.getSituacao().getTipo() + "# ";
		}if(!antigo.getList_de_lotacao().getTipo().equals(novo.getList_de_lotacao().getTipo())){
			comment += "Lista de lotação mudada de " + antigo.getList_de_lotacao().getTipo() + " para " + novo.getList_de_lotacao().getTipo() + "# ";
		}if(!antigo.getConservacao().getTipo().equals(novo.getConservacao().getTipo())){
			comment += "Conservação alterada de " + antigo.getConservacao().getTipo() + " para " + novo.getConservacao().getTipo() + "#";		
		}if(!(antigo.getLocal().getId() == novo.getLocal().getId())){
			comment += "Local alterado de " + antigo.getLocal().getFullLocal() + " para " + novo.getLocal().getFullLocal() + "# ";	
		}if(!antigo.getData_incorporacao().equals(novo.getData_incorporacao())){
			comment += "Data incoporação alterada de " + antigo.getData_incorporacao() + " para " + novo.getData_incorporacao() + "# ";
		}if(!antigo.getData_chegada_campus().equals(novo.getData_chegada_campus())){
			comment += "Data de chegada no campus alterada de " + antigo.getData_chegada_campus() + " para " + novo.getData_chegada_campus() + "# ";
		}if(!antigo.getComentario().getMensagem().equals(novo.getComentario().getMensagem())){
			comment += "Comentário alterado de ( " + antigo.getComentario().getMensagem() + " ) para ( " + novo.getComentario().getMensagem() + " ) #";
		}
		if(!comment.isEmpty()){
			Historico historico = new Historico();
			historico.setComentario(comment);
			historico.setPatrimonio(novo);

			historico.setTimestamp(new Date() );
			return historico;
		}
		
		return null;		
			
	}

}
