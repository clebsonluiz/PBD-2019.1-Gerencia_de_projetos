package br.com.pbd2019_1.fachada;

import java.util.List;

import br.com.pbd2019_1.business.BO;
import br.com.pbd2019_1.business.BOBackup;
import br.com.pbd2019_1.business.BOCaracteristicaExtra;
import br.com.pbd2019_1.business.BOColaborador;
import br.com.pbd2019_1.business.BOContato;
import br.com.pbd2019_1.business.BOEtapa;
import br.com.pbd2019_1.business.BOLogUpdate;
import br.com.pbd2019_1.business.BOPessoa;
import br.com.pbd2019_1.business.BOProjeto;
import br.com.pbd2019_1.business.BOTarefa;
import br.com.pbd2019_1.dao.DAO;
import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class Fachada {

	private BO bo;
	private BOEtapa boEtapa;
	private BOBackup boBackup;
	private BOPessoa boPessoa;
	private BOTarefa boTarefa;
	private BOContato boContato;
	private BOProjeto boProjeto;
	private BOLogUpdate boLogUpdate;
	private BOColaborador boColaborador;
	private BOCaracteristicaExtra boCaracteristicaExtra;
	
	/*Loaders*/
	public void carregarBo() {this.bo = new BO(new DAO() {}) {};}
	public void carregarBoEtapa() {this.boEtapa = new BOEtapa();}
	public void carregarBoBackup() {this.boBackup = new BOBackup();}
	public void carregarBoPessoa() {this.boPessoa = new BOPessoa();}
	public void carregarBoTarefa() {this.boTarefa = new BOTarefa();}
	public void carregarBoContato() {this.boContato = new BOContato();}
	public void carregarBoProjeto() {this.boProjeto = new BOProjeto();}
	public void carregarBoLogUpdate() {this.boLogUpdate = new BOLogUpdate();}
	public void carregarBoColaborador() {this.boColaborador = new BOColaborador();}
	public void carregarBoCaracteristicaExtra() {this.boCaracteristicaExtra = new BOCaracteristicaExtra();}

	private  Fachada() {}

	private static Fachada fachada;
	
	public static Fachada getInstance() {
		if(fachada == null)
			fachada = new Fachada();
		return fachada;
	}
	
	/*CRUD BASICO*/
	public <T extends Entidade> Entidade inserir(T t) throws BOException, DAOException{
		if(t instanceof CaracteristicaExtra)
			return boCaracteristicaExtra.inserir((CaracteristicaExtra)t);
		else if(t instanceof Colaborador)
			return boColaborador.inserir((Colaborador)t);
		else if(t instanceof Contato)
			return boContato.inserir((Contato)t);
		else if(t instanceof Etapa)
			return boEtapa.inserir((Etapa)t);
		else if(t instanceof LogUpdate)
			return boLogUpdate.inserir((LogUpdate)t);
		else if(t instanceof Pessoa)
			return boPessoa.inserir((Pessoa)t);
		else if(t instanceof Projeto)
			return boProjeto.inserir((Projeto)t);
		else if(t instanceof Tarefa)
			return boTarefa.inserir((Tarefa)t);
		else if(t instanceof Backup)
			return boBackup.inserir((Backup)t);
		else
			return null;
	}
	
	public <T extends Entidade> Entidade atualizar(T t) throws BOException, DAOException{
		if(t instanceof CaracteristicaExtra)
			return boCaracteristicaExtra.atualizar((CaracteristicaExtra)t);
		else if(t instanceof Colaborador)
			return boColaborador.atualizar((Colaborador)t);
		else if(t instanceof Contato)
			return boContato.atualizar((Contato)t);
		else if(t instanceof Etapa)
			return boEtapa.atualizar((Etapa)t);
		else if(t instanceof LogUpdate)
			return boLogUpdate.atualizar((LogUpdate)t);
		else if(t instanceof Pessoa)
			return boPessoa.atualizar((Pessoa)t);
		else if(t instanceof Projeto)
			return boProjeto.atualizar((Projeto)t);
		else if(t instanceof Tarefa)
			return boTarefa.atualizar((Tarefa)t);
		else if(t instanceof Backup)
			return boBackup.atualizar((Backup)t);
		else
			return null;
	}
	
	
	public void deletar(Object t) throws BOException, DAOException{
		if(t instanceof CaracteristicaExtra)
			boCaracteristicaExtra.deletar((CaracteristicaExtra)t);
		else if(t instanceof Colaborador)
			boColaborador.deletar((Colaborador)t);
		else if(t instanceof Contato)
			boContato.deletar((Contato)t);
		else if(t instanceof Etapa)
			boEtapa.deletar((Etapa)t);
		else if(t instanceof LogUpdate)
			boLogUpdate.deletar((LogUpdate)t);
		else if(t instanceof Pessoa)
			boPessoa.deletar((Pessoa)t);
		else if(t instanceof Projeto)
			boProjeto.deletar((Projeto)t);
		else if(t instanceof Tarefa)
			boTarefa.deletar((Tarefa)t);
		else if(t instanceof Backup)
			boBackup.deletar((Backup)t);
	}
	
	public <T extends Entidade> Entidade buscar(Class<T> classe, int id) throws BOException, DAOException{
		if(classe.getSimpleName().equals(CaracteristicaExtra.class.getSimpleName()))
			return boCaracteristicaExtra.buscar(id);
		else if(classe.getSimpleName().equals(Colaborador.class.getSimpleName()))
			return boColaborador.buscar(id);
		else if(classe.getSimpleName().equals(Contato.class.getSimpleName()))
			return boContato.buscar(id);
		else if(classe.getSimpleName().equals(Etapa.class.getSimpleName()))
			return boEtapa.buscar(id);
		else if(classe.getSimpleName().equals(LogUpdate.class.getSimpleName()))
			return boLogUpdate.buscar(id);
		else if(classe.getSimpleName().equals(Pessoa.class.getSimpleName()))
			return boPessoa.buscar(id);
		else if(classe.getSimpleName().equals(Projeto.class.getSimpleName()))
			return boProjeto.buscar(id);
		else if(classe.getSimpleName().equals(Tarefa.class.getSimpleName()))
			return boTarefa.buscar(id);
		else if(classe.getSimpleName().equals(Backup.class.getSimpleName()))
			return boBackup.buscar(id);
		else
			return null;
	}
	
	/*Metodos Do Log*/
	public String[] gerarLog(Entidade e) 
	{
		if(e instanceof CaracteristicaExtra)
			return boLogUpdate.gerarLog((CaracteristicaExtra) e);
		else if(e instanceof Colaborador)
			return boLogUpdate.gerarLog((Colaborador) e);
		else if(e instanceof Contato)
			return boLogUpdate.gerarLog((Contato) e);
		else if(e instanceof Etapa)
			return boLogUpdate.gerarLog((Etapa) e);
		else if(e instanceof Pessoa)
			return boLogUpdate.gerarLog((Pessoa) e);
		else if(e instanceof Projeto)
			return boLogUpdate.gerarLog((Projeto) e);
		else if(e instanceof Tarefa)
			return boLogUpdate.gerarLog((Tarefa) e);
		return null;
	}
	
	public void gerarLogInsercao(Entidade entidade, Pessoa responsavel, LogUpdate log) 
			throws BOException, DAOException 
	{
		boLogUpdate.gerarLogInsercao(entidade, responsavel, log);
	}
	
	public void gerarLogUpdate(String[] antes, Entidade entidade, Pessoa responsavel, LogUpdate log) 
			throws BOException, DAOException
	{
		boLogUpdate.gerarLogUpdate(antes, entidade, responsavel, log);
	}
	
	public void gerarLogDelete(String[] antes, Entidade entidade, Pessoa responsavel, LogUpdate log) 
			throws BOException, DAOException
	{
		boLogUpdate.gerarLogDelete(antes, entidade, responsavel, log);
	}
	
	/*Metodos do Bo Basico*/
	public <T extends Entidade> List<T> buscarAll(Class<T> classe) throws BOException, DAOException{
		return bo.buscarALL(classe);
	}
	
	public <T extends Entidade> T buscarHQL(Class<T> classe, String hql) throws BOException, DAOException{
		return bo.buscaHQL(classe, hql);
	}
	
	public <T extends Entidade> List<T> buscarHQLList(Class<T> classe, String hql) throws BOException, DAOException{
		return bo.buscaHQLList(classe, hql);
	}
	
	public List<Object[]> inserirSQLGenerica(String sql) throws BOException, DAOException{
		return bo.buscaSQLGenerica(sql);
	}
	
	/*Getters*/
	public BO getBo() {return bo;}
	public BOEtapa getBoEtapa() {return boEtapa;}
	public BOBackup getBoBackup() {return boBackup;}
	public BOPessoa getBoPessoa() {return boPessoa;}
	public BOTarefa getBoTarefa() {return boTarefa;}
	public BOContato getBoContato() {return boContato;}
	public BOProjeto getBoProjeto() {return boProjeto;}
	public BOLogUpdate getBoLogUpdate() {return boLogUpdate;}
	public BOColaborador getBoColaborador() {return boColaborador;}
	public BOCaracteristicaExtra getBoCaracteristicaExtra() {return boCaracteristicaExtra;}
	
}
