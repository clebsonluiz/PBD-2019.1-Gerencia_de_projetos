package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.CellRenderer;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TLogUpdate;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TSubEtapa;
import br.com.pbd2019_1.tabelas.TSubTarefa;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.view.MeuJDialog;

public class Ctrl_JTable {

	private Controlador_Principal ctrl_P;
	
	public Ctrl_JTable(Controlador_Principal controlador_Principal) {
		this.ctrl_P = controlador_Principal;
	}
	
	public void adicionarEventos()
	{
		JTable tableSubTarefas = ctrl_P.getjInternal_TelaInfoSubEtapa()
				.getTelaInfoSubEtapaSubTarefas()
				.getTelaTarefas()
				.getTable();
		
		JTable tableSubEtapas = ctrl_P.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaSubEtapas()
				.getTable();
		
		JTable tableTarefas = ctrl_P.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaTarefas()
				.getTable();
		
		JTable tableEtapas = ctrl_P.getjInternal_TelaInfoProjeto_Etapas()
				.getTelaProjeto_Etapas()
				.getTelaEtapas()
				.getTable();
		
		JTable tableColaboradores = ctrl_P.getjInternal_TelaInfoProjeto_Etapas()
				.getTelaProjeto_Etapas()
				.getTelaColaboradores()
				.getTable();
		
		JTable tableCaracteristicas = ctrl_P.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos()
				.getTelaInfoPessoa()
				.getTelaContatoCaracteristica()
				.getJTable();
		
		JTable tableProjetos = ctrl_P.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos()
				.getTelaProjetos()
				.getTable();
		
		JTable tableColaboracoes = ctrl_P.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos()
				.getTelaColaboracoes()
				.getTable();
		
		JTable tableEtapasSimples = ctrl_P.getjInternal_TelaInfoProjeto_Etapas_Simples()
				.getTelaProjeto_Etapas_Simples()
				.getTelaEtapas()
				.getTable();
		
		JTable tablePessoas = ctrl_P.getjInternal_TabelaPessoas()
				.getTelaPessoas()
				.getTable();
		
//		JTable tablePessoasDisponiveis = ctrl_P.getjInternal_TabelaPessoasColaboradores()
//				.getTelaPessoas()
//				.getTable();
		
		JTable tableLogs = ctrl_P.getjInternal_TabelaLogs()
				.getTelaLogs()
				.getTable();
		
		JTable tableColabProjeto = ctrl_P.getjInternal_TabelaPessoasColaboradores()
				.getTelaPessoas()
				.getTable();
		JTable tableColabEtapa = ctrl_P.getjInternal_ColaboradoresEtapa()
				.getTelaPessoas()
				.getTable();
		JTable tableColabSubEtapa = ctrl_P.getjInternal_ColaboradoresSubEtapa()
				.getTelaPessoas()
				.getTable();
		JTable tableColabTarefa = ctrl_P.getjInternal_ColaboradoresTarefa()
				.getTelaPessoas()
				.getTable();
		
		tableSubTarefas.setModel(ctrl_P.gettSubTarefa());
		tableSubEtapas.setModel(ctrl_P.gettSubEtapa());
		tableLogs.setModel(ctrl_P.gettLogUpdate());
		tableTarefas.setModel(ctrl_P.gettTarefa());
		tableCaracteristicas.setModel(ctrl_P.gettCaracteristicaExtra());
		tableEtapas.setModel(ctrl_P.gettEtapa());
		tableColaboradores.setModel(ctrl_P.gettColaborador());
		tableProjetos.setModel(ctrl_P.gettProjeto());
		tableColaboracoes.setModel(ctrl_P.gettColaboracoes());
		tableEtapasSimples.setModel(ctrl_P.gettEtapa());
		tablePessoas.setModel(ctrl_P.gettPessoa());
//		tablePessoasDisponiveis.setModel(ctrl_P.gettPessoa());
		
		tableColabProjeto.setModel(ctrl_P.gettColaboradorProjeto());
		tableColabEtapa.setModel(ctrl_P.gettColaboradorEtapa());
		tableColabSubEtapa.setModel(ctrl_P.gettColaboradorSubEtapa());
		tableColabTarefa.setModel(ctrl_P.gettColaboradorTarefa());
		
		
		tableTarefas.getColumnModel().getColumn(2).setCellEditor(ctrl_P.gettTarefa().getCellEditor());
		tableSubTarefas.getColumnModel().getColumn(2).setCellEditor(ctrl_P.gettSubTarefa().getCellEditor());
		
		List<JTable> tables = new ArrayList<>();
		
		Collections.addAll(tables, tableSubTarefas, tableSubEtapas,
				tableLogs, tableTarefas, tableCaracteristicas, tableEtapas, 
				tableColaboradores,	tableProjetos, tableColaboracoes,
				tableEtapasSimples, tablePessoas, 
//				tablePessoasDisponiveis,
				tableColabProjeto,
				tableColabEtapa,
				tableColabSubEtapa,
				tableColabTarefa
				);
		
		for(JTable table: tables)
		{
			table.setDefaultRenderer(Object.class, new CellRenderer());
			
			if(table.equals(tableLogs)) table.setRowHeight(50);
			else table.setRowHeight(30);

			adicionarMouseEventJTable(table);
		}
		
		adicionarEventosComboBox(tableTarefas);
		adicionarEventosComboBox(tableSubTarefas);
	}
	
