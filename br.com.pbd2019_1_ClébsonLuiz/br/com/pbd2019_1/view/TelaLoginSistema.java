package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Fonts.Arial;


public class TelaLoginSistema extends JPanel{

private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private CampoTexto loginField;
	private CampoSenha senhaField;
	private JButton btnLogar;
	private JButton btnCadastrar;
	private JLabel lblSistema;

	private JLabel lblErro;

	
	public TelaLoginSistema() {
		super(new BorderLayout());
		
		setPreferredSize(new Dimension(400, 450));
		setMinimumSize(getPreferredSize());

		contentPane = new JPanel(null);
		
		contentPane.setPreferredSize(new Dimension(400, 400));
		contentPane.setMinimumSize(getPreferredSize());
		
		lblSistema = new JLabel(ViewUtil.Icones.ICONE_TELA_LOGIN);
		lblSistema.setBounds(23, 12, 345, 130);
		lblSistema.setToolTipText("Sistema de Gestão de Projetos Tela de Login");
		lblSistema.setFont(Arial.ARIAL_TITULO_B);
		lblSistema.setForeground(Color.RED.darker());
		contentPane.add(lblSistema);
		
		JLabel lblLogin = new JLabel(ViewUtil.Icones.ICONE_LOGIN_USUARIO);
		lblLogin.setToolTipText("CPF é o Login de Usuário");
		lblLogin.setBounds(10, 140, 98, 85);
		lblLogin.setFont(Arial.ARIAL_TITULO_B);
		contentPane.add(lblLogin);
		
		loginField = new CampoTexto();
		loginField.setDescricao("Digite seu CPF aqui!");
		loginField.setBounds(131, 155, 237, 50);
		loginField.setFont(Arial.ARIAL_TITULO_B);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		JLabel lblSenha = new JLabel(ViewUtil.Icones.ICONE_SENHA_USUARIO);
		lblSenha.setToolTipText("Senha de Usuário");
		lblSenha.setFont(Arial.ARIAL_TITULO_B);
		lblSenha.setBounds(10, 216, 98, 73);
		contentPane.add(lblSenha);
		
		senhaField = new CampoSenha();
		senhaField.setDescricao("Senha");
		senhaField.setBounds(131, 228, 237, 50);
		senhaField.setFont(Arial.ARIAL_TITULO_B);
		contentPane.add(senhaField);
		
		btnLogar = new JButton(ViewUtil.Icones.ICONE_LOGAR);
		btnLogar.setToolTipText("Logar no sistema");
		btnLogar.setBounds(23, 300, 98, 73);
		btnLogar.setFont(Arial.ARIAL_TITULO_B);
		btnLogar.setBackground(Color.lightGray);
		btnLogar.setForeground(Color.green.darker());
		contentPane.add(btnLogar);
		
		btnCadastrar = new JButton(ViewUtil.Icones.ICONE_CADASTRAR_USUARIO);
		btnCadastrar.setToolTipText("Cadastrar novo usuario");
		btnCadastrar.setBounds(141, 300, 220, 73);
		btnCadastrar.setFont(Arial.ARIAL_TITULO_B);
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.setForeground(Color.red.darker());
		contentPane.add(btnCadastrar);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(contentPane);
		add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		lblErro = new JLabel("");
		lblErro.setForeground(Color.RED.darker());
		lblErro.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO);
		panel_1.add(lblErro);
	}

	
	public void limparCampos() {
		loginField.setText("");
		senhaField.setText("");
	}
	
	public CampoTexto getLoginField() {
		return loginField;
	}

	public CampoSenha getSenhaField() {
		return senhaField;
	}

	public JButton getBtnLogar() {
		return btnLogar;
	}

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}

	public JLabel getLblErro() {
		return lblErro;
	}
	
}
