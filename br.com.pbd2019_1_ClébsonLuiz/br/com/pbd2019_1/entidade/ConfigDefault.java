package br.com.pbd2019_1.entidade;

import java.io.Serializable;

public class ConfigDefault implements Serializable{

	private static final long serialVersionUID = 1L;

	private String hora_bakup;
	private String imagemFundoDefault;
	
	public String getHora_bakup() {
		return hora_bakup;
	}
	public void setHora_bakup(String hora_bakup) {
		this.hora_bakup = hora_bakup;
	}
	public String getImagemFundoDefault() {
		return imagemFundoDefault;
	}
	public void setImagemFundoDefault(String imagemFundoDefault) {
		this.imagemFundoDefault = imagemFundoDefault;
	}
	@Override
	public String toString() {
		return "ConfigDefault [hora_bakup=" + hora_bakup + ", imagemFundoDefault=" + imagemFundoDefault + "]";
	}
	
}