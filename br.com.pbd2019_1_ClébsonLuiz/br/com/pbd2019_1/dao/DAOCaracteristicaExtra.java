package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOCaracteristicaExtra extends DAOGenerico<CaracteristicaExtra>{

	public List<CaracteristicaExtra> buscarPorPessoa(Pessoa pessoa) throws DAOException {
		EntityManager entityManager = createEntityManager();
		List<CaracteristicaExtra> caracteristicas = null;
		try {
			caracteristicas = entityManager.createNamedQuery("Caracteristica.pessoa",
					CaracteristicaExtra.class)
			.setParameter("pessoa", pessoa).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			caracteristicas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return caracteristicas;
	}
	
	
}
