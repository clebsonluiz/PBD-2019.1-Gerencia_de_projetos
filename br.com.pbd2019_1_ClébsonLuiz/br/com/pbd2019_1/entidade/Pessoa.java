package br.com.pbd2019_1.entidade;

import java.time.LocalDate;
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
	@NamedQuery(name = "Pessoa.loginSenhaId",
			query = "select cast(COUNT(p) as int) from Pessoa p where p.cpf like :cpf and p.user_senha like :senha and p.id = :id and p.ativado = true"),
	@NamedQuery(name = "Pessoa.cpfID",
			query = "select cast(COUNT(p) as int) from Pessoa p where p.cpf like :cpf and p.id <> :id and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarUsuarioLogin",
			query = "select cast(COUNT(p) as int) from Pessoa p where p.cpf like :cpf and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarUsuarioLoginID",
			query = "select cast(COUNT(p) as int) from Pessoa p where p.cpf like :cpf and p.id <> :id and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarUsuario",
			query = "select p from Pessoa as p where p.cpf like :cpf and p.user_senha like :senha and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarPessoas",
			query = "select p from Pessoa as p where p.id <> :id and p.ativado = true"),
	@NamedQuery(name = "Pessoa.buscarPessoasEspecificar",
			query = "select p from Pessoa as p where p.id <> :id and p.ativado = true "
					+ "and lower(p.nome) like lower(:nome) and lower(p.cpf) like lower(:cpf) "
					+ "and lower(cast(p.disponibilidade as text)) like lower(:disponibilidade)")

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
	private LocalDate data_nascimento;
	@Column(nullable = false)
	private boolean disponibilidade;
	@Column(nullable = false)
	private String user_senha;
	@Column(nullable = false)
	private boolean reset_senha;
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
	public boolean isReset_senha() {return reset_senha;}
	public LocalDate getData_nascimento() {return data_nascimento;}
	public boolean isDisponibilidade() {return disponibilidade;}

	public void setCpf(String cpf) {this.cpf = cpf;}
	public void setNome(String nome) {this.nome = nome;}
	public void setSexo(String sexo) {this.sexo = sexo;}
	public void setContato(Contato contato) {this.contato = contato;}
	public void setProjetos(List<Projeto> projetos) {this.projetos = projetos;}
	public void setReset_senha(boolean reset_senha) {this.reset_senha = reset_senha;}
	public void setData_nascimento(LocalDate data_nascimento) {this.data_nascimento = data_nascimento;}
	public void setDisponibilidade(boolean disponibilidade) {this.disponibilidade = disponibilidade;}
	public void setColaboradores(List<Colaborador> colaboradores) {this.colaboradores = colaboradores;}
	public void setMembro_equipes(List<Colaborador> membro_equipes) {this.colaboradores = membro_equipes;}
	public void setCaracteristicas(List<CaracteristicaExtra> caracteristicas) {this.caracteristicas = caracteristicas;}

	public List<Projeto> getProjetos() {return projetos;}
	public List<Colaborador> getColaboradores() {return colaboradores;}
	public List<Colaborador> getMembro_equipes() {return colaboradores;}
	public List<CaracteristicaExtra> getCaracteristicas() {return caracteristicas;}

	public String getUser_type() {return user_type;}
	public String getUser_senha() {return user_senha;}

	public void setUser_type(String user_type) {this.user_type = user_type;}
	public void setUser_senha(String user_senha) {this.user_senha = user_senha;}

}
