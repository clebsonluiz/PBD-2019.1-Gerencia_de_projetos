package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

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
import br.com.pbd2019_1.tabelas.TObject;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.view.JInternal_InfoLog;
import br.com.pbd2019_1.view.JInternal_Sobre;
import br.com.pbd2019_1.view.JInternal_TabelaLogs;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
import br.com.pbd2019_1.view.JInternal_TelaAgendarBackup;
import br.com.pbd2019_1.view.JInternal_TelaBackups;
import br.com.pbd2019_1.view.JInternal_TelaCadastroSubEtapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastroSubTarefa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoaOutrem;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoaOutremSimples;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas_Simples;
import br.com.pbd2019_1.view.JInternal_TelaInfoSubEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoSubTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.PopUp;

public class Controlador_JTable {

	private JInternal_TelaCadastro_Etapa jInternal_TelaCadastro_Etapa;
	private JInternal_TelaCadastro_Projeto jInternal_TelaCadastro_Projeto;
	private JInternal_TelaCadastro_Pessoa jInternal_TelaCadastro_Pessoa;
	private JInternal_TelaCadastro_Tarefa jInternal_TelaCadastro_Tarefa;
	private JInternal_TelaCadastroSubEtapa jInternal_TelaCadastroSubEtapa;
	private JInternal_TelaCadastroSubTarefa jInternal_TelaCadastroSubTarefa;
	
	private JInternal_TelaInfoPessoaOutrem jInternal_TelaInfoPessoaOutrem;
	private JInternal_TelaInfoPessoaOutremSimples jInternal_TelaInfoPessoaOutremSimples;
	private JInternal_TelaInfoSubEtapa jInternal_TelaInfoSubEtapa;
	private JInternal_TelaInfoSubTarefa jInternal_TelaInfoSubTarefa;
	private JInternal_TelaInfoEtapa jInternal_TelaInfoEtapa;
	private JInternal_TelaInserirSQL jInternal_TelaInserirSQL;
	private JInternal_TelaInfoPessoa jInternal_TelaInfoPessoa;
	private JInternal_TelaInfoTarefa jInternal_TelaInfoTarefa;
	private JInternal_TelaInfoProjeto_Etapas jInternal_TelaInfoProjeto_Etapas;
	private JInternal_TelaInfoPessoa_Projetos jInternal_TelaInfoPessoa_Projetos;
	private JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoProjeto_Etapas_Simples;
	private JInternal_TabelaPessoas jInternal_TabelaPessoas;
	private JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores;
	private JInternal_TelaBackups jInternal_TelaBackups;
	private JInternal_TabelaLogs jInternal_TabelaLogs;
	private JInternal_InfoLog jInternal_InfoLog;
	private JInternal_TelaAgendarBackup jInternal_TelaAgendarBackup;
	private JInternal_Sobre jInternal_Sobre;
	
	private Controlador_Principal controlador_Principal;
	
	private TCaracteristicaExtra tCaracteristicaExtra;
	private TCaracteristicaExtra tCaracteristicaExtra2;
	private TColaborador tColaborador;
	private TColaboracoes tColaboracoes;
	private TEtapa tEtapa;
	private TLogUpdate tLogUpdate;
	private TPessoa tPessoa;
	private TProjeto tProjeto;
	private TTarefa tTarefa;
	private TObject tObject;
	private TBackup tBackup;

	
	private static PopUp popUpCaracteristica;
	private static PopUp popUp;
	
	
	public Controlador_JTable(Controlador_Principal controlador_Principal) {
		this.controlador_Principal = controlador_Principal;
		popUpCaracteristica = Controlador_Principal.getPopUpCaracteristica();
		popUp = Controlador_Principal.getPopUp();
	}
	
