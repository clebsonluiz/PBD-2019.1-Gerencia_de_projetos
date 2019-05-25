package br.com.pbd2019_1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Bordas;

public class TelaMiniPessoa2 extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	
	private CampoTexto loginField;
	private CampoSenha senhaField;
	private TelaCadastroEdicao telaCadastroEdicao;

	private JPanel panel;

	private JCheckBox exibirSenhaChbx;

	public TelaMiniPessoa2() {
		setPreferredSize(new Dimension(310, 240));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(getBackground());
		panel.setLayout(null);
		panel.setBorder(Bordas.criarBordaTitulo("Login e Senha",
						TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panel.setBounds(6, 6, 300, 172);
		add(panel);
		
		loginField = new CampoTexto(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		loginField.setDescricao("Login");
		loginField.setBorder(Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		loginField.setBounds(8, 25, 285, 65);
		panel.add(loginField);
		
		senhaField = new CampoSenha();
		senhaField.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		senhaField.setDescricao("Senha");
		senhaField.setBorder(Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		senhaField.setBounds(8, 92, 202, 65);
		panel.add(senhaField);
		
		exibirSenhaChbx = new JCheckBox("Ver");
		exibirSenhaChbx.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		exibirSenhaChbx.setBounds(215, 95, 75, 50);
		panel.add(exibirSenhaChbx);
		exibirSenhaChbx.addItemListener(ItemEvent->{
			if(exibirSenhaChbx.isSelected())
				senhaField.setEchoChar((char)0);
			else
				senhaField.setEchoChar('*');
		});
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				
				getLoginField().setEnabled(true);
				getSenhaField().setEnabled(true);
				super.abilitar();
			}
			
			protected void desabilitar() {
				getLoginField().setEnabled(false);
				getSenhaField().setEnabled(false);
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBackground(SystemColor.menu);
		telaCadastroEdicao.setBounds(5, 190, 300, 38);
		add(telaCadastroEdicao);
	}
	
	public void limparCampos() {
		getLoginField().setText("");
		getSenhaField().setText("");
	}

	public CampoTexto getLoginField() {
		return loginField;
	}

	public CampoSenha getSenhaField() {
		return senhaField;
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
		return exibirSenhaChbx;
	}
	
}
