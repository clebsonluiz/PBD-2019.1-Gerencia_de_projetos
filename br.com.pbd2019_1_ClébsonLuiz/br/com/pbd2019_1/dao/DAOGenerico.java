package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.exception.DAOException;

public abstract class DAOGenerico <T extends Entidade> {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco");
	
	public EntityManager createEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
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

	public void deletar(T t) throws DAOException{
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
	
	
	public List<T> buscarAll(Class<T> classe) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<T> t = null;
		try {
			t = entityManager.
					createQuery("from "+classe.getSimpleName()+ 
							" entidade where entidade.ativado = true",
							classe).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			t = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro ao buscar todos "+classe.getSimpleName());
		} finally {
			entityManager.close();
		}
		return t;
	}
	
	public T buscaSQLGenerica(Class<T> classe, String sql) throws DAOException{
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
	
	public List<T> buscaListaSQLGenerica(Class<T> classe, String sql) throws DAOException{
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
	
	public List<Object[]> buscaListaSQLGenerica(String sql) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<Object[]> t = null;
		try {
			t = entityManager.createQuery(sql, Object[].class).getResultList();
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
