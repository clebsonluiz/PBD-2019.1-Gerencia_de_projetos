package br.com.pbd2019_1.controll;

import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TBackup;
import br.com.pbd2019_1.view.JInternal_TelaAlerta;
import br.com.pbd2019_1.view.JInternal_TelaBackups;

public class Controlador_Backup {

	
	public void adicionarEvento(JInternal_TelaBackups telaBackups, TBackup tBackup)
	{
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
				JInternal_TelaAlerta.showAlerta("Erro ao consultar Lista de BACKUPS", e.getMessage());
			}
		});
		
		telaBackups.getTelaBackups().getBtnNovoBackup()
		.addActionListener(ActionEvent->{
			int retorno = Controlador_Statics.janelaEscolherCaminhoArquivo.exibirParaBackup(telaBackups);
			if (retorno == JFileChooser.APPROVE_OPTION)
			{
				String arquivoNome = Controlador_Statics.janelaEscolherCaminhoArquivo.getSelectedFile().getName();
				String arquivoPath = Controlador_Statics.janelaEscolherCaminhoArquivo.getSelectedFile().getPath();
				String arquivoPath2 = Controlador_Statics.janelaEscolherCaminhoArquivo.getSelectedFile().getAbsolutePath();
				
				System.out.println("Nome arquivo: "+arquivoNome);
				System.out.println("Nome Path: "+arquivoPath);
				System.out.println("Nome Path2: "+arquivoPath2);
				
				JInternal_TelaAlerta.showAlerta("Erro", "Mensagem de ALERTA");
			}
		});
	}
	
	
}
