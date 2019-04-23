package br.com.pbd2019_1.controll;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.CellRenderer;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TLogUpdate;
import br.com.pbd2019_1.tabelas.TPessoa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.JInternalAbstract;
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
import br.com.pbd2019_1.view.PopUp;
import br.com.pbd2019_1.view.TelaCadastro_Tarefa;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaInfoPessoa;
import br.com.pbd2019_1.view.TelaInfoTarefa;
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
	
	private TCaracteristicaExtra tCaracteristicaExtra;
	private TCaracteristicaExtra tCaracteristicaExtra2;
	private TColaborador tColaborador;
	private TColaboracoes tColaboracoes;
	private TEtapa tEtapa;
	private TLogUpdate tLogUpdate;
	private TPessoa tPessoa;
	private TProjeto tProjeto;
	private TTarefa tTarefa;
	
	private static Pessoa pessoa_static;
	private static Pessoa pessoa_outrem_static;
	private static Projeto projeto_static;
	private static Etapa etapa_static;
	private static Tarefa tarefa_static;
	private static Colaborador colaborador_static;
	private static LogUpdate logUpdate_static;
	private static String type_user = "";
	private static boolean bool_colaborador = false;
	
	private static PopUp popUpCaracteristica = new PopUp(new String[]{"Editar", "Excluir"});
	private static PopUp popUp = new PopUp(new String[]{"", "Excluir", ""});
	
	public Controlador_Principal(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
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
			JInternal_TabelaPessoasColaboradores jInternal_TabelaPessoasColaboradores) {
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
		
		
		adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Tarefa);
		adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Etapa);
		adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Projeto);
		adicionarEventoJInternalCadastro(jInternal_TelaCadastro_Pessoa);
		
		adicionarEventoJInternal(jInternal_TelaInfoEtapa);
		adicionarEventoJInternal(jInternal_TelaInfoPessoa);
		adicionarEventoJInternal(jInternal_TelaInfoPessoa_Projetos);
		adicionarEventoJInternal(jInternal_TelaInfoProjeto_Etapas);
		adicionarEventoJInternal(jInternal_TelaInfoProjeto_Etapas_Simples);
		adicionarEventoJInternal(jInternal_TelaInfoTarefa);
		adicionarEventoJInternal(jInternal_TelaInserirSQL);
		
	}
	
	private void adicionarMouseEventJTable(JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO
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
									if(bool_colaborador)
									{
										if(!colaborador_static.getPrivilegio().equals("Visitante"))
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
									}
								} 
								catch (ValidacaoException ve) 
								{
									// TODO Auto-generated catch block
									ve.printStackTrace();
								}
							}
						}
						else if (coluna == 4)
						{
							try 
							{
								tarefa_static = tTarefa.getValor(linha);
								preencherTelaTarefa(jInternal_TelaInfoTarefa.getTelaInfoTarefa(), tarefa_static);
								jInternal_TelaInfoTarefa.queroFoco();
							} catch (PropertyVetoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} 
					else if (table.getModel() instanceof TEtapa)
					{
						if(coluna == 2) 
						{
							//TODO - Abrir tela ver Etapa
							try 
							{
								etapa_static = tEtapa.getValor(linha);
								
								TelaEtapa tE = jInternal_TelaInfoEtapa
										.getTelaEtapa_Tarefas()
										.getTelaEtapa();
								
								atualizarDadoEtapa(tE, etapa_static, tTarefa);
								jInternal_TelaInfoEtapa.queroFoco();
								
							} 
							catch (ValidacaoException e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							catch (PropertyVetoException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (table.getModel() instanceof TProjeto)
					{
						if(coluna == 3)
						{
							//TODO - Abrir Tela ver Projeto
							try
							{
								bool_colaborador = false;
								projeto_static = tProjeto.getValor(linha);
								
								if(!type_user.equals(Pessoa.COMUM_USER))
								{
									TelaProjeto tP = jInternal_TelaInfoProjeto_Etapas
											.getTelaProjeto_Etapas()
											.getTelaProjeto();
									
									atualizarDadoProjeto(tP, projeto_static, tEtapa, tColaborador);
									jInternal_TelaInfoProjeto_Etapas.queroFoco();
								}
								else
								{
									TelaProjeto tP = jInternal_TelaInfoProjeto_Etapas_Simples
											.getTelaProjeto_Etapas_Simples()
											.getTelaProjeto();
									
									atualizarDadoProjetoSimples(tP, projeto_static, tEtapa);
									jInternal_TelaInfoProjeto_Etapas_Simples.queroFoco();
								}
								
								
							} 
							catch (ValidacaoException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							catch (PropertyVetoException e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (table.getModel() instanceof TColaborador)
					{
						//TODO - TColaborador
						if(coluna == 3) 
						{
							try 
							{
								colaborador_static = tColaborador.getValor(linha);
							
								pessoa_outrem_static = (Pessoa) Fachada
										.getInstance()
										.buscar(
												Pessoa.class,
												colaborador_static
												.getPessoa()
												.getId()
												);
								
								TelaInfoPessoa tIP = jInternal_TelaInfoPessoa.getTelaInfoPessoa();
								
								atualizarDadoPessoa(tIP, pessoa_outrem_static, tCaracteristicaExtra2);
								
								jInternal_TelaInfoPessoa.queroFoco();
								
							} 
							catch (ValidacaoException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							catch (PropertyVetoException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}
					else if (table.getModel() instanceof TColaboracoes)
					{
						//TODO - TColaboracoes
						if(coluna == 3)
						{
							try 
							{
								bool_colaborador = true;
								
								colaborador_static = tColaboracoes.getValor(linha);
							
								projeto_static = (Projeto) Fachada.getInstance()
										.buscar(
												Projeto.class,
												colaborador_static
												.getProjeto()
												.getId()
												);
								
								TelaProjeto tP = jInternal_TelaInfoProjeto_Etapas_Simples
										.getTelaProjeto_Etapas_Simples()
										.getTelaProjeto();
							
								atualizarDadoProjetoSimples(tP, projeto_static, tEtapa);
								jInternal_TelaInfoProjeto_Etapas_Simples.queroFoco();
								
							} catch (ValidacaoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (PropertyVetoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if (table.getModel() instanceof TLogUpdate)
					{
						if(coluna == 2)
						{
							//TODO - Abrir tela ver log update
							logUpdate_static = tLogUpdate.getValor(linha);
						}
					}
					else if (table.getModel() instanceof TPessoa)
					{
						if(coluna == 3)
						{
							//TODO - Abrir tela ver Pessoa outrem 
							try
							{
								pessoa_outrem_static = tPessoa.getValor(linha);
								
								TelaInfoPessoa tIP = jInternal_TelaInfoPessoa.getTelaInfoPessoa();
								
								atualizarDadoPessoa(tIP, pessoa_outrem_static, tCaracteristicaExtra2);
								
								jInternal_TelaInfoPessoa.queroFoco();
								
							} 
							catch (ValidacaoException e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							catch (PropertyVetoException e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
	}
	
	
	private void adicionarEventosPopUp() 
	{
		JMenuItem excluir = popUp.getMenuItens()[1];
		
		excluir.addItemListener(ItemEvent->{
			//TODO - 
			try {
				if(bool_colaborador)
					if(!colaborador_static.getPrivilegio().equals("Administrador"))
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
						if(bool_colaborador)
							if(colaborador_static.getPrivilegio().equals("Visitante"))
								throw new ValidacaoException("Não tem permição");
						
						tTarefa.setValueAt(tTarefa.getCombo().getSelectedItem(),
								linha,
								coluna
								);
						Fachada.getInstance().atualizar(tTarefa.getValor(linha));
					} 
					catch (ValidacaoException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	
	public void adicionarEventosTelaPrincipal() {
		
		telaPrincipal.getTelaCadastro_Pessoa()
			.getBtBotao1().addActionListener(ActionEvent->{
				//TODO - inserir aqui o cadastro
				try {
					TelaPessoa telaPessoa = telaPrincipal.getTelaCadastro_Pessoa().getTelaPessoa();
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
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		
		telaPrincipal.getTelaLoginSistema()
			.getBtnLogar().addActionListener(ActionEvent->{
				//TODO - Inserir aqui o login
				
				
				try {
					String login = telaPrincipal
							.getTelaLoginSistema()
							.getLoginField()
							.getTexto();
					String senha = telaPrincipal
							.getTelaLoginSistema()
							.getSenhaField()
							.getTexto();
					
					pessoa_static = Fachada
							.getInstance()
							.getBoPessoa()
							.buscarUsuario(
									login,
									senha
									);
					
					if(pessoa_static != null) 
					{
						type_user = pessoa_static.getUser_type();
						
						TelaInfoPessoa tIP = jInternal_TelaInfoPessoa_Projetos
								.getTelaInfoPessoaProjetos().getTelaInfoPessoa();
						
						atualizarDadoMinhaPessoa(tIP, pessoa_static, tCaracteristicaExtra, tProjeto, tColaboracoes);
						
						if(type_user.equals(Pessoa.COMUM_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
						else if(type_user.equals(Pessoa.ADMIN_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
						else if(type_user.equals(Pessoa.SUPER_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_SUPER);
						
						telaPrincipal.exibirTela(TelaPrincipal.TELA_PRINCIPAL);
					}
					
					
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			try 
			{
				TelaInfoPessoa tIP = jInternal_TelaInfoPessoa_Projetos
						.getTelaInfoPessoaProjetos().getTelaInfoPessoa();
			
				atualizarDadoMinhaPessoa(tIP, pessoa_static, tCaracteristicaExtra, tProjeto, tColaboracoes);
				resetLocation(jInternal_TelaInfoPessoa_Projetos);
				jInternal_TelaInfoPessoa_Projetos.queroFoco();
			} 
			catch (ValidacaoException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		telaOpcoes.getBtnLog().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM de ver os logs dos usuarios
			try
			{
				tLogUpdate.addAll((List<LogUpdate>) Fachada.getInstance().buscarAll(LogUpdate.class));
			} 
			catch (ValidacaoException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		telaOpcoes.getBtnPessoas().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM ver pessoas
			try
			{
				tPessoa.addAll((List<Pessoa>) Fachada.getInstance().buscarAll(Pessoa.class));
				resetLocation(jInternal_TabelaPessoas);
				jInternal_TabelaPessoas.queroFoco();
			} 
			catch (ValidacaoException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		telaOpcoes.getBtnSQL().addActionListener(ActionEvent->{
			//TODO - Inserir evento SUPER USUARIO Inserir SQL
		});
		
		telaOpcoes.getBtnSobre().addActionListener(ActionEvent->{
			//TODO - Inserir evento Abrir tela Info Projeto
		});
		
		telaOpcoes.getBtnSair().addActionListener(ActionEvent->{
			//TODO - Inserir evento deslogar
			telaPrincipal.getTelaLoginSistema().getLoginField().setText("");
			telaPrincipal.getTelaLoginSistema().getSenhaField().setText("");
			telaPrincipal.exibirTela(TelaPrincipal.TELA_LOGIN);
		});
	}
	
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Etapa telaCadastroEtapa) {
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
					etapa.setProjeto(projeto_static);
					Fachada.getInstance().inserir(etapa);
					tEtapa.addValor(etapa);
					telaEtapa.limparCampos();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Pessoa telaCadastroPessoa) {
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

					if(type_user.equals(Pessoa.ADMIN_USER))
						pessoa.setUser_type(Pessoa.COMUM_USER);
					else if(type_user.equals(Pessoa.SUPER_USER))
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
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Projeto telaCadastroProjeto) {
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
					projeto.setPessoa(pessoa_static);
					projeto.setPrivilegio(!(type_user.equals(Pessoa.COMUM_USER)));
					Fachada.getInstance().inserir(projeto);
					tProjeto.addValor(projeto);
					telaProjeto.limparCampos();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
		});
	}
	
	private void adicionarEventoJInternalCadastro(JInternal_TelaCadastro_Tarefa telaCadastroTarefa) {
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

					Fachada.getInstance().inserir(tarefa);
					tTarefa.addValor(tarefa);
					telaCadastro.limparCampos();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
	}

	

	private void adicionarEventoJInternal(JInternal_TelaInfoEtapa telaInfoEtapa) {
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa().getBotao1()
			.addActionListener(ActionEvent->{
			//TODO - Atualizar info Etapa
				
				try {
					
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaEtapa telaEtapa = telaInfoEtapa.getTelaEtapa_Tarefas().getTelaEtapa();
					String nome = telaEtapa.getNomeEtapaField().getTexto();
					String descr = telaEtapa.getDescricaoTextArea().getText();

					etapa_static.setNome(nome);
					etapa_static.setDescricao(descr);
					Fachada.getInstance().atualizar(etapa_static);
					tEtapa.fireTableDataChanged();
				} catch (ValidacaoException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			});
		
		telaInfoEtapa.getTelaEtapa_Tarefas().getTelaTarefas().getBtNovaTarefa()
			.addActionListener(ActionEvent->{
			//TODO - Nova Tarefa
				try {
				
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					resetLocation(jInternal_TelaCadastro_Tarefa);
					jInternal_TelaCadastro_Tarefa.queroFoco();
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				try {

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


					pessoa_outrem_static.setNome(nome);
					pessoa_outrem_static.setCpf(cpf);
					pessoa_outrem_static.setData_nascimento(DateUtil.getDateSQL(data));
					pessoa_outrem_static.setSexo(sexo);
					pessoa_outrem_static.setUser_login(login);
					pessoa_outrem_static.setUser_senha(senha);
					pessoa_outrem_static.setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(pessoa_outrem_static);
					tPessoa.fireTableDataChanged();

					Contato c = pessoa_outrem_static.getContato();

					if(c == null) {
						c = new Contato();
						c.setEmail(email);
						c.setCelular(celular);
						c.setTelefone(telef);
						Fachada.getInstance().inserir(c);
					}else {
						c.setEmail(email);
						c.setCelular(celular);
						c.setTelefone(telef);
						Fachada.getInstance().atualizar(c);
					}

				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				try {
						
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
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

					tarefa_static.setNome(nome);
					tarefa_static.setDescricao(descr);
					tarefa_static.setConcluida(finalizada);
					tarefa_static.setPrioridade(prior);
					tarefa_static.setHorario_tarefa(horario);

					Fachada.getInstance().atualizar(tarefa_static);
					tTarefa.fireTableDataChanged();
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		
		telaInfoTarefa.getTelaInfoTarefa().getChckbxFinalizada()
			.addItemListener(ItemEvent->{
				//TODO - Marcar/Desmarcar como finalizada
				try {
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
				
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
	}
	

	private void adicionarEventoJInternal(JInternal_TelaInfoProjeto_Etapas telaInfoProjetoEtapas) {
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaProjeto().getBotao1()
			.addActionListener(ActionEvent->{
				//TODO - Update Projeto
				try {
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaProjeto telaProjeto = telaInfoProjetoEtapas.getTelaProjeto_Etapas()
							.getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					projeto_static.setNome(nome);
					projeto_static.setDescricao(descr);
					projeto_static.setData_inicio(DateUtil.getDateSQL(dataI));
					projeto_static.setData_fim(DateUtil.getDateSQL(dataF));
					Fachada.getInstance().atualizar(projeto_static);
					tProjeto.fireTableDataChanged();
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			});
		
		telaInfoProjetoEtapas.getTelaProjeto_Etapas().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
				//TODO - Adicionar etapa
				try {
					
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
				
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				try {
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

					pessoa_static.setNome(nome);
					pessoa_static.setCpf(cpf);
					pessoa_static.setData_nascimento(DateUtil.getDateSQL(data));
					pessoa_static.setSexo(sexo);
					pessoa_static.setUser_login(login);
					pessoa_static.setUser_senha(senha);
					pessoa_static.setDisponibilidade(disponivel);
					Fachada.getInstance().atualizar(pessoa_static);
					tPessoa.fireTableDataChanged();

					Contato c = pessoa_static.getContato();

					if(c == null) {
						c = new Contato();
						c.setEmail(email);
						c.setCelular(celular);
						c.setTelefone(telef);
						Fachada.getInstance().inserir(c);
					}else {
						c.setEmail(email);
						c.setCelular(celular);
						c.setTelefone(telef);
						Fachada.getInstance().atualizar(c);
					}
				
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
				try {
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
					
					TelaProjeto telaProjeto = telaInfoProjeto_Etapas_Simples
							.getTelaProjeto_Etapas_Simples().getTelaProjeto();

					String nome = telaProjeto.getNomeProjetoField().getTexto();
					String descr = telaProjeto.getDescricaoTextArea().getText();
					Date dataI = telaProjeto.getDataInicioDateChooser().getDate();
					Date dataF = telaProjeto.getDataFimDateChooser().getDate();

					projeto_static.setNome(nome);
					projeto_static.setDescricao(descr);
					projeto_static.setData_inicio(DateUtil.getDateSQL(dataI));
					projeto_static.setData_fim(DateUtil.getDateSQL(dataF));
					Fachada.getInstance().atualizar(projeto_static);
					tProjeto.fireTableDataChanged();
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	
		telaInfoProjeto_Etapas_Simples.getTelaProjeto_Etapas_Simples().getTelaEtapas()
			.getBtNovaEtapa().addActionListener(ActionEvent->{
			//TODO - Adicionar etapa
				try {
					
					if(bool_colaborador)
						if(colaborador_static.getPrivilegio().equals("Visitante"))
							throw new ValidacaoException("Não tem permição");
				
				} catch (ValidacaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

	}
	
	private void preencherTelaPessoa(TelaPessoa telaPessoa, Pessoa pessoa) 
	{
		telaPessoa.getNomeField().setText(pessoa.getNome());
		telaPessoa.getCampoFormatadoCPF().setText(pessoa.getCpf());
		telaPessoa.getNascimentoDateChooser().setDate(DateUtil.getDate(pessoa.getData_nascimento()));
		telaPessoa.getSexoComboBox().setSelectedItem(pessoa.getSexo());
		telaPessoa.getLoginField().setText(pessoa.getUser_login());
		telaPessoa.getSenhaField().setText(pessoa.getUser_senha());

		telaPessoa.getRdbtnSim().setSelected(pessoa.isDisponibilidade());
		telaPessoa.getRdbtnNo().setSelected(!pessoa.isDisponibilidade());
	}
	
	private void preencherTelaContato(TelaContatoCaracteristica telaContato, Contato contato) 
	{
		telaContato.getEmailField().setText(contato.getEmail());
		telaContato.getCelularField().setText(contato.getCelular());
		telaContato.getTelefoneField().setText(contato.getCelular());
	}
	
	private void preencherTelaProjeto(TelaProjeto telaProjeto, Projeto projeto)
	{
		
		telaProjeto.getNomeProjetoField().setText(projeto.getNome());
		telaProjeto.getDescricaoTextArea().setText(projeto.getDescricao());
		telaProjeto.getDataInicioDateChooser().setDate(DateUtil.getDate(projeto.getData_inicio()));
		telaProjeto.getDataFimDateChooser().setDate(DateUtil.getDate(projeto.getData_fim()));
		
	}
	
	private void preencherTelaEtapa(TelaEtapa telaEtapa, Etapa etapa)
	{
		telaEtapa.getNomeEtapaField().setText(etapa.getNome());
		telaEtapa.getDescricaoTextArea().setText(etapa.getDescricao());
		telaEtapa.getBarraProgressBar().setValue(Math.round(etapa.getPorcentagem_andamento()));
	}
	
	private void preencherTelaTarefa(TelaInfoTarefa telaTarefa, Tarefa tarefa) 
	{
		telaTarefa.getNomeTarefaField().setText(tarefa.getNome());
		telaTarefa.getDescricaoTextArea().setText(tarefa.getDescricao());
		telaTarefa.getChckbxFinalizada().setSelected(tarefa.isConcluida());
		telaTarefa.getPrioridadeComboBox().setSelectedItem(tarefa.getPrioridade());

		String[] horaData = tarefa.getHorario_tarefa().split("'T'");
		
		String[] hora = horaData[1].split(":");
		
		telaTarefa.getHorario().setLocalTime(hora[0], hora[1], hora[2]);
		telaTarefa.getDateChooser().setDate(DateUtil.getDate(horaData[0]));
		
	}
	
	private void atualizarDadoProjetoSimples(TelaProjeto tp, Projeto p, TEtapa te) throws ValidacaoException
	{
		List<Etapa> lEtapa = Fachada.getInstance().getBoEtapa().buscarPorProjeto(p);
		te.addAll(lEtapa);
		p.setEtapas(lEtapa);
		preencherTelaProjeto(tp, p);
	}
	
	private void atualizarDadoProjeto(TelaProjeto tp, Projeto p, TEtapa te, TColaborador tc) throws ValidacaoException
	{
		atualizarDadoProjetoSimples(tp, p, te);
		
		List<Colaborador> lColaborador = Fachada.getInstance().getBoColaborador().buscarPorProjeto(p);
		tc.addAll(lColaborador);
		p.setColaboradores(lColaborador);
	}
	
	private void atualizarDadoPessoa(TelaInfoPessoa tIP, Pessoa p, TCaracteristicaExtra tce) throws ValidacaoException
	{
		preencherTelaPessoa(tIP.getTelaPessoa(),p);
	
		Contato c = Fachada.getInstance().getBoContato().buscarPorPessoa(p);
		
		if(c == null) c = new Contato();
		
		List<CaracteristicaExtra> lc = Fachada.getInstance().getBoCaracteristicaExtra().buscaPorPessoa(p);
		
		preencherTelaContato(tIP.getTelaContatoCaracteristica(), c);
		
		p.setContato(c);
		p.setCaracteristicas(lc);
		
		tce.addAll(lc);
	}
	
	private void atualizarDadoMinhaPessoa(TelaInfoPessoa tIP, Pessoa p, TCaracteristicaExtra tce, TProjeto tP, TColaboracoes tC) throws ValidacaoException
	{
		atualizarDadoPessoa(tIP, p, tce);
		List<Projeto> lp = Fachada.getInstance().getBoProjeto().buscarPorPessoa(p);
		tP.addAll(lp);
		List<Colaborador> lcolaboracoes = Fachada.getInstance().getBoColaborador().buscarPorPessoa(p);
		tC.addAll(lcolaboracoes);
		p.setProjetos(lp);
		p.setColaboradores(lcolaboracoes);
	}
	
	private void atualizarDadoEtapa(TelaEtapa tE, Etapa e, TTarefa tT) throws ValidacaoException
	{
		preencherTelaEtapa(tE,e);
		List<Tarefa> t = Fachada.getInstance().getBoTarefa().buscarPorEtapa(e);
		tT.addAll(t);
		e.setTarefas(t);
	}
	
	private void resetLocation(JInternalAbstract jIF) 
	{
		int largura = telaPrincipal.getjDesktopPane().getWidth();
		int altura = telaPrincipal.getjDesktopPane().getHeight();
		Point p =  new Point(largura/2 - jIF.getWidth()/2, altura/2 - jIF.getHeight()/2);
		jIF.setLocation(p);
	}
}

