package br.com.pbd2019_1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.DAOException;

public class DAOPessoa extends DAOGenerico<Pessoa>{

	public boolean buscarPorCPF(String cpf) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.cpf", Integer.class)
			.setParameter("cpf", cpf).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorCPFID(String cpf, int id) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.cpfID", Integer.class)
			.setParameter("cpf", cpf).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorUsuario(String login) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.buscarUsuarioLogin", Integer.class)
			.setParameter("login", login).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public boolean buscarPorUsuarioID(String login, int id) throws DAOException {
		EntityManager entityManager = createEntityManager();
		int quantidade = 0;
		try {
			quantidade = entityManager.createNamedQuery("Pessoa.buscarUsuarioLoginID", Integer.class)
			.setParameter("login", login).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		System.out.println("Quantidade : "+quantidade);
		return (quantidade > 0)? true : false;
	}
	
	public Pessoa buscarPorUsuario(String login, String senha) throws DAOException {
		EntityManager entityManager = createEntityManager();
		Pessoa pessoa = null;
		try {
			
			TypedQuery<Pessoa> query = entityManager.
					createNamedQuery("Pessoa.buscarUsuario", Pessoa.class);
			
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			
			pessoa = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			pessoa = null;
			throw new DAOException("Não existe uma pessoa com esse login e senha");
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new DAOException("Erro de busca no banco de dados");
		} finally {
			entityManager.close();
		}
		return pessoa;
	}
	
	public List<Pessoa> buscarPessoasDiferentes(int id) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<Pessoa> pessoas = null;
		try {
			pessoas = entityManager.
					createNamedQuery("Pessoa.buscarPessoas", Pessoa.class)
					.setParameter("id", id).getResultList();
					
		} catch (NoResultException e) {
			e.printStackTrace();
			pessoas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro ao buscar todos as Pessoas");
		} finally {
			entityManager.close();
		}
		return pessoas;
	}
	
	public List<Pessoa> buscarPessoasDiferentes(int id, String nome, String cpf, String disponibilidade) throws DAOException{
		EntityManager entityManager = createEntityManager();
		List<Pessoa> pessoas = null;
		try {
			pessoas = entityManager.
					createNamedQuery("Pessoa.buscarPessoasEspecificar", Pessoa.class)
					.setParameter("id", id)
					.setParameter("nome", "%" + nome + "%")
					.setParameter("cpf", "%" + cpf + "%")
					.setParameter("disponibilidade", "%" + disponibilidade + "%").getResultList();
					
		} catch (NoResultException e) {
			e.printStackTrace();
			pessoas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Erro ao buscar todos as Pessoas");
		} finally {
			entityManager.close();
		}
		return pessoas;
	}
	
	public int[] buscarTotalTarefasPessoa(int id) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		int totais[] = null;
		
		try 
		{
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("tarefas_pessoa");
			query.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
			query.setParameter(0, id);
			Object[] o = (Object[]) query.getSingleResult();
			totais = new int[] {(int) o[0], (int) o[1]}; 
		} 
		catch (NoResultException e) 
		{
			e.printStackTrace();
			totais = new int[] {0, 0};
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Erro ao buscar todos as Pessoas");
		}
		finally
		{
			entityManager.close();
		}
		return totais;
	}
	
	public int[] buscarTotalEtapasPessoa(int id) throws DAOException{
		EntityManager entityManager = createEntityManager();
		
		int totais[] = null;
		
		try 
		{
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("etapas_pessoa");
			query.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
			query.setParameter(0, id);
			Object[] o = (Object[]) query.getSingleResult();
			totais = new int[] {(int) o[0], (int) o[1]}; 
		} 
		catch (NoResultException e) 
		{
			e.printStackTrace();
			totais = new int[] {0, 0};
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Erro ao buscar todos as Pessoas");
		}
		finally
		{
			entityManager.close();
		}
		return totais;
	}
}
