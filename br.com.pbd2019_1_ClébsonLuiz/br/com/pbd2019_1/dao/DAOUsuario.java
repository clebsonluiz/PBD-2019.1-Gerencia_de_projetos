package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pbd2019_1.entidade.Usuario;
import br.com.pbd2019_1.exception.DAOException;

public class DAOUsuario extends DAOGenerico<Usuario>{

	public boolean buscarPorLogin(String login) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Usuario.buscaLogin")
			.setParameter("login", "'%"+login+"%'").getMaxResults();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return (quantidade > 0)? true : false;
	}
	
	public Usuario buscarUsuario(String login, String senha) throws DAOException {
		EntityManager entityManager = createEntityManager();
		Usuario usuario = null;
		try {
			Query query = 
					entityManager.createNamedQuery("Usuario.buscarUsuario",	Usuario.class);
			query.setParameter("login", "'%"+login+"%'");
			query.setParameter("senha", "'%"+senha+"%'");
			usuario = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return usuario;
	}
	
}
