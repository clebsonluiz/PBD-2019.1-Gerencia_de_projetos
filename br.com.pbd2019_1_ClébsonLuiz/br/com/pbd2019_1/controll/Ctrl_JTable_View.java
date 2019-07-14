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
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.entidade.ViewGerenteEtapa;
import br.com.pbd2019_1.entidade.ViewSubEtapaColaborador;
import br.com.pbd2019_1.entidade.ViewTarefaColaborador;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.CellRenderer;
import br.com.pbd2019_1.tabelas.TViewGerenteEtapa;
import br.com.pbd2019_1.tabelas.TViewSubEtapaColaborador;
import br.com.pbd2019_1.tabelas.TViewTarefaColaborador;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.TelaJTabViewColaborador;
import br.com.pbd2019_1.view.TelaTabelaViewGerenteEtapa;
import br.com.pbd2019_1.view.TelaTabelaViewSubEtapaColaborador;
import br.com.pbd2019_1.view.TelaTabelaViewTarefaColaborador;

public class Ctrl_JTable_View 
{
	private Controlador_Principal ctrl_P;

	public Ctrl_JTable_View(Controlador_Principal ctrl_P) {
		this.ctrl_P = ctrl_P;
	}
	
	public void adicionarTableModels()
	{
		TelaJTabViewColaborador tj = ctrl_P
				.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos()
				.getTelaJTabViewColaborador();
		
		JTable tableGerente = tj.getTelaTabelaViewGerenteEtapa().getTable();
		JTable tableSubEtapa = tj.getTelaTabelaViewSubEtapaColaborador().getTable();
		JTable tableTarefa = tj.getTelaTabelaViewTarefaColaborador().getTable();
		
		List<JTable> list = new ArrayList<>();
		Collections.addAll(list, tableGerente, tableSubEtapa, tableTarefa);
		
		tableGerente.setModel(ctrl_P.gettViewGerenteEtapa());
		tableSubEtapa.setModel(ctrl_P.gettViewSubEtapaColaborador());
		tableTarefa.setModel(ctrl_P.gettViewTarefaColaborador());
		
		list.forEach(table->
		{
			table.setDefaultRenderer(Object.class, new CellRenderer());
			table.setRowHeight(30);
			adicionarEventos(table);
		});
	}
	
