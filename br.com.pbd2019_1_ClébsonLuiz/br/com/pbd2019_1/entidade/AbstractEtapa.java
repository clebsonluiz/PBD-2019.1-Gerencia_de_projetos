package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEtapa extends Entidade{

	@Column(nullable = false)
	private String nome;
	@Column
	private String descricao;
	@Column(nullable = false)
	private float porcentagem;
	
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public float getPorcentagem() {return porcentagem;}

	public void setNome(String nome) {this.nome = nome;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setPorcentagem(float porcentagem) {this.porcentagem = porcentagem;}
	
}
