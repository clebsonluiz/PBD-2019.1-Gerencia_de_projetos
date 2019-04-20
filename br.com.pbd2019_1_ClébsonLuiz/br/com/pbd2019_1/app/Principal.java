package br.com.pbd2019_1.app;

import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import br.com.pbd2019_1.controll.Controlador_Principal;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
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
import br.com.pbd2019_1.view.JanelaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		try {
			Fachada.getInstance().buscarAll(Pessoa.class);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JanelaPrincipal janela = new JanelaPrincipal();
		
		
		JInternal_TelaCadastro_Etapa jInternal_TelaCadastro_Etapa = new JInternal_TelaCadastro_Etapa();;
		JInternal_TelaCadastro_Projeto jInternal_TelaCadastro_Projeto = new JInternal_TelaCadastro_Projeto();;
		JInternal_TelaCadastro_Pessoa jInternal_TelaCadastro_Pessoa = new JInternal_TelaCadastro_Pessoa();;
		JInternal_TelaCadastro_Tarefa jInternal_TelaCadastro_Tarefa = new JInternal_TelaCadastro_Tarefa();
		
		JInternal_TelaInfoEtapa jInternal_TelaInfoEtapa = new JInternal_TelaInfoEtapa();
		JInternal_TelaInserirSQL jInternal_TelaInserirSQL = new JInternal_TelaInserirSQL();
		JInternal_TelaInfoPessoa jInternal_TelaInfoPessoa = new JInternal_TelaInfoPessoa();
		JInternal_TelaInfoTarefa jInternal_TelaInfoTarefa = new JInternal_TelaInfoTarefa() ;
		JInternal_TelaInfoProjeto_Etapas jInternal_TelaInfoProjeto_Etapas = new JInternal_TelaInfoProjeto_Etapas();
		JInternal_TelaInfoPessoa_Projetos jInternal_TelaInfoPessoa_Projetos = new JInternal_TelaInfoPessoa_Projetos();
		JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoProjeto_Etapas_Simples = new JInternal_TelaInfoProjeto_Etapas_Simples();
		JInternal_TabelaPessoas jInternal_TabelaPessoas = new JInternal_TabelaPessoas();
		JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores = new JInternal_TabelaPessoasColaboradores();
		
		JDesktopPane jDesktopPane = janela.getTelaPrincipal().getjDesktopPane();
		
		jDesktopPane.add(jInternal_TelaCadastro_Etapa);
		jDesktopPane.add(jInternal_TelaCadastro_Projeto);
		jDesktopPane.add(jInternal_TelaCadastro_Pessoa);
		jDesktopPane.add(jInternal_TelaCadastro_Tarefa);
		jDesktopPane.add(jInternal_TelaInfoEtapa);
		jDesktopPane.add(jInternal_TelaInserirSQL);
		jDesktopPane.add(jInternal_TelaInfoPessoa);
		jDesktopPane.add(jInternal_TelaInfoTarefa);
		jDesktopPane.add(jInternal_TelaInfoProjeto_Etapas);
		jDesktopPane.add(jInternal_TelaInfoPessoa_Projetos);
		jDesktopPane.add(jInternal_TelaInfoProjeto_Etapas_Simples);
		jDesktopPane.add(jInternal_TabelaPessoas);
		jDesktopPane.add(jInternal_TabelaPessoasColaboradores);
		
		Controlador_Principal controlador = new Controlador_Principal(janela.getTelaPrincipal());
		controlador.adicionarJInternals(
				jInternal_TelaCadastro_Etapa,
				jInternal_TelaCadastro_Projeto,
				jInternal_TelaCadastro_Pessoa,
				jInternal_TelaCadastro_Tarefa,
				jInternal_TelaInfoEtapa,
				jInternal_TelaInserirSQL,
				jInternal_TelaInfoPessoa,
				jInternal_TelaInfoTarefa,
				jInternal_TelaInfoProjeto_Etapas,
				jInternal_TelaInfoPessoa_Projetos,
				jInternal_TelaInfoProjeto_Etapas_Simples,
				jInternal_TabelaPessoas,
				jInternal_TabelaPessoasColaboradores);
		controlador.adicionarTableModels();
		controlador.adicionarEventosTelaPrincipal();
		
		janela.setVisible(true);
	}
	
}
