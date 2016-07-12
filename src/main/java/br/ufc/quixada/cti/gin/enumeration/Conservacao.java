package br.ufc.quixada.cti.gin.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Conservacao {
	
	EXCELENTE("Excelente"), 
	OTIMO("Ótimo"),
	BOM("Bom"),
	REGULAR("Regular"), 
	RUIM("Ruim"), 
	ND("Nada");
	
	private String tipo;

	private Conservacao(String tipo) {
		this.tipo = tipo;
	}
	@JsonValue
	public String getTipo() {
		return tipo;
	}
	
}
