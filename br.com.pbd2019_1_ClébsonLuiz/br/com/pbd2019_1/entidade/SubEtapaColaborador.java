package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_ETAPA_COLABORADOR")
public class SubEtapaColaborador extends Entidade{

	@ManyToOne
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;
	@OneToOne
	@JoinColumn(name = "sub_etapa_id")
	private SubEtapa sub_etapa;
	
	public SubEtapaColaborador() {}
	
	public Colaborador getColaborador() {return colaborador;}
	public SubEtapa getSub_etapa() {return sub_etapa;}
	public void setColaborador(Colaborador colaborador) {this.colaborador = colaborador;}
	public void setSub_etapa(SubEtapa sub_etapa) {this.sub_etapa = sub_etapa;}
	
}
