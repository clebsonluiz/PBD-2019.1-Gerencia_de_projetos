package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

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
			quantidade = entityManager.createNamedQuery("Pessoa.cpf", Integer.class)
			.setParameter("cpf", cpf).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorCPFID(String cpf, int id) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.cpfID", Integer.class)
			.setParameter("cpf", cpf).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorUsuario(String login) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.buscarUsuarioLogin", Integer.class)
			.setParameter("login", login).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorUsuarioID(String login, int id) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.buscarUsuarioLoginID", Integer.class)
			.setParameter("login", login).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
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
	
	public List<Pessoa> buscarPessoasDiferentes(int id) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<Pessoa> pessoas = null;
		try {
			pessoas = entityManager.
					createNamedQuery("Pessoa.buscarPessoas", Pessoa.class)
					.setParameter("id", id).getResultList();
					
		} catch (NoResultException e) {
			e.printStackTrace();
			pessoas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro ao buscar todos as Pessoas");
		} finally {
			entityManager.close();
		}
		return pessoas;
	}
	
}
