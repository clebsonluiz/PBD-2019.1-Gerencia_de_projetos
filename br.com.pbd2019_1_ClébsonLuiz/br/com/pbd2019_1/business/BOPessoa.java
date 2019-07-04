package br.com.pbd2019_1.business;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.pbd2019_1.dao.DAOPessoa;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.SecurityUtil;
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
				|| t.getUser_senha() == null || t.getUser_type() == null
				|| t.getUser_senha().trim().equals("") 
				)
				throw new BOException("Erro ao validar pessoa");
			
			if(!(t.getUser_senha().length() > 6 && t.getUser_senha().length() < 11))
				throw new BOException("Numero de caracteres da senha deve estar entre 6 e 11");
			
			/*if(t.getUser_login().contains(UserUtil.TypeUtil.TAG_COMUM))
				t.setUser_type(Pessoa.COMUM_USER);
			if(t.getUser_login().contains(UserUtil.TypeUtil.TAG_ADMIN))
				t.setUser_type(Pessoa.ADMIN_USER);
			if(t.getUser_login().contains(UserUtil.TypeUtil.TAG_SUPER))
				t.setUser_type(Pessoa.SUPER_USER);*/
			
//			t.setUser_login(UserUtil.TypeUtil.removerCaracteresEspeciais(t.getUser_login()));
			
			t.setCpf(UserUtil.DocumentoUtil.removerCaracteresEspeciais(t.getCpf()));
			
			if(buscarPorCPF(t.getCpf()))
				throw new BOException("Já existe uma pessoa de mesmo CPF");
//			if(buscarPorLogin(t.getUser_login()))
//				throw new BOException("Já existe uma pessoa de mesmo login");
			
			t.setUser_senha(SecurityUtil.criptografarSHA2(t.getUser_senha()));
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DAOException e) {
			throw new BOException(e.getMessage());
		}
	}

	protected void validacaoAtualizacao(Pessoa t) throws BOException{
		try 
		{
			if(t == null || t.getId() <= 0 || t.getNome() == null || t.getNome().trim().equals("")
					|| t.getNome().split(" ").length < 2 || t.getData_nascimento() == null
					|| t.getSexo() == null || t.getSexo().trim().equals("")
					|| t.getUser_senha() == null || t.getUser_type() == null
					)
				throw new BOException("Erro ao validar pessoa, algum campo está vazio");

			if(!UserUtil.DocumentoUtil.isCPF(t.getCpf()))
				throw new BOException("Não é um cpf valido");
			if(!buscarPorLoginSenhaID(t.getCpf(), t.getUser_senha(), t.getId()))
			{
				if(!(t.getUser_senha().length() > 6 && t.getUser_senha().length() < 11))
					throw new BOException("Numero de caracteres da senha deve ser maior que 5 e menor que 11");
				t.setUser_senha(SecurityUtil.criptografarSHA2(t.getUser_senha()));
			}			

			t.setCpf(UserUtil.DocumentoUtil.removerCaracteresEspeciais(t.getCpf()));

			if(buscarPorCPFID(t.getCpf(), t.getId()))
				throw new BOException("Já existe uma pessoa de mesmo CPF");
//			if(buscarPorLoginID(t.getUser_login(), t.getId()))
//				throw new BOException("Já existe uma pessoa de mesmo Login");
			
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException | DAOException e) 
		{
			throw new BOException(e.getMessage());
		}
	}
	
	public boolean buscarPorCPF(String cpf) throws BOException, DAOException {
		if(!UserUtil.DocumentoUtil.isCPF(cpf))
			throw new BOException("Não é um cpf valido");
		
		cpf = UserUtil.DocumentoUtil.removerCaracteresEspeciais(cpf);
		
		return ((DAOPessoa)this.daoT).buscarPorCPF(cpf);
	}
	
	public boolean buscarPorCPFID(String cpf, int id) throws BOException, DAOException {
		if(!UserUtil.DocumentoUtil.isCPF(cpf))
			throw new BOException("Não é um cpf valido");
		
		cpf = UserUtil.DocumentoUtil.removerCaracteresEspeciais(cpf);
		
		return ((DAOPessoa)this.daoT).buscarPorCPFID(cpf, id);
	}

	
	public boolean buscarPorLogin(String login) throws BOException, DAOException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		login = UserUtil.TypeUtil.removerCaracteresEspeciais(login);
		if(((DAOPessoa)this.daoT).buscarPorUsuario(login))
			throw new BOException("Já Existe um usuario de mesmo login");
		return false;
	}
	
	public boolean buscarPorLoginID(String login, int id) throws BOException, DAOException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		login = UserUtil.TypeUtil.removerCaracteresEspeciais(login);
		if(((DAOPessoa)this.daoT).buscarPorUsuarioID(login, id))
			throw new BOException("Já Existe um usuario de mesmo login");
		return false;
	}
	
	public Pessoa buscarUsuario(String login, String senha) throws BOException, DAOException, NoSuchAlgorithmException, UnsupportedEncodingException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		if(senha == null || senha.trim().equals(""))
			throw new BOException("Campo senha vazio");
		return ((DAOPessoa)this.daoT).buscarPorUsuario(login, SecurityUtil.criptografarSHA2(senha));
	}
	
