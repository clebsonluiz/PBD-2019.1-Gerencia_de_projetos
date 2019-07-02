package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GERENTE_ETAPA")
@NamedQueries({
	@NamedQuery(name = "Gerente.etapa",
			query = "select g from GerenteEtapa g where g.ativado = true and g.etapa = :etapa"),
	@NamedQuery(name = "Gerente.colaborador",
			query = "select g from GerenteEtapa g where g.ativado = true and g.colaborador = :colaborador"),
	@NamedQuery(name = "", 
			query = "")
})
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
