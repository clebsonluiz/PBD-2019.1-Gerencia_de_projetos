package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TelaProjeto_Etapas extends JPanel {

	private static final long serialVersionUID = 1L;

	private TelaProjeto telaProjeto;
	private TelaEtapas telaEtapas;
	private TelaColaboradores telaColaboradores;
	
	private JTabbedPane jTabbedPane;
	public TelaProjeto_Etapas() {
		setLayout(new BorderLayout(5, 5));
		setMinimumSize(new Dimension(510, 430));
		setPreferredSize(new Dimension(510, 430));
		
		telaProjeto = new TelaInfoProjeto();
		telaProjeto.getNomeProjetoField().setEnabled(false);
		telaProjeto.getDescricaoTextArea().setEnabled(false);
		telaProjeto.getDataInicioDateChooser().setEnabled(false);
		telaProjeto.getDataFimDateChooser().setEnabled(false);
		telaEtapas = new TelaEtapas();
		telaColaboradores = new TelaColaboradores();
		
		jTabbedPane = new JTabbedPane(JTabbedPane.EAST);
		jTabbedPane.add("Etapas", telaEtapas);
		jTabbedPane.add("Colaboradores", telaColaboradores);
		
		add(telaProjeto, BorderLayout.NORTH);
		add(jTabbedPane, BorderLayout.CENTER);
	}

	public TelaProjeto getTelaProjeto() {
		return telaProjeto;
	}

	public TelaEtapas getTelaEtapas() {
		return telaEtapas;
	}

	public TelaColaboradores getTelaColaboradores() {
		return telaColaboradores;
	}

	public JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}
	
}