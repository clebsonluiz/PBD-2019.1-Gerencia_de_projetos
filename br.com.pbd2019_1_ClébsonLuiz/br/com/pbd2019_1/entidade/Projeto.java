package br.com.pbd2019_1.entidade;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "projeto")
public class Projeto extends Entidade{

	@Column
	private String nome;
	@Column
	private String objetivo;
	@Column
	private String custo;
	@Column
	private Date data_inicio;
	@Column
	private Date data_fim;
	@Column
	private float porcentagem_andamento;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gerente_projeto_id")
	private GerenteProjeto gerenteProjeto;
	@Transient
	private Equipe equipe;
	@Transient
	private List<Etapa> etapas;
	
	public Projeto() {}
	
	public String getNome() {return nome;}
	public String getCusto() {return custo;}
	public String getObjetivo() {return objetivo;}

	public Date getData_fim() {return data_fim;}
	public Date getData_inicio() {return data_inicio;}
	
	public Equipe getEquipe() {return equipe;}
	public List<Etapa> getEtapas() {return etapas;}
	public GerenteProjeto getGerenteProjeto() {return gerenteProjeto;}
	public float getPorcentagem_andamento() {return porcentagem_andamento;}

	public void setNome(String nome) {this.nome = nome;}
	public void setCusto(String custo) {this.custo = custo;}
	public void setEquipe(Equipe equipe) {this.equipe = equipe;}
	public void setEtapas(List<Etapa> etapas) {this.etapas = etapas;}
	public void setData_fim(Date data_fim) {this.data_fim = data_fim;}
	public void setObjetivo(String objetivo) {this.objetivo = objetivo;}
	public void setData_inicio(Date data_inicio) {this.data_inicio = data_inicio;}
	public void setGerenteProjeto(GerenteProjeto gerenteProjeto) {this.gerenteProjeto = gerenteProjeto;}
	public void setPorcentagem_andamento(float porcentagem_andamento) {this.porcentagem_andamento = porcentagem_andamento;}
	
	
}
