package br.com.pbd2019_1.fachada;

import java.util.List;

import br.com.pbd2019_1.business.BOCaracteristicaExtra;
import br.com.pbd2019_1.business.BOColaborador;
import br.com.pbd2019_1.business.BOContato;
import br.com.pbd2019_1.business.BOEtapa;
import br.com.pbd2019_1.business.BOLogUpdate;
import br.com.pbd2019_1.business.BOPessoa;
import br.com.pbd2019_1.business.BOProjeto;
import br.com.pbd2019_1.business.BOTarefa;
import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.BOException;
import br.com.pbd2019_1.exception.DAOException;

public class Fachada {

	private BOEtapa boEtapa;
	private BOPessoa boPessoa;
	private BOTarefa boTarefa;
	private BOContato boContato;
	private BOProjeto boProjeto;
	private BOLogUpdate boLogUpdate;
	private BOColaborador boColaborador;
	private BOCaracteristicaExtra boCaracteristicaExtra;
	
	public void carregarBoEtapa() {this.boEtapa = new BOEtapa();}
	public void carregarBoPessoa() {this.boPessoa = new BOPessoa();}
	public void carregarBoTarefa() {this.boTarefa = new BOTarefa();}
	public void carregarBoContato() {this.boContato = new BOContato();}
	public void carregarBoProjeto() {this.boProjeto = new BOProjeto();}
	public void carregarBoLogUpdate() {this.boLogUpdate = new BOLogUpdate();}
	public void carregarBoColaborador() {this.boColaborador = new BOColaborador();}
	public void carregarBoCaracteristicaExtra() {this.boCaracteristicaExtra = new BOCaracteristicaExtra();}

	private  Fachada() {}

	private static Fachada fachada;
	
	public static Fachada getInstance() {
		if(fachada == null)
			fachada = new Fachada();
		return fachada;
	}
	
	
	public <T extends Entidade> Entidade inserir(T t) throws BOException, DAOException{
		if(t instanceof CaracteristicaExtra)
			return boCaracteristicaExtra.inserir((CaracteristicaExtra)t);
		else if(t instanceof Colaborador)
			return boColaborador.inserir((Colaborador)t);
		else if(t instanceof Contato)
			return boContato.inserir((Contato)t);
		else if(t instanceof Etapa)
			return boEtapa.inserir((Etapa)t);
		else if(t instanceof LogUpdate)
			return boLogUpdate.inserir((LogUpdate)t);
		else if(t instanceof Pessoa)
			return boPessoa.inserir((Pessoa)t);
		else if(t instanceof Projeto)
			return boProjeto.inserir((Projeto)t);
		else if(t instanceof Tarefa)
			return boTarefa.inserir((Tarefa)t);
		else
			return null;
	}
	
	public <T extends Entidade> Entidade atualizar(T t) throws BOException, DAOException{
		if(t instanceof CaracteristicaExtra)
			return boCaracteristicaExtra.atualizar((CaracteristicaExtra)t);
		else if(t instanceof Colaborador)
			return boColaborador.atualizar((Colaborador)t);
		else if(t instanceof Contato)
			return boContato.atualizar((Contato)t);
		else if(t instanceof Etapa)
			return boEtapa.atualizar((Etapa)t);
		else if(t instanceof LogUpdate)
			return boLogUpdate.atualizar((LogUpdate)t);
		else if(t instanceof Pessoa)
			return boPessoa.atualizar((Pessoa)t);
		else if(t instanceof Projeto)
			return boProjeto.atualizar((Projeto)t);
		else if(t instanceof Tarefa)
			return boTarefa.atualizar((Tarefa)t);
		else
			return null;
	}
	
	
	public void deletar(Object t) throws BOException, DAOException{
		if(t instanceof CaracteristicaExtra)
			boCaracteristicaExtra.deletar((CaracteristicaExtra)t);
		else if(t instanceof Colaborador)
			boColaborador.deletar((Colaborador)t);
		else if(t instanceof Contato)
			boContato.deletar((Contato)t);
		else if(t instanceof Etapa)
			boEtapa.deletar((Etapa)t);
		else if(t instanceof LogUpdate)
			boLogUpdate.deletar((LogUpdate)t);
		else if(t instanceof Pessoa)
			boPessoa.deletar((Pessoa)t);
		else if(t instanceof Projeto)
			boProjeto.deletar((Projeto)t);
		else if(t instanceof Tarefa)
			boTarefa.deletar((Tarefa)t);
	}
	
	public <T extends Entidade> Entidade buscar(Class<T> classe, int id) throws BOException, DAOException{
		if(classe.getSimpleName().equals(CaracteristicaExtra.class.getSimpleName()))
			return boCaracteristicaExtra.buscar(id);
		else if(classe.getSimpleName().equals(Colaborador.class.getSimpleName()))
			return boColaborador.buscar(id);
		else if(classe.getSimpleName().equals(Contato.class.getSimpleName()))
			return boContato.buscar(id);
		else if(classe.getSimpleName().equals(Etapa.class.getSimpleName()))
			return boEtapa.buscar(id);
		else if(classe.getSimpleName().equals(LogUpdate.class.getSimpleName()))
			return boLogUpdate.buscar(id);
		else if(classe.getSimpleName().equals(Pessoa.class.getSimpleName()))
			return boPessoa.buscar(id);
		else if(classe.getSimpleName().equals(Projeto.class.getSimpleName()))
			return boProjeto.buscar(id);
		else if(classe.getSimpleName().equals(Tarefa.class.getSimpleName()))
			return boTarefa.buscar(id);
		else
			return null;
	}
	
	public  List<?> buscarAll(Class<?> classe) throws BOException, DAOException{
		if(classe.getSimpleName().equals(CaracteristicaExtra.class.getSimpleName()))
			return boCaracteristicaExtra.buscarALL();
		else if(classe.getSimpleName().equals(Colaborador.class.getSimpleName()))
			return boColaborador.buscarALL();
		else if(classe.getSimpleName().equals(Contato.class.getSimpleName()))
			return boContato.buscarALL();
		else if(classe.getSimpleName().equals(Etapa.class.getSimpleName()))
			return boEtapa.buscarALL();
		else if(classe.getSimpleName().equals(LogUpdate.class.getSimpleName()))
			return boLogUpdate.buscarALL();
		else if(classe.getSimpleName().equals(Pessoa.class.getSimpleName()))
			return boPessoa.buscarALL();
		else if(classe.getSimpleName().equals(Projeto.class.getSimpleName()))
			return boProjeto.buscarALL();
		else if(classe.getSimpleName().equals(Tarefa.class.getSimpleName()))
			return boTarefa.buscarALL();
		else
			return null;
	}

	public BOEtapa getBoEtapa() {return boEtapa;}
	public BOPessoa getBoPessoa() {return boPessoa;}
	public BOTarefa getBoTarefa() {return boTarefa;}
	public BOContato getBoContato() {return boContato;}
	public BOProjeto getBoProjeto() {return boProjeto;}
	public BOLogUpdate getBoLogUpdate() {return boLogUpdate;}
	public BOColaborador getBoColaborador() {return boColaborador;}
	public BOCaracteristicaExtra getBoCaracteristicaExtra() {return boCaracteristicaExtra;}
	
}
