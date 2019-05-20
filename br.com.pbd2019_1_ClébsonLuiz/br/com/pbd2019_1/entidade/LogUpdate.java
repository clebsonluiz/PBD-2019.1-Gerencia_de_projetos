package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "log_update")

@NamedQueries({
	@NamedQuery(name = "LogUpdate",
			query = "select l from LogUpdate l ORDER BY l.id ASC"),
	@NamedQuery(name = "LogUpdate.date", 
			query = "select l from LogUpdate l where l.data_log between :data1 and :data2 and l.ativado = true ORDER BY l.id ASC"),
	@NamedQuery(name = "LogUpdate.dateEspecificado", 
			query = "select l from LogUpdate l where "
					+ "lower(l.tipo) like lower(:tipo) and "
					+ "lower(l.tabela) like lower(:tabela) and "
					+ "lower(cast(l.id_tabela as text)) like :id_tabela and "
					+ "lower(l.responsavel) like lower(:responsavel) and "
					+ "l.data_log between :data1 and :data2 and l.ativado = true ORDER BY l.id ASC"),
	@NamedQuery(name = "LogUpdate.dateMiniEspecificado", 
			query = "select l from LogUpdate l where "
					+ "lower(l.tipo) like lower(:tipo) and "
					+ "lower(l.tabela) like lower(:tabela) and "
					+ "lower(cast(l.id_tabela as text)) like :id_tabela and "
					+ "lower(l.responsavel) like lower(:responsavel) and "
					+ "l.ativado = true ORDER BY l.id ASC")
})

public class LogUpdate extends Entidade{

	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private String tabela;
	@Column(nullable = false)
	private int id_tabela;
	@ElementCollection
	@JoinTable
	@OrderColumn(nullable = true)
	private String[] antes;
	@ElementCollection
	@JoinTable
	@OrderColumn(nullable = false)
	private String[] depois;
	@Column(nullable = false)
	private String responsavel;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date data_log;
	
	public LogUpdate() {}

	public String getTipo() {return tipo;}
	public String getTabela() {return tabela;}
	public int getId_tabela() {return id_tabela;}
	public String[] getAntes() {return antes;}
	public String[] getDepois() {return depois;}
	public String getResponsavel() {return responsavel;}
	public java.util.Date getData_log() {return data_log;}

	public void setTipo(String tipo) {this.tipo = tipo;}
	public void setTabela(String tabela) {this.tabela = tabela;}
	public void setId_tabela(int id_tabela) {this.id_tabela = id_tabela;}
	public void setAntes(String[] antes) {this.antes = antes;}
	public void setDepois(String[] depois) {this.depois = depois;}
	public void setResponsavel(String responsavel) {this.responsavel = responsavel;}
	public void setData_log(java.util.Date data_log) {this.data_log = data_log;}
	
}
