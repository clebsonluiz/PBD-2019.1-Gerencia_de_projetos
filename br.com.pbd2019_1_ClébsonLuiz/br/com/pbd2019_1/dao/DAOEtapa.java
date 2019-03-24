package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Projeto;

public class DAOEtapa extends DAOGenerico<Etapa>{

	public List<Etapa> buscarPorProjeto(Projeto projeto) {
		EntityManager entityManager = createEntityManager();
		List<Etapa> etapas = null;
		try {
			etapas = entityManager.createNamedQuery("Etapa.projeto", Etapa.class)
			.setParameter("projeto", projeto).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return etapas;
	}
	
}
