package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import br.com.pbd2019_1.utils.DateUtil;

public abstract class TelaPessoaContatoCaracteristica extends JPanel {

	private TelaPessoa telaPessoa;
	private TelaContatoCaracteristica telaContatoCaracteristica;
	private JTabbedPane tabbedPane;
	private static final long serialVersionUID = 1L;

	public TelaPessoaContatoCaracteristica() {
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(310, 420));
		setLayout(new GridBagLayout());
		JPanel border = new JPanel(new BorderLayout());
		border.setMinimumSize(new Dimension(310, 420));
		border.setPreferredSize(new Dimension(310, 420));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		telaPessoa = new TelaPessoa();
		telaContatoCaracteristica = new TelaContatoCaracteristica();
		JScrollPane scroll1 = new JScrollPane(telaPessoa);
		scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scroll2 = new JScrollPane(telaContatoCaracteristica);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//scroll1.setBorder(ViewUtil.Bordas.criarBordaArredondadaBasic(""));
		//scroll2.setBorder(ViewUtil.Bordas.criarBordaTitulo(""));
		
		tabbedPane.addTab("Info", scroll1);
		tabbedPane.addTab("Extra", scroll2);
		
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
