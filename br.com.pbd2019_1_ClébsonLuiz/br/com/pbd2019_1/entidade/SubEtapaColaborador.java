package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_ETAPA_COLABORADOR")
@NamedQueries({
	@NamedQuery(name = "SubEtapaColaborador.colaborador",
			query = "select s from SubEtapaColaborador s where s.ativado = true and s.colaborador = :colaborador ORDER BY s.id ASC"),
	@NamedQuery(name = "SubEtapaColaborador.sub_etapa",
			query = "select s from SubEtapaColaborador s where s.ativado = true and s.sub_etapa = :sub_etapa ORDER BY s.id ASC"),
	@NamedQuery(name = "", query = "")
})
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
