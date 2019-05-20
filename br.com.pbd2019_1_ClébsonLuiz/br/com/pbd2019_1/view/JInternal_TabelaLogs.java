package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TabelaLogs extends JInternalAbstract {
	
	private static final long serialVersionUID = 1L;

	private TelaLogs telaLogs;
	
	public JInternal_TabelaLogs() {
		super("LOG");
		setIconifiable(false);
		setMinimumSize(new Dimension(620, 400));
		setPreferredSize(new Dimension(620, 400));
		setSize(getPreferredSize());
		telaLogs = new TelaLogs();
		
		add(telaLogs, BorderLayout.CENTER);
	}

	public TelaLogs getTelaLogs() {
		return telaLogs;
	}

	@Override
	protected void fechar() {}
	
}