package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.GerenteProjeto;
import br.com.pbd2019_1.entidade.Projeto;

public class DAOProjeto extends DAOGenerico<Projeto>{

	public Projeto buscarPorGerente(GerenteProjeto gerenteProjeto) {
		EntityManager entityManager = createEntityManager();
		Projeto projeto = null;
		try {
			projeto = entityManager.createNamedQuery("Projeto.gerente", Projeto.class)
			.setParameter("gerente", gerenteProjeto).getSingleResult();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return projeto;
	}
	
}
