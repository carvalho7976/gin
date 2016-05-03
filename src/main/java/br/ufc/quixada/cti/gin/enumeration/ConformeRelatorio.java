package br.ufc.quixada.cti.gin.enumeration;

//checar os valores de B e NE
public enum ConformeRelatorio {
	NCR("Não Consta no Relatório"), 
	C("Consta no Relatório"), 
	B("Baixa"),
	NE("Não Existente");
	
	private String tipo; 
	
	private ConformeRelatorio(String tipo){
		this.tipo = tipo; 
	}

	public String getTipo() {
		return tipo;
	}
	
}
