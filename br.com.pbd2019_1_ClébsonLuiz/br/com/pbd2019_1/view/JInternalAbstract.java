package br.com.pbd2019_1.view;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public abstract class JInternalAbstract extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public JInternalAbstract(String titulo) {
		super(titulo, false, true, true, true);
		setVisible(false);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				fechar();
				setVisible(false);
			}
		});
	}
	
	protected abstract void fechar();
	
	public void queroFoco() throws PropertyVetoException {
		toFront();
		setSelected(true);
		if(isIcon())
			setIcon(false);
		show();
		grabFocus();
	}
}