	private void adicionarEventos(JTable table) 
	{
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.rowAtPoint(e.getPoint());
				int coluna = table.columnAtPoint(e.getPoint());
				
				table.setRowSelectionInterval(linha, linha);
				table.setColumnSelectionInterval(coluna, coluna);
				
				if(!SwingUtilities.isRightMouseButton(e)) 
				{
					if(table.getModel() instanceof TViewGerenteEtapa)
					{
						if(coluna == 3)
						{
							chamarParaViewGerenteEtapa(
									ctrl_P.gettViewGerenteEtapa().getValor(linha)
									);
						}
					}
					else if(table.getModel() instanceof TViewSubEtapaColaborador)
					{
						if(coluna == 3)
						{
							chamarParaViewSubEtapaColaborador(
									ctrl_P.gettViewSubEtapaColaborador().getValor(linha)
									);
						}
					}
					else if(table.getModel() instanceof TViewTarefaColaborador)
					{
						if(coluna == 4)
						{
							chamarParaViewTarefaColaborador(
									ctrl_P.gettViewTarefaColaborador().getValor(linha)
									);
						}
					}
				}
			}
		});
	}

	public void adicionarEventos()
	{
		TelaJTabViewColaborador telaJTabViewColaborador = ctrl_P
				.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos()
				.getTelaJTabViewColaborador();
		
		TelaTabelaViewGerenteEtapa tg = telaJTabViewColaborador.getTelaTabelaViewGerenteEtapa();
		TelaTabelaViewSubEtapaColaborador ts = telaJTabViewColaborador.getTelaTabelaViewSubEtapaColaborador();
		TelaTabelaViewTarefaColaborador tc = telaJTabViewColaborador.getTelaTabelaViewTarefaColaborador();
		
		tg.getBtBuscar().addActionListener((ActionEvent)->
		{
			try 
			{
				ctrl_P.gettViewGerenteEtapa()
				.addAll(
						Fachada.getInstance().getBoViewGerenteEtapa()
						.getPorPessoa(
								tg.getTipoBuscaCmbx().getSelectedIndex(),
								tg.getCmptxtNome().getTexto(),
								ctrl_P.getPessoa_Logada().getId()
								)
						);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Buscar", e.getMessage());
			}
		});
		
		ts.getBtBuscar().addActionListener((ActionEvent)->
		{
			try 
			{
				ctrl_P.gettViewSubEtapaColaborador()
				.addAll(
						Fachada.getInstance().getBoViewSubEtapaColaborador()
						.getPorPessoa(
								ts.getTipoBuscaCmbx().getSelectedIndex(),
								ts.getCmptxtNome().getTexto(),
								ctrl_P.getPessoa_Logada().getId()
								)
						);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Buscar", e.getMessage());
			}
		});
		
		tc.getBtBuscar().addActionListener((ActionEvent)->
		{
			try 
			{
				ctrl_P.gettViewTarefaColaborador()
				.addAll(
						Fachada.getInstance().getBoViewTarefaColaborador()
						.getPorPessoa(
								tc.getTipoBuscaCmbx().getSelectedIndex(),
								tc.getBuscaEspecificaCmbx().getSelectedIndex(),
								tc.getCmptxtNome().getTexto(),
								tc.getDateChooser().getDate(),
								ctrl_P.getPessoa_Logada().getId()
								)
						);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Buscar", e.getMessage());
			}
		});
	}
	
	private void chamarParaViewGerenteEtapa(ViewGerenteEtapa vg)
	{
		try 
		{
			ctrl_P.setEtapa_Atual((Etapa)
					Fachada.getInstance().buscar(Etapa.class, vg.getCod_de_etapa())
					);
			Projeto projeto = new Projeto();
			projeto.setId(vg.getCod_de_projeto());
			ctrl_P.setProjeto_Atual(projeto);
			
			ctrl_P.getCtrl_PreenchementoTela().exibirMinhaColaboracaoGerenteEtapa(ctrl_P.getEtapa_Atual());
		}
		catch (ValidacaoException e) 
		{
			MeuJDialog.exibirAlertaErro(null, "Erro ao carregar Etapa", e.getMessage());
		} 
		catch (PropertyVetoException e) 
		{
			MeuJDialog.exibirAlertaErro(null, "Erro ao carregar Tela", e.getMessage());
		}
	}

	private void chamarParaViewSubEtapaColaborador(ViewSubEtapaColaborador vs)
	{
		try 
		{
			ctrl_P.setSubEtapa_Atual((SubEtapa)
					Fachada.getInstance().buscar(SubEtapa.class, vs.getCod_de_sub_etapa())
					);
			Projeto projeto = new Projeto();
			projeto.setId(vs.getCod_de_projeto());
			ctrl_P.setProjeto_Atual(projeto);
			
			ctrl_P.getCtrl_PreenchementoTela().exibirMinhaColaboracaoSubEtapa(ctrl_P.getSubEtapa_Atual());
		}
		catch (ValidacaoException e) 
		{
			MeuJDialog.exibirAlertaErro(null, "Erro ao carregar Sub Etapa", e.getMessage());
		} 
		catch (PropertyVetoException e) 
		{
			MeuJDialog.exibirAlertaErro(null, "Erro ao carregar Tela", e.getMessage());
		}
	}
	
	private void chamarParaViewTarefaColaborador(ViewTarefaColaborador vt)
	{
		try 
		{
			ctrl_P.setTarefa_Atual((Tarefa)
					Fachada.getInstance().buscar(Tarefa.class, vt.getCod_de_tarefa())
					);
			Projeto projeto = new Projeto();
			projeto.setId(vt.getCod_de_projeto());
			ctrl_P.setProjeto_Atual(projeto);
			
			ctrl_P.getCtrl_PreenchementoTela().exibirMinhaColaboracaoTarefa(ctrl_P.getTarefa_Atual());
		}
		catch (ValidacaoException e) 
		{
			MeuJDialog.exibirAlertaErro(null, "Erro ao carregar Etapa", e.getMessage());
		} 
		catch (PropertyVetoException e) 
		{
			MeuJDialog.exibirAlertaErro(null, "Erro ao carregar Tela", e.getMessage());
		}
	}
}
