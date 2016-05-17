package br.ufc.quixada.cti.gin.enumeration;

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

	public String getTipo() {
		return tipo;
	}
	
}
