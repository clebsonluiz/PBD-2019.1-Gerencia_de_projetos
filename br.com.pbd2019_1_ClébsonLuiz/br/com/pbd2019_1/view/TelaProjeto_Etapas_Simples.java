package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaProjeto_Etapas_Simples extends TelaProjeto_Etapas{

	private static final long serialVersionUID = 1L;

	public TelaProjeto_Etapas_Simples() {
		setMinimumSize(new Dimension(510, 430));
		setPreferredSize(new Dimension(510, 430));
		getjTabbedPane().removeTabAt(1);
	}
	
}
