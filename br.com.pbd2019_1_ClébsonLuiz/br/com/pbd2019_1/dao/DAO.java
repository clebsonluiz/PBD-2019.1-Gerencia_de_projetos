package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.exception.DAOException;

public abstract class DAO {

	private EntityManagerFactory entityManagerFactory;
	
	public DAO() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("banco");
	}
	public EntityManager createEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	public <T extends Entidade> List<T> buscarAll(Class<T> classe) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<T> t = null;
		try {
			t = entityManager.
					createQuery("from "+classe.getSimpleName()+ 
							" entidade where entidade.ativado = true ORDER BY entidade.id ASC",
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
	
	
	public List<Object[]> buscaSQLGenerica(String sql) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<Object[]> t = null;
		try {
			
			Session session = entityManager.unwrap(Session.class);
			
			@SuppressWarnings({ "deprecation", "unchecked" })
			NativeQuery<Object[]> query = session.createSQLQuery(sql);
			
			t = query.list();
			
		} 
		
		catch (Exception e) 
		{
			if(e.getMessage().equals("org.hibernate.MappingException: No Dialect mapping for JDBC type: 2002"))
			{
				e.printStackTrace();
				throw new DAOException("Mapeamento Errado");
			}
			if(e.getMessage().equals("org.hibernate.exception.GenericJDBCException: could not extract ResultSet"))
			{
				e.printStackTrace();
				throw new DAOException("Sem resultados");
			}
			e.printStackTrace();
			throw new DAOException("Erro de busca lista SQL no banco de dados");
		} 
		finally 
		{
			entityManager.close();
		}
		return t;
	}
	
	public <T extends Entidade> T buscaHQLGenerica(Class<T> classe, String sql) throws DAOException{
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
	
	public <T extends Entidade> List<T> buscaListaHQLGenerica(Class<T> classe, String sql) throws DAOException{
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
	
	public <T extends Entidade> List<T> buscaNamedQueryGenericaListFK(Class<T> classe, String namedQuery, String parameter, Object obj) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<T> t = null;
		try {
			
			TypedQuery<T> query =  entityManager.createNamedQuery(namedQuery, classe);
			query.setParameter(parameter, obj);
			t = query.getResultList();
		
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
	
	public <T extends Entidade> T buscaNamedQueryGenericaFK(Class<T> classe, String namedQuery, String parameter, Object obj) throws DAOException{
		EntityManager entityManager = createEntityManager();
		T t = null;
		try {
			
			TypedQuery<T> query =  entityManager.createNamedQuery(namedQuery, classe);
			query.setParameter(parameter, obj);
			t = query.getSingleResult();
		
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro de busca lista SQL no banco de dados");
		} finally {
			entityManager.close();
		}
		return t;
	}
}
