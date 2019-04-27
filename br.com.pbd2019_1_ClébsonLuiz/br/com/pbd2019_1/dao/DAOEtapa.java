package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.DAOException;

public class DAOEtapa extends DAOGenerico<Etapa>{

	public List<Etapa> buscarPorProjeto(Projeto projeto) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Etapa> etapas = null;
		try {
			etapas = entityManager.createNamedQuery("Etapa.projeto", Etapa.class)
			.setParameter("projeto", projeto).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			etapas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return etapas;
	}
	
	public float recalcularPorcentagem(Etapa t) throws DAOException {
		EntityManager entityManager = createEntityManager();

		float porcent = 0;
		try {
			TypedQuery<Float> query = entityManager.
					createNamedQuery("Etapa.recalcula_porcentagem", Float.class);
			query.setParameter("etapa_id", t.getId());
			porcent = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return porcent;
	}
	

}
