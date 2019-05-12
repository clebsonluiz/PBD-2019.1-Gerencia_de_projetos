package br.com.pbd2019_1.entidade;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "projeto")
@NamedQuery(name = "Projeto.pessoa",
	query = "select p from Projeto as p where p.pessoa = :pessoa and p.ativado = true ORDER BY p.id ASC")
public class Projeto extends Entidade{

	@Column(nullable = false)
	private String nome;
	@Column
	private String descricao;
	@Column(nullable = false)
	private boolean privilegio;
	@Column(nullable = false)
	private Date data_inicio;
	@Column(nullable = false)
	private Date data_fim;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@Transient
	private List<Colaborador> colaboradores;
	@Transient
	private List<Etapa> etapas;
	
	public Projeto() {}
	
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}

	public Date getData_fim() {return data_fim;}
	public Date getData_inicio() {return data_inicio;}
	
	public List<Etapa> getEtapas() {return etapas;}
	public List<Colaborador> getColaboradores() {return colaboradores;}
	public Pessoa getPessoa() {return pessoa;}

	public boolean isPrivilegio() {return privilegio;}
	public void setNome(String nome) {this.nome = nome;}
	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setEtapas(List<Etapa> etapas) {this.etapas = etapas;}
	public void setData_fim(Date data_fim) {this.data_fim = data_fim;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setPrivilegio(boolean privilegio) {this.privilegio = privilegio;}
	public void setData_inicio(Date data_inicio) {this.data_inicio = data_inicio;}
	public void setColaboradores(List<Colaborador> colaboradores) {this.colaboradores = colaboradores;}
	
}
