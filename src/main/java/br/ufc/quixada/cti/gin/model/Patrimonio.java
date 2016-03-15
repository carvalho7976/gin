package br.ufc.quixada.cti.gin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="patrimonio")
public class Patrimonio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer tombamento;
	private String marca;
	private String modelo;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Sala sala;
	
	@OneToMany(mappedBy="patrimonio", targetEntity=Comentario.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy="patrimonio", targetEntity=Registro.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Registro> registros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTombamento() {
		return tombamento;
	}

	public void setTombamento(Integer tombamento) {
		this.tombamento = tombamento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
}
