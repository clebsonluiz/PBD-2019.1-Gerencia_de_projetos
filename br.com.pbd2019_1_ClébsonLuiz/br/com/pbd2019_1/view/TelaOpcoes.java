package br.com.pbd2019_1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import br.com.pbd2019_1.utils.ViewUtil;
import javax.swing.SwingConstants;

public class TelaOpcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private Botao btnInfo;
	private Botao btnPessoas;
	private Botao btnSQL;
	private Botao btnLog;
	private Botao btnBackup;
	private Botao btnSobre;
	private Botao btnSair;

	public TelaOpcoes() {
		setLayout(new GridLayout(7, 1, 5, 0));
		setMinimumSize(new Dimension(160, 500));
		setPreferredSize(new Dimension(160, 500));
		setBackground(Color.DARK_GRAY);
		
		btnInfo = new Botao("My Info");
		btnPessoas = new Botao("Pessoas");
		btnSQL = new Botao("Query SQL");
		btnLog = new Botao("Log");
		btnBackup = new Botao("Backup");
		btnSobre = new Botao("Sobre");
		btnSair = new Botao("Sair");
		btnSair.setName("sair");
		
		btnInfo.setIcon(ViewUtil.Icones.ICONE_USER);
		btnPessoas.setIcon(ViewUtil.Icones.ICONE_PEOPLE);
		btnSQL.setIcon(ViewUtil.Icones.ICONE_SQL);
		btnLog.setIcon(ViewUtil.Icones.ICONE_LOG);
		btnBackup.setIcon(ViewUtil.Icones.ICONE_BACKUP);
		btnSobre.setIcon(ViewUtil.Icones.ICONE_SOBRE);
		btnSair.setIcon(ViewUtil.Icones.ICONE_LOGOUT);
		
		btnInfo.setHorizontalAlignment(SwingConstants.LEFT);
		btnPessoas.setHorizontalAlignment(SwingConstants.LEFT);
		btnSQL.setHorizontalAlignment(SwingConstants.LEFT);
		btnLog.setHorizontalAlignment(SwingConstants.LEFT);
		btnBackup.setHorizontalAlignment(SwingConstants.LEFT);
		btnSobre.setHorizontalAlignment(SwingConstants.LEFT);
		btnSair.setHorizontalAlignment(SwingConstants.LEFT);

		/*btnInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPessoas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSQL.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLog.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBackup.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSobre.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSair.setHorizontalTextPosition(SwingConstants.RIGHT);*/
		
//		user_comum();
//		user_admin();
//		user_super();
	}
	
	public void user_comum() {
		add(btnInfo);
		add(btnBackup);
		add(btnSobre);
		add(btnSair);
	}
	
	public void user_admin() {
		add(btnInfo);
		add(btnPessoas);
		add(btnLog);
		add(btnBackup);
		add(btnSobre);
		add(btnSair);
	}
	
	public void user_super() {
		add(btnInfo);
		add(btnPessoas);
		add(btnSQL);
		add(btnLog);
		add(btnBackup);
		add(btnSobre);
		add(btnSair);
	}

	public Botao getBtnInfo() {
		return btnInfo;
	}

	public Botao getBtnPessoas() {
		return btnPessoas;
	}

	public Botao getBtnSQL() {
		return btnSQL;
	}

	public Botao getBtnLog() {
		return btnLog;
	}
	
	public Botao getBtnBackup() {
		return btnBackup;
	}

	public Botao getBtnSobre() {
		return btnSobre;
	}

	public Botao getBtnSair() {
		return btnSair;
	}
	
}
