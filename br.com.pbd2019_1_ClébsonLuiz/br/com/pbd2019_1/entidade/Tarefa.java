package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tarefa")
@NamedQuery(name = "Tarefa.etapa",
	query = "select t from Tarefa t where t.etapa = :etapa and t.ativado = true")
public class Tarefa extends Entidade{

	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private boolean concluida;
	@Column
	private String prioridade;
	@Column
	private String horario_tarefa;
	@ManyToOne
	@JoinColumn(name = "etapa_id")
	private Etapa etapa;
	
	public Tarefa() {}

	public Etapa getEtapa() {return etapa;}
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public boolean isConcluida() {return concluida;}
	public String getPrioridade() {return prioridade;}
	public String getHorario_tarefa() {return horario_tarefa;}

	public void setNome(String nome) {this.nome = nome;}
	public void setEtapa(Etapa etapa) {this.etapa = etapa;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setConcluida(boolean concluida) {this.concluida = concluida;}
	public void setPrioridade(String prioridade) {this.prioridade = prioridade;}
	public void setHorario_tarefa(String horario_tarefa) {this.horario_tarefa = horario_tarefa;}
	
}
