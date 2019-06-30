package br.com.pbd2019_1.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.itextpdf.text.DocumentException;

import br.com.pbd2019_1.controll.Controlador_Principal;
import br.com.pbd2019_1.dao.DAOConfigDefault;
import br.com.pbd2019_1.dao.DAOResBackup;
import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.entidade.ConfigDefault;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternal_Backup_Efetuando;
import br.com.pbd2019_1.view.JInternal_InfoLog;
import br.com.pbd2019_1.view.JInternal_Sobre;
import br.com.pbd2019_1.view.JInternal_TabelaLogs;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
import br.com.pbd2019_1.view.JInternal_TelaAgendarBackup;
import br.com.pbd2019_1.view.JInternal_TelaBackups;
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
import br.com.pbd2019_1.view.MeuJDialog;

public class Principal{
	
	static boolean isEfetuado = false;
//	static Long tempoEntreVerificacao = (60000l * 30l); //30 min
	static Long tempoEntreVerificacao = (60000l * 1l); //1 min
	static Long tempo1dia = (60000l * 60l) * 24l;
//	static Long tempo1dia = (60000l * 60l);
	static Long tempoEspera = 0l;
	
	public static void main(String[] args) throws FileNotFoundException, DocumentException, BOException, DAOException {
		
		
/*		
		List<String> antes = new ArrayList<>();
		List<String> depois = new ArrayList<>();
		List<String> coluna = new ArrayList<>();
		
		Collections.addAll(antes, "", "", "", "");
		Collections.addAll(depois, "Novo 1", "Novo 2", "Novo 3", "Novo 4");
		Collections.addAll(coluna, "coluna1", "coluna2", "coluna3", "coluna4");
		
		
		LogUpdate log = new LogUpdate();
		
		
		log.setAntes(antes);
		log.setDepois(depois);
		log.setColuna(coluna);
		log.setData_log(LocalDateTime.now());
		log.setTipo("Cadastro");
		log.setTabela("Pessoa");
		log.setId_tabela(1);
		log.setResponsavel("11629633445");
		
		Fachada.getInstance().carregarBoLogUpdate();
		Fachada.getInstance().getBoLogUpdate().inserir(log);
		
		
		LogUpdate log2 = Fachada.getInstance().getBoLogUpdate().buscar(log.getId());
		System.out.println("\n\nImprimir");
		System.out.println(log2.toString());*/
		
/*
		Fachada.getInstance().carregarBoProjeto();
		Fachada.getInstance().carregarBoEtapa();
		Fachada.getInstance().carregarBoTarefa();
		Fachada.getInstance().carregarBoColaborador();
		
		
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		
		Projeto projeto = Fachada.getInstance().getBoProjeto().buscarPorPessoa(pessoa).get(4);
		
		projeto.setColaboradores(
				Fachada.getInstance().getBoColaborador().buscarPorProjeto(projeto)
				);
		projeto.setEtapas(
				Fachada.getInstance().getBoEtapa().buscarPorProjeto(projeto)
				);
		
		projeto.getEtapas().forEach(etapa->{
			try 
			{
				etapa.setTarefas(					
						Fachada.getInstance().getBoTarefa().buscarPorEtapa(etapa)
						);
			} catch (DAOException e) {e.printStackTrace();}
		});
		
		
		DAOResRelatorio.getInstance().gerarRelatorio(2 , projeto, "C:\\Users\\Aluno.WIN-OT9K4KMKI2A\\Desktop\\TesteRelatorio.pdf");
		
		System.exit(0);
*/

		/*
		UIManager.put("DesktopPaneUI","javax.swing.plaf.basic.BasicDesktopPaneUI");
		
		try 
		{
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
		
		JanelaLoading janelaLoading = new JanelaLoading();
		janelaLoading.setVisible(true);
		janelaLoading.etapaAtual("Banco de Dados!", 0);
		
		Fachada.getInstance().carregarBo();
		
		janelaLoading.etapaAtual("Banco de Dados!", 5);
		
		Fachada.getInstance().carregarBoEtapa();
		
		janelaLoading.etapaAtual("Banco de Dados!", 10);
		
		Fachada.getInstance().carregarBoBackup();
		
		janelaLoading.etapaAtual("Banco de Dados!", 15);
		
		Fachada.getInstance().carregarBoPessoa();
		
		janelaLoading.etapaAtual("Banco de Dados!", 20);
		
		Fachada.getInstance().carregarBoTarefa();
		
		janelaLoading.etapaAtual("Banco de Dados!", 25);
		
		Fachada.getInstance().carregarBoContato();
		
		janelaLoading.etapaAtual("Banco de Dados!", 30);
		
		Fachada.getInstance().carregarBoProjeto();
		
		janelaLoading.etapaAtual("Banco de Dados!", 35);
		
		Fachada.getInstance().carregarBoLogUpdate();
		
		janelaLoading.etapaAtual("Banco de Dados!", 40);
		
		Fachada.getInstance().carregarBoColaborador();
		
		janelaLoading.etapaAtual("Banco de Dados!", 45);
		
		Fachada.getInstance().carregarBoCaracteristicaExtra();
		
		janelaLoading.etapaAtual("Banco de Dados Carregado!", 50);
		janelaLoading.etapaAtual("Janela Principal!", 50);
		
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
		JInternal_TelaBackups jInternal_TelaBackups = new JInternal_TelaBackups();
		JInternal_TabelaLogs jInternal_TabelaLogs = new JInternal_TabelaLogs();
		JInternal_InfoLog jInternal_InfoLog = new JInternal_InfoLog();
		JInternal_TelaAgendarBackup jInternal_TelaAgendarBackup = new JInternal_TelaAgendarBackup();
		
		JInternal_Sobre jInternal_Sobre = new JInternal_Sobre();
		
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
		jDesktopPane.add(jInternal_TelaBackups);
		jDesktopPane.add(jInternal_TabelaLogs);
		jDesktopPane.add(jInternal_InfoLog);
		jDesktopPane.add(jInternal_TelaAgendarBackup);
		jDesktopPane.add(jInternal_Sobre);
		jDesktopPane.add(JInternal_Backup_Efetuando.getInstance());
		
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
				jInternal_TabelaPessoasColaboradores,
				jInternal_TelaBackups,
				jInternal_TabelaLogs,
				jInternal_InfoLog,
				jInternal_TelaAgendarBackup,
				jInternal_Sobre);
		
		janelaLoading.etapaAtual("Parâmetros JInternals Carregado!", 90);
		janelaLoading.etapaAtual("Adicionando TableModels!", 90);
		
		controlador.adicionarTableModels();
		
		janelaLoading.etapaAtual("TableModels Adicionados!", 95);
		janelaLoading.etapaAtual("Adicionando Eventos!", 95);
		
		controlador.adicionarEventosTelaPrincipal();
		controlador.adicionarEventoJFrame(janela);
		
		janelaLoading.etapaAtual("Eventos Adicionados!", 100);
		janelaLoading.etapaAtual("Concluído!", 100);
		janelaLoading.dispose();
		
		janela.setVisible(true);
		
		new Thread(()->
		{
			while(true)
			{
				try 
				{
					if(isEfetuado)
					{
						tempoEspera = (tempo1dia - tempoEntreVerificacao);
						isEfetuado = false;
					}
					else
					{
						tempoEspera = tempoEntreVerificacao;
					}

					Thread.sleep(tempoEspera);

					ConfigDefault config = DAOConfigDefault.loadConfig();

					if(config != null && config.getHora_bakup() != null && config.getHora_bakup().length() == 8)
					{
						int[] hora = DateUtil.TimeUtil.horario(config.getHora_bakup());

						if(LocalTime.now().isAfter(LocalTime.of(hora[0], hora[1], hora[2])))
						{
							String arquivoNome = "PBD_AUTO_BACKUP" + "_" + new SimpleDateFormat("HH-mm-ss").format(new Date());
							String arquivoPathParent = "C:\\PBD_BACKUP\\PBD";
							String arquivoPathAbsolute = arquivoPathParent + "\\" + arquivoNome;

							Backup b = new Backup();
							b.setAutor_backup("BACKUP AUTOMATICO");
							b.setData_backup(LocalDateTime.now());
							b.setLocal_backup(arquivoPathAbsolute);

							try 
							{
								DAOResBackup.getInstance().executarOperacaoBackup(arquivoPathParent, arquivoNome, "postgres", "13111996", DAOResBackup.BACKUP);
								b.setStatus_backup(Backup.EFETUADO);
								Fachada.getInstance().inserir(b);
								isEfetuado = true;
							}
							catch (ValidacaoException e) 
							{
								e.printStackTrace();
								try 
								{
									b.setStatus_backup(Backup.ERRO);
									Fachada.getInstance().inserir(b);
								} 
								catch (ValidacaoException e1) 
								{
									MeuJDialog.exibirAlertaErro(null, "Erro", e1.getMessage());
								}
								MeuJDialog.exibirAlertaErro(null, "ERRO AO FAZER BACKUP AUTOMATICO", e.getMessage());
							}
						}
					}
				}
				catch (ClassNotFoundException | IOException | InterruptedException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "ERRO AO FAZER BACKUP AUTOMATICO", e.getMessage());
				}
			}
		}).start();
*/
		
	}
	
}
