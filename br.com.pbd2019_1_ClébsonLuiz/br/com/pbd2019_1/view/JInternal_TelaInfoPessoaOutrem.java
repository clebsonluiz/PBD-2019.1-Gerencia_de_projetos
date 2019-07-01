package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoPessoaOutrem extends JInternalAbstract {

	
	private static final long serialVersionUID = 1L;

	private TelaInfoPessoaContatoOutrem telaInfoPessoaContatoOutrem;
	
	public JInternal_TelaInfoPessoaOutrem() {
		super("TelaInfoPessoaOutrem");
		setResizable(false);
		setIconifiable(false);
		setMinimumSize(new Dimension(320, 420));
		setPreferredSize(new Dimension(320, 420));
		setSize(getPreferredSize());
		
		telaInfoPessoaContatoOutrem = new TelaInfoPessoaContatoOutrem();
		
		add(telaInfoPessoaContatoOutrem, BorderLayout.CENTER);
		
	}

	public TelaInfoPessoaContatoOutrem getTelaInfoPessoaContatoOutrem() {
		return telaInfoPessoaContatoOutrem;
	}


	@Override
	protected void fechar() {}

}
