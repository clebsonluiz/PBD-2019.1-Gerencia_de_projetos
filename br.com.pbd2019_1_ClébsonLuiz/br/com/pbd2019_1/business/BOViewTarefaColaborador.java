package br.com.pbd2019_1.business;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.pbd2019_1.dao.DAOViewTarefaColaborador;
import br.com.pbd2019_1.entidade.ViewTarefaColaborador;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class BOViewTarefaColaborador extends BOView<ViewTarefaColaborador>
{

	public BOViewTarefaColaborador()
	{
		super(new DAOViewTarefaColaborador(), ViewTarefaColaborador.class);
	}
	
	public List<ViewTarefaColaborador> getPorPessoa(int indexCombo, int indexCombo2, String busca, java.util.Date date, int id_pessoa) throws DAOException, BOException
	{
		String sql = "";
		if(indexCombo == 0) 
			sql = "select tc from ViewTarefaColaborador tc where tc.cod_de_pessoa = " + id_pessoa;
		else 
		{
			String data = new SimpleDateFormat("yyyy-MM-dd").format((date == null)? new java.util.Date() : date);
			
			String buscaData = date != null? " and (date(tc.horario)) = '" + data + "'" : "";
			
			switch (indexCombo2) 
			{
			case 0:
				sql = "select tc from ViewTarefaColaborador tc where tc.nome like '%" + busca + "%' "+ buscaData +" and tc.cod_de_pessoa = " + id_pessoa;
				break;
			case 1:
				sql = "select tc from ViewTarefaColaborador tc where tc.nome like '%" + busca + "%' "+ buscaData +"  and tc.concluida = false and tc.cod_de_pessoa = " + id_pessoa;
				break;
			case 2:
				sql = "select tc from ViewTarefaColaborador tc where tc.nome like '%" + busca + "%' "+ buscaData +"  and tc.concluida = true and tc.cod_de_pessoa = " + id_pessoa;
				break;
			case 3:
				sql = "select tc from ViewTarefaColaborador tc where tc.nome like '%" + busca + "%' and (date(tc.horario)) < CURRENT_DATE and tc.concluida = false and tc.cod_de_pessoa = " + id_pessoa;
				break;
			}
		}
		return buscaListaHQLGenerica(sql);
	}
	
	public ViewTarefaColaborador getPorTarefa(int id_tarefa) throws DAOException, BOException
	{
		return buscaHQLGenerica("select tc from tarefa_colaborador_view tc where tc.cod_de_tarefa = " + id_tarefa);
	}
	
}
