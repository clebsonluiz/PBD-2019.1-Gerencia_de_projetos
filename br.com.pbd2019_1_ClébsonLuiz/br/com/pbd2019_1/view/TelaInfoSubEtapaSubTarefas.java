package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class TelaInfoSubEtapaSubTarefas extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private TelaInfoSubEtapa telaInfoSubEtapa;
	private TelaTarefas telaTarefas;

	public TelaInfoSubEtapaSubTarefas() {
		setLayout(new BorderLayout());
		telaInfoSubEtapa = new TelaInfoSubEtapa();
		
		telaTarefas = new TelaTarefas();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.25);
		
		add(splitPane, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(310, 400));
		setPreferredSize(new Dimension(310, 400));
		
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(telaInfoSubEtapa.getMinimumSize().width);
		splitPane.setDividerSize(10);
		splitPane.setAutoscrolls(false);
		JPanel pGridBag = new MeuJPanel(new GridBagLayout());
		pGridBag.add(telaInfoSubEtapa);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setMinimumSize(new Dimension(300, 320));
		panel.setPreferredSize(new Dimension(300, 320));
		panel.add(pGridBag, BorderLayout.CENTER);
		
		splitPane.setLeftComponent(panel);
		splitPane.setRightComponent(telaTarefas);
		
	}

	public TelaInfoSubEtapa getTelaInfoSubEtapa() {
		return telaInfoSubEtapa;
	}

	public TelaTarefas getTelaTarefas() {
		return telaTarefas;
	}

}
