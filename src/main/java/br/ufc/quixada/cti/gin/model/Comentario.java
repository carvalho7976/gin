package br.ufc.quixada.cti.gin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufc.quixada.cti.gin.enumeration.TipoComentario;

@Entity
@Table(name="comentario")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 500)
	private String mensagem;
	
	@Enumerated(EnumType.STRING)
	private TipoComentario tipoComentario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TipoComentario getTipoComentario() {
		return tipoComentario;
	}

	public void setTipoComentario(TipoComentario tipoComentario) {
		this.tipoComentario = tipoComentario;
	}
	
}
