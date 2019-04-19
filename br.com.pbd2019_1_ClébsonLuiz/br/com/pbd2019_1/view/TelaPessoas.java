package br.com.pbd2019_1.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaPessoas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Botao btAdicionarPessoa;

	public TelaPessoas() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btAdicionarPessoa = new Botao();
		btAdicionarPessoa.setText("Adicionar Pessoa");
		panel.add(btAdicionarPessoa);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtAdicionarPessoa() {
		return btAdicionarPessoa;
	}
	
}
