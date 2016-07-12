package br.ufc.quixada.cti.gin.enumeration;

public enum Estado {

	AC("Acre"), AL("Alagoas"), AP("Amapa"), AM("Amazonas"), BA("Bahia"), 
	CE("Ceará"), DF("Distrito Federal"), ES("Espirito Santo"), GO("Goiás"), MA("Maranhão"), 
	MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas Gerais"), PA("Pará"), PB("Paraíba"), 
	PR("Paraná"), PE("Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), 
	RS("Rio Grande do Sul"), RO("Rondonia"), RR("Roraima"), SC("Santa Catarina"), SP("São Paulo"), 
	SE("Sergipe"), TO("Tocantins");
	
	private String tipo;
	
	private Estado(String tipo) {
		this.tipo = tipo;
	}
	
	
	public String getTipo(){
		return tipo;
	}
}
