package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.DAOException;

public class DAOColaborador extends DAOGenerico<Colaborador>{

	public List<Colaborador> buscarPorPessoa(Pessoa pessoa) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Colaborador> colaboradores = null;
		try {
			colaboradores = entityManager.createNamedQuery("Colaborador.pessoa",
					Colaborador.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			colaboradores = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return colaboradores;
	}
	
	public List<Colaborador> buscarPorProjeto(Projeto projeto) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Colaborador> colaboradores = null;
		try {
			colaboradores = entityManager.createNamedQuery("Colaborador.projeto",
					Colaborador.class)
			.setParameter("projeto", projeto).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return colaboradores;
	}
	
}
