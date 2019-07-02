package br.com.pbd2019_1.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Contato;
import br.com.pbd2019_1.entidade.Entidade;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.SubEtapa;
import br.com.pbd2019_1.entidade.SubTarefa;
import br.com.pbd2019_1.entidade.Tarefa;

public interface LogUpdateUtil {

	public static abstract class GerarLog
	{
		public static List<String> gerarLog(Pessoa p)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					p.getNome(),
					p.getCpf(),
					DateUtil.getLocalDateString("yyyy-MM-dd", p.getData_nascimento()),
					p.getSexo(), "USER_PASSWORD",
					new Boolean(p.isDisponibilidade()).toString(),
					p.getUser_type());
			
			return list;
		}
		
		public static List<String> gerarLog(CaracteristicaExtra ce)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list, ce.getNome());
				
			return list;
		}
		
		public static List<String> gerarLog(Colaborador c)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					DateUtil.getLocalDateTimeString("yyyy-MM-dd", c.getData_ingresso()));
			return list;
		}
		
		public static List<String> gerarLog(Contato co)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					co.getEmail(),
					co.getCelular(),
					co.getTelefone());
			
			return list;
		}
		
		public static List<String> gerarLog(Etapa e)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					e.getNome(),
					e.getDescricao(),
					new Float(e.getPorcentagem()).toString());
			
			return list;
		}
		
		public static List<String> gerarLog(Projeto pro)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					pro.getNome(),
					pro.getDescricao(), 
					DateUtil.getDateString("yyyy-MM-dd", pro.getData_inicio()),
					DateUtil.getDateString("yyyy-MM-dd", pro.getData_fim()),
					new Boolean(pro.isPrivilegio()).toString());
			
			return list;
		}
		
		public static List<String> gerarLog(SubEtapa s)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					s.getNome(),
					s.getDescricao(),
					new Float(s.getPorcentagem()).toString());
			
			return list;
		}
		
		public static List<String> gerarLog(SubTarefa s)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list, 
					s.getNome(),
					s.getDescricao(),
					new Boolean(s.isConcluida()).toString(),
					s.getPrioridade(),
					s.getHorario().toString());
			
			return list;
		}
		
		public static List<String> gerarLog(Tarefa ta)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list, 
					ta.getNome(),
					ta.getDescricao(),
					new Boolean(ta.isConcluida()).toString(),
					ta.getPrioridade(),
					ta.getHorario().toString());
			
			return list;
		}
		
	}
	
	public static abstract class GerarColunas
	{
		public static List<String> gerarColuna(Pessoa p)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					"Nome",
					"CPF",
					"Nascimento",
					"Sexo", "USER_PASSWORD",
					"Disponibilidade Cooperativa",
					"User Type");
			
			return list;
		}
		
		public static List<String> gerarColuna(CaracteristicaExtra ce)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list, "Caracteristica");
				
			return list;
		}
		
		public static List<String> gerarColuna(Colaborador c)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					"Data de Ingresso");
			return list;
		}
		
		public static List<String> gerarColuna(Contato co)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					"Email",
					"Celular",
					"Telefone");
			
			return list;
		}
		
		public static List<String> gerarColuna(Etapa e)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					"Nome",
					"Descrição",
					"Porcentagem");
			
			return list;
		}
		
		public static List<String> gerarColuna(Projeto pro)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					"Nome",
					"Descrição", 
					"Data de Inicio",
					"Data de Términio",
					"Privilegiado");
			
			return list;
		}
		
		public static List<String> gerarColuna(SubEtapa s)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list,
					"Nome",
					"Descrição",
					"Porcentagem");
			
			return list;
		}
		
		public static List<String> gerarColuna(SubTarefa s)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list, 
					"Nome",
					"Descrição",
					"Finalizada?",
					"Prioridade",
					"Horario");
			
			return list;
		}
		
		public static List<String> gerarColuna(Tarefa ta)
		{
			List<String> list = new ArrayList<>();
			
			Collections.addAll(list, 
					"Nome",
					"Descrição",
					"Finalizada?",
					"Prioridade",
					"Horario");
			
			return list;
		}
		
		public static List<String> gerarColunas(Entidade entidade)
		{
			if(entidade instanceof CaracteristicaExtra)
				return gerarColuna((CaracteristicaExtra) entidade);
			else if(entidade instanceof Colaborador)
				return gerarColuna((Colaborador) entidade);
			else if(entidade instanceof Contato)
				return gerarColuna((Contato) entidade);
			else if(entidade instanceof Etapa)
				return gerarColuna((Etapa) entidade);
			else if(entidade instanceof Pessoa)
				return gerarColuna((Pessoa) entidade);
			else if(entidade instanceof Projeto)
				return gerarColuna((Projeto) entidade);
			else if(entidade instanceof Tarefa)
				return gerarColuna((Tarefa) entidade);
			else if(entidade instanceof SubTarefa)
				return gerarColuna((SubTarefa) entidade);
			else if(entidade instanceof SubEtapa)
				return gerarColuna((SubEtapa) entidade);
			return null;
		}
	}
	
	
}
