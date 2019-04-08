package br.com.pbd2019_1.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaColaboradores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Botao btAdicionarColaborador;

	public TelaColaboradores() {
		setMinimumSize(new Dimension(510, 175));
		setPreferredSize(new Dimension(510, 175));
		setLayout(new BorderLayout(0, 0));
		setBorder(ViewUtil.Bordas.criarBordaTitulo("Colaboradores"));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btAdicionarColaborador = new Botao();
		btAdicionarColaborador.setText("Adicionar Colaborador");
		panel.add(btAdicionarColaborador);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtAdicionarColaborador() {
		return btAdicionarColaborador;
	}
	
	

}
