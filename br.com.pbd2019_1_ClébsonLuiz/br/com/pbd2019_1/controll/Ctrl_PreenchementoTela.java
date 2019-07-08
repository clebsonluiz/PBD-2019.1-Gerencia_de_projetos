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
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
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
import br.com.pbd2019_1.tabelas.TSubTarefa;
import br.com.pbd2019_1.tabelas.TTarefa;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.TelaColaboradorEnvolvido;
import br.com.pbd2019_1.view.TelaContatoCaracteristica;
import br.com.pbd2019_1.view.TelaEtapa;
import br.com.pbd2019_1.view.TelaGraficoPessoa;
import br.com.pbd2019_1.view.TelaInfoLog;
import br.com.pbd2019_1.view.TelaInfoPessoa;
import br.com.pbd2019_1.view.TelaInfoPessoaContatoOutrem;
import br.com.pbd2019_1.view.TelaInfoPessoaContatoOutremSimples;
import br.com.pbd2019_1.view.TelaInfoProjeto;
import br.com.pbd2019_1.view.TelaInfoSubEtapa;
import br.com.pbd2019_1.view.TelaInfoSubTarefa;
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
	 * TODO - A partir daqui é chamada de JInternals para exibição
	 */
	
	/**TODO - Visão do Colab*/
	public void exibirJInt_InfoSubTarefaColab(SubTarefa t) throws PropertyVetoException 
	{
			ctrl_P.setSubTarefa_Atual_Colab(t);
			preencherTelaSubTarefa(
					ctrl_P.getJif_Inf_SbTarf_Colab().getTelaInfoSubTarefa(),
					ctrl_P.getSubTarefa_Atual_Colab()
					);
			ctrl_P.getJif_Inf_SbTarf_Colab().queroFoco();
	}
	
	public void exibirJInt_InfoSubEtapaColab(SubEtapa e) throws PropertyVetoException, ValidacaoException
	{
		ctrl_P.setSubEtapa_Atual_Colab(e);
		
		TelaInfoSubEtapa tE = ctrl_P.getJif_Inf_SbEtp_Colab().getTelaInfoSubEtapaSubTarefas().getTelaInfoSubEtapa();
		atualizarDadoSubEtapa(
				tE,
				ctrl_P.getSubEtapa_Atual_Colab(),
				ctrl_P.gettSubTarefaColab()
				);
		atualizarDadoColaborador(tE.getTelaColaboradorEnvolvido(), e, true);
		
		ctrl_P.getJif_Inf_SbEtp_Colab().queroFoco();
	}
	
	public void exibirJInt_InfoTarefaColab(Tarefa t) throws PropertyVetoException, ValidacaoException 
	{
			ctrl_P.setTarefa_Atual_Colab(t);
			preencherTelaTarefa(ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaInfoTarefa(), ctrl_P.getTarefa_Atual());
			atualizarDadoColaborador(ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaColaboradorEnvolvido(), t, true);
			
			ctrl_P.getjInternal_TelaInfoTarefa().queroFoco();
	}
	
	public void exibirJInt_InfoEtapaColab(Etapa e) throws PropertyVetoException, ValidacaoException
	{
		ctrl_P.setEtapa_Atual_Colab(e);
		
		TelaEtapa tE = ctrl_P.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaEtapa();
		
		atualizarDadoEtapa(tE, ctrl_P.getEtapa_Atual(), ctrl_P.gettTarefa());
		
		atualizarDadoColaborador(tE.getTelaColaboradorEnvolvido(), e, true);
		
		ctrl_P.getjInternal_TelaInfoEtapa().queroFoco();
	}
	
	public void exibirJInt_InfoProjetoColab(Projeto p) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setProjeto_Atual_Colab(p);
		
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
	
	/**TODO - Visão do Colab*/
	public void exibirJInternalInfoSubTarefa(SubTarefa t) throws PropertyVetoException 
	{
			ctrl_P.setSubTarefa_Atual(t);
			preencherTelaTarefa(ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaInfoTarefa(), ctrl_P.getTarefa_Atual());
			ctrl_P.getjInternal_TelaInfoTarefa().queroFoco();
	}
	
	public void exibirJInternalInfoSubEtapa(SubEtapa e) throws PropertyVetoException, ValidacaoException
	{
		ctrl_P.setSubEtapa_Atual(e);
		
		TelaEtapa tE = ctrl_P.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaEtapa();
		
		atualizarDadoEtapa(tE, ctrl_P.getEtapa_Atual(), ctrl_P.gettTarefa());
		
		atualizarDadoColaborador(tE.getTelaColaboradorEnvolvido(), e, false);
		
		ctrl_P.getjInternal_TelaInfoEtapa().queroFoco();
	}
	
	public void exibirJInternalInfoTarefa(Tarefa t) throws PropertyVetoException, ValidacaoException 
	{
			ctrl_P.setTarefa_Atual(t);
			preencherTelaTarefa(ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaInfoTarefa(), ctrl_P.getTarefa_Atual());
			
			atualizarDadoColaborador(ctrl_P.getjInternal_TelaInfoTarefa().getTelaInfoTarefa().getTelaColaboradorEnvolvido(), t, false);
			
			ctrl_P.getjInternal_TelaInfoTarefa().queroFoco();
	}
	
	public void exibirJInternalInfoEtapa(Etapa e) throws PropertyVetoException, ValidacaoException
	{
		ctrl_P.setEtapa_Atual(e);
		
		TelaEtapa tE = ctrl_P.getjInternal_TelaInfoEtapa()
				.getTelaEtapa_Tarefas()
				.getTelaEtapa();
		
		atualizarDadoEtapa(tE, ctrl_P.getEtapa_Atual(), ctrl_P.gettTarefa());
		
		atualizarDadoColaborador(tE.getTelaColaboradorEnvolvido(), e, false);
		
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
	
	public void exibirJInternalInfoColaboradorSimples(Colaborador c) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setPessoa_Atual_Colab(
		(Pessoa) Fachada
				.getInstance()
				.buscar(
						Pessoa.class,
						c
						.getPessoa()
						.getId()
						));
		
		TelaInfoPessoaContatoOutremSimples tIP = ctrl_P.getjInternal_TelaInfoPessoaOutremSimples().getTelaInfoPessoaContatoOutremSimples();
		atualizarDadoPessoaColaboradorSimples(tIP, ctrl_P.getPessoa_Atual_Colab(), ctrl_P.gettCaracteristicaExtra2());
		ctrl_P.getjInternal_TelaInfoPessoaOutremSimples().queroFoco();
	}
	
	public void exibirJInternalInfoPessoaSimples(Pessoa pessoa) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setPessoa_Outrem(pessoa);
		
		TelaInfoPessoaContatoOutremSimples tIP = ctrl_P.getjInternal_TelaInfoPessoaOutremSimples().getTelaInfoPessoaContatoOutremSimples();
		atualizarDadoPessoaColaboradorSimples(tIP, ctrl_P.getPessoa_Outrem(), ctrl_P.gettCaracteristicaExtra2());
		ctrl_P.getjInternal_TelaInfoPessoaOutremSimples().queroFoco();
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

	public void exibirJInternalInfoPessoaOutrem(Pessoa p) throws ValidacaoException, PropertyVetoException
	{
		ctrl_P.setPessoa_Outrem(p);
		
		TelaInfoPessoaContatoOutrem tIP = ctrl_P.getjInternal_TelaInfoPessoaOutrem().getTelaInfoPessoaContatoOutrem();
		
		atualizarDadoPessoaContatoOutrem(tIP, ctrl_P.getPessoa_Outrem(), ctrl_P.gettCaracteristicaExtra2());
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
	 * TODO - A partir daqui é só preenchimento de Tela
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
	
	private void preencherTelaSubEtapa(TelaInfoSubEtapa telaEtapa, SubEtapa sub_etapa)
	{
		telaEtapa.getNomeEtapaField().setDescricao("Nome da Etapa");
		telaEtapa.getNomeEtapaField().setTexto(sub_etapa.getNome());
		telaEtapa.getDescricaoTextArea().setText(sub_etapa.getDescricao());
		telaEtapa.getBarraProgressBar().setValue(Math.round(sub_etapa.getPorcentagem()));
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
	
	private void preencherTelaSubTarefa(TelaInfoSubTarefa telaTarefa, SubTarefa sub_tarefa) 
	{
		telaTarefa.getNomeTarefaField().setDescricao("Nome da Tarefa");
		telaTarefa.getNomeTarefaField().setTexto(sub_tarefa.getNome());
		telaTarefa.getDescricaoTextArea().setText(sub_tarefa.getDescricao());
		telaTarefa.getChckbxFinalizada().setSelected(sub_tarefa.isConcluida());
		telaTarefa.getPrioridadeComboBox().setSelectedItem(sub_tarefa.getPrioridade());

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
		
		String data = format1.format(sub_tarefa.getHorario());
		String horario = format2.format(sub_tarefa.getHorario());
		
		System.out.println("Horario "+ sub_tarefa.getHorario());
		
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
	
	private void atualizarDadoSubEtapa(TelaInfoSubEtapa tE, SubEtapa e, TSubTarefa tT) throws ValidacaoException
	{
		
		preencherTelaSubEtapa(tE,e);
		List<SubTarefa> t = Fachada.getInstance().getBoSubTarefa().getListPorEtapa(e);		
		tT.addAll(t);
		e.setSub_tarefas(t);
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
	
	private void atualizarDadoPessoaContatoOutrem(TelaInfoPessoaContatoOutrem tIP, Pessoa p, TCaracteristicaExtra tce) throws ValidacaoException
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
		
		tIP.getTelaMiniPessoa3().getResetarSenhaChbx().setSelected(p.isReset_senha());
		
	}
	
	private void atualizarDadoColaborador(TelaColaboradorEnvolvido tCE, Tarefa t, boolean b_colab) throws ValidacaoException
	{
		t.setTarefaColaborador(
				Fachada.getInstance().getBoTarefaColaborador().getPorTarefa(t)
				);
		if(t.getTarefaColaborador() != null)
		{
			t.getTarefaColaborador().setColaborador(
					Fachada.getInstance().getBoColaborador().buscarPorTarefaColaborador(t.getTarefaColaborador())
					);
			atualizarDadoColaborador(tCE, t.getTarefaColaborador().getColaborador(), b_colab);
		}
		else
		{
			Colaborador c = null;
			atualizarDadoColaborador(tCE, c, b_colab);
		}
	}
	
	private void atualizarDadoColaborador(TelaColaboradorEnvolvido tCE, Etapa e, boolean b_colab) throws ValidacaoException
	{
		e.setGerenteEtapa(
				Fachada.getInstance().getBoGerenteEtapa().getPorEtapa(e)
				);
		if(e.getGerenteEtapa() != null)
		{
			e.getGerenteEtapa().setColaborador(
					Fachada.getInstance().getBoColaborador().buscarPorGerenteEtapa(e.getGerenteEtapa())
					);
			atualizarDadoColaborador(tCE, e.getGerenteEtapa().getColaborador(), b_colab);
		}
		else
		{
			Colaborador c = null;
			atualizarDadoColaborador(tCE, c, b_colab);
		}
	}
	
	private void atualizarDadoColaborador(TelaColaboradorEnvolvido tCE, SubEtapa se, boolean b_colab) throws ValidacaoException
	{
		se.setSubEtapaColaborador(
				Fachada.getInstance().getBoSubEtapaColaborador().getPorEtapa(se)
				);
		if(se.getSubEtapaColaborador() != null)
		{
			se.getSubEtapaColaborador().setColaborador(
					Fachada.getInstance().getBoColaborador().buscarPorSubEtapaColaborador(se.getSubEtapaColaborador())
					);
			atualizarDadoColaborador(tCE, se.getSubEtapaColaborador().getColaborador(), b_colab);
		}
		else
		{
			Colaborador c = null;
			atualizarDadoColaborador(tCE, c, b_colab);
		}
	}
	
	
	private void preencherTelaColaboradorEnvolvido(TelaColaboradorEnvolvido tCE, Colaborador c)
	{
		tCE.getCmptxtResponsavel().setText(c.getPessoa().getNome());;
		tCE.getCmptxtDatalog().setText(c.getData_ingresso().toString());
	}
	
	private void atualizarDadoColaborador(TelaColaboradorEnvolvido tCE, Colaborador c, boolean b_colab) throws BOException, DAOException
	{
		if(c == null)
		{
			tCE.getCmptxtResponsavel().setText("");
			tCE.getCmptxtDatalog().setText("");
			tCE.exibirSemColaborador();
		}
		else
		{
			c.setPessoa(
					(Pessoa)Fachada.getInstance().buscar(Pessoa.class, c.getPessoa().getId())
					);
			preencherTelaColaboradorEnvolvido(tCE, c);
			if(b_colab)
				tCE.exibirComColaboradorVisaoColaborador();
			else
				tCE.exibirComColaborador();
		}
	}
}
