package br.com.pbd2019_1.business;

import java.util.List;

import br.com.pbd2019_1.dao.DAOCaracteristicaExtra;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOCaracteristicaExtra extends BOGenerico<CaracteristicaExtra>{

	public BOCaracteristicaExtra() {
		super(new DAOCaracteristicaExtra(), CaracteristicaExtra.class);
	}

	protected void validacaoInsercao(CaracteristicaExtra t) throws BOException{
		if(t == null || t.getPessoa() == null ||
				t.getNome() == null || t.getNome().trim().equals(""))
			throw new BOException("Erro ao validar caracteristica");
	}
	
	protected void validacaoAtualizacao(CaracteristicaExtra t) throws BOException{
		if(t == null || t.getPessoa() == null ||
				t.getNome() == null || t.getNome().trim().equals(""))
			throw new BOException("Erro ao validar caracteristica");
	}

	public List<CaracteristicaExtra> buscaPorPessoa(Pessoa pessoa) throws BOException, DAOException{
		if(pessoa == null || pessoa.getId() <= 0)
			throw new BOException("Erro ao tentar buscar caracteristicas");
		return ((DAOCaracteristicaExtra)this.daoT).buscarPorPessoa(pessoa);
	}

}
