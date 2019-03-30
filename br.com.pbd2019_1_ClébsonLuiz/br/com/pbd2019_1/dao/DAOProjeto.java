package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.DAOException;

public class DAOProjeto extends DAOGenerico<Projeto>{

	public List<Projeto> buscarPorPessoa(Pessoa pessoa) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<Projeto> projetos = null;
		try {
			projetos = entityManager.createNamedQuery("Projeto.pessoa", Projeto.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return projetos;
	}
	
}
