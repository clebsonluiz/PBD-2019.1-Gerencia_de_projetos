package br.com.pbd2019_1.entidade;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "log_update")

@NamedQueries({
	@NamedQuery(name = "LogUpdate",
			query = "select l from LogUpdate l"),
	@NamedQuery(name = "LogUpdate.date", 
			query = "select l from LogUpdate l where l.data_log between ':data1' and ':data2'")
})

public class LogUpdate extends Entidade{

	@Column
	private String antes_alteracao;
	@Column
	private String depois_alteracao;
	@Column
	private String responsavel_alteracao;
	@Column
	private Date data_log;
	
	public LogUpdate() {}

	public Date getData_log() {return data_log;}
	public String getAntes_alteracao() {return antes_alteracao;}
	public String getDepois_alteracao() {return depois_alteracao;}
	public String getResponsavel_alteracao() {return responsavel_alteracao;}

	public void setData_log(Date data_log) {this.data_log = data_log;}
	public void setAntes_alteracao(String antes_alteracao) {this.antes_alteracao = antes_alteracao;}
	public void setDepois_alteracao(String depois_alteracao) {this.depois_alteracao = depois_alteracao;}
	public void setResponsavel_alteracao(String responsavel_alteracao) {this.responsavel_alteracao = responsavel_alteracao;}
	
}