	public void adicionarJInternals() {
		this.jInternal_TelaCadastro_Etapa = controlador_Principal.getjInternal_TelaCadastro_Etapa();
		this.jInternal_TelaCadastro_Projeto = controlador_Principal.getjInternal_TelaCadastro_Projeto();
		this.jInternal_TelaCadastro_Pessoa = controlador_Principal.getjInternal_TelaCadastro_Pessoa();
		this.jInternal_TelaCadastro_Tarefa = controlador_Principal.getjInternal_TelaCadastro_Tarefa();
		this.jInternal_TelaInfoEtapa = controlador_Principal.getjInternal_TelaInfoEtapa();
		this.jInternal_TelaInserirSQL = controlador_Principal.getjInternal_TelaInserirSQL();
		this.jInternal_TelaInfoPessoa = controlador_Principal.getjInternal_TelaInfoPessoa();
		this.jInternal_TelaInfoTarefa = controlador_Principal.getjInternal_TelaInfoTarefa();
		this.jInternal_TelaInfoProjeto_Etapas = controlador_Principal.getjInternal_TelaInfoProjeto_Etapas();
		this.jInternal_TelaInfoPessoa_Projetos = controlador_Principal.getjInternal_TelaInfoPessoa_Projetos();
		this.jInternal_TelaInfoProjeto_Etapas_Simples = controlador_Principal.getjInternal_TelaInfoProjeto_Etapas_Simples();
		this.jInternal_TabelaPessoas = controlador_Principal.getjInternal_TabelaPessoas();
		this.jInternal_TabelaPessoasColaboradores = controlador_Principal.getjInternal_TabelaPessoasColaboradores();
		this.jInternal_TelaBackups = controlador_Principal.getjInternal_TelaBackups();
		this.jInternal_TabelaLogs = controlador_Principal.getjInternal_TabelaLogs();
		this.jInternal_InfoLog = controlador_Principal.getjInternal_InfoLog();
		this.jInternal_TelaAgendarBackup = controlador_Principal.getjInternal_TelaAgendarBackup();
		this.jInternal_Sobre = controlador_Principal.getjInternal_Sobre();
		this.jInternal_TelaCadastroSubEtapa = controlador_Principal.getjInternal_TelaCadastroSubEtapa();
		this.jInternal_TelaCadastroSubTarefa = controlador_Principal.getjInternal_TelaCadastroSubTarefa();
		this.jInternal_TelaInfoPessoaOutrem =  controlador_Principal.getjInternal_TelaInfoPessoaOutrem();
		this.jInternal_TelaInfoPessoaOutremSimples = controlador_Principal.getjInternal_TelaInfoPessoaOutremSimples();
		this.jInternal_TelaInfoSubEtapa = controlador_Principal.getjInternal_TelaInfoSubEtapa();
		this.jInternal_TelaInfoSubTarefa = controlador_Principal.getjInternal_TelaInfoSubTarefa();
	}
	
