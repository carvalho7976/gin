package br.ufc.quixada.cti.gin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="local", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "pavimento", "bloco"}))
public class Local {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String nome;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String pavimento;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String bloco;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
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
		return this.nome +" / bloco: " + this.bloco + "/ pavimento: " + this.pavimento;
	}
	
}
