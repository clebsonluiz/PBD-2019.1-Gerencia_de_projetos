package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoSubEtapa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInfoSubEtapaSubTarefas telaInfoSubEtapaSubTarefas;
	
	public JInternal_TelaInfoSubEtapa() {
		super("SUB ETAPA");
		setMinimumSize(new Dimension(310, 400));
		setPreferredSize(new Dimension(320, 400));
		setSize(getPreferredSize());
		telaInfoSubEtapaSubTarefas = new TelaInfoSubEtapaSubTarefas();
		getContentPane().add(telaInfoSubEtapaSubTarefas, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoSubEtapaSubTarefas getTelaInfoSubEtapaSubTarefas() {
		return telaInfoSubEtapaSubTarefas;
	}

}
