package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.ViewSubEtapaColaborador;

public class TViewSubEtapaColaborador extends TView<ViewSubEtapaColaborador>{

	private static final long serialVersionUID = 1L;

	public TViewSubEtapaColaborador() 
	{
		super(new String[] {"Nome", "Descrição", "Andamento", "" });
	}

	@Override
	public Object getValueAt(int linha, int coluna) 
	{
		switch (coluna) 
		{
		case 0:
			return getList().get(linha).getNome();
		case 1:
			return getList().get(linha).getDescricao();
		case 2:
			return getList().get(linha).getPorcentagem();
		case 3:
			return getBtn();
		}
		return null;
	}
}
