package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
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
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.view.MeuJDialog;

public class Ctrl_JTable {

	private Controlador_Principal ctrl_P;
	
	public Ctrl_JTable(Controlador_Principal controlador_Principal) {
		this.ctrl_P = controlador_Principal;
	}
	
	public void addListener()
	{
		
	}
	
	/**
	 * Eventos de Mouse das Tabelas mais importantes
	 * 
	 * */
	private void adicionarMouseEventJTable(JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.rowAtPoint(e.getPoint());
				int coluna = table.columnAtPoint(e.getPoint());
				
				table.setRowSelectionInterval(linha, linha);
				table.setColumnSelectionInterval(coluna, coluna);
				
				Object obj = table.getValueAt(linha, coluna);
				
				if(SwingUtilities.isRightMouseButton(e)) 
				{
					if(table.getModel() instanceof TCaracteristicaExtra) 
					{
						Controlador_Principal.getPopUpCaracteristica().setjTableAtual(table);
						Controlador_Principal.getPopUpCaracteristica().show(table, e.getX(), e.getY());
					} 
					else 
//						if(!(table.getModel() instanceof TLogUpdate))
					{
						Controlador_Principal.getPopUp().setjTableAtual(table);
						Controlador_Principal.getPopUp().show(table, e.getX(), e.getY());
					}
				} 
				else 
				{
					if(table.getModel() instanceof TTarefa) 
					{
						if(coluna == 3) 
						{
							if(obj instanceof Boolean)
							{
								try 
								{
									if(ctrl_P.isBool_Colaborador_Ativado())
									{
										/*if(!colaborador_Atual.getPrivilegio().equals("Visitante"))
										{
											boolean b = (((Boolean)obj).booleanValue());
											tTarefa.setValueAt(b, linha, coluna);
											Fachada.getInstance().atualizar(tTarefa.getValor(linha));
											
											etapa_Atual.setPorcentagem_andamento(
													Fachada.getInstance()
														.getBoEtapa()
														.recalcularPorcentagem(etapa_Atual)
													);
											
											jInternal_TelaInfoEtapa
												.getTelaEtapa_Tarefas()
												.getTelaEtapa()
												.getBarraProgressBar()
												.setValue(
														Math.round(etapa_Atual.getPorcentagem_andamento())
														);
											
										}
										else*/
										{
											boolean b = (((Boolean)obj).booleanValue());
											ctrl_P.gettTarefa().setValueAt(!b, linha, coluna);
										}
									}
									else 
									{
										boolean b = (((Boolean)obj).booleanValue());
										System.out.println(((Boolean)obj).booleanValue());
										System.out.println(b);
										ctrl_P.gettTarefa().setValueAt(b, linha, coluna);
										Fachada.getInstance().atualizar(ctrl_P.gettTarefa().getValor(linha));
										
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
								} 
								catch (ValidacaoException ve) 
								{
									MeuJDialog.exibirAlertaErro(null, "Erro ao alterar", ve.getMessage());
								}
							}
						}
						else if (coluna == 4)
						{
							try 
							{
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoTarefa(
											ctrl_P.gettTarefa().getValor(linha)
											);
							}
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela", e1.getMessage());
							} 
							catch (ValidacaoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao carregar informações para tela", e1.getMessage());
							}
						}
					} 
					else if (table.getModel() instanceof TEtapa)
					{
						if(coluna == 2) 
						{
							try 
							{
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoEtapa(
											ctrl_P.gettEtapa().getValor(linha)
											);
							} 
							catch (ValidacaoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao consultar dados da etapa", e1.getMessage());
							}
							catch (PropertyVetoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoEtapa", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TProjeto)
					{
						if(coluna == 3)
						{
							try
							{
								//TODO - MUDAR AQUI
								
								ctrl_P.setBool_Colaborador_Ativado(false);
								
								ctrl_P.setProjeto_Atual(
										ctrl_P.gettProjeto().getValor(linha)
										);
								
								Projeto projeto_Atual = ctrl_P.getProjeto_Atual();
								
								if(!ctrl_P.getType_User_Logado().equals(Pessoa.COMUM_USER))
								{
									ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoProjetoEtapa(projeto_Atual);
								}
								else
								{
									ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoProjetoEtapaSimples(projeto_Atual);
								}
							} 
							catch (ValidacaoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao consultar dados do projeto", e1.getMessage());
							} 
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoProjeto", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TColaboracoes)
					{
						if(coluna == 3)
						{
							try 
							{
								//TODO - MUDAR AQUI
								ctrl_P.setBool_Colaborador_Ativado(true);
								
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoColaboracoes(ctrl_P.gettColaboracoes().getValor(linha));
								
							} 
							catch (ValidacaoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao carregar info. do projeto de colaborador", e1.getMessage());
							} 
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoProjeto de Colaborador", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TColaborador)
					{
						if(coluna == 3) 
						{
							try 
							{
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoColaborador(
											ctrl_P.gettColaborador().getValor(linha)
											);
							} 
							catch (ValidacaoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao carregar informações do colaborador", e1.getMessage());
							} 
							catch (PropertyVetoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoPessoa do colaborador", e1.getMessage());
							}
							
						}
					}
					else if (table.getModel() instanceof TLogUpdate)
					{
						if(coluna == 5)
						{
							try 
							{
								ctrl_P.setLogUpdate_Atual(
										ctrl_P.gettLogUpdate().getValor(linha)
										); 
								
								LogUpdate logUpdate_Atual = ctrl_P.getLogUpdate_Atual();
								
								ctrl_P.getCtrl_PreenchementoTela().exibirJInternalTabelaLog(logUpdate_Atual);
								
								
							} 
							catch (PropertyVetoException e1) {
								MeuJDialog.exibirAlertaErro(null, "Erro Ao Exibir Tela de Log", e1.getMessage());
							}
							
						}
					}
					else if (table.getModel() instanceof TPessoa)
					{
						if(coluna == 3)
						{
							//TODO - Abrir tela ver Pessoa outrem 
							try
							{
								Pessoa pessoa = ctrl_P.gettPessoa().getValor(linha);
								
								if(ctrl_P.getType_User_Logado().equals(Pessoa.SUPER_USER))
									ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoPessoaOutrem(pessoa);
								else
									ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoPessoaOutremSimples(pessoa);
							} 
							catch (ValidacaoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao carregar dados de pessoa", e1.getMessage());
							}
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoTarefa", e1.getMessage());
							}
						}
					}
				}
			}
		});
	}
	
	/**
	 * Eventos do Popup<br>
	 * TODO
	 * */
	private void adicionarEventosPopUp() 
	{
		JMenuItem excluir = Controlador_Principal.getPopUp().getMenuItens()[1];
		
		excluir.addActionListener(ActionEvent->{
			
			int decisao = MeuJDialog.exibirAlertaPergunta(null, "Opa!", "Tem a Certeza de que quer remover?");
			
			if(decisao == 1)
			try 
			{
				if(ctrl_P.isBool_Colaborador_Ativado())
					/*if(!colaborador_Atual.getPrivilegio().equals("Administrador")
							&& (!(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TColaboracoes)
									&& !(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TProjeto)))*/
						throw new ValidacaoException("Não tem permição");
					
				int linha = Controlador_Principal.getPopUp().getjTableAtual().getSelectedRow();
				
				if(Controlador_Principal.getPopUp().getjTableAtual().getModel() instanceof TTarefa) 
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
				if(ctrl_P.isBool_Colaborador_Ativado())
//					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
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
				if(ctrl_P.isBool_Colaborador_Ativado())
//					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
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
	
	
	private void adicionarEventosComboBox(JTable tarefaTable, JTable colaboradorTable) 
	{
		ctrl_P.gettTarefa().getCombo().addActionListener(ActionEvent->{
			
			if(ctrl_P.gettTarefa().getCombo().getSelectedItem() != null) 
			{
				int linha = tarefaTable.getSelectedRow();
				int coluna = tarefaTable.getSelectedColumn();
				
				if(linha >= 0 && coluna >= 0) 
				{ 
					try 
					{
						if(ctrl_P.isBool_Colaborador_Ativado())
//							if(colaborador_Atual.getPrivilegio().equals("Visitante"))
								throw new ValidacaoException("Não tem permição");
						
						ctrl_P.gettTarefa().setValueAt(ctrl_P.gettTarefa().getCombo().getSelectedItem(),
								linha,
								coluna
								);
						Fachada.getInstance().atualizar(ctrl_P.gettTarefa().getValor(linha));
					} 
					catch (ValidacaoException e) 
					{
						MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar info. tarefa", e.getMessage());
					}
				}
			}
		});
		
		/*tColaborador.getCombo().addActionListener(ActionEvent->{
			
			if(tColaborador.getCombo().getSelectedItem() != null) 
			{
				int linha = colaboradorTable.getSelectedRow();
				int coluna = colaboradorTable.getSelectedColumn();
				
				if(linha >= 0 && coluna >= 0) 
				{ 
					try 
					{
						tColaborador.setValueAt(tColaborador.getCombo().getSelectedItem(),
								linha,
								coluna
								);
						Fachada.getInstance().atualizar(tColaborador.getValor(linha));
					} 
					catch (ValidacaoException e) 
					{
						MeuJDialog.exibirAlertaErro(null, "Erro ao exibir atualizar info Colaborador", e.getMessage());
					}
				}
			}
		});*/
	}
	
	
	
}
