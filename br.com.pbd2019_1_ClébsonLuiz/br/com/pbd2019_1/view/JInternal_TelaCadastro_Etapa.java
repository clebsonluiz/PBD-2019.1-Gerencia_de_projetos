package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaCadastro_Etapa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaCadastro_Etapa telaCadastro_Etapa;
	
	public JInternal_TelaCadastro_Etapa() {
		super("Cadastro Etapa");
		setMaximizable(false);
		telaCadastro_Etapa = new TelaCadastro_Etapa();
		
		setMinimumSize(new Dimension(310, 280));
		setPreferredSize(new Dimension(310, 280));
		setSize(getPreferredSize());
		add(telaCadastro_Etapa, BorderLayout.CENTER);
		
	}

	@Override
	protected void fechar() {
		telaCadastro_Etapa.limparCampos();
	}

	public TelaCadastro_Etapa getTelaCadastro_Etapa() {
		return telaCadastro_Etapa;
	}
	
	public Botao getBotao()
	{
		return telaCadastro_Etapa.getBotao();
	}

}