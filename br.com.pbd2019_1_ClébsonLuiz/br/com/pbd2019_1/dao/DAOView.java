package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.pbd2019_1.entidade.View;
import br.com.pbd2019_1.exception.DAOException;

public abstract class DAOView<T extends View> {

private EntityManagerFactory entityManagerFactory;
	
	public DAOView() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("banco");
	}
	public EntityManager createEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	public T buscaHQLGenerica(Class<T> classe, String sql) throws DAOException{
		EntityManager entityManager = createEntityManager();
		T t = null;
		try {
			t = entityManager.createQuery(sql, classe).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			t = null;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de buscaSQL no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;
	}
	
	public List<T> buscaListaHQLGenerica(Class<T> classe, String sql) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<T> t = null;
		try {
			t = entityManager.createQuery(sql, classe).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			t = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca lista SQL no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;
	}
	
	
}
