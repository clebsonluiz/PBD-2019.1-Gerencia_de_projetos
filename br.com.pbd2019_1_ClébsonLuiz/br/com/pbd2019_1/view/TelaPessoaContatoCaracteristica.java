package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

public abstract class TelaPessoaContatoCaracteristica extends JPanel {

	private TelaPessoa telaPessoa;
	private TelaContatoCaracteristica telaContatoCaracteristica;
	private TelaCadastroEdicao telaCadastroEdicao;
	
	private static final long serialVersionUID = 1L;

	public TelaPessoaContatoCaracteristica() {
		setMinimumSize(new Dimension(300, 340));
		setPreferredSize(new Dimension(310, 420));
		setLayout(new GridBagLayout());
		JPanel border = new JPanel(new BorderLayout());
		border.setMinimumSize(new Dimension(310, 420));
		border.setPreferredSize(new Dimension(310, 420));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		telaPessoa = new TelaPessoa();
		telaContatoCaracteristica = new TelaContatoCaracteristica();
		
		JScrollPane scroll1 = new JScrollPane(telaPessoa);
		scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scroll2 = new JScrollPane(telaContatoCaracteristica);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		tabbedPane.addTab("Info", scroll1);
		tabbedPane.addTab("Extra", scroll2);
		
		
		border.add(tabbedPane, BorderLayout.CENTER);
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				
				telaPessoa.getNomeField().setEnabled(true);
				telaPessoa.getLoginField().setEnabled(true);
				telaPessoa.getSenhaField().setEnabled(true);
				telaPessoa.getCampoFormatadoCPF().setEnabled(true);
				telaPessoa.getSexoComboBox().setEnabled(true);
				telaPessoa.getNascimentoDateChooser().setEnabled(true);
				telaPessoa.getRdbtnSim().setEnabled(true);
				telaPessoa.getRdbtnNo().setEnabled(true);
				
				telaContatoCaracteristica.getTelefoneField().setEnabled(true);
				telaContatoCaracteristica.getCelularField().setEnabled(true);
				telaContatoCaracteristica.getEmailField().setEnabled(true);
				
			}
			
			protected void desabilitar() {
				telaPessoa.getNomeField().setEnabled(false);
				telaPessoa.getLoginField().setEnabled(false);
				telaPessoa.getSenhaField().setEnabled(false);
				telaPessoa.getCampoFormatadoCPF().setEnabled(false);
				telaPessoa.getSexoComboBox().setEnabled(false);
				telaPessoa.getNascimentoDateChooser().setEnabled(false);
				telaPessoa.getRdbtnSim().setEnabled(false);
				telaPessoa.getRdbtnNo().setEnabled(false);
				
				telaContatoCaracteristica.getTelefoneField().setEnabled(false);
				telaContatoCaracteristica.getCelularField().setEnabled(false);
				telaContatoCaracteristica.getEmailField().setEnabled(false);
			}
			
		};
		
		border.add(telaCadastroEdicao, BorderLayout.SOUTH);		
		
		add(border);
		
	}

	public void limparCampos() {
		
	}

	
	
	public TelaPessoa getTelaPessoa() {
		return telaPessoa;
	}

	public TelaContatoCaracteristica getTelaContatoCaracteristica() {
		return telaContatoCaracteristica;
	}

	public Botao getBtBotao1() {
		return telaCadastroEdicao.getBtBotao1();
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}

	
	
}
