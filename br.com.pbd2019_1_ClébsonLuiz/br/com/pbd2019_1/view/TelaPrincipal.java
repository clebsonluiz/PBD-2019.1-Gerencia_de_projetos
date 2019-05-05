package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

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
	
	private String atual = "1";
	private CardLayout card;
	
	private TelaMenu telaMenu;
	private JDesktopPane jDesktopPane;
	
	private TelaLoginSistema telaLoginSistema;
	private TelaCadastro_Pessoa telaCadastro_Pessoa;
	
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
		
		panel.add(telaMenu, BorderLayout.WEST);
		panel.add(jDesktopPane, BorderLayout.CENTER);
		
		card.addLayoutComponent(panel, TELA_PRINCIPAL);
		card.addLayoutComponent(telaLoginSistema, TELA_LOGIN);
		card.addLayoutComponent(telaCadastro_Pessoa, TELA_CADASTRO_PESSOA);
		
		add(panel);
		add(telaLoginSistema);
		add(telaCadastro_Pessoa);
		
		Botao btn = new Botao("Cancelar", Color.RED.brighter());
		btn.addActionListener(ActionEvent->{
			exibirTela(TELA_LOGIN);
			telaCadastro_Pessoa.limparCampos();
			});
		telaCadastro_Pessoa.getTelaCadastroEdicao().add(btn);
		
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
	
	public String getTelaAtual() {
		return atual;
	}
}