/*	public int[] buscarRelacaoTarefas(Pessoa pessoa) throws BOException, DAOException
	{
		List<Object[]> list = ((DAOPessoa)this.daoT).buscaSQLGenerica("select * from tarefas_pessoa("+ pessoa.getId() +")");
		
		Object[] obj = list.get(0);
		
		int tarefas_finalizadas = (int) obj[0];
		int tarefas_total = (int) obj[1];
		
		return new int[] { tarefas_finalizadas , tarefas_total};
	}*/
	
	public List<Pessoa> buscarPessoasDiferentesDe(Pessoa pessoa) throws BOException, DAOException
	{
		if(pessoa == null || pessoa.getId() < 0)
			throw new BOException("Erro ao buscar pessoas");
		List<Pessoa> list = ((DAOPessoa)this.daoT).buscarPessoasDiferentes(pessoa.getId());
		return list;
	}
	
	public List<Pessoa> buscarPessoasEspecificarDiferentesDe(
			Pessoa pessoa,
			String nome,
			String cpf,
			String disponibilidade
			) throws BOException, DAOException
	{
		if(pessoa == null || pessoa.getId() < 0)
			throw new BOException("Erro ao buscar pessoas");
		List<Pessoa> list = ((DAOPessoa)this.daoT).buscarPessoasDiferentes(pessoa.getId(), nome, cpf, disponibilidade);
		return list;
	}
	
	public int[] buscarDesempenhoTarefas(Pessoa pessoa) throws BOException, DAOException
	{
		if(pessoa == null || pessoa.getId() <= 0)
			throw new BOException("Erro ao desenpenho da pessoa");
		
		return ((DAOPessoa)this.daoT).buscarTotalTarefasPessoa(pessoa.getId());
	}
	
	public int[] buscarDesempenhoEtapas(Pessoa pessoa) throws BOException, DAOException
	{
		if(pessoa == null || pessoa.getId() <= 0)
			throw new BOException("Erro ao desenpenho da pessoa");
		return ((DAOPessoa)this.daoT).buscarTotalEtapasPessoa(pessoa.getId());
	}
	
	public boolean buscarPorLoginSenhaID(String cpf, String senha, int id) throws DAOException {
		if(cpf == null || senha == null || id == 0)
			throw new BOException("Erro ao buscar dados de usuario");
		cpf = UserUtil.DocumentoUtil.removerCaracteresEspeciais(cpf);
		
		
		if(!(senha.length() > 6 && senha.length() < 11))
		{
			try
			{
				senha = SecurityUtil.criptografarSHA2(senha);
			}
			catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
			{
				throw new DAOException("Erro ao criptografar senha");
			}
		}
		
		return ((DAOPessoa)this.daoT).buscarPorCpfSenhaID(cpf, senha, id);
	}
	
}
