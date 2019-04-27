package br.com.pbd2019_1.business;

import java.util.Date;
import java.util.List;

import br.com.pbd2019_1.dao.DAOBackup;
import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.DateUtil;

public class BOBackup extends BOGenerico<Backup>{

	public BOBackup() {
		super(new DAOBackup(), Backup.class);
	}
	
	
	public List<Backup> buscarAllRecentes() throws DAOException {
		return ((DAOBackup)this.daoT).buscarAll("RECENTES");
	}
	
	public List<Backup> buscarAllAntigos() throws DAOException {
		return ((DAOBackup)this.daoT).buscarAll("ANTIGOS");
	}
	
	public List<Backup> buscarAllRecentesPeriodo(Date data1, Date data2) throws BOException, DAOException {
		if(data1 == null || data2 == null)
			throw new BOException("Datas invalidas");
		String date1 = DateUtil.getDateString("yyyy-MM-dd", DateUtil.getDateSQL(data1));
		String date2 = DateUtil.getDateString("yyyy-MM-dd", DateUtil.getDateSQL(data2));
		return ((DAOBackup)this.daoT).buscarAll("RECENTES", date1, date2);
	}
	
	public List<Backup> buscarAllAntigosPeriodo(Date data1, Date data2) throws BOException, DAOException {
		if(data1 == null || data2 == null)
			throw new BOException("Datas invalidas");
		String date1 = DateUtil.getDateString("yyyy-MM-dd", DateUtil.getDateSQL(data1));
		String date2 = DateUtil.getDateString("yyyy-MM-dd", DateUtil.getDateSQL(data2));
		return ((DAOBackup)this.daoT).buscarAll("ANTIGOS", date1, date2);
	}
	

}
