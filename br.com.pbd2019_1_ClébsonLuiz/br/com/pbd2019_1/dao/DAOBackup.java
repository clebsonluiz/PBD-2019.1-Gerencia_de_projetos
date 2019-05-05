package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.StoredProcedureQuery;

import br.com.pbd2019_1.entidade.Backup;
import br.com.pbd2019_1.exception.DAOException;

public class DAOBackup extends DAOGenerico<Backup>{

	public List<Backup> buscarAll(String ordem) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Backup> backups = null;
		try {
			
			switch(ordem)
			{
			case "RECENTES":
				backups = entityManager.createNamedQuery("Backup.ascALL",
						Backup.class).getResultList();
				break;
			case "ANTIGOS":
				backups = entityManager.createNamedQuery("Backup.descALL",
						Backup.class).getResultList();
				break;
			}
			
		} catch (NoResultException e) {
			e.printStackTrace();
			backups = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return backups;
	}
	
	public List<Backup> buscarAll(String ordem, java.util.Date data1, java.util.Date data2) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Backup> backups = null;
		try {
			
			switch(ordem)
			{
			case "RECENTES":
				backups = entityManager.createNamedQuery("Backup.asc",
						Backup.class)
				.setParameter("data1", data1)
				.setParameter("data2", data2)
				.getResultList();
				break;
			case "ANTIGOS":
				backups = entityManager.createNamedQuery("Backup.desc",
						Backup.class)
				.setParameter("data1", data1)
				.setParameter("data2", data2)
				.getResultList();
				break;
			}
			
		} catch (NoResultException e) {
			e.printStackTrace();
			backups = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return backups;
	}
	
	public boolean buscarExistente(String procedure) throws DAOException {
		EntityManager entityManager = createEntityManager();
		boolean isExistente;
		try {
			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedure);
			
			isExistente = (boolean) query.getSingleResult();
			
		} catch (NoResultException e) {
			e.printStackTrace();
			isExistente = false;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return isExistente;
	}
	
}
