package br.com.pbd2019_1.controll;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternal_TelaAlerta;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaPessoa;
import br.com.pbd2019_1.view.TelaProjeto;

public class Controlador_Cadastro {
	
	private Controlador_Principal controlador_Principal;
	
	public Controlador_Cadastro(Controlador_Principal controlador_Principal) {
		this.controlador_Principal = controlador_Principal;
	}

	public void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Etapa telaCadastroEtapa, TEtapa tEtapa) {
		telaCadastroEtapa.getTelaCadastro_Etapa().getBotao1()
			.addActionListener(ActionEvent->{
				try 
				{
					TelaEtapa telaEtapa = telaCadastroEtapa.getTelaCadastro_Etapa();
					String nome = telaEtapa.getNomeEtapaField().getText();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					Etapa etapa = new Etapa();
					etapa.setNome(nome);
					etapa.setDescricao(descr);
					etapa.setPorcentagem_andamento(0);
					etapa.setProjeto(controlador_Principal.getProjeto_Atual());
					Fachada.getInstance().inserir(etapa);
					tEtapa.addValor(etapa);
					telaEtapa.limparCampos();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(etapa, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				} 
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Cadastrar", e.getMessage());
				}
				
		});
	}
	
	public void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Pessoa telaCadastroPessoa, TPessoa tPessoa) {
		telaCadastroPessoa.getTelaCadastro_Pessoa().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Pessoa
				try 
				{
					TelaPessoa telaPessoa = telaCadastroPessoa.getTelaCadastro_Pessoa();
					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					String login = telaPessoa.getLoginField().getTexto();
					String senha = telaPessoa.getSenhaField().getTexto();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					Fachada.getInstance().getBoPessoa().buscarPorCPF(cpf);
					Fachada.getInstance().getBoPessoa().buscarPorLogin(login);

					Pessoa pessoa = new Pessoa();
					pessoa.setNome(nome);
					pessoa.setCpf(cpf);
					pessoa.setData_nascimento(DateUtil.getDateSQL(data));
					pessoa.setSexo(sexo);
					pessoa.setUser_login(login);
					pessoa.setUser_senha(senha);
					pessoa.setDisponibilidade(disponivel);

					if(controlador_Principal.getType_User_Logado().equals(Pessoa.ADMIN_USER))
						pessoa.setUser_type(Pessoa.COMUM_USER);
					else if(controlador_Principal.getType_User_Logado().equals(Pessoa.SUPER_USER))
						pessoa.setUser_type(Pessoa.ADMIN_USER);
					Fachada.getInstance().inserir(pessoa);
					tPessoa.addValor(pessoa);
					telaCadastroPessoa.getTelaCadastro_Pessoa().limparCampos();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(pessoa, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Cadastrar", e.getMessage());
				}
		});
	}
	
	public void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Projeto telaCadastroProjeto, TProjeto tProjeto) {
		telaCadastroProjeto.getTelaCadastro_Projeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Projeto
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
					projeto.setPessoa(controlador_Principal.getPessoa_Logada());
					projeto.setPrivilegio(!(controlador_Principal.getType_User_Logado().equals(Pessoa.COMUM_USER)));
					Fachada.getInstance().inserir(projeto);
					tProjeto.addValor(projeto);
					telaProjeto.limparCampos();
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(projeto, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
				}
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Cadastrar", e.getMessage());
				}
			
				
				
		});
	}
	
	public void adicionarEventoJInternalCadastro(
			JInternal_TelaCadastro_Tarefa telaCadastroTarefa, TTarefa tTarefa, TelaEtapa telaEtapa) {
		telaCadastroTarefa.getTelaCadastro_Tarefa().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Tarefa
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

					String horario = localDateTime.toString();

					Tarefa tarefa = new Tarefa();
					tarefa.setNome(nome);
					tarefa.setDescricao(descr);
					tarefa.setConcluida(finalizada);
					tarefa.setPrioridade(prior);
					tarefa.setHorario_tarefa(horario);
					tarefa.setEtapa(controlador_Principal.getEtapa_Atual());
					Fachada.getInstance().inserir(tarefa);
					tTarefa.addValor(tarefa);
					telaCadastro.limparCampos();
					
					controlador_Principal.getEtapa_Atual().setPorcentagem_andamento(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(controlador_Principal.getEtapa_Atual())
							);
					
					telaEtapa
					.getBarraProgressBar()
					.setValue(
							Math.round(controlador_Principal.getEtapa_Atual().getPorcentagem_andamento())
							);
					
					LogUpdate log = new LogUpdate();
					Fachada.getInstance().getBoLogUpdate().gerarLogInsercao(tarefa, controlador_Principal.getPessoa_Logada(), log);
					controlador_Principal.gettLogUpdate().addValor(log);
					
				} 
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Cadastrar", e.getMessage());
				}
		});
	}

	
	
}
