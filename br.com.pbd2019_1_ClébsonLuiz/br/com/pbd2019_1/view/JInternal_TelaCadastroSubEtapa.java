package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class JInternal_TelaCadastroSubEtapa extends JInternal_TelaCadastro_Etapa {

	private static final long serialVersionUID = 1L;

	
	public JInternal_TelaCadastroSubEtapa()
	{
		this.setTitle("Cadastro de Sub Etapa");
		setMinimumSize(new Dimension(310, 280));
		setPreferredSize(new Dimension(310, 280));
		setSize(getPreferredSize());
	}
	
}
