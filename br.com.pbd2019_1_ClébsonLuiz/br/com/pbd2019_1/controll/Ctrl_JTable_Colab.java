package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.tabelas.CellRenderer;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TSubEtapa;
import br.com.pbd2019_1.tabelas.TSubTarefa;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.view.MeuJDialog;

public class Ctrl_JTable_Colab {

	private Controlador_Principal ctrl_P;

	public Ctrl_JTable_Colab(Controlador_Principal ctrl_P) {
		super();
		this.ctrl_P = ctrl_P;
	}
	
	public void adicionarEventos()
	{
		JTable tableTarefas = ctrl_P.getJif_Inf_Etp_Colab()
				.getTelaEtapa_Tarefas()
				.getTelaTarefas()
				.getTable();
		JTable tableSubEtapas = ctrl_P.getJif_Inf_Etp_Colab()
				.getTelaEtapa_Tarefas()
				.getTelaSubEtapas()
				.getTable();
		JTable tableEtapas = ctrl_P.getJif_Inf_Proj_Colab()
				.getTelaProjeto_Etapas()
				.getTelaEtapas()
				.getTable();
		JTable tableColaboradores = ctrl_P.getJif_Inf_Proj_Colab()
				.getTelaProjeto_Etapas()
				.getTelaColaboradores()
				.getTable();
		JTable tableSubTarefas = ctrl_P.getJif_Inf_SbEtp_Colab()
				.getTelaInfoSubEtapaSubTarefas()
				.getTelaTarefas()
				.getTable();
		
		tableTarefas.setModel(ctrl_P.gettTarefaColab());
		tableSubEtapas.setModel(ctrl_P.gettSubEtapaColab());
		tableEtapas.setModel(ctrl_P.gettEtapaColab());
		tableColaboradores.setModel(ctrl_P.gettColab());
		tableSubTarefas.setModel(ctrl_P.gettSubTarefaColab());
		
		tableTarefas.setDefaultRenderer(Object.class, new CellRenderer());
		tableSubEtapas.setDefaultRenderer(Object.class, new CellRenderer());
		tableEtapas.setDefaultRenderer(Object.class, new CellRenderer());
		tableColaboradores.setDefaultRenderer(Object.class, new CellRenderer());
		tableSubTarefas.setDefaultRenderer(Object.class, new CellRenderer());
		
		tableTarefas.setRowHeight(30);
		tableSubEtapas.setRowHeight(30);
		tableEtapas.setRowHeight(30);
		tableColaboradores.setRowHeight(30);
		tableSubTarefas.setRowHeight(30);
		
		adicionarMouseEventJTable(tableTarefas);
		adicionarMouseEventJTable(tableSubEtapas);
		adicionarMouseEventJTable(tableEtapas);
		adicionarMouseEventJTable(tableColaboradores);
		adicionarMouseEventJTable(tableSubTarefas);
	}
	
	
	/**
	 * Eventos de Mouse das Tabelas mais importantes da visão de colab
	 * 
	 * */
	public void adicionarMouseEventJTable(JTable table) {
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.rowAtPoint(e.getPoint());
				int coluna = table.columnAtPoint(e.getPoint());
				
				table.setRowSelectionInterval(linha, linha);
				table.setColumnSelectionInterval(coluna, coluna);
				
				if(SwingUtilities.isRightMouseButton(e)) 
				{
					
				} 
				else 
				{
					if(table.getModel() instanceof TSubTarefa) 
					{
						if (coluna == 4)
						{
							try 
							{
								ctrl_P.setSubTarefa_Atual_Colab(
										ctrl_P.gettSubTarefaColab().getValor(linha)
										);
								
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInt_InfoSubTarefaColab(
											ctrl_P.getSubTarefa_Atual_Colab()
											);
							}
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela", e1.getMessage());
							}
						}
					} 
					else if(table.getModel() instanceof TTarefa) 
					{
						if (coluna == 4)
						{
							try 
							{
								ctrl_P.setTarefa_Atual_Colab(
										ctrl_P.gettTarefaColab().getValor(linha)
										);
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInt_InfoTarefaColab(
											ctrl_P.getTarefa_Atual_Colab()
											);
							} 
							catch (PropertyVetoException e1) 
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela", e1.getMessage());
							} 
							catch (ValidacaoException e1)
							{
								MeuJDialog.exibirAlertaErro(null, "Erro ao carregar dados para tela", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TSubEtapa)
					{
						if(coluna == 2) 
						{
							try 
							{
								ctrl_P.setSubEtapa_Atual_Colab(
										ctrl_P.gettSubEtapaColab().getValor(linha)
										);
								
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInt_InfoSubEtapaColab(
											ctrl_P.getSubEtapa_Atual_Colab()
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
					else if (table.getModel() instanceof TEtapa)
					{
						if(coluna == 2) 
						{
							try 
							{
								ctrl_P.setEtapa_Atual_Colab(
										ctrl_P.gettEtapaColab().getValor(linha)
										);
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInt_InfoEtapaColab(
											ctrl_P.getEtapa_Atual_Colab()
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
					else if (table.getModel() instanceof TColaborador)
					{
						if(coluna == 2) 
						{
							try 
							{
								ctrl_P.getCtrl_PreenchementoTela()
									.exibirJInternalInfoColaboradorSimples(
											ctrl_P.gettColab().getValor(linha)
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
				}
			}
		});
	}
	
	
}
