package br.ufc.quixada.cti.gin.enumeration;

public enum Situacao {
	
	USO("Em uso"), 
	AUSENTE("Ausente"), 
	DEPOSITO("Em depósito"), 
	BAIXA("Baixo uso"), 
	MANUTENCAO("Em manutenção"),
	TERMO_RESPONSABILIDADE("Termo de responsabilidade");
	
	private String tipo;
	
	private Situacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
