package br.com.pbd2019_1.entidade;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "log_update")
@NamedQuery(name = "LogUpdate.pessoa",
	query = "select l from LogUpdate l where l.pessoa = :pessoa")
public class LogUpdate extends Entidade{

	@Column
	private String alteracao;
	@Column
	private Date data_log;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public LogUpdate() {}

	public Pessoa getPessoa() {return pessoa;}
	public Date getData_log() {return data_log;}
	public String getAlteracao() {return alteracao;}

	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setData_log(Date data_log) {this.data_log = data_log;}
	public void setAlteracao(String alteracao) {this.alteracao = alteracao;}
	
}
