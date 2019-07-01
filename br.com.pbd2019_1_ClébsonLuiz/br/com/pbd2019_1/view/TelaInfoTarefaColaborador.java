package br.com.pbd2019_1.view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTabbedPane;

public class TelaInfoTarefaColaborador extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private TelaInfoTarefa telaInfoTarefa;
	private TelaColaboradorEnvolvido telaColaboradorEnvolvido;

	public TelaInfoTarefaColaborador() {
		setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(300, 410));
		setPreferredSize(new Dimension(300, 410));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		telaInfoTarefa = new TelaInfoTarefa();
		telaColaboradorEnvolvido = new TelaColaboradorEnvolvido("Colaborador de Tarefa");
		
		tabbedPane.addTab("Tarefa", telaInfoTarefa);
		tabbedPane.addTab("Responsável de Tarefa", telaColaboradorEnvolvido);
		
	}

	public TelaInfoTarefa getTelaInfoTarefa() {
		return telaInfoTarefa;
	}

	public TelaColaboradorEnvolvido getTelaColaboradorEnvolvido() {
		return telaColaboradorEnvolvido;
	}

}
