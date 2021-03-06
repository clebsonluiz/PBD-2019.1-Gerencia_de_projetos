package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JDesktopPane;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.tabelas.TBackup;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TLogUpdate;
import br.com.pbd2019_1.tabelas.TObject;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TSubEtapa;
import br.com.pbd2019_1.tabelas.TSubTarefa;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.tabelas.TViewGerenteEtapa;
import br.com.pbd2019_1.tabelas.TViewSubEtapaColaborador;
import br.com.pbd2019_1.tabelas.TViewTarefaColaborador;
import br.com.pbd2019_1.view.JIF_Inf_Etp_Colab;
import br.com.pbd2019_1.view.JIF_Inf_Proj_Colab;
import br.com.pbd2019_1.view.JIF_Inf_SbEtp_Colab;
import br.com.pbd2019_1.view.JIF_Inf_SbTarf_Colab;
import br.com.pbd2019_1.view.JIF_Inf_Tarf_Colab;
import br.com.pbd2019_1.view.JIF_Reset_Senha;
import br.com.pbd2019_1.view.JInternalAbstract;
import br.com.pbd2019_1.view.JInternal_Backup_Efetuando;
import br.com.pbd2019_1.view.JInternal_ColaboradoresEtapa;
import br.com.pbd2019_1.view.JInternal_ColaboradoresSubEtapa;
import br.com.pbd2019_1.view.JInternal_ColaboradoresTarefa;
import br.com.pbd2019_1.view.JInternal_InfoLog;
import br.com.pbd2019_1.view.JInternal_Sobre;
import br.com.pbd2019_1.view.JInternal_TabelaLogs;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
import br.com.pbd2019_1.view.JInternal_TelaAgendarBackup;
import br.com.pbd2019_1.view.JInternal_TelaBackups;
import br.com.pbd2019_1.view.JInternal_TelaCadastroSubEtapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastroSubTarefa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Etapa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Projeto;
import br.com.pbd2019_1.view.JInternal_TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoaOutrem;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoaOutremSimples;
import br.com.pbd2019_1.view.JInternal_TelaInfoPessoa_Projetos;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas;
import br.com.pbd2019_1.view.JInternal_TelaInfoProjeto_Etapas_Simples;
import br.com.pbd2019_1.view.JInternal_TelaInfoSubEtapa;
import br.com.pbd2019_1.view.JInternal_TelaInfoSubTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInfoTarefa;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.JanelaPrincipal;
import br.com.pbd2019_1.view.PopUp;
import br.com.pbd2019_1.view.TelaPrincipal;

public class Controlador_Principal {

	public static final String SUPER_USER_PORTA_BANCO = "5432";
	public static final String SUPER_USER_NOME_BANCO = "PBD_GESTAO";
	public static final String SUPER_USER_QT_ENTIDADE_BANCO = "17";
	public static final String SUPER_USER_USUARIO_BANCO = "postgres";
	public static final String SUPER_USER_SENHA_BANCO = "13111996";
	
	
	/**
	 * janelaEscolherCaminhoArquivo - JFileChooser para escolher os caminhos dos arquivos
	 *
	 * pessoa_Logada - Pessoa que est� logada no sistema atualmente
	 * pessoa_Outrem - Quando a pessoa que est� logado no sistema ver outra pessa
	 * projeto_Atual - Projeto que uma pessoa quer ver
	 * etapa_Atual - Etapa pertecente ao projeto anterior que uma pessoa quer ver
	 * tarefa_Atual - Tarefa que pertence a Etapa anterior que uma pessoa quer ver
	 * colaborador_Atual - Quando a pessoa vai ver um projeto que est� como colaborador
	 * logUpdate_Atual - Log que est� sendo visutalizado atualmente.
	 * type_User_Logado - Tipo do Usuario que est� logado no sistema
	 * bool_Colaborador_Ativado - Indica que um projeto que est� sendo visto � um projeto de colaborador.
	 * 
	 * */
	
