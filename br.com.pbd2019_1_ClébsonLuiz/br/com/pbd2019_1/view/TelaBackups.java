package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaBackups extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TelaPesquisaData telaPesquisaData;
	private JTable table;
	private Botao btnNovoBackup;
	
	public TelaBackups() {
		setMinimumSize(new Dimension(600, 320));
		setPreferredSize(new Dimension(600, 320));
		setLayout(new BorderLayout(10, 10));
		telaPesquisaData = new TelaPesquisaData();
		add(telaPesquisaData, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnNovoBackup = new Botao("Criar novo Backup");
		panel.add(btnNovoBackup);
		
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtnNovoBackup() {
		return btnNovoBackup;
	}

	public TelaPesquisaData getTelaPesquisaData() {
		return telaPesquisaData;
	}
	
}
