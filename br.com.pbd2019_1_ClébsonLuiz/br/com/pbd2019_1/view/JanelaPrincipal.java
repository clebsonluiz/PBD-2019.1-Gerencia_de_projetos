package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TelaPrincipal telaPrincipal;
	
	public JanelaPrincipal() {
		super("Principal");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(800, 500));
		setPreferredSize(new Dimension(800, 500));
		setSize(new Dimension(800, 500));
		
		setLocationRelativeTo(null);
		
		telaPrincipal = new TelaPrincipal();
		add(telaPrincipal, BorderLayout.CENTER);
		
		
		telaPrincipal.getTelaLoginSistema().getBtnCadastrar()
			.addActionListener(	ActionEvent->{
				telaPrincipal.exibirTela(TelaPrincipal.TELA_CADASTRO_PESSOA);
				});
	}

	public TelaPrincipal getTelaPrincipal() {
		return telaPrincipal;
	}

}