	private Pessoa pessoa_Logada;
	private Pessoa pessoa_Outrem;
	private Projeto projeto_Atual;
	private Etapa etapa_Atual;
	private Tarefa tarefa_Atual;
	private Colaborador colaborador_Atual;
	private LogUpdate logUpdate_Atual;
	private String type_User_Logado = "";
	private boolean bool_Colaborador_Ativado = false;
	
	private SubEtapa subEtapa_Atual;
	private SubTarefa subTarefa_Atual;
	
	private Pessoa pessoa_Atual_Colab;
	private Projeto projeto_Atual_Colab;
	private Etapa etapa_Atual_Colab;
	private Tarefa tarefa_Atual_Colab;
	private SubEtapa subEtapa_Atual_Colab;
	private SubTarefa subTarefa_Atual_Colab;
	
	private JInternal_ColaboradoresEtapa jInternal_ColaboradoresEtapa;
	private JInternal_ColaboradoresSubEtapa jInternal_ColaboradoresSubEtapa;
	private JInternal_ColaboradoresTarefa jInternal_ColaboradoresTarefa;
	
	private JInternal_TelaCadastro_Etapa jInternal_TelaCadastro_Etapa;
	private JInternal_TelaCadastro_Projeto jInternal_TelaCadastro_Projeto;
	private JInternal_TelaCadastro_Pessoa jInternal_TelaCadastro_Pessoa;
	private JInternal_TelaCadastro_Tarefa jInternal_TelaCadastro_Tarefa;
	
	private JInternal_TelaCadastroSubEtapa jInternal_TelaCadastroSubEtapa;
	private JInternal_TelaCadastroSubTarefa jInternal_TelaCadastroSubTarefa;
	
	private JInternal_TelaInfoPessoaOutrem jInternal_TelaInfoPessoaOutrem;
	private JInternal_TelaInfoPessoaOutremSimples jInternal_TelaInfoPessoaOutremSimples;
	private JInternal_TelaInfoSubEtapa jInternal_TelaInfoSubEtapa;
	private JInternal_TelaInfoSubTarefa jInternal_TelaInfoSubTarefa;
	
	private JInternal_TelaInfoEtapa jInternal_TelaInfoEtapa;
	private JInternal_TelaInserirSQL jInternal_TelaInserirSQL;
	private JInternal_TelaInfoPessoa jInternal_TelaInfoPessoa;
	private JInternal_TelaInfoTarefa jInternal_TelaInfoTarefa;
	private JInternal_TelaInfoProjeto_Etapas jInternal_TelaInfoProjeto_Etapas;
	private JInternal_TelaInfoPessoa_Projetos jInternal_TelaInfoPessoa_Projetos;
	private JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoProjeto_Etapas_Simples;
	private JInternal_TabelaPessoas jInternal_TabelaPessoas;
	private JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores;
	private JInternal_TelaBackups jInternal_TelaBackups;
	private JInternal_TabelaLogs jInternal_TabelaLogs;
	private JInternal_InfoLog jInternal_InfoLog;
	private JInternal_TelaAgendarBackup jInternal_TelaAgendarBackup;
	
	private JIF_Inf_Proj_Colab jif_Inf_Proj_Colab;
	private JIF_Inf_Etp_Colab jif_Inf_Etp_Colab;
	private JIF_Inf_SbEtp_Colab jif_Inf_SbEtp_Colab;
	private JIF_Inf_SbTarf_Colab jif_Inf_SbTarf_Colab;
	private JIF_Inf_Tarf_Colab jif_Inf_Tarf_Colab;
	
	private TCaracteristicaExtra tCaracteristicaExtra;
	private TCaracteristicaExtra tCaracteristicaExtra2;
	private TColaborador tColaborador;
	private TColaboracoes tColaboracoes;
	private TEtapa tEtapa;
	private TLogUpdate tLogUpdate;
	private TPessoa tPessoa;
	private TProjeto tProjeto;
	private TTarefa tTarefa;
	private TObject tObject;
	private TBackup tBackup;
	
	private TPessoa tColaboradorProjeto;
	private TColaborador tColaboradorEtapa;
	private TColaborador tColaboradorSubEtapa;
	private TColaborador tColaboradorTarefa;
	
