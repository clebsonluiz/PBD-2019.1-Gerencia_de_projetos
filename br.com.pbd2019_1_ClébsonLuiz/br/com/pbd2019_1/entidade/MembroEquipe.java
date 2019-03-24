package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membro_equipe")
public class MembroEquipe extends Entidade{

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;
	
	public MembroEquipe() {}

	public Pessoa getPessoa() {return pessoa;}
	public Equipe getEquipe() {return equipe;}

	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	public void setEquipe(Equipe equipe) {this.equipe = equipe;}
	
}
