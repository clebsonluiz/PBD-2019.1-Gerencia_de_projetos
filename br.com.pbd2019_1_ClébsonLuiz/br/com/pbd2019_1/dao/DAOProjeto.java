package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;

public class DAOProjeto extends DAOGenerico<Projeto>{

	public Projeto buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		Projeto projeto = null;
		try {
			projeto = entityManager.createNamedQuery("Projeto.pessoa", Projeto.class)
			.setParameter("pessoa", pessoa).getSingleResult();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return projeto;
	}
	
}
