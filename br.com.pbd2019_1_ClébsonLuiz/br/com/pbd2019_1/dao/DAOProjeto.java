package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.DAOException;

public class DAOProjeto extends DAOGenerico<Projeto>{

	public List<Projeto> buscarPorPessoa(Pessoa pessoa) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Projeto> projetos = null;
		try {
			projetos = entityManager.createNamedQuery("Projeto.pessoa", Projeto.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			projetos = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return projetos;
	}
	
	public int diferenca_Datas(java.sql.Date data1, java.sql.Date data2) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int diferenca;
		try {
			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("intervalo_data");
			query.registerStoredProcedureParameter(0, java.sql.Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(1, java.sql.Date.class, ParameterMode.IN);
			query.setParameter(0, data1);
			query.setParameter(1, data2);

			diferenca = (int) query.getSingleResult();
			
		} catch (NoResultException e) {
			e.printStackTrace();
			diferenca = 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return diferenca;
	}
	
	public double andamento_Projeto(Projeto projeto) throws DAOException {
		EntityManager entityManager = createEntityManager();
		double diferenca;
		try {
			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("andamento_projeto");
			query.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
			query.setParameter(0, projeto.getId());

			diferenca = (double) query.getSingleResult();
			
		} catch (NoResultException e) {
			e.printStackTrace();
			diferenca = 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return diferenca;
	}
}
