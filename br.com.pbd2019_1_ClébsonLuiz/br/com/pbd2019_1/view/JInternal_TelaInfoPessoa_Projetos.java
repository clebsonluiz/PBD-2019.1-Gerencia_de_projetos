package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

public class JInternal_TelaInfoPessoa_Projetos extends JInternalAbstract {
	
	private static final long serialVersionUID = 1L;

	private TelaInfoPessoaProjetos telaInfoPessoaProjetos;
	
	public JInternal_TelaInfoPessoa_Projetos() {
		super("Info Pessoa");

		setMinimumSize(new Dimension(340, 460));
		setPreferredSize(new Dimension(340, 460));
		setSize(new Dimension(340, 460));
		telaInfoPessoaProjetos = new TelaInfoPessoaProjetos();
		add(telaInfoPessoaProjetos, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoPessoaProjetos getTelaInfoPessoaProjetos() {
		return telaInfoPessoaProjetos;
	}
	
	public void queroFoco() throws PropertyVetoException {
		telaInfoPessoaProjetos.getTelaInfoPessoa().getTelaMiniPessoa2().getExibirSenhaChbx().setSelected(false);
//		telaInfoPessoaProjetos.getTelaInfoPessoa().getTelaPessoa().getExibirSenhaChbx().setSelected(false);
		super.queroFoco();
	}
}
