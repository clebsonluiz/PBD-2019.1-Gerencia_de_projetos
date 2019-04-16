package br.com.pbd2019_1.controll;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TLogUpdate;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas_Simples;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaOpcoes;
import br.com.pbd2019_1.view.TelaPessoa;
import br.com.pbd2019_1.view.TelaPrincipal;
import br.com.pbd2019_1.view.TelaProjeto;

public class Controlador_Principal {

	private TelaPrincipal telaPrincipal;
	
	private JInternal_TelaCadastro_Etapa jInternal_TelaCadastro_Etapa;
	private JInternal_TelaCadastro_Projeto jInternal_TelaCadastro_Projeto;
	private JInternal_TelaCadastro_Pessoa jInternal_TelaCadastro_Pessoa;
	private JInternal_TelaCadastro_Tarefa jInternal_TelaCadastro_Tarefa;
	
	private JInternal_TelaInfoEtapa jInternal_TelaInfoEtapa;
	private JInternal_TelaInserirSQL jInternal_TelaInserirSQL;
	private JInternal_TelaInfoPessoa jInternal_TelaInfoPessoa;
	private JInternal_TelaInfoTarefa jInternal_TelaInfoTarefa;
	private JInternal_TelaInfoProjeto_Etapas jInternal_TelaInfoProjeto_Etapas;
	private JInternal_TelaInfoPessoa_Projetos jInternal_TelaInfoPessoa_Projetos;
	private JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoPessoa_Projetos_Simples;

	private TCaracteristicaExtra tCaracteristicaExtra;
	private TColaborador tColaborador;
	private TEtapa tEtapa;
	private TLogUpdate tLogUpdate;
	private TPessoa tPessoa;
	private TProjeto tProjeto;
	private TTarefa tTarefa;
	
	public Controlador_Principal(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
	}
	
	public void adicionarTableModels() {
		tCaracteristicaExtra = new TCaracteristicaExtra();
		tColaborador = new TColaborador();
		tEtapa = new TEtapa();
		tLogUpdate = new TLogUpdate();
		tPessoa = new TPessoa();
		tProjeto = new TProjeto();
		tTarefa = new TTarefa();
		
		jInternal_TelaInfoEtapa.getTelaEtapa_Tarefas().getTelaTarefas().getTable();
		jInternal_TelaInfoPessoa.getTelaInfoPessoa().getTelaContatoCaracteristica().getJTable();
		jInternal_TelaInfoProjeto_Etapas.getTelaProjeto_Etapas().getTelaEtapas().getTable();
		jInternal_TelaInfoProjeto_Etapas.getTelaProjeto_Etapas().getTelaColaboradores().getTable();
		jInternal_TelaInfoPessoa_Projetos.getTelaInfoPessoaProjetos().getTelaProjetos().getTable();
		jInternal_TelaInfoPessoa_Projetos.getTelaInfoPessoaProjetos().getTelaColaboracoes().getTable();
		jInternal_TelaInfoPessoa_Projetos_Simples.getTelaProjeto_Etapas_Simples().getTelaEtapas().getTable();

	}
	
	public void adicionarEventos() {
		
		telaPrincipal.getTelaCadastro_Pessoa()
			.getBtBotao1().addActionListener(ActionEvent->{
				//TODO - inserir aqui o cadastro
			});
		
		telaPrincipal.getTelaLoginSistema()
			.getBtnLogar().addActionListener(ActionEvent->{
				//TODO - Inserir aqui o login
			});
		
		TelaOpcoes telaOpcoes1 = telaPrincipal.getTelaMenu().getTelaOpcoesComum();
		TelaOpcoes telaOpcoes2 = telaPrincipal.getTelaMenu().getTelaOpcoesAdmin();
		TelaOpcoes telaOpcoes3 = telaPrincipal.getTelaMenu().getTelaOpcoesSuper();
		
		adicionarEventoMenu(telaOpcoes1);
		adicionarEventoMenu(telaOpcoes2);
		adicionarEventoMenu(telaOpcoes3);
	}
	
