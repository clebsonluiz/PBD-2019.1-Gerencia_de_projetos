package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoPessoa extends JInternalAbstract {
	
	private static final long serialVersionUID = 1L;

	private TelaInfoPessoa telaInfoPessoa;
	
	public JInternal_TelaInfoPessoa() {
		super("Info Pessoa");
		setMinimumSize(new Dimension(350, 450));
		setPreferredSize(new Dimension(350, 450));
		setSize(getPreferredSize());
		telaInfoPessoa = new TelaInfoPessoa();
		
		telaInfoPessoa.getTelaContatoCaracteristica().getBtAdicionar().setVisible(false);
		telaInfoPessoa.getTelaContatoCaracteristica().getBtAdicionar().setEnabled(false);
		add(telaInfoPessoa, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoPessoa getTelaInfoPessoa() {
		return telaInfoPessoa;
	}

}
