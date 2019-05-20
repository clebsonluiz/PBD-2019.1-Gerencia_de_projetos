package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaLogs extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private TelaPesquisaLog telaPesquisaLog;
	
	public TelaLogs() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(600, 400));
		setMinimumSize(getPreferredSize());
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		telaPesquisaLog = new TelaPesquisaLog() ;
		
		add(telaPesquisaLog, BorderLayout.NORTH);
	}

	public JTable getTable() {
		return table;
	}

	public TelaPesquisaLog getTelaPesquisaLog() {
		return telaPesquisaLog;
	}
}
