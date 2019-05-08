package br.com.pbd2019_1.dao;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.entidade.Tarefa;

public class DAOResRelatorio {

	
	public static Font TITULO_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	public static Font CATEGORIA_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	public static Font NORMAL_VERMELHA_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	public static Font SUBCATEGORIA_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	public static Font NORMAL_NEGRITO_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	   
	public static BaseColor COR_NOME_CELULA = new BaseColor(0xC0, 0xC0, 0xC0);
	
	private static DAOResRelatorio instance;
	
	public static DAOResRelatorio getInstance() {
		if(instance == null)
			instance = new DAOResRelatorio();
		return instance;
	}

	private Document criarDocumento(String pathCaminho) throws FileNotFoundException, DocumentException
	{
		Document documento = new Document();
		PdfWriter.getInstance(documento, new FileOutputStream(pathCaminho + ".pdf"));
		documento.open();
		return documento;
	}
	
	public void gerarRelatorio(Projeto projeto, String pathCaminho) throws FileNotFoundException, DocumentException
	{
		Document documento = criarDocumento(pathCaminho);
		
		addConteudoProjeto(projeto, documento);
		
		documento.close();
	}
	
	public Paragraph addTitulo(String titulo)
	{
		Anchor anchor = new Anchor(titulo, TITULO_FONT);
		anchor.setName(titulo);
		Paragraph paragraph = new Paragraph(anchor);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		return paragraph;
	}
	
	public Paragraph addSubTitulo(String titulo)
	{
		Anchor anchor = new Anchor(titulo, CATEGORIA_FONT);
		anchor.setName(titulo);
		Paragraph paragraph = new Paragraph(anchor);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		return paragraph;
	}
	
	public Paragraph addParagrafo(String conteudo, Font font, int elementAlign) {
		Paragraph paragraph = new Paragraph(conteudo, font);
		paragraph.setAlignment(elementAlign);
		return paragraph;
	}
	
	public Image addImagem(java.awt.Image image) throws BadElementException, IOException
	{
		Image imagem = Image.getInstance(image, Color.white);
		return imagem;
	}
	
	public void gerarRelatorioResumido(Projeto projeto, Document documento) {
		//TODO - Gerar relatorio resumido do projeto.
	}
	
	public void gerarRelatorioSemiCompleto(Projeto projeto, Document documento) {
		//TODO - Gerar relatorio semi-completo do projeto.
	}
	
	public void gerarRelatorioCompleto(Projeto projeto, Document documento) {
		//TODO - Gerar relatorio Completo do projeto.
	}
	
	public void addConteudoProjeto(Projeto projeto, Document documento) throws DocumentException
	{
		Paragraph paragrafo = new Paragraph();
		
		paragrafo.add(addSubTitulo("RELATORIO DE PROJETO"));
		addEmptyLine(paragrafo, 6);
		paragrafo.add(addTitulo(projeto.getNome()));
		paragrafo.add(addParagrafo(projeto.getDescricao(), NORMAL_FONT, Element.ALIGN_JUSTIFIED));
		
		paragrafo.add(new Paragraph("Criado por: " + projeto.getPessoa().getNome()));
		addEmptyLine(paragrafo, 1);
		paragrafo.add(new Paragraph(
				"Data de Inicio: " + projeto.getData_inicio().toString() 
				+ " e " + 
				"Data de Final: " + projeto.getData_fim().toString()));
		
		addEmptyLine(paragrafo, 1);
		Paragraph paragraph;
		
		if(projeto.getColaboradores().size() > 0) 
		{
			paragraph = addParagrafo("Colaboradores do Projeto", NORMAL_NEGRITO_FONT, Element.ALIGN_CENTER);
			addEmptyLine(paragraph, 1);
			paragraph.add(addTabelaColaboradores(projeto.getColaboradores()));
		}else 
			paragraph = addParagrafo("Não ouve colaboradores no projeto", NORMAL_NEGRITO_FONT, Element.ALIGN_CENTER);
		
		paragrafo.add(paragraph);
		
		/*
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			paragraph.add(addTabelaEtapas(projeto.getEtapas()));
		}else 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
		
		paragraph.setFont(SUBCATEGORIA_FONT);
		paragrafo.add(paragraph);
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			paragraph.add(addTabelaEtapas(projeto.getEtapas()));
		}else 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
		*/
		
		documento.add(paragrafo);
	}
	
	public PdfPTable addTabelaColaboradores(List<Colaborador> colaboradores)
	{
		PdfPTable table = new PdfPTable(7);
		table.setTotalWidth(520);
		table.setLockedWidth(true);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("CPF"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Data de Ingressão"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Privilégio"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		table.setHeaderRows(1);
		
		colaboradores.forEach(colaborador->{
			
			PdfPCell celula = new PdfPCell(new Phrase(colaborador.getPessoa().getNome()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(colaborador.getPessoa().getCpf()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase((colaborador.getData_ingresso().toString())));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(colaborador.getPrivilegio()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);
			
		});
		
		return table;
	}
	
	public PdfPTable addTabelaEtapas(List<Etapa> etapas)
	{
		PdfPTable table = new PdfPTable(7);
		table.setTotalWidth(520);
		table.setLockedWidth(true);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Descrição"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Porcentagem do Andamento"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		table.setHeaderRows(1);
		
		etapas.forEach(etapa->{
			PdfPCell celula = new PdfPCell(new Phrase(etapa.getNome()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(etapa.getDescricao()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(""+ etapa.getPorcentagem_andamento() + "%"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);
		});
		
		return table;
	}
	
	public PdfPTable addTabelaTarefas(List<Tarefa> tarefas)
	{
		PdfPTable table = new PdfPTable(7);
		table.setTotalWidth(520);
		table.setLockedWidth(true);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Descrição"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Situação"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Prioridade"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Horario"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_NOME_CELULA);
		table.addCell(c1);

		table.setHeaderRows(1);
		
		tarefas.forEach(tarefa->{
			
			PdfPCell celula = new PdfPCell(new Phrase(tarefa.getNome()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(tarefa.getDescricao()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase((tarefa.isConcluida())? "Finalizada": "Não Finalizada"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(tarefa.getPrioridade()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(tarefa.getHorario_tarefa()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celula);
		});
		
		return table;
	}
	
	
	public Paragraph linhaDivisoria() {

		Paragraph paragrafo = new Paragraph("______________________________________________________________________________");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		return paragrafo;
	}
	
	public void addEmptyLine(Paragraph paragrafo, int numeroLinhas) {
		for (int i = 0; i < numeroLinhas; i++) {
			paragrafo.add(new Paragraph(" "));
		}
	}
	
	
	
}
