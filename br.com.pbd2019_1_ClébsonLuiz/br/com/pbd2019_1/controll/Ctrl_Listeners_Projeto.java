package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.GerenteEtapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.SubEtapaColaborador;
import br.com.pbd2019_1.entidade.TarefaColaborador;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.Botao;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoSubEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoSubTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.TelaColaboradorEnvolvido;
import br.com.pbd2019_1.view.TelaColaboradores;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaInfoProjeto;
import br.com.pbd2019_1.view.TelaInfoSubTarefa;
import br.com.pbd2019_1.view.TelaInfoTarefa;
import br.com.pbd2019_1.view.TelaProjeto;

/**
 * 
 * Eventos reverentes ao projeto. Como Eventos das telas de projeto<br>
 * eventos de telas de Etapa, eventos de Sub. Etapas, eventos de Tarefa<br>
 * eventos de telas de Sub. Tarefa. e eventos referentes a adicionar colaboradores<br>
 * de projeto, etapa, sub etapa e tarefa.
 *
 */
public class Ctrl_Listeners_Projeto {

	private Controlador_Principal ctrl_P;

	public Ctrl_Listeners_Projeto(Controlador_Principal ctrl_P) {
		this.ctrl_P = ctrl_P;
	}
	
	/**
	 * TODO - Eventos de Projeto
	 */
	
