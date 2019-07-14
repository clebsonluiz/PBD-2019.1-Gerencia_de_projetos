package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOView;
import br.com.pbd2019_1.entidade.View;
import br.com.pbd2019_1.exception.DAOException;

public class BOView<T extends View>{

	protected DAOView<T> daoT;
	protected Class<T> classe;
	
	public BOView(DAOView<T> daoT, Class<T> classe) {
		this.daoT = daoT;
		this.classe = classe;
	}
	
	public T buscaHQLGenerica(String sql) throws DAOException{
		return this.daoT.buscaHQLGenerica(classe, sql);
	}
	
	public List<T> buscaListaHQLGenerica(String sql) throws DAOException{
		return this.daoT.buscaListaHQLGenerica(classe, sql);
	}
	
}
