package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

public class JInternal_TelaInfoPessoa extends JInternalAbstract {
	
	private static final long serialVersionUID = 1L;

	private TelaInfoPessoa telaInfoPessoa;
	
	public JInternal_TelaInfoPessoa() {
		super("Info Pessoa");
		setMinimumSize(new Dimension(340, 460));
		setPreferredSize(new Dimension(340, 460));
		setSize(new Dimension(340, 460));
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

	public void queroFoco() throws PropertyVetoException {
		telaInfoPessoa.getTelaMiniPessoa2().getExibirSenhaChbx().setSelected(false);
//		telaInfoPessoa.getTelaPessoa().getExibirSenhaChbx().setSelected(false);
		super.queroFoco();
	}
}
