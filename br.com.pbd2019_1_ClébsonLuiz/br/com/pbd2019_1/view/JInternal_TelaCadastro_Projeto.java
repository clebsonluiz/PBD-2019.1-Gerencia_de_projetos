package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaCadastro_Projeto extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaCadastro_Projeto telaCadastro_Projeto;
	
	public JInternal_TelaCadastro_Projeto() {
		super("Cadastro Projeto");
		setResizable(false);
		telaCadastro_Projeto = new TelaCadastro_Projeto();
		add(telaCadastro_Projeto, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(520, 230));
		setPreferredSize(new Dimension(520, 230));
	}

	@Override
	protected void fechar() {
		telaCadastro_Projeto.limparCampos();
	}

	public TelaCadastro_Projeto getTelaCadastro_Projeto() {
		return telaCadastro_Projeto;
	}

}
