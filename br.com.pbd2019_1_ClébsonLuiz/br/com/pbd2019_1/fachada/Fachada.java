package br.com.pbd2019_1.fachada;

import br.com.pbd2019_1.dao.DAOCaracteristicaExtra;
import br.com.pbd2019_1.dao.DAOColaborador;
import br.com.pbd2019_1.dao.DAOContato;
import br.com.pbd2019_1.dao.DAOEtapa;
import br.com.pbd2019_1.dao.DAOLogUpdate;
import br.com.pbd2019_1.dao.DAOPessoa;
import br.com.pbd2019_1.dao.DAOProjeto;
import br.com.pbd2019_1.dao.DAOTarefa;
import br.com.pbd2019_1.dao.DAOUsuario;

public class Fachada {

	private DAOEtapa daoEtapa;
	private DAOPessoa daoPessoa;
	private DAOTarefa daoTarefa;
	private DAOContato daoContato;
	private DAOProjeto daoProjeto;
	private DAOUsuario daoUsuario;
	private DAOLogUpdate daoLogUpdate;
	private DAOColaborador daoColaborador;
	private DAOCaracteristicaExtra daoCaracteristicaExtra;
	
	private  Fachada() {
		this.daoEtapa = new DAOEtapa();
		this.daoPessoa = new DAOPessoa();
		this.daoTarefa = new DAOTarefa();
		this.daoContato = new DAOContato();
		this.daoProjeto = new DAOProjeto();
		this.daoUsuario = new DAOUsuario();
		this.daoLogUpdate = new DAOLogUpdate();
		this.daoColaborador = new DAOColaborador();
		this.daoCaracteristicaExtra = new DAOCaracteristicaExtra();
	}

	private static Fachada fachada;
	
	public static Fachada getInstance() {
		if(fachada == null)
			fachada = new Fachada();
		return fachada;
	}

	public DAOEtapa getDaoEtapa() {return daoEtapa;}
	public DAOPessoa getDaoPessoa() {return daoPessoa;}
	public DAOTarefa getDaoTarefa() {return daoTarefa;}
	public DAOContato getDaoContato() {return daoContato;}
	public DAOProjeto getDaoProjeto() {return daoProjeto;}
	public DAOUsuario getDaoUsuario() {return daoUsuario;}
	public DAOLogUpdate getDaoLogUpdate() {return daoLogUpdate;}
	public DAOColaborador getDaoColaborador() {return daoColaborador;}
	public DAOCaracteristicaExtra getDaoCaracteristicaExtra() {return daoCaracteristicaExtra;}
	
}
