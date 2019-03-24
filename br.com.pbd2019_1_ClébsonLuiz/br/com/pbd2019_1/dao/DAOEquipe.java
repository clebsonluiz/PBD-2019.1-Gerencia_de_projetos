package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Equipe;
import br.com.pbd2019_1.entidade.Projeto;

public class DAOEquipe extends DAOGenerico<Equipe>{

	public Equipe buscarPorProjeto(Projeto projeto) {
		EntityManager entityManager = createEntityManager();
		Equipe equipe = null;
		try {
			equipe = entityManager.createNamedQuery("Equipe.projeto", Equipe.class)
			.setParameter("projeto", projeto).getSingleResult();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return equipe;
	}
	
}
