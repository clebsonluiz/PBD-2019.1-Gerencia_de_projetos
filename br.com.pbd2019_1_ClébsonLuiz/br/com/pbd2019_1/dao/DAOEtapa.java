package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;

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
	
/*	public float recalcularPorcentagem(Etapa t) throws DAOException {
		EntityManager entityManager = createEntityManager();

		float porcent = 0;
		try {
			TypedQuery<Float> query = entityManager.
					createNamedQuery("Etapa.recalcula", Float.class);
			query.setParameter("etapa_id", t.getId());
			porcent = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return porcent;
	}*/
	
/*	public float recalcularPorcentagem(Etapa t) throws DAOException {
		EntityManager entityManager = createEntityManager();

		float porcent = 0;
		long totalConcluida = 0;
		long totalTarefa = 0;
		
		try {
			TypedQuery<Long> query1 = entityManager.
					createNamedQuery("Etapa.totalTarefaConcluida", Long.class);
			query1.setParameter("etapa_fk", t.getId());
			
			TypedQuery<Long> query2 = entityManager.
					createNamedQuery("Etapa.totalTarefa", Long.class);
			query2.setParameter("etapa_fk", t.getId());
			
			totalConcluida = query1.getSingleResult();
			totalTarefa =  query2.getSingleResult();
			
			if(totalTarefa <= 0)
				totalTarefa = 1;
			
			porcent = (float)((float)totalConcluida/(float)totalTarefa)*100;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return porcent;
	}*/
	
	@SuppressWarnings("deprecation")
	public float recalcularPorcentagem(Etapa t) throws DAOException {
		EntityManager entityManager = createEntityManager();

		float porcent = 0;
		try {
			
			 /*Query query = entityManager.
					createNativeQuery("select" + 
							" (cast(finalizadas as FLOAT)/cast(total as FLOAT))*100 " + 
							" as porcentagem " + 
							" FROM ( select SUM(CASE WHEN t.concluida = true THEN 1 ELSE 0 END) as finalizadas, " + 
							" COUNT(*) as total from Tarefa as t where t.etapa.id = :etapa_fk " + 
							" and t.ativado = true) as alias_tabela");
			query.setParameter("etapa_fk", t.getId());
			
			porcent = (Float)query.getSingleResult();
			*/	
			Session session = entityManager.unwrap(Session.class);
			Object o = session.createSQLQuery("select" + 
					" (cast(finalizadas as FLOAT)/cast(total as FLOAT))*100 " + 
					" as porcentagem " + 
					" FROM ( select SUM(CASE WHEN t.concluida = true THEN 1 ELSE 0 END) as finalizadas, " + 
					" COUNT(*) as total from Tarefa as t where t.etapa_id = "+ t.getId()+ " "+ 
					" and t.ativado = true) as alias_tabela")
			.getSingleResult();
			
			porcent = ((Double)o).floatValue();
			
		} catch (NoResultException e) {
			e.printStackTrace();
			porcent = 0;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return porcent;
	}
}
