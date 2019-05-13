package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.CellRenderer;
import br.com.pbd2019_1.tabelas.TBackup;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TLogUpdate;
import br.com.pbd2019_1.tabelas.TObject;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternalAbstract;
import br.com.pbd2019_1.view.JInternal_Backup_Efetuando;
import br.com.pbd2019_1.view.JInternal_TabelaPessoas;
import br.com.pbd2019_1.view.JInternal_TabelaPessoasColaboradores;
import br.com.pbd2019_1.view.JInternal_TelaAlerta;
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
import br.com.pbd2019_1.view.PopUp;
import br.com.pbd2019_1.view.TelaMenu;
import br.com.pbd2019_1.view.TelaOpcoes;
import br.com.pbd2019_1.view.TelaPessoa;
import br.com.pbd2019_1.view.TelaPrincipal;

public class Controlador_Principal {

	
	/**
	 * janelaEscolherCaminhoArquivo - JFileChooser para escolher os caminhos dos arquivos
	 *
	 * pessoa_Logada - Pessoa que está logada no sistema atualmente
	 * pessoa_Outrem - Quando a pessoa que está logado no sistema ver outra pessa
	 * projeto_Atual - Projeto que uma pessoa quer ver
	 * etapa_Atual - Etapa pertecente ao projeto anterior que uma pessoa quer ver
	 * tarefa_Atual - Tarefa que pertence a Etapa anterior que uma pessoa quer ver
	 * colaborador_Atual - Quando a pessoa vai ver um projeto que está como colaborador
	 * logUpdate_Atual - Log que está sendo visutalizado atualmente.
	 * type_User_Logado - Tipo do Usuario que está logado no sistema
	 * bool_Colaborador_Ativado - Indica que um projeto que está sendo visto é um projeto de colaborador.
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
	private JInternal_TelaInfoProjeto_Etapas_Simples jInternal_TelaInfoProjeto_Etapas_Simples;
	private JInternal_TabelaPessoas jInternal_TabelaPessoas;
	private JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores;
	private JInternal_TelaBackups jInternal_TelaBackups;
	
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
	
	
	private Controlador_Info_JInternal_Tela controlador_Info_JInternal_Tela;
	private Controlador_Cadastro controlador_Cadastro;
	private Controlador_Backup controlador_Backup;

	
	private static PopUp popUpCaracteristica = new PopUp(new String[]{"Salvar", "Excluir"});
	private static PopUp popUp = new PopUp(new String[]{"", "Excluir", ""});
	
	public Controlador_Principal(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		this.controlador_Info_JInternal_Tela = new Controlador_Info_JInternal_Tela(this);
		this.controlador_Cadastro = new Controlador_Cadastro(this);
		this.controlador_Backup = new Controlador_Backup(this);
	}

	public void adicionarEventoJFrame(JFrame frame)
	{
		this.controlador_Backup.adicionarEvento(frame);
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
			JInternal_TelaBackups jInternal_TelaBackups) {
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
	}

	public void adicionarTableModels() {
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
		
		JTable tableTarefas = jInternal_TelaInfoEtapa
				.getTelaEtapa_Tarefas()
				.getTelaTarefas()
				.getTable();
		
		JTable tableCaracteristicas2 = jInternal_TelaInfoPessoa
				.getTelaInfoPessoa()
				.getTelaContatoCaracteristica()
				.getJTable();
		
		JTable tableEtapas = jInternal_TelaInfoProjeto_Etapas
				.getTelaProjeto_Etapas()
				.getTelaEtapas()
				.getTable();
		
		JTable tableColaboradores = jInternal_TelaInfoProjeto_Etapas
				.getTelaProjeto_Etapas()
				.getTelaColaboradores()
				.getTable();
		
		JTable tableCaracteristicas = jInternal_TelaInfoPessoa_Projetos
				.getTelaInfoPessoaProjetos()
				.getTelaInfoPessoa()
				.getTelaContatoCaracteristica()
				.getJTable();
		
		JTable tableProjetos = jInternal_TelaInfoPessoa_Projetos
				.getTelaInfoPessoaProjetos()
				.getTelaProjetos()
				.getTable();
		
		JTable tableColaboracoes = jInternal_TelaInfoPessoa_Projetos
				.getTelaInfoPessoaProjetos()
				.getTelaColaboracoes()
				.getTable();
		
		JTable tableEtapas2 = jInternal_TelaInfoProjeto_Etapas_Simples
				.getTelaProjeto_Etapas_Simples()
				.getTelaEtapas()
				.getTable();
		
		JTable tablePessoas = jInternal_TabelaPessoas
				.getTelaPessoas()
				.getTable();
		
		JTable tablePessoasDisponiveis = jInternal_TabelaPessoasColaboradores
				.getTelaPessoas()
				.getTable();
		
		JTable tableInserirSQL = jInternal_TelaInserirSQL
				.getTelaInserirSQL()
				.getTable();
		
		JTable tableBackups = jInternal_TelaBackups
				.getTelaBackups()
				.getTable();
		
		tableTarefas.setModel(tTarefa);
		tableTarefas.getColumnModel().getColumn(2).setCellEditor(tTarefa.getCellEditor());
		tableCaracteristicas.setModel(tCaracteristicaExtra);
		tableEtapas.setModel(tEtapa);
		tableColaboradores.setModel(tColaborador);
		tableColaboradores.getColumnModel().getColumn(2).setCellEditor(tColaborador.getCellEditor());
		tableCaracteristicas2.setModel(tCaracteristicaExtra2);
		tableProjetos.setModel(tProjeto);
		tableColaboracoes.setModel(tColaboracoes);
		tableEtapas2.setModel(tEtapa);
		tablePessoas.setModel(tPessoa);
		tablePessoasDisponiveis.setModel(tPessoa);

		tableInserirSQL.setModel(tObject);
		tableInserirSQL.setRowHeight(30);
		
		tableBackups.setModel(tBackup);
		
		tableTarefas.setDefaultRenderer(Object.class, new CellRenderer());
		tableTarefas.setDefaultRenderer(Object.class, new CellRenderer());
		tableCaracteristicas.setDefaultRenderer(Object.class, new CellRenderer());
		tableEtapas.setDefaultRenderer(Object.class, new CellRenderer());
		tableColaboradores.setDefaultRenderer(Object.class, new CellRenderer());
		tableColaboradores.setDefaultRenderer(Object.class, new CellRenderer());
		tableCaracteristicas2.setDefaultRenderer(Object.class, new CellRenderer());
		tableProjetos.setDefaultRenderer(Object.class, new CellRenderer());
		tableColaboracoes.setDefaultRenderer(Object.class, new CellRenderer());
		tableEtapas2.setDefaultRenderer(Object.class, new CellRenderer());
		tablePessoas.setDefaultRenderer(Object.class, new CellRenderer());
		tablePessoasDisponiveis.setDefaultRenderer(Object.class, new CellRenderer());
		
		tableTarefas.setRowHeight(30);
		tableTarefas.setRowHeight(30);
		tableCaracteristicas.setRowHeight(30);
		tableEtapas.setRowHeight(30);
		tableColaboradores.setRowHeight(30);
		tableColaboradores.setRowHeight(30);
		tableCaracteristicas2.setRowHeight(30);
		tableProjetos.setRowHeight(30);
		tableColaboracoes.setRowHeight(30);
		tableEtapas2.setRowHeight(30);
		tablePessoas.setRowHeight(30);
		tablePessoasDisponiveis.setRowHeight(30);
		
		
		adicionarMouseEventJTable(tableTarefas);
		adicionarMouseEventJTable(tableCaracteristicas);
		adicionarMouseEventJTable(tableEtapas);
		adicionarMouseEventJTable(tableColaboradores);
		adicionarMouseEventJTable(tableCaracteristicas2);
		adicionarMouseEventJTable(tableProjetos);
		adicionarMouseEventJTable(tableColaboracoes);
		adicionarMouseEventJTable(tableEtapas2);
		adicionarMouseEventJTable(tablePessoas);
		adicionarMouseEventJTable(tablePessoasDisponiveis);
		
		adicionarEventosPopUp();
		
		adicionarEventosComboBox(tableTarefas, tableColaboradores);
		
		controlador_Cadastro.adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Tarefa, tTarefa, jInternal_TelaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa());
		controlador_Cadastro.adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Etapa, tEtapa);
		controlador_Cadastro.adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Projeto, tProjeto);
		controlador_Cadastro.adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Pessoa, tPessoa);
		
		controlador_Backup.adicionarEvento(jInternal_TelaBackups, tBackup);
		
		controlador_Info_JInternal_Tela.adicionarEventosJInternals();
		
/*		adicionarEventoJInternal(jInternal_TelaInfoEtapa);
		adicionarEventoJInternal(jInternal_TelaInfoPessoa);
		adicionarEventoJInternal(jInternal_TelaInfoPessoa_Projetos);
		adicionarEventoJInternal(jInternal_TelaInfoProjeto_Etapas);
		adicionarEventoJInternal(jInternal_TelaInfoProjeto_Etapas_Simples);
		adicionarEventoJInternal(jInternal_TelaInfoTarefa);
		adicionarEventoJInternal(jInternal_TelaInserirSQL);*/
		
	}
	
	
	/**
	 * Eventos de Mouse das Tabelas mais importantes
	 * 
	 * */
	private void adicionarMouseEventJTable(JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.rowAtPoint(e.getPoint());
				int coluna = table.columnAtPoint(e.getPoint());
				
				table.setRowSelectionInterval(linha, linha);
				table.setColumnSelectionInterval(coluna, coluna);
				
				Object obj = table.getValueAt(linha, coluna);
				
				if(SwingUtilities.isRightMouseButton(e)) 
				{
					if(table.getModel() instanceof TCaracteristicaExtra) 
					{
						popUpCaracteristica.setjTableAtual(table);
						popUpCaracteristica.show(table, e.getX(), e.getY());
					} 
					else 
					{
						popUp.setjTableAtual(table);
						popUp.show(table, e.getX(), e.getY());
					}
				} 
				else 
				{
					if(table.getModel() instanceof TTarefa) 
					{
						if(coluna == 3) 
						{
							if(obj instanceof Boolean)
							{
								try 
								{
									if(bool_Colaborador_Ativado)
									{
										if(!colaborador_Atual.getPrivilegio().equals("Visitante"))
										{
											boolean b = (((Boolean)obj).booleanValue());
											tTarefa.setValueAt(b, linha, coluna);
											Fachada.getInstance().atualizar(tTarefa.getValor(linha));
										}
									}
									else 
									{
										boolean b = (((Boolean)obj).booleanValue());
										System.out.println(((Boolean)obj).booleanValue());
										System.out.println(b);
										tTarefa.setValueAt(b, linha, coluna);
										Fachada.getInstance().atualizar(tTarefa.getValor(linha));
										
										etapa_Atual.setPorcentagem_andamento(
												Fachada.getInstance()
													.getBoEtapa()
													.recalcularPorcentagem(etapa_Atual)
												);
										
										jInternal_TelaInfoEtapa
											.getTelaEtapa_Tarefas()
											.getTelaEtapa()
											.getBarraProgressBar()
											.setValue(
													Math.round(etapa_Atual.getPorcentagem_andamento())
													);
										
									}
								} 
								catch (ValidacaoException ve) 
								{
									JInternal_TelaAlerta.showAlerta("Erro ao alterar", ve.getMessage());
								}
							}
						}
						else if (coluna == 4)
						{
							try 
							{
								controlador_Info_JInternal_Tela
									.exibirJInternalInfoTarefa(
											tTarefa.getValor(linha)
											);
							} catch (PropertyVetoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoTarefa", e1.getMessage());
							}
						}
					} 
					else if (table.getModel() instanceof TEtapa)
					{
						if(coluna == 2) 
						{
							try 
							{
								controlador_Info_JInternal_Tela
									.exibirJInternalInfoEtapa(
											tEtapa.getValor(linha)
											);
							} 
							catch (ValidacaoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao consultar dados da etapa", e1.getMessage());
							}
							catch (PropertyVetoException e1)
							{
								JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoEtapa", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TProjeto)
					{
						if(coluna == 3)
						{
							try
							{
								bool_Colaborador_Ativado = false;
								projeto_Atual = tProjeto.getValor(linha);
								
								if(!type_User_Logado.equals(Pessoa.COMUM_USER))
								{
									controlador_Info_JInternal_Tela.exibirJInternalInfoProjetoEtapa(projeto_Atual);
								}
								else
								{
									controlador_Info_JInternal_Tela.exibirJInternalInfoProjetoEtapaSimples(projeto_Atual);
								}
							} 
							catch (ValidacaoException e1)
							{
								JInternal_TelaAlerta.showAlerta("Erro ao consultar dados do projeto", e1.getMessage());
							} 
							catch (PropertyVetoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoProjeto", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TColaborador)
					{
						if(coluna == 3) 
						{
							try 
							{
								controlador_Info_JInternal_Tela
									.exibirJInternalInfoColaborador(
											tColaborador.getValor(linha)
											);
							} 
							catch (ValidacaoException e1)
							{
								JInternal_TelaAlerta.showAlerta("Erro ao carregar informações do colaborador", e1.getMessage());
							} 
							catch (PropertyVetoException e1)
							{
								JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoPessoa do colaborador", e1.getMessage());
							}
							
						}
					}
					else if (table.getModel() instanceof TColaboracoes)
					{
						if(coluna == 3)
						{
							try 
							{
								bool_Colaborador_Ativado = true;
								
								controlador_Info_JInternal_Tela.exibirJInternalInfoColaboracoes(tColaboracoes.getValor(linha));
								
							} 
							catch (ValidacaoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao carregar info. do projeto de colaborador", e1.getMessage());
							} 
							catch (PropertyVetoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoProjeto de Colaborador", e1.getMessage());
							}
						}
					}
					else if (table.getModel() instanceof TLogUpdate)
					{
						if(coluna == 2)
						{
							//TODO - Abrir tela ver log update
							logUpdate_Atual = tLogUpdate.getValor(linha);
						}
					}
					else if (table.getModel() instanceof TPessoa)
					{
						if(coluna == 3)
						{
							//TODO - Abrir tela ver Pessoa outrem 
							try
							{
								controlador_Info_JInternal_Tela.exibirJInternalInfoPessoa(tPessoa.getValor(linha));
							} 
							catch (ValidacaoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao carregar dados de pessoa", e1.getMessage());
							}
							catch (PropertyVetoException e1) 
							{
								JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoTarefa", e1.getMessage());
							}
						}
					}
				}
			}
		});
	}
	
	/**
	 * Eventos do Popup<br>
	 * TODO
	 * */
	private void adicionarEventosPopUp() 
	{
		JMenuItem excluir = popUp.getMenuItens()[1];
		
		excluir.addActionListener(ActionEvent->{
			try 
			{
				if(bool_Colaborador_Ativado)
					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
				int linha = popUp.getjTableAtual().getSelectedRow();
				
				if(popUp.getjTableAtual().getModel() instanceof TTarefa) 
				{
					Fachada.getInstance().deletar(tTarefa.getValor(linha));
					tTarefa.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TEtapa) 
				{
					Fachada.getInstance().deletar(tEtapa.getValor(linha));
					tEtapa.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TProjeto) 
				{
					Fachada.getInstance().deletar(tProjeto.getValor(linha));
					tProjeto.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					Fachada.getInstance().deletar(tCaracteristicaExtra.getValor(linha));
					tCaracteristicaExtra.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TColaboracoes) 
				{
					Fachada.getInstance().deletar(tColaboracoes.getValor(linha));
					tColaboracoes.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TColaborador) 
				{
					Fachada.getInstance().deletar(tColaborador.getValor(linha));
					tColaborador.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TLogUpdate) 
				{
					Fachada.getInstance().deletar(tLogUpdate.getValor(linha));
					tLogUpdate.remover(linha);
				}
				else if(popUp.getjTableAtual().getModel() instanceof TPessoa) 
				{
					Fachada.getInstance().deletar(tPessoa.getValor(linha));
					tPessoa.remover(linha);
				}
			} 
			catch (ValidacaoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro na remoção", e.getMessage());
			}
		});
		
		JMenuItem excluirCaracteristica = popUpCaracteristica.getMenuItens()[1];
		
		
		excluirCaracteristica.addActionListener(ActionEvent->
		{
			try 
			{
				if(bool_Colaborador_Ativado)
					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
				int linha = popUpCaracteristica.getjTableAtual().getSelectedRow();
				
				if(popUpCaracteristica.getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					Fachada.getInstance().deletar(tCaracteristicaExtra.getValor(linha));
					tCaracteristicaExtra.remover(linha);
				}
			} 
			catch (ValidacaoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro na remoção", e.getMessage());
			}
		});
		
		JMenuItem salvarCaracteristica = popUpCaracteristica.getMenuItens()[0];
		
		salvarCaracteristica.addActionListener((ItemEvent)->
		{
			try 
			{
				if(bool_Colaborador_Ativado)
					if(!colaborador_Atual.getPrivilegio().equals("Administrador"))
						throw new ValidacaoException("Não tem permição");
					
				int linha = popUpCaracteristica.getjTableAtual().getSelectedRow();
				
				if(popUpCaracteristica.getjTableAtual().getModel() instanceof TCaracteristicaExtra) 
				{
					Object value = popUpCaracteristica.getjTableAtual().getValueAt(linha, 0);
					
					tCaracteristicaExtra.setValueAt(value, linha, 0);
					Fachada.getInstance().atualizar(tCaracteristicaExtra.getValor(linha));
				}
			} 
			catch (ValidacaoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro na remoção", e.getMessage());
			}
		});
		
	}
	
	
	private void adicionarEventosComboBox(JTable tarefaTable, JTable colaboradorTable) 
	{
		tTarefa.getCombo().addActionListener(ActionEvent->{
			
			if(tTarefa.getCombo().getSelectedItem() != null) 
			{
				int linha = tarefaTable.getSelectedRow();
				int coluna = tarefaTable.getSelectedColumn();
				
				if(linha >= 0 && coluna >= 0) 
				{ 
					try 
					{
						if(bool_Colaborador_Ativado)
							if(colaborador_Atual.getPrivilegio().equals("Visitante"))
								throw new ValidacaoException("Não tem permição");
						
						tTarefa.setValueAt(tTarefa.getCombo().getSelectedItem(),
								linha,
								coluna
								);
						Fachada.getInstance().atualizar(tTarefa.getValor(linha));
					} 
					catch (ValidacaoException e) 
					{
						JInternal_TelaAlerta.showAlerta("Erro ao atualizar info. tarefa", e.getMessage());
					}
				}
			}
		});
		
		tColaborador.getCombo().addActionListener(ActionEvent->{
			
			if(tColaborador.getCombo().getSelectedItem() != null) 
			{
				int linha = colaboradorTable.getSelectedRow();
				int coluna = colaboradorTable.getSelectedColumn();
				
				if(linha >= 0 && coluna >= 0) 
				{ 
					try 
					{
						tColaborador.setValueAt(tColaborador.getCombo().getSelectedItem(),
								linha,
								coluna
								);
						Fachada.getInstance().atualizar(tColaborador.getValor(linha));
					} 
					catch (ValidacaoException e) 
					{
						JInternal_TelaAlerta.showAlerta("Erro ao exibir atualizar info Colaborador", e.getMessage());
					}
				}
			}
		});
	}
	
	
	public void adicionarEventosTelaPrincipal() {
		
		telaPrincipal.getTelaCadastro_Pessoa()
			.getBotao().addActionListener(ActionEvent->{
				//TODO - 
				try 
				{
					TelaPessoa telaPessoa = telaPrincipal.getTelaCadastro_Pessoa();
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

					pessoa.setUser_type(Pessoa.COMUM_USER);
					Fachada.getInstance().inserir(pessoa);
					telaPrincipal.getTelaCadastro_Pessoa().limparCampos();
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao cadastrar pessoa", e.getMessage());
				}
				
			});
		
		telaPrincipal.getTelaLoginSistema()
			.getBtnLogar().addActionListener(ActionEvent->{
				//TODO - Inserir aqui o login
				try 
				{
					String login = telaPrincipal
							.getTelaLoginSistema()
							.getLoginField()
							.getTexto();
					String senha = telaPrincipal
							.getTelaLoginSistema()
							.getSenhaField()
							.getTexto();
					
					pessoa_Logada = Fachada
							.getInstance()
							.getBoPessoa()
							.buscarUsuario(
									login,
									senha
									);
					
					if(pessoa_Logada != null) 
					{
						type_User_Logado = pessoa_Logada.getUser_type();
						
						if(type_User_Logado.equals(Pessoa.COMUM_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
						else if(type_User_Logado.equals(Pessoa.ADMIN_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
						else if(type_User_Logado.equals(Pessoa.SUPER_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_SUPER);
						
						telaPrincipal.exibirTela(TelaPrincipal.TELA_PRINCIPAL);
						
						telaPrincipal.getTelaLoginSistema().getLblErro().setText("");
					}
					
					
				} 
				catch (ValidacaoException | NoSuchAlgorithmException | UnsupportedEncodingException e) 
				{
					telaPrincipal.getTelaLoginSistema().getLblErro().setText(e.getMessage());
				}
				
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
			if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				controlador_Info_JInternal_Tela.exibirJInternalInfoMinhaPessoa(pessoa_Logada);
			} 
			catch (ValidacaoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro ao Consultar Info de Pessoa", e.getMessage());
			} catch (PropertyVetoException e) {
				JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaInfoPessoa", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnLog().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM de ver os logs dos usuarios
			if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try
			{
				tLogUpdate.addAll((List<LogUpdate>) Fachada.getInstance().buscarAll(LogUpdate.class));
			} 
			catch (ValidacaoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro ao consultar a lista de Logs", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnPessoas().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM ver pessoas
			if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try
			{
				tPessoa.addAll((List<Pessoa>) Fachada.getInstance().buscarAll(Pessoa.class));
				jInternal_TabelaPessoas.queroFoco();
			} 
			catch (ValidacaoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro ao consultar a lista de pessoas", e.getMessage());
			}
			catch (PropertyVetoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaPessoas", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnBackup().addActionListener(ActionEvent->{
			//TODO - Inserir evento Backups BD
			if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				jInternal_TelaBackups.queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro ao exibir TelaBackup", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnSQL().addActionListener(ActionEvent->{
			//TODO - Inserir evento SUPER USUARIO Inserir SQL
			if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				jInternal_TelaInserirSQL.queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				JInternal_TelaAlerta.showAlerta("Erro ao exibir tela de SQL", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnSobre().addActionListener(ActionEvent->{
			if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado);
			//TODO - Inserir evento Abrir tela Info Projeto
			
		});
		
		telaOpcoes.getBtnSair().addActionListener(controlador_Backup);
	}
	
	public void sair() throws PropertyVetoException {
		
		telaPrincipal.getTelaLoginSistema().getLoginField().setText("");
		telaPrincipal.getTelaLoginSistema().getSenhaField().setText("");
		telaPrincipal.exibirTela(TelaPrincipal.TELA_LOGIN);
		
		JInternalAbstract jIA[] = new JInternalAbstract[16];
		jIA[0] = jInternal_TelaCadastro_Etapa;
		jIA[1] = jInternal_TelaCadastro_Projeto;
		jIA[2] = jInternal_TelaCadastro_Pessoa;
		jIA[3] = jInternal_TelaCadastro_Tarefa;
		
		jIA[4] = jInternal_TelaInfoEtapa;
		jIA[5] = jInternal_TelaInserirSQL;
		jIA[6] = jInternal_TelaInfoPessoa;
		jIA[7] = jInternal_TelaInfoTarefa;
		jIA[8] = jInternal_TelaInfoProjeto_Etapas;
		jIA[9] = jInternal_TelaInfoPessoa_Projetos;
		jIA[10] = jInternal_TelaInfoProjeto_Etapas_Simples;
		jIA[11] = jInternal_TabelaPessoas;
		jIA[12] = jInternal_TabelaPessoasColaboradores;
		jIA[13] = jInternal_TelaBackups;
		jIA[14] = JInternal_TelaAlerta.getInstance();
		jIA[15] = JInternal_Backup_Efetuando.getInstance();
		
		for(JInternalAbstract jAbstract: jIA) {
			jAbstract.setIcon(false);
			jAbstract.setVisible(false);
		}
	}

	public JInternal_TelaCadastro_Etapa getjInternal_TelaCadastro_Etapa() {return jInternal_TelaCadastro_Etapa;}
	public JInternal_TelaCadastro_Tarefa getjInternal_TelaCadastro_Tarefa() {return jInternal_TelaCadastro_Tarefa;}
	public JInternal_TelaCadastro_Pessoa getjInternal_TelaCadastro_Pessoa() {return jInternal_TelaCadastro_Pessoa;}
	public JInternal_TelaCadastro_Projeto getjInternal_TelaCadastro_Projeto() {return jInternal_TelaCadastro_Projeto;}

	public JInternal_TelaInserirSQL getjInternal_TelaInserirSQL() {return jInternal_TelaInserirSQL;}

	public JInternal_TelaInfoEtapa getjInternal_TelaInfoEtapa() {return jInternal_TelaInfoEtapa;}
	public JInternal_TelaInfoPessoa getjInternal_TelaInfoPessoa() {return jInternal_TelaInfoPessoa;}
	public JInternal_TelaInfoTarefa getjInternal_TelaInfoTarefa() {return jInternal_TelaInfoTarefa;}
	public JInternal_TelaInfoProjeto_Etapas getjInternal_TelaInfoProjeto_Etapas() {return jInternal_TelaInfoProjeto_Etapas;}
	public JInternal_TelaInfoPessoa_Projetos getjInternal_TelaInfoPessoa_Projetos() {return jInternal_TelaInfoPessoa_Projetos;}
	public JInternal_TelaInfoProjeto_Etapas_Simples getjInternal_TelaInfoProjeto_Etapas_Simples() {return jInternal_TelaInfoProjeto_Etapas_Simples;}
	
	public JInternal_TelaBackups getjInternal_TelaBackups() {return jInternal_TelaBackups;}
	public JInternal_TabelaPessoas getjInternal_TabelaPessoas() {return jInternal_TabelaPessoas;}
	public JInternal_TabelaPessoasColaboradores getjInternal_TabelaPessoasColaboradores() {return jInternal_TabelaPessoasColaboradores;}

	public TEtapa gettEtapa() {return tEtapa;}
	public TTarefa gettTarefa() {return tTarefa;}
	public TPessoa gettPessoa() {return tPessoa;}
	public TObject gettObject() {return tObject;}
	public TBackup gettBackup() {return tBackup;}
	public TProjeto gettProjeto() {return tProjeto;}
	public TLogUpdate gettLogUpdate() {return tLogUpdate;}
	public TColaborador gettColaborador() {return tColaborador;}
	public TColaboracoes gettColaboracoes() {return tColaboracoes;}
	public TCaracteristicaExtra gettCaracteristicaExtra() {return tCaracteristicaExtra;}
	public TCaracteristicaExtra gettCaracteristicaExtra2() {return tCaracteristicaExtra2;}

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

}

