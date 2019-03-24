package br.com.pbd2019_1.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco extends Entidade{

	@Column
	private String casa;
	@Column
	private String rua;
	@Column
	private String bairro;
	@Column
	private String cidade;
	@Column
	private String estado;
	
	public Endereco() {}

	public String getRua() {return rua;}
	public String getCasa() {return casa;}
	public String getBairro() {return bairro;}
	public String getCidade() {return cidade;}
	public String getEstado() {return estado;}

	public void setRua(String rua) {this.rua = rua;}
	public void setCasa(String casa) {this.casa = casa;}
	public void setBairro(String bairro) {this.bairro = bairro;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	public void setEstado(String estado) {this.estado = estado;}
	
}
