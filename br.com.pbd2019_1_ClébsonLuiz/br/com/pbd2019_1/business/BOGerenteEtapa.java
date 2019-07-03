package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOGerenteEtapa;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.GerenteEtapa;
import br.com.pbd2019_1.exception.DAOException;

public class BOGerenteEtapa extends BOGenerico<GerenteEtapa>{

	public BOGerenteEtapa() {
		super(new DAOGerenteEtapa(), GerenteEtapa.class);
	}
	
	public List<GerenteEtapa> getListPorColaborador(Colaborador colaborador) throws DAOException
	{
		return ((DAOGerenteEtapa)this.daoT).getListPorColaborador(colaborador);
	}
	
	public List<GerenteEtapa> getListPorEtapa(Etapa etapa) throws DAOException
	{
		return ((DAOGerenteEtapa)this.daoT).getListPorEtapa(etapa);
	}
	
	public GerenteEtapa getPorEtapa(Etapa etapa) throws DAOException
	{
		return ((DAOGerenteEtapa)this.daoT).getPorEtapa(etapa);
	}
	
}
