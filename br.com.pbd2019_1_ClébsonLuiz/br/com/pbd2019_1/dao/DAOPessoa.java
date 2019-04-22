package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOPessoa extends DAOGenerico<Pessoa>{

	public boolean buscarPorCPF(String cpf) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.cpf")
			.setParameter("cpf", cpf).getMaxResults();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorUsuario(String login) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.buscarUsuarioLogin", Pessoa.class)
			.setParameter("login", login).getMaxResults();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return (quantidade > 0)? true : false;
	}
	
	public Pessoa buscarPorUsuario(String login, String senha) throws DAOException {
		EntityManager entityManager = createEntityManager();
		Pessoa pessoa = null;
		try {
			
			TypedQuery<Pessoa> query = entityManager.
					createNamedQuery("Pessoa.buscarUsuario", Pessoa.class);
			
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			
			pessoa = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			pessoa = null;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return pessoa;
	}
	
}
