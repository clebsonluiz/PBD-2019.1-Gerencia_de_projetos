package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class TelaProjetos extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Botao btCriarUmNovo;
	private Botao btnGerarRelatorio;
	private JComboBox<String> comboBox;

	public TelaProjetos() {
		setMinimumSize(new Dimension(400, 340));
		setPreferredSize(new Dimension(400, 340));
		setLayout(new BorderLayout(10, 10));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btCriarUmNovo = new Botao();
		btCriarUmNovo.setText("Criar um novo projeto");
		panel.add(btCriarUmNovo);
		
		btnGerarRelatorio = new Botao("Gerar Relatorio");
		panel.add(btnGerarRelatorio);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Resumido");
		comboBox.addItem("COMPLETO");
		comboBox.addItem("COMPLETO COM TABELA");
		comboBox.setSelectedIndex(0);
		
		panel.add(comboBox);
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtCriarUmNovo() {
		return btCriarUmNovo;
	}

	public Botao getBtnGerarRelatorio() {
		return btnGerarRelatorio;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
}
