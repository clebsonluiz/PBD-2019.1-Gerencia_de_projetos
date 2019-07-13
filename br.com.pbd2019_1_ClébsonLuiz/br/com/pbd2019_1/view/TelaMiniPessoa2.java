package br.com.pbd2019_1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Bordas;

public class TelaMiniPessoa2 extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	
	private CampoSenha senhaAntigaField;
	private CampoSenha senhaNovaField;
	private TelaCadastroEdicao telaCadastroEdicao;
	private Border border = Bordas.criarBordaTitulo("Senha Antiga e Nova Senha",

			TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
	private JPanel panel;

	private JCheckBox exibirSenhaChbx;

	private JCheckBox exibirSenhaChbx0;

	public TelaMiniPessoa2() {
		setPreferredSize(new Dimension(310, 240));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(getBackground());
		panel.setLayout(null);
		panel.setBorder(border);
		panel.setBounds(6, 6, 300, 172);
		add(panel);
		
		senhaAntigaField = new CampoSenha();
		senhaAntigaField.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		senhaAntigaField.setDescricao("Senha Antiga");
		senhaAntigaField.setBorder(Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		senhaAntigaField.setBounds(8, 25, 202, 65);
		panel.add(senhaAntigaField);
		
		senhaNovaField = new CampoSenha();
		senhaNovaField.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		senhaNovaField.setDescricao("Nova Senha");
		senhaNovaField.setBorder(Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		senhaNovaField.setBounds(8, 92, 202, 65);
		panel.add(senhaNovaField);
		
		exibirSenhaChbx = new JCheckBox("Ver");
		exibirSenhaChbx.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		exibirSenhaChbx.setBounds(215, 95, 75, 50);
		panel.add(exibirSenhaChbx);
		
		exibirSenhaChbx0 = new JCheckBox("Ver");
		exibirSenhaChbx0.setFont(new Font("Arial", Font.BOLD, 15));
		exibirSenhaChbx0.setBounds(215, 27, 75, 50);
		panel.add(exibirSenhaChbx0);
		
		exibirSenhaChbx0.addItemListener(ItemEvent->{
			if(exibirSenhaChbx0.isSelected())
				senhaAntigaField.setEchoChar((char)0);
			else
				senhaAntigaField.setEchoChar('*');
		});
		
		exibirSenhaChbx.addItemListener(ItemEvent->{
			if(exibirSenhaChbx.isSelected())
				senhaNovaField.setEchoChar((char)0);
			else
				senhaNovaField.setEchoChar('*');
		});
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				
				getSenhaAntigaField().setEnabled(true);
				getSenhaNovaField().setEnabled(true);
				super.abilitar();
			}
			
			protected void desabilitar() {
				getSenhaAntigaField().setEnabled(false);
				getSenhaNovaField().setEnabled(false);
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBackground(SystemColor.menu);
		telaCadastroEdicao.setBounds(5, 190, 300, 38);
		add(telaCadastroEdicao);
	}
	
	public void limparCampos() {
		getSenhaAntigaField().setText("");
		getSenhaNovaField().setText("");
	}

	public CampoSenha getSenhaAntigaField() {
		return senhaAntigaField;
	}

	public CampoSenha getSenhaNovaField() {
		return senhaNovaField;
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

	public JCheckBox getExibirSenhaChbx0() {
		return exibirSenhaChbx0;
	}

	public Border getBorda() {
		return border;
	}
	
}
