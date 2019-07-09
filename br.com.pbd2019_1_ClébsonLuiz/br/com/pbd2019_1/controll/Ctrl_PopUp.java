package br.com.pbd2019_1.controll;

import java.util.List;

import javax.swing.JMenuItem;

import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TBackup;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TLogUpdate;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TSubEtapa;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.view.MeuJDialog;

public class Ctrl_PopUp{

	private Controlador_Principal ctrl_P;

	public Ctrl_PopUp(Controlador_Principal ctrl_P) {
		this.ctrl_P = ctrl_P;
	}

	/**
	 * Eventos do Popup<br>
	 * */
	public void adicionarEventos() 
	{
		JMenuItem excluir = Controlador_Principal.getPopUp().getMenuItens()[1];
		
		excluir.addActionListener(ActionEvent->{
			
			int decisao = MeuJDialog.exibirAlertaPergunta(null, "Opa!", "Tem a Certeza de que quer remover?");
			
			if(decisao == 1)
			try 
			{
				int linha = Controlador_Principal.getPopUp().getjTableAtual().getSelectedRow();
				if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TTarefa) 
				{
					
					SubTarefa t = ctrl_P.gettSubTarefa().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettSubTarefa().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
					
					SubEtapa sub_etapa_Atual = ctrl_P.getSubEtapa_Atual();
					
					sub_etapa_Atual.setPorcentagem(
							Fachada.getInstance()
								.getBoSubEtapa()
								.recalcularPorcentagem(sub_etapa_Atual)
							);
					
					ctrl_P.getjInternal_TelaInfoSubEtapa()
					.getTelaInfoSubEtapaSubTarefas()
					.getTelaInfoSubEtapa()
					.getBarraProgressBar()
					.setValue(
							Math.round(sub_etapa_Atual.getPorcentagem())
							);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TSubEtapa) 
				{
					SubEtapa t = ctrl_P.gettSubEtapa().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettSubEtapa().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				} 
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TTarefa) 
				{
					
					Tarefa t = ctrl_P.gettTarefa().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettTarefa().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
					
					Etapa etapa_Atual = ctrl_P.getEtapa_Atual();
					
					etapa_Atual.setPorcentagem(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(etapa_Atual)
							);
					
					ctrl_P.getjInternal_TelaInfoEtapa()
					.getTelaEtapa_Tarefas()
					.getTelaEtapa()
					.getBarraProgressBar()
					.setValue(
							Math.round(etapa_Atual.getPorcentagem())
							);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TEtapa) 
				{
					Etapa t = ctrl_P.gettEtapa().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettEtapa().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TProjeto) 
				{
					Projeto t = ctrl_P.gettProjeto().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettProjeto().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					CaracteristicaExtra t = ctrl_P.gettCaracteristicaExtra().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettCaracteristicaExtra().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TColaboracoes) 
				{
					
					Colaborador t = ctrl_P.gettColaboracoes().getValor(linha);
					
					Fachada.getInstance().remover(t);
					ctrl_P.gettColaboracoes().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TColaborador) 
				{
					
					Colaborador t = ctrl_P.gettColaborador().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettColaborador().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TLogUpdate) 
				{
					
					LogUpdate t = ctrl_P.gettLogUpdate().getValor(linha);
					
					Fachada.getInstance().remover(t);
					ctrl_P.gettLogUpdate().remover(linha);
					
/*					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, pessoa_Logada, log);
					tLogUpdate.addValor(log);*/
					
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TPessoa) 
				{
					
					Pessoa t = ctrl_P.gettPessoa().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettPessoa().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TBackup) 
				{
					
					Backup t = ctrl_P.gettBackup().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettBackup().remover(linha);
					
					/*LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, pessoa_Logada, log);
					tLogUpdate.addValor(log);*/
				}
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro na remoção", e.getMessage());
			}
		});
		
		JMenuItem excluirCaracteristica = Controlador_Principal.getPopUpCaracteristica().getMenuItens()[1];
		
		
		excluirCaracteristica.addActionListener(ActionEvent->
		{
			try 
			{
				int linha = Controlador_Principal.getPopUpCaracteristica().getjTableAtual().getSelectedRow();
				
				if(Controlador_Principal.getPopUpCaracteristica().getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					CaracteristicaExtra t = ctrl_P.gettCaracteristicaExtra().getValor(linha);
					Fachada.getInstance().remover(t);
					ctrl_P.gettCaracteristicaExtra().remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro na remoção", e.getMessage());
			}
		});
		
		JMenuItem salvarCaracteristica = Controlador_Principal.getPopUpCaracteristica().getMenuItens()[0];
		
		salvarCaracteristica.addActionListener((ItemEvent)->
		{
			try 
			{
				int linha = Controlador_Principal.getPopUpCaracteristica().getjTableAtual().getSelectedRow();
				
				if(Controlador_Principal.getPopUpCaracteristica().getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					Object value = Controlador_Principal.getPopUpCaracteristica().getjTableAtual().getValueAt(linha, 0);
					
					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.gettCaracteristicaExtra().getValor(linha));
					
					ctrl_P.gettCaracteristicaExtra().setValueAt(value, linha, 0);
					
					CaracteristicaExtra t = ctrl_P.gettCaracteristicaExtra().getValor(linha);
					
					Fachada.getInstance().atualizar(t);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(antes, t, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro na alteração", e.getMessage());
			}
		});
		
	}
	
	
	
}
