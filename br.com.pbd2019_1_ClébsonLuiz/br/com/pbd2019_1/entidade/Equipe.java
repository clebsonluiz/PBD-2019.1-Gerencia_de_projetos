package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipe")
public class Equipe extends Entidade{

	@Column
	private String nome;
	@OneToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	public Equipe() {}

	public String getNome() {return nome;}
	public Projeto getProjeto() {return projeto;}

	public void setNome(String nome) {this.nome = nome;}
	public void setProjeto(Projeto projeto) {this.projeto = projeto;}
	
}
