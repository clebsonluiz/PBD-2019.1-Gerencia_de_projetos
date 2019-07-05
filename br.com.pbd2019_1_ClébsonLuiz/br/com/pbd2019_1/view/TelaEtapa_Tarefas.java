package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class TelaEtapa_Tarefas extends MeuJPanel {

	private static final long serialVersionUID = 1L;

	private TelaEtapa telaEtapa;
	private TelaEtapas telaEtapas;
	private TelaTarefas telaTarefas;
	
	public TelaEtapa_Tarefas() {
		setLayout(new BorderLayout(10, 10));
		
		telaEtapa = new TelaInfoEtapa();
		telaEtapas = new TelaEtapas();
		telaTarefas = new TelaTarefas();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		
		add(splitPane, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(310, 400));
		setPreferredSize(new Dimension(310, 400));
		
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(telaEtapa.getMinimumSize().width);
		splitPane.setDividerSize(10);
		splitPane.setAutoscrolls(false);
		JPanel pGridBag = new JPanel(new GridBagLayout());
		pGridBag.setBackground(getBackground());
		pGridBag.add(telaEtapa);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setMinimumSize(new Dimension(300, 320));
		panel.setPreferredSize(new Dimension(300, 320));
		panel.add(pGridBag, BorderLayout.CENTER);
		
		splitPane.setLeftComponent(panel);
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.addTab("Tarefas", telaTarefas);
		jTabbedPane.addTab("Sub Etapas", telaEtapas);
		
		splitPane.setRightComponent(jTabbedPane);
	}

	public TelaEtapa getTelaEtapa() {
		return telaEtapa;
	}

	public TelaTarefas getTelaTarefas() {
		return telaTarefas;
	}

	public TelaEtapas getTelaSubEtapas() {
		return telaEtapas;
	}
	
}
