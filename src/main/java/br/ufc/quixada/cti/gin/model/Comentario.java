package br.ufc.quixada.cti.gin.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="comentario")
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_patrimonio")
	private Patrimonio patrimonio;
	
	private String mensagem;
	private Date timestamp;
	private String tipo_comentario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Patrimonio getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getTipo_comentario() {
		return tipo_comentario;
	}
	public void setTipo_comentario(String tipo_comentario) {
		this.tipo_comentario = tipo_comentario;
	}
	
}
