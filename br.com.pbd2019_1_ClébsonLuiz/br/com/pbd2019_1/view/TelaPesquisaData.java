package br.com.pbd2019_1.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaPesquisaData extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private JDateChooser data1;
	private JDateChooser data2;
	private Botao btnBuscar;
	private JComboBox<String> comboBoxOrdem;

	public TelaPesquisaData() {
		setMinimumSize(new Dimension(600, 50));
		setPreferredSize(new Dimension(600, 50));
		setMaximumSize(new Dimension(700, 50));
		comboBox = new JComboBox<String>();
		comboBox.addItem("Todos");
		comboBox.addItem("Específicar");
		comboBox.setToolTipText("Tipo de Busca");
		comboBox.setSelectedIndex(0);
		comboBox.setMinimumSize(new Dimension(105, 40));
		comboBox.setPreferredSize(new Dimension(105, 40));
		add(comboBox);
		
		comboBoxOrdem = new JComboBox<String>();
		comboBoxOrdem.addItem("RECENTE");
		comboBoxOrdem.addItem("ANTIGO");
		comboBoxOrdem.setToolTipText("Ordem dos Resultados");
		comboBoxOrdem.setSelectedIndex(0);
		comboBoxOrdem.setMinimumSize(new Dimension(100, 40));
		comboBoxOrdem.setPreferredSize(new Dimension(100, 40));
		add(comboBoxOrdem);
		JLabel lblEntre = new JLabel("Entre:");
		add(lblEntre);
		data1 = new JDateChooser();
		data1.setDate(new Date());
		data1.setMinimumSize(new Dimension(120, 40));
		data1.setPreferredSize(new Dimension(120, 40));
		data1.setMaximumSize(new Dimension(150, 40));
		add(data1);
		JLabel lblE = new JLabel("E");
		add(lblE);
		data2 = new JDateChooser();
		data2.setDate(new Date());
		data2.setMinimumSize(new Dimension(120, 40));
		data2.setPreferredSize(new Dimension(120, 40));
		data2.setMaximumSize(new Dimension(150, 40));
		add(data2);
		btnBuscar = new Botao();
		btnBuscar.setIcon(ViewUtil.Icones.ICONE_LUPA);
		btnBuscar.setToolTipText("Buscar");
		add(btnBuscar);
		
		for(Component c: getComponents()) {
			if(c instanceof JLabel) c.setFont(ViewUtil.Fonts.Arial.ARIAL_PEQUENO_B);
			if(c instanceof JComboBox) c.setFont(ViewUtil.Fonts.Arial.ARIAL_PEQUENO_B);
			if(c instanceof JDateChooser) c.setFont(ViewUtil.Fonts.Arial.ARIAL_PEQUENO);
		}
		
		lblEntre.setVisible(false);
		data1.setVisible(false);
		lblE.setVisible(false);
		data2.setVisible(false);
		
		comboBox.addItemListener((ItemEvent)->{
			if(comboBox.getSelectedIndex() == 0)
			{
				lblEntre.setVisible(false);
				data1.setVisible(false);
				lblE.setVisible(false);
				data2.setVisible(false);
			}
			else 
			{
				lblEntre.setVisible(true);
				data1.setVisible(true);
				lblE.setVisible(true);
				data2.setVisible(true);
			}
		});
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public JDateChooser getData1() {
		return data1;
	}

	public JDateChooser getData2() {
		return data2;
	}

	public Botao getBtnBuscar() {
		return btnBuscar;
	}

	public JComboBox<String> getComboBoxOrdem() {
		return comboBoxOrdem;
	}

}