	public void adicionarEventos()
	{
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoProjeto_Etapas());
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoEtapa());
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoTarefa());
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoSubEtapa());
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoSubTarefa());
		
		adicionarEventosBotaoAddColaborador();
	}
	
	/**TODO - Projeto*/
	private void adicionarEventoJInternal(JInternal_TelaInfoProjeto_Etapas telaInfoProjetoEtapas) {
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaProjeto().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaProjeto telaProjeto = telaInfoProjetoEtapas.getTelaProjeto_Etapas()
							.getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getProjeto_Atual());
					
					ctrl_P.getProjeto_Atual().setNome(nome);
					ctrl_P.getProjeto_Atual().setDescricao(descr);
					ctrl_P.getProjeto_Atual().setData_inicio(DateUtil.getDateSQL(dataI));
					ctrl_P.getProjeto_Atual().setData_fim(DateUtil.getDateSQL(dataF));
					
					Fachada.getInstance().atualizar(ctrl_P.getProjeto_Atual());
					
					ctrl_P.gettProjeto().fireTableDataChanged();
					
					int valorA = Fachada.getInstance()
							.getBoProjeto()
							.andamento_Projeto(
								ctrl_P.getProjeto_Atual()
								);
					
					((TelaInfoProjeto)telaProjeto).getProgressBar().setValue(valorA);
					
					telaInfoProjetoEtapas
					.getTelaProjeto_Etapas()
					.getTelaProjeto()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							ctrl_P.getProjeto_Atual(),
							ctrl_P.getPessoa_Logada(),
							log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar info. projeto", e.getMessage());
				}
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
				try
				{
					ctrl_P.getjInternal_TelaCadastro_Etapa().queroFoco();
				} 
				catch (PropertyVetoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tela de Cadastro", e.getMessage());
				}
			});

		adicionarEventoJInternalColaborador(telaInfoProjetoEtapas);
		
	}
	
	/**TODO - ETAPA*/
	private void adicionarEventoJInternal(JInternal_TelaInfoEtapa telaInfoEtapa) {
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaEtapa telaEtapa = telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa();
					String nome = telaEtapa.getNomeEtapaField().getTexto();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getEtapa_Atual());
					
					
					ctrl_P.getEtapa_Atual().setNome(nome);
					ctrl_P.getEtapa_Atual().setDescricao(descr);
					Fachada.getInstance().atualizar(ctrl_P.getEtapa_Atual());
					ctrl_P.gettEtapa().fireTableDataChanged();
					
					telaInfoEtapa.getTelaEtapa_Tarefas()
					.getTelaEtapa()
					.getTelaCadastroEdicao()
					.escondeBtn();
				
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							ctrl_P.getEtapa_Atual(),
							ctrl_P.getPessoa_Logada(),
							log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Atualizar Etapa", e.getMessage());
				}
			});
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaTarefas().getBtNovaTarefa()
			.addActionListener(ActionEvent->{
				try 
				{
					ctrl_P.getjInternal_TelaCadastro_Tarefa().queroFoco();
				} 
				catch (PropertyVetoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir ", e.getMessage());
				}
			});
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaSubEtapas().getBtNovaEtapa()
			.addActionListener(ActionEvent->{
				//TODO - Cadastrar a sub etapa
				try 
				{
					ctrl_P.getjInternal_TelaCadastroSubEtapa().queroFoco();
				} 
				catch (PropertyVetoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir ", e.getMessage());
				}
			});
		
		
		adicionarEventoJInternalColaborador(telaInfoEtapa);
		
	}
	
	/**TODO - SUB. ETAPA*/
	private void adicionarEventoJInternal(JInternal_TelaInfoSubEtapa telaInfoSubEtapa) {
		
		telaInfoSubEtapa.getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaEtapa telaEtapa = telaInfoSubEtapa.getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa();
					String nome = telaEtapa.getNomeEtapaField().getTexto();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getSubEtapa_Atual());
					
					
					ctrl_P.getSubEtapa_Atual().setNome(nome);
					ctrl_P.getSubEtapa_Atual().setDescricao(descr);
					Fachada.getInstance().atualizar(ctrl_P.getSubEtapa_Atual());
					ctrl_P.gettSubEtapa().fireTableDataChanged();
					
					telaInfoSubEtapa.getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa()
					.getTelaCadastroEdicao()
					.escondeBtn();
				
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							ctrl_P.getSubEtapa_Atual(),
							ctrl_P.getPessoa_Logada(),
							log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Atualizar Etapa", e.getMessage());
				}
			});
		
		telaInfoSubEtapa.getTelaInfoSubEtapaSubTarefas().getTelaTarefas().getBtNovaTarefa()
			.addActionListener(ActionEvent->{
				try 
				{
					ctrl_P.getjInternal_TelaCadastroSubTarefa().queroFoco();
				} 
				catch (PropertyVetoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir ", e.getMessage());
				}
			});
		
		adicionarEventoJInternalColaborador(telaInfoSubEtapa);
		
	}
	
	/**TODO - TAREFA*/
	private void adicionarEventoJInternal(JInternal_TelaInfoTarefa telaInfoTarefa) {
		
		telaInfoTarefa.getTelaInfoTarefa().getTelaInfoTarefa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa().getTelaInfoTarefa();
					
					String nome = telaTarefa.getNomeTarefaField().getTexto();
					String descr = telaTarefa.getDescricaoTextArea().getText();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					String prior = (String) telaTarefa.getPrioridadeComboBox().getSelectedItem();

					LocalTime time = telaTarefa.getHorario().getLocalTime();
					Date date = telaTarefa.getDateChooser().getDate();

					LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getTarefa_Atual());
					
					ctrl_P.getTarefa_Atual().setNome(nome);
					ctrl_P.getTarefa_Atual().setDescricao(descr);
					ctrl_P.getTarefa_Atual().setConcluida(finalizada);
					ctrl_P.getTarefa_Atual().setPrioridade(prior);
					ctrl_P.getTarefa_Atual().setHorario(localDateTime);

					Fachada.getInstance().atualizar(ctrl_P.getTarefa_Atual());
					ctrl_P.gettTarefa().fireTableDataChanged();
					
					telaInfoTarefa
					.getTelaInfoTarefa()
					.getTelaInfoTarefa()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(
							antes,
							ctrl_P.getTarefa_Atual(),
							ctrl_P.getPessoa_Logada(), 
							log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar tarefa", e.getMessage());
				}
			});
		
		telaInfoTarefa.getTelaInfoTarefa().getTelaInfoTarefa().getChckbxFinalizada()
			.addActionListener(ActionEvent->{
				try
				{
					TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa().getTelaInfoTarefa();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					ctrl_P.getTarefa_Atual().setConcluida(finalizada);
					Fachada.getInstance().atualizar(ctrl_P.getTarefa_Atual());
					
					ctrl_P.gettTarefa().fireTableDataChanged();
					
					ctrl_P.getEtapa_Atual().setPorcentagem(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(ctrl_P.getEtapa_Atual())
							);
					ctrl_P
						.getjInternal_TelaInfoEtapa()
						.getTelaEtapa_Tarefas()
						.getTelaEtapa()
						.getBarraProgressBar()
						.setValue(
								Math.round(ctrl_P.getEtapa_Atual().getPorcentagem())
								);
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar tarefa", e.getMessage());
				}
			});
		
		adicionarEventoJInternalColaborador(telaInfoTarefa);
	}
	
	/**TODO - SUB. TAREFA*/
	private void adicionarEventoJInternal(JInternal_TelaInfoSubTarefa telaInfoSubTarefa) {
		
		telaInfoSubTarefa.getTelaInfoSubTarefa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaInfoSubTarefa telaTarefa = telaInfoSubTarefa.getTelaInfoSubTarefa();
					
					String nome = telaTarefa.getNomeTarefaField().getTexto();
					String descr = telaTarefa.getDescricaoTextArea().getText();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					String prior = (String) telaTarefa.getPrioridadeComboBox().getSelectedItem();

					LocalTime time = telaTarefa.getHorario().getLocalTime();
					Date date = telaTarefa.getDateChooser().getDate();

					LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getSubTarefa_Atual());
					
					ctrl_P.getSubTarefa_Atual().setNome(nome);
					ctrl_P.getSubTarefa_Atual().setDescricao(descr);
					ctrl_P.getSubTarefa_Atual().setConcluida(finalizada);
					ctrl_P.getSubTarefa_Atual().setPrioridade(prior);
					ctrl_P.getSubTarefa_Atual().setHorario(localDateTime);

					Fachada.getInstance().atualizar(ctrl_P.getSubTarefa_Atual());
					ctrl_P.gettSubTarefa().fireTableDataChanged();
					
					telaInfoSubTarefa
					.getTelaInfoSubTarefa()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(
							antes,
							ctrl_P.getSubTarefa_Atual(),
							ctrl_P.getPessoa_Logada(), 
							log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar tarefa", e.getMessage());
				}
			});
		
		telaInfoSubTarefa.getTelaInfoSubTarefa().getChckbxFinalizada()
			.addActionListener(ActionEvent->{
				try
				{
					TelaInfoTarefa telaTarefa = telaInfoSubTarefa.getTelaInfoSubTarefa();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					ctrl_P.getSubTarefa_Atual().setConcluida(finalizada);
					Fachada.getInstance().atualizar(ctrl_P.getSubTarefa_Atual());
					
					ctrl_P.gettSubTarefa()
					.fireTableDataChanged();
					
					ctrl_P.getSubEtapa_Atual().setPorcentagem(
							Fachada.getInstance()
								.getBoSubEtapa()
								.recalcularPorcentagem(ctrl_P.getSubEtapa_Atual())
							);
					ctrl_P
						.getjInternal_TelaInfoSubEtapa()
						.getTelaInfoSubEtapaSubTarefas()
						.getTelaInfoSubEtapa()
						.getBarraProgressBar()
						.setValue(
								Math.round(ctrl_P.getSubEtapa_Atual().getPorcentagem())
								);
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar tarefa", e.getMessage());
				}
			});
	}
	
	/**
	 * TODO - Eventos de adicionar Colaborador
	 */
	
	private void adicionarEventoJInternalColaborador(JInternal_TelaInfoProjeto_Etapas infoProjetoEtapa)
	{
		TelaColaboradores tc = infoProjetoEtapa.getTelaProjeto_Etapas().getTelaColaboradores();
		
		tc.getBtAdicionarColaborador().addActionListener((ActionEvent)->
		{
			try
			{
				//TODO MUDAR AQUI
//				ctrl_P.gettColaboradorProjeto().getList().clear();
//				ctrl_P.gettColaboradorProjeto().fireTableDataChanged();
				ctrl_P.getjInternal_TabelaPessoasColaboradores().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			}
		});
		
	}
	
	private void adicionarEventoJInternalColaborador(JInternal_TelaInfoEtapa infoEtapa)
	{
		TelaColaboradorEnvolvido tce = infoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getTelaColaboradorEnvolvido();
		
		tce.getBtAdicionarColaborador().addActionListener((ActionEvent)->
		{
			try
			{
				List<Colaborador> lColaborador = Fachada.getInstance().getBoColaborador().buscarPorProjeto(ctrl_P.getProjeto_Atual());
				
				ctrl_P.gettColaboradorEtapa().addAll(lColaborador);
				ctrl_P.getjInternal_ColaboradoresEtapa().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			} 
		});
		
		tce.getBtRemoverColaborador().addActionListener((ActionEvent)->
		{
			try 
			{
				GerenteEtapa ge = ctrl_P.getEtapa_Atual().getGerenteEtapa();
				
				Fachada.getInstance().remover(ge);
				
				ctrl_P.getEtapa_Atual().setGerenteEtapa(null);
				
				tce.getCmptxtResponsavel().setTexto("");
				tce.getCmptxtDatalog().setTexto("");
				tce.exibirSemColaborador();
			}
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao remover colaborador de tarefa", e.getMessage());
			}
		});
		
		tce.getBtVerColaborador().addActionListener((ActionEvent)->
		{
			try 
			{
				Colaborador colaborador = ctrl_P.getEtapa_Atual().getGerenteEtapa().getColaborador();
				Pessoa p = colaborador.getPessoa();	
				
				ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoPessoaOutremSimples(p);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Não foi possível carregar informações da pessoa ", e.getMessage());
			} 
			catch (PropertyVetoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Não foi possível carregar informações da pessoa ", e.getMessage());
			}
		});
		
	}
	
	private void adicionarEventoJInternalColaborador(JInternal_TelaInfoSubEtapa infoSubEtapa)
	{
		TelaColaboradorEnvolvido tce = infoSubEtapa.getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa().getTelaColaboradorEnvolvido();
		
		tce.getBtAdicionarColaborador().addActionListener((ActionEvent)->
		{
			try
			{
				List<Colaborador> lColaborador = Fachada.getInstance().getBoColaborador().buscarPorProjeto(ctrl_P.getProjeto_Atual());
				
				ctrl_P.gettColaboradorSubEtapa().addAll(lColaborador);
				ctrl_P.getjInternal_ColaboradoresSubEtapa().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			}
		});
		
		tce.getBtRemoverColaborador().addActionListener((ActionEvent)->
		{
			try 
			{
				SubEtapaColaborador sec = ctrl_P.getSubEtapa_Atual().getSubEtapaColaborador();
				
				Fachada.getInstance().remover(sec);
				
				ctrl_P.getSubEtapa_Atual().setSubEtapaColaborador(null);
				
				tce.getCmptxtResponsavel().setTexto("");
				tce.getCmptxtDatalog().setTexto("");
				tce.exibirSemColaborador();
			}
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao remover colaborador de sub etapa", e.getMessage());
			}
		});
		
		tce.getBtVerColaborador().addActionListener((ActionEvent)->
		{
			try 
			{
				Colaborador colaborador = ctrl_P.getSubEtapa_Atual().getSubEtapaColaborador().getColaborador();
				Pessoa p = colaborador.getPessoa();	
				
				ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoPessoaOutremSimples(p);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Não foi possível carregar informações da pessoa ", e.getMessage());
			} 
			catch (PropertyVetoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Não foi possível carregar informações da pessoa ", e.getMessage());
			}
		});
	}
	
	private void adicionarEventoJInternalColaborador(JInternal_TelaInfoTarefa infoTarefa)
	{
		TelaColaboradorEnvolvido tce = infoTarefa.getTelaInfoTarefa().getTelaColaboradorEnvolvido();
		
		tce.getBtAdicionarColaborador().addActionListener((ActionEvent)->
		{
			try
			{
				List<Colaborador> lColaborador = Fachada.getInstance().getBoColaborador().buscarPorProjeto(ctrl_P.getProjeto_Atual());
				
				ctrl_P.gettColaboradorTarefa().addAll(lColaborador);
				ctrl_P.getjInternal_ColaboradoresTarefa().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
			}
		});
		
		tce.getBtRemoverColaborador().addActionListener((ActionEvent)->
		{
			try 
			{
				TarefaColaborador tc = ctrl_P.getTarefa_Atual().getTarefaColaborador();
				
				Fachada.getInstance().remover(tc);
				
				ctrl_P.getTarefa_Atual().setTarefaColaborador(null);
				
				tce.getCmptxtResponsavel().setTexto("");
				tce.getCmptxtDatalog().setTexto("");
				tce.exibirSemColaborador();
			}
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao remover colaborador de tarefa", e.getMessage());
			}
		});
		
		tce.getBtVerColaborador().addActionListener((ActionEvent)->
		{
			try 
			{
				Colaborador colaborador = ctrl_P.getTarefa_Atual().getTarefaColaborador().getColaborador();
				Pessoa p = colaborador.getPessoa();	
				
				ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoPessoaOutremSimples(p);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Não foi possível carregar informações da pessoa ", e.getMessage());
			} 
			catch (PropertyVetoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Não foi possível carregar informações da pessoa ", e.getMessage());
			}
		});
	}
	
	/**TODO - Eventos para adicionar colaboradores no Projeto, Etapa, Sub.Etapa e Tarefa*/
	
	public void adicionarEventosBotaoAddColaborador()
	{
		Botao btAddColaboradorProjeto = ctrl_P.getjInternal_TabelaPessoasColaboradores().getTelaPessoas().getBtAdicionarPessoa();
		Botao btAddGerenteEtapa = ctrl_P.getjInternal_ColaboradoresEtapa().getTelaPessoas().getBtAdicionarPessoa();
		Botao btAddColaboradorSubEtapa = ctrl_P.getjInternal_ColaboradoresSubEtapa().getTelaPessoas().getBtAdicionarPessoa();
		Botao btAddColaboradorTarefa = ctrl_P.getjInternal_ColaboradoresTarefa().getTelaPessoas().getBtAdicionarPessoa();
		
		btAddColaboradorProjeto.addActionListener((ActionEvent)->
		{
			int index = ctrl_P
					.getjInternal_TabelaPessoasColaboradores()
					.getTelaPessoas()
					.getTable()
					.getSelectedRow();
			
			if(index >= 0)
			try
			{
				Pessoa pessoa = ctrl_P.gettColaboradorProjeto().getValor(index);
				
				Colaborador colaborador = new Colaborador();
				colaborador.setData_ingresso(LocalDateTime.now());
				colaborador.setPessoa(pessoa);
				colaborador.setProjeto(ctrl_P.getProjeto_Atual());
				
				Fachada.getInstance().inserir(colaborador);
			
				ctrl_P.gettColaborador().addValor(colaborador);
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(colaborador, ctrl_P.getPessoa_Logada(), log);
				ctrl_P.gettLogUpdate().addValor(log);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao adicionar colaborador", e.getMessage());
			}
		});
		
		btAddGerenteEtapa.addActionListener((ActionEvent)->
		{
			int index = ctrl_P
					.getjInternal_ColaboradoresEtapa()
					.getTelaPessoas()
					.getTable()
					.getSelectedRow();
			
			if(index >= 0)
			try
			{
				Colaborador colaborador = ctrl_P.gettColaboradorEtapa().getValor(index);
				
				GerenteEtapa gerenteEtapa = new GerenteEtapa();
				
				gerenteEtapa.setEtapa(ctrl_P.getEtapa_Atual());
				gerenteEtapa.setColaborador(colaborador);
				
				Fachada.getInstance().inserir(gerenteEtapa);
			
				TelaColaboradorEnvolvido tce = ctrl_P.getjInternal_TelaInfoEtapa().getTelaEtapa_Tarefas().getTelaEtapa().getTelaColaboradorEnvolvido();
				
				tce.getCmptxtResponsavel().setText(colaborador.getPessoa().getNome());
				tce.getCmptxtDatalog().setText(colaborador.getData_ingresso().toString());
				
				tce.exibirComColaborador();
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(gerenteEtapa, ctrl_P.getPessoa_Logada(), log);
				ctrl_P.gettLogUpdate().addValor(log);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao adicionar a colaborador", e.getMessage());
			}
		});
		
		btAddColaboradorSubEtapa.addActionListener((ActionEvent)->
		{
			int index = ctrl_P
					.getjInternal_ColaboradoresSubEtapa()
					.getTelaPessoas()
					.getTable()
					.getSelectedRow();
			
			if(index >= 0)
			try
			{
				Colaborador colaborador = ctrl_P.gettColaboradorSubEtapa().getValor(index);
				
				SubEtapaColaborador subEtapaColaborador = new SubEtapaColaborador();
				
				subEtapaColaborador.setSub_etapa(ctrl_P.getSubEtapa_Atual());
				subEtapaColaborador.setColaborador(colaborador);
				
				Fachada.getInstance().inserir(subEtapaColaborador);
			
				TelaColaboradorEnvolvido tce = ctrl_P.getjInternal_TelaInfoSubEtapa().getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa().getTelaColaboradorEnvolvido();
				
				tce.getCmptxtResponsavel().setText(colaborador.getPessoa().getNome());
				tce.getCmptxtDatalog().setText(colaborador.getData_ingresso().toString());
				
				tce.exibirComColaborador();
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(subEtapaColaborador, ctrl_P.getPessoa_Logada(), log);
				ctrl_P.gettLogUpdate().addValor(log);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao adicionar a colaborador", e.getMessage());
			}
		});
		
		btAddColaboradorTarefa.addActionListener((ActionEvent)->
		{
			int index = ctrl_P
					.getjInternal_ColaboradoresTarefa()
					.getTelaPessoas()
					.getTable()
					.getSelectedRow();
			
			if(index >= 0)
			try
			{
				Colaborador colaborador = ctrl_P.gettColaboradorTarefa().getValor(index);
				
				TarefaColaborador tarefaColaborador = new TarefaColaborador();
				
				tarefaColaborador.setTarefa(ctrl_P.getTarefa_Atual());
				tarefaColaborador.setColaborador(colaborador);
				
				Fachada.getInstance().inserir(tarefaColaborador);
			
				TelaColaboradorEnvolvido tce = ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaColaboradorEnvolvido();
				
				tce.getCmptxtResponsavel().setText(colaborador.getPessoa().getNome());
				tce.getCmptxtDatalog().setText(colaborador.getData_ingresso().toString());
				
				tce.exibirComColaborador();
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(tarefaColaborador, ctrl_P.getPessoa_Logada(), log);
				ctrl_P.gettLogUpdate().addValor(log);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao adicionar a colaborador", e.getMessage());
			}
		});
		
	}
	
}
