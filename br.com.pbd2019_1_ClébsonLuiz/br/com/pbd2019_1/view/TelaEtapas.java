package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaEtapas extends JPanel {

private static final long serialVersionUID = 1L;
	
	private JTable table;
	private Botao btNovaEtapa;
	
	public TelaEtapas() {
		setMinimumSize(new Dimension(510, 200));
		setPreferredSize(new Dimension(510, 200));
		setLayout(new BorderLayout(10, 10));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btNovaEtapa = new Botao("Criar nova Etapa");
		panel.add(btNovaEtapa);
		
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtNovaEtapa() {
		return btNovaEtapa;
	}
}
