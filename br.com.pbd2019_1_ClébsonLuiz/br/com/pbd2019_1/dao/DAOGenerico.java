package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.exception.DAOException;

public abstract class DAOGenerico <T extends Entidade> extends DAO{

	public T inserir(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			entityManager.getTransaction().rollback();
			
			throw new DAOException("Erro de inserção no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;
	}

	public T atualizar(T t)throws DAOException{
		EntityManager entityManager = createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			entityManager.getTransaction().rollback();
			
			throw new DAOException("Erro de atualização no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;
	}

	public T buscar(Class<T> classe, int id) throws DAOException{
		EntityManager entityManager = createEntityManager();
		T t = null;
		try {
		
		t = entityManager.find(classe, id);
		
		} catch (NoResultException e) {
			e.printStackTrace();
			t = null;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;
	}

	public void remover(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		try {
			t.setAtivado(false); //Desativa a entidade
			entityManager.getTransaction().begin();
			entityManager.merge(t); // Ao invéz de Remove eu dou um merge
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			entityManager.getTransaction().rollback();
			
			throw new DAOException("Erro de remoção no banco de dados");
			
		} finally {
			entityManager.close();
		}
	}
	
	public void deletar(T t) throws DAOException{
		EntityManager entityManager = createEntityManager();
		try {

			entityManager.getTransaction().begin();
			entityManager.remove(t); // Ao invéz de Remove eu dou um merge
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			entityManager.getTransaction().rollback();
			
			throw new DAOException("Erro de remoção no banco de dados");
			
		} finally {
			entityManager.close();
		}
	}
}
