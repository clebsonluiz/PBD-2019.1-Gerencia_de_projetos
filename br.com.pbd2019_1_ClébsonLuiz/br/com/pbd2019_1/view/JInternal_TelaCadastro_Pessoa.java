package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaCadastro_Pessoa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaCadastro_Pessoa telaCadastro_Pessoa;
	
	public JInternal_TelaCadastro_Pessoa() {
		super("Cadastro Pessoa");
		setMaximizable(false);
		
		telaCadastro_Pessoa = new TelaCadastro_Pessoa();
		add(telaCadastro_Pessoa, BorderLayout.CENTER);

		setMinimumSize(new Dimension(300, 340));
		setPreferredSize(new Dimension(310, 420));
		
	}

	@Override
	protected void fechar() {
		telaCadastro_Pessoa.limparCampos();
	}

	public TelaCadastro_Pessoa getTelaCadastro_Pessoa() {
		return telaCadastro_Pessoa;
	}

}
