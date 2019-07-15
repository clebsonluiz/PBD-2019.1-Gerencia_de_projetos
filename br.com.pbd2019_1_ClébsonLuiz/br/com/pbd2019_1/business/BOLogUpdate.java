package br.com.pbd2019_1.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pbd2019_1.dao.DAOLogUpdate;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.utils.LogUpdateUtil;

public class BOLogUpdate extends BOGenerico<LogUpdate>{

	public BOLogUpdate() {
		super(new DAOLogUpdate(), LogUpdate.class);
	}
	
	protected void validacaoInsercao(LogUpdate t) throws BOException{
		if(t == null)
			throw new BOException("Erro ao inserir Log");
		t.setData_log(LocalDateTime.now());
	}
	
	protected void validacaoAtualizacao(LogUpdate t) throws BOException{
		validacaoInsercao(t);
	}
	
	public List<LogUpdate> buscarPorData(Date data1, Date data2) throws BOException, DAOException{
		if(data1 == null || data2 == null)
			throw new BOException("Não foi possivel buscar por data");
		return ((DAOLogUpdate)this.daoT).buscarPorData(
				DateUtil.getDateSQL(data1), 
				DateUtil.getDateSQL(data2));
	}
	
	public List<LogUpdate> buscarEspecificado(String tipo, String tabela, String id_tabela, String responsavel,Date data1, Date data2) throws BOException, DAOException{
		if(data1 == null || data2 == null)
			return ((DAOLogUpdate)this.daoT).buscarMiniEspecificada(tipo, tabela, id_tabela, responsavel);
		else
			return ((DAOLogUpdate)this.daoT).buscarEspecificada(tipo, tabela, id_tabela, responsavel,
					DateUtil.getDateSQL(data1), 
					DateUtil.getDateSQL(data2));
	}
	
	public void gerarLogInsercao(Entidade entidade, Pessoa responsavel, LogUpdate log) 
			throws BOException, DAOException 
	{
		log.setId_tabela(entidade.getId());
		log.setTipo("CADASTRO");
		log.setData_log(LocalDateTime.now());
		if(responsavel != null)
			log.setResponsavel(responsavel.getCpf());
		else
			log.setResponsavel("");
		log.setColuna(LogUpdateUtil.GerarColunas.gerarColunas(entidade));
		
		if(entidade instanceof CaracteristicaExtra)
			inserirLogCaracteristicaExtra(log, (CaracteristicaExtra) entidade);
		else if(entidade instanceof Colaborador)
			inserirLogColaborador(log, (Colaborador) entidade);
		else if(entidade instanceof Contato)
			inserirLogContato(log, (Contato) entidade);
		else if(entidade instanceof Etapa)
			inserirLogEtapa(log, (Etapa) entidade);
		else if(entidade instanceof Pessoa)
			inserirLogPessoa(log, (Pessoa) entidade);
		else if(entidade instanceof Projeto)
			inserirLogProjeto(log, (Projeto) entidade);
		else if(entidade instanceof Tarefa)
			inserirLogTarefa(log, (Tarefa) entidade);
		else if(entidade instanceof SubEtapa)
			inserirLogSubEtapa(log, (SubEtapa) entidade);
		else if(entidade instanceof SubTarefa)
			inserirLogSubTarefa(log, (SubTarefa) entidade);
	}
	
	public void gerarLogUpdate(List<String> antes, Entidade entidade, Pessoa responsavel, LogUpdate log) 
			throws BOException, DAOException
	{
		log.setId_tabela(entidade.getId());
		log.setTipo("UPDATE");
		log.setData_log(LocalDateTime.now());
		log.setResponsavel(responsavel.getCpf());
		log.setColuna(LogUpdateUtil.GerarColunas.gerarColunas(entidade));
		log.setAntes(antes);
		
		if(entidade instanceof CaracteristicaExtra)
			updateLogCaracteristicaExtra(log, (CaracteristicaExtra) entidade);
		else if(entidade instanceof Colaborador)
			updateLogColaborador(log, (Colaborador) entidade);
		else if(entidade instanceof Contato)
			updateLogContato(log, (Contato) entidade);
		else if(entidade instanceof Etapa)
			updateLogEtapa(log, (Etapa) entidade);
		else if(entidade instanceof Pessoa)
			updateLogPessoa(log, (Pessoa) entidade);
		else if(entidade instanceof Projeto)
			updateLogProjeto(log, (Projeto) entidade);
		else if(entidade instanceof Tarefa)
			updateLogTarefa(log, (Tarefa) entidade);
		else if(entidade instanceof SubEtapa)
			updateLogSubEtapa(log, (SubEtapa) entidade);
		else if(entidade instanceof SubTarefa)
			updateLogSubTarefa(log, (SubTarefa) entidade);
	}
	
