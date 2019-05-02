package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

public class JInternal_Backup_Efetuando extends JInternalAbstract {

	private static final long serialVersionUID = 1L;


	public static final int EVENTO_BACKUP_NORMAL = 1;
	public static final int EVENTO_DESLOGAMENTO = 2;
	public static final int EVENTO_FECHAMENTO = 3;
	public static boolean isAtivado = false;
	
	private static JInternal_Backup_Efetuando instance;
	
	public static JInternal_Backup_Efetuando getInstance()
	{
		if(instance == null)
			instance = new JInternal_Backup_Efetuando();
		return instance;
	}
	
	private TelaEfetuandoBackup telaEfetuandoBackup;
	private static int evento = 0;
	
	private JInternal_Backup_Efetuando() {
		super("Backup");
		setIconifiable(false);
		setMaximizable(false);
		setClosable(false);
		
		telaEfetuandoBackup = new TelaEfetuandoBackup();
		
		setPreferredSize(telaEfetuandoBackup.getPreferredSize());
		setSize(telaEfetuandoBackup.getPreferredSize());

		add(telaEfetuandoBackup, BorderLayout.CENTER);
	}

	public void ocultarTodosBotoes() {telaEfetuandoBackup.ocultarTodosBotoes();}
	
	public Botao getBtnOK_Event_Backup_Normal() 
	{
		return telaEfetuandoBackup.getBtnOK_Event_Backup_Normal();
	}
	public Botao getBtnOK_Event_Deslogar() 
	{
		return telaEfetuandoBackup.getBtnOK_Event_Deslogar();
	}
	public Botao getBtnOK_Event_Fechar()
	{
		return telaEfetuandoBackup.getBtnOK_Event_Fechar();
	}
	
	public static void chamar_Evento_Backup_Normal() throws PropertyVetoException 
	{
		getInstance().telaEfetuandoBackup.limparCampo();
		isAtivado = true;
		evento = EVENTO_BACKUP_NORMAL;
		getInstance().queroFoco();
	}
	
	public static void chamar_Evento_Deslogamento() throws PropertyVetoException 
	{
		getInstance().telaEfetuandoBackup.limparCampo();
		isAtivado = true;
		evento = EVENTO_DESLOGAMENTO;
		getInstance().queroFoco();
	}
	
	public static void chamar_Evento_Fechamento() throws PropertyVetoException 
	{
		getInstance().telaEfetuandoBackup.limparCampo();
		isAtivado = true;
		evento = EVENTO_FECHAMENTO;
		getInstance().queroFoco();
	}
	
	public void exibirBotao() 
	{
		switch (evento) 
		{
		case EVENTO_BACKUP_NORMAL:
			telaEfetuandoBackup.exibir_BtnOK_Event_Backup_Normal();
			break;
		case EVENTO_DESLOGAMENTO:
			telaEfetuandoBackup.exibir_BtnOK_Event_Deslogar();
			break;
		case EVENTO_FECHAMENTO:
			telaEfetuandoBackup.exibir_BtnOK_Event_Fechar();
			break;
		default:
			break;
		}
	}
	
	
	public void limparCampo() {telaEfetuandoBackup.limparCampo();}
	public void adicionarConteudo(String msg) {telaEfetuandoBackup.adicionarConteudo(msg);}

	@Override
	public void fechar() {
		isAtivado = false;
		setVisible(false);
	}

}
