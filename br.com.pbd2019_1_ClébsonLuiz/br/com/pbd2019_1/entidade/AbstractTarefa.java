package br.com.pbd2019_1.entidade;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractTarefa extends Entidade{

	@Column(nullable = false)
	private String nome;
	@Column
	private String descricao;
	@Column(nullable = false)
	private boolean concluida;
	@Column(nullable = false)
	private String prioridade;
	@Column(nullable = false)
	private LocalDateTime horario;
	
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public boolean isConcluida() {return concluida;}
	public String getPrioridade() {return prioridade;}
	public LocalDateTime getHorario() {return horario;}
	public void setNome(String nome) {this.nome = nome;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setConcluida(boolean concluida) {this.concluida = concluida;}
	public void setPrioridade(String prioridade) {this.prioridade = prioridade;}
	public void setHorario(LocalDateTime horario) {this.horario = horario;}
	
}
