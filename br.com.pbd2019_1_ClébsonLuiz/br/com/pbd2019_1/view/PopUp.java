package br.com.pbd2019_1.view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

public class PopUp extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	
	private JMenuItem[] menuItens;
	
	public PopUp(String[] itens) {
		
		menuItens = new JMenuItem[itens.length];
		
		for(int i = 0; i < itens.length; i++) {
			menuItens[i] = new JMenuItem(itens[i]);
			add(new JSeparator());
			add(menuItens[i]);
		}
	}

	public JMenuItem[] getMenuItens() {
		return menuItens;
	}
	
}
