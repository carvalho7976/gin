package br.ufc.quixada.cti.gin.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConformeRelatorio {
	NCR("Não consta no relatório"), 
	C("Consta no relatório"), 
	B("Baixa"),
	NE("Não existente");
	
	private String tipo; 
	
	private ConformeRelatorio(String tipo){
		this.tipo = tipo; 
	}
	
	@JsonValue
	public String getTipo() {
		return tipo;
	}
	
}
