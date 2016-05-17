package br.ufc.quixada.cti.gin.enumeration;

public enum Situacao {
	
	USO("Em uso"),
	DP("Devolvido ao patrimônio"),
	AUSENTE("Ausente"), 
	DEPOSITO("Em depósito"), 
	BAIXA("Baixo uso"), 
	MANUTENCAO("Pede manutenção"),
	SOB_RESPONS("Sob responsabilidade"),
	DEFEITO("Com defeito");
	
	private String tipo;
	
	private Situacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
