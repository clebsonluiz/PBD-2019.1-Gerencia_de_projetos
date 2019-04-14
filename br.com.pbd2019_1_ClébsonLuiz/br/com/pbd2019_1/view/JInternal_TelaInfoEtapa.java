package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoEtapa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaEtapa_Tarefas telaEtapa_Tarefas;
	
	public JInternal_TelaInfoEtapa() {
		super("Info Etapa");
		setMinimumSize(new Dimension(320, 350));
		setPreferredSize(new Dimension(320, 350));
		
		telaEtapa_Tarefas = new TelaEtapa_Tarefas();

		add(telaEtapa_Tarefas, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaEtapa_Tarefas getTelaEtapa_Tarefas() {
		return telaEtapa_Tarefas;
	}

}
