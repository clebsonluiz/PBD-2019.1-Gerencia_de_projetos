package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOProjeto;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOProjeto extends BOGenerico<Projeto>{

	public BOProjeto() {
		super(new DAOProjeto(), Projeto.class);
	}
	
	protected void validacaoInsercao(Projeto t) throws BOException {
		if(t.getNome() == null || t.getNome().trim().equals(""))
			throw new BOException("Campo nome vazio");
		if(t.getData_inicio() == null || t.getData_fim() == null)
			throw new BOException("Campos de data Vazios");
		if(t.getPessoa() == null || t.getPessoa().getId() <= 0)
			throw new BOException("Erro, projeto não contem uma pessoa associada");
	}
	
	protected void validacaoAtualizacao(Projeto t) throws BOException {
		validacaoInsercao(t);
	}
	
	public List<Projeto> buscarPorPessoa(Pessoa pessoa) throws BOException, DAOException {
		if(pessoa == null || pessoa.getId() <= 0)
			throw new BOException("Erro ao buscar Projetos por pessoa");
		return ((DAOProjeto)this.daoT).buscarPorPessoa(pessoa);
	}
	
	public int diferenca_Datas(java.sql.Date data1, java.sql.Date data2) throws BOException, DAOException
	{
		if(data1 == null || data2 == null)
			throw new BOException("Alguma das datas estão vazias");
		return ((DAOProjeto)this.daoT).diferenca_Datas(data1, data2);
	}
	
	public int diferenca_Datas(Projeto projeto) throws BOException, DAOException
	{
		if(projeto == null) 
			throw new BOException("Não pode ser valor null");
		return diferenca_Datas(projeto.getData_inicio(), projeto.getData_fim());
	}
	
	public int andamento_Projeto(Projeto projeto)  throws BOException, DAOException { 
		if(projeto == null) 
			throw new BOException("Não pode ser valor null");
		Double double_value = ((DAOProjeto)this.daoT).andamento_Projeto(projeto);
		
		int porcentagem = double_value.intValue();
		
		if(porcentagem >= 100)	porcentagem = 100;
		return porcentagem;
	}
}
