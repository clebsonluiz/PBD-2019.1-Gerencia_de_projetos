package br.com.pbd2019_1.dao;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Usuario;

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
	
	public Pessoa buscarPorUsuario(Usuario usuario) {
		EntityManager entityManager = createEntityManager();
		Pessoa pessoa = null;
		try {
			pessoa = entityManager.createNamedQuery("Pessoa.buscarUsuario", Pessoa.class)
			.setParameter("usuario", usuario).getSingleResult();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return pessoa;
	}
	
}
