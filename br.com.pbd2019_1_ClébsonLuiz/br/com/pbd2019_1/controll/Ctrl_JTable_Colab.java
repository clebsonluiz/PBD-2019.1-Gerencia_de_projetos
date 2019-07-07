package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.exception.ValidacaoException;
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
									.exibirJInternalInfoSubTarefa(
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
									.exibirJInternalInfoTarefa(
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
									.exibirJInternalInfoSubEtapa(
											ctrl_P.getSubEtapa_Atual_Colab()
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
									.exibirJInternalInfoEtapa(
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
						if(coluna == 3) 
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
