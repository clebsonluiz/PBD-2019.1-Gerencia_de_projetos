package br.com.pbd2019_1.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.utils.ChartsUtil;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.utils.RelatorioUtil;
import br.com.pbd2019_1.utils.RelatorioUtil.DocUtil;

public class DAOResRelatorio {

	private Document documento;
	private PdfWriter writer;
	
	private static DAOResRelatorio instance;
	
	public static DAOResRelatorio getInstance() {
		if(instance == null)
			instance = new DAOResRelatorio();
		return instance;
	}

	private Document criarDocumento(String pathCaminho) throws FileNotFoundException, DocumentException
	{
		documento = new Document(PageSize.A4, DocUtil.M_ESQ, DocUtil.M_DIR, DocUtil.M_SUP, DocUtil.M_INF);
		writer = PdfWriter.getInstance(documento, new FileOutputStream(pathCaminho + ".pdf"));
		
		RelatorioUtil.HeaderFooterPageEvent event = new RelatorioUtil.HeaderFooterPageEvent();
		
        writer.setPageEvent(event);
		documento.open();
		return documento;
	}
	
	public void gerarRelatorio(int op, Projeto projeto, String pathCaminho) throws FileNotFoundException, DocumentException
	{
		Document documento = criarDocumento(pathCaminho);
		
		switch (op) 
		{
			case 0:
				gerarRelatorioSistematico(projeto, documento);
				break;
			case 1:
				gerarRelatorioCompleto(projeto, documento);
				break;
			case 2:
				gerarRelatorioAnalitico(projeto, documento);
				break;
		}
		documento.close();
	}
	
	
	public void gerarRelatorioSistematico(Projeto projeto, Document documento) throws DocumentException {
		conteudoProjetoResumido(projeto, documento);
	}
	
	public void gerarRelatorioCompleto(Projeto projeto, Document documento) throws DocumentException {
		conteudoProjetoCompleto(projeto, documento);
	}
	
	public void gerarRelatorioAnalitico(Projeto projeto, Document documento) throws DocumentException {
		conteudoProjetoCompletoTabelas(projeto, documento);
	}
	
	
	public Image getGrafico(Projeto projeto) throws Exception {
		
		final java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 20);
		
		int porc_andamento = Fachada.getInstance().getBoProjeto().andamento_Projeto(projeto);
		int porc_restante = 100 - porc_andamento;
		
		int width = 1000;
        int height = 400;
        
		Map<String, Double> map = new HashMap<>();
		map.put("Atual", (double)porc_andamento);
		map.put("Restante", (double)porc_restante);
		
