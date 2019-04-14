package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoProjeto_Etapas extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaProjeto_Etapas telaProjeto_Etapas;
	
	public JInternal_TelaInfoProjeto_Etapas() {
		super("Projeto");
		setMinimumSize(new Dimension(520, 380));
		setPreferredSize(new Dimension(520, 380));
		telaProjeto_Etapas = new TelaProjeto_Etapas();
		getContentPane().add(telaProjeto_Etapas, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaProjeto_Etapas getTelaProjeto_Etapas() {
		return telaProjeto_Etapas;
	}

}
