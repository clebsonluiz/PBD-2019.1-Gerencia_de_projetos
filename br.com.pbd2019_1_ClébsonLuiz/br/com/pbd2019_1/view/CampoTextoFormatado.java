package br.com.pbd2019_1.view;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class CampoTextoFormatado extends JFormattedTextField{

	
	private static final long serialVersionUID = 1L;
	
	public CampoTextoFormatado() {
	}

	public CampoTextoFormatado(String text) {
		super(text);
	}
	
	public CampoTextoFormatado(String text, Font font) {
		super(text);
		setFont(font);
	}
	
	public CampoTextoFormatado(Font font) {
		setFont(font);
	}
	
	
	public void setMascara(String mascaraCampo) {
		MaskFormatter mascara = new MaskFormatter();
        try{
            mascara.setMask(mascaraCampo); 
            mascara.setPlaceholderCharacter(' '); 
        }
        catch (Exception e) {
        e.printStackTrace();
        } 
        mascara.install(this);
	}
	

}
