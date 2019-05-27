package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

import br.com.pbd2019_1.dao.DAOConfigDefault;
import br.com.pbd2019_1.dao.DAOResRelatorio;
import br.com.pbd2019_1.dao.DAOResSQL;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
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
import br.com.pbd2019_1.view.Horario;
import br.com.pbd2019_1.view.JInternal_TabelaLogs;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
import br.com.pbd2019_1.view.JInternal_TelaAgendarBackup;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas_Simples;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.MeuJFileChooser;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaGraficoPessoa;
import br.com.pbd2019_1.view.TelaInfoPessoa;
import br.com.pbd2019_1.view.TelaInfoProjeto;
import br.com.pbd2019_1.view.TelaInfoTarefa;
import br.com.pbd2019_1.view.TelaInserirSQL;
import br.com.pbd2019_1.view.TelaMiniPessoa1;
import br.com.pbd2019_1.view.TelaMiniPessoa2;
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
		adicionarEventoJInternal(controlador_Principal.getjInternal_TabelaPessoas());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TabelaPessoasColaboradores());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TabelaLogs());
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaAgendarBackup());
	}
	
	
	private void adicionarEventoJInternal(JInternal_TelaAgendarBackup jInternal_TelaAgendarBackup)
	{
		jInternal_TelaAgendarBackup.getBtDefinirHorario()
		.addActionListener(ActionEvent->
		{
			Horario horario = controlador_Principal.getjInternal_TelaAgendarBackup().getHorario();
			DAOConfigDefault.setHorarioAgenda(horario.toString());
		});
		
		jInternal_TelaAgendarBackup.getBtResetarHorario()
		.addActionListener(ActionEvent->
		{
			Horario horario = controlador_Principal.getjInternal_TelaAgendarBackup().getHorario();
			
			DAOConfigDefault.setHorarioAgenda(null);
			horario.setLocalTime("00", "00", "00");
		});
	}

	private void adicionarEventoJInternal(JInternal_TabelaLogs jInternal_TabelaLogs) {
		
		jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getBuscarBtn()
		.addActionListener(ActionEvent->
		{
			try
			{
			List<LogUpdate> logs = null;
			
			int index = jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getEspecificarComboBox().getSelectedIndex();
			String tipo = (String) jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getTipoComboBox().getSelectedItem();
			String tabela = (String) jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getTabelaComboBox().getSelectedItem();
			String id = jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getIdTabelaCampoTexto().getTexto();
			String cpf_responsavel = jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getResponsavelCampoTexto().getTexto();
			Date data1 = jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getData1DateChooser().getDate();
			Date data2 = jInternal_TabelaLogs.getTelaLogs().getTelaPesquisaLog().getData2DateChooser().getDate();
			
			
			switch (index) {
				case 0:
					logs = Fachada.getInstance().getBoLogUpdate().buscarALL();
					break;
				case 1:
					String type = (tipo.equalsIgnoreCase("todos"))? "" : (tipo.equalsIgnoreCase("atualização"))? "UPDATE" : (tipo.equalsIgnoreCase("deleção"))? "DELETE" : tipo;
					logs = Fachada.getInstance().getBoLogUpdate().buscarEspecificado(type, (tabela.equalsIgnoreCase("todas"))? "" : tabela, id, cpf_responsavel, data1, data2);
					break;
				}
			controlador_Principal.gettLogUpdate().addAll(logs);
			}
			catch (ValidacaoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Buscar Logs", e.getMessage());
			}
		});
		
	}

	private void adicionarEventoJInternal(JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores) {
		
		jInternal_TabelaPessoasColaboradores
		.getTelaPessoas()
		.getBtAdicionarPessoa()
		.addActionListener(ActionEvent->{
			
			int index = jInternal_TabelaPessoasColaboradores
					.getTelaPessoas()
					.getTable()
					.getSelectedRow();
			if(index >= 0)
			try
			{
				
				Pessoa pessoa = controlador_Principal.gettPessoa().getValor(index);
				
				Colaborador colaborador = new Colaborador();
				colaborador.setData_ingresso(DateUtil.getDataAtual());
				colaborador.setPrivilegio("Visitante");
				colaborador.setPessoa(pessoa);
				colaborador.setProjeto(controlador_Principal.getProjeto_Atual());
				
				Fachada.getInstance().inserir(colaborador);
			
				controlador_Principal.gettColaborador().addValor(colaborador);
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(colaborador, controlador_Principal.getPessoa_Logada(), log);
				controlador_Principal.gettLogUpdate().addValor(log);
				
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao consultar a lista de pessoas", e.getMessage());
			}
		});
		
		jInternal_TabelaPessoasColaboradores
		.getTelaPessoas()
		.getBotao()
		.addActionListener(ActionEvent->
		{
			try
			{
				int index = jInternal_TabelaPessoasColaboradores.getTelaPessoas().getEspecificarComboBox().getSelectedIndex();

				if(index == 0)
				{
					controlador_Principal.gettPessoa().addAll((List<Pessoa>) Fachada.getInstance().getBoPessoa().buscarPessoasDiferentesDe(controlador_Principal.getPessoa_Logada()));
				}
				else
				{
					String nome = jInternal_TabelaPessoasColaboradores
							.getTelaPessoas().getNomeCampoTexto().getTexto();
					String cpf = jInternal_TabelaPessoasColaboradores
							.getTelaPessoas().getCpfCampoTexto().getTexto();
					int i = jInternal_TabelaPessoasColaboradores
							.getTelaPessoas().getDisponivelComboBox().getSelectedIndex();

					String disponibilidade = (i == 0)? "": (i == 1)? "true": "false";

					controlador_Principal.gettPessoa().addAll(
							(List<Pessoa>) Fachada.getInstance()
							.getBoPessoa()
							.buscarPessoasEspecificarDiferentesDe(controlador_Principal.getPessoa_Logada(), nome, cpf, disponibilidade)
							);
				}
			} catch (ValidacaoException e) {
				MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
			}
		});
	}

	private void adicionarEventoJInternal(JInternal_TabelaPessoas jInternal_TabelaPessoas) {

		jInternal_TabelaPessoas
		.getTelaPessoas()
		.getBtAdicionarPessoa()
		.addActionListener(ActionEvent->{
			
			try 
			{
				
				controlador_Principal.getjInternal_TelaCadastro_Pessoa().queroFoco();
			}
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela de Cadastro", e.getMessage());
			}
			
		});
		
		jInternal_TabelaPessoas
		.getTelaPessoas()
		.getBotao()
		.addActionListener(ActionEvent->
		{
			try
			{
				int index = jInternal_TabelaPessoas.getTelaPessoas().getEspecificarComboBox().getSelectedIndex();

				if(index == 0)
				{
					controlador_Principal.gettPessoa().addAll((List<Pessoa>) Fachada.getInstance().getBoPessoa().buscarPessoasDiferentesDe(controlador_Principal.getPessoa_Logada()));
				}
				else
				{
					String nome = jInternal_TabelaPessoas
							.getTelaPessoas().getNomeCampoTexto().getTexto();
					String cpf = jInternal_TabelaPessoas
							.getTelaPessoas().getCpfCampoTexto().getTexto();
					int i = jInternal_TabelaPessoas
							.getTelaPessoas().getDisponivelComboBox().getSelectedIndex();

					String disponibilidade = (i == 0)? "": (i == 1)? "true": "false";

					controlador_Principal.gettPessoa().addAll(
							(List<Pessoa>) Fachada.getInstance()
							.getBoPessoa()
							.buscarPessoasEspecificarDiferentesDe(controlador_Principal.getPessoa_Logada(), nome, cpf, disponibilidade)
							);
				}
			} catch (ValidacaoException e) {
				MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
			}
		});
		
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

					String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getEtapa_Atual());
					
					
					controlador_Principal.getEtapa_Atual().setNome(nome);
					controlador_Principal.getEtapa_Atual().setDescricao(descr);
					Fachada.getInstance().atualizar(controlador_Principal.getEtapa_Atual());
					controlador_Principal.gettEtapa().fireTableDataChanged();
					
					telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getTelaCadastroEdicao().escondeBtn();
				
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							controlador_Principal.getEtapa_Atual(), controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Atualizar Etapa", e.getMessage());
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
					MeuJDialog.exibirAlertaErro(null, "Não Tem permição", e.getMessage());
				} 
				catch (PropertyVetoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir ", e.getMessage());
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
			int op = MeuJFileChooser.getInstance().exibirParaSQL(jInternal_TelaInserirSQL);
			if(op == MeuJFileChooser.APPROVE_OPTION)
			{
				try 
				{
					String caminho = MeuJFileChooser.getInstance().getSelectedFile().getAbsolutePath();
					DAOResSQL.EscreverArquivo(caminho, telaInserirSQL.getTextArea().getText());
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(jInternal_TelaInserirSQL, "Erro", e.getMessage());
				}
			}
		});

		telaInserirSQL.getBtnAbrir()
		.addActionListener(ActionEvent->{
			//TODO - Botao abrir
			int op = MeuJFileChooser.getInstance().exibirParaSQL(jInternal_TelaInserirSQL);
			if(op == MeuJFileChooser.APPROVE_OPTION)
			{
				try 
				{
					String caminho = MeuJFileChooser.getInstance().getSelectedFile().getAbsolutePath();
					String textoSQL = DAOResSQL.LerArquivo(caminho);
					telaInserirSQL.getTextArea().setText(textoSQL);
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(jInternal_TelaInserirSQL, "Erro", e.getMessage());
				}
			}
		});
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa telaInfoPessoa) {
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaMiniPessoa1().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa 
				try 
				{
					TelaMiniPessoa1 telaPessoa = telaInfoPessoa.getTelaInfoPessoa().getTelaMiniPessoa1();
					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getPessoa_Outrem());
					
					controlador_Principal.getPessoa_Outrem().setNome(nome);
					controlador_Principal.getPessoa_Outrem().setCpf(cpf);
					controlador_Principal.getPessoa_Outrem().setData_nascimento(DateUtil.getDateSQL(data));
					controlador_Principal.getPessoa_Outrem().setSexo(sexo);
					controlador_Principal.getPessoa_Outrem().setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(controlador_Principal.getPessoa_Outrem());
					controlador_Principal.gettPessoa().fireTableDataChanged();
					
					telaInfoPessoa.getTelaInfoPessoa()
					.getTelaMiniPessoa1()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							controlador_Principal.getPessoa_Outrem(), controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
				}
			});
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaMiniPessoa2().getBotao1()
		.addActionListener(ActionEvent->{
			//TODO - Update Pessoa 
			try 
			{
				TelaMiniPessoa2 telaPessoa = telaInfoPessoa.getTelaInfoPessoa().getTelaMiniPessoa2();
				String login = telaPessoa.getLoginField().getTexto();
				String senha = telaPessoa.getSenhaField().getTexto();

				String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getPessoa_Outrem());
				
				controlador_Principal.getPessoa_Outrem().setUser_login(login);
				controlador_Principal.getPessoa_Outrem().setUser_senha(senha);
				Fachada.getInstance().atualizar(controlador_Principal.getPessoa_Outrem());
				controlador_Principal.gettPessoa().fireTableDataChanged();
				
				telaInfoPessoa.getTelaInfoPessoa().getTelaMiniPessoa2()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
						antes,
						controlador_Principal.getPessoa_Outrem(), controlador_Principal.getPessoa_Logada(), log);
				controlador_Principal.gettLogUpdate().addValor(log);
				
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
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
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(
							c, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				else 
				{
					String[] antes = Fachada.getInstance().gerarLog(c);
					
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							c, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				
				telaInfoPessoa.getTelaInfoPessoa()
				.getTelaContatoCaracteristica()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
			} 
			catch (ValidacaoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
			}
		});
		/*
		telaInfoPessoa.getTelaInfoPessoa().getTelaContatoCaracteristica()
			.getBtAdicionar().addActionListener(ActionEvent->{
				//TODO - Add Caracteristica Extra
				
			});
		*/
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

					
					String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getTarefa_Atual());
					
					controlador_Principal.getTarefa_Atual().setNome(nome);
					controlador_Principal.getTarefa_Atual().setDescricao(descr);
					controlador_Principal.getTarefa_Atual().setConcluida(finalizada);
					controlador_Principal.getTarefa_Atual().setPrioridade(prior);
					controlador_Principal.getTarefa_Atual().setHorario_tarefa(horario);

					Fachada.getInstance().atualizar(controlador_Principal.getTarefa_Atual());
					controlador_Principal.gettTarefa().fireTableDataChanged();
					
					telaInfoTarefa.getTelaInfoTarefa().getTelaCadastroEdicao().escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(
							antes,
							controlador_Principal.getTarefa_Atual(), controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
					
				} catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar tarefa", e.getMessage());
				}
			});
		
		telaInfoTarefa.getTelaInfoTarefa().getChckbxFinalizada()
			.addActionListener(ActionEvent->{
				//TODO - Marcar/Desmarcar como finalizada
				try
				{
					if(controlador_Principal.isBool_Colaborador_Ativado())
						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
						{
							TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa();
							boolean b = telaTarefa.getChckbxFinalizada().isSelected();
							telaTarefa.getChckbxFinalizada().setSelected(!b);
							throw new ValidacaoException("Não tem permição");
						}
					
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
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar tarefa", e.getMessage());
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

					String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getProjeto_Atual());
					
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
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							controlador_Principal.getProjeto_Atual(), controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar info. projeto", e.getMessage());
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
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				catch (PropertyVetoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tela de Cadastro", e.getMessage());
				}
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaColaboradores()
			.getBtAdicionarColaborador().addActionListener(ActionEvent->{
				try
				{
					controlador_Principal.gettPessoa().getList().clear();
					controlador_Principal.gettPessoa().fireTableDataChanged();
					controlador_Principal.getjInternal_TabelaPessoasColaboradores().queroFoco();
				} 
				catch (PropertyVetoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tabela de Pessoas", e.getMessage());
				}
			});
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa_Projetos telaInfoPessoaProjetos) {
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getTelaMiniPessoa1().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa
				try 
				{
					TelaMiniPessoa1 telaPessoa = telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
							.getTelaInfoPessoa().getTelaMiniPessoa1();

					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getPessoa_Logada());
					
					controlador_Principal.getPessoa_Logada().setNome(nome);
					controlador_Principal.getPessoa_Logada().setCpf(cpf);
					controlador_Principal.getPessoa_Logada().setData_nascimento(DateUtil.getDateSQL(data));
					controlador_Principal.getPessoa_Logada().setSexo(sexo);
					controlador_Principal.getPessoa_Logada().setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(controlador_Principal.getPessoa_Logada());
					controlador_Principal.gettPessoa().fireTableDataChanged();
					
					telaInfoPessoaProjetos
					.getTelaInfoPessoaProjetos()
					.getTelaInfoPessoa()
					.getTelaMiniPessoa1()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(
							antes,
							controlador_Principal.getPessoa_Logada(), controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Atualizar Info de Pessoas ", e.getMessage());
				}
				
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getTelaMiniPessoa2().getBotao1()
		.addActionListener(ActionEvent->{
			//TODO - Update Pessoa
			try 
			{
				TelaMiniPessoa2 telaPessoa = telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
						.getTelaInfoPessoa().getTelaMiniPessoa2();

				String login = telaPessoa.getLoginField().getTexto();
				String senha = telaPessoa.getSenhaField().getTexto();
				
				String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getPessoa_Logada());
				
				controlador_Principal.getPessoa_Logada().setUser_login(login);
				controlador_Principal.getPessoa_Logada().setUser_senha(senha);
				Fachada.getInstance().atualizar(controlador_Principal.getPessoa_Logada());
				controlador_Principal.gettPessoa().fireTableDataChanged();
				
				telaInfoPessoaProjetos
				.getTelaInfoPessoaProjetos()
				.getTelaInfoPessoa()
				.getTelaMiniPessoa2()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().gerarLogUpdate(
						antes,
						controlador_Principal.getPessoa_Logada(), controlador_Principal.getPessoa_Logada(), log);
				controlador_Principal.gettLogUpdate().addValor(log);
			}
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Atualizar Info de Pessoas ", e.getMessage());
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
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogInsercao(
							c, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				else 
				{
					String[] antes = Fachada.getInstance().gerarLog(c);
					
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							c, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
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
				MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
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
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(
							c, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Adicionar Caracteristica", e.getMessage());
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
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tela de Cadastro", e.getMessage());
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
									MeuJDialog.exibirAlertaErro(null, "Erro ao Consultar dados de colaborador", e.getMessage());
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
									MeuJDialog.exibirAlertaErro(null, "Erro ao consultar dados de etapa", e.getMessage());
								}
							});
							
							
							
							DAOResRelatorio.getInstance().gerarRelatorio(
									telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
									.getTelaProjetos().getComboBox().getSelectedIndex(),
									projeto,
									path
									);
							
							MeuJDialog.exibirAlertaInfo(null, "Relatório Gerado", "Gerado em: "+ path + ".pdf");
						}
						catch (ValidacaoException | FileNotFoundException | DocumentException e)
						{
							MeuJDialog.exibirAlertaErro(null, "Erro ao gerar Relatório", e.getMessage());
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

					String[] antes = Fachada.getInstance().gerarLog(controlador_Principal.getProjeto_Atual());
					
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
					
					telaInfoProjeto_Etapas_Simples
					.getTelaProjeto_Etapas_Simples()
					.getTelaProjeto().getTelaCadastroEdicao().escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							controlador_Principal.getProjeto_Atual(), controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar info. projeto", e.getMessage());
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
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				catch (PropertyVetoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao exibir Tela de Cadastro", e.getMessage());
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
	
	public void exibirJInternalTabelaLog()
	{
		
	}
	
	private void preencherTelaMiniPessoa1(TelaMiniPessoa1 telaPessoa, Pessoa pessoa) 
	{
		telaPessoa.getNomeField().setDescricao("Nome");
		telaPessoa.getNomeField().setTexto(pessoa.getNome());
		telaPessoa.getCampoFormatadoCPF().setText(pessoa.getCpf());
		telaPessoa.getNascimentoDateChooser().setDate(DateUtil.getDate(pessoa.getData_nascimento()));
		telaPessoa.getSexoComboBox().setSelectedItem(pessoa.getSexo());
		
		telaPessoa.getRdbtnSim().setSelected(pessoa.isDisponibilidade());
		telaPessoa.getRdbtnNo().setSelected(!pessoa.isDisponibilidade());
	}
	
	private void preencherTelaMiniPessoa2(TelaMiniPessoa2 telaPessoa, Pessoa pessoa) 
	{
		telaPessoa.getLoginField().setTexto(pessoa.getUser_login());
		telaPessoa.getSenhaField().setText("");
		telaPessoa.getSenhaField().setDescricao("Nova Senha");
		
	}
	
	private void preencherTelaContato(TelaContatoCaracteristica telaContato, Contato contato) 
	{
		telaContato.getEmailField().setText(contato.getEmail());
		telaContato.getCelularField().setText(contato.getCelular());
		telaContato.getTelefoneField().setText(contato.getTelefone());
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
	
	private void atualizarDadoPessoaDesempenho(TelaGraficoPessoa tgp, Pessoa pessoa) throws ValidacaoException
	{
		int[] etapas = Fachada.getInstance().getBoPessoa().buscarDesempenhoEtapas(pessoa);
		int[] tarefas = Fachada.getInstance().getBoPessoa().buscarDesempenhoTarefas(pessoa);
		tgp.atualizarGrafico(tarefas, etapas);
	}
	
	private void atualizarDadoPessoa(TelaInfoPessoa tIP, Pessoa p, TCaracteristicaExtra tce) throws ValidacaoException
	{
		preencherTelaMiniPessoa1(tIP.getTelaMiniPessoa1(), p);
		preencherTelaMiniPessoa2(tIP.getTelaMiniPessoa2(), p);
		
		
		Contato c = Fachada.getInstance().getBoContato().buscarPorPessoa(p);
		
		if(c == null) c = new Contato();
		
		List<CaracteristicaExtra> lc = Fachada.getInstance().getBoCaracteristicaExtra().buscaPorPessoa(p);
		
		preencherTelaContato(tIP.getTelaContatoCaracteristica(), c);
		atualizarDadoPessoaDesempenho(tIP.getTelaGraficoPessoa(), p);
		
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
