package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Pessoa;

public class DAOContato extends DAOGenerico<Contato> {

	public Contato buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		Contato contato = null;
		try {
			contato = entityManager.createNamedQuery("Contato.pessoa", Contato.class)
			.setParameter("pessoa", pessoa).getSingleResult();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return contato;
	}
}
