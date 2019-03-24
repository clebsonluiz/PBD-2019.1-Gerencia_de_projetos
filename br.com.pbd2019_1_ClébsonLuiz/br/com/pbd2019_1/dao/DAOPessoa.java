package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Pessoa;

public class DAOPessoa extends DAOGenerico<Pessoa>{

	public boolean buscarPorCPF(String cpf) {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.cpf")
			.setParameter("cpf", cpf).getMaxResults();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return (quantidade > 0)? true : false;
	}
	
}
