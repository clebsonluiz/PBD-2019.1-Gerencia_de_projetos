package br.com.pbd2019_1.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "colaborador")
@NamedQueries({
	@NamedQuery(name = "Colaborador.pessoa",
			query = "select c from Colaborador c where c.pessoa = :pessoa and c.ativado = true ORDER BY c.id ASC"),
	@NamedQuery(name = "Colaborador.projeto",
			query = "select c from Colaborador c where c.projeto = :projeto and c.ativado = true and c.pessoa.ativado = true ORDER BY c.id ASC")
})
public class Colaborador extends Entidade{

	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_ingresso;
	@Column(nullable = false)
	private String privilegio;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	public Colaborador() {}

	public Pessoa getPessoa() {return pessoa;}
	public Projeto getProjeto() {return projeto;}
	public String getPrivilegio() {return privilegio;}
	public Date getData_ingresso() {return data_ingresso;}

	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setProjeto(Projeto projeto) {this.projeto = projeto;}
	public void setPrivilegio(String privilegio) {this.privilegio = privilegio;}
	public void setData_ingresso(Date data_ingresso) {this.data_ingresso = data_ingresso;}
	
}
