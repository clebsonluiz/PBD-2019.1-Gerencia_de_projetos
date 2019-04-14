package br.com.pbd2019_1.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TelaPrincipal telaPrincipal;
	
	public JanelaPrincipal() {
		super("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		telaPrincipal = new TelaPrincipal();
		add(telaPrincipal, BorderLayout.CENTER);
		
		
		telaPrincipal.getTelaLoginSistema().getBtnCadastrar()
			.addActionListener(	ActionEvent->{
				telaPrincipal.exibirTela(TelaPrincipal.TELA_CADASTRO_PESSOA);
				});
		telaPrincipal.getTelaMenu().getTelaOpcoesAdmin().getBtnSair()
		.addActionListener(
				ActionEvent->{telaPrincipal.exibirTela(TelaPrincipal.TELA_LOGIN);}
				);
		telaPrincipal.getTelaMenu().getTelaOpcoesComum().getBtnSair()
		.addActionListener(
				ActionEvent->{telaPrincipal.exibirTela(TelaPrincipal.TELA_LOGIN);}
				);
		telaPrincipal.getTelaMenu().getTelaOpcoesSuper().getBtnSair()
		.addActionListener(
				ActionEvent->{telaPrincipal.exibirTela(TelaPrincipal.TELA_LOGIN);}
				);
		
	}

	public TelaPrincipal getTelaPrincipal() {
		return telaPrincipal;
	}

}
