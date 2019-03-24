package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "gerente_projeto")
public class GerenteProjeto extends Entidade{

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@Transient
	private Projeto projeto;
	
	public GerenteProjeto() {}

	public Pessoa getPessoa() {return pessoa;}
	public Projeto getProjeto() {return projeto;}

	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setProjeto(Projeto projeto) {this.projeto = projeto;}
	
}
