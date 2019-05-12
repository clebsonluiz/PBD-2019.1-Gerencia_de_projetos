package br.com.pbd2019_1.dao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
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

public class DAOResRelatorio {

	
	public static Font TITULO_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	public static Font CATEGORIA_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	public static Font NORMAL_VERMELHA_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	public static Font SUBCATEGORIA_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);
	public static Font SUBCATEGORIA_NEGRITO_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	public static Font NORMAL_NEGRITO_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	
	public static BaseColor COR_NOME_CELULA = new BaseColor(0xC0, 0xC0, 0xC0);
	
	
	//1 cm = 28.33333333333333 pontos
	public static final float CM_1 = 28.3f;
	
	//MARGENS do Documento
	public static final float M_ESQ = 3 * CM_1;
	public static final float M_DIR = 2 * CM_1;
	public static final float M_SUP = 3 * CM_1;
	public static final float M_INF = 2 * CM_1;
	
	public static final float LARGURA_TOTAL = 21 * CM_1;
	public static final float ALTURA_TOTAL = 29.7f * CM_1;
	
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
		documento = new Document(PageSize.A4, M_ESQ, M_DIR, M_SUP, M_INF);
		writer = PdfWriter.getInstance(documento, new FileOutputStream(pathCaminho + ".pdf"));
		
//		RelatorioUtil.MyFooter event = new RelatorioUtil.MyFooter();
		
		RelatorioUtil.HeaderFooterPageEvent event = new RelatorioUtil.HeaderFooterPageEvent();
		
        writer.setPageEvent(event);
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
	
	
	public Image getGrafico(Projeto projeto) throws Exception {
		
		JFreeChart chart = ChartsUtil.getPizza("ANDAMENTO DO PROJETO (Em %)");
		PiePlot plot = (PiePlot) chart.getPlot();
		DefaultPieDataset dataSet = (DefaultPieDataset) plot.getDataset();
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator( "({2})"));
		
		plot.setBackgroundPaint(Color.WHITE);
		chart.setBackgroundPaint(Color.white);
		
		int porc_andamento = Fachada.getInstance().getBoProjeto().andamento_Projeto(projeto);
		int porc_restante = 100 - porc_andamento;
		
		dataSet.setValue("Atual", porc_andamento);
		dataSet.setValue("Restante", porc_restante);
		
		ChartsUtil.changeColorsPiePlot(plot, ChartsUtil.CORES_1);
		
		int width = 500;
        int height = 200;
        
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
		image.setAlignment(Element.ALIGN_CENTER);
		return image;
	}
	
	public void addConteudoProjeto(Projeto projeto, Document documento) throws DocumentException
	{
		Paragraph paragrafo = new Paragraph();
		
		paragrafo.add(addSubTitulo("RELATORIO DE PROJETO"));
		addEmptyLine(paragrafo, 2);
		paragrafo.add(addTitulo(projeto.getNome()));
		paragrafo.add(addParagrafo(projeto.getDescricao(), SUBCATEGORIA_FONT, Element.ALIGN_JUSTIFIED));
		addEmptyLine(paragrafo, 2);
		Paragraph image = new Paragraph();
		try 
		{
			image.add(getGrafico(projeto));
		} 
		catch (Exception e) {e.printStackTrace();}
		image.setAlignment(Element.ALIGN_CENTER);
		paragrafo.add(image);
		
		paragrafo.add(new Paragraph("Criado por: " + projeto.getPessoa().getNome() + "."));
		addEmptyLine(paragrafo, 1);
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
		
		addEmptyLine(paragrafo, 1);
		
		//ADICIONAR PARAGRAFO
		documento.add(paragrafo);
		
		Paragraph paragraph;
		PdfPTable table;
		
		if(projeto.getColaboradores().size() > 0) 
		{
			paragraph = addParagrafo("Colaboradores do Projeto", NORMAL_NEGRITO_FONT, Element.ALIGN_CENTER);
			addEmptyLine(paragraph, 1);
			table = addTabelaColaboradores(projeto.getColaboradores());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else {
			paragraph = addParagrafo("Não ouve colaboradores no projeto", NORMAL_NEGRITO_FONT, Element.ALIGN_CENTER);
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}		
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}		
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = new Paragraph("Etapas do Projeto");
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = new Paragraph("Não tem Etapas no o projeto");
			paragraph.setFont(SUBCATEGORIA_NEGRITO_FONT);
			documento.add(paragraph);
		}
		
		
		
	}
	
	
	public Paragraph gerarParaColaborador(Colaborador colaborador)
	{
		Paragraph paragraph = new Paragraph(" ");
		
		String data = DateUtil.getDateString("HH:mm:ss - dd/MM/yyyy", colaborador.getData_ingresso());
		
		paragraph.add(new Phrase("Nome: "+ colaborador.getPessoa().getNome()));
		paragraph.add(new Phrase("Privilégio: "+ colaborador.getPrivilegio()));
		paragraph.add(new Phrase("Estado: " + colaborador.getPessoa().getCpf()));
		paragraph.add(new Phrase("Data: "+ data));
		
		return paragraph;
	}
	
	
	public Paragraph gerarParaEtapa(Etapa etapa)
	{
		Paragraph paragraph = new Paragraph(" ");

		paragraph.add(new Phrase("Nome: "+ etapa.getNome()));
		paragraph.add(new Phrase("Descrição: "+ etapa.getDescricao()));
		paragraph.add(new Phrase("Porcentagem: "+ etapa.getPorcentagem_andamento() + "%"));
		
		return paragraph;
	}
	
	public Paragraph gerarParaTarefa(Tarefa tarefa)
	{
		Paragraph paragraph = new Paragraph(" ");
		
		String[] horaData = tarefa.getHorario_tarefa().split("T");
		
		String[] hora = horaData[1].split(":");
		
		if(hora.length < 3)
			hora = new String[]{hora[0], hora[1], "00"};
		
		String data = DateUtil.getDateString("dd/MM/yyyy", DateUtil.getDate(horaData[0]));
		
		paragraph.add(new Phrase("Nome: "+ tarefa.getNome()));
		paragraph.add(new Phrase("Descrição: "+ tarefa.getDescricao()));
		paragraph.add(new Phrase("Estado: "+ ((tarefa.isConcluida())? "Finalizada" : "Não Finalizada")));
		paragraph.add(new Phrase("Prioridade: "+ tarefa.getPrioridade()));
		paragraph.add(new Phrase("Horário: "+ hora[0] + ":"+ hora[1] + ":" + hora[2]));
		paragraph.add(new Phrase("Data: "+ data));
		
		return paragraph;
	}
	
	public PdfPTable addTabelaColaboradores(List<Colaborador> colaboradores)
	{
		PdfPTable table = new PdfPTable(4);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - M_DIR);
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
		PdfPTable table = new PdfPTable(3);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - M_DIR);
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
		PdfPTable table = new PdfPTable(5);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - M_DIR);
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
