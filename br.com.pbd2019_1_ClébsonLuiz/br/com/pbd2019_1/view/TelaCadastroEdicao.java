package br.com.pbd2019_1.view;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class TelaCadastroEdicao extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Botao btBotao1;
	private AbstractButton tglbtnBotao;

	public TelaCadastroEdicao() {
		btBotao1 = new Botao();
		btBotao1.setText("Concluir");
		btBotao1.setVisible(false);
		add(btBotao1);
		
		tglbtnBotao = new JToggleButton("Editar");
		
		tglbtnBotao.addActionListener((ActionEvent)->{
			if(tglbtnBotao.isSelected()) 
				abilitar();
			else 
				desabilitar();
		});
		
		add(tglbtnBotao);
	}

	
	
	protected void abilitar() {
		btBotao1.setVisible(true);
		tglbtnBotao.setBackground(Color.red.brighter());
		tglbtnBotao.setText("Cancelar");
	}
	
	protected void desabilitar() {
		btBotao1.setVisible(false);
		tglbtnBotao.setBackground(null);
		tglbtnBotao.setText("Editar");
	}

	public Botao getBtBotao1() {
		return btBotao1;
	}
	
}
