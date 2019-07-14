package br.com.pbd2019_1.entidade;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class View {

	@Id
	private int id;
	@Column
    private LocalDateTime data_ingresso;
	@Column
    private int cod_de_pessoa;
	@Column
    private String cpf_pessoa;
	@Column
    private boolean ativado;
    
	public int getId() {return id;}
	public boolean isAtivado() {return ativado;}
	public String getCpf_pessoa() {return cpf_pessoa;}
	public int getCod_de_pessoa() {return cod_de_pessoa;}
	public LocalDateTime getData_ingresso() {return data_ingresso;}

	public void setId(int id) {this.id = id;}
	public void setAtivado(boolean ativado) {this.ativado = ativado;}
	public void setCpf_pessoa(String cpf_pessoa) {this.cpf_pessoa = cpf_pessoa;}
	public void setCod_de_pessoa(int cod_de_pessoa) {this.cod_de_pessoa = cod_de_pessoa;}
	public void setData_ingresso(LocalDateTime data_ingresso) {this.data_ingresso = data_ingresso;}
    
}
