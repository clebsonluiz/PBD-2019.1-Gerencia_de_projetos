package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOViewGerenteEtapa;
import br.com.pbd2019_1.entidade.ViewGerenteEtapa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOViewGerenteEtapa extends BOView<ViewGerenteEtapa>{

	public BOViewGerenteEtapa() {
		super(new DAOViewGerenteEtapa(), ViewGerenteEtapa.class);
	}
	
	public List<ViewGerenteEtapa> getPorPessoa(int indexCombo, String busca, int id_pessoa) throws DAOException, BOException
	{
		String sql = "";
		if(indexCombo == 0) 
			sql = "select ge from ViewGerenteEtapa ge where ge.cod_de_pessoa = " + id_pessoa;
		else 
			sql = "select ge from ViewGerenteEtapa ge where ge.nome like '%" + busca + "%' and ge.cod_de_pessoa = " + id_pessoa;
		return buscaListaHQLGenerica(sql);
	}
	
	public ViewGerenteEtapa getPorEtapa(int id_etapa) throws DAOException, BOException
	{
		return buscaHQLGenerica("select ge from ViewGerenteEtapa ge where ge.cod_de_etapa = " + id_etapa);
	}
}

