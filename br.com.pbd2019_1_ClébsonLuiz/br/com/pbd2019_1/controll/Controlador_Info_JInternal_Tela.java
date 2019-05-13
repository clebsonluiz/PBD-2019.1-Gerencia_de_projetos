package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

import br.com.pbd2019_1.dao.DAOResRelatorio;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternal_TelaAlerta;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas_Simples;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.MeuJFileChooser;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaInfoPessoa;
import br.com.pbd2019_1.view.TelaInfoProjeto;
import br.com.pbd2019_1.view.TelaInfoTarefa;
import br.com.pbd2019_1.view.TelaInserirSQL;
import br.com.pbd2019_1.view.TelaPessoa;
import br.com.pbd2019_1.view.TelaProjeto;

public class Controlador_Info_JInternal_Tela {

	private Controlador_Principal controlador_Principal;
	
	public Controlador_Info_JInternal_Tela(Controlador_Principal controlador_Principal) {
		this.controlador_Principal = controlador_Principal;
	}
	
	public void adicionarEventosJInternals() {
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInfoEtapa());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInfoPessoa());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInfoPessoa_Projetos());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInfoProjeto_Etapas());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInfoProjeto_Etapas_Simples());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInfoTarefa());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInserirSQL());
	}
	
	
	private void adicionarEventoJInternal(JInternal_TelaInfoEtapa telaInfoEtapa) {
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getBotao1()
			.addActionListener(ActionEvent->{
			//TODO - Atualizar info Etapa
				
				try 
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaEtapa telaEtapa = telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa();
					String nome = telaEtapa.getNomeEtapaField().getTexto();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					controlador_Principal.getEtapa_Atual().setNome(nome);
					controlador_Principal.getEtapa_Atual().setDescricao(descr);
					Fachada.getInstance().atualizar(controlador_Principal.getEtapa_Atual());
					controlador_Principal.gettEtapa().fireTableDataChanged();
					
					telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getTelaCadastroEdicao().escondeBtn();
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Atualizar Etapa", e.getMessage());
				}
				
				
			});
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaTarefas().getBtNovaTarefa()
			.addActionListener(ActionEvent->{
			//TODO - Nova Tarefa
				try 
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					controlador_Principal.getjInternal_TelaCadastro_Tarefa().queroFoco();
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Não Tem permição", e.getMessage());
				} 
				catch (PropertyVetoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir ", e.getMessage());
				}
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInserirSQL jInternal_TelaInserirSQL) {
		
		TelaInserirSQL telaInserirSQL = jInternal_TelaInserirSQL.getTelaInserirSQL();
		
		telaInserirSQL.getBtnInserir()
		.addActionListener(ActionEvent->{
			//TODO - Inserir SQL
			try {
				String sql = telaInserirSQL.getTextArea().getSelectedText();
			
				if(sql == null)
					sql = telaInserirSQL.getTextArea().getText();
			
				controlador_Principal.gettObject().addAll(Fachada.getInstance().inserirSQLGenerica(sql));
				
				telaInserirSQL.getExceptionTextArea().setText("");
			} catch (ValidacaoException e) {
				telaInserirSQL.getExceptionTextArea().setText(e.getMessage());
			}
			
		});

		telaInserirSQL.getBtnLimpar()
		.addActionListener(ActionEvent->{
			//TODO - Botao Limpar
			telaInserirSQL.getTextArea().setText("");
		});

		telaInserirSQL.getBtnSalvar()
		.addActionListener(ActionEvent->{
			//TODO - Botao Salvar
		});

		telaInserirSQL.getBtnAbrir()
		.addActionListener(ActionEvent->{
			//TODO - Botao abrir
		});
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa telaInfoPessoa) {
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaPessoa().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa 
				try 
				{
					TelaPessoa telaPessoa = telaInfoPessoa.getTelaInfoPessoa().getTelaPessoa();
					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					String login = telaPessoa.getLoginField().getTexto();
					String senha = telaPessoa.getSenhaField().getTexto();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					controlador_Principal.getPessoa_Outrem().setNome(nome);
					controlador_Principal.getPessoa_Outrem().setCpf(cpf);
					controlador_Principal.getPessoa_Outrem().setData_nascimento(DateUtil.getDateSQL(data));
					controlador_Principal.getPessoa_Outrem().setSexo(sexo);
					controlador_Principal.getPessoa_Outrem().setUser_login(login);
					controlador_Principal.getPessoa_Outrem().setUser_senha(senha);
					controlador_Principal.getPessoa_Outrem().setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(controlador_Principal.getPessoa_Outrem());
					controlador_Principal.gettPessoa().fireTableDataChanged();
					
					telaInfoPessoa.getTelaInfoPessoa()
					.getTelaPessoa()
					.getTelaCadastroEdicao()
					.escondeBtn();
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar dados", e.getMessage());
				}
				
				
				
			});
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaContatoCaracteristica().getBotao()
		.addActionListener(ActionEvent->{
			//TODO - Add Contato
			try 
			{
				TelaContatoCaracteristica telaContato = telaInfoPessoa.getTelaInfoPessoa()
						.getTelaContatoCaracteristica();

				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();

				Contato c = controlador_Principal.getPessoa_Outrem().getContato();

				if(c == null) 
					c = new Contato();
				if(c.getId() <= 0)
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					c.setPessoa(controlador_Principal.getPessoa_Outrem());
					Fachada.getInstance().inserir(c);
				}
				else 
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
				}
				
				telaInfoPessoa.getTelaInfoPessoa()
				.getTelaContatoCaracteristica()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
			} 
			catch (ValidacaoException e)
			{
				JInternal_TelaAlerta.showAlerta("Erro ao atualizar dados", e.getMessage());
			}
		});
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaContatoCaracteristica()
			.getBtAdicionar().addActionListener(ActionEvent->{
				//TODO - Add Caracteristica Extra
				
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoTarefa telaInfoTarefa) {
		
		telaInfoTarefa.getTelaInfoTarefa().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update tarefa
				try 
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa();
					
					String nome = telaTarefa.getNomeTarefaField().getTexto();
					String descr = telaTarefa.getDescricaoTextArea().getText();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					String prior = (String) telaTarefa.getPrioridadeComboBox().getSelectedItem();

					LocalTime time = telaTarefa.getHorario().getLocalTime();
					Date date = telaTarefa.getDateChooser().getDate();

					LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);

					String horario = localDateTime.toString();

					controlador_Principal.getTarefa_Atual().setNome(nome);
					controlador_Principal.getTarefa_Atual().setDescricao(descr);
					controlador_Principal.getTarefa_Atual().setConcluida(finalizada);
					controlador_Principal.getTarefa_Atual().setPrioridade(prior);
					controlador_Principal.getTarefa_Atual().setHorario_tarefa(horario);

					Fachada.getInstance().atualizar(controlador_Principal.getTarefa_Atual());
					controlador_Principal.gettTarefa().fireTableDataChanged();
					
					telaInfoTarefa.getTelaInfoTarefa().getTelaCadastroEdicao().escondeBtn();
				} catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar tarefa", e.getMessage());
				}
				
			});
		
		telaInfoTarefa.getTelaInfoTarefa().getChckbxFinalizada()
			.addItemListener(ItemEvent->{
				//TODO - Marcar/Desmarcar como finalizada
				try
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					controlador_Principal.getTarefa_Atual().setConcluida(finalizada);
					Fachada.getInstance().atualizar(controlador_Principal.getTarefa_Atual());
					controlador_Principal.gettTarefa().fireTableDataChanged();
					
					
					controlador_Principal.getEtapa_Atual().setPorcentagem_andamento(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(controlador_Principal.getEtapa_Atual())
							);
					
					controlador_Principal
						.getjInternal_TelaInfoEtapa()
						.getTelaEtapa_Tarefas()
						.getTelaEtapa()
						.getBarraProgressBar()
						.setValue(
								Math.round(controlador_Principal.getEtapa_Atual().getPorcentagem_andamento())
								);
				} catch (ValidacaoException e) {
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar tarefa", e.getMessage());
				}
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoProjeto_Etapas telaInfoProjetoEtapas) {
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaProjeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update Projeto
				try 
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaProjeto telaProjeto = telaInfoProjetoEtapas.getTelaProjeto_Etapas()
							.getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					controlador_Principal.getProjeto_Atual().setNome(nome);
					controlador_Principal.getProjeto_Atual().setDescricao(descr);
					controlador_Principal.getProjeto_Atual().setData_inicio(DateUtil.getDateSQL(dataI));
					controlador_Principal.getProjeto_Atual().setData_fim(DateUtil.getDateSQL(dataF));
					Fachada.getInstance().atualizar(controlador_Principal.getProjeto_Atual());
					controlador_Principal.gettProjeto().fireTableDataChanged();
					
					int valorA = Fachada.getInstance()
							.getBoProjeto()
							.andamento_Projeto(
								controlador_Principal.getProjeto_Atual()
								);
					
					((TelaInfoProjeto)telaProjeto).getProgressBar().setValue(valorA);
					
					telaInfoProjetoEtapas
					.getTelaProjeto_Etapas()
					.getTelaProjeto()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
				}
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar info. projeto", e.getMessage());
				}
				
				
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
				//TODO - Adicionar etapa
				try
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					controlador_Principal.getjInternal_TelaCadastro_Etapa().queroFoco();
					
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				catch (PropertyVetoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaColaboradores()
			.getBtAdicionarColaborador().addActionListener(ActionEvent->{
				//TODO - Adicionar Colaborador
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa_Projetos telaInfoPessoaProjetos) {
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getTelaPessoa().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa
				try 
				{
					TelaPessoa telaPessoa = telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
							.getTelaInfoPessoa().getTelaPessoa();

					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					String login = telaPessoa.getLoginField().getTexto();
					String senha = telaPessoa.getSenhaField().getTexto();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					controlador_Principal.getPessoa_Logada().setNome(nome);
					controlador_Principal.getPessoa_Logada().setCpf(cpf);
					controlador_Principal.getPessoa_Logada().setData_nascimento(DateUtil.getDateSQL(data));
					controlador_Principal.getPessoa_Logada().setSexo(sexo);
					controlador_Principal.getPessoa_Logada().setUser_login(login);
					controlador_Principal.getPessoa_Logada().setUser_senha(senha);
					controlador_Principal.getPessoa_Logada().setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(controlador_Principal.getPessoa_Logada());
					controlador_Principal.gettPessoa().fireTableDataChanged();
					
					telaInfoPessoaProjetos
					.getTelaInfoPessoaProjetos()
					.getTelaInfoPessoa()
					.getTelaPessoa()
					.getTelaCadastroEdicao()
					.escondeBtn();
				}
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Atualizar Info de Pessoas ", e.getMessage());
				}
				
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getTelaContatoCaracteristica().getBotao()
		.addActionListener(ActionEvent->{
			//TODO - COntato
			try 
			{

				TelaContatoCaracteristica telaContato = telaInfoPessoaProjetos
						.getTelaInfoPessoaProjetos()
						.getTelaInfoPessoa()
						.getTelaContatoCaracteristica();

				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();


				Contato c = controlador_Principal.getPessoa_Logada().getContato();

				if(c == null) 
					c = new Contato();
				if(c.getId() <= 0)
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					c.setPessoa(controlador_Principal.getPessoa_Logada());
					Fachada.getInstance().inserir(c);
				}
				else 
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
				}
				
				telaInfoPessoaProjetos
				.getTelaInfoPessoaProjetos()
				.getTelaInfoPessoa()
				.getTelaContatoCaracteristica()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
				
			} 
			catch (ValidacaoException e)
			{
				JInternal_TelaAlerta.showAlerta("Erro ao atualizar dados", e.getMessage());
			}
		});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaInfoPessoa().getTelaContatoCaracteristica().getBtAdicionar()
			.addActionListener(ActionEvent->{
				//TODO - Adicionar caracteristica Extra
				try 
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					CaracteristicaExtra c = new CaracteristicaExtra();
					c.setPessoa(controlador_Principal.getPessoa_Logada());
					c.setNome("");
				
					Fachada.getInstance().inserir(c);
					
					controlador_Principal.gettCaracteristicaExtra().addValor(c);
					
					/*LogUpdate log = new LogUpdate();
					
					Fachada.getInstance().gerarLogInsercao(c, controlador_Principal.pessoa_static, log);
					tLogUpdate.addValor(log);*/
				} 
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Adicionar Caracteristica", e.getMessage());
				}
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaProjetos().getBtCriarUmNovo()
			.addActionListener(ActionEvent->{
				//TODO - Cadastrar Novo projeto
				try
				{
					controlador_Principal.getjInternal_TelaCadastro_Projeto().queroFoco();
				}
				catch (PropertyVetoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaProjetos().getBtnGerarRelatorio()
			.addActionListener(ActionEvent->{
				//TODO - Gerar Relatorio projeto
				
				int index = telaInfoPessoaProjetos
						.getTelaInfoPessoaProjetos()
						.getTelaProjetos()
						.getTable()
						.getSelectedRow();
				
				if(index >= 0)
				{
					int escolha = MeuJFileChooser.getInstance().exibirParaPDF(telaInfoPessoaProjetos);
					if(escolha == MeuJFileChooser.APPROVE_OPTION)
					{
						try 
						{
							Projeto projeto = controlador_Principal.gettProjeto().getValor(index);
							String path = MeuJFileChooser.getInstance().getSelectedFile().getAbsolutePath();
						
						
							projeto.setColaboradores(Fachada.getInstance().getBoColaborador().buscarPorProjeto(projeto));
							
							projeto.getColaboradores().forEach(colaborador->{
								try
								{
									colaborador.setPessoa(
											Fachada.getInstance()
											.getBoPessoa()
											.buscar(
													colaborador.getPessoa().getId()
													)
									);
								}
								catch (ValidacaoException e)
								{
									e.printStackTrace();
								}
							});
							
							projeto.setEtapas(Fachada.getInstance().getBoEtapa().buscarPorProjeto(projeto));
							
							projeto.getEtapas().forEach(etapa->{
								try
								{
									etapa.setTarefas(
											Fachada.getInstance()
											.getBoTarefa()
											.buscarPorEtapa(etapa)
									);
								}
								catch (ValidacaoException e)
								{
									e.printStackTrace();
								}
							});
							
							
							
							DAOResRelatorio.getInstance().gerarRelatorio(
									telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
									.getTelaProjetos().getComboBox().getSelectedIndex(),
									projeto,
									path
									);
						}
						catch (ValidacaoException | FileNotFoundException | DocumentException e)
						{
							e.printStackTrace();
							JInternal_TelaAlerta.showAlerta("Erro", e.getMessage());
						}
					}
				}
				
			});
		
		
	}
	

	private void adicionarEventoJInternal(
			JInternal_TelaInfoProjeto_Etapas_Simples telaInfoProjeto_Etapas_Simples) {
		
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaProjeto()
			.getBotao1().addActionListener(ActionEvent->{
				//TODO - Update Projeto
				try {
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaProjeto telaProjeto = telaInfoProjeto_Etapas_Simples
							.getTelaProjeto_Etapas_Simples().getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					controlador_Principal.getProjeto_Atual().setNome(nome);
					controlador_Principal.getProjeto_Atual().setDescricao(descr);
					controlador_Principal.getProjeto_Atual().setData_inicio(DateUtil.getDateSQL(dataI));
					controlador_Principal.getProjeto_Atual().setData_fim(DateUtil.getDateSQL(dataF));
					Fachada.getInstance().atualizar(controlador_Principal.getProjeto_Atual());
					controlador_Principal.gettProjeto().fireTableDataChanged();
					
					int valorA = Fachada.getInstance()
							.getBoProjeto()
							.andamento_Projeto(
								controlador_Principal.getProjeto_Atual()
								);
					
					((TelaInfoProjeto)telaProjeto).getProgressBar().setValue(valorA);
					
					
				}
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar info. projeto", e.getMessage());
				}
			});
	
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
			//TODO - Adicionar etapa
				try {
					
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					controlador_Principal.getjInternal_TelaCadastro_Etapa().queroFoco();
				} 
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				catch (PropertyVetoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
			});
	}
	
	
	public void exibirJInternalInfoTarefa(Tarefa t) throws PropertyVetoException 
	{
			controlador_Principal.setTarefa_Atual(t);
			preencherTelaTarefa(controlador_Principal.getjInternal_TelaInfoTarefa().getTelaInfoTarefa(), controlador_Principal.getTarefa_Atual());
			controlador_Principal.getjInternal_TelaInfoTarefa().queroFoco();
	}
	
	public void exibirJInternalInfoEtapa(Etapa e) throws PropertyVetoException, ValidacaoException
	{
		controlador_Principal.setEtapa_Atual(e);
		
		TelaEtapa tE = controlador_Principal.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaEtapa();
		
		atualizarDadoEtapa(tE, controlador_Principal.getEtapa_Atual(), controlador_Principal.gettTarefa());
		controlador_Principal.getjInternal_TelaInfoEtapa().queroFoco();
	}
	
	public void exibirJInternalInfoProjetoEtapa(Projeto p) throws ValidacaoException, PropertyVetoException
	{
		TelaProjeto tP = controlador_Principal.getjInternal_TelaInfoProjeto_Etapas()
				.getTelaProjeto_Etapas()
				.getTelaProjeto();
		
		atualizarDadoProjeto(tP,
				controlador_Principal.getProjeto_Atual(),
				controlador_Principal.gettEtapa(),
				controlador_Principal.gettColaborador()
				);
		controlador_Principal.getjInternal_TelaInfoProjeto_Etapas().queroFoco();
	}
	
	public void exibirJInternalInfoProjetoEtapaSimples(Projeto p) throws ValidacaoException, PropertyVetoException
	{
		TelaProjeto tP = controlador_Principal.getjInternal_TelaInfoProjeto_Etapas_Simples()
				.getTelaProjeto_Etapas_Simples()
				.getTelaProjeto();
		
		atualizarDadoProjetoSimples(tP, controlador_Principal.getProjeto_Atual(), controlador_Principal.gettEtapa());
		controlador_Principal.getjInternal_TelaInfoProjeto_Etapas_Simples().queroFoco();
	}
	
	public void exibirJInternalInfoColaborador(Colaborador c) throws ValidacaoException, PropertyVetoException
	{
		controlador_Principal.setColaborador_Atual(c);
		
		controlador_Principal.setPessoa_Outrem((Pessoa) Fachada
				.getInstance()
				.buscar(
						Pessoa.class,
						controlador_Principal.getColaborador_Atual()
						.getPessoa()
						.getId()
						));
		
		TelaInfoPessoa tIP = controlador_Principal.getjInternal_TelaInfoPessoa().getTelaInfoPessoa();
		
		atualizarDadoPessoa(tIP, controlador_Principal.getPessoa_Outrem(), controlador_Principal.gettCaracteristicaExtra2());
		controlador_Principal.getjInternal_TelaInfoPessoa().queroFoco();
	}
	
	public void exibirJInternalInfoColaboracoes(Colaborador c) throws ValidacaoException, PropertyVetoException
	{
		controlador_Principal.setColaborador_Atual(c);
		
		controlador_Principal.setProjeto_Atual((Projeto) Fachada.getInstance()
				.buscar(
						Projeto.class,
						controlador_Principal.getColaborador_Atual()
						.getProjeto()
						.getId()
						));
		
		TelaProjeto tP = controlador_Principal.getjInternal_TelaInfoProjeto_Etapas_Simples()
				.getTelaProjeto_Etapas_Simples()
				.getTelaProjeto();
	
		atualizarDadoProjetoSimples(tP, controlador_Principal.getProjeto_Atual(), controlador_Principal.gettEtapa());
		controlador_Principal.getjInternal_TelaInfoProjeto_Etapas_Simples().queroFoco();
	}
	
	public void exibirJInternalInfoPessoa(Pessoa p) throws ValidacaoException, PropertyVetoException
	{
		controlador_Principal.setPessoa_Outrem(p);
		
		TelaInfoPessoa tIP = controlador_Principal.getjInternal_TelaInfoPessoa().getTelaInfoPessoa();
		
		atualizarDadoPessoa(tIP, controlador_Principal.getPessoa_Outrem(), controlador_Principal.gettCaracteristicaExtra2());
		controlador_Principal.getjInternal_TelaInfoPessoa().queroFoco();
	}

	public void exibirJInternalInfoMinhaPessoa(Pessoa p) throws ValidacaoException, PropertyVetoException
	{
		TelaInfoPessoa tIP = controlador_Principal.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos().getTelaInfoPessoa();
	
		atualizarDadoMinhaPessoa(tIP,
				controlador_Principal.getPessoa_Logada(),
				controlador_Principal.gettCaracteristicaExtra(),
				controlador_Principal.gettProjeto(),
				controlador_Principal.gettColaboracoes()
				);
		controlador_Principal.getjInternal_TelaInfoPessoa_Projetos().queroFoco();
	}
	
	private void preencherTelaPessoa(TelaPessoa telaPessoa, Pessoa pessoa) 
	{
		telaPessoa.getNomeField().setDescricao("Nome");
		telaPessoa.getNomeField().setTexto(pessoa.getNome());
		telaPessoa.getCampoFormatadoCPF().setText(pessoa.getCpf());
		telaPessoa.getNascimentoDateChooser().setDate(DateUtil.getDate(pessoa.getData_nascimento()));
		telaPessoa.getSexoComboBox().setSelectedItem(pessoa.getSexo());
		telaPessoa.getLoginField().setTexto(pessoa.getUser_login());
		telaPessoa.getSenhaField().setText("");
		telaPessoa.getSenhaField().setDescricao("Nova Senha");
		
		telaPessoa.getRdbtnSim().setSelected(pessoa.isDisponibilidade());
		telaPessoa.getRdbtnNo().setSelected(!pessoa.isDisponibilidade());
	}
	
	private void preencherTelaContato(TelaContatoCaracteristica telaContato, Contato contato) 
	{
		telaContato.getEmailField().setText(contato.getEmail());
		telaContato.getCelularField().setText(contato.getCelular());
		telaContato.getTelefoneField().setText(contato.getCelular());
	}
	
	private void preencherTelaProjeto(TelaProjeto telaProjeto, Projeto projeto) throws ValidacaoException
	{
		
		telaProjeto.getNomeProjetoField().setDescricao("Nome do Projeto");
		telaProjeto.getNomeProjetoField().setTexto(projeto.getNome());
		telaProjeto.getDescricaoTextArea().setText(projeto.getDescricao());
		telaProjeto.getDataInicioDateChooser().setDate(DateUtil.getDate(projeto.getData_inicio()));
		telaProjeto.getDataFimDateChooser().setDate(DateUtil.getDate(projeto.getData_fim()));
		
		int valorA = Fachada.getInstance()
				.getBoProjeto()
				.andamento_Projeto(
						projeto
						);
		
		((TelaInfoProjeto)telaProjeto).getProgressBar().setValue(valorA);
	}
	
	private void preencherTelaEtapa(TelaEtapa telaEtapa, Etapa etapa)
	{
		telaEtapa.getNomeEtapaField().setDescricao("Nome da Etapa");
		telaEtapa.getNomeEtapaField().setTexto(etapa.getNome());
		telaEtapa.getDescricaoTextArea().setText(etapa.getDescricao());
		telaEtapa.getBarraProgressBar().setValue(Math.round(etapa.getPorcentagem_andamento()));
	}
	
	private void preencherTelaTarefa(TelaInfoTarefa telaTarefa, Tarefa tarefa) 
	{
		telaTarefa.getNomeTarefaField().setDescricao("Nome da Tarefa");
		telaTarefa.getNomeTarefaField().setTexto(tarefa.getNome());
		telaTarefa.getDescricaoTextArea().setText(tarefa.getDescricao());
		telaTarefa.getChckbxFinalizada().setSelected(tarefa.isConcluida());
		telaTarefa.getPrioridadeComboBox().setSelectedItem(tarefa.getPrioridade());

		String[] horaData = tarefa.getHorario_tarefa().split("T");
		
		System.out.println("Horario "+ tarefa.getHorario_tarefa());
		System.out.println("HoraData " + horaData.toString());
		
		String[] hora = horaData[1].split(":");
		
		if(hora.length < 3)
			hora = new String[]{hora[0], hora[1], "00"};
		
		telaTarefa.getHorario().setLocalTime(hora[0], hora[1], hora[2]);
		telaTarefa.getDateChooser().setDate(DateUtil.getDate(horaData[0]));
		
	}
	
	private void atualizarDadoProjetoSimples(TelaProjeto tp, Projeto p, TEtapa te) throws ValidacaoException
	{
		List<Etapa> lEtapa = Fachada.getInstance().getBoEtapa().buscarPorProjeto(p);
		te.addAll(lEtapa);
		p.setEtapas(lEtapa);
		preencherTelaProjeto(tp, p);
	}
	
	private void atualizarDadoProjeto(TelaProjeto tp, Projeto p, TEtapa te, TColaborador tc) throws ValidacaoException
	{
		atualizarDadoProjetoSimples(tp, p, te);
		
		List<Colaborador> lColaborador = Fachada.getInstance().getBoColaborador().buscarPorProjeto(p);
		tc.addAll(lColaborador);
		p.setColaboradores(lColaborador);
	}
	
	private void atualizarDadoPessoa(TelaInfoPessoa tIP, Pessoa p, TCaracteristicaExtra tce) throws ValidacaoException
	{
		preencherTelaPessoa(tIP.getTelaPessoa(),p);
	
		Contato c = Fachada.getInstance().getBoContato().buscarPorPessoa(p);
		
		if(c == null) c = new Contato();
		
		List<CaracteristicaExtra> lc = Fachada.getInstance().getBoCaracteristicaExtra().buscaPorPessoa(p);
		
		preencherTelaContato(tIP.getTelaContatoCaracteristica(), c);
		
		p.setContato(c);
		p.setCaracteristicas(lc);
		
		tce.addAll(lc);
	}
	
	private void atualizarDadoMinhaPessoa(TelaInfoPessoa tIP, Pessoa p, TCaracteristicaExtra tce, TProjeto tP, TColaboracoes tC) throws ValidacaoException
	{
		atualizarDadoPessoa(tIP, p, tce);
		List<Projeto> lp = Fachada.getInstance().getBoProjeto().buscarPorPessoa(p);
		tP.addAll(lp);
		List<Colaborador> lcolaboracoes = Fachada.getInstance().getBoColaborador().buscarPorPessoa(p);
		tC.addAll(lcolaboracoes);
		p.setProjetos(lp);
		p.setColaboradores(lcolaboracoes);
	}
	
	private void atualizarDadoEtapa(TelaEtapa tE, Etapa e, TTarefa tT) throws ValidacaoException
	{
		preencherTelaEtapa(tE,e);
		List<Tarefa> t = Fachada.getInstance().getBoTarefa().buscarPorEtapa(e);
		tT.addAll(t);
		e.setTarefas(t);
	}
	
}