	private TSubEtapa tSubEtapa;
	private TSubTarefa tSubTarefa;
	
	/**TableModels para as JInternals na vis�o de Colab*/
	private TColaborador tColab;
	private TProjeto tProjetoColab;
	private TEtapa tEtapaColab;
	private TTarefa tTarefaColab;
	private TSubEtapa tSubEtapaColab;
	private TSubTarefa tSubTarefaColab;
	
	private TViewGerenteEtapa tViewGerenteEtapa;
	private TViewSubEtapaColaborador tViewSubEtapaColaborador;
	private TViewTarefaColaborador tViewTarefaColaborador;
	
	private Ctrl_Info_JInternal_Tela controlador_Info_JInternal_Tela;
	private Ctrl_Cadastro controlador_Cadastro;
	private Ctrl_Backup controlador_Backup;
	private Ctrl_JInternal_SQL ctrl_JInternal_SQL;
	
	private Ctrl_PreenchementoTela ctrl_PreenchementoTela;
	private Ctrl_JTable ctrl_JTable;
	private Ctrl_JTable_Colab ctrl_JTable_Colab;
	private Ctrl_Listeners_Projeto ctrl_Listeners_Projeto;
	private Ctrl_PopUp ctrl_PopUp;
	private Ctrl_TelaPrincipal ctrl_TelaPrincipal;
	private Ctrl_JTable_View ctrl_JTable_View;
	
	private JanelaPrincipal janelaPrincipal;
	
	private static PopUp popUpCaracteristica = new PopUp(new String[]{"Salvar", "Excluir"});
	private static PopUp popUp = new PopUp(new String[]{"", "Excluir", ""});
	
	private List<JInternalAbstract> jifs = new ArrayList<>();
	
	public Controlador_Principal(JanelaPrincipal janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
		this.controlador_Info_JInternal_Tela = new Ctrl_Info_JInternal_Tela(this);
		this.controlador_Cadastro = new Ctrl_Cadastro(this);
		this.controlador_Backup = new Ctrl_Backup(this);
		this.ctrl_JInternal_SQL = new Ctrl_JInternal_SQL(this);
		this.ctrl_PreenchementoTela = new Ctrl_PreenchementoTela(this);
		this.ctrl_JTable = new Ctrl_JTable(this);
		this.ctrl_JTable_Colab = new Ctrl_JTable_Colab(this);
		this.ctrl_Listeners_Projeto = new Ctrl_Listeners_Projeto(this);
		this.ctrl_PopUp = new Ctrl_PopUp(this);
		this.ctrl_TelaPrincipal = new Ctrl_TelaPrincipal(this);
		this.ctrl_JTable_View = new Ctrl_JTable_View(this);
	}

