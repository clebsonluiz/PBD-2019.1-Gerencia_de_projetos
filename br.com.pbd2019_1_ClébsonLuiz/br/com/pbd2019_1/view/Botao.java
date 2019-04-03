package br.com.pbd2019_1.view;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JButton;

public class Botao extends JButton {

	public static final Color CINZA_CLARO_PADRAO = Color.LIGHT_GRAY;
	public static final Color CINZA_CLARO_2 = new Color(210, 210, 210);
	
	private static final long serialVersionUID = 1L;

	public Botao() {
		super();
		setBackground(CINZA_CLARO_PADRAO);
	}
	
	public Botao(String texto) {
		super(texto);
		setBackground(CINZA_CLARO_PADRAO);
	}
	
	public Botao(Icon icone,String texto_ao_passar_mouse) {
		super(icone);
		setToolTipText(texto_ao_passar_mouse);
		setBackground(CINZA_CLARO_PADRAO);
	}
	
	public Botao(Icon icone,String texto_ao_passar_mouse, Color corFundo, Color corFrontal) {
		super(icone);
		setToolTipText(texto_ao_passar_mouse);
		setForeground(corFrontal);
		setBackground(corFundo);
	}
	
	public Botao(Icon icone, String texto, int posicaoVerticalTexto, int posicaoHorizontalTexto) {
		super(icone);
		setText(texto);
		setVerticalTextPosition(posicaoVerticalTexto);
		setHorizontalTextPosition(posicaoHorizontalTexto);
		setBackground(CINZA_CLARO_PADRAO);
	}
	
	
	
}
