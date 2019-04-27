package br.com.pbd2019_1.app;

import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import br.com.pbd2019_1.controll.Controlador_Principal;
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
import br.com.pbd2019_1.view.JanelaLoading;
import br.com.pbd2019_1.view.JanelaPrincipal;

public class Principal {

	public static void main(String[] args) {
		UIManager.put("DesktopPaneUI","javax.swing.plaf.basic.BasicDesktopPaneUI");
		
		
		
		
		
		/*Etapa e = new Etapa();
		e.setId(1);
		
		try {
			Fachada.getInstance().carregarBo();
			
			
			System.out.println(Fachada.getInstance().buscarHQL(Etapa.class, "select e from Etapa e where e.id = 1"));
			
			
//			float f = Fachada.getInstance().getBoEtapa().recalcularPorcentagem(e);
			
//			System.out.println(f);
		
			List<Object[]> o = Fachada.getInstance().inserirSQLGenerica("select e.id from Etapa e where e.id = 2");
			
			System.out.println(o.toString());
			
			for(int i = 0; i < o.size(); i++) 
			{
				Object ob = o.get(i);
				
				if(ob instanceof Object[]) 
				{
					Object[] obV = (Object[])ob;
					for(Object obj: obV)
						System.out.println(obj.toString());
				}
				else
					System.out.println(ob.toString());
				
				System.out.println(ob.toString());
				
				
				if(o.get(i) instanceof Number[]) 
				{
					Number[] ov = (Number[]) o.get(i);
					System.out.println(ov.toString());
				}
				else
				{
					Object[] ov = o.get(i);
					System.out.println(ov.toString());
				}
				
				
			}
			
			System.exit(0);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JanelaLoading janelaLoading = new JanelaLoading();
		janelaLoading.setVisible(true);
		janelaLoading.etapaAtual("Banco de Dados!", 0);
		
		Fachada.getInstance().carregarBo();
		
		janelaLoading.etapaAtual("Banco de Dados!", 6);
		
		Fachada.getInstance().carregarBoEtapa();
		
		janelaLoading.etapaAtual("Banco de Dados!", 12);
		
		Fachada.getInstance().carregarBoPessoa();
		
		janelaLoading.etapaAtual("Banco de Dados!", 18);
		
		Fachada.getInstance().carregarBoTarefa();
		
		janelaLoading.etapaAtual("Banco de Dados!", 24);
		
		Fachada.getInstance().carregarBoContato();
		
		janelaLoading.etapaAtual("Banco de Dados!", 30);
		
		Fachada.getInstance().carregarBoProjeto();
		
		janelaLoading.etapaAtual("Banco de Dados!", 36);
		
		Fachada.getInstance().carregarBoLogUpdate();
		
		janelaLoading.etapaAtual("Banco de Dados!", 42);
		
		Fachada.getInstance().carregarBoColaborador();
		
		janelaLoading.etapaAtual("Banco de Dados!", 48);
		
		Fachada.getInstance().carregarBoCaracteristicaExtra();
		
		janelaLoading.etapaAtual("Banco de Dados Carregado!", 54);
		janelaLoading.etapaAtual("Janela Principal!", 54);
		
		JanelaPrincipal janela = new JanelaPrincipal();
		
		janelaLoading.etapaAtual("Janela Principal Carregada!", 55);
		janelaLoading.etapaAtual("Telas de Cadastro!", 55);
		
		JInternal_TelaCadastro_Etapa jInternal_TelaCadastro_Etapa = new JInternal_TelaCadastro_Etapa();;
		JInternal_TelaCadastro_Projeto jInternal_TelaCadastro_Projeto = new JInternal_TelaCadastro_Projeto();;
		JInternal_TelaCadastro_Pessoa jInternal_TelaCadastro_Pessoa = new JInternal_TelaCadastro_Pessoa();;
		JInternal_TelaCadastro_Tarefa jInternal_TelaCadastro_Tarefa = new JInternal_TelaCadastro_Tarefa();
		
		janelaLoading.etapaAtual("Telas de Cadastro Carregadas!", 60);
		janelaLoading.etapaAtual("Restante das Telas!", 60);

		JInternal_TelaInfoEtapa jInternal_TelaInfoEtapa = new JInternal_TelaInfoEtapa();
		JInternal_TelaInserirSQL jInternal_TelaInserirSQL = new JInternal_TelaInserirSQL();
		JInternal_TelaInfoPessoa jInternal_TelaInfoPessoa = new JInternal_TelaInfoPessoa();
		JInternal_TelaInfoTarefa jInternal_TelaInfoTarefa = new JInternal_TelaInfoTarefa() ;
		JInternal_TelaInfoProjeto_Etapas jInternal_TelaInfoProjeto_Etapas = new JInternal_TelaInfoProjeto_Etapas();
		JInternal_TelaInfoPessoa_Projetos jInternal_TelaInfoPessoa_Projetos = new JInternal_TelaInfoPessoa_Projetos();
		JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoProjeto_Etapas_Simples = new JInternal_TelaInfoProjeto_Etapas_Simples();
		JInternal_TabelaPessoas jInternal_TabelaPessoas = new JInternal_TabelaPessoas();
		JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores = new JInternal_TabelaPessoasColaboradores();
		
		janelaLoading.etapaAtual("Restante das Telas Carregadas!", 70);
		janelaLoading.etapaAtual("Área de Trabalho!", 70);
		
		JDesktopPane jDesktopPane = janela.getTelaPrincipal().getjDesktopPane();
		
		janelaLoading.etapaAtual("Área de Trabalho Carregada!", 71);
		
		janelaLoading.etapaAtual("Incorporando telas!", 71);
		
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
		
		janelaLoading.etapaAtual("Telas Incorporadas!", 80);
		janelaLoading.etapaAtual("Controlador!", 80);
		
		Controlador_Principal controlador = new Controlador_Principal(janela.getTelaPrincipal());
		
		janelaLoading.etapaAtual("Controlador Carregado!", 85);
		janelaLoading.etapaAtual("Parâmetros JInternals!", 85);
		
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
		
		janelaLoading.etapaAtual("Parâmetros JInternals Carregado!", 90);
		janelaLoading.etapaAtual("Adicionando TableModels!", 90);
		
		controlador.adicionarTableModels();
		
		janelaLoading.etapaAtual("TableModels Adicionados!", 95);
		janelaLoading.etapaAtual("Adicionando Eventos!", 95);
		
		controlador.adicionarEventosTelaPrincipal();
		
		janelaLoading.etapaAtual("Eventos Adicionados!", 100);
		janelaLoading.etapaAtual("Concluído!", 100);
		janelaLoading.dispose();
		
		janela.setVisible(true);
		
	}
	
}
