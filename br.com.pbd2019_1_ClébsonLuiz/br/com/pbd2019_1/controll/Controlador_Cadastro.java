package br.com.pbd2019_1.controll;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TSubEtapa;
import br.com.pbd2019_1.tabelas.TSubTarefa;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternal_TelaCadastroSubEtapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastroSubTarefa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.TelaCadastro_Etapa;
import br.com.pbd2019_1.view.TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaInfoSubEtapa;
import br.com.pbd2019_1.view.TelaProjeto;

public class Controlador_Cadastro {
	
	private Controlador_Principal ctrl_P;
	
	public Controlador_Cadastro(Controlador_Principal ctrl_P) {
		this.ctrl_P = ctrl_P;
	}
	
	public void adicionarEventos()
	{
		adicionarEventoJInternalCadastro(ctrl_P.getjInternal_TelaCadastro_Etapa(), ctrl_P.gettEtapa());
		adicionarEventoJInternalCadastro(ctrl_P.getjInternal_TelaCadastroSubEtapa(), ctrl_P.gettSubEtapa());
		adicionarEventoJInternalCadastro(ctrl_P.getjInternal_TelaCadastro_Pessoa(), ctrl_P.gettPessoa());
		adicionarEventoJInternalCadastro(ctrl_P.getjInternal_TelaCadastro_Projeto(), ctrl_P.gettProjeto());
		adicionarEventoJInternalCadastro(ctrl_P.getjInternal_TelaCadastro_Tarefa(), ctrl_P.gettTarefa(), ctrl_P.getjInternal_TelaInfoEtapa().getTelaEtapa_Tarefas().getTelaEtapa());
		adicionarEventoJInternalCadastro(ctrl_P.getjInternal_TelaCadastroSubTarefa(), ctrl_P.gettSubTarefa(), ctrl_P.getjInternal_TelaInfoSubEtapa().getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa());
	}

	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Etapa telaCadastroEtapa, TEtapa tEtapa) {
		telaCadastroEtapa.getTelaCadastro_Etapa().getBotao()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaCadastro_Etapa telaEtapa = telaCadastroEtapa.getTelaCadastro_Etapa();
					String nome = telaEtapa.getNomeEtapaField().getText();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					Etapa etapa = new Etapa();
					etapa.setNome(nome);
					etapa.setDescricao(descr);
					etapa.setPorcentagem(0);
					etapa.setProjeto(ctrl_P.getProjeto_Atual());
					Fachada.getInstance().inserir(etapa);
					tEtapa.addValor(etapa);
					telaEtapa.limparCampos();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(etapa, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
				}
				
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastroSubEtapa telaCadastroSubEtapa, TSubEtapa tSubEtapa) {
		telaCadastroSubEtapa.getTelaCadastro_Etapa().getBotao()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaCadastro_Etapa telaEtapa = telaCadastroSubEtapa.getTelaCadastro_Etapa();
					String nome = telaEtapa.getNomeEtapaField().getText();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					SubEtapa etapa = new SubEtapa();
					etapa.setNome(nome);
					etapa.setDescricao(descr);
					etapa.setPorcentagem(0);
					etapa.setEtapa(ctrl_P.getEtapa_Atual());
					Fachada.getInstance().inserir(etapa);
					tSubEtapa.addValor(etapa);
					telaEtapa.limparCampos();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(etapa, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
				}
				
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Pessoa telaCadastroPessoa, TPessoa tPessoa) {
		telaCadastroPessoa.getTelaCadastro_Pessoa().getBotao()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaCadastro_Pessoa telaPessoa = telaCadastroPessoa.getTelaCadastro_Pessoa();
					cadastrarPessoa(telaPessoa, Pessoa.COMUM_USER, tPessoa, ctrl_P.getPessoa_Logada());
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
				}
		});
		
		telaCadastroPessoa.getTelaCadastro_Pessoa().getBtCadastrarComoAdmin()
		.addActionListener(ActionEvent->{
			try 
			{
				TelaCadastro_Pessoa telaPessoa = telaCadastroPessoa.getTelaCadastro_Pessoa();
				cadastrarPessoa(telaPessoa, Pessoa.ADMIN_USER, tPessoa, ctrl_P.getPessoa_Logada());
			}
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
			}
	});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Projeto telaCadastroProjeto, TProjeto tProjeto) {
		telaCadastroProjeto.getTelaCadastro_Projeto().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaProjeto telaProjeto = telaCadastroProjeto.getTelaCadastro_Projeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					Projeto projeto = new Projeto();
					projeto.setNome(nome);
					projeto.setDescricao(descr);
					projeto.setData_inicio(DateUtil.getDateSQL(dataI));
					projeto.setData_fim(DateUtil.getDateSQL(dataF));
					projeto.setPessoa(ctrl_P.getPessoa_Logada());
					projeto.setPrivilegio(!(ctrl_P.getType_User_Logado().equals(Pessoa.COMUM_USER)));
					Fachada.getInstance().inserir(projeto);
					tProjeto.addValor(projeto);
					telaProjeto.limparCampos();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(projeto, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
				}
		});
	}
	
	private void adicionarEventoJInternalCadastro(
			JInternal_TelaCadastro_Tarefa telaCadastroTarefa, TTarefa tTarefa, TelaEtapa telaEtapa) {
		telaCadastroTarefa.getTelaCadastro_Tarefa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaCadastro_Tarefa telaCadastro = telaCadastroTarefa.getTelaCadastro_Tarefa();

					String nome = telaCadastro.getNomeTarefaField().getTexto();
					String descr = telaCadastro.getDescricaoTextArea().getText();
					boolean finalizada = telaCadastro.getChckbxFinalizada().isSelected();
					String prior = (String) telaCadastro.getPrioridadeComboBox().getSelectedItem();

					LocalTime time = telaCadastro.getHorario().getLocalTime();
					Date date = telaCadastro.getDateChooser().getDate();

					LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);

					Tarefa tarefa = new Tarefa();
					tarefa.setNome(nome);
					tarefa.setDescricao(descr);
					tarefa.setConcluida(finalizada);
					tarefa.setPrioridade(prior);
					tarefa.setHorario(localDateTime);
					tarefa.setEtapa(ctrl_P.getEtapa_Atual());
					Fachada.getInstance().inserir(tarefa);
					tTarefa.addValor(tarefa);
					telaCadastro.limparCampos();
					
					ctrl_P.getEtapa_Atual().setPorcentagem(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(ctrl_P.getEtapa_Atual())
							);
					
					telaEtapa
					.getBarraProgressBar()
					.setValue(
							Math.round(ctrl_P.getEtapa_Atual().getPorcentagem())
							);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(tarefa, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
				}
		});
	}
	
	private void adicionarEventoJInternalCadastro(
			JInternal_TelaCadastroSubTarefa telaCadastroSubTarefa, TSubTarefa tSubTarefa, TelaInfoSubEtapa telaInfoSubEtapa) {
		telaCadastroSubTarefa.getTelaCadastro_Tarefa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaCadastro_Tarefa telaCadastro = telaCadastroSubTarefa.getTelaCadastro_Tarefa();

					String nome = telaCadastro.getNomeTarefaField().getTexto();
					String descr = telaCadastro.getDescricaoTextArea().getText();
					boolean finalizada = telaCadastro.getChckbxFinalizada().isSelected();
					String prior = (String) telaCadastro.getPrioridadeComboBox().getSelectedItem();

					LocalTime time = telaCadastro.getHorario().getLocalTime();
					Date date = telaCadastro.getDateChooser().getDate();

					LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);


					SubTarefa tarefa = new SubTarefa();
					tarefa.setNome(nome);
					tarefa.setDescricao(descr);
					tarefa.setConcluida(finalizada);
					tarefa.setPrioridade(prior);
					tarefa.setHorario(localDateTime);
					tarefa.setSub_etapa(ctrl_P.getSubEtapa_Atual());
					Fachada.getInstance().inserir(tarefa);
					tSubTarefa.addValor(tarefa);
					telaCadastro.limparCampos();
					
					ctrl_P.getEtapa_Atual().setPorcentagem(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(ctrl_P.getEtapa_Atual())
							);
					
					telaInfoSubEtapa
					.getBarraProgressBar()
					.setValue(
							Math.round(ctrl_P.getEtapa_Atual().getPorcentagem())
							);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(tarefa, ctrl_P.getPessoa_Logada(), log);
					ctrl_P.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e)
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao Cadastrar", e.getMessage());
				}
		});
	}

	
	protected void cadastrarPessoa(TelaCadastro_Pessoa telaPessoa, String type_user, TPessoa tPessoa, Pessoa responsavelLog) throws ValidacaoException
	{
		String nome = telaPessoa.getNomeField().getTexto();
		String cpf = telaPessoa.getCampoFormatadoCPF().getText();
		Date data = telaPessoa.getNascimentoDateChooser().getDate();
		String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
		String senha = telaPessoa.getSenhaField().getTexto();
		String senhaConfirma = telaPessoa.getSenhaComfirmaField().getTexto();
		boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

		if(!senha.equals(senhaConfirma))
			throw new ValidacaoException("As senhas não batem");
		
		Fachada.getInstance().getBoPessoa().buscarPorCPF(cpf);

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setCpf(cpf);
		pessoa.setData_nascimento(DateUtil.parseToLocalDate(data));
		pessoa.setSexo(sexo);
		pessoa.setUser_senha(senha);
		pessoa.setDisponibilidade(disponivel);

		pessoa.setUser_type(type_user);
		Fachada.getInstance().inserir(pessoa);
		tPessoa.addValor(pessoa);
		telaPessoa.limparCampos();
		
		LogUpdate log = new LogUpdate();
		Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(pessoa, responsavelLog, log);
		ctrl_P.gettLogUpdate().addValor(log);
	}
}
