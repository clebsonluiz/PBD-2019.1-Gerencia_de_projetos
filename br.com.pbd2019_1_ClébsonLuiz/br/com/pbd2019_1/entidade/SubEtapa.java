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
@Table(name = "SUB_ETAPA")
@NamedQueries({
	@NamedQuery(name = "SubEtapa.etapa", 
			query = "select s from SubEtapa s where s.ativado = true and s.etapa = :etapa ORDER BY s.id ASC"),
	@NamedQuery(name = "SubEtapa.colaborador", 
			query = "select s from SubEtapa s where s.ativado = true and s.subEtapaColaborador = :subEtapaColaborador ORDER BY s.id ASC"),
	@NamedQuery(name = "", query = "")
})
public class SubEtapa extends AbstractEtapa{

	@ManyToOne
	@JoinColumn(name = "etapa_id")
	private Etapa etapa;
	@Transient
	private List<SubTarefa> sub_tarefas;
	@Transient
	private SubEtapaColaborador subEtapaColaborador;
	
	public SubEtapa() {}
	
	public Etapa getEtapa() {return etapa;}
	public List<SubTarefa> getSub_tarefas() {return sub_tarefas;}

	public void setEtapa(Etapa etapa) {this.etapa = etapa;}
	public void setSub_tarefas(List<SubTarefa> sub_tarefas) {this.sub_tarefas = sub_tarefas;}

	public SubEtapaColaborador getSubEtapaColaborador() {return subEtapaColaborador;}
	public void setSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) {this.subEtapaColaborador = subEtapaColaborador;}
	
}
