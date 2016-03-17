package br.ufc.quixada.cti.gin.enumeration;

public enum Lotacao {
	
	JA00("JA00"), ND("Nada");
	
	private String tipo;
	
	private Lotacao(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
