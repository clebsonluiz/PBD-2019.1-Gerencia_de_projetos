package br.com.pbd2019_1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubEtapaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class DAOSubEtapa extends DAOGenerico<SubEtapa>{

	public List<SubEtapa> getListPorSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubEtapa.class,
				"SubEtapa.colaborador",
				"subEtapaColaborador",
				subEtapaColaborador);
	}
	
	public List<SubEtapa> getListPorEtapa(Etapa etapa) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				SubEtapa.class,
				"SubEtapa.etapa",
				"etapa",
				etapa);
	}
	
	public SubEtapa getPorSubEtapaColaborador(SubEtapaColaborador subEtapaColaborador) throws DAOException
	{
		return buscaNamedQueryGenericaFK(
				SubEtapa.class,
				"SubEtapa.colaborador",
				"subEtapaColaborador",
				subEtapaColaborador);
	}
	
	public float recalcularPorcentagem(SubEtapa t) throws DAOException {
		EntityManager entityManager = createEntityManager();

		float porcent = 0;
		try {
			TypedQuery<Float> query = entityManager.
					createNamedQuery("SubEtapa.recalcula_porcentagem", Float.class);
			query.setParameter("sub_etapa_id", t.getId());
			porcent = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return porcent;
	}
	
}