	public void addTables()
	{
		tCaracteristicaExtra = controlador_Principal.gettCaracteristicaExtra();
		tCaracteristicaExtra2 = controlador_Principal.gettCaracteristicaExtra2();
		tColaborador = controlador_Principal.gettColaborador();
		tColaboracoes = controlador_Principal.gettColaboracoes();
		tEtapa = controlador_Principal.gettEtapa();
		tLogUpdate = controlador_Principal.gettLogUpdate();
		tPessoa = controlador_Principal.gettPessoa();
		tProjeto = controlador_Principal.gettProjeto();
		tTarefa = controlador_Principal.gettTarefa();
		tObject = controlador_Principal.gettObject();
		tBackup = controlador_Principal.gettBackup();
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
						popUpCaracteristica.setjTableAtual(table);
						popUpCaracteristica.show(table, e.getX(), e.getY());
					} 
					else 
//						if(!(table.getModel() instanceof TLogUpdate))
					{
						popUp.setjTableAtual(table);
						popUp.show(table, e.getX(), e.getY());
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
									if(controlador_Principal.isBool_Colaborador_Ativado())
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
											tTarefa.setValueAt(!b, linha, coluna);
										}
									}
									else 
									{
										boolean b = (((Boolean)obj).booleanValue());
										System.out.println(((Boolean)obj).booleanValue());
										System.out.println(b);
										tTarefa.setValueAt(b, linha, coluna);
										Fachada.getInstance().atualizar(tTarefa.getValor(linha));
										
										Etapa etapa_Atual = controlador_Principal.getEtapa_Atual();
										
										etapa_Atual.setPorcentagem(
												Fachada.getInstance()
													.getBoEtapa()
													.recalcularPorcentagem(etapa_Atual)
												);
										
										jInternal_TelaInfoEtapa
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
								controlador_Principal.getCtrl_PreenchementoTela()
									.exibirJInternalInfoTarefa(
											tTarefa.getValor(linha)
											);
							} catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela", e1.getMessage());
							}
						}
					} 
					else if (table.getModel() instanceof TEtapa)
					{
						if(coluna == 2) 
						{
							try 
							{
								controlador_Principal.getCtrl_PreenchementoTela()
									.exibirJInternalInfoEtapa(
											tEtapa.getValor(linha)
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
								controlador_Principal.setBool_Colaborador_Ativado(false);
								
								controlador_Principal.setProjeto_Atual(
										tProjeto.getValor(linha)
										);
								
								Projeto projeto_Atual = controlador_Principal.getProjeto_Atual();
								
								if(!controlador_Principal.getType_User_Logado().equals(Pessoa.COMUM_USER))
								{
									controlador_Principal.getCtrl_PreenchementoTela().exibirJInternalInfoProjetoEtapa(projeto_Atual);
								}
								else
								{
									controlador_Principal.getCtrl_PreenchementoTela().exibirJInternalInfoProjetoEtapaSimples(projeto_Atual);
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
								controlador_Principal.setBool_Colaborador_Ativado(true);
								
								controlador_Principal.getCtrl_PreenchementoTela()
									.exibirJInternalInfoColaboracoes(tColaboracoes.getValor(linha));
								
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
								controlador_Principal.getCtrl_PreenchementoTela()
									.exibirJInternalInfoColaborador(
											tColaborador.getValor(linha)
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
								controlador_Principal.setLogUpdate_Atual(
										tLogUpdate.getValor(linha)
										); 
								
								LogUpdate logUpdate_Atual = controlador_Principal.getLogUpdate_Atual();
								
								controlador_Principal.getCtrl_PreenchementoTela().exibirJInternalTabelaLog(logUpdate_Atual);
								
								
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
								controlador_Principal.
								getCtrl_PreenchementoTela().exibirJInternalInfoPessoa(tPessoa.getValor(linha));
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
		JMenuItem excluir = popUp.getMenuItens()[1];
		
		excluir.addActionListener(ActionEvent->{
			
			int decisao = MeuJDialog.exibirAlertaPergunta(null, "Opa!", "Tem a Certeza de que quer remover?");
			
			if(decisao == 1)
			try 
			{
				if(controlador_Principal.isBool_Colaborador_Ativado())
					/*if(!colaborador_Atual.getPrivilegio().equals("Administrador")
							&& (!(popUp.getjTableAtual().getModel() instanceof TColaboracoes)
									&& !(popUp.getjTableAtual().getModel() instanceof TProjeto)))*/
						throw new ValidacaoException("Não tem permição");
					
				int linha = popUp.getjTableAtual().getSelectedRow();
				
				if(popUp.getjTableAtual().getModel() instanceof TTarefa) 
				{
					
					Tarefa t = tTarefa.getValor(linha);
					Fachada.getInstance().remover(t);
					tTarefa.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
					
					Etapa etapa_Atual = controlador_Principal.getEtapa_Atual();
					
					etapa_Atual.setPorcentagem(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(etapa_Atual)
							);
					
					jInternal_TelaInfoEtapa
					.getTelaEtapa_Tarefas()
					.getTelaEtapa()
					.getBarraProgressBar()
					.setValue(
							Math.round(etapa_Atual.getPorcentagem())
							);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TEtapa) 
				{
					Etapa t = tEtapa.getValor(linha);
					Fachada.getInstance().remover(t);
					tEtapa.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TProjeto) 
				{
					Projeto t = tProjeto.getValor(linha);
					Fachada.getInstance().remover(t);
					tProjeto.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					CaracteristicaExtra t = tCaracteristicaExtra.getValor(linha);
					Fachada.getInstance().remover(t);
					tCaracteristicaExtra.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TColaboracoes) 
				{
					
					Colaborador t = tColaboracoes.getValor(linha);
					
					Fachada.getInstance().remover(t);
					tColaboracoes.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TColaborador) 
				{
					
					Colaborador t = tColaborador.getValor(linha);
					Fachada.getInstance().remover(t);
					tColaborador.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TLogUpdate) 
				{
					
					LogUpdate t = tLogUpdate.getValor(linha);
					
					Fachada.getInstance().remover(t);
					tLogUpdate.remover(linha);
					
/*					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, pessoa_Logada, log);
					tLogUpdate.addValor(log);*/
					
				}
				else if(popUp.getjTableAtual().getModel() instanceof TPessoa) 
				{
					
					Pessoa t = tPessoa.getValor(linha);
					Fachada.getInstance().remover(t);
					tPessoa.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TBackup) 
				{
					
					Backup t = tBackup.getValor(linha);
					Fachada.getInstance().remover(t);
					tBackup.remover(linha);
					
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
		
		JMenuItem excluirCaracteristica = popUpCaracteristica.getMenuItens()[1];
		
		
		excluirCaracteristica.addActionListener(ActionEvent->
		{
			try 
			{
				if(controlador_Principal.isBool_Colaborador_Ativado())
//					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
				int linha = popUpCaracteristica.getjTableAtual().getSelectedRow();
				
				if(popUpCaracteristica.getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					CaracteristicaExtra t = tCaracteristicaExtra.getValor(linha);
					Fachada.getInstance().remover(t);
					tCaracteristicaExtra.remover(linha);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogDelete(t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
				}
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro na remoção", e.getMessage());
			}
		});
		
		JMenuItem salvarCaracteristica = popUpCaracteristica.getMenuItens()[0];
		
		salvarCaracteristica.addActionListener((ItemEvent)->
		{
			try 
			{
				if(controlador_Principal.isBool_Colaborador_Ativado())
//					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
				int linha = popUpCaracteristica.getjTableAtual().getSelectedRow();
				
				if(popUpCaracteristica.getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					Object value = popUpCaracteristica.getjTableAtual().getValueAt(linha, 0);
					
					List<String> antes = Fachada.getInstance().gerarLog(tCaracteristicaExtra.getValor(linha));
					
					tCaracteristicaExtra.setValueAt(value, linha, 0);
					
					CaracteristicaExtra t = tCaracteristicaExtra.getValor(linha);
					
					Fachada.getInstance().atualizar(t);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(antes, t, controlador_Principal.getPessoa_Logada(), log);
					tLogUpdate.addValor(log);
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
		tTarefa.getCombo().addActionListener(ActionEvent->{
			
			if(tTarefa.getCombo().getSelectedItem() != null) 
			{
				int linha = tarefaTable.getSelectedRow();
				int coluna = tarefaTable.getSelectedColumn();
				
				if(linha >= 0 && coluna >= 0) 
				{ 
					try 
					{
						if(controlador_Principal.isBool_Colaborador_Ativado())
//							if(colaborador_Atual.getPrivilegio().equals("Visitante"))
								throw new ValidacaoException("Não tem permição");
						
						tTarefa.setValueAt(tTarefa.getCombo().getSelectedItem(),
								linha,
								coluna
								);
						Fachada.getInstance().atualizar(tTarefa.getValor(linha));
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
