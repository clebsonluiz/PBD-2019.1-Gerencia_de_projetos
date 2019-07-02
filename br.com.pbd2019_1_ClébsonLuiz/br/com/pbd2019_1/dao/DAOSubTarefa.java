package br.com.pbd2019_1.dao;

import java.util.List;

import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOSubTarefa extends DAOGenerico<SubTarefa>{

	public List<SubTarefa> getListPorEtapa(SubEtapa sub_etapa) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubTarefa.class,
				"SubTarefa.subEtapa",
				"sub_etapa",
				sub_etapa);
	}
	
}
