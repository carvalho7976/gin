package br.ufc.quixada.cti.gin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="local")
public class Local {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="local", targetEntity=Patrimonio.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Patrimonio> patrimonios;
	
	private String nome;
	private String pavimento;
	private String bloco;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Patrimonio> getPatrimonios() {
		return patrimonios;
	}
	public void setPatrimonios(List<Patrimonio> patrimonios) {
		this.patrimonios = patrimonios;
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
	
}
