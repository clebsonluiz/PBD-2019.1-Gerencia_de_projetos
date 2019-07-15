package br.com.pbd2019_1.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.pbd2019_1.utils.ViewUtil;

public class JIF_Reset_Senha extends JInternalAbstract {

	private static final long serialVersionUID = 1L;
	private JLabel lblSenha;

	public static JIF_Reset_Senha instance;
	
	public static JIF_Reset_Senha getInstance()
	{
		if(instance == null) 
			instance = new JIF_Reset_Senha();
		return instance;
	}
	
	private JIF_Reset_Senha() {
		super("Senha Resetada");
		setResizable(false);
		setIconifiable(false);
		setMaximizable(false);
		setPreferredSize(new Dimension(300, 300));
		setMinimumSize(getPreferredSize());
		setSize(new Dimension(300, 220));
		MeuJPanel meuJPanel = new MeuJPanel();
		setContentPane(meuJPanel);
		meuJPanel.setLayout(null);
		
		JLabel lblSuaSenhaFoi = new JLabel("Sua senha foi resetada");
		lblSuaSenhaFoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuaSenhaFoi.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		lblSuaSenhaFoi.setBounds(6, 6, 276, 30);
		meuJPanel.add(lblSuaSenhaFoi);
		
		JLabel lblSuaNovaSenha = new JLabel("Sua nova senha \u00E9");
		lblSuaNovaSenha.setBounds(6, 48, 276, 30);
		lblSuaNovaSenha.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		meuJPanel.add(lblSuaNovaSenha);
		
		lblSenha = new JLabel("senha");
		lblSenha.setToolTipText("É importante que guarde essa senha para que possa mudar para outra no futuro");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		lblSenha.setBounds(6, 90, 276, 50);
		meuJPanel.add(lblSenha);
		
	}

	@Override
	protected void fechar() {}

	public JLabel getLblSenha() {
		return lblSenha;
	}
	
}
