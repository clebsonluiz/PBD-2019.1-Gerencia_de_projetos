package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaEtapa extends JPanel {

	private static final long serialVersionUID = 1L;
	private CampoTexto nomeEtapaField;
	private JTextArea descricaoTextArea;
	private JProgressBar barraProgressBar;

	public TelaEtapa() {
		setMinimumSize(new Dimension(300, 290));
		setPreferredSize(new Dimension(300, 290));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Etapa"));
		panel.setBounds(6, 6, 288, 200);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(ViewUtil.Bordas.criarBordaTituloField("Descrição"));
		scrollPane.setBounds(6, 69, 276, 125);
		panel.add(scrollPane);
		
		descricaoTextArea = new JTextArea();
		descricaoTextArea.setWrapStyleWord(true);
		descricaoTextArea.setLineWrap(true);
		scrollPane.setViewportView(descricaoTextArea);
		
		nomeEtapaField = new CampoTexto();
		nomeEtapaField.setBorder(ViewUtil.Bordas.criarBordaArredondada(null));
		nomeEtapaField.setDescricao("Nome da Etapa");
		nomeEtapaField.setBounds(8, 22, 272, 46);
		panel.add(nomeEtapaField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(ViewUtil.Bordas.criarBordaTitulo("Progresso"));
		panel_1.setToolTipText("Progresso em andamanto da etapa");
		panel_1.setBounds(6, 210, 288, 68);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		barraProgressBar = new JProgressBar();
		barraProgressBar.setStringPainted(true);
		barraProgressBar.setMinimum(0);
		barraProgressBar.setMaximum(100);
		barraProgressBar.setToolTipText("Progresso em andamanto da etapa");
		panel_1.add(barraProgressBar, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 218, 288, 60);
		add(panel_2);
	}

	public CampoTexto getNomeEtapaField() {
		return nomeEtapaField;
	}

	public JTextArea getDescricaoTextArea() {
		return descricaoTextArea;
	}

	public JProgressBar getBarraProgressBar() {
		return barraProgressBar;
	}
	
	
}
