package br.com.pbd2019_1.entidade;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "backup")
@NamedQueries({
	@NamedQuery(name = "Backup.asc",
	query = "select b from Backup b where b.data_backup between ':data1' and ':data2' and b.ativado = true order by b.data_backup ASC"),
	@NamedQuery(name = "Backup.desc",
	query = "select b from Backup b where b.data_backup between ':data1' and ':data2' and b.ativado = true order by b.data_backup DESC"),
	@NamedQuery(name = "Backup.ascALL",
	query = "select b from Backup b  where b.ativado = true order by b.data_backup ASC"),
	@NamedQuery(name = "Backup.descALL",
	query = "select b from Backup b  where b.ativado = true order by b.data_backup DESC")
})
public class Backup extends Entidade{

	@Column(nullable = false)
	private Date data_backup;
	@Column(nullable = false)
	private String local_backup;
	@Column(nullable = false)
	private String autor_backup;
	@Column(nullable = false)
	private String status_backup;
	
	public Date getData_backup() {return data_backup;}
	public String getLocal_backup() {return local_backup;}
	public String getAutor_backup() {return autor_backup;}
	public String getStatus_backup() {return status_backup;}
	public void setData_backup(Date data_backup) {this.data_backup = data_backup;}
	public void setLocal_backup(String local_backup) {this.local_backup = local_backup;}
	public void setAutor_backup(String autor_backup) {this.autor_backup = autor_backup;}
	public void setStatus_backup(String status_backup) {this.status_backup = status_backup;}
	
}
