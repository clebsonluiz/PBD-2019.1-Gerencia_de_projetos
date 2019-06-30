package br.com.pbd2019_1.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.entidade.Tarefa;

public interface EntidadeUtil {
	
	public static Contato getContato(
			Contato contato,
			String email,
			String celular,
			String telef,
			Pessoa pessoa
			)
	{
		if(contato == null) contato = new Contato();
		contato.setEmail(email);
		contato.setCelular(celular);
		contato.setTelefone(telef);
		contato.setPessoa(pessoa);
		return contato;
	}
	
	public static Pessoa getPessoa(
			Pessoa pessoa, 
			String nome,
			String cpf,
			LocalDate data,
			String sexo,
			String senha,
			boolean disponivel,
			String user_type)
	{
		if(pessoa == null) pessoa = new Pessoa();
		
		pessoa.setNome(nome);
		pessoa.setCpf(cpf);
		pessoa.setData_nascimento(data);
		pessoa.setSexo(sexo);
		pessoa.setUser_senha(senha);
		pessoa.setDisponibilidade(disponivel);
		pessoa.setUser_type(user_type);		
		
		return pessoa;
	}
	
	public static Projeto getProjeto(
			Projeto projeto,
			String nome,
			String descr,
			Date dataI,
			Date dataF,
			Pessoa pessoa,
			boolean isPrivilegio
			)
	{
		if(projeto == null) projeto = new Projeto();
		
		projeto.setNome(nome);
		projeto.setDescricao(descr);
		projeto.setData_inicio(DateUtil.getDateSQL(dataI));
		projeto.setData_fim(DateUtil.getDateSQL(dataF));
		projeto.setPessoa(pessoa);
		projeto.setPrivilegio(isPrivilegio);
		
		return projeto;
	}
	
	public static Etapa getEtapa(
			Etapa etapa,
			String nome,
			String descr,
			int porcentagem,
			Projeto projeto
			)
	{
		if(etapa == null) etapa = new Etapa();
		
		etapa.setNome(nome);
		etapa.setDescricao(descr);
		etapa.setPorcentagem(porcentagem);
		etapa.setProjeto(projeto);
		return etapa;
	}
	
	public static Tarefa getTarefa(
			Tarefa tarefa,
			String nome,
			String descr,
			boolean finalizada,
			String prioridade,
			LocalDateTime horario,
			Etapa etapa
			)
	{
		if(tarefa == null) tarefa = new Tarefa();
		
		tarefa.setNome(nome);
		tarefa.setDescricao(descr);
		tarefa.setConcluida(finalizada);
		tarefa.setPrioridade(prioridade);
		tarefa.setHorario(horario);
		tarefa.setEtapa(etapa);
		
		return tarefa;
	}
	
	public static SubEtapa getSubEtapa(
			SubEtapa sub_etapa,
			String nome,
			String descr,
			int porcentagem,
			Etapa etapa
			)
	{
		if(sub_etapa == null) sub_etapa = new SubEtapa();
		
		sub_etapa.setNome(nome);
		sub_etapa.setDescricao(descr);
		sub_etapa.setPorcentagem(porcentagem);
		sub_etapa.setEtapa(etapa);
		return sub_etapa;
	}
	
	public static SubTarefa getSubTarefa(
			SubTarefa sub_tarefa,
			String nome,
			String descr,
			boolean finalizada,
			String prioridade,
			LocalDateTime horario,
			SubEtapa sub_etapa
			)
	{
		if(sub_tarefa == null) sub_tarefa = new SubTarefa();
		
		sub_tarefa.setNome(nome);
		sub_tarefa.setDescricao(descr);
		sub_tarefa.setConcluida(finalizada);
		sub_tarefa.setPrioridade(prioridade);
		sub_tarefa.setHorario(horario);
		sub_tarefa.setSub_etapa(sub_etapa);
		
		return sub_tarefa;
	}
	
	
}