		return DocUtil.getGrafico(writer, width, height, 50, "Atual", map, "({2})", "ANDAMENTO DO PRAZO DO PROJETO (Em %)", font, ChartsUtil.CORES_1);
	}
	
	public void conteudoProjetoCompletoTabelas(Projeto projeto, Document documento) throws DocumentException
	{
		conteudoProjeto("RELATORIO DE PROJETO", projeto, documento);
		
		Paragraph paragraph;
		
		if(projeto.getColaboradores().size() > 0) 
		{
			paragraph = DocUtil.addParagrafo("Colaboradores do Projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			DocUtil.addEmptyLine(paragraph, 1);
			PdfPTable table = DocUtil.addTabelaColaboradores(projeto.getColaboradores());
			paragraph.add(table);
			documento.add(paragraph);
		}else {
			paragraph = DocUtil.addParagrafo("Não ouve colaboradores no projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			DocUtil.addEmptyLine(paragraph, 1);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0)
			paragraph = DocUtil.addParagrafo("Etapas do Projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = DocUtil.addParagrafo("Não se tem etapas adicionadas neste o projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getEtapas().size(); i++) 
		{
			
			Etapa e = projeto.getEtapas().get(i);
			paragraph = gerarParaEtapa(e, (i + 1) + ". Etapa", 1);
			DocUtil.addEmptyLine(paragraph, 1);
			documento.add(paragraph);
			
			
			if(e.getTarefas().size() > 0) {
				
				paragraph = DocUtil.addParagrafo("Tarefas da "+ (i + 1) + ". Etapa", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
				DocUtil.addEmptyLine(paragraph, 1);
				documento.add(paragraph);
				documento.add(DocUtil.addTabelaTarefas(e.getTarefas()));
			}
			else {
				paragraph = DocUtil.addParagrafo("Não se tem tarefas adicionadas para a "+ (i + 1) + ". Etapa", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
				documento.add(paragraph);
			}
			
		}
		
		
	}
	
	public void conteudoProjetoCompleto(Projeto projeto, Document documento) throws DocumentException
	{
		conteudoProjeto("RELATORIO DE PROJETO", projeto, documento);
		
		Paragraph paragraph;
		
		if(projeto.getColaboradores().size() > 0)
			paragraph = DocUtil.addParagrafo("Colaboradores do projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = DocUtil.addParagrafo("Não se tem colaboradores neste o projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		
		DocUtil.addEmptyLine(paragraph, 1);
		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getColaboradores().size(); i++)
		{
			
			Colaborador c = projeto.getColaboradores().get(i);
			paragraph = gerarParaColaborador(c, (i + 1) + ". Colaborador", 1);
			DocUtil.addEmptyLine(paragraph, 1);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0)
			paragraph = DocUtil.addParagrafo("Etapas do Projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = DocUtil.addParagrafo("Não se tem etapas adicionadas neste projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);

		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getEtapas().size(); i++) 
		{
			Etapa e = projeto.getEtapas().get(i);
			paragraph = gerarParaEtapa(e, (i + 1) + ". Etapa", 1);
			DocUtil.addEmptyLine(paragraph, 1);
			documento.add(paragraph);
			
			
			if(e.getTarefas().size() > 0)
				paragraph = DocUtil.addParagrafo("Tarefas da "+ (i + 1) + ". Etapa", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			else
				paragraph = DocUtil.addParagrafo("Não se tem tarefas adicionadas para "+ (i + 1) + ". Etapa", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			documento.add(paragraph);
			for(int j = 0; j < e.getTarefas().size(); j++) 
			{
				Tarefa t = e.getTarefas().get(j);
				paragraph = gerarParaTarefa(t, DocUtil.addTab(2) + (j + 1) + ". Tarefa", 3);
				DocUtil.addEmptyLine(paragraph, 1);
				documento.add(paragraph);
			}
		}
		
		
	}
	
	public void conteudoProjetoResumido(Projeto projeto, Document documento) throws DocumentException
	{
		conteudoProjeto("RELATORIO DE PROJETO", projeto, documento);
		
		Paragraph paragraph;
		PdfPTable table;
		
		if(projeto.getColaboradores().size() > 0) 
		{
			paragraph = DocUtil.addParagrafo("Colaboradores do Projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			DocUtil.addEmptyLine(paragraph, 1);
			table = DocUtil.addTabelaColaboradores(projeto.getColaboradores());
			paragraph.add(table);
			documento.add(paragraph);
		}else {
			paragraph = DocUtil.addParagrafo("Não ouve colaboradores no projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			DocUtil.addEmptyLine(paragraph, 1);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = DocUtil.addParagrafo("Etapas do Projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			DocUtil.addEmptyLine(paragraph, 1);
			table = DocUtil.addTabelaEtapas(writer, projeto.getEtapas());
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = DocUtil.addParagrafo("Não se tem Etapas adicionadas neste Projeto", DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			documento.add(paragraph);
		}
		
	}
	
	public void conteudoProjeto(String subTitulo,  Projeto projeto, Document documento) throws DocumentException
	{
		Paragraph paragrafo = new Paragraph();
		
		paragrafo.add(DocUtil.addSubTitulo(subTitulo));
		DocUtil.addEmptyLine(paragrafo, 2);
		paragrafo.add(DocUtil.addTitulo(projeto.getNome()));
		DocUtil.addEmptyLine(paragrafo, 1);
		paragrafo.add(DocUtil.addParagrafo(projeto.getDescricao(), DocUtil.SUBCATEGORIA_FONT, Element.ALIGN_JUSTIFIED));
		DocUtil.addEmptyLine(paragrafo, 2);
		Paragraph image = new Paragraph();
		try 
		{
			image.add(getGrafico(projeto));
		} 
		catch (Exception e) {e.printStackTrace();}
		image.setAlignment(Element.ALIGN_CENTER);
		paragrafo.add(image);
		
		paragrafo.add(new Paragraph("Criado por: " + projeto.getPessoa().getNome() + "."));
		DocUtil.addEmptyLine(paragrafo, 1);
		paragrafo.add(new Paragraph(
				"Data de ínicio do projeto: " + projeto.getData_inicio().toString() + "."
				+ "\n" + 
				"Data final do projeto: " + projeto.getData_fim().toString() + "."));
		
		
		try 
		{
			int dias = Fachada.getInstance().getBoProjeto().diferenca_Datas(projeto);
			int dias_decorridos = Fachada
					.getInstance()
					.getBoProjeto()
					.diferenca_Datas(
							projeto.getData_inicio(),
							DateUtil.getDataAtual()
							);
			paragrafo.add(new Paragraph("São " + dias + " dias ao todo e "+ 
							dias_decorridos + " dias decorridos desde a data de inicio do projeto."));
		} 
		catch (ValidacaoException e) {e.printStackTrace();}
		
		DocUtil.addEmptyLine(paragrafo, 1);
		
		//ADICIONAR PARAGRAFO
		documento.add(paragrafo);
	}
	
	
	public Paragraph gerarParaColaborador(Colaborador colaborador, String paragrafo, int tab)
	{
		Paragraph paragraph = DocUtil.addParagrafo(paragrafo, DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_LEFT);
		
		String data = colaborador.getData_ingresso().format(DocUtil.DATE_TIME_FORMATTER);
		
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Nome: "+ colaborador.getPessoa().getNome()));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "CPF: " + colaborador.getPessoa().getCpf()));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Data: "+ data));
		
		return paragraph;
	}
	
	
	public Paragraph gerarParaEtapa(Etapa etapa, String paragrafo, int tab)
	{
		Paragraph paragraph = new Paragraph(" ");

		Image image = null;
		
		try 
		{
			image = DocUtil.getGrafico(writer, etapa, "Porcentagem", 30, 35, true, false);
			image.setAlignment(Element.ALIGN_CENTER);
		} 
		catch (Exception e) {e.printStackTrace();}
		
		
		PdfPTable table = new PdfPTable(2);
		table.setTotalWidth(DocUtil.LARGURA_TOTAL - DocUtil.M_ESQ - (DocUtil.M_DIR * tab));
		table.setLockedWidth(true);
		
		PdfPCell c0 = new PdfPCell(new Phrase(paragrafo, DocUtil.SUBCATEGORIA_NEGRITO_FONT));
		c0.setHorizontalAlignment(Element.ALIGN_LEFT);
		c0.setBackgroundColor(BaseColor.WHITE);
		c0.setBorder(PdfPCell.NO_BORDER);
		c0.setPadding(10);
		table.addCell(c0);
		
		c0 = new PdfPCell(new Phrase(""));
		c0.setHorizontalAlignment(Element.ALIGN_CENTER);
		c0.setBackgroundColor(BaseColor.WHITE);
		c0.setBorder(PdfPCell.NO_BORDER);
		c0.setPadding(10);
		table.addCell(c0);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome: "+ etapa.getNome() + "\n\nDescrição: "+ etapa.getDescricao()));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setBackgroundColor(BaseColor.WHITE);
		c1.setBorder(PdfPCell.NO_BORDER);
		c1.setPadding(10);
		table.addCell(c1);
		
		c1 = new PdfPCell(image);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setBackgroundColor(BaseColor.WHITE);
		c1.setBorder(PdfPCell.NO_BORDER);
		c1.setPadding(10);
		table.addCell(c1);
		
		paragraph.add(table);
		return paragraph;
	}
	
	public Paragraph gerarParaTarefa(Tarefa tarefa, String paragrafo, int tab)
	{
		Paragraph paragraph = DocUtil.addParagrafo(paragrafo, DocUtil.SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_LEFT);
		
		String[] horaData = tarefa.getHorario().toString().split("T");
		
		String[] hora = horaData[1].split(":");
		
		if(hora.length < 3)
			hora = new String[]{hora[0], hora[1], "00"};
		
		String data = DateUtil.getDateString("dd/MM/yyyy", DateUtil.getDate(horaData[0]));
		
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Nome: "+ tarefa.getNome()));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Descrição: "+ tarefa.getDescricao()));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Estado: "+ ((tarefa.isConcluida())? "Finalizada" : "Não Finalizada")));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Prioridade: "+ tarefa.getPrioridade()));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Horário: "+ hora[0] + ":"+ hora[1] + ":" + hora[2]));
		paragraph.add(new Paragraph(DocUtil.addTab(tab) + "Data: "+ data));
		
		return paragraph;
	}
	
}
