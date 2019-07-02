package br.com.pbd2019_1.dao;

import java.util.List;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.entidade.TarefaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class DAOTarefaColaborador extends DAOGenerico<TarefaColaborador>{

	
	public List<TarefaColaborador> getListPorColaborador(Colaborador colaborador) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				TarefaColaborador.class,
				"TarefaColaborador.colaborador",
				"colaborador",
				colaborador);
	}
	
	public List<TarefaColaborador> getListPorTarefa(Tarefa tarefa) throws DAOException
	{
		return buscaNamedQueryGenericaListFK(
				TarefaColaborador.class,
				"TarefaColaborador.tarefa",
				"tarefa",
				tarefa);
	}
	
	public TarefaColaborador getPorTarefa(Tarefa tarefa) throws DAOException
	{
		return buscaNamedQueryGenericaFK(
				TarefaColaborador.class,
				"TarefaColaborador.tarefa",
				"tarefa",
				tarefa);
	}
}
