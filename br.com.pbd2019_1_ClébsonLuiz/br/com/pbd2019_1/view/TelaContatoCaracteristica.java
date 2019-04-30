package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaContatoCaracteristica extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabelaCaracteristicaExtra;
	private CampoTexto emailField;
	private CampoTextoFormatado celularField;
	private CampoTextoFormatado telefoneField;
	private Botao btAdicionar;
	private TelaCadastroEdicao telaCadastroEdicao;

	public TelaContatoCaracteristica() {
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(300, 380));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 280, 150);
		panel.setBorder(BorderFactory.createTitledBorder("Contato"));
		add(panel);
		panel.setLayout(null);
		
		emailField = new CampoTexto();
		emailField.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		emailField.setBounds(15, 22, 250, 37);
		emailField.setDescricao("E-mail");
		panel.add(emailField);
		
		celularField = new CampoTextoFormatado();
		celularField.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		celularField.setBounds(15, 62, 250, 37);
		celularField.setMascara("(##) #####-####");
		panel.add(celularField);
		
		telefoneField = new CampoTextoFormatado();
		telefoneField.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		telefoneField.setBounds(15, 101, 250, 37);
		telefoneField.setMascara("(##) ####-####");
		panel.add(telefoneField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 204, 280, 170);
		panel_1.setBorder(BorderFactory.createTitledBorder("Caracteristicas Extras"));
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tabelaCaracteristicaExtra = new JTable();
		scrollPane.setViewportView(tabelaCaracteristicaExtra);
		
		btAdicionar = new Botao();
		btAdicionar.setText("Adicionar Caracteristica");
		panel_1.add(btAdicionar, BorderLayout.SOUTH);
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				getTelefoneField().setEnabled(true);
				getCelularField().setEnabled(true);
				getEmailField().setEnabled(true);
				super.abilitar();
			}
			
			protected void desabilitar() {
				getTelefoneField().setEnabled(false);
				getCelularField().setEnabled(false);
				getEmailField().setEnabled(false);
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBounds(10, 163, 284, 40);
		add(telaCadastroEdicao);
	}

	public JTable getJTable() {
		return tabelaCaracteristicaExtra;
	}

	public CampoTexto getEmailField() {
		return emailField;
	}

	public CampoTextoFormatado getCelularField() {
		return celularField;
	}

	public CampoTextoFormatado getTelefoneField() {
		return telefoneField;
	}

	public Botao getBtAdicionar() {
		return btAdicionar;
	}
	
	public Botao getBotao() {
		return this.telaCadastroEdicao.getBtBotao1();
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}
	
}
