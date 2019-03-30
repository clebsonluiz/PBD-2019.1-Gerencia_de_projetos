package br.com.pbd2019_1.business;

import java.util.Date;
import java.util.List;

import br.com.pbd2019_1.dao.DAOLogUpdate;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.DateUtil;

public class BOLogUpdate extends BOGenerico<LogUpdate>{

	public BOLogUpdate() {
		super(new DAOLogUpdate(), LogUpdate.class);
	}
	
	protected void validacaoInsercao(LogUpdate t) throws BOException{
		if(t == null)
			throw new BOException("Erro ao inserir Log");
		t.setData_log(DateUtil.getDataAtual());
	}
	
	protected void validacaoAtualizacao(LogUpdate t) throws BOException{
		validacaoInsercao(t);
	}
	
	public List<LogUpdate> buscarPorData(Date data1, Date data2) throws BOException, DAOException{
		if(data1 == null || data2 == null)
			throw new BOException("Não foi possivel buscar por data");
		return ((DAOLogUpdate)this.daoT).buscarPorData(
				DateUtil.getDateSQL(data1), 
				DateUtil.getDateSQL(data2));
	}

}
