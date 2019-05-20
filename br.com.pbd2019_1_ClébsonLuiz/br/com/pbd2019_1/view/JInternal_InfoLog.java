package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_InfoLog extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInfoLog telaInfoLog;
	
	public JInternal_InfoLog() {
		super("Info do Log");
		setIconifiable(false);
		setMaximizable(false);
		setPreferredSize(new Dimension(405, 400));
		setMinimumSize(getPreferredSize());
		setSize(getPreferredSize());
		telaInfoLog = new TelaInfoLog();
		
		add(telaInfoLog, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoLog getTelaInfoLog() {
		return telaInfoLog;
	}

}
