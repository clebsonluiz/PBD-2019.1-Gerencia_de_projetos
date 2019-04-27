package br.com.pbd2019_1.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "etapa")
@NamedQueries({
	@NamedQuery(name = "Etapa.projeto",
	query = "select e from Etapa e where e.projeto = :projeto and e.ativado = true"),
	
	@NamedQuery(name = "Etapa.recalcula_porcentagem",
	query = " select e.porcentagem_andamento from Etapa e where e.id = :etapa_id and e.ativado = true"),
})

public class Etapa extends Entidade{

	@Column(nullable = false)
	private String nome;
	@Column
	private String descricao;
	@Column(nullable = false)
	private float porcentagem_andamento;
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	@Transient
	private List<Tarefa> tarefas;
	
	public Etapa() {}

	public String getNome() {return nome;}
	public Projeto getProjeto() {return projeto;}
	public String getDescricao() {return descricao;}
	public List<Tarefa> getTarefas() {return tarefas;}
	public float getPorcentagem_andamento() {return porcentagem_andamento;}

	public void setNome(String nome) {this.nome = nome;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setPorcentagem_andamento(float porcentagem_andamento) {this.porcentagem_andamento = porcentagem_andamento;}
	public void setProjeto(Projeto projeto) {this.projeto = projeto;}
	public void setTarefas(List<Tarefa> tarefas) {this.tarefas = tarefas;}
	
}
