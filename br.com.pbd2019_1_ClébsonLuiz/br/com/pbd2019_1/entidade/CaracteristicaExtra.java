package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")
@NamedQuery(name = "Caracteristica.pessoa", 
	query = "select c from CaracteristicaExtra as c where c.pessoa = :pessoa and c.ativado = true ORDER BY c.id ASC")
public class CaracteristicaExtra extends Entidade{

	
	@Column
	private String nome;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public CaracteristicaExtra() {}

	public String getNome() {return nome;}
	public Pessoa getPessoa() {return pessoa;}
	public void setNome(String nome) {this.nome = nome;}
	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}
	
}
