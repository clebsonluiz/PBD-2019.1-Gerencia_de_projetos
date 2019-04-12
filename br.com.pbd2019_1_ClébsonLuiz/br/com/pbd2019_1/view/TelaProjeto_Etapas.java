package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TelaProjeto_Etapas extends JPanel {

	private static final long serialVersionUID = 1L;

	private TelaProjeto telaProjeto;
	private TelaEtapas telaEtapas;
	
	public TelaProjeto_Etapas() {
		setLayout(new BorderLayout(5, 5));
		setMinimumSize(new Dimension(510, 370));
		setPreferredSize(new Dimension(510, 370));
		
		telaProjeto = new TelaInfoProjeto();
		telaProjeto.getNomeProjetoField().setEnabled(false);
		telaProjeto.getDescricaoTextArea().setEnabled(false);
		telaProjeto.getDataInicioDateChooser().setEnabled(false);
		telaProjeto.getDataFimDateChooser().setEnabled(false);
		telaEtapas = new TelaEtapas();
		
		add(telaProjeto, BorderLayout.NORTH);
		add(telaEtapas, BorderLayout.CENTER);
	}

	public TelaProjeto getTelaProjeto() {
		return telaProjeto;
	}

	public TelaEtapas getTelaEtapas() {
		return telaEtapas;
	}
	
}
