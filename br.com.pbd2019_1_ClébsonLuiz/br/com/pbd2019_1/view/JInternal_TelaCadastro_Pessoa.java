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
		telaCadastro_Pessoa.getBtCadastrarComoSuper().setVisible(false);
		telaCadastro_Pessoa.getBtCadastrarComoSuper().setEnabled(false);
		getContentPane().add(telaCadastro_Pessoa, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(520, 400));
		setPreferredSize(new Dimension(520, 400));
		setSize(new Dimension(520, 400));
	}

	@Override
	protected void fechar() {
		telaCadastro_Pessoa.limparCampos();
	}

	public TelaCadastro_Pessoa getTelaCadastro_Pessoa() {
		return telaCadastro_Pessoa;
	}

}
