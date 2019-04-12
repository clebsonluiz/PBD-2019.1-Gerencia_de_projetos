package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaTarefas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private Botao btNovaTarefa;
	
	public TelaTarefas() {
		setMinimumSize(new Dimension(400, 320));
		setPreferredSize(new Dimension(400, 320));
		setLayout(new BorderLayout(10, 10));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btNovaTarefa = new Botao("Criar nova Tarefa");
		panel.add(btNovaTarefa);
		
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtNovaTarefa() {
		return btNovaTarefa;
	}
}
