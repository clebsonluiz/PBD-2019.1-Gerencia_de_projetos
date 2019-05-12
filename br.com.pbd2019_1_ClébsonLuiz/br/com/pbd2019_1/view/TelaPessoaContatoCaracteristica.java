package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.com.pbd2019_1.utils.DateUtil;

public abstract class TelaPessoaContatoCaracteristica extends JPanel {

	private TelaPessoa telaPessoa;
	private TelaContatoCaracteristica telaContatoCaracteristica;
	private TelaGraficoPessoa telaGraficoPessoa;
	private JTabbedPane tabbedPane;
	private static final long serialVersionUID = 1L;

	public TelaPessoaContatoCaracteristica() {
		
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(310, 420));
		setMaximumSize(new Dimension(600, 520));
		setLayout(new GridBagLayout());
		
		JPanel border = new JPanel(new BorderLayout());
		border.setMinimumSize(new Dimension(310, 420));
		//border.setPreferredSize(new Dimension(310, 420));
		border.setMaximumSize(new Dimension(600, 520));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		telaPessoa = new TelaPessoa();
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
		
		JPanel panel1 = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel(new GridBagLayout());
		JPanel panel3 = new JPanel(new GridBagLayout());
		
		panel1.add(telaPessoa);
		panel2.add(telaContatoCaracteristica);
		panel3.add(telaGraficoPessoa);
		
		tabbedPane.addTab("Info", panel1);
		tabbedPane.addTab("Extra", panel2);
		tabbedPane.addTab("Desempenho", panel3);
		add(border);
		
		border.add(tabbedPane, BorderLayout.CENTER);
		
	}

	public void limparCampos() {
		telaPessoa.getNomeField().setText("");
		telaPessoa.getLoginField().setText("");
		telaPessoa.getSenhaField().setText("");
		telaPessoa.getCampoFormatadoCPF().setText("");
		telaPessoa.getSexoComboBox().setSelectedIndex(0);
		telaPessoa.getNascimentoDateChooser().setDate(DateUtil.getDataAtual());
		telaPessoa.getRdbtnSim().setSelected(false);
		telaPessoa.getRdbtnNo().setSelected(true);
	}

	public TelaPessoa getTelaPessoa() {
		return telaPessoa;
	}

	public TelaContatoCaracteristica getTelaContatoCaracteristica() {
		return telaContatoCaracteristica;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

}
