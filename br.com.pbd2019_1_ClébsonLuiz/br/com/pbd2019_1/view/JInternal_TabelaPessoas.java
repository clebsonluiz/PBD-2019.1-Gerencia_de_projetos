package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

public class JInternal_TabelaPessoas extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaPessoas telaPessoas;
	
	public JInternal_TabelaPessoas() {
		super("Pessoas");
		setIconifiable(false);
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setSize(getPreferredSize());
		telaPessoas = new TelaPessoas();
		add(telaPessoas, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	@Override
	public void queroFoco() throws PropertyVetoException {
		setMaximum(true);
		super.queroFoco();
	}
	
	public TelaPessoas getTelaPessoas() {
		return telaPessoas;
	}

}
