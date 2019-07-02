package br.com.pbd2019_1.dao;

import java.util.List;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubEtapaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class DAOSubEtapa extends DAOGenerico<SubEtapa>{

	public List<SubEtapa> getListPorSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubEtapa.class,
				"SubEtapa.colaborador",
				"subEtapaColaborador",
				subEtapaColaborador);
	}
	
	public List<SubEtapa> getListPorEtapa(Etapa etapa) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubEtapa.class,
				"SubEtapa.etapa",
				"etapa",
				etapa);
	}
	
	public SubEtapa getPorSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) throws DAOException
	{
		return buscaNamedQueryGenericaFK(
				SubEtapa.class,
				"SubEtapa.colaborador",
				"subEtapaColaborador",
				subEtapaColaborador);
	}
	
}
