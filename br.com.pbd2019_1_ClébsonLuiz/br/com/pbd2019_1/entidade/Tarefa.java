package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TAREFA")
@NamedQueries({
	@NamedQuery(name = "Tarefa.etapa",
			query = "select t from Tarefa t where t.etapa = :etapa and t.ativado = true ORDER BY t.id ASC"),
})

public class Tarefa extends AbstractTarefa{

	@ManyToOne
	@JoinColumn(name = "etapa_id")
	private Etapa etapa;
	
	public Tarefa() {}

	public Etapa getEtapa() {return etapa;}

	public void setEtapa(Etapa etapa) {this.etapa = etapa;}
	
}
