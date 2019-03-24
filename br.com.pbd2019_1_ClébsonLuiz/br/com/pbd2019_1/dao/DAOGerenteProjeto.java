package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.GerenteProjeto;
import br.com.pbd2019_1.entidade.Pessoa;

public class DAOGerenteProjeto extends DAOGenerico<GerenteProjeto>{

	public List<GerenteProjeto> buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		List<GerenteProjeto> gerenteProjetos = null;
		try {
			gerenteProjetos = entityManager.createNamedQuery("GerenteProjeto.pessoa",
					GerenteProjeto.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return gerenteProjetos;
	}
	
}
