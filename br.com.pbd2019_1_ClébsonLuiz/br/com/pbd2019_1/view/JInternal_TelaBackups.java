package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaBackups extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaBackups telaBackups;
	
	public JInternal_TelaBackups() {
		super("Historico de Backups");
		setIconifiable(false);
		setMinimumSize(new Dimension(400, 320));
		setPreferredSize(new Dimension(400, 320));
		setSize(getPreferredSize());
		
		telaBackups = new TelaBackups();
		add(telaBackups, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaBackups getTelaBackups() {
		return telaBackups;
	}

}
