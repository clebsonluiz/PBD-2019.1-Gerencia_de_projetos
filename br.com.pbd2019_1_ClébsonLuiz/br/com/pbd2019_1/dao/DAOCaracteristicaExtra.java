package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Pessoa;

public class DAOCaracteristicaExtra extends DAOGenerico<CaracteristicaExtra>{

	public List<CaracteristicaExtra> buscarPorPessoa(Pessoa pessoa) {
		EntityManager entityManager = createEntityManager();
		List<CaracteristicaExtra> caracteristicas = null;
		try {
			caracteristicas = entityManager.createNamedQuery("Caracteristica.pessoa",
					CaracteristicaExtra.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (Exception e) {
		
		} finally {
			entityManager.close();
		}
		return caracteristicas;
	}
	
	
}
