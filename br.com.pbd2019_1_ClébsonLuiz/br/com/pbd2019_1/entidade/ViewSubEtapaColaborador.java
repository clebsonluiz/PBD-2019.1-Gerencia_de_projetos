package br.com.pbd2019_1.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "sub_etapa_colaborador_view")
public class ViewSubEtapaColaborador extends View{
	
    private int cod_de_sub_etapa;
    private String nome;
    private String descricao;
    private float porcentagem;
    
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public float getPorcentagem() {return porcentagem;}
	public int getCod_de_sub_etapa() {return cod_de_sub_etapa;}

	public void setNome(String nome) {this.nome = nome;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setPorcentagem(float porcentagem) {this.porcentagem = porcentagem;}
	public void setCod_de_sub_etapa(int cod_de_sub_etapa) {this.cod_de_sub_etapa = cod_de_sub_etapa;}
    
}
