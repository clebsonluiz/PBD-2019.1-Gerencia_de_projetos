package br.com.pbd2019_1.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.exception.DAOException;

public class DAOLogUpdate extends DAOGenerico<LogUpdate>{

	public List<LogUpdate> buscarPorData(Date data1, Date data2) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<LogUpdate> logUpdates = null;
		try {
			
			TypedQuery<LogUpdate> query = entityManager.createNamedQuery("LogUpdate.date",
					LogUpdate.class);
			query.setParameter("data1", data1);
			query.setParameter("data2", data2);
			logUpdates = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			logUpdates = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return logUpdates;
	}
	
}
