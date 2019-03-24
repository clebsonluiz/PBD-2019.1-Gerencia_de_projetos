package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")

@NamedQueries({
	
	@NamedQuery(name = "Usuario.buscarLogin",
			query = "select u.id from Usuario as u where u.login like :login"),
	@NamedQuery(name = "Usuario.buscarUsuario",
			query = "select u from Usuario as u where u.login like :login and u.senha like :senha"),
	@NamedQuery(name = "Usuario.buscarPessoa",
			query = "select u from Usuario as u where u.pessoa = :pessoa")
})

public class Usuario extends Entidade{

	@Column
	private String login;
	@Column
	private String senha;
	@Column
	private boolean super_user;
	@Column
	private boolean admin_user;
	@Column
	private boolean comum_user;
	
	@OneToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public String getLogin() {return login;}
	public String getSenha() {return senha;}
	public Pessoa getPessoa() {return pessoa;}

	public void setLogin(String login) {this.login = login;}
	public void setSenha(String senha) {this.senha = senha;}
	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}

}
