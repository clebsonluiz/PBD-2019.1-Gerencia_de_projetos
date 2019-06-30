package br.com.pbd2019_1.view;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaCadastroEdicao extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Botao btBotao1;
	private AbstractButton tglbtnBotao;

	public TelaCadastroEdicao() {
		btBotao1 = new Botao();
		btBotao1.setText("Concluir");
		btBotao1.setVisible(false);
		btBotao1.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		add(btBotao1);
		
		btBotao1.addActionListener(ActionEvent->{
			
		});
		
		tglbtnBotao = new JToggleButton("Editar");
		
		tglbtnBotao.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		
		tglbtnBotao.addActionListener((ActionEvent)->{
			if(tglbtnBotao.isSelected()) 
				abilitar();
			else 
				desabilitar();
		});
		
		add(tglbtnBotao);
	}
	
	public void escondeBtn()
	{
		desabilitar();
		tglbtnBotao.setSelected(false);
	}
	
	protected void abilitar() {
		btBotao1.setVisible(true);
		tglbtnBotao.setBackground(Color.red.brighter());
		tglbtnBotao.setForeground(Color.white);
		tglbtnBotao.setText("Cancelar");
	}
	
	protected void desabilitar() {
		btBotao1.setVisible(false);
		tglbtnBotao.setBackground(null);
		tglbtnBotao.setForeground(Color.black);
		tglbtnBotao.setText("Editar");
	}

	public Botao getBtBotao1() {
		return btBotao1;
	}

	protected void esconderTglbtnBotao() {
		tglbtnBotao.setEnabled(false);
		tglbtnBotao.setVisible(false);
		btBotao1.setVisible(true);
	}
	
}
