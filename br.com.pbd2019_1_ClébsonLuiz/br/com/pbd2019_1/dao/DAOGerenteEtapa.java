package br.com.pbd2019_1.dao;

import java.util.List;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.GerenteEtapa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOGerenteEtapa extends DAOGenerico<GerenteEtapa>{

	public List<GerenteEtapa> getListPorColaborador(Colaborador colaborador) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				GerenteEtapa.class,
				"Gerente.colaborador",
				"colaborador",
				colaborador);
	}
	
	public List<GerenteEtapa> getListPorEtapa(Etapa etapa) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				GerenteEtapa.class,
				"Gerente.etapa",
				"etapa",
				etapa);
	}
	
	public GerenteEtapa getPorEtapa(Etapa etapa) throws DAOException
	{
		return buscaNamedQueryGenericaFK(
				GerenteEtapa.class,
				"Gerente.etapa",
				"etapa",
				etapa);
	}
	
}
