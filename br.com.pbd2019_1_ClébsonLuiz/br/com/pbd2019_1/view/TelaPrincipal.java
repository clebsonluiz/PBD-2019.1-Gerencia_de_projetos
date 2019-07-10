package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final String TELA_LOGIN = "1";
	public static final String TELA_PRINCIPAL = "2";
	public static final String TELA_CADASTRO_PESSOA = "3";
	public static final String TELA_CADASTRO_SUPER = "4";
	
	private String atual = "1";
	private CardLayout card;
	
	private TelaMenu telaMenu;
	private JDesktopPane jDesktopPane;
	
	private TelaLoginSistema telaLoginSistema;
	private TelaCadastro_Pessoa telaCadastro_Pessoa;
	
	private Tela_CadastroSuperUsuario tela_CadastroSuperUsuario;
	
	public TelaPrincipal() {
		
		card = new CardLayout();
		JPanel panel = new JPanel(new BorderLayout());
		setLayout(card);
		telaMenu = new TelaMenu();
		jDesktopPane = new MeuJDesktopPane();
		jDesktopPane.setToolTipText("Área de Trabalho");
		jDesktopPane.setBackground(Color.BLACK);
		
		telaMenu.setBorder(ViewUtil.Bordas.criarBordaTituloField(""));
		jDesktopPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		telaLoginSistema = new TelaLoginSistema();
		telaCadastro_Pessoa = new TelaCadastro_Pessoa();
		tela_CadastroSuperUsuario = new Tela_CadastroSuperUsuario();
		
		JPanel gridPanel = new JPanel(new GridBagLayout());
		gridPanel.add(telaCadastro_Pessoa);
		gridPanel.setBackground(telaCadastro_Pessoa.getBackground());

		JPanel gridPanel2 = new JPanel(new GridBagLayout());
		gridPanel2.add(tela_CadastroSuperUsuario);
		gridPanel2.setBackground(tela_CadastroSuperUsuario.getBackground());
		
		panel.add(telaMenu, BorderLayout.WEST);
		panel.add(jDesktopPane, BorderLayout.CENTER);
		
		card.addLayoutComponent(panel, TELA_PRINCIPAL);
		card.addLayoutComponent(telaLoginSistema, TELA_LOGIN);
		card.addLayoutComponent(gridPanel, TELA_CADASTRO_PESSOA);
		card.addLayoutComponent(gridPanel2, TELA_CADASTRO_SUPER);
		
		add(panel);
		add(telaLoginSistema);
		add(gridPanel);
		add(gridPanel2);
		
		telaLoginSistema.getBtnCadastrar().addActionListener(ActionEvent->
		{
			exibirTela(TELA_CADASTRO_PESSOA);
			telaCadastro_Pessoa.limparCampos();
		});
		
		Botao btn = new Botao("Cancelar", Color.RED.brighter());
		btn.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		btn.setForeground(Color.WHITE);
		
		btn.setPreferredSize(new Dimension(100, 40));
		
		btn.addActionListener(ActionEvent->{
			exibirTela(TELA_LOGIN);
			telaCadastro_Pessoa.limparCampos();
			});
		telaCadastro_Pessoa.getTelaCadastroEdicao().add(btn);
		
		Botao btn1 = telaCadastro_Pessoa.getBtCadastrarComoSuper();
		
		btn1.addActionListener(ActionEvent->{
			exibirTela(TELA_CADASTRO_SUPER);
			});
		
		Botao btn2 = tela_CadastroSuperUsuario.getBtCancelar();
		
		btn2.addActionListener(ActionEvent->{
			exibirTela(TELA_CADASTRO_PESSOA);
			});
		
		exibirTela(TELA_LOGIN);
	}
	
	public void exibirTela(String o){
		card.show(this, o);
		atual = o;
	}

	public TelaMenu getTelaMenu() {
		return telaMenu;
	}

	public JDesktopPane getjDesktopPane() {
		return jDesktopPane;
	}

	public TelaLoginSistema getTelaLoginSistema() {
		return telaLoginSistema;
	}

	public TelaCadastro_Pessoa getTelaCadastro_Pessoa() {
		return telaCadastro_Pessoa;
	}
	
	public Tela_CadastroSuperUsuario getTela_CadastroSuperUsuario() {
		return tela_CadastroSuperUsuario;
	}

	public String getTelaAtual() {
		return atual;
	}
}
