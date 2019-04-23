package br.com.pbd2019_1.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class TelaOpcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private Botao btnInfo;
	private Botao btnPessoas;
	private Botao btnSQL;
	private Botao btnLog;
	private Botao btnSobre;
	private Botao btnSair;

	public TelaOpcoes() {
		setLayout(new GridLayout(6, 1, 5, 5));
		setMinimumSize(new Dimension(200, 500));
		setPreferredSize(new Dimension(200, 500));
		btnInfo = new Botao("Info");
		btnPessoas = new Botao("Pessoas");
		btnSQL = new Botao("Query SQL");
		btnLog = new Botao("Log");
		btnSobre = new Botao("Sobre");
		btnSair = new Botao("Sair");
	}
	
	public void user_comum() {
		add(btnInfo);
		add(btnSobre);
		add(btnSair);
	}
	
	public void user_admin() {
		add(btnInfo);
		add(btnPessoas);
		add(btnLog);
		add(btnSobre);
		add(btnSair);
	}
	
	public void user_super() {
		add(btnInfo);
		add(btnPessoas);
		add(btnSQL);
		add(btnLog);
		add(btnSobre);
		add(btnSair);
	}

	public Botao getBtnInfo() {
		return btnInfo;
	}

	public Botao getBtnPessoas() {
		return btnPessoas;
	}

	public Botao getBtnSQL() {
		return btnSQL;
	}

	public Botao getBtnLog() {
		return btnLog;
	}

	public Botao getBtnSobre() {
		return btnSobre;
	}

	public Botao getBtnSair() {
		return btnSair;
	}
	
}
