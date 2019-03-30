package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")

@NamedQueries({
	
	@NamedQuery(name = "Usuario.buscarLogin",
			query = "select u.id from Usuario as u where u.login like :login"),
	@NamedQuery(name = "Usuario.buscarUsuario",
			query = "select u from Usuario as u where u.login like :login and u.senha like :senha")
	
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
	
	public String getLogin() {return login;}
	public String getSenha() {return senha;}
	public boolean isSuper_user() {return super_user;}
	public boolean isAdmin_user() {return admin_user;}
	public boolean isComum_user() {return comum_user;}

	public void setLogin(String login) {this.login = login;}
	public void setSenha(String senha) {this.senha = senha;}
	public void setSuper_user(boolean super_user) {this.super_user = super_user;}
	public void setAdmin_user(boolean admin_user) {this.admin_user = admin_user;}
	public void setComum_user(boolean comum_user) {this.comum_user = comum_user;}
	
}
