package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TAREFA")
@NamedQueries({
	@NamedQuery(name = "Tarefa.etapa",
			query = "select t from Tarefa t where t.etapa = :etapa and t.ativado = true ORDER BY t.id ASC"),
	@NamedQuery(name = "Tarefa.colaborador", 
			query = "select t from Tarefa t inner join TarefaColaborador tc on (t.ativado = true and tc.ativado = true and tc.tarefa.id = t.id and tc = :tarefaColaborador ) ORDER BY t.id ASC "),

})

public class Tarefa extends AbstractTarefa{

	@ManyToOne
	@JoinColumn(name = "etapa_id")
	private Etapa etapa;
	@Transient
	private TarefaColaborador tarefaColaborador;
	
	public Tarefa() {}

	public Etapa getEtapa() {return etapa;}

	public void setEtapa(Etapa etapa) {this.etapa = etapa;}

	public TarefaColaborador getTarefaColaborador() {
		return tarefaColaborador;
	}

	public void setTarefaColaborador(TarefaColaborador tarefaColaborador) {
		this.tarefaColaborador = tarefaColaborador;
	}
	
}
