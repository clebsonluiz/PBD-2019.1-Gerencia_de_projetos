package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoProjeto_Etapas_Simples extends JInternalAbstract{

	private static final long serialVersionUID = 1L;
	private TelaProjeto_Etapas_Simples telaProjeto_Etapas_Simples;
	
	public JInternal_TelaInfoProjeto_Etapas_Simples() {
		super("Projeto");
		setMinimumSize(new Dimension(510, 430));
		setPreferredSize(new Dimension(510, 430));
		setSize(getPreferredSize());
		telaProjeto_Etapas_Simples = new TelaProjeto_Etapas_Simples();
		add(telaProjeto_Etapas_Simples, BorderLayout.CENTER);
	}

	public TelaProjeto_Etapas_Simples getTelaProjeto_Etapas_Simples() {
		return telaProjeto_Etapas_Simples;
	}

	@Override
	protected void fechar() {}

}
