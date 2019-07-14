package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOViewSubEtapaColaborador;
import br.com.pbd2019_1.entidade.ViewSubEtapaColaborador;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOViewSubEtapaColaborador extends BOView<ViewSubEtapaColaborador>{

	public BOViewSubEtapaColaborador() 
	{
		super(new DAOViewSubEtapaColaborador(), ViewSubEtapaColaborador.class);
	}
	
	public List<ViewSubEtapaColaborador> getPorPessoa(int indexCombo, String busca, int id_pessoa) throws DAOException, BOException
	{
		String sql = "";
		if(indexCombo == 0) 
			sql = "select se from ViewSubEtapaColaborador se where se.cod_de_pessoa = " + id_pessoa;
		else 
			sql = "select se from ViewSubEtapaColaborador se where se.nome like '%" + busca + "%' and se.cod_de_pessoa = " + id_pessoa;
		return buscaListaHQLGenerica(sql);
	}
	
	public ViewSubEtapaColaborador getPorSubEtapa(int id_sub_etapa) throws DAOException, BOException
	{
		return buscaHQLGenerica("select se from ViewSubEtapaColaborador se where se.cod_de_sub_etapa = " + id_sub_etapa);
	}
	
}