	public void adicionarJInternals(JInternal_TelaCadastro_Etapa jInternal_TelaCadastro_Etapa,
			JInternal_TelaCadastro_Projeto jInternal_TelaCadastro_Projeto,
			JInternal_TelaCadastro_Pessoa jInternal_TelaCadastro_Pessoa,
			JInternal_TelaCadastro_Tarefa jInternal_TelaCadastro_Tarefa,
			JInternal_TelaInfoEtapa jInternal_TelaInfoEtapa, JInternal_TelaInserirSQL jInternal_TelaInserirSQL,
			JInternal_TelaInfoPessoa jInternal_TelaInfoPessoa, JInternal_TelaInfoTarefa jInternal_TelaInfoTarefa,
			JInternal_TelaInfoProjeto_Etapas jInternal_TelaInfoProjeto_Etapas,
			JInternal_TelaInfoPessoa_Projetos jInternal_TelaInfoPessoa_Projetos,
			JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoProjeto_Etapas_Simples,
			JInternal_TabelaPessoas jInternal_TabelaPessoas,
			JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores,
			JInternal_TelaBackups jInternal_TelaBackups, 
			JInternal_TabelaLogs jInternal_TabelaLogs,
			JInternal_InfoLog jInternal_InfoLog,
			JInternal_TelaAgendarBackup jInternal_TelaAgendarBackup,
			
			JInternal_ColaboradoresEtapa jInternal_ColaboradoresEtapa,
			JInternal_ColaboradoresSubEtapa jInternal_ColaboradoresSubEtapa,
			JInternal_ColaboradoresTarefa jInternal_ColaboradoresTarefa,
			
			JInternal_TelaCadastroSubEtapa jInternal_TelaCadastroSubEtapa,
			JInternal_TelaCadastroSubTarefa jInternal_TelaCadastroSubTarefa,
			JInternal_TelaInfoPessoaOutrem jInternal_TelaInfoPessoaOutrem,
			JInternal_TelaInfoPessoaOutremSimples jInternal_TelaInfoPessoaOutremSimples,
			JInternal_TelaInfoSubEtapa jInternal_TelaInfoSubEtapa,
			JInternal_TelaInfoSubTarefa jInternal_TelaInfoSubTarefa,
			JIF_Inf_Proj_Colab jif_Inf_Proj_Colab,
			JIF_Inf_Etp_Colab jif_Inf_Etp_Colab,
			JIF_Inf_SbEtp_Colab jif_Inf_SbEtp_Colab,
			JIF_Inf_SbTarf_Colab jif_Inf_SbTarf_Colab,
			JIF_Inf_Tarf_Colab jif_Inf_Tarf_Colab
			) {
		this.jInternal_TelaCadastro_Etapa = jInternal_TelaCadastro_Etapa;
		this.jInternal_TelaCadastro_Projeto = jInternal_TelaCadastro_Projeto;
		this.jInternal_TelaCadastro_Pessoa = jInternal_TelaCadastro_Pessoa;
		this.jInternal_TelaCadastro_Tarefa = jInternal_TelaCadastro_Tarefa;
		this.jInternal_TelaInfoEtapa = jInternal_TelaInfoEtapa;
		this.jInternal_TelaInserirSQL = jInternal_TelaInserirSQL;
		this.jInternal_TelaInfoPessoa = jInternal_TelaInfoPessoa;
		this.jInternal_TelaInfoTarefa = jInternal_TelaInfoTarefa;
		this.jInternal_TelaInfoProjeto_Etapas = jInternal_TelaInfoProjeto_Etapas;
		this.jInternal_TelaInfoPessoa_Projetos = jInternal_TelaInfoPessoa_Projetos;
		this.jInternal_TelaInfoProjeto_Etapas_Simples = jInternal_TelaInfoProjeto_Etapas_Simples;
		this.jInternal_TabelaPessoas = jInternal_TabelaPessoas;
		this.jInternal_TabelaPessoasColaboradores = jInternal_TabelaPessoasColaboradores;
		this.jInternal_TelaBackups = jInternal_TelaBackups;
		this.jInternal_TabelaLogs = jInternal_TabelaLogs;
		this.jInternal_InfoLog = jInternal_InfoLog;
		this.jInternal_TelaAgendarBackup = jInternal_TelaAgendarBackup;
		
		this.jInternal_ColaboradoresEtapa = jInternal_ColaboradoresEtapa;
		this.jInternal_ColaboradoresSubEtapa = jInternal_ColaboradoresSubEtapa;
		this.jInternal_ColaboradoresTarefa = jInternal_ColaboradoresTarefa;
		
		this.jInternal_TelaCadastroSubEtapa = jInternal_TelaCadastroSubEtapa;
		this.jInternal_TelaCadastroSubTarefa = jInternal_TelaCadastroSubTarefa;
		this.jInternal_TelaInfoPessoaOutrem =  jInternal_TelaInfoPessoaOutrem;
		this.jInternal_TelaInfoPessoaOutremSimples = jInternal_TelaInfoPessoaOutremSimples;
		this.jInternal_TelaInfoSubEtapa = jInternal_TelaInfoSubEtapa;
		this.jInternal_TelaInfoSubTarefa = jInternal_TelaInfoSubTarefa;
		this.jif_Inf_Proj_Colab = jif_Inf_Proj_Colab;
		this.jif_Inf_Etp_Colab = jif_Inf_Etp_Colab;
		this.jif_Inf_SbEtp_Colab = jif_Inf_SbEtp_Colab;
		this.jif_Inf_SbTarf_Colab = jif_Inf_SbTarf_Colab;
		this.jif_Inf_Tarf_Colab = jif_Inf_Tarf_Colab;
		
		Collections.addAll(jifs,
				this.jInternal_TelaCadastro_Etapa,
				this.jInternal_TelaCadastro_Projeto,
				this.jInternal_TelaCadastro_Pessoa,
				this.jInternal_TelaCadastro_Tarefa,
				this.jInternal_TelaInfoEtapa,
				this.jInternal_TelaInserirSQL,
				this.jInternal_TelaInfoPessoa,
				this.jInternal_TelaInfoTarefa,
				this.jInternal_TelaInfoProjeto_Etapas,
				this.jInternal_TelaInfoPessoa_Projetos,
				this.jInternal_TelaInfoProjeto_Etapas_Simples,
				this.jInternal_TabelaPessoas,
				this.jInternal_TabelaPessoasColaboradores,
				this.jInternal_TelaBackups,
				this.jInternal_TabelaLogs,
				this.jInternal_InfoLog,
				this.jInternal_TelaAgendarBackup,
				
				this.jInternal_ColaboradoresEtapa,
				this.jInternal_ColaboradoresSubEtapa,
				this.jInternal_ColaboradoresTarefa,
				
				this.jInternal_TelaCadastroSubEtapa,
				this.jInternal_TelaCadastroSubTarefa,
				this.jInternal_TelaInfoPessoaOutrem,
				this.jInternal_TelaInfoPessoaOutremSimples,
				this.jInternal_TelaInfoSubEtapa,
				this.jInternal_TelaInfoSubTarefa,
				this.jif_Inf_Proj_Colab,
				this.jif_Inf_Etp_Colab,
				this.jif_Inf_SbEtp_Colab,
				this.jif_Inf_SbTarf_Colab,
				this.jif_Inf_Tarf_Colab,
				JInternal_Sobre.getInstance(),
				JInternal_Backup_Efetuando.getInstance(),
				JIF_Reset_Senha.getInstance()
				);
	}
	
