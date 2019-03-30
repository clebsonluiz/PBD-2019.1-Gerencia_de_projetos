package br.com.pbd2019_1.business;

import br.com.pbd2019_1.dao.DAOPessoa;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Usuario;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.DocumentoUtil;

public class BOPessoa extends BOGenerico<Pessoa>{

	public BOPessoa() {
		super(new DAOPessoa(), Pessoa.class);
	}
	
	protected void validacaoInsercao(Pessoa t) throws BOException{
		try {
			if(t == null || t.getNome() == null || t.getNome().trim().equals("")
				|| t.getNome().split(" ").length < 2 || t.getData_nascimento() == null
				|| t.getSexo() == null || t.getSexo().trim().equals("")
				|| t.getUsuario() == null)
				throw new BOException("Erro ao validar pessoa");
			if(buscarPorCPF(t.getCpf()))
				throw new BOException("Já existe uma pessoa de mesmo CPF");
		} catch (DAOException e) {
			throw new BOException(e.getMessage());
		}
	}

	protected void validacaoAtualizacao(Pessoa t) throws BOException{
		if(t == null || t.getId() <= 0 || t.getNome() == null || t.getNome().trim().equals("")
				|| t.getNome().split(" ").length < 2 || t.getData_nascimento() == null
				|| t.getSexo() == null || t.getSexo().trim().equals("")
				|| t.getUsuario() == null)
				throw new BOException("Erro ao validar pessoa");
		if(!DocumentoUtil.isCPF(t.getCpf()))
			throw new BOException("Não é um cpf valido");
	}
	
	public boolean buscarPorCPF(String cpf) throws BOException, DAOException {
		if(!DocumentoUtil.isCPF(cpf))
			throw new BOException("Não é um cpf valido");
		return ((DAOPessoa)this.daoT).buscarPorCPF(cpf);
	}
	
	public Pessoa buscarPorUsuario(Usuario usuario) throws BOException, DAOException {
		if(usuario == null || usuario.getId() <= 0)
			throw new BOException("Erro ao buscar pessoa");
		return ((DAOPessoa)this.daoT).buscarPorUsuario(usuario);
	}
	
}
