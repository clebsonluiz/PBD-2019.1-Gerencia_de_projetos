package br.com.pbd2019_1.controll;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;

public class Controlador_Statics {

	public static Pessoa pessoa_static;
	public static Pessoa pessoa_outrem_static;
	public static Projeto projeto_static;
	public static Etapa etapa_static;
	public static Tarefa tarefa_static;
	public static Colaborador colaborador_static;
	public static LogUpdate logUpdate_static;
	public static String type_user = "";
	public static boolean bool_colaborador = false;
	
	/**
	 *
	 * pessoa_static - Pessoa que está logada no sistema atualmente
	 * pessoa_outrem_static - Quando a pessoa que está logado no sistema ver outra pessa
	 * projeto_static - Projeto que uma pessoa quer ver
	 * etapa_static - Etapa pertecente ao projeto anterior que uma pessoa quer ver
	 * tarefa_static - Tarefa que pertence a Etapa anterior que uma pessoa quer ver
	 * colaborador_static - Quando a pessoa vai ver um projeto que está como colaborador
	 * logUpdate_static - Log que está sendo visutalizado atualmente.
	 * type_user - Tipo do Usuario que está logado no sistema
	 * bool_colaborador - Indica que um projeto que está sendo visto é um projeto de colaborador.
	 * 
	 * */
	
}