	public void adicionarJInternalsAoJDesktop()
	{
		JDesktopPane desktop = janelaPrincipal.getTelaPrincipal().getjDesktopPane();
		jifs.forEach(jInternalAbstract-> desktop.add(jInternalAbstract));
	}

	public void adicionarTableModels() 
	{
		tCaracteristicaExtra = new TCaracteristicaExtra();
		tCaracteristicaExtra2 = new TCaracteristicaExtra();
		tColaborador = new TColaborador();
		tColaboracoes = new TColaboracoes();
		tEtapa = new TEtapa();
		tLogUpdate = new TLogUpdate();
		tPessoa = new TPessoa();
		tProjeto = new TProjeto();
		tTarefa = new TTarefa();
		tObject = new TObject();
		tBackup = new TBackup();
		
		tColaboradorProjeto = new TPessoa();
		tColaboradorEtapa = new TColaborador();
		tColaboradorSubEtapa = new TColaborador();
		tColaboradorTarefa = new TColaborador();
		
		tSubEtapa = new TSubEtapa();
		tSubTarefa = new TSubTarefa();
		
		tColab = new TColaborador();
		tProjetoColab = new TProjeto();
		tEtapaColab = new TEtapa();
		tTarefaColab = new TTarefa();
		tSubEtapaColab = new TSubEtapa();
		tSubTarefaColab = new TSubTarefa();
		
		tViewGerenteEtapa = new TViewGerenteEtapa();
		tViewSubEtapaColaborador = new TViewSubEtapaColaborador();
		tViewTarefaColaborador = new TViewTarefaColaborador();
		
		this.controlador_Backup.adicionarTableModel();
		this.ctrl_PreenchementoTela.adicionarTableModel();;
		this.ctrl_JTable_View.adicionarTableModels();
	}
	
