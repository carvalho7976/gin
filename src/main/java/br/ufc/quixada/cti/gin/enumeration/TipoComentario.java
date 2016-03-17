package br.ufc.quixada.cti.gin.enumeration;

public enum TipoComentario {
	
	INFORMACAO("Informação"), ALERTA("Alerta"), FALHA("Falha");
	
	private String tipo;
	
	private TipoComentario(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
