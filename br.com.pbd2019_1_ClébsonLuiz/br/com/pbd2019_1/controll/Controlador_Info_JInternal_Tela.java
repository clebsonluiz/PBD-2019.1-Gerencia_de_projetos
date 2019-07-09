package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

import br.com.pbd2019_1.dao.DAOResRelatorio;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternal_TabelaLogs;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoaOutrem;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.MeuJFileChooser;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaMiniPessoa1;
import br.com.pbd2019_1.view.TelaMiniPessoa2;

public class Controlador_Info_JInternal_Tela {

	private Controlador_Principal ctrl_P;
	
	public Controlador_Info_JInternal_Tela(Controlador_Principal controlador_Principal) {
		this.ctrl_P = controlador_Principal;
	}
	
	public void adicionarEventos() {
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoPessoa_Projetos());
		adicionarEventoJInternal(ctrl_P.getjInternal_TabelaPessoas());
		adicionarEventoJInternal(ctrl_P.getjInternal_TabelaPessoasColaboradores());
		adicionarEventoJInternal(ctrl_P.getjInternal_TabelaLogs());
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInfoPessoaOutrem());
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
			ctrl_P.gettLogUpdate().addAll(logs);
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
		.getBotao()
		.addActionListener(ActionEvent->
		{
			try
			{
				int index = jInternal_TabelaPessoasColaboradores.getTelaPessoas().getEspecificarComboBox().getSelectedIndex();

				if(index == 0)
				{
					ctrl_P.gettPessoa().addAll((List<Pessoa>) Fachada.getInstance().getBoPessoa().buscarPessoasDiferentesDe(ctrl_P.getPessoa_Logada()));
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

					ctrl_P.gettPessoa().addAll(
							(List<Pessoa>) Fachada.getInstance()
							.getBoPessoa()
							.buscarPessoasEspecificarDiferentesDe(ctrl_P.getPessoa_Logada(), nome, cpf, disponibilidade)
							);
				}
			} catch (ValidacaoException e) {
				MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
			}
		});
		
		/*jInternal_TabelaPessoasColaboradores
		.getTelaPessoas()
		.getBtAdicionarPessoa()
		.addActionListener(ActionEvent->{
			try 
			{
				int index = jInternal_TabelaPessoasColaboradores
						.getTelaPessoas().getTable().getSelectedRow();
				
				if(index >= 0)
				{
					Projeto projeto = ctrl_P.getProjeto_Atual();
					Pessoa pessoa = ctrl_P.gettColaboradorProjeto().getValor(index);
					
					Colaborador colab = new Colaborador();
					colab.setData_ingresso(LocalDateTime.now());
					colab.setPessoa(pessoa);
					colab.setProjeto(projeto);
					
					Fachada.getInstance().inserir(colab);
					
					ctrl_P.gettColaborador().addValor(colab);
					
					LogUpdate log = new LogUpdate();
					
					Fachada.getInstance().gerarLogInsercao(
							colab,
							ctrl_P.getPessoa_Logada(),
							log);
					
					ctrl_P.gettLogUpdate().addValor(log);
				}
			}
			catch (ValidacaoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao adicionar colaborador", e.getMessage());
			}
			
		});*/
	}

	private void adicionarEventoJInternal(JInternal_TabelaPessoas jInternal_TabelaPessoas) {

		jInternal_TabelaPessoas
		.getTelaPessoas()
		.getBtAdicionarPessoa()
		.addActionListener(ActionEvent->{
			
			try 
			{
				
				ctrl_P.getjInternal_TelaCadastro_Pessoa().queroFoco();
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
					ctrl_P.gettPessoa().addAll((List<Pessoa>) Fachada.getInstance().getBoPessoa().buscarPessoasDiferentesDe(ctrl_P.getPessoa_Logada()));
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

					ctrl_P.gettPessoa().addAll(
							(List<Pessoa>) Fachada.getInstance()
							.getBoPessoa()
							.buscarPessoasEspecificarDiferentesDe(ctrl_P.getPessoa_Logada(), nome, cpf, disponibilidade)
							);
				}
			} catch (ValidacaoException e) {
				MeuJDialog.exibirAlertaErro(null, "Erro", e.getMessage());
			}
		});
		
	}
	
	private void adicionarEventoJInternal(JInternal_TelaInfoPessoaOutrem telaInfoPessoa) {
		
		telaInfoPessoa.getTelaInfoPessoaContatoOutrem().getTelaMiniPessoa1().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa 
				try 
				{
					TelaMiniPessoa1 telaPessoa = telaInfoPessoa.getTelaInfoPessoaContatoOutrem().getTelaMiniPessoa1();
					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getPessoa_Outrem());
					
					ctrl_P.getPessoa_Outrem().setNome(nome);
					ctrl_P.getPessoa_Outrem().setCpf(cpf);
					ctrl_P.getPessoa_Outrem().setData_nascimento(DateUtil.parseToLocalDate(data));
					ctrl_P.getPessoa_Outrem().setSexo(sexo);
					ctrl_P.getPessoa_Outrem().setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(ctrl_P.getPessoa_Outrem());
					ctrl_P.gettPessoa().fireTableDataChanged();
					
					telaInfoPessoa.getTelaInfoPessoaContatoOutrem()
					.getTelaMiniPessoa1()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							ctrl_P.getPessoa_Outrem(), ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
				}
			});
		
		telaInfoPessoa.getTelaInfoPessoaContatoOutrem().getTelaMiniPessoa3().getBotao1()
		.addActionListener(ActionEvent->{
			//TODO - Update Pessoa 
			try 
			{
				
				boolean b = telaInfoPessoa.getTelaInfoPessoaContatoOutrem().getTelaMiniPessoa3().getResetarSenhaChbx().isSelected();
				
				ctrl_P.getPessoa_Outrem().setReset_senha(b);
				
				List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getPessoa_Outrem());
				
				Fachada.getInstance().atualizar(ctrl_P.getPessoa_Outrem());
				
				telaInfoPessoa.getTelaInfoPessoaContatoOutrem().getTelaMiniPessoa3()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
						antes,
						ctrl_P.getPessoa_Outrem(), ctrl_P.getPessoa_Logada(), log);
				ctrl_P.gettLogUpdate().addValor(log);
				
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
			}
		});
		
		
		telaInfoPessoa.getTelaInfoPessoaContatoOutrem().getTelaContatoCaracteristica().getBotao()
		.addActionListener(ActionEvent->{
			//TODO - Add Contato
			try 
			{
				TelaContatoCaracteristica telaContato = telaInfoPessoa.getTelaInfoPessoaContatoOutrem()
						.getTelaContatoCaracteristica();

				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();

				Contato c = ctrl_P.getPessoa_Outrem().getContato();

				if(c == null) 
					c = new Contato();
				
				
				if(c.getId() <= 0)
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					c.setPessoa(ctrl_P.getPessoa_Outrem());
					Fachada.getInstance().inserir(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(
							c, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else 
				{
					List<String> antes = Fachada.getInstance().gerarLog(c);
					
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							c, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				
				telaInfoPessoa.getTelaInfoPessoaContatoOutrem()
				.getTelaContatoCaracteristica()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
			} 
			catch (ValidacaoException e)
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao atualizar dados", e.getMessage());
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

					List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getPessoa_Logada());
					
					ctrl_P.getPessoa_Logada().setNome(nome);
					ctrl_P.getPessoa_Logada().setCpf(cpf);
					ctrl_P.getPessoa_Logada().setData_nascimento(DateUtil.parseToLocalDate(data));
					ctrl_P.getPessoa_Logada().setSexo(sexo);
					ctrl_P.getPessoa_Logada().setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(ctrl_P.getPessoa_Logada());
					ctrl_P.gettPessoa().fireTableDataChanged();
					
					telaInfoPessoaProjetos
					.getTelaInfoPessoaProjetos()
					.getTelaInfoPessoa()
					.getTelaMiniPessoa1()
					.getTelaCadastroEdicao()
					.escondeBtn();
					
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogUpdate(
							antes,
							ctrl_P.getPessoa_Logada(), ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
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

				String senhaAntiga = telaPessoa.getSenhaAntigaField().getTexto();
				String senhaNova = telaPessoa.getSenhaNovaField().getTexto();
				
				List<String> antes = Fachada.getInstance().gerarLog(ctrl_P.getPessoa_Logada());
				
//				controlador_Principal.getPessoa_Logada().setUser_login(login);
				
				Pessoa pessoa = ctrl_P.getPessoa_Logada();
				
				if(!Fachada.getInstance().getBoPessoa().buscarPorLoginSenhaID(pessoa.getCpf(), senhaAntiga, pessoa.getId()))
					throw new ValidacaoException("A senha antiga informada não condiz");
				
				ctrl_P.getPessoa_Logada().setUser_senha(senhaNova);
				
				Fachada.getInstance().atualizar(ctrl_P.getPessoa_Logada());
				ctrl_P.gettPessoa().fireTableDataChanged();
				
				telaInfoPessoaProjetos
				.getTelaInfoPessoaProjetos()
				.getTelaInfoPessoa()
				.getTelaMiniPessoa2()
				.getTelaCadastroEdicao()
				.escondeBtn();
				
				
				LogUpdate log = new LogUpdate();
				Fachada.getInstance().gerarLogUpdate(
						antes,
						ctrl_P.getPessoa_Logada(), ctrl_P.getPessoa_Logada(), log);
				ctrl_P.gettLogUpdate().addValor(log);
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


				Contato c = ctrl_P.getPessoa_Logada().getContato();

				if(c == null) 
					c = new Contato();
				if(c.getId() <= 0)
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					c.setPessoa(ctrl_P.getPessoa_Logada());
					
					Fachada.getInstance().inserir(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().gerarLogInsercao(
							c, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				else 
				{
					List<String> antes = Fachada.getInstance().gerarLog(c);
					
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogUpdate(
							antes,
							c, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
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
					if(ctrl_P.isBool_Colaborador_Ativado())
//						if(controlador_Principal.getColaborador_Atual().getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					CaracteristicaExtra c = new CaracteristicaExtra();
					c.setPessoa(ctrl_P.getPessoa_Logada());
					c.setNome("");
				
					Fachada.getInstance().inserir(c);
					
					ctrl_P.gettCaracteristicaExtra().addValor(c);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(
							c, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
					
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
					ctrl_P.getjInternal_TelaCadastro_Projeto().queroFoco();
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
							Projeto projeto = ctrl_P.gettProjeto().getValor(index);
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

}
