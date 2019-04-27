package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAO;
import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public abstract class BO {

	protected DAO dao;

	public BO(DAO dao) {
		this.dao = dao;
	}

	public <T extends Entidade> T buscaHQL(Class<T> classe, String s) throws BOException, DAOException {
		validacaoBuscaHQL(s);
		return this.dao.buscaHQLGenerica(classe, s);
	};
	
	public <T extends Entidade> List<T> buscaHQLList(Class<T> classe, String s) throws BOException, DAOException {
		validacaoBuscaHQLLista(s);
		return this.dao.buscaListaHQLGenerica(classe, s);
	};
	
	public <T extends Entidade> List<T> buscarALL(Class<T> classe) throws BOException, DAOException {
		return this.dao.buscarAll(classe);
	};
	
	public List<Object[]> buscaSQLGenerica(String sql)throws BOException, DAOException {
		return this.dao.buscaSQLGenerica(sql);
	}
	
	protected void validacaoBuscaHQL(String s) throws BOException{
		//TODO - Serão sobrescritos na classe concreta
	}
	
	protected void validacaoBuscaHQLLista(String s) throws BOException{
		//TODO - Serão sobrescritos na classe concreta
	}
}
