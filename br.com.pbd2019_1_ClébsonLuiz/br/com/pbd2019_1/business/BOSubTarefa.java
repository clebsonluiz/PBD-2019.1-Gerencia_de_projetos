package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOSubTarefa;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.exception.DAOException;

public class BOSubTarefa extends BOGenerico<SubTarefa>{

	public BOSubTarefa() {
		super(new DAOSubTarefa(), SubTarefa.class);
	}
	
	public List<SubTarefa> getListPorEtapa(SubEtapa sub_etapa) throws DAOException
	{
		return ((DAOSubTarefa)this.daoT).getListPorEtapa(sub_etapa);
	}
	
	
}
