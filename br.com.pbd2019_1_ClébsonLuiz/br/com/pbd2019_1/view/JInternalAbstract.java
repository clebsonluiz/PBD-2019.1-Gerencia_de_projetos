package br.com.pbd2019_1.view;

import java.awt.Point;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
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
		resetLocation();
		toFront();
		setSelected(true);
		if(isIcon())
			setIcon(false);
		show();
		grabFocus();
	}
	
	private void resetLocation() 
	{
		if(getDesktopPane() != null) 
		{
			int largura = ((JDesktopPane) getDesktopPane()).getWidth();
			int altura = ((JDesktopPane) getDesktopPane()).getHeight();
			Point p =  new Point(largura/2 - this.getWidth()/2, altura/2 - this.getHeight()/2);
			this.setLocation(p);
		}
	}
}
