package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOEtapa;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOEtapa extends BOGenerico<Etapa>{

	public BOEtapa() {
		super(new DAOEtapa(), Etapa.class);
	}

	@Override
	public void validacaoInsercao(Etapa t) throws BOException {
		if(t == null || t.getProjeto() == null)
			throw new BOException("Erro na valida��o da Etapa");
		if(t.getNome() == null || t.getNome().trim().equals(""))
			throw new BOException("Campo nome n�o pode estar vazio");
	}

	@Override
	public void validacaoAtualizacao(Etapa t) throws BOException {
		if(t.getProjeto() == null)
			throw new BOException("Erro na valida��o da Etapa");
		if(t.getNome() == null || t.getNome().trim().equals(""))
			throw new BOException("Campo nome n�o pode estar vazio");
	}

	public List<Etapa> buscarPorProjeto(Projeto projeto) throws BOException, DAOException {
		if(projeto == null || projeto.getId() <= 0)
			throw new BOException("Erro ao buscar a Etapa por projeto");
		return ((DAOEtapa)this.daoT).buscarPorProjeto(projeto);
	}

	
	public int recalcularPorcentagem(Etapa t) throws BOException, DAOException {
		if(t == null || t.getId() <= 0)
			throw new BOException("Erro ao recalcular");
		return (int) ((DAOEtapa)this.daoT).recalcularPorcentagem(t);
	}
	
}
