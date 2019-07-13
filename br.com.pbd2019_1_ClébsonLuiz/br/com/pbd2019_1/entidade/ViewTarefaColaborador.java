package br.com.pbd2019_1.entidade;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "tarefa_colaborador_view")
public class ViewTarefaColaborador extends View
{
    private int cod_de_tarefa;
    private String nome;
    private String descricao;
    private boolean concluida;
    private LocalDateTime horario;
    private String prioridade;
    
	public int getCod_de_tarefa() {return cod_de_tarefa;}
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public boolean isConcluida() {return concluida;}
	public String getPrioridade() {return prioridade;}
	public LocalDateTime getHorario() {return horario;}

	public void setNome(String nome) {this.nome = nome;}
	public void setHorario(LocalDateTime horario) {this.horario = horario;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setConcluida(boolean concluida) {this.concluida = concluida;}
	public void setPrioridade(String prioridade) {this.prioridade = prioridade;}
	public void setCod_de_tarefa(int cod_de_tarefa) {this.cod_de_tarefa = cod_de_tarefa;}
    
}
