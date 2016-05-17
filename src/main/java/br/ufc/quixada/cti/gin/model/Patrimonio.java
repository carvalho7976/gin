package br.ufc.quixada.cti.gin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import br.ufc.quixada.cti.gin.enumeration.ConformeRelatorio;
import br.ufc.quixada.cti.gin.enumeration.Conservacao;
import br.ufc.quixada.cti.gin.enumeration.Lotacao;
import br.ufc.quixada.cti.gin.enumeration.Situacao;

@Entity
@Table(name="patrimonio", uniqueConstraints = @UniqueConstraint(columnNames = "tombamento"))
@NamedQueries({
	@NamedQuery(name = "Patrimonio.findPatrimonioComHistoricoById", query = "select p from Patrimonio p left join fetch p.historicos where p.id = :idPatrimonio")
})
public class Patrimonio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Campo obrigatório.")
	@Min(value = 0, message = "Número de tombamento deve ser maior ou igual a um.")
	private Integer tombamento;
	
	@NotEmpty(message = "Campo obrigatório.")
	@Size(max = 140, message = "Máximo de 140 caracteres.")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name="local_id")
	private Local local;
	
	@NotNull(message = "Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@NotNull(message = "Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Lotacao lotacao;
	
	@NotNull(message = "Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Conservacao conservacao;
	
	@NotNull(message = "Campo obrigatório.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date incorporacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date chegadaCampus;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "comentario_id")
	private Comentario comentario;
	
	@OneToMany(mappedBy="patrimonio", cascade=CascadeType.ALL)
	private List<Historico> historicos;
	
	@NotNull(message = "Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private ConformeRelatorio conformeRelatorio;
	

	public ConformeRelatorio getConformeRelatorio() {
		return conformeRelatorio;
	}

	public void setConformeRelatorio(ConformeRelatorio conformeRelatorio) {
		this.conformeRelatorio = conformeRelatorio;
	}

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

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public Conservacao getConservacao() {
		return conservacao;
	}

	public void setConservacao(Conservacao conservacao) {
		this.conservacao = conservacao;
	}

	public Date getIncorporacao() {
		return incorporacao;
	}

	public void setIncorporacao(Date incorporacao) {
		this.incorporacao = incorporacao;
	}

	public Date getChegadaCampus() {
		return chegadaCampus;
	}

	public void setChegadaCampus(Date chegadaCampus) {
		this.chegadaCampus = chegadaCampus;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public List<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

}
