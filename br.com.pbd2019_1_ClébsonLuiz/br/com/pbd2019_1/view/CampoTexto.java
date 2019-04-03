package br.com.pbd2019_1.view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class CampoTexto extends JTextField implements FocusListener{

	private static final long serialVersionUID = 1L;
	private String campoDescricao;
	private String campoAtual;
	
	public CampoTexto() {
		addFocusListener(this);
	}

	public CampoTexto(String text) {
		super(text);
		addFocusListener(this);
	}
	
	/**
	 * @param descricao - Mensagem que será exibida
	 * quando o campo estiver vazio e sem foco.
	 */
	public void setDescricao(String descricao) {
		this.campoDescricao = descricao;
		setPadrao();
	}
	
	private void setPadrao() {
		this.campoAtual = "";
		this.setText(campoDescricao);
		this.setForeground(Color.lightGray);
	} 
	
	private void setAtual() {
		this.campoAtual = getText();
		this.setText("");
		this.setForeground(Color.black);
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		if(getText().trim().equals(""))
			setPadrao();
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(campoAtual == null || campoAtual.trim().equals(""))
			setAtual();
	}
	
	public String getTexto() {
		if(campoAtual.trim().equals(""))
			return "";
		else
			return getText();
	}
	
}
