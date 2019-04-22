package br.com.pbd2019_1.business;

import br.com.pbd2019_1.dao.DAOPessoa;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.UserUtil;

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
				|| t.getUser_login().trim().equals("") || t.getUser_senha().trim().equals("") 
				|| t.getUser_login().trim().equals(""))
				throw new BOException("Erro ao validar pessoa");
			
			if(!(t.getUser_senha().length() > 5 && t.getUser_senha().length() < 11))
				throw new BOException("Numero de caracteres da senha deve ser maior que 5 e menor que 11");
			
			if(t.getUser_login().contains(UserUtil.TypeUtil.TAG_ADMIN))
				t.setUser_type(Pessoa.ADMIN_USER);
			if(t.getUser_login().contains(UserUtil.TypeUtil.TAG_SUPER))
				t.setUser_type(Pessoa.SUPER_USER);
			
			t.setUser_login(UserUtil.TypeUtil.removerCaracteresEspeciais(t.getUser_login()));
			
			if(buscarPorCPF(t.getCpf()))
				throw new BOException("Já existe uma pessoa de mesmo CPF");
			
			t.setCpf(UserUtil.DocumentoUtil.removerCaracteresEspeciais(t.getCpf()));
			
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
		if(!UserUtil.DocumentoUtil.isCPF(t.getCpf()))
			throw new BOException("Não é um cpf valido");
		
		t.setCpf(UserUtil.DocumentoUtil.removerCaracteresEspeciais(t.getCpf()));
	}
	
	public boolean buscarPorCPF(String cpf) throws BOException, DAOException {
		if(!UserUtil.DocumentoUtil.isCPF(cpf))
			throw new BOException("Não é um cpf valido");
		
		cpf = UserUtil.DocumentoUtil.removerCaracteresEspeciais(cpf);
		
		return ((DAOPessoa)this.daoT).buscarPorCPF(cpf);
	}

	
	public boolean buscarPorLogin(String login) throws BOException, DAOException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		login = UserUtil.TypeUtil.removerCaracteresEspeciais(login);
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
