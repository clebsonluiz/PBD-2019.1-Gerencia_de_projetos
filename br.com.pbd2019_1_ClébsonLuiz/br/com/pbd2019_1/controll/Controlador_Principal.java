package br.com.pbd2019_1.controll;

import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas_Simples;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.TelaOpcoes;
import br.com.pbd2019_1.view.TelaPrincipal;

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
	
	public Controlador_Principal(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
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
			//TODO - Cadastro Etapa
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Pessoa telaCadastroPessoa) {
		telaCadastroPessoa.getTelaCadastro_Pessoa().getBtBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Pessoa
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Projeto telaCadastroProjeto) {
		telaCadastroProjeto.getTelaCadastro_Projeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Pessoa
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Tarefa telaCadastroTarefa) {
		telaCadastroTarefa.getTelaCadastro_Tarefa().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Cadastro Pessoa
		});
	}

	

	private void adicionarEventoJInternal(JInternal_TelaInfoEtapa telaInfoEtapa) {
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getBotao1()
			.addActionListener(ActionEvent->{
			//TODO - Atualizar info Etapa
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
			});
	
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
			//TODO - Adicionar etapa
			});

	}
	
}
