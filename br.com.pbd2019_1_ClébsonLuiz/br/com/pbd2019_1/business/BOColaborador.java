package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOColaborador;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOColaborador extends BOGenerico<Colaborador>{

	public BOColaborador() {
		super(new DAOColaborador(), Colaborador.class);
	}

	protected void validacaoInsercao(Colaborador t) throws BOException{
		if(t == null || t.getPessoa() == null 
				|| t.getProjeto() == null || t.getData_ingresso() == null)
			throw new BOException("Erro ao validar colaborador");
	}
	
	protected void validacaoAtualizacao(Colaborador t) throws BOException {
		if(t == null || t.getPessoa() == null 
				|| t.getProjeto() == null || t.getData_ingresso() == null)
			throw new BOException("Erro ao validar colaborador");
	}
	
	public List<Colaborador> buscarPorPessoa(Pessoa pessoa) throws BOException, DAOException {
		if(pessoa == null || pessoa.getId() <= 0)
			throw new BOException("Erro ao buscar por pessoa");
		return ((DAOColaborador)this.daoT).buscarPorPessoa(pessoa);
	}
	
	public List<Colaborador> buscarPorProjeto(Projeto projeto) throws BOException, DAOException {
		if(projeto == null || projeto.getId() <= 0)
			throw new BOException("Erro ao buscar por projeto");
		return ((DAOColaborador)this.daoT).buscarPorProjeto(projeto);
	}
	
}
