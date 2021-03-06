package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAREFA_COLABORADOR")
@NamedQueries({
	@NamedQuery(name = "TarefaColaborador.colaborador",
			query = "select tc from TarefaColaborador tc where tc.ativado = true and tc.colaborador = :colaborador"),
	@NamedQuery(name = "TarefaColaborador.tarefa",
			query = "select tc from TarefaColaborador tc where tc.ativado = true and tc.tarefa = :tarefa"),
})
public class TarefaColaborador extends Entidade{

	@ManyToOne
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;
	@OneToOne
	@JoinColumn(name = "tarefa_id")
	private Tarefa tarefa;
	
	public TarefaColaborador() {}
	
	public Colaborador getColaborador() {return colaborador;}
	public void setColaborador(Colaborador colaborador) {this.colaborador = colaborador;}
	public Tarefa getTarefa() {return tarefa;}
	public void setTarefa(Tarefa tarefa) {this.tarefa = tarefa;}

}
