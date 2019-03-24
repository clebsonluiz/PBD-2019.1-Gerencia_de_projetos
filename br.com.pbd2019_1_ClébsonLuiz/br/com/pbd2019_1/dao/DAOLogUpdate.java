package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;

public class DAOLogUpdate extends DAOGenerico<LogUpdate>{

	public List<LogUpdate> buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		List<LogUpdate> logUpdates = null;
		try {
			logUpdates = entityManager.createNamedQuery("LogUpdate.pessoa",
					LogUpdate.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return logUpdates;
	}
	
}
