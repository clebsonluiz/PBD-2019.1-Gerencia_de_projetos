package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOTarefa;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOTarefa extends BOGenerico<Tarefa>{

	public BOTarefa() {
		super(new DAOTarefa(), Tarefa.class);
	}
	
	protected void validacaoInsercao(Tarefa t) throws BOException {
		if(t == null || t.getNome() == null || t.getNome().trim().equals("")
				|| t.getPrioridade() == null ||t.getPrioridade().trim().equals("")
				|| t.getHorario() == null 
				|| t.getEtapa() == null || t.getEtapa().getId() <= 0)
			throw new BOException("Campos de Tarefa Invalidos");
	}
	
	protected void validacaoAtualizacao(Tarefa t) throws BOException {
		validacaoInsercao(t);
		if(t.getId() <= 0)
			throw new BOException("Não foi possivel atualizar tarefa");
	}
	
	public List<Tarefa> buscarPorEtapa(Etapa etapa) throws BOException, DAOException {
		if(etapa == null || etapa.getId() <= 0)
			throw new BOException("Não é possivel buscar por etapa");
		return ((DAOTarefa)this.daoT).buscarPorEtapa(etapa);
	}
}