	public void adicionarEventos()
	{
		this.controlador_Info_JInternal_Tela.adicionarEventos();;
		this.controlador_Cadastro.adicionarEventos();;
		this.controlador_Backup.adicionarEventos();;
		this.ctrl_JInternal_SQL.adicionarEventos();;
		this.ctrl_JTable.adicionarEventos();;
		this.ctrl_JTable_Colab.adicionarEventos();;
		this.ctrl_Listeners_Projeto.adicionarEventos();;
		this.ctrl_PopUp.adicionarEventos();;
		this.ctrl_TelaPrincipal.adicionarEventos();;
		this.ctrl_JTable_View.adicionarEventos();
	}
	
	public void sair() throws PropertyVetoException {
		
		TelaPrincipal telaPrincipal = janelaPrincipal.getTelaPrincipal();
		
		telaPrincipal.getTelaLoginSistema().getLoginField().setText("");
		telaPrincipal.getTelaLoginSistema().getSenhaField().setText("");
		telaPrincipal.exibirTela(TelaPrincipal.TELA_LOGIN);
		
		for(JInternalAbstract jAbstract: jifs) {
			jAbstract.setIcon(false);
			jAbstract.setVisible(false);
		}
	}

	public JanelaPrincipal getJanelaPrincipal() {
		return janelaPrincipal;
	}

	public JInternal_TelaCadastroSubEtapa getjInternal_TelaCadastroSubEtapa() {return jInternal_TelaCadastroSubEtapa;}
	public JInternal_TelaCadastroSubTarefa getjInternal_TelaCadastroSubTarefa() {return jInternal_TelaCadastroSubTarefa;}
	public JInternal_TelaCadastro_Etapa getjInternal_TelaCadastro_Etapa() {return jInternal_TelaCadastro_Etapa;}
	public JInternal_TelaCadastro_Tarefa getjInternal_TelaCadastro_Tarefa() {return jInternal_TelaCadastro_Tarefa;}
	public JInternal_TelaCadastro_Pessoa getjInternal_TelaCadastro_Pessoa() {return jInternal_TelaCadastro_Pessoa;}
	public JInternal_TelaCadastro_Projeto getjInternal_TelaCadastro_Projeto() {return jInternal_TelaCadastro_Projeto;}

	public JInternal_TelaInserirSQL getjInternal_TelaInserirSQL() {return jInternal_TelaInserirSQL;}

	public JInternal_TelaInfoEtapa getjInternal_TelaInfoEtapa() {return jInternal_TelaInfoEtapa;}
	public JInternal_TelaInfoPessoa getjInternal_TelaInfoPessoa() {return jInternal_TelaInfoPessoa;}
	public JInternal_TelaInfoPessoaOutrem getjInternal_TelaInfoPessoaOutrem() {return jInternal_TelaInfoPessoaOutrem;}
	public JInternal_TelaInfoTarefa getjInternal_TelaInfoTarefa() {return jInternal_TelaInfoTarefa;}
	public JInternal_TelaInfoProjeto_Etapas getjInternal_TelaInfoProjeto_Etapas() {return jInternal_TelaInfoProjeto_Etapas;}
	public JInternal_TelaInfoPessoa_Projetos getjInternal_TelaInfoPessoa_Projetos() {return jInternal_TelaInfoPessoa_Projetos;}
	public JInternal_TelaInfoProjeto_Etapas_Simples getjInternal_TelaInfoProjeto_Etapas_Simples() {return jInternal_TelaInfoProjeto_Etapas_Simples;}
	public JInternal_TelaAgendarBackup getjInternal_TelaAgendarBackup() {return jInternal_TelaAgendarBackup;}
	
	public JInternal_InfoLog getjInternal_InfoLog() {return jInternal_InfoLog;}
	public JInternal_TabelaLogs getjInternal_TabelaLogs() {return jInternal_TabelaLogs;}
	public JInternal_TelaBackups getjInternal_TelaBackups() {return jInternal_TelaBackups;}
	public JInternal_TabelaPessoas getjInternal_TabelaPessoas() {return jInternal_TabelaPessoas;}
	public JInternal_TabelaPessoasColaboradores getjInternal_TabelaPessoasColaboradores() {return jInternal_TabelaPessoasColaboradores;}
	
