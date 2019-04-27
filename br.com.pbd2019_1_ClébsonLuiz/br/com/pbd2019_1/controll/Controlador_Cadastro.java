package br.com.pbd2019_1.controll;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import br.com.pbd2019_1.entidade.Etapa;
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
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaPessoa;
import br.com.pbd2019_1.view.TelaProjeto;

public class Controlador_Cadastro {
	
	public void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Etapa telaCadastroEtapa, TEtapa tEtapa) {
		telaCadastroEtapa.getTelaCadastro_Etapa().getBotao1()
			.addActionListener(ActionEvent->{
				try {
					TelaEtapa telaEtapa = telaCadastroEtapa.getTelaCadastro_Etapa();
					String nome = telaEtapa.getNomeEtapaField().getText();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					Etapa etapa = new Etapa();
					etapa.setNome(nome);
					etapa.setDescricao(descr);
					etapa.setPorcentagem_andamento(0);
					etapa.setProjeto(Controlador_Statics.projeto_static);
					Fachada.getInstance().inserir(etapa);
					tEtapa.addValor(etapa);
					telaEtapa.limparCampos();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		});
	}
	
	public void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Pessoa telaCadastroPessoa, TPessoa tPessoa) {
		telaCadastroPessoa.getTelaCadastro_Pessoa().getBtBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Pessoa
				try {
					TelaPessoa telaPessoa = telaCadastroPessoa.getTelaCadastro_Pessoa().getTelaPessoa();
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

					if(Controlador_Statics.type_user.equals(Pessoa.ADMIN_USER))
						pessoa.setUser_type(Pessoa.COMUM_USER);
					else if(Controlador_Statics.type_user.equals(Pessoa.SUPER_USER))
						pessoa.setUser_type(Pessoa.ADMIN_USER);
					Fachada.getInstance().inserir(pessoa);
					tPessoa.addValor(pessoa);
					telaCadastroPessoa.getTelaCadastro_Pessoa().limparCampos();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
	}
	
	public void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Projeto telaCadastroProjeto, TProjeto tProjeto) {
		telaCadastroProjeto.getTelaCadastro_Projeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Projeto
				try {

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
					projeto.setPessoa(Controlador_Statics.pessoa_static);
					projeto.setPrivilegio(!(Controlador_Statics.type_user.equals(Pessoa.COMUM_USER)));
					Fachada.getInstance().inserir(projeto);
					tProjeto.addValor(projeto);
					telaProjeto.limparCampos();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
		});
	}
	
	public void adicionarEventoJInternalCadastro(
			JInternal_TelaCadastro_Tarefa telaCadastroTarefa, TTarefa tTarefa, TelaEtapa telaEtapa) {
		telaCadastroTarefa.getTelaCadastro_Tarefa().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Tarefa
				
				try {
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
					tarefa.setEtapa(Controlador_Statics.etapa_static);
					Fachada.getInstance().inserir(tarefa);
					tTarefa.addValor(tarefa);
					telaCadastro.limparCampos();
					
					Controlador_Statics.etapa_static.setPorcentagem_andamento(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(Controlador_Statics.etapa_static)
							);
					
					telaEtapa
					.getBarraProgressBar()
					.setValue(
							Math.round(Controlador_Statics.etapa_static.getPorcentagem_andamento())
							);
					
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
	}

	
	
}
