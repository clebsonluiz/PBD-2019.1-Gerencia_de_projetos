package br.com.pbd2019_1.view;
import java.awt.Dimension;
import javax.swing.JPanel;

import br.com.pbd2019_1.utils.ViewUtil;

import java.awt.BorderLayout;
import javax.swing.JProgressBar;

public class TelaInfoProjeto extends TelaProjeto {

	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;

	public TelaInfoProjeto() {
		setMinimumSize(super.getMinimumSize());
		setPreferredSize(new Dimension(510, 270));
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setMinimumSize(new Dimension(510, 60));
		panel.setPreferredSize(new Dimension(510, 70));
		panel.setBorder(ViewUtil.Bordas.criarBordaTitulo("Tempo Decorrido"));
		add(panel, BorderLayout.SOUTH);
		
		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(510, 50));
		panel.add(progressBar, BorderLayout.CENTER);
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
}
