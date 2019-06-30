package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_TAREFA")
public class SubTarefa extends AbstractTarefa{

	@ManyToOne
	@JoinColumn(name = "sub_etapa_id")
	private SubEtapa sub_etapa;

	public SubTarefa() {}
	
	public SubEtapa getSub_etapa() {return sub_etapa;}

	public void setSub_etapa(SubEtapa sub_etapa) {this.sub_etapa = sub_etapa;}
	
}
