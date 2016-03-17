package br.ufc.quixada.cti.gin.enumeration;

public enum Conservacao {
	
	RUIM("Ruim"), 
	REGULAR("Regular"), 
	EXCELENTE("Excelente"), 
	OTIMO("Ótimo"), 
	ND("Nada");
	
	private String tipo;

	private Conservacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
