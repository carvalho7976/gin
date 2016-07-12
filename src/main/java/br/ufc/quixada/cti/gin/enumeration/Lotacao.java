package br.ufc.quixada.cti.gin.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Lotacao {
	
	JA00("JA00"), ND("Nada");
	
	private String tipo;
	
	private Lotacao(String tipo) {
		this.tipo = tipo;
	}
	
	@JsonValue
	public String getTipo() {
		return tipo;
	}
}
