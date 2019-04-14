package br.com.pbd2019_1.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TelaOpcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnInfo;
	private JButton btnPessoas;
	private JButton btnSQL;
	private JButton btnLog;
	private JButton btnSobre;

	public TelaOpcoes() {
		setLayout(new GridLayout(5, 1, 5, 5));
		setMinimumSize(new Dimension(200, 500));
		setPreferredSize(new Dimension(200, 500));
		btnInfo = new JButton("Info");
		btnPessoas = new JButton("Pessoas");
		btnSQL = new JButton("Query SQL");
		btnLog = new JButton("Log");
		btnSobre = new JButton("Sobre");
	}
	
	public void user_comum() {
		add(btnInfo);
		add(btnSobre);
	}
	
	public void user_admin() {
		add(btnInfo);
		add(btnPessoas);
		add(btnLog);
		add(btnSobre);
	}
	
	public void user_super() {
		add(btnInfo);
		add(btnPessoas);
		add(btnSQL);
		add(btnLog);
		add(btnSobre);
	}

	public JButton getBtnInfo() {
		return btnInfo;
	}

	public JButton getBtnPessoas() {
		return btnPessoas;
	}

	public JButton getBtnSQL() {
		return btnSQL;
	}

	public JButton getBtnLog() {
		return btnLog;
	}

	public JButton getBtnSobre() {
		return btnSobre;
	}
	
}
