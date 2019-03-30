package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;

public class DAOColaborador extends DAOGenerico<Colaborador>{

	public List<Colaborador> buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		List<Colaborador> colaboradores = null;
		try {
			colaboradores = entityManager.createNamedQuery("Colaborador.pessoa",
					Colaborador.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return colaboradores;
	}
	
	public List<Colaborador> buscarPorProjeto(Projeto projeto) {
		EntityManager entityManager = createEntityManager();
		List<Colaborador> colaboradores = null;
		try {
			colaboradores = entityManager.createNamedQuery("Colaborador.projeto",
					Colaborador.class)
			.setParameter("projeto", projeto).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return colaboradores;
	}
	
}
