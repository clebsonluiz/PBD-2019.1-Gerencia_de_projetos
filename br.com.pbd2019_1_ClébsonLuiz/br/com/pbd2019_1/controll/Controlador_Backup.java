package br.com.pbd2019_1.controll;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import br.com.pbd2019_1.dao.DAOConfigDefault;
import br.com.pbd2019_1.dao.DAOResBackup;
import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.entidade.ConfigDefault;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TBackup;
import br.com.pbd2019_1.view.Botao;
import br.com.pbd2019_1.view.Horario;
import br.com.pbd2019_1.view.JInternal_Backup_Efetuando;
import br.com.pbd2019_1.view.JInternal_TelaAgendarBackup;
import br.com.pbd2019_1.view.JInternal_TelaBackups;
import br.com.pbd2019_1.view.JanelaPrincipal;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.MeuJFileChooser;

public class Controlador_Backup implements Observer, ActionListener{

	
	private Controlador_Principal controlador_Principal;
	private JInternal_TelaBackups jInternal_TelaBackups;
	private TBackup tBackup;
	
	
	
	public Controlador_Backup(Controlador_Principal controlador_Principal) {
		this.controlador_Principal = controlador_Principal;
	}

	public void addEventJInternal()
	{
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaAgendarBackup());
	}
	
	private void adicionarEventoJInternal(JInternal_TelaAgendarBackup jInternal_TelaAgendarBackup)
	{
		jInternal_TelaAgendarBackup.getBtDefinirHorario()
		.addActionListener(ActionEvent->
		{
			Horario horario = controlador_Principal.getjInternal_TelaAgendarBackup().getHorario();
			DAOConfigDefault.setHorarioAgenda(horario.toString());
		});
		
		jInternal_TelaAgendarBackup.getBtResetarHorario()
		.addActionListener(ActionEvent->
		{
			Horario horario = controlador_Principal.getjInternal_TelaAgendarBackup().getHorario();
			
			DAOConfigDefault.setHorarioAgenda(null);
			horario.setLocalTime("00", "00", "00");
		});
	}
	
	public void adicionarEventoBotoes(JInternal_Backup_Efetuando jInternal)
	{
		/*Se o evento for para o backup normal */
		jInternal.getBtnOK_Event_Backup_Normal().addActionListener(ActionEvent->{
			jInternal.fechar();
		});
		/*Se o evento foi disparado ao usuario deslogar */
		jInternal.getBtnOK_Event_Deslogar().addActionListener(ActionEvent->{
			try 
			{
				jInternal.fechar();
				controlador_Principal.sair();
			} 
			catch (PropertyVetoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao sair do Sistema", e.getMessage());
			}
		});
		/*Se o evento foi disparado ao fechar a Janela do Sistema*/
		jInternal.getBtnOK_Event_Fechar().addActionListener(ActionEvent->{
			jInternal.fechar();
			System.exit(0);
		});
	}
	
	public void adicionarEvento(JFrame frame)
	{
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) 
			{
				int op = MeuJDialog.exibirAlertaPergunta(null, "?", "Tem certeza de que quer encerrar a aplicação?");
				if(op != 0)
				{
					if(!((JanelaPrincipal)frame).getTelaPrincipal().getTelaAtual().equals("2"))
						System.exit(0);
					else
						try 
					{
							if(Fachada.getInstance().getBoBackup().isBackupNecessario())
							{
								efetuarBackup(frame, frame);
							}
							else 
							{
								System.exit(0);
							}
					}
					catch (ValidacaoException | ClassNotFoundException | IOException e1) 
					{
						MeuJDialog.exibirAlertaErro(null, "ERRO AO FAZER BACKUP", e1.getMessage());
					}
				}
			}
		});
	}
	
	public void adicionarEvento(JInternal_TelaBackups telaBackups, TBackup tBackup)
	{
		DAOResBackup.getInstance().addObserver(this);
		this.jInternal_TelaBackups = telaBackups;
		this.tBackup = tBackup;
		adicionarEventoBotoes(JInternal_Backup_Efetuando.getInstance());
		
		telaBackups.getTelaBackups().getTelaPesquisaData().getBtnBuscar()
		.addActionListener(ActionEvent->{
			int indexCombo = telaBackups.getTelaBackups().getTelaPesquisaData().getComboBox().getSelectedIndex();
			int indexComboOrdem = telaBackups.getTelaBackups().getTelaPesquisaData().getComboBoxOrdem().getSelectedIndex();
			Date data1 = telaBackups.getTelaBackups().getTelaPesquisaData().getData1().getDate();
			Date data2 = telaBackups.getTelaBackups().getTelaPesquisaData().getData2().getDate();
			try
			{
				List<Backup> backups;
				switch (indexCombo) 
				{
				case 0:
					if(indexComboOrdem == 0)
					{
						backups = Fachada.getInstance().getBoBackup().buscarAllRecentes();
						tBackup.addAll(backups);
					}
					else
					{
						backups = Fachada.getInstance().getBoBackup().buscarAllAntigos();
						tBackup.addAll(backups);
					}
					break;
				case 1:
					if(indexComboOrdem == 0)
					{
						backups = Fachada.getInstance().getBoBackup().buscarAllRecentesPeriodo(data1, data2);
						tBackup.addAll(backups);
					}
					else
					{
						backups = Fachada.getInstance().getBoBackup().buscarAllAntigosPeriodo(data1, data2);
						tBackup.addAll(backups);
					}
					break;
				}
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao consultar Lista de BACKUPS", e.getMessage());
			}
		});
		
		telaBackups.getTelaBackups().getBtnNovoBackup()
		.addActionListener(this);
		
		telaBackups.getTelaBackups().getBtnAgendarBackup()
		.addActionListener(ActionEvent->
		{
			try
			{
				ConfigDefault config = DAOConfigDefault.loadConfig();
				Horario horario = controlador_Principal.getjInternal_TelaAgendarBackup().getHorario();
				
				if(config != null && config.getHora_bakup() != null && config.getHora_bakup().length() == 8)
				{
					String[] hora = config.getHora_bakup().split(":");
					horario.setLocalTime(hora[0], hora[1] , hora[2]);
				}
				else
				{
					horario.setLocalTime("00", "00", "00");
				}
				
				controlador_Principal.getjInternal_TelaAgendarBackup().queroFoco();
			}
			catch (PropertyVetoException | ClassNotFoundException | IOException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
			}
		});
	}



	@Override
	public void update(Observable o, Object obj) {
		if(obj != null) 
		{
			JInternal_Backup_Efetuando.getInstance().adicionarConteudo((String) obj);
		}
		else 
		{
			JInternal_Backup_Efetuando.getInstance().exibirBotao();
		}
	}

	
	private void efetuarBackup(Component c, Object o) 
	{
		int retorno = MeuJFileChooser.getInstance().exibirParaBackup(c);
		if (retorno == JFileChooser.APPROVE_OPTION)
		{
			try {
				if(o instanceof JInternal_TelaBackups)
				{
					JInternal_Backup_Efetuando.chamar_Evento_Backup_Normal();
				}
				else if (o instanceof Botao)
				{
					JInternal_Backup_Efetuando.chamar_Evento_Deslogamento();
				}
				else if (o instanceof JFrame)
				{
					JInternal_Backup_Efetuando.chamar_Evento_Fechamento();
				}
			} 
			catch (PropertyVetoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Exibir tela do Backup efetuado", e.getMessage());
			}
			
			String arquivoNome = MeuJFileChooser.getInstance().getSelectedFile().getName();
			String arquivoPathParent = MeuJFileChooser.getInstance().getSelectedFile().getParent();
			String arquivoPathAbsolute = MeuJFileChooser.getInstance().getSelectedFile().getAbsolutePath();
			
			Backup b = new Backup();
			b.setAutor_backup(controlador_Principal.getPessoa_Logada().getCpf());
			b.setData_backup(LocalDateTime.now());
			b.setLocal_backup(arquivoPathAbsolute);
			
			try 
			{
				
				DAOResBackup.getInstance().executarOperacaoBackup(arquivoPathParent, arquivoNome, "postgres", "13111996", DAOResBackup.BACKUP);
				b.setStatus_backup(Backup.EFETUADO);
				Fachada.getInstance().inserir(b);
				tBackup.addValor(b);
			}
			catch (ValidacaoException e) 
			{
				e.printStackTrace();
				try 
				{
					b.setStatus_backup(Backup.ERRO);
					Fachada.getInstance().inserir(b);
					tBackup.addValor(b);
				} 
				catch (ValidacaoException e1) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
				}
				
				MeuJDialog.exibirAlertaErro(null, "ERRO AO FAZER BACKUP", e.getMessage());
			}
		}
		else
		{
			if (o instanceof Botao)
			{
				try 
				{
					controlador_Principal.sair();
				}
				catch (PropertyVetoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao deslogar", e.getMessage());
				}
			}
			else if (o instanceof JFrame)
			{
				System.exit(0);
			}
		}
	}
	


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() instanceof Botao) 
		{
			Botao botao = (Botao) e.getSource();
			
			/*Se o botão for o da tela backups*/
			if(botao.getName().equals("novo")) 
			{
				efetuarBackup(jInternal_TelaBackups, jInternal_TelaBackups);
			}
			/*Se o botão for o de sair na TelaOpçoes*/
			else if (botao.getName().equals("sair"))
			{
				//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado) 
				//{
					try 
					{
						if(Fachada.getInstance().getBoBackup().isBackupNecessario())
						{
							efetuarBackup(null, botao);
						}
						else 
						{
							try 
							{
								controlador_Principal.sair();
							} 
							catch (PropertyVetoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao sair do Sistema", e1.getMessage());
							}
						}
					} 
					catch (DAOException | ClassNotFoundException | IOException e1)
					{
						MeuJDialog.exibirAlertaErro(null, "ERRO AO FAZER BACKUP", e1.getMessage());
					}
				//}
			}
		}
	}
	
}
