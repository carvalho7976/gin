package br.ufc.quixada.cti.gin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.cti.gin.enumeration.Conservacao;
import br.ufc.quixada.cti.gin.enumeration.Lotacao;
import br.ufc.quixada.cti.gin.enumeration.Situacao;

@Entity
@Table(name="patrimonio")
public class Patrimonio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer tombamento;
	private String descricao;
			
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@Enumerated(EnumType.STRING)
	private Lotacao list_de_lotacao;
	
	@Enumerated(EnumType.STRING)
	private Conservacao conservacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_incorporacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_chegada_campus;
	
	private Date data_registro_sist; 	
	
	@ManyToOne
	@JoinColumn(name="id_local")
	private Local local;
	
	@OneToMany(mappedBy="patrimonio", targetEntity=Comentario.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy="patrimonio", targetEntity=Historico.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Historico> registros;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	
	public Lotacao getList_de_lotacao() {
		return list_de_lotacao;
	}

	public void setList_de_lotacao(Lotacao list_de_lotacao) {
		this.list_de_lotacao = list_de_lotacao;
	}
	
	
	public Conservacao getConservacao() {
		return conservacao;
	}

	public void setConservacao(Conservacao conservacao) {
		this.conservacao = conservacao;
	}

	public Date getData_incorporacao() {
		return data_incorporacao;
	}

	public void setData_incorporacao(Date data_incorporacao) {
		this.data_incorporacao = data_incorporacao;
	}

	public Date getData_chegada_campus() {
		return data_chegada_campus;
	}

	public void setData_chegada_campus(Date data_chegada_campus) {
		this.data_chegada_campus = data_chegada_campus;
	}

	public Date getData_registro_sist() {
		return data_registro_sist;
	}

	public void setData_registro_sist(Date data_registro_sist) {
		this.data_registro_sist = data_registro_sist;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Historico> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Historico> registros) {
		this.registros = registros;
	}

	
}
