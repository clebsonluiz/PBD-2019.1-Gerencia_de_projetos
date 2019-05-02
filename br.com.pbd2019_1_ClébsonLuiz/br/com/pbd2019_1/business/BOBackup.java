package br.com.pbd2019_1.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import br.com.pbd2019_1.dao.DAOBackup;
import br.com.pbd2019_1.dao.DAOConfigDefault;
import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.entidade.ConfigDefault;
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
		return ((DAOBackup)this.daoT).buscarAll("RECENTES", data1, data2);
	}
	
	public List<Backup> buscarAllAntigosPeriodo(Date data1, Date data2) throws BOException, DAOException {
		if(data1 == null || data2 == null)
			throw new BOException("Datas invalidas");
		return ((DAOBackup)this.daoT).buscarAll("ANTIGOS", data1, data2);
	}
	
	public boolean buscarExistente() throws DAOException 
	{
		return ((DAOBackup)this.daoT).buscarExistente("verifica_backup_existente");
	}
	
	public boolean buscarMaisAntigo() throws DAOException 
	{
		return ((DAOBackup)this.daoT).buscarExistente("backup_recente");
	}

	
	public boolean isBackupNecessario() throws DAOException, ClassNotFoundException, IOException {
		if(buscarExistente())
		{
			return buscarMaisAntigo();
		}
		else
		{
			ConfigDefault config = DAOConfigDefault.loadConfig();
			if(config != null) 
			{
				if(config.getHora_bakup() != null)
				{
					java.sql.Date date = DateUtil.getDateSQL(DateUtil.getDataAtual());
					String dataAtual = DateUtil.getDateString("HH:MM:SS", date);
					
					return DateUtil.TimeUtil.isAntes(dataAtual, config.getHora_bakup());
				}
				return false;
			}
			else
			{
				DAOConfigDefault.setConfigPadrao();
				return false;
			}
		}
	}
	
	
}
