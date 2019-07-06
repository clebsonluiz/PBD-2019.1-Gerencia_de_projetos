package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.tabelas.TCaracteristicaExtra;
import br.com.pbd2019_1.tabelas.TColaboracoes;
import br.com.pbd2019_1.tabelas.TColaborador;
import br.com.pbd2019_1.tabelas.TEtapa;
import br.com.pbd2019_1.tabelas.TProjeto;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaGraficoPessoa;
import br.com.pbd2019_1.view.TelaInfoLog;
import br.com.pbd2019_1.view.TelaInfoPessoa;
import br.com.pbd2019_1.view.TelaInfoPessoaContatoOutremSimples;
import br.com.pbd2019_1.view.TelaInfoProjeto;
import br.com.pbd2019_1.view.TelaInfoTarefa;
import br.com.pbd2019_1.view.TelaMiniPessoa1;
import br.com.pbd2019_1.view.TelaMiniPessoa2;
import br.com.pbd2019_1.view.TelaProjeto;

public class Ctrl_PreenchementoTela {

	private Controlador_Principal ctrl_P;

	public Ctrl_PreenchementoTela(Controlador_Principal ctrl_P) {
		this.ctrl_P = ctrl_P;
	}
	
	/*
	 * TODO - A partir daqui � chamada de JInternals para exibi��o
	 */
	public void exibirJInternalInfoTarefa(Tarefa t) throws PropertyVetoException 
	{
			ctrl_P.setTarefa_Atual(t);
			preencherTelaTarefa(ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaInfoTarefa(), ctrl_P.getTarefa_Atual());
			ctrl_P.getjInternal_TelaInfoTarefa().queroFoco();
	}
	
	public void exibirJInternalInfoEtapa(Etapa e) throws PropertyVetoException, ValidacaoException
	{
		ctrl_P.setEtapa_Atual(e);
		
		TelaEtapa tE = ctrl_P.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaEtapa();
		
		atualizarDadoEtapa(tE, ctrl_P.getEtapa_Atual(), ctrl_P.gettTarefa());
		ctrl_P.getjInternal_TelaInfoEtapa().queroFoco();
	}
	
	public void exibirJInternalInfoProjetoEtapa(Projeto p) throws ValidacaoException, PropertyVetoException
	{
		TelaProjeto tP = ctrl_P.getjInternal_TelaInfoProjeto_Etapas()
				.getTelaProjeto_Etapas()
				.getTelaProjeto();
		
		atualizarDadoProjeto(tP,
				ctrl_P.getProjeto_Atual(),
				ctrl_P.gettEtapa(),
				ctrl_P.gettColaborador()
				);
		ctrl_P.getjInternal_TelaInfoProjeto_Etapas().queroFoco();
	}
	
	public void exibirJInternalInfoProjetoEtapaSimples(Projeto p) throws ValidacaoException, PropertyVetoException
	{
		TelaProjeto tP = ctrl_P.getjInternal_TelaInfoProjeto_Etapas_Simples()
				.getTelaProjeto_Etapas_Simples()
				.getTelaProjeto();
		
		atualizarDadoProjetoSimples(tP, ctrl_P.getProjeto_Atual(), ctrl_P.gettEtapa());
		ctrl_P.getjInternal_TelaInfoProjeto_Etapas_Simples().queroFoco();
	}
	
	public void exibirJInternalInfoColaborador(Colaborador c) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setColaborador_Atual(c);
		
		ctrl_P.setPessoa_Outrem((Pessoa) Fachada
				.getInstance()
				.buscar(
						Pessoa.class,
						ctrl_P.getColaborador_Atual()
						.getPessoa()
						.getId()
						));
		
		TelaInfoPessoa tIP = ctrl_P.getjInternal_TelaInfoPessoa().getTelaInfoPessoa();
		
