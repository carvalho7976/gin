package br.ufc.quixada.cti.gin.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/** representa o log do sistema, alterações feita em algum patrimonio.
 * é salvo quando alguma alteração em patrimonio é feita * 
 * **/


@Entity
@Table(name="historico")
public class Historico {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//data do ocorrido
	private Date timestamp;
	
	/*representa qualquer alteracao que é feita. 
	 * É registrado no momento em que atualização ou criação de um item	é feita * 
	 * Registra a alteração, EX: Local alterado de <X> para Y;
	 */	
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name="id_patrimonio")
	private Patrimonio patrimonio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}

}
