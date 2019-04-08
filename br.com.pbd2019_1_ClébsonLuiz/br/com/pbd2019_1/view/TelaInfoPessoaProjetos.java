package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class TelaInfoPessoaProjetos extends JPanel {

	private static final long serialVersionUID = 1L;

	private TelaInfoPessoa telaInfoPessoa;
	private TelaProjetos telaProjetos;
	private TelaColaboracoes telaColaboracoes;
	
	public TelaInfoPessoaProjetos() {
		setLayout(new BorderLayout(0, 0));
		
		telaInfoPessoa = new TelaInfoPessoa();
		setMinimumSize(telaInfoPessoa.getMinimumSize());
		setPreferredSize(telaInfoPessoa.getPreferredSize());
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		add(splitPane);
		
		splitPane.setLeftComponent(telaInfoPessoa);
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(telaInfoPessoa.getMaximumSize().width);
		splitPane.setDividerSize(10);
		splitPane.setAutoscrolls(false);
		
		telaProjetos = new TelaProjetos();
		telaColaboracoes = new TelaColaboracoes();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(400, 340));
		
		tabbedPane.addTab("Projetos", telaProjetos);
		tabbedPane.addTab("Colaborações", telaColaboracoes);
		
		splitPane.setRightComponent(tabbedPane);
		
//		add(telaPessoaContatoCaracteristica, BorderLayout.WEST);
		/*
		JToggleButton toggleButton = new JToggleButton(">");
		add(toggleButton, BorderLayout.CENTER);
		
		telaProjetos = new TelaProjetos();
		telaColaboracoes = new TelaColaboracoes();*/
		
		/*;
		
		add(tabbedPane, BorderLayout.EAST);*/
		
		
		
		
		
		
	}
}