	public JInternal_TelaInfoPessoaOutremSimples getjInternal_TelaInfoPessoaOutremSimples() {return jInternal_TelaInfoPessoaOutremSimples;}
	public JInternal_TelaInfoSubEtapa getjInternal_TelaInfoSubEtapa() {return jInternal_TelaInfoSubEtapa;}
	public JInternal_TelaInfoSubTarefa getjInternal_TelaInfoSubTarefa() {return jInternal_TelaInfoSubTarefa;}

	public Ctrl_Info_JInternal_Tela getControlador_Info_JInternal_Tela() {return controlador_Info_JInternal_Tela;}
	public Ctrl_Cadastro getControlador_Cadastro() {return controlador_Cadastro;}
	public Ctrl_Backup getControlador_Backup() {return controlador_Backup;}
	public Ctrl_PreenchementoTela getCtrl_PreenchementoTela() {return ctrl_PreenchementoTela;}

	public SubEtapa getSubEtapa_Atual() {return subEtapa_Atual;}
	public SubTarefa getSubTarefa_Atual() {return subTarefa_Atual;}
	
	public void setSubEtapa_Atual(SubEtapa subEtapa_Atual) {this.subEtapa_Atual = subEtapa_Atual;}
	public void setSubTarefa_Atual(SubTarefa subTarefa_Atual) {this.subTarefa_Atual = subTarefa_Atual;}

	public JInternal_ColaboradoresEtapa getjInternal_ColaboradoresEtapa() {return jInternal_ColaboradoresEtapa;}
	public JInternal_ColaboradoresSubEtapa getjInternal_ColaboradoresSubEtapa() {return jInternal_ColaboradoresSubEtapa;}
	public JInternal_ColaboradoresTarefa getjInternal_ColaboradoresTarefa() {return jInternal_ColaboradoresTarefa;}

	public JIF_Inf_Proj_Colab getJif_Inf_Proj_Colab() {return jif_Inf_Proj_Colab;}
	public JIF_Inf_Etp_Colab getJif_Inf_Etp_Colab() {return jif_Inf_Etp_Colab;}
	public JIF_Inf_SbEtp_Colab getJif_Inf_SbEtp_Colab() {return jif_Inf_SbEtp_Colab;}
	public JIF_Inf_SbTarf_Colab getJif_Inf_SbTarf_Colab() {return jif_Inf_SbTarf_Colab;}
	public JIF_Inf_Tarf_Colab getJif_Inf_Tarf_Colab() {return jif_Inf_Tarf_Colab;}

	public TPessoa gettColaboradorProjeto() {return tColaboradorProjeto;}
	public TColaborador gettColaboradorEtapa() {return tColaboradorEtapa;}
	public TColaborador gettColaboradorSubEtapa() {return tColaboradorSubEtapa;}
	public TColaborador gettColaboradorTarefa() {return tColaboradorTarefa;}

	public TColaborador gettColab() {return tColab;}
	public TProjeto gettProjetoColab() {return tProjetoColab;}
	public TEtapa gettEtapaColab() {return tEtapaColab;}
	public TTarefa gettTarefaColab() {return tTarefaColab;}
	public TSubEtapa gettSubEtapaColab() {return tSubEtapaColab;}
	public TSubTarefa gettSubTarefaColab() {return tSubTarefaColab;}

	public TEtapa gettEtapa() {return tEtapa;}
	public TTarefa gettTarefa() {return tTarefa;}
	public TPessoa gettPessoa() {return tPessoa;}
	public TObject gettObject() {return tObject;}
	public TBackup gettBackup() {return tBackup;}
	public TProjeto gettProjeto() {return tProjeto;}
	public TSubEtapa gettSubEtapa() {return tSubEtapa;}
	public TSubTarefa gettSubTarefa() {return tSubTarefa;}
	public TLogUpdate gettLogUpdate() {return tLogUpdate;}
	public TColaborador gettColaborador() {return tColaborador;}
	public TColaboracoes gettColaboracoes() {return tColaboracoes;}
	public TCaracteristicaExtra gettCaracteristicaExtra() {return tCaracteristicaExtra;}
	public TCaracteristicaExtra gettCaracteristicaExtra2() {return tCaracteristicaExtra2;}

