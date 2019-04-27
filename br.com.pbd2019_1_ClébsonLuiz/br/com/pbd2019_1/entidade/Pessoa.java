package br.com.pbd2019_1.entidade;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "pessoa")
@NamedQueries({
	@NamedQuery(name = "Pessoa.cpf",
			query = "select cast(COUNT(p) as int) from Pessoa p where p.cpf like :cpf and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarUsuarioLogin",
			query = "select cast(COUNT(p) as int) from Pessoa p where p.user_login like :login and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarUsuario",
			query = "select p from Pessoa as p where p.user_login like :login and p.user_senha like :senha and p.ativado = true")

})

public class Pessoa extends Entidade{
	
	public static final String COMUM_USER = "comum";
	public static final String ADMIN_USER = "admin";
	public static final String SUPER_USER = "super";
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false)
	private String sexo;
	@Column(nullable = false)
	private Date data_nascimento;
	@Column(nullable = false)
	private boolean disponibilidade;
	@Column(nullable = false, unique = true)
	private String user_login;
	@Column(nullable = false)
	private String user_senha;
	@Column(nullable = false)
	private String user_type;
	@Transient
	private Contato contato;
	@Transient
	private List<Projeto> projetos;
	@Transient
	private List<Colaborador> colaboradores;
	@Transient
	private List<CaracteristicaExtra> caracteristicas;
	
	
	public Pessoa() {}

	public String getCpf() {return cpf;}
	public String getNome() {return nome;}
	public String getSexo() {return sexo;}
	public Contato getContato() {return contato;}
	public Date getData_nascimento() {return data_nascimento;}
	public boolean isDisponibilidade() {return disponibilidade;}

	public void setCpf(String cpf) {this.cpf = cpf;}
	public void setNome(String nome) {this.nome = nome;}
	public void setSexo(String sexo) {this.sexo = sexo;}
	public void setContato(Contato contato) {this.contato = contato;}
	public void setProjetos(List<Projeto> projetos) {this.projetos = projetos;}
	public void setData_nascimento(Date data_nascimento) {this.data_nascimento = data_nascimento;}
	public void setDisponibilidade(boolean disponibilidade) {this.disponibilidade = disponibilidade;}
	public void setColaboradores(List<Colaborador> colaboradores) {this.colaboradores = colaboradores;}
	public void setMembro_equipes(List<Colaborador> membro_equipes) {this.colaboradores = membro_equipes;}
	public void setCaracteristicas(List<CaracteristicaExtra> caracteristicas) {this.caracteristicas = caracteristicas;}

	public List<Projeto> getProjetos() {return projetos;}
	public List<Colaborador> getColaboradores() {return colaboradores;}
	public List<Colaborador> getMembro_equipes() {return colaboradores;}
	public List<CaracteristicaExtra> getCaracteristicas() {return caracteristicas;}

	public String getUser_type() {return user_type;}
	public String getUser_login() {return user_login;}
	public String getUser_senha() {return user_senha;}

	public void setUser_type(String user_type) {this.user_type = user_type;}
	public void setUser_login(String user_login) {this.user_login = user_login;}
	public void setUser_senha(String user_senha) {this.user_senha = user_senha;}
	
}
