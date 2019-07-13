package br.com.pbd2019_1.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;

public class TelaJTabViewColaborador extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	
	private TelaTabelaViewGerenteEtapa telaTabelaViewGerenteEtapa;
	private TelaTabelaViewSubEtapaColaborador telaTabelaViewSubEtapaColaborador;
	private TelaTabelaViewTarefaColaborador telaTabelaViewTarefaColaborador;
	
	public TelaJTabViewColaborador() {
		setPreferredSize(new Dimension(600, 400));
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		telaTabelaViewGerenteEtapa = new TelaTabelaViewGerenteEtapa();
		telaTabelaViewSubEtapaColaborador = new TelaTabelaViewSubEtapaColaborador();
		telaTabelaViewTarefaColaborador = new TelaTabelaViewTarefaColaborador();
		
		tabbedPane.addTab("Colaborações Como Gerente", telaTabelaViewGerenteEtapa);
		tabbedPane.addTab("Colaborações em SubEtapas ", telaTabelaViewSubEtapaColaborador);
		tabbedPane.addTab("Colaborações em Tarefas", telaTabelaViewTarefaColaborador);
	}

	public TelaTabelaViewGerenteEtapa getTelaTabelaViewGerenteEtapa() {
		return telaTabelaViewGerenteEtapa;
	}

	public TelaTabelaViewSubEtapaColaborador getTelaTabelaViewSubEtapaColaborador() {
		return telaTabelaViewSubEtapaColaborador;
	}

	public TelaTabelaViewTarefaColaborador getTelaTabelaViewTarefaColaborador() {
		return telaTabelaViewTarefaColaborador;
	}

}
