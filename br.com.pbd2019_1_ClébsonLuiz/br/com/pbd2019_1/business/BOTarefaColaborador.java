package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOTarefaColaborador;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.entidade.TarefaColaborador;
import br.com.pbd2019_1.exception.DAOException;

public class BOTarefaColaborador extends BOGenerico<TarefaColaborador>{

	public BOTarefaColaborador() {
		super(new DAOTarefaColaborador(), TarefaColaborador.class);
	}
	
	public List<TarefaColaborador> getListPorColaborador(Colaborador colaborador) throws DAOException
	{
		return ((DAOTarefaColaborador)this.daoT).getListPorColaborador(colaborador);
	}
	
	public List<TarefaColaborador> getListPorTarefa(Tarefa tarefa) throws DAOException
	{
		return ((DAOTarefaColaborador)this.daoT).getListPorTarefa(tarefa);
	}
	
	public TarefaColaborador getPorTarefa(Tarefa tarefa) throws DAOException
	{
		return ((DAOTarefaColaborador)this.daoT).getPorTarefa(tarefa);
	}
	
}
