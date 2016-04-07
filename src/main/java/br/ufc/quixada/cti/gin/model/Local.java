package br.ufc.quixada.cti.gin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="local", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "pavimento", "bloco"}))
public class Local {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="local", targetEntity=Patrimonio.class, cascade = CascadeType.MERGE)
	@JsonBackReference
	private List<Patrimonio> patrimonios;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String nome;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String pavimento;
	
	@NotEmpty(message = "Campo obrigatório.")
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
	public String getFullLocal(){
		return this.nome +" / bloco: " + this.bloco + "/ pavimento: " + this.pavimento;
	}
	
}
