package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GERENTE_ETAPA")
public class GerenteEtapa extends Entidade{

	@ManyToOne
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;
	@OneToOne
	@JoinColumn(name = "etapa_id")
	private Etapa etapa;
	
	public GerenteEtapa() {}
	
	public Colaborador getColaborador() {return colaborador;}
	public Etapa getEtapa() {return etapa;}
	public void setColaborador(Colaborador colaborador) {this.colaborador = colaborador;}
	public void setEtapa(Etapa etapa) {this.etapa = etapa;}
	
}
