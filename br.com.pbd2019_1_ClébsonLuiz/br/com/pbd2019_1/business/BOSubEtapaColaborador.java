package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOSubEtapaColaborador;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubEtapaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class BOSubEtapaColaborador extends BOGenerico<SubEtapaColaborador>{

	public BOSubEtapaColaborador() {
		super(new DAOSubEtapaColaborador(), SubEtapaColaborador.class);
	}
	
	public List<SubEtapaColaborador> getListPorColaborador(Colaborador colaborador) throws DAOException
	{
		return ((DAOSubEtapaColaborador)this.daoT).getListPorColaborador(colaborador);
	}
	
	public List<SubEtapaColaborador> getListPorEtapa(SubEtapa sub_etapa) throws DAOException
	{
		return ((DAOSubEtapaColaborador)this.daoT).getListPorEtapa(sub_etapa);
	}
	
	public SubEtapaColaborador getPorEtapa(SubEtapa sub_etapa) throws DAOException
	{
		return ((DAOSubEtapaColaborador)this.daoT).getPorEtapa(sub_etapa);
	}
	
}
