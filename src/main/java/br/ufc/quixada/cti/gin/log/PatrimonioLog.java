package br.ufc.quixada.cti.gin.log;

import java.util.Date;
import br.ufc.quixada.cti.gin.model.Historico;
import br.ufc.quixada.cti.gin.model.Patrimonio;

public class PatrimonioLog {
	
				
	public static Historico editar(Patrimonio antigoPatrimonio, Patrimonio novoPatrimonio){
		
		String comment = "";
				
		if(!antigoPatrimonio.getTombamento().equals(novoPatrimonio.getTombamento())){
			comment = "Tombamento alterado de " + antigoPatrimonio.getTombamento() + " para " + novoPatrimonio.getTombamento() + "# ";
			
		}
		
		if(!antigoPatrimonio.getDescricao().equalsIgnoreCase(novoPatrimonio.getDescricao())){
			comment += "Descrição alterada de " + antigoPatrimonio.getDescricao() + " para " + novoPatrimonio.getDescricao() + "# ";
			
		}
		
		if(!antigoPatrimonio.getCategoria().getNome().equals(novoPatrimonio.getCategoria().getNome())){
			comment += "Categoria alterada de " + antigoPatrimonio.getCategoria().getNome() + " para " + novoPatrimonio.getCategoria().getNome() + " # ";
			
		}
		
		if(!antigoPatrimonio.getSituacao().getTipo().equals(novoPatrimonio.getSituacao().getTipo())){
			comment += "Situação alterada de " + antigoPatrimonio.getSituacao().getTipo() + " para " + novoPatrimonio.getSituacao().getTipo() + "# ";
			
		}
		
		if(!antigoPatrimonio.getLotacao().getTipo().equals(novoPatrimonio.getLotacao().getTipo())){
			comment += "Lista de lotação mudada de " + antigoPatrimonio.getLotacao().getTipo() + " para " + novoPatrimonio.getLotacao().getTipo() + "# ";
			
		}
		
		if(!antigoPatrimonio.getConservacao().getTipo().equals(novoPatrimonio.getConservacao().getTipo())){
			comment += "Conservação alterada de " + antigoPatrimonio.getConservacao().getTipo() + " para " + novoPatrimonio.getConservacao().getTipo() + "#";
			
		}
		
		if(!(antigoPatrimonio.getLocal().getId() == novoPatrimonio.getLocal().getId())){
			comment += "Local alterado de " + antigoPatrimonio.getLocal().getFullLocal() + " para " + novoPatrimonio.getLocal().getFullLocal() + "# ";
			
		}
		
		if(!antigoPatrimonio.getIncorporacao().equals(novoPatrimonio.getIncorporacao())){
			comment += "Data incoporação alterada de " + antigoPatrimonio.getIncorporacao() + " para " + novoPatrimonio.getIncorporacao() + "# ";
			
		}
		
		if(antigoPatrimonio.getChegadaCampus() == null && novoPatrimonio.getChegadaCampus() != null){
			comment += "Data de chegada no campus alterada de " + antigoPatrimonio.getChegadaCampus() + " para " + novoPatrimonio.getChegadaCampus() + "# ";
			
		} else if (antigoPatrimonio.getChegadaCampus() != null) {
			if (!antigoPatrimonio.getChegadaCampus().equals(novoPatrimonio.getChegadaCampus())) {
				comment += "Data de chegada no campus alterada de " + antigoPatrimonio.getChegadaCampus() + " para " + novoPatrimonio.getChegadaCampus() + "# ";
			}
		}
		
		if(!antigoPatrimonio.getComentario().equalsIgnoreCase(novoPatrimonio.getComentario())){
			comment += "Comentário alterado de ( " + antigoPatrimonio.getComentario() + " ) para ( " + novoPatrimonio.getComentario() + " ) #";
			
		}
		
		if(!comment.isEmpty()){
			Historico historico = new Historico();
			
			historico.setComentario(comment);
			historico.setPatrimonio(novoPatrimonio);
			historico.setTimestamp(new Date() );
			
			return historico;
		}
		
		return null;		
			
	}

}
