package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.ViewTarefaColaborador;

public class TViewTarefaColaborador extends TView<ViewTarefaColaborador>{

	private static final long serialVersionUID = 1L;

	public TViewTarefaColaborador() 
	{
		super(new String[] {"Nome", "Horario", "Prioridade", "Status", "" });
	}

	@Override
	public Object getValueAt(int linha, int coluna) 
	{
		switch (coluna) 
		{
		case 0:
			return getList().get(linha).getNome();
		case 1:
			return getList().get(linha).getHorario().toString();
		case 2:
			return getList().get(linha).getPrioridade();
		case 3:
			return getList().get(linha).isConcluida();
		case 4:
			return getBtn();
		}
		return null;
	}
}
