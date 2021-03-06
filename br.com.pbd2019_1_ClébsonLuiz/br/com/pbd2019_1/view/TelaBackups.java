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
	private Botao btnAgendarBackup;
	
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
		
		btnAgendarBackup = new Botao();
		btnAgendarBackup.setText("Agendar Backup");
		panel.add(btnAgendarBackup);
		
		btnNovoBackup = new Botao("Criar novo Backup");
		btnNovoBackup.setName("novo");
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

	public Botao getBtnAgendarBackup() {
		return btnAgendarBackup;
	}
	
}
