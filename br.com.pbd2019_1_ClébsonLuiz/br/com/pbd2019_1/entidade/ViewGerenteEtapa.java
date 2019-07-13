package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "gerente_etapa_view")
public class ViewGerenteEtapa extends View{
	
    private int cod_de_etapa;
    private String nome;
    private String descricao;
    private float porcentagem;
    
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public float getPorcentagem() {return porcentagem;}
	public int getCod_de_etapa() {return cod_de_etapa;}

	public void setNome(String nome) {this.nome = nome;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setPorcentagem(float porcentagem) {this.porcentagem = porcentagem;}
	public void setCod_de_etapa(int cod_de_etapa) {this.cod_de_etapa = cod_de_etapa;}
    
}

