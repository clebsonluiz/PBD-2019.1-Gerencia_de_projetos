package br.com.pbd2019_1.entidade;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contato")

@NamedQueries({
	@NamedQuery(name = "Contato.cliente",
			query = "select c from Contato c where c.cliente = :cliente"),
	@NamedQuery(name = "Contato.fornecedor",
			query = "select c from Contato c where c.fornecedor = :fornecedor"),
	@NamedQuery(name = "Contato.funcionario",
			query = "select c from Contato c where c.funcionario = :funcoinario")
})
public class Contato extends Entidade{
	
	@Column
	private String telefone ;
	@Column
	private String celular;
	@Column
	private String email;
	@OneToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public Contato() {}

	public Pessoa getPessoa() {return pessoa;}

	public String getEmail() {return email;}
	public String getCelular() {return celular;}
	public String getTelefone() {return telefone;}

	public void setEmail(String email) {this.email = email;}
	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setCelular(String celular) {this.celular = celular;}
	public void setTelefone(String telefone) {this.telefone = telefone;}

}
