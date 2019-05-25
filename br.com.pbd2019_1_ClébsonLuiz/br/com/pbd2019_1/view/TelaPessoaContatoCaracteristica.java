package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public abstract class TelaPessoaContatoCaracteristica extends JPanel {

	private TelaMiniPessoa1 telaMiniPessoa1;
	private TelaMiniPessoa2 telaMiniPessoa2;
	
	
	private TelaContatoCaracteristica telaContatoCaracteristica;
	private TelaGraficoPessoa telaGraficoPessoa;
	private JTabbedPane tabbedPane;
	private static final long serialVersionUID = 1L;

	public TelaPessoaContatoCaracteristica() {
		
		setMinimumSize(new Dimension(320, 420));
		setPreferredSize(new Dimension(320, 420));
		setMaximumSize(new Dimension(600, 520));
//		setLayout(new GridBagLayout());
		setLayout(new BorderLayout());
		
		JPanel border = new JPanel(new BorderLayout());
		border.setMinimumSize(new Dimension(320, 420));
		//border.setPreferredSize(new Dimension(310, 420));
		border.setMaximumSize(new Dimension(600, 520));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		telaMiniPessoa1 = new TelaMiniPessoa1();
		telaMiniPessoa2 = new TelaMiniPessoa2();
		
		
//		telaPessoa = new TelaPessoa();
		telaContatoCaracteristica = new TelaContatoCaracteristica();
		telaGraficoPessoa = new TelaGraficoPessoa();
		
		/*
		JScrollPane scroll1 = new JScrollPane(telaPessoa);
		scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scroll2 = new JScrollPane(telaContatoCaracteristica);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		*/
		//scroll1.setBorder(ViewUtil.Bordas.criarBordaArredondadaBasic(""));
		//scroll2.setBorder(ViewUtil.Bordas.criarBordaTitulo(""));
		
		
		JPanel panel0 = new JPanel(new GridBagLayout());
		JPanel panel1 = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel(new GridBagLayout());
		JPanel panel3 = new JPanel(new GridBagLayout());
		
		panel0.setBackground(telaMiniPessoa1.getBackground());
		panel1.setBackground(telaMiniPessoa2.getBackground());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		
		panel0.add(telaMiniPessoa1);
		panel1.add(telaMiniPessoa2);
		
//		panel1.add(telaPessoa);
		panel2.add(telaContatoCaracteristica);
		panel3.add(telaGraficoPessoa);
		
		tabbedPane.addTab("Info", panel0);
//		tabbedPane.addTab("Info", panel1);
		tabbedPane.addTab("Config.", panel1);
		tabbedPane.addTab("Extra", panel2);
		tabbedPane.addTab("Desempenho", panel3);
		add(border);
		
		border.add(tabbedPane, BorderLayout.CENTER);
		
	}

	public void limparCampos() {
		telaMiniPessoa1.limparCampos();
		telaMiniPessoa2.limparCampos();
	}

	public TelaContatoCaracteristica getTelaContatoCaracteristica() {
		return telaContatoCaracteristica;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public TelaMiniPessoa1 getTelaMiniPessoa1() {
		return telaMiniPessoa1;
	}

	public TelaMiniPessoa2 getTelaMiniPessoa2() {
		return telaMiniPessoa2;
	}
	
}