	/**
	 * Eventos de Mouse das Tabelas mais importantes
	 * 
	 * */
	private void adicionarMouseEventJTable(JTable table) 
	{
		table.addMouseListener(new MouseAdapter() 
		{
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
					{
						Controlador_Principal.getPopUp().setjTableAtual(table);
						Controlador_Principal.getPopUp().show(table, e.getX(), e.getY());
					}
				} 
				else 
				{
					if(table.getModel() instanceof TSubTarefa) 
					{
						if (coluna == 3)
						{
							if(obj instanceof Boolean)
							{
								try 
								{
										boolean b = (((Boolean)obj).booleanValue());
										System.out.println(((Boolean)obj).booleanValue());
										System.out.println(b);
										ctrl_P.gettSubTarefa().setValueAt(b, linha, coluna);
										Fachada.getInstance().atualizar(ctrl_P.gettSubTarefa().getValor(linha));
										
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
								ctrl_P.setSubTarefa_Atual(
										ctrl_P.gettSubTarefa().getValor(linha)
										);
								
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoSubTarefa(
											ctrl_P.getSubTarefa_Atual()
											);
							}
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TSubEtapa)
					{
						if(coluna == 2) 
						{
							try 
							{
								ctrl_P.setSubEtapa_Atual(
										ctrl_P.gettSubEtapa().getValor(linha)
										);
								
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoSubEtapa(
											ctrl_P.getSubEtapa_Atual()
											);
							} 
							catch (ValidacaoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao consultar dados da sub etapa", e1.getMessage());
							}
							catch (PropertyVetoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoSubEtapa", e1.getMessage());
							}
						}
					}
					else if(table.getModel() instanceof TTarefa) 
					{
						if(coluna == 3) 
						{
							if(obj instanceof Boolean)
							{
								try 
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
						if(coluna == 2)
						{
							try 
							{
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
						if(coluna == 2) 
						{
							try 
							{
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoColaboradorSimples(
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
	
	private void adicionarEventosComboBox(JTable tarefaTable) 
	{
		if(tarefaTable.getModel() instanceof TTarefa)
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
		}
		else if (tarefaTable.getModel() instanceof TSubTarefa)
		{
			ctrl_P.gettSubTarefa().getCombo().addActionListener(ActionEvent->{
				
				if(ctrl_P.gettSubTarefa().getCombo().getSelectedItem() != null) 
				{
					int linha = tarefaTable.getSelectedRow();
					int coluna = tarefaTable.getSelectedColumn();
					
					if(linha >= 0 && coluna >= 0) 
					{ 
						try 
						{
							ctrl_P.gettSubTarefa().setValueAt(ctrl_P.gettSubTarefa().getCombo().getSelectedItem(),
									linha,
									coluna
									);
							Fachada.getInstance().atualizar(ctrl_P.gettSubTarefa().getValor(linha));
						} 
						catch (ValidacaoException e) 
						{
							MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar info. tarefa", e.getMessage());
						}
					}
				}
			});

		}

	}
	
}
