package br.com.pbd2019_1.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalTime;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import br.com.pbd2019_1.utils.ViewUtil;

public class Horario extends JPanel {

	@Override
	public void setEnabled(boolean b) {
		cmboxHora.setEnabled(b);
		cmboxMinuto.setEnabled(b);
		cmboxSegundo.setEnabled(b);
		super.setEnabled(b);
	}
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmboxHora;
	private JComboBox<String> cmboxMinuto;
	private JComboBox<String> cmboxSegundo;

	public Horario() {
		setLayout(new GridLayout(1, 3, 2, 2));
		setBorder(ViewUtil.Bordas.criarBordaTituloField("HH:MM:SS"));
		cmboxHora = new JComboBox<String>();
		add(cmboxHora);
		
		cmboxMinuto = new JComboBox<String>();
		add(cmboxMinuto);
		
		cmboxSegundo = new JComboBox<String>();
		add(cmboxSegundo);

		for(int i = 0; i < 60; i++) {
			if(i < 10) {
				cmboxHora.addItem("0"+i);
				cmboxMinuto.addItem("0"+i);
				cmboxSegundo.addItem("0"+i);
			}
			else{
				if(i < 24)
					cmboxHora.addItem(""+i);
				cmboxMinuto.addItem(""+i);
				cmboxSegundo.addItem(""+i);
			}
		}
		
		cmboxHora.setSelectedIndex(0);
		cmboxMinuto.setSelectedIndex(0);
		cmboxSegundo.setSelectedIndex(0);

	}
	
	public LocalTime getLocalTime() {
		return LocalTime.of(
				cmboxHora.getSelectedIndex(),
				cmboxMinuto.getSelectedIndex(),
				cmboxSegundo.getSelectedIndex()
				);
	}
	
	public void setLocalTime(Object h, Object m, Object s) {
		cmboxHora.setSelectedItem(h);
		cmboxMinuto.setSelectedItem(m);
		cmboxSegundo.setSelectedItem(s);
	}

	public String toString() 
	{
		return new String(
				(String)cmboxHora.getSelectedItem()+ ":" + 
				(String)cmboxMinuto.getSelectedItem() + ":" + 
				(String)cmboxSegundo.getSelectedItem());
	}
	
	public void setFonte(Font font)
	{
		cmboxHora.setFont(font);
		cmboxMinuto.setFont(font);
		cmboxSegundo.setFont(font);
	}
}
