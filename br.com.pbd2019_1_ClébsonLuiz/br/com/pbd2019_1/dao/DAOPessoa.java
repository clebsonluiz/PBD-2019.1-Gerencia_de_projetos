package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Usuario;
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
	
	public Pessoa buscarPorUsuario(Usuario usuario) throws DAOException {
		EntityManager entityManager = createEntityManager();
		Pessoa pessoa = null;
		try {
			pessoa = entityManager.createNamedQuery("Pessoa.buscarUsuario", Pessoa.class)
			.setParameter("usuario", usuario).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return pessoa;
	}
	
}
