package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class TelaInfoPessoaProjetos extends MeuJPanel {

	private static final long serialVersionUID = 1L;

	private TelaInfoPessoa telaInfoPessoa;
	private TelaProjetos telaProjetos;
	private TelaColaboracoes telaColaboracoes;
	private TelaJTabViewColaborador telaJTabViewColaborador;
	private JTabbedPane tabbedPane;
	
	public TelaInfoPessoaProjetos() {
		setLayout(new BorderLayout(0, 0));
		
		telaInfoPessoa = new TelaInfoPessoa();
		setMinimumSize(telaInfoPessoa.getPreferredSize());
		setPreferredSize(new Dimension(330, 450));
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
		telaJTabViewColaborador = new TelaJTabViewColaborador();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setPreferredSize(new Dimension(400, 430));
		
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		
		jTabbedPane.addTab("Como Colaborador de Projeto", telaColaboracoes);
		jTabbedPane.addTab("Colaborando como", telaJTabViewColaborador);
		
		splitPane.setRightComponent(tabbedPane);
		
		tabbedPane.addTab("Meus Projetos", telaProjetos);
		tabbedPane.addTab("Minhas Colaborações", jTabbedPane);
	}

	public TelaInfoPessoa getTelaInfoPessoa() {
		return telaInfoPessoa;
	}

	public TelaProjetos getTelaProjetos() {
		return telaProjetos;
	}

	public TelaColaboracoes getTelaColaboracoes() {
		return telaColaboracoes;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public TelaJTabViewColaborador getTelaJTabViewColaborador() {
		return telaJTabViewColaborador;
	}
	
}
