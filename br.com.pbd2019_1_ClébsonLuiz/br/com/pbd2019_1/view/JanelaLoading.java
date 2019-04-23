package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import br.com.pbd2019_1.utils.ViewUtil;

public class JanelaLoading extends JFrame {

	private static final long serialVersionUID = 1L;	

	private Font fonteCarregamento = new Font("Arial", Font.PLAIN, 14);
	private JLabel labelEtapa;
	private JProgressBar progressBar;
	
	public JanelaLoading() {
		super("Sistema de Gest\u00E3o de Projetos");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel(null);
		
		add(panel, BorderLayout.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setBounds(6, 243, 388, 51);
		progressBar.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(progressBar);
		
		labelEtapa = new JLabel("Carregando:...");
		labelEtapa.setFont(fonteCarregamento);
		labelEtapa.setForeground(Color.white);
		labelEtapa.setBounds(6, 215, 388, 30);
		panel.add(labelEtapa);
		
		JLabel label = new JLabel("");
		label.setIcon(ViewUtil.Icones.IMAGEM_CARREGAMENTO);
		label.setBounds(0, 0, getWidth(), getHeight());
		panel.add(label);
	}
	
	public void etapaAtual(String etapa, int porcentagem) {
		labelEtapa.setText("Carregando:..." + etapa);
		progressBar.setValue(porcentagem);
		revalidate();
		repaint();
	}
	
	
}
