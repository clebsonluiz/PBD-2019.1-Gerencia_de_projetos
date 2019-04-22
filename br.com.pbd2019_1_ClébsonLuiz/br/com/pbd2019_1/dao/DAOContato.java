package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOContato extends DAOGenerico<Contato> {

	public Contato buscarPorPessoa(Pessoa pessoa) throws DAOException {
		EntityManager entityManager = createEntityManager();
		Contato contato = null;
		try {
			contato = entityManager.createNamedQuery("Contato.pessoa", Contato.class)
			.setParameter("pessoa", pessoa).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			contato = null;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return contato;
	}
}
