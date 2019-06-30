package br.com.pbd2019_1.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ETAPA")
@NamedQueries({
	@NamedQuery(name = "Etapa.projeto",
	query = "select e from Etapa e where e.projeto = :projeto and e.ativado = true ORDER BY e.id ASC"),
	
	@NamedQuery(name = "Etapa.recalcula_porcentagem",
	query = " select e.porcentagem from Etapa e where e.id = :etapa_id and e.ativado = true ORDER BY e.id ASC"),
})

public class Etapa extends AbstractEtapa{

	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	@Transient
	private List<Tarefa> tarefas;
	
	public Etapa() {}

	public List<Tarefa> getTarefas() {return tarefas;}

	public void setProjeto(Projeto projeto) {this.projeto = projeto;}
	public void setTarefas(List<Tarefa> tarefas) {this.tarefas = tarefas;}
	
}