	public void gerarLogDelete(List<String> antes, Entidade entidade, Pessoa responsavel, LogUpdate log) 
			throws BOException, DAOException
	{
		log.setId_tabela(entidade.getId());
		log.setTipo("DELETE");
		log.setData_log(LocalDateTime.now());
		log.setResponsavel(responsavel.getCpf());
		log.setColuna(LogUpdateUtil.GerarColunas.gerarColunas(entidade));
		log.setAntes(antes);
		log.setDepois(new ArrayList<>());
		
		if(entidade instanceof CaracteristicaExtra)
			log.setTabela(CaracteristicaExtra.class.getSimpleName());
		else if(entidade instanceof Colaborador)
			log.setTabela(Colaborador.class.getSimpleName());
		else if(entidade instanceof Contato)
			log.setTabela(Contato.class.getSimpleName());
		else if(entidade instanceof Etapa)
			log.setTabela(Etapa.class.getSimpleName());
		else if(entidade instanceof Pessoa)
			log.setTabela(Pessoa.class.getSimpleName());
		else if(entidade instanceof Projeto)
			log.setTabela(Projeto.class.getSimpleName());
		else if(entidade instanceof Tarefa)
			log.setTabela(Tarefa.class.getSimpleName());
		else if(entidade instanceof SubEtapa)
			log.setTabela(SubEtapa.class.getSimpleName());
		else if(entidade instanceof SubTarefa)
			log.setTabela(SubTarefa.class.getSimpleName());
		
		inserir(log);
	}
	
	private void updateLogTarefa(LogUpdate log, Tarefa ta) throws BOException, DAOException {
		log.setTabela(Tarefa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(ta));
		inserir(log);
	}
	
	private void updateLogSubTarefa(LogUpdate log, SubTarefa ta) throws BOException, DAOException {
		log.setTabela(SubTarefa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(ta));
		inserir(log);
	}

	private void updateLogProjeto(LogUpdate log, Projeto pro) throws BOException, DAOException {
		log.setTabela(Projeto.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(pro));
		inserir(log);
	}

	private void updateLogPessoa(LogUpdate log, Pessoa pe) throws BOException, DAOException {
		log.setTabela(Pessoa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(pe));
		inserir(log);
	}

	private void updateLogEtapa(LogUpdate log, Etapa e) throws BOException, DAOException {
		log.setTabela(Etapa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(e));
		inserir(log);
	}

	private void updateLogSubEtapa(LogUpdate log, SubEtapa e) throws BOException, DAOException {
		log.setTabela(SubEtapa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(e));
		inserir(log);
	}
	
	private void updateLogContato(LogUpdate log, Contato co) throws BOException, DAOException {
		log.setTabela(Contato.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(co));
		inserir(log);
	}

	private void updateLogColaborador(LogUpdate log, Colaborador cl) throws BOException, DAOException {
		log.setTabela(Colaborador.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(cl));
		inserir(log);
	}

	private void updateLogCaracteristicaExtra(LogUpdate log, CaracteristicaExtra ce) throws BOException, DAOException {
		log.setTabela(CaracteristicaExtra.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(ce));
		inserir(log);
	}

	private void inserirLogProjeto(LogUpdate log, Projeto pro) throws BOException, DAOException
	{
		log.setTabela(Projeto.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(pro));
		inserir(log);
	}
	
	private void inserirLogTarefa(LogUpdate log, Tarefa ta) throws BOException, DAOException
	{
		log.setTabela(Tarefa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(ta));
		inserir(log);
	}
	
	private void inserirLogSubTarefa(LogUpdate log, SubTarefa ta) throws BOException, DAOException
	{
		log.setTabela(SubTarefa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(ta));
		inserir(log);
	}
	
	private void inserirLogEtapa(LogUpdate log, Etapa e) throws BOException, DAOException
	{
		log.setTabela(Etapa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(e));
		inserir(log);
	}
	
	private void inserirLogSubEtapa(LogUpdate log, SubEtapa e) throws BOException, DAOException
	{
		log.setTabela(SubEtapa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(e));
		inserir(log);
	}
	
	private void inserirLogContato(LogUpdate log, Contato co) throws BOException, DAOException
	{
		log.setTabela(Contato.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(co));
		inserir(log);
	}
	
	private void inserirLogColaborador(LogUpdate log, Colaborador cl) throws BOException, DAOException
	{
		log.setTabela(Colaborador.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(cl));
		inserir(log);
	}
	
	private void inserirLogPessoa(LogUpdate log, Pessoa pe) throws BOException, DAOException
	{
		if(log.getResponsavel().trim().equals(""))
			log.setResponsavel(pe.getCpf());
		log.setTabela(Pessoa.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(pe));
		inserir(log);
	}
	
	private void inserirLogCaracteristicaExtra(LogUpdate log, CaracteristicaExtra ca) throws BOException, DAOException
	{
		log.setTabela(CaracteristicaExtra.class.getSimpleName());
		log.setDepois(LogUpdateUtil.GerarLog.gerarLog(ca));
		inserir(log);
	}
	
}
