package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaInserirSQL extends JPanel {

	private static Font font = new Font("Consolas", Font.PLAIN, 16);
	private static Font font2 = new Font("Consolas", Font.PLAIN, 14);
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextArea textArea;
	private JTextArea exceptionTextArea;
	private Botao btnInserir;
	private Botao btnLimpar;
	private Botao btnSalvar;
	private Botao btnAbrir;

	public TelaInserirSQL() {
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(300, 350));
		setPreferredSize(new Dimension(300, 350));
		JScrollPane scrollPane = new JScrollPane();
		
		textArea = new JTextArea();
		textArea.setFont(font);
		scrollPane.setViewportView(textArea);
		scrollPane.setMinimumSize(new Dimension(300, 150));
		scrollPane.setPreferredSize(new Dimension(300, 150));
		
		JPanel panelGrid = new JPanel();
		JPanel panelCentro = new JPanel(new BorderLayout(0, 0));

		add(panelCentro, BorderLayout.CENTER);
		
		panelGrid.setMinimumSize(new Dimension(300, 40));
		panelGrid.setPreferredSize(new Dimension(300, 40));
		
		add(panelGrid, BorderLayout.NORTH);
		panelGrid.setLayout(new GridLayout(1, 5, 10, 10));
		
		btnInserir = new Botao();
		btnInserir.setIcon(ViewUtil.Icones.ICONE_SETA_PLAY);
		btnInserir.setText("Inserir");
		btnInserir.setToolTipText("Executar código SQL");
		panelGrid.add(btnInserir);
		
		btnLimpar = new Botao();
		btnLimpar.setIcon(ViewUtil.Icones.ICONE_VASSOURA);
		btnLimpar.setText("Limpar");
		btnLimpar.setToolTipText("Limpar o campo");
		panelGrid.add(btnLimpar);
		
		btnSalvar = new Botao();
		btnSalvar.setIcon(ViewUtil.Icones.ICONE_SALVAR_ARQUIVO);
		btnSalvar.setText("Salvar");
		btnSalvar.setToolTipText("Salvar arquivo sql");
		panelGrid.add(btnSalvar);
		
		btnAbrir = new Botao();
		btnAbrir.setIcon(ViewUtil.Icones.ICONE_ABRIR_ARQUIVO);
		btnAbrir.setText("Abrir");
		btnAbrir.setToolTipText("Abrir arquivo sql");
		panelGrid.add(btnAbrir);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setMinimumSize(new Dimension(300, 150));
		scrollPane_1.setPreferredSize(new Dimension(300, 150));
		
		table = new JTable();
		
		panelCentro.add(scrollPane, BorderLayout.CENTER);
		panelCentro.add(scrollPane_1, BorderLayout.SOUTH);
		
		scrollPane_1.setViewportView(table);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		exceptionTextArea = new JTextArea();
		exceptionTextArea.setFont(font2);
		exceptionTextArea.setEditable(false);
		exceptionTextArea.setWrapStyleWord(true);
		exceptionTextArea.setLineWrap(true);
		exceptionTextArea.setBackground(Color.LIGHT_GRAY);
		exceptionTextArea.setForeground(Color.RED);
		exceptionTextArea.setText("Campo de Exceções");
		scrollPane_2.setViewportView(exceptionTextArea);
		scrollPane_2.setMinimumSize(new Dimension(300, 50));
		scrollPane_2.setPreferredSize(new Dimension(300, 50));
		
		add(scrollPane_2, BorderLayout.SOUTH);
	}

	public JTable getTable() {
		return table;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public Botao getBtnInserir() {
		return btnInserir;
	}

	public Botao getBtnLimpar() {
		return btnLimpar;
	}
	
	public Botao getBtnSalvar() {
		return btnSalvar;
	}

	public Botao getBtnAbrir() {
		return btnAbrir;
	}

	public JTextArea getExceptionTextArea() {
		return exceptionTextArea;
	}
	
}
