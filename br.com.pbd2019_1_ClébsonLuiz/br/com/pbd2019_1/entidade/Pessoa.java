package br.com.pbd2019_1.entidade;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "pessoa")
public class Pessoa extends Entidade{

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	@Column
	private String nome;
	@Column
	private String cpf;
	@Column
	private String sexo;
	@Column
	private Date data_nascimento;
	@Transient
	private Contato contato;
	@Transient
	private List<LogUpdate> logs;
	@Transient
	private Usuario usuario;
	@Transient
	private List<GerenteProjeto> gerente_projetos;
	@Transient
	private List<MembroEquipe> membro_equipes;
	
	public Pessoa() {}

	public String getCpf() {return cpf;}
	public String getNome() {return nome;}
	public String getSexo() {return sexo;}
	public Contato getContato() {return contato;}
	public Usuario getUsuario() {return usuario;}
	public Endereco getEndereco() {return endereco;}
	public Date getData_nascimento() {return data_nascimento;}

	public void setCpf(String cpf) {this.cpf = cpf;}
	public void setNome(String nome) {this.nome = nome;}
	public void setSexo(String sexo) {this.sexo = sexo;}
	public void setLogs(List<LogUpdate> logs) {this.logs = logs;}
	public void setContato(Contato contato) {this.contato = contato;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}
	public void setData_nascimento(Date data_nascimento) {this.data_nascimento = data_nascimento;}
	public void setGerente_projetos(List<GerenteProjeto> gerente_projetos) {this.gerente_projetos = gerente_projetos;}
	public void setMembro_equipes(List<MembroEquipe> membro_equipes) {this.membro_equipes = membro_equipes;}

	public List<LogUpdate> getLogs() {return logs;}
	public List<GerenteProjeto> getGerente_projetos() {return gerente_projetos;}
	public List<MembroEquipe> getMembro_equipes() {return membro_equipes;}
	
}
