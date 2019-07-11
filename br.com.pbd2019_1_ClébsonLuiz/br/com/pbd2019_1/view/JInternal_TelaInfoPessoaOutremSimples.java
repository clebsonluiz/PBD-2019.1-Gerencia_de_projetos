package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoPessoaOutremSimples extends JInternalAbstract {

	
	private static final long serialVersionUID = 1L;

	private TelaInfoPessoaContatoOutremSimples telaInfoPessoaContatoOutremSimples;
	
	public JInternal_TelaInfoPessoaOutremSimples() {
		super("TelaInfoPessoaOutrem");
		setResizable(false);
		setIconifiable(false);
		setMinimumSize(new Dimension(340, 460));
		setPreferredSize(new Dimension(340, 460));
		setSize(getPreferredSize());
		
		telaInfoPessoaContatoOutremSimples = new TelaInfoPessoaContatoOutremSimples();
		
		add(telaInfoPessoaContatoOutremSimples, BorderLayout.CENTER);
		
	}

	public TelaInfoPessoaContatoOutremSimples getTelaInfoPessoaContatoOutremSimples() {
		return telaInfoPessoaContatoOutremSimples;
	}


	@Override
	protected void fechar() {}

}