	private void adicionarEventoMenu(TelaOpcoes telaOpcoes) {
		telaOpcoes.getBtnInfo().addActionListener(ActionEvent->{
			//TODO - Inserir evento info usuario
		});
		
		telaOpcoes.getBtnLog().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM de ver os logs dos usuarios
		});
		
		telaOpcoes.getBtnPessoas().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM ver pessoas
		});
		
		telaOpcoes.getBtnSQL().addActionListener(ActionEvent->{
			//TODO - Inserir evento SUPER USUARIO Inserir SQL
		});
		
		telaOpcoes.getBtnSobre().addActionListener(ActionEvent->{
			//TODO - Inserir evento Abrir tela Info Projeto
		});
		
		telaOpcoes.getBtnSair().addActionListener(ActionEvent->{
			//TODO - Inserir evento deslogar
		});
	}
	
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Etapa telaCadastroEtapa) {
		telaCadastroEtapa.getTelaCadastro_Etapa().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastrar Etapa
				TelaEtapa telaEtapa = telaCadastroEtapa.getTelaCadastro_Etapa();
				String nome = telaEtapa.getNomeEtapaField().getText();
				String descr = telaEtapa.getDescricaoTextArea().getText();
				
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Pessoa telaCadastroPessoa) {
		telaCadastroPessoa.getTelaCadastro_Pessoa().getBtBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Pessoa
				TelaPessoa telaPessoa = telaCadastroPessoa.getTelaCadastro_Pessoa().getTelaPessoa();
				String nome = telaPessoa.getNomeField().getTexto();
				String cpf = telaPessoa.getCampoFormatadoCPF().getText();
				Date data = telaPessoa.getNascimentoDateChooser().getDate();
				String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
				String login = telaPessoa.getLoginField().getTexto();
				String senha = telaPessoa.getSenhaField().getTexto();
				boolean disponivel = telaPessoa.getRdbtnSim().isSelected();
				
				
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Projeto telaCadastroProjeto) {
		telaCadastroProjeto.getTelaCadastro_Projeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Projeto
				
				TelaProjeto telaProjeto = telaCadastroProjeto.getTelaCadastro_Projeto();
				
				String nome = telaProjeto.getNomeProjetoField().getTexto();
				String descr = telaProjeto.getDescricaoTextArea().getText();
				Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
				Date dataF = telaProjeto.getDataFimDateChooser().getDate();
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Tarefa telaCadastroTarefa) {
		telaCadastroTarefa.getTelaCadastro_Tarefa().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Tarefa
				
				TelaCadastro_Tarefa telaCadastro = telaCadastroTarefa.getTelaCadastro_Tarefa();
				
				String nome = telaCadastro.getNomeTarefaField().getTexto();
				String descr = telaCadastro.getDescricaoTextArea().getText();
				boolean finalizada = telaCadastro.getChckbxFinalizada().isSelected();
				String prior = (String) telaCadastro.getPrioridadeComboBox().getSelectedItem();
				
				LocalTime time = telaCadastro.getHorario().getLocalTime();
				Date date = telaCadastro.getDateChooser().getDate();
				
				LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);
				
				String horario = localDateTime.toString();
				
		});
	}

	

	private void adicionarEventoJInternal(JInternal_TelaInfoEtapa telaInfoEtapa) {
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getBotao1()
			.addActionListener(ActionEvent->{
			//TODO - Atualizar info Etapa
				
				TelaEtapa telaEtapa = telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa();
				String nome = telaEtapa.getNomeEtapaField().getTexto();
				String descr = telaEtapa.getDescricaoTextArea().getText();
				
				
			});
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaTarefas().getBtNovaTarefa()
			.addActionListener(ActionEvent->{
			//TODO - Nova Tarefa
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInserirSQL telaInserirSQL) {
		
		telaInserirSQL.getTelaInserirSQL().getBtInserir()
			.addActionListener(ActionEvent->{
				//TODO - Inserir SQL
			});
		
		telaInserirSQL.getTelaInserirSQL().getBtBtn()
			.addActionListener(ActionEvent->{
				//TODO - Botao Extra
			});
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa telaInfoPessoa) {
		
		telaInfoPessoa.getTelaInfoPessoa().getBtBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa 
				
				TelaPessoa telaPessoa = telaInfoPessoa.getTelaInfoPessoa().getTelaPessoa();
				String nome = telaPessoa.getNomeField().getTexto();
				String cpf = telaPessoa.getCampoFormatadoCPF().getText();
				Date data = telaPessoa.getNascimentoDateChooser().getDate();
				String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
				String login = telaPessoa.getLoginField().getTexto();
				String senha = telaPessoa.getSenhaField().getTexto();
				boolean disponivel = telaPessoa.getRdbtnSim().isSelected();
				
				TelaContatoCaracteristica telaContato = telaInfoPessoa.getTelaInfoPessoa()
						.getTelaContatoCaracteristica();
				
				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();
				
				
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
				
				String nome = telaInfoTarefa.getTelaInfoTarefa().getNomeTarefaField().getTexto();
				String descr = telaInfoTarefa.getTelaInfoTarefa().getDescricaoTextArea().getText();
				boolean finalizada = telaInfoTarefa.getTelaInfoTarefa().getChckbxFinalizada().isSelected();
				String prior = (String) telaInfoTarefa.getTelaInfoTarefa().getPrioridadeComboBox().getSelectedItem();
				
				LocalTime time = telaInfoTarefa.getTelaInfoTarefa().getHorario().getLocalTime();
				Date date = telaInfoTarefa.getTelaInfoTarefa().getDateChooser().getDate();
				
				LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);
				
				String horario = localDateTime.toString();
				
				
			});
		
		telaInfoTarefa.getTelaInfoTarefa().getChckbxFinalizada()
			.addItemListener(ItemEvent->{
				//TODO - Marcar/Desmarcar como finalizada
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoProjeto_Etapas telaInfoProjetoEtapas) {
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaProjeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update Projeto
				
				TelaProjeto telaProjeto = telaInfoProjetoEtapas.getTelaProjeto_Etapas()
						.getTelaProjeto();
				
				String nome = telaProjeto.getNomeProjetoField().getTexto();
				String descr = telaProjeto.getDescricaoTextArea().getText();
				Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
				Date dataF = telaProjeto.getDataFimDateChooser().getDate();
				
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
				//TODO - Adicionar etapa
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaColaboradores()
			.getBtAdicionarColaborador().addActionListener(ActionEvent->{
				//TODO - Adicionar Colaborador
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa_Projetos telaInfoPessoaProjetos) {
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getBtBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa
				TelaPessoa telaPessoa = telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
						.getTelaInfoPessoa().getTelaPessoa();
				
				String nome = telaPessoa.getNomeField().getTexto();
				String cpf = telaPessoa.getCampoFormatadoCPF().getText();
				Date data = telaPessoa.getNascimentoDateChooser().getDate();
				String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
				String login = telaPessoa.getLoginField().getTexto();
				String senha = telaPessoa.getSenhaField().getTexto();
				boolean disponivel = telaPessoa.getRdbtnSim().isSelected();
				
				TelaContatoCaracteristica telaContato = telaInfoPessoaProjetos
						.getTelaInfoPessoaProjetos()
						.getTelaInfoPessoa()
						.getTelaContatoCaracteristica();
				
				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();
				
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaInfoPessoa().getTelaContatoCaracteristica().getBtAdicionar()
			.addActionListener(ActionEvent->{
				//TODO - Adicionar caracteristica Extra
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaProjetos().getBtCriarUmNovo()
			.addActionListener(ActionEvent->{
				//TODO - Cadastrar Novo projeto
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaProjetos().getBtnGerarRelatorio()
			.addActionListener(ActionEvent->{
				//TODO - Gerar Relatorio projeto
			});
		
		
	}
	

	private void adicionarEventoJInternal(
			JInternal_TelaInfoProjeto_Etapas_Simples telaInfoProjeto_Etapas_Simples) {
		
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaProjeto()
			.getBotao1().addActionListener(ActionEvent->{
				//TODO - Update Projeto
				
				TelaProjeto telaProjeto = telaInfoProjeto_Etapas_Simples
						.getTelaProjeto_Etapas_Simples().getTelaProjeto();
				
				String nome = telaProjeto.getNomeProjetoField().getTexto();
				String descr = telaProjeto.getDescricaoTextArea().getText();
				Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
				Date dataF = telaProjeto.getDataFimDateChooser().getDate();
				
			});
	
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
			//TODO - Adicionar etapa
			});

	}
	
}
