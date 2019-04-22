package br.com.pbd2019_1.business;

import br.com.pbd2019_1.dao.DAOContato;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.UserUtil;

public class BOContato extends BOGenerico<Contato>{

	public BOContato() {
		super(new DAOContato(), Contato.class);
	}

	protected void validacaoInsercao(Contato t) throws BOException{
		if(t == null || t.getPessoa() == null)
			throw new BOException("Erro ao validar contato");
		if(!UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getCelular()).trim().equals(""))
			if(!UserUtil.ContatoUtil.isCelular(t.getCelular()))
				throw new BOException("Numero Celular não é válido");
		if(!UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getTelefone()).trim().equals(""))
			if(!UserUtil.ContatoUtil.isTelefone(t.getTelefone()))
				throw new BOException("Numero de telefone não é válido");
		t.setCelular(UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getCelular()));
		t.setTelefone(UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getTelefone()));
	}

	protected void validacaoAtualizacao(Contato t) throws BOException{
		if(t.getPessoa() == null)
			throw new BOException("Erro ao validar contato");
		if(!UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getCelular()).trim().equals(""))
			if(!UserUtil.ContatoUtil.isCelular(t.getCelular()))
				throw new BOException("Numero Celular não é válido");
		if(!UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getTelefone()).trim().equals(""))
			if(!UserUtil.ContatoUtil.isTelefone(t.getTelefone()))
				throw new BOException("Numero de telefone não é válido");
		t.setCelular(UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getCelular()));
		t.setTelefone(UserUtil.ContatoUtil.removerCaracteresEspeciais(t.getTelefone()));
	}

	public Contato buscarPorPessoa(Pessoa pessoa) throws BOException, DAOException {
		if(pessoa == null || pessoa.getId() <= 0)
			throw new BOException("Erro ao buscar contato por pessoa");
		return ((DAOContato)this.daoT).buscarPorPessoa(pessoa);
	}
	
}