	public TViewGerenteEtapa gettViewGerenteEtapa() {return tViewGerenteEtapa;}
	public TViewSubEtapaColaborador gettViewSubEtapaColaborador() {return tViewSubEtapaColaborador;}
	public TViewTarefaColaborador gettViewTarefaColaborador() {return tViewTarefaColaborador;}

	public Etapa getEtapa_Atual() {return etapa_Atual;}
	public Tarefa getTarefa_Atual() {return tarefa_Atual;}
	public Pessoa getPessoa_Logada() {return pessoa_Logada;}
	public Pessoa getPessoa_Outrem() {return pessoa_Outrem;}
	public Projeto getProjeto_Atual() {return projeto_Atual;}
	public String getType_User_Logado() {return type_User_Logado;}
	public LogUpdate getLogUpdate_Atual() {return logUpdate_Atual;}
	public Colaborador getColaborador_Atual() {return colaborador_Atual;}
	public boolean isBool_Colaborador_Ativado() {return bool_Colaborador_Ativado;}
	public void setEtapa_Atual(Etapa etapa_Atual) {this.etapa_Atual = etapa_Atual;}
	public void setTarefa_Atual(Tarefa tarefa_Atual) {this.tarefa_Atual = tarefa_Atual;}
	public void setPessoa_Logada(Pessoa pessoa_Logada) {this.pessoa_Logada = pessoa_Logada;}
	public void setPessoa_Outrem(Pessoa pessoa_Outrem) {this.pessoa_Outrem = pessoa_Outrem;}
	public void setProjeto_Atual(Projeto projeto_Atual) {this.projeto_Atual = projeto_Atual;}
	public void setLogUpdate_Atual(LogUpdate logUpdate_Atual) {this.logUpdate_Atual = logUpdate_Atual;}
	public void setType_User_Logado(String type_User_Logado) {this.type_User_Logado = type_User_Logado;}
	public void setColaborador_Atual(Colaborador colaborador_Atual) {this.colaborador_Atual = colaborador_Atual;}
	public void setBool_Colaborador_Ativado(boolean bool_Colaborador_Ativado) {this.bool_Colaborador_Ativado = bool_Colaborador_Ativado;}
	
	public Pessoa getPessoa_Atual_Colab() {return pessoa_Atual_Colab;}
	public Projeto getProjeto_Atual_Colab() {return projeto_Atual_Colab;}
	public Etapa getEtapa_Atual_Colab() {return etapa_Atual_Colab;}
	public Tarefa getTarefa_Atual_Colab() {return tarefa_Atual_Colab;}
	public SubEtapa getSubEtapa_Atual_Colab() {return subEtapa_Atual_Colab;}
	public SubTarefa getSubTarefa_Atual_Colab() {return subTarefa_Atual_Colab;}

	
	
	public void setPessoa_Atual_Colab(Pessoa pessoa_Atual_Colab) {this.pessoa_Atual_Colab = pessoa_Atual_Colab;}
	public void setProjeto_Atual_Colab(Projeto projeto_Atual_Colab) {this.projeto_Atual_Colab = projeto_Atual_Colab;}
	public void setEtapa_Atual_Colab(Etapa etapa_Atual_Colab) {this.etapa_Atual_Colab = etapa_Atual_Colab;}
	public void setTarefa_Atual_Colab(Tarefa tarefa_Atual_Colab) {this.tarefa_Atual_Colab = tarefa_Atual_Colab;}
	public void setSubEtapa_Atual_Colab(SubEtapa subEtapa_Atual_Colab) {this.subEtapa_Atual_Colab = subEtapa_Atual_Colab;}
	public void setSubTarefa_Atual_Colab(SubTarefa subTarefa_Atual_Colab) {this.subTarefa_Atual_Colab = subTarefa_Atual_Colab;}

	public static PopUp getPopUpCaracteristica() {return popUpCaracteristica;}
	public static PopUp getPopUp() {return popUp;}
	
}
