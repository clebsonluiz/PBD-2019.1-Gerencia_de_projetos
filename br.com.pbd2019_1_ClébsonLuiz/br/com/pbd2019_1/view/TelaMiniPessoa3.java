package br.com.pbd2019_1.view;

import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Bordas;

public class TelaMiniPessoa3 extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private TelaCadastroEdicao telaCadastroEdicao;

	private JPanel panel;

	private JCheckBox resetarSenhaChbx;

	public TelaMiniPessoa3() {
		setPreferredSize(new Dimension(310, 240));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(getBackground());
		panel.setLayout(null);
		panel.setBorder(Bordas.criarBordaTitulo("Senha de Usuário",
						TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panel.setBounds(6, 6, 300, 172);
		add(panel);
		
		resetarSenhaChbx = new JCheckBox("Resetar senha");
		resetarSenhaChbx.setVisible(false);
		resetarSenhaChbx.setToolTipText("Ao marcar essa op\u00E7\u00E3o, estar\u00E1 indicando que este usu\u00E1rio ter\u00E1 sua senha resetada");
		resetarSenhaChbx.setHorizontalAlignment(SwingConstants.CENTER);
		resetarSenhaChbx.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		resetarSenhaChbx.setBounds(56, 63, 191, 50);
		panel.add(resetarSenhaChbx);
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				resetarSenhaChbx.setVisible(true);
				super.abilitar();
			}
			
			protected void desabilitar() {
				resetarSenhaChbx.setVisible(false);
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBackground(SystemColor.menu);
		telaCadastroEdicao.setBounds(5, 190, 300, 38);
		add(telaCadastroEdicao);
	}
	
	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}
	
	public Botao getBotao1() {
		return telaCadastroEdicao.getBtBotao1();
	}

	public JPanel getPanel() {
		return panel;
	}

	public JCheckBox getExibirSenhaChbx() {
		return resetarSenhaChbx;
	}
}
