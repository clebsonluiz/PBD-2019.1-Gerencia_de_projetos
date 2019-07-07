package br.com.pbd2019_1.entidade;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COLABORADOR")
@NamedQueries({
	@NamedQuery(name = "Colaborador.pessoa",
			query = "select c from Colaborador c where c.pessoa = :pessoa and c.ativado = true ORDER BY c.id ASC"),
	@NamedQuery(name = "Colaborador.projeto",
			query = "select c from Colaborador c where c.projeto = :projeto and c.ativado = true and c.pessoa.ativado = true ORDER BY c.id ASC"),
	@NamedQuery(name = "Colaborador.gerenteEtapa",
			query = "select c from Colaborador c inner join GerenteEtapa g on(c.id = g.colaborador.id and c.ativado = true and g = :g ) "),
	@NamedQuery(name = "Colaborador.sub_etapa_colaborador",
			query = "select c from Colaborador c inner join SubEtapaColaborador sb on(c.id = sb.colaborador.id and c.ativado = true and sb = :sb )"),
	@NamedQuery(name = "Colaborador.tarefa_colaborador",
			query = "select c from Colaborador c inner join TarefaColaborador tc on(c.id = tc.colaborador.id and c.ativado = true and tc = :tc )"),

})
public class Colaborador extends Entidade{

	
	@Column(nullable = false)
	private LocalDateTime data_ingresso;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	public Colaborador() {}

	public Pessoa getPessoa() {return pessoa;}
	public Projeto getProjeto() {return projeto;}
	public LocalDateTime getData_ingresso() {return data_ingresso;}

	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setProjeto(Projeto projeto) {this.projeto = projeto;}
	public void setData_ingresso(LocalDateTime data_ingresso) {this.data_ingresso = data_ingresso;}
	
}
