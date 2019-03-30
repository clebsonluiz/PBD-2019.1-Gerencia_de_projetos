package br.com.pbd2019_1.fachada;

import br.com.pbd2019_1.business.BOCaracteristicaExtra;
import br.com.pbd2019_1.business.BOColaborador;
import br.com.pbd2019_1.business.BOContato;
import br.com.pbd2019_1.business.BOEtapa;
import br.com.pbd2019_1.business.BOLogUpdate;
import br.com.pbd2019_1.business.BOPessoa;
import br.com.pbd2019_1.business.BOProjeto;
import br.com.pbd2019_1.business.BOTarefa;
import br.com.pbd2019_1.business.BOUsuario;

public class Fachada {

	private BOEtapa boEtapa;
	private BOPessoa boPessoa;
	private BOTarefa boTarefa;
	private BOContato boContato;
	private BOProjeto boProjeto;
	private BOUsuario boUsuario;
	private BOLogUpdate boLogUpdate;
	private BOColaborador boColaborador;
	private BOCaracteristicaExtra boCaracteristicaExtra;
	
	private  Fachada() {
		this.boEtapa = new BOEtapa();
		this.boPessoa = new BOPessoa();
		this.boTarefa = new BOTarefa();
		this.boContato = new BOContato();
		this.boProjeto = new BOProjeto();
		this.boUsuario = new BOUsuario();
		this.boLogUpdate = new BOLogUpdate();
		this.boColaborador = new BOColaborador();
		this.boCaracteristicaExtra = new BOCaracteristicaExtra();
	}

	private static Fachada fachada;
	
	public static Fachada getInstance() {
		if(fachada == null)
			fachada = new Fachada();
		return fachada;
	}

	public BOEtapa getBoEtapa() {return boEtapa;}
	public BOPessoa getBoPessoa() {return boPessoa;}
	public BOTarefa getBoTarefa() {return boTarefa;}
	public BOContato getBoContato() {return boContato;}
	public BOProjeto getBoProjeto() {return boProjeto;}
	public BOUsuario getBoUsuario() {return boUsuario;}
	public BOLogUpdate getBoLogUpdate() {return boLogUpdate;}
	public BOColaborador getBoColaborador() {return boColaborador;}
	public BOCaracteristicaExtra getBoCaracteristicaExtra() {return boCaracteristicaExtra;}
	
}
