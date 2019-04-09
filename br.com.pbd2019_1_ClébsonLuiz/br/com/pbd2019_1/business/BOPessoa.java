package br.com.pbd2019_1.business;

import br.com.pbd2019_1.dao.DAOPessoa;
import br.com.pbd2019_1.entidade.Pessoa;
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
				||t.getUser_login() == null || t.getUser_senha() == null || t.getUser_type() == null
				|| t.getUser_login().trim().equals("") || t.getUser_login().trim().equals("") 
				|| t.getUser_login().trim().equals(""))
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
				|| t.getUser_login() == null || t.getUser_senha() == null || t.getUser_type() == null
				|| t.getUser_login().trim().equals("") || t.getUser_login().trim().equals("") 
				|| t.getUser_login().trim().equals(""))
				throw new BOException("Erro ao validar pessoa, algum campo está vazio");
		if(!DocumentoUtil.isCPF(t.getCpf()))
			throw new BOException("Não é um cpf valido");
	}
	
	public boolean buscarPorCPF(String cpf) throws BOException, DAOException {
		if(!DocumentoUtil.isCPF(cpf))
			throw new BOException("Não é um cpf valido");
		return ((DAOPessoa)this.daoT).buscarPorCPF(cpf);
	}

	
	public boolean buscarPorLogin(String login) throws BOException, DAOException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		if(((DAOPessoa)this.daoT).buscarPorUsuario(login))
			throw new BOException("Já Existe um usuario de mesmo login");
		return false;
	}
	
	public Pessoa buscarUsuario(String login, String senha) throws BOException, DAOException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		if(senha == null || senha.trim().equals(""))
			throw new BOException("Campo senha vazio");
		return ((DAOPessoa)this.daoT).buscarPorUsuario(login, senha);
	}
	
}
