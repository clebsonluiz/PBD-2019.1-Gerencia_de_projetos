package br.com.pbd2019_1.entidade;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "pessoa")
@NamedQueries({
	@NamedQuery(name = "Pessoa.cpf",
			query = "select COUNT(p) from Pessoa p where p.cpf like cpf"),
	@NamedQuery(name = "Pessoa.buscarUsuario",
			query = "select p from Pessoa as p where p.usuaro = :usuario")
	
})

public class Pessoa extends Entidade{

	@Column
	private String nome;
	@Column
	private String cpf;
	@Column
	private String sexo;
	@Column
	private Date data_nascimento;
	@Column
	private boolean disponibilidade;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
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
	public Usuario getUsuario() {return usuario;}
	public Date getData_nascimento() {return data_nascimento;}
	public boolean isDisponibilidade() {return disponibilidade;}

	public void setCpf(String cpf) {this.cpf = cpf;}
	public void setNome(String nome) {this.nome = nome;}
	public void setSexo(String sexo) {this.sexo = sexo;}
	public void setContato(Contato contato) {this.contato = contato;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}
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

}
