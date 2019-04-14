package br.com.pbd2019_1.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.GridLayout;

public class TelaInserirSQL extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextArea textArea;
	private Botao btInserir;
	private Botao btBtn;

	public TelaInserirSQL() {
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(300, 300));
		setPreferredSize(new Dimension(300, 300));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JPanel panel = new JPanel();
		
		panel.setMinimumSize(new Dimension(300, 40));
		panel.setPreferredSize(new Dimension(300, 40));
		
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 1, 5, 5));
		
		btInserir = new Botao();
		btInserir.setText("Inserir");
		panel.add(btInserir);
		
		btBtn = new Botao();
		btBtn.setText("Btn2");
		panel.add(btBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setMinimumSize(new Dimension(300, 100));
		scrollPane_1.setPreferredSize(new Dimension(300, 100));
		
		table = new JTable();
		
		add(scrollPane_1, BorderLayout.SOUTH);
		scrollPane_1.setViewportView(table);
	}

	public JTable getTable() {
		return table;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public Botao getBtInserir() {
		return btInserir;
	}

	public Botao getBtBtn() {
		return btBtn;
	}
	
}
