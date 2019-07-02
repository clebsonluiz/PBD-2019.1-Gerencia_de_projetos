package br.com.pbd2019_1.dao;

import java.util.List;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubEtapaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class DAOSubEtapaColaborador extends DAOGenerico<SubEtapaColaborador>{

	public List<SubEtapaColaborador> getListPorColaborador(Colaborador colaborador) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubEtapaColaborador.class,
				"SubEtapaColaborador.colaborador",
				"colaborador",
				colaborador);
	}
	
	public List<SubEtapaColaborador> getListPorEtapa(SubEtapa sub_etapa) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubEtapaColaborador.class,
				"SubEtapaColaborador.sub_etapa",
				"sub_etapa",
				sub_etapa);
	}
	
	public SubEtapaColaborador getPorEtapa(SubEtapa sub_etapa) throws DAOException
	{
		return buscaNamedQueryGenericaFK(
				SubEtapaColaborador.class,
				"SubEtapaColaborador.sub_etapa",
				"sub_etapa",
				sub_etapa);
	}
	
}
