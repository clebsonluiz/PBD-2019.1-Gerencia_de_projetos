package br.com.pbd2019_1.entidade;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

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
	private LocalDateTime data_log;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private String tabela;
	@Column(nullable = false)
	private int id_tabela;
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable
	@OrderColumn(nullable = true)
	private List<String> antes;
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable
	@OrderColumn(nullable = false)
	private List<String> depois;
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable
	@OrderColumn(nullable = false)
	private List<String> coluna;
	@Column(nullable = false)
	private String responsavel;
	
	public LogUpdate() {}

	public String getTipo() {return tipo;}
	public String getTabela() {return tabela;}
	public int getId_tabela() {return id_tabela;}
	public List<String> getAntes() {return antes;}
	public List<String> getDepois() {return depois;}
	public List<String> getColuna() {return coluna;}
	public String getResponsavel() {return responsavel;}
	public LocalDateTime getData_log() {return data_log;}

	public void setTipo(String tipo) {this.tipo = tipo;}
	public void setTabela(String tabela) {this.tabela = tabela;}
	public void setId_tabela(int id_tabela) {this.id_tabela = id_tabela;}
	public void setAntes(List<String> antes) {this.antes = antes;}
	public void setDepois(List<String> depois) {this.depois = depois;}
	public void setColuna(List<String> coluna) {this.coluna = coluna;}
	public void setResponsavel(String responsavel) {this.responsavel = responsavel;}
	public void setData_log(LocalDateTime data_log) {this.data_log = data_log;}

	@Override
	public String toString() {
		
		String antesS = "";
		String depoisS = "";
		String colunasS = "";
		
		System.out.println(antes);
		
		for(String s: antes)
			antesS += "|Str: " + s + "|";
		for(String s: depois)
			depoisS += "|Str: " + s + "|";
		for(String s: coluna)
			colunasS += "|Str: " + s + "|";
		
		
		return  "LogUpdate [data_log=" + data_log + ", tipo=" + tipo + ", tabela=" + tabela + ", id_tabela=" + id_tabela
				+ ", antes=" + antesS + ", depois=" + depoisS + ", coluna=" + colunasS + ", responsavel=" + responsavel
				+ "]";
	}
	
}
