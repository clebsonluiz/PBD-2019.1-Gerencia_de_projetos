package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.Equipe;
import br.com.pbd2019_1.entidade.MembroEquipe;
import br.com.pbd2019_1.entidade.Pessoa;

public class DAOMembroEquipe extends DAOGenerico<MembroEquipe>{

	public List<MembroEquipe> buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		List<MembroEquipe> membroEquipes = null;
		try {
			membroEquipes = entityManager.createNamedQuery("MembroEquipe.pessoa",
					MembroEquipe.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return membroEquipes;
	}
	
	public List<MembroEquipe> buscarPorEquipe(Equipe equipe) {
		EntityManager entityManager = createEntityManager();
		List<MembroEquipe> membroEquipes = null;
		try {
			membroEquipes = entityManager.createNamedQuery("MembroEquipe.equipe",
					MembroEquipe.class)
			.setParameter("equipe", equipe).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return membroEquipes;
	}
	
}
