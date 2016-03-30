package br.ufc.quixada.cti.gin.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufc.quixada.cti.gin.enumeration.TipoComentario;

@Entity
@Table(name="comentario")
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Patrimonio patrimonio;
	
	private String mensagem;
	private Date timestamp;
	
	@Enumerated(EnumType.STRING)
	private TipoComentario tipo_comentario;
	
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
	public TipoComentario getTipo_comentario() {
		return tipo_comentario;
	}
	public void setTipo_comentario(TipoComentario tipo_comentario) {
		this.tipo_comentario = tipo_comentario;
	}
	
}
