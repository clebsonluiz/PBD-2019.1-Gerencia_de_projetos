package br.com.pbd2019_1.business;

import br.com.pbd2019_1.dao.DAOUsuario;
import br.com.pbd2019_1.entidade.Usuario;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOUsuario extends BOGenerico<Usuario> {

	public BOUsuario() {
		super(new DAOUsuario(), Usuario.class);
	}

	protected void validacaoInsercao(Usuario t) throws BOException{
		if(t == null 
				|| t.getLogin() == null || t.getLogin().trim().equals("")
				|| t.getSenha() == null || t.getSenha().trim().equals("")
				|| !t.isAdmin_user() && !t.isComum_user() && !t.isSuper_user())
			throw new BOException("Campos Vazios");
	}
	
	protected void validacaoAtualizacao(Usuario t) throws BOException {
		validacaoInsercao(t);
		if(t.getId() <= 0)
			throw new BOException("Não é possivel atualizar esse usuario");
	}
	
	public boolean buscarPorLogin(String login) throws BOException, DAOException {
		if(((DAOUsuario)this.daoT).buscarPorLogin(login))
			throw new BOException("Já Existe um usuario de mesmo login");
		return false;
	}
	
	public Usuario buscarUsuario(String login, String senha) throws BOException, DAOException {
		if(login == null || login.trim().equals(""))
			throw new BOException("Campo login vazio");
		if(senha == null || senha.trim().equals(""))
			throw new BOException("Campo senha vazio");
		return ((DAOUsuario)this.daoT).buscarUsuario(login, senha);
	}
	
}
