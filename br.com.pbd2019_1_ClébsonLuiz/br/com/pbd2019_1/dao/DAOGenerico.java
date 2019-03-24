package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pbd2019_1.entidade.Entidade;

public abstract class DAOGenerico <T extends Entidade> {

	private EntityManagerFactory entityManagerFactory;
	
	public EntityManager createEntityManager(){
		entityManagerFactory = Persistence.createEntityManagerFactory("banco");
		return entityManagerFactory.createEntityManager();
	}
	
	public T inserir(T t){
		EntityManager entityManager = createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return t;
	}

	public T atualizar(T t){
		EntityManager entityManager = createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return t;
	}

	public T buscar(Class<T> classe, int id) {
		EntityManager entityManager = createEntityManager();
		T t = null;
		try {
		
		t = entityManager.find(classe, id);
		
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return t;
	}

	public void deletar(T t){
		EntityManager entityManager = createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}
	
}