		atualizarDadoPessoa(tIP, ctrl_P.getPessoa_Outrem(), ctrl_P.gettCaracteristicaExtra2());
		ctrl_P.getjInternal_TelaInfoPessoa().queroFoco();
	}
	
	public void exibirJInternalInfoColaboracoes(Colaborador c) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setColaborador_Atual(c);
		
		ctrl_P.setProjeto_Atual((Projeto) Fachada.getInstance()
				.buscar(
						Projeto.class,
						ctrl_P.getColaborador_Atual()
						.getProjeto()
						.getId()
						));
		
		TelaProjeto tP = ctrl_P.getjInternal_TelaInfoProjeto_Etapas_Simples()
				.getTelaProjeto_Etapas_Simples()
				.getTelaProjeto();
	
		atualizarDadoProjetoSimples(tP, ctrl_P.getProjeto_Atual(), ctrl_P.gettEtapa());
		ctrl_P.getjInternal_TelaInfoProjeto_Etapas_Simples().queroFoco();
	}
	
	public void exibirJInternalInfoPessoa(Pessoa p) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setPessoa_Outrem(p);
		
		TelaInfoPessoa tIP = ctrl_P.getjInternal_TelaInfoPessoa().getTelaInfoPessoa();
		
		atualizarDadoPessoa(tIP, ctrl_P.getPessoa_Outrem(), ctrl_P.gettCaracteristicaExtra2());
		ctrl_P.getjInternal_TelaInfoPessoa().queroFoco();
	}

	public void exibirJInternalInfoMinhaPessoa(Pessoa p) throws ValidacaoException, PropertyVetoException
	{
		TelaInfoPessoa tIP = ctrl_P.getjInternal_TelaInfoPessoa_Projetos()
				.getTelaInfoPessoaProjetos().getTelaInfoPessoa();
	
		atualizarDadoMinhaPessoa(tIP,
				ctrl_P.getPessoa_Logada(),
				ctrl_P.gettCaracteristicaExtra(),
				ctrl_P.gettProjeto(),
				ctrl_P.gettColaboracoes()
				);
		ctrl_P.getjInternal_TelaInfoPessoa_Projetos().queroFoco();
	}
	
	public void exibirJInternalInfoPessoaOutremSimples(Pessoa p) throws ValidacaoException, PropertyVetoException
	{
		TelaInfoPessoaContatoOutremSimples tIP = ctrl_P.getjInternal_TelaInfoPessoaOutremSimples().getTelaInfoPessoaContatoOutremSimples();
		
		atualizarDadoPessoaColaboradorSimples(tIP, p, ctrl_P.gettCaracteristicaExtra());
		
		ctrl_P.getjInternal_TelaInfoPessoaOutremSimples().queroFoco();
	}
	
	public void exibirJInternalTabelaLog(LogUpdate log) throws PropertyVetoException
	{
		preencherTelaLog(ctrl_P.getjInternal_InfoLog().getTelaInfoLog(), log);
		
		ctrl_P.getjInternal_InfoLog().queroFoco();
	}
	
	/*
	 * TODO - A partir daqui � s� preenchimento de Tela
	 */
	
	private void preencherTelaLog(TelaInfoLog telaLog, LogUpdate log)
	{
		String id_tabela = ""+log.getId_tabela();
		String tabela = log.getTabela();
		String tipo = log.getTipo();
		String cpf = log.getResponsavel();
		
		String data = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(log.getData_log());
		
		List<String> antes = log.getAntes();
		List<String> depois = log.getDepois();
		List<String> colunas = log.getColuna();
		
		JTable table = telaLog.getTable();
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		int index = tableModel.getRowCount();
		
		System.out.println(index);
		
		for(int i = 0; i < index; i++)
		{
			System.out.println(i);
			tableModel.removeRow(0);
			tableModel.fireTableRowsDeleted(0, 0);
			tableModel.fireTableDataChanged();
		}
		
		String[] rowData;
		
		int indexMaximum = 0;
		
		if(tipo.equals("CADASTRO"))
		{
			indexMaximum = (depois != null)? depois.size() : 0;
		}
		else 
		{
			indexMaximum = (antes != null)? antes.size() :
				(depois != null)? depois.size() : 0;
		}
		
		for(int i = 0; i < indexMaximum; i ++)
		{
			rowData = new String[] {
					(colunas != null)? ((depois.size() == indexMaximum)? colunas.get(i) : "") : "",
					(antes != null)? ((antes.size() == indexMaximum)? antes.get(i) : "") : "",
					(depois != null)? ((depois.size() == indexMaximum)? depois.get(i) : "") : "",
			};
			
			tableModel.addRow(rowData);
			tableModel.fireTableDataChanged();
		}
		
		telaLog.getCmptxtCod().setTexto(id_tabela);
		telaLog.getCmptxtTabela().setTexto(tabela);
		telaLog.getCmptxtTipo().setTexto(tipo);
		telaLog.getCmptxtResponsavel().setTexto(cpf);
		telaLog.getCmptxtDatalog().setTexto(data);
		
		
	}
	
	private void preencherTelaMiniPessoa1(TelaMiniPessoa1 telaPessoa, Pessoa pessoa) 
	{
		telaPessoa.getNomeField().setDescricao("Nome");
		telaPessoa.getNomeField().setTexto(pessoa.getNome());
		telaPessoa.getCampoFormatadoCPF().setText(pessoa.getCpf());
		telaPessoa.getNascimentoDateChooser().setDate(DateUtil.parseToDate(pessoa.getData_nascimento()));
		telaPessoa.getSexoComboBox().setSelectedItem(pessoa.getSexo());
		
		telaPessoa.getRdbtnSim().setSelected(pessoa.isDisponibilidade());
		telaPessoa.getRdbtnNo().setSelected(!pessoa.isDisponibilidade());
	}
	
	private void preencherTelaMiniPessoa2(TelaMiniPessoa2 telaPessoa, Pessoa pessoa) 
	{
		/*telaPessoa.getLoginField().setTexto(pessoa.getUser_login());
		telaPessoa.getSenhaField().setText("");
		telaPessoa.getSenhaField().setDescricao("Nova Senha");*/
		
	}
	
	private void preencherTelaContato(TelaContatoCaracteristica telaContato, Contato contato) 
	{
		telaContato.getEmailField().setText(contato.getEmail());
		telaContato.getCelularField().setText(contato.getCelular());
		telaContato.getTelefoneField().setText(contato.getTelefone());
	}
	
	private void preencherTelaProjeto(TelaProjeto telaProjeto, Projeto projeto) throws ValidacaoException
	{
		
		telaProjeto.getNomeProjetoField().setDescricao("Nome do Projeto");
		telaProjeto.getNomeProjetoField().setTexto(projeto.getNome());
		telaProjeto.getDescricaoTextArea().setText(projeto.getDescricao());
		telaProjeto.getDataInicioDateChooser().setDate(DateUtil.getDate(projeto.getData_inicio()));
		telaProjeto.getDataFimDateChooser().setDate(DateUtil.getDate(projeto.getData_fim()));
		
		int valorA = Fachada.getInstance()
				.getBoProjeto()
				.andamento_Projeto(
						projeto
						);
		
		((TelaInfoProjeto)telaProjeto).getProgressBar().setValue(valorA);
	}
	
	private void preencherTelaEtapa(TelaEtapa telaEtapa, Etapa etapa)
	{
		telaEtapa.getNomeEtapaField().setDescricao("Nome da Etapa");
		telaEtapa.getNomeEtapaField().setTexto(etapa.getNome());
		telaEtapa.getDescricaoTextArea().setText(etapa.getDescricao());
		telaEtapa.getBarraProgressBar().setValue(Math.round(etapa.getPorcentagem()));
	}
	
	private void preencherTelaTarefa(TelaInfoTarefa telaTarefa, Tarefa tarefa) 
	{
		telaTarefa.getNomeTarefaField().setDescricao("Nome da Tarefa");
		telaTarefa.getNomeTarefaField().setTexto(tarefa.getNome());
		telaTarefa.getDescricaoTextArea().setText(tarefa.getDescricao());
		telaTarefa.getChckbxFinalizada().setSelected(tarefa.isConcluida());
		telaTarefa.getPrioridadeComboBox().setSelectedItem(tarefa.getPrioridade());

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
		
		String data = format1.format(tarefa.getHorario());
		String horario = format2.format(tarefa.getHorario());
		
		System.out.println("Horario "+ tarefa.getHorario());
		
		String[] hora = horario.split(":");
		
		if(hora.length < 3)
			hora = new String[]{hora[0], hora[1], "00"};
		
		telaTarefa.getHorario().setLocalTime(hora[0], hora[1], hora[2]);
		telaTarefa.getDateChooser().setDate(DateUtil.getDate(data));
		
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
	
	private void atualizarDadoPessoaDesempenho(TelaGraficoPessoa tgp, Pessoa pessoa) throws ValidacaoException
	{
		int[] etapas = Fachada.getInstance().getBoPessoa().buscarDesempenhoEtapas(pessoa);
		int[] tarefas = Fachada.getInstance().getBoPessoa().buscarDesempenhoTarefas(pessoa);
		tgp.atualizarGrafico(tarefas, etapas);
	}
	
	private void atualizarDadoPessoa(TelaInfoPessoa tIP, Pessoa p, TCaracteristicaExtra tce) throws ValidacaoException
	{
		preencherTelaMiniPessoa1(tIP.getTelaMiniPessoa1(), p);
		preencherTelaMiniPessoa2(tIP.getTelaMiniPessoa2(), p);
		
		
		Contato c = Fachada.getInstance().getBoContato().buscarPorPessoa(p);
		
		if(c == null) c = new Contato();
		
		List<CaracteristicaExtra> lc = Fachada.getInstance().getBoCaracteristicaExtra().buscaPorPessoa(p);
		
		preencherTelaContato(tIP.getTelaContatoCaracteristica(), c);
		atualizarDadoPessoaDesempenho(tIP.getTelaGraficoPessoa(), p);
		
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
	
	private void atualizarDadoPessoaColaboradorSimples(TelaInfoPessoaContatoOutremSimples tIP, Pessoa p, TCaracteristicaExtra tce) throws ValidacaoException
	{
		preencherTelaMiniPessoa1(tIP.getTelaMiniPessoa1(), p);
		
		Contato c = Fachada.getInstance().getBoContato().buscarPorPessoa(p);
		
		if(c == null) c = new Contato();
		
		List<CaracteristicaExtra> lc = Fachada.getInstance().getBoCaracteristicaExtra().buscaPorPessoa(p);
		
		preencherTelaContato(tIP.getTelaContatoCaracteristica(), c);
		atualizarDadoPessoaDesempenho(tIP.getTelaGraficoPessoa(), p);
		
		p.setContato(c);
		p.setCaracteristicas(lc);
		
		tce.addAll(lc);
	}
}
