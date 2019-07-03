package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOSubEtapa;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubEtapaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class BOSubEtapa extends BOGenerico<SubEtapa>{

	public BOSubEtapa() {
		super(new DAOSubEtapa(), SubEtapa.class);
	}
	
	public List<SubEtapa> getListPorSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) throws DAOException
	{
		return ((DAOSubEtapa)this.daoT).getListPorSubEtapaColaborador(subEtapaColaborador);
	}
	
	public List<SubEtapa> getListPorEtapa(Etapa etapa) throws DAOException
	{
		return ((DAOSubEtapa)this.daoT).getListPorEtapa(etapa);
	}
	
	public SubEtapa getPorSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) throws DAOException
	{
		return ((DAOSubEtapa)this.daoT).getPorSubEtapaColaborador(subEtapaColaborador);
	}
	
}
