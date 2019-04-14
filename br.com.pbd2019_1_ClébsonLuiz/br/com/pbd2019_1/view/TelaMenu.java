package br.com.pbd2019_1.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class TelaMenu extends JPanel{

	
	public static final String USER_COMUM = "1";
	public static final String USER_ADMIN = "2";
	public static final String USER_SUPER = "3";
	
	private static final long serialVersionUID = 1L;
	
	private TelaOpcoes telaOpcoesComum;
	private TelaOpcoes telaOpcoesAdmin;
	private TelaOpcoes telaOpcoesSuper;
	
	private CardLayout cardLayout;
	
	public TelaMenu() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		telaOpcoesComum = new TelaOpcoes();
		telaOpcoesAdmin = new TelaOpcoes();
		telaOpcoesSuper = new TelaOpcoes();
		
		telaOpcoesComum.user_comum();
		telaOpcoesAdmin.user_admin();
		telaOpcoesSuper.user_super();
		
		cardLayout.addLayoutComponent(telaOpcoesComum, USER_COMUM);
		cardLayout.addLayoutComponent(telaOpcoesAdmin, USER_ADMIN);
		cardLayout.addLayoutComponent(telaOpcoesSuper, USER_SUPER);
		
		setMinimumSize(telaOpcoesComum.getMinimumSize());
		setPreferredSize(telaOpcoesComum.getPreferredSize());
		
		add(telaOpcoesComum);
		add(telaOpcoesAdmin);
		add(telaOpcoesSuper);
	
		exibirTelaOpcoes(USER_COMUM);
	}
	
	public void exibirTelaOpcoes(String o) {
		cardLayout.show(this, o);
	}
	
	public TelaOpcoes getTelaOpcoesComum() {return telaOpcoesComum;}
	public TelaOpcoes getTelaOpcoesAdmin() {return telaOpcoesAdmin;}
	public TelaOpcoes getTelaOpcoesSuper() {return telaOpcoesSuper;}
	
}
