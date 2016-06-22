package br.ufc.quixada.cti.gin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="local")
public class Local {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String localizacao;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String pavimento;
	
	private String bloco;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getPavimento() {
		return pavimento;
	}
	
	public void setPavimento(String pavimento) {
		this.pavimento = pavimento;
	}
	
	public String getBloco() {
		return bloco;
	}
	
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	
	public String getFullLocal(){
		String fullLocal = this.localizacao;
		fullLocal += (this.pavimento.equals("0")) ? " - Pavimento: Térreo " : " - Pavimento: " + this.pavimento;
		fullLocal += (this.bloco != null && !this.bloco.isEmpty()) ? " - Bloco:" + this.bloco : ".";
		
		return fullLocal;
	}
	
}
