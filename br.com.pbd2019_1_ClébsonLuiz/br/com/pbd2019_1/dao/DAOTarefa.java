package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOTarefa extends DAOGenerico<Tarefa>{

	public List<Tarefa> buscarPorEtapa(Etapa etapa) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Tarefa> tarefas = null;
		try {
			tarefas = entityManager.createNamedQuery("Tarefa.etapa",
					Tarefa.class)
			.setParameter("etapa", etapa).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return tarefas;
	}
	
	
	
}
