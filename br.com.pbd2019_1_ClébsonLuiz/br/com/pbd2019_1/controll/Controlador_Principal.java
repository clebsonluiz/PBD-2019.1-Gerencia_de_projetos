package br.com.pbd2019_1.controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
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
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaInfoPessoa;
import br.com.pbd2019_1.view.TelaInfoTarefa;
import br.com.pbd2019_1.view.TelaInserirSQL;
import br.com.pbd2019_1.view.TelaMenu;
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
	
	
	private Controlador_Preenche_Tela preenche_Tela;
	private Controlador_Cadastro controlador_Cadastro;
	private Controlador_Backup controlador_Backup;

	
	private static PopUp popUpCaracteristica = new PopUp(new String[]{"Salvar", "Excluir"});
	private static PopUp popUp = new PopUp(new String[]{"", "Excluir", ""});
	
	public Controlador_Principal(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		this.preenche_Tela = new Controlador_Preenche_Tela();
		this.controlador_Cadastro = new Controlador_Cadastro();
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
		
		adicionarEventoJInternal(jInternal_TelaInfoEtapa);
		adicionarEventoJInternal(jInternal_TelaInfoPessoa);
		adicionarEventoJInternal(jInternal_TelaInfoPessoa_Projetos);
		adicionarEventoJInternal(jInternal_TelaInfoProjeto_Etapas);
		adicionarEventoJInternal(jInternal_TelaInfoProjeto_Etapas_Simples);
		adicionarEventoJInternal(jInternal_TelaInfoTarefa);
		adicionarEventoJInternal(jInternal_TelaInserirSQL);
		
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
									if(Controlador_Statics.bool_colaborador)
									{
										if(!Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
										{
											obj = !((Boolean)obj).booleanValue();
											tTarefa.setValueAt(obj, linha, coluna);
											Fachada.getInstance().atualizar(tTarefa.getValor(linha));
										}
									}
									else 
									{
										obj = !((Boolean)obj).booleanValue();
										tTarefa.setValueAt(obj, linha, coluna);
										Fachada.getInstance().atualizar(tTarefa.getValor(linha));
										
										Controlador_Statics.etapa_static.setPorcentagem_andamento(
												Fachada.getInstance()
													.getBoEtapa()
													.recalcularPorcentagem(Controlador_Statics.etapa_static)
												);
										
										jInternal_TelaInfoEtapa
											.getTelaEtapa_Tarefas()
											.getTelaEtapa()
											.getBarraProgressBar()
											.setValue(
													Math.round(Controlador_Statics.etapa_static.getPorcentagem_andamento())
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
								Controlador_Statics.tarefa_static = tTarefa.getValor(linha);
								preenche_Tela.preencherTelaTarefa(jInternal_TelaInfoTarefa.getTelaInfoTarefa(), Controlador_Statics.tarefa_static);
								jInternal_TelaInfoTarefa.queroFoco();
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
								Controlador_Statics.etapa_static = tEtapa.getValor(linha);
								
								TelaEtapa tE = jInternal_TelaInfoEtapa
										.getTelaEtapa_Tarefas()
										.getTelaEtapa();
								
								preenche_Tela.atualizarDadoEtapa(tE, Controlador_Statics.etapa_static, tTarefa);
								jInternal_TelaInfoEtapa.queroFoco();
								
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
								Controlador_Statics.bool_colaborador = false;
								Controlador_Statics.projeto_static = tProjeto.getValor(linha);
								
								if(!Controlador_Statics.type_user.equals(Pessoa.COMUM_USER))
								{
									TelaProjeto tP = jInternal_TelaInfoProjeto_Etapas
											.getTelaProjeto_Etapas()
											.getTelaProjeto();
									
									preenche_Tela.atualizarDadoProjeto(tP, Controlador_Statics.projeto_static, tEtapa, tColaborador);
									jInternal_TelaInfoProjeto_Etapas.queroFoco();
								}
								else
								{
									TelaProjeto tP = jInternal_TelaInfoProjeto_Etapas_Simples
											.getTelaProjeto_Etapas_Simples()
											.getTelaProjeto();
									
									preenche_Tela.atualizarDadoProjetoSimples(tP, Controlador_Statics.projeto_static, tEtapa);
									jInternal_TelaInfoProjeto_Etapas_Simples.queroFoco();
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
								Controlador_Statics.colaborador_static = tColaborador.getValor(linha);
							
								Controlador_Statics.pessoa_outrem_static = (Pessoa) Fachada
										.getInstance()
										.buscar(
												Pessoa.class,
												Controlador_Statics.colaborador_static
												.getPessoa()
												.getId()
												);
								
								TelaInfoPessoa tIP = jInternal_TelaInfoPessoa.getTelaInfoPessoa();
								
								preenche_Tela.atualizarDadoPessoa(tIP, Controlador_Statics.pessoa_outrem_static, tCaracteristicaExtra2);
								jInternal_TelaInfoPessoa.queroFoco();
								
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
								Controlador_Statics.bool_colaborador = true;
								
								Controlador_Statics.colaborador_static = tColaboracoes.getValor(linha);
							
								Controlador_Statics.projeto_static = (Projeto) Fachada.getInstance()
										.buscar(
												Projeto.class,
												Controlador_Statics.colaborador_static
												.getProjeto()
												.getId()
												);
								
								TelaProjeto tP = jInternal_TelaInfoProjeto_Etapas_Simples
										.getTelaProjeto_Etapas_Simples()
										.getTelaProjeto();
							
								preenche_Tela.atualizarDadoProjetoSimples(tP, Controlador_Statics.projeto_static, tEtapa);
								jInternal_TelaInfoProjeto_Etapas_Simples.queroFoco();
								
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
							Controlador_Statics.logUpdate_static = tLogUpdate.getValor(linha);
						}
					}
					else if (table.getModel() instanceof TPessoa)
					{
						if(coluna == 3)
						{
							//TODO - Abrir tela ver Pessoa outrem 
							try
							{
								Controlador_Statics.pessoa_outrem_static = tPessoa.getValor(linha);
								
								TelaInfoPessoa tIP = jInternal_TelaInfoPessoa.getTelaInfoPessoa();
								
								preenche_Tela.atualizarDadoPessoa(tIP, Controlador_Statics.pessoa_outrem_static, tCaracteristicaExtra2);
								jInternal_TelaInfoPessoa.queroFoco();
								
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
				if(Controlador_Statics.bool_colaborador)
					if(!Controlador_Statics.colaborador_static.getPrivilegio().equals("Administrador"))
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
				if(Controlador_Statics.bool_colaborador)
					if(!Controlador_Statics.colaborador_static.getPrivilegio().equals("Administrador"))
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
				if(Controlador_Statics.bool_colaborador)
					if(!Controlador_Statics.colaborador_static.getPrivilegio().equals("Administrador"))
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
						if(Controlador_Statics.bool_colaborador)
							if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
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
					
					Controlador_Statics.pessoa_static = Fachada
							.getInstance()
							.getBoPessoa()
							.buscarUsuario(
									login,
									senha
									);
					
					if(Controlador_Statics.pessoa_static != null) 
					{
						Controlador_Statics.type_user = Controlador_Statics.pessoa_static.getUser_type();
						
						TelaInfoPessoa tIP = jInternal_TelaInfoPessoa_Projetos
								.getTelaInfoPessoaProjetos().getTelaInfoPessoa();
						
						preenche_Tela.atualizarDadoMinhaPessoa(tIP, Controlador_Statics.pessoa_static, tCaracteristicaExtra, tProjeto, tColaboracoes);
						
						if(Controlador_Statics.type_user.equals(Pessoa.COMUM_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
						else if(Controlador_Statics.type_user.equals(Pessoa.ADMIN_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
						else if(Controlador_Statics.type_user.equals(Pessoa.SUPER_USER))
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
			if(!JInternal_TelaAlerta.isAtivado || !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				TelaInfoPessoa tIP = jInternal_TelaInfoPessoa_Projetos
						.getTelaInfoPessoaProjetos().getTelaInfoPessoa();
			
				preenche_Tela.atualizarDadoMinhaPessoa(tIP, Controlador_Statics.pessoa_static, tCaracteristicaExtra, tProjeto, tColaboracoes);
				jInternal_TelaInfoPessoa_Projetos.queroFoco();
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
			if(!JInternal_TelaAlerta.isAtivado || !JInternal_Backup_Efetuando.isAtivado)
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
			if(!JInternal_TelaAlerta.isAtivado || !JInternal_Backup_Efetuando.isAtivado)
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
			if(!JInternal_TelaAlerta.isAtivado || !JInternal_Backup_Efetuando.isAtivado)
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
			if(!JInternal_TelaAlerta.isAtivado || !JInternal_Backup_Efetuando.isAtivado)
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
			if(!JInternal_TelaAlerta.isAtivado || !JInternal_Backup_Efetuando.isAtivado);
			//TODO - Inserir evento Abrir tela Info Projeto
			
		});
		
		telaOpcoes.getBtnSair().addActionListener(controlador_Backup);
	}
	
	

	private void adicionarEventoJInternal(JInternal_TelaInfoEtapa telaInfoEtapa) {
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getBotao1()
			.addActionListener(ActionEvent->{
			//TODO - Atualizar info Etapa
				
				try 
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaEtapa telaEtapa = telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa();
					String nome = telaEtapa.getNomeEtapaField().getTexto();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					Controlador_Statics.etapa_static.setNome(nome);
					Controlador_Statics.etapa_static.setDescricao(descr);
					Fachada.getInstance().atualizar(Controlador_Statics.etapa_static);
					tEtapa.fireTableDataChanged();
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Atualizar Etapa", e.getMessage());
				}
				
				
			});
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaTarefas().getBtNovaTarefa()
			.addActionListener(ActionEvent->{
			//TODO - Nova Tarefa
				try 
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					jInternal_TelaCadastro_Tarefa.queroFoco();
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Não Tem permição", e.getMessage());
				} 
				catch (PropertyVetoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir ", e.getMessage());
				}
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInserirSQL jInternal_TelaInserirSQL) {
		
		TelaInserirSQL telaInserirSQL = jInternal_TelaInserirSQL.getTelaInserirSQL();
		
		telaInserirSQL.getBtnInserir()
		.addActionListener(ActionEvent->{
			//TODO - Inserir SQL
			try {
				String sql = telaInserirSQL.getTextArea().getSelectedText();
			
				if(sql == null)
					sql = telaInserirSQL.getTextArea().getText();
			
				tObject.addAll(Fachada.getInstance().inserirSQLGenerica(sql));
				
				telaInserirSQL.getExceptionTextArea().setText("");
			} catch (ValidacaoException e) {
				telaInserirSQL.getExceptionTextArea().setText(e.getMessage());
			}
			
		});

		telaInserirSQL.getBtnLimpar()
		.addActionListener(ActionEvent->{
			//TODO - Botao Limpar
			telaInserirSQL.getTextArea().setText("");
		});

		telaInserirSQL.getBtnSalvar()
		.addActionListener(ActionEvent->{
			//TODO - Botao Salvar
		});

		telaInserirSQL.getBtnAbrir()
		.addActionListener(ActionEvent->{
			//TODO - Botao abrir
		});
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa telaInfoPessoa) {
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaPessoa().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa 
				try 
				{
					TelaPessoa telaPessoa = telaInfoPessoa.getTelaInfoPessoa().getTelaPessoa();
					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					String login = telaPessoa.getLoginField().getTexto();
					String senha = telaPessoa.getSenhaField().getTexto();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					Controlador_Statics.pessoa_outrem_static.setNome(nome);
					Controlador_Statics.pessoa_outrem_static.setCpf(cpf);
					Controlador_Statics.pessoa_outrem_static.setData_nascimento(DateUtil.getDateSQL(data));
					Controlador_Statics.pessoa_outrem_static.setSexo(sexo);
					Controlador_Statics.pessoa_outrem_static.setUser_login(login);
					Controlador_Statics.pessoa_outrem_static.setUser_senha(senha);
					Controlador_Statics.pessoa_outrem_static.setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(Controlador_Statics.pessoa_outrem_static);
					tPessoa.fireTableDataChanged();

				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar dados", e.getMessage());
				}
				
				
				
			});
		
		telaInfoPessoa.getTelaInfoPessoa().getTelaContatoCaracteristica().getBotao()
		.addActionListener(ActionEvent->{
			//TODO - Add Contato
			try 
			{
				TelaContatoCaracteristica telaContato = telaInfoPessoa.getTelaInfoPessoa()
						.getTelaContatoCaracteristica();

				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();

				Contato c = Controlador_Statics.pessoa_outrem_static.getContato();

				if(c == null) 
					c = new Contato();
				if(c.getId() <= 0)
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					c.setPessoa(Controlador_Statics.pessoa_outrem_static);
					Fachada.getInstance().inserir(c);
				}
				else 
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
				}
			} 
			catch (ValidacaoException e)
			{
				JInternal_TelaAlerta.showAlerta("Erro ao atualizar dados", e.getMessage());
			}
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
				try 
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa();
					
					String nome = telaTarefa.getNomeTarefaField().getTexto();
					String descr = telaTarefa.getDescricaoTextArea().getText();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					String prior = (String) telaTarefa.getPrioridadeComboBox().getSelectedItem();

					LocalTime time = telaTarefa.getHorario().getLocalTime();
					Date date = telaTarefa.getDateChooser().getDate();

					LocalDateTime localDateTime = DateUtil.getDateTime(DateUtil.parseToLocalDate(date), time);

					String horario = localDateTime.toString();

					Controlador_Statics.tarefa_static.setNome(nome);
					Controlador_Statics.tarefa_static.setDescricao(descr);
					Controlador_Statics.tarefa_static.setConcluida(finalizada);
					Controlador_Statics.tarefa_static.setPrioridade(prior);
					Controlador_Statics.tarefa_static.setHorario_tarefa(horario);

					Fachada.getInstance().atualizar(Controlador_Statics.tarefa_static);
					tTarefa.fireTableDataChanged();
				} catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar tarefa", e.getMessage());
				}
				
			});
		
		telaInfoTarefa.getTelaInfoTarefa().getChckbxFinalizada()
			.addItemListener(ItemEvent->{
				//TODO - Marcar/Desmarcar como finalizada
				try
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaInfoTarefa telaTarefa = telaInfoTarefa.getTelaInfoTarefa();
					boolean finalizada = telaTarefa.getChckbxFinalizada().isSelected();
					Controlador_Statics.tarefa_static.setConcluida(finalizada);
					Fachada.getInstance().atualizar(Controlador_Statics.tarefa_static);
					tTarefa.fireTableDataChanged();
					
					
					Controlador_Statics.etapa_static.setPorcentagem_andamento(
							Fachada.getInstance()
								.getBoEtapa()
								.recalcularPorcentagem(Controlador_Statics.etapa_static)
							);
					
					jInternal_TelaInfoEtapa
						.getTelaEtapa_Tarefas()
						.getTelaEtapa()
						.getBarraProgressBar()
						.setValue(
								Math.round(Controlador_Statics.etapa_static.getPorcentagem_andamento())
								);
				} catch (ValidacaoException e) {
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar tarefa", e.getMessage());
				}
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoProjeto_Etapas telaInfoProjetoEtapas) {
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaProjeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update Projeto
				try 
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaProjeto telaProjeto = telaInfoProjetoEtapas.getTelaProjeto_Etapas()
							.getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					Controlador_Statics.projeto_static.setNome(nome);
					Controlador_Statics.projeto_static.setDescricao(descr);
					Controlador_Statics.projeto_static.setData_inicio(DateUtil.getDateSQL(dataI));
					Controlador_Statics.projeto_static.setData_fim(DateUtil.getDateSQL(dataF));
					Fachada.getInstance().atualizar(Controlador_Statics.projeto_static);
					tProjeto.fireTableDataChanged();
				}
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar info. projeto", e.getMessage());
				}
				
				
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
				//TODO - Adicionar etapa
				try
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					jInternal_TelaCadastro_Etapa.queroFoco();
					
				} 
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				catch (PropertyVetoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaColaboradores()
			.getBtAdicionarColaborador().addActionListener(ActionEvent->{
				//TODO - Adicionar Colaborador
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoPessoa_Projetos telaInfoPessoaProjetos) {
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getTelaPessoa().getBotao()
			.addActionListener(ActionEvent->{
				//TODO - Update Pessoa
				try 
				{
					TelaPessoa telaPessoa = telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
							.getTelaInfoPessoa().getTelaPessoa();

					String nome = telaPessoa.getNomeField().getTexto();
					String cpf = telaPessoa.getCampoFormatadoCPF().getText();
					Date data = telaPessoa.getNascimentoDateChooser().getDate();
					String sexo = (String) telaPessoa.getSexoComboBox().getSelectedItem();
					String login = telaPessoa.getLoginField().getTexto();
					String senha = telaPessoa.getSenhaField().getTexto();
					boolean disponivel = telaPessoa.getRdbtnSim().isSelected();

					Controlador_Statics.pessoa_static.setNome(nome);
					Controlador_Statics.pessoa_static.setCpf(cpf);
					Controlador_Statics.pessoa_static.setData_nascimento(DateUtil.getDateSQL(data));
					Controlador_Statics.pessoa_static.setSexo(sexo);
					Controlador_Statics.pessoa_static.setUser_login(login);
					Controlador_Statics.pessoa_static.setUser_senha(senha);
					Controlador_Statics.pessoa_static.setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(Controlador_Statics.pessoa_static);
					tPessoa.fireTableDataChanged();

				}
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Atualizar Info de Pessoas ", e.getMessage());
				}
				
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos().getTelaInfoPessoa().getTelaContatoCaracteristica().getBotao()
		.addActionListener(ActionEvent->{
			//TODO - COntato
			try 
			{

				TelaContatoCaracteristica telaContato = telaInfoPessoaProjetos
						.getTelaInfoPessoaProjetos()
						.getTelaInfoPessoa()
						.getTelaContatoCaracteristica();

				String email = telaContato.getEmailField().getTexto();
				String celular = telaContato.getCelularField().getText();
				String telef = telaContato.getTelefoneField().getText();


				Contato c = Controlador_Statics.pessoa_static.getContato();

				if(c == null) 
					c = new Contato();
				if(c.getId() <= 0)
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					c.setPessoa(Controlador_Statics.pessoa_static);
					Fachada.getInstance().inserir(c);
				}
				else 
				{
					c.setEmail(email);
					c.setCelular(celular);
					c.setTelefone(telef);
					Fachada.getInstance().atualizar(c);
				}
			} 
			catch (ValidacaoException e)
			{
				JInternal_TelaAlerta.showAlerta("Erro ao atualizar dados", e.getMessage());
			}
		});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaInfoPessoa().getTelaContatoCaracteristica().getBtAdicionar()
			.addActionListener(ActionEvent->{
				//TODO - Adicionar caracteristica Extra
				try 
				{
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					CaracteristicaExtra c = new CaracteristicaExtra();
					c.setPessoa(Controlador_Statics.pessoa_static);
					c.setNome("");
				
					Fachada.getInstance().inserir(c);
					
					tCaracteristicaExtra.addValor(c);
					
					/*LogUpdate log = new LogUpdate();
					
					Fachada.getInstance().gerarLogInsercao(c, Controlador_Statics.pessoa_static, log);
					tLogUpdate.addValor(log);*/
				} 
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao Adicionar Caracteristica", e.getMessage());
				}
			});
		
		telaInfoPessoaProjetos.getTelaInfoPessoaProjetos()
			.getTelaProjetos().getBtCriarUmNovo()
			.addActionListener(ActionEvent->{
				//TODO - Cadastrar Novo projeto
				try
				{
					jInternal_TelaCadastro_Projeto.queroFoco();
				}
				catch (PropertyVetoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				
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
				try {
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaProjeto telaProjeto = telaInfoProjeto_Etapas_Simples
							.getTelaProjeto_Etapas_Simples().getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					Controlador_Statics.projeto_static.setNome(nome);
					Controlador_Statics.projeto_static.setDescricao(descr);
					Controlador_Statics.projeto_static.setData_inicio(DateUtil.getDateSQL(dataI));
					Controlador_Statics.projeto_static.setData_fim(DateUtil.getDateSQL(dataF));
					Fachada.getInstance().atualizar(Controlador_Statics.projeto_static);
					tProjeto.fireTableDataChanged();
				}
				catch (ValidacaoException e) 
				{
					JInternal_TelaAlerta.showAlerta("Erro ao atualizar info. projeto", e.getMessage());
				}
			});
	
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
			//TODO - Adicionar etapa
				try {
					
					if(Controlador_Statics.bool_colaborador)
						if(Controlador_Statics.colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					jInternal_TelaCadastro_Etapa.queroFoco();
				} 
				catch (ValidacaoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
				catch (PropertyVetoException e)
				{
					JInternal_TelaAlerta.showAlerta("Erro ao exibir Tela de Cadastro", e.getMessage());
				}
			});

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
}

