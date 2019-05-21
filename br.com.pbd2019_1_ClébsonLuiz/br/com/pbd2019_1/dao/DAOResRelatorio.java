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

	
	public static Font TITULO_FONT = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);
	public static Font CATEGORIA_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
	public static Font NORMAL_VERMELHA_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
	public static Font SUBCATEGORIA_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.NORMAL);
	public static Font SUBCATEGORIA_NEGRITO_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
	public static Font NORMAL_NEGRITO_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	public static Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
	
	public static BaseColor COR_FUNDO_NOME_COLUNA = BaseColor.DARK_GRAY;
	public static Font NOME_COLUNA_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.WHITE);
	public static BaseColor COR_CELULA_1 = new BaseColor(245, 245, 245);
	public static BaseColor COR_CELULA_2 = new BaseColor(220, 220, 220);
	
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
	
	private boolean mudarCor;
	
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
	
	public void gerarRelatorio(int op, Projeto projeto, String pathCaminho) throws FileNotFoundException, DocumentException
	{
		Document documento = criarDocumento(pathCaminho);
		
		switch (op) 
		{
			case 0:
				gerarRelatorioResumido(projeto, documento);
				break;
			case 1:
				gerarRelatorioCompleto(projeto, documento);
				break;
			case 2:
				gerarRelatorioCompletoTabelas(projeto, documento);
				break;
		}
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
	
	public void gerarRelatorioResumido(Projeto projeto, Document documento) throws DocumentException {
		conteudoProjetoResumido(projeto, documento);
	}
	
	public void gerarRelatorioCompleto(Projeto projeto, Document documento) throws DocumentException {
		conteudoProjetoCompleto(projeto, documento);
	}
	
	public void gerarRelatorioCompletoTabelas(Projeto projeto, Document documento) throws DocumentException {
		conteudoProjetoCompletoTabelas(projeto, documento);
	}
	
	
	public Image getGrafico(Projeto projeto) throws Exception {
		
		final java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 20);
		
		JFreeChart chart = ChartsUtil.getPizza("ANDAMENTO DO PRAZO DO PROJETO (Em %)");
		PiePlot plot = (PiePlot) chart.getPlot();
		DefaultPieDataset dataSet = (DefaultPieDataset) plot.getDataset();
		
		chart.getLegend().setItemFont(font);
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator( "({2})"));
		plot.setLabelFont(font);
		
		plot.setShadowPaint(Color.gray);
		
		plot.setBackgroundPaint(Color.WHITE);
		chart.setBackgroundPaint(Color.white);
		
		int porc_andamento = Fachada.getInstance().getBoProjeto().andamento_Projeto(projeto);
		int porc_restante = 100 - porc_andamento;
		
		dataSet.setValue("Atual", porc_andamento);
		dataSet.setValue("Restante", porc_restante);
		plot.setExplodePercent("Atual", 0.15f);
		
		ChartsUtil.changeColorsPiePlot(plot, ChartsUtil.CORES_1);
		
		int width = 1000;
        int height = 400;
        
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        image.scalePercent(50);
		image.setAlignment(Element.ALIGN_CENTER);
		return image;
	}
	
	public Image getGrafico(Etapa etapa, String titulo, float escala, int sizeFont, boolean simpleLabels, boolean legendVisible) throws Exception {
		
		final java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, sizeFont);
		
		JFreeChart chart = ChartsUtil.getPizza(titulo);
		PiePlot plot = (PiePlot) chart.getPlot();
		DefaultPieDataset dataSet = (DefaultPieDataset) plot.getDataset();
		
		chart.getTitle().setFont(font);
		chart.getLegend().setItemFont(font);
		chart.getLegend().setVisible(legendVisible);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} : ({2})"));
		plot.setLabelFont(font);
		plot.setSimpleLabels(simpleLabels);
		plot.setBackgroundPaint(Color.WHITE);
		chart.setBackgroundPaint(Color.white);
		
		
		
		int porc_andamento = Math.round(etapa.getPorcentagem_andamento());
		int porc_restante = 100 - porc_andamento;
		
		dataSet.setValue("Completado", porc_andamento);
		dataSet.setValue("Restante", porc_restante);
		
		plot.setShadowPaint(Color.gray);
		plot.setExplodePercent("Completado", 0.10f);
		
		ChartsUtil.changeColorsPiePlot(plot, ChartsUtil.CORES_2);
		
		int width = 800;
        int height = 400;
        
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        image.scalePercent(escala);
		image.setAlignment(Element.ALIGN_CENTER);
		return image;
	}
	
	
	public void conteudoProjetoCompletoTabelas(Projeto projeto, Document documento) throws DocumentException
	{
		conteudoProjeto("RELATORIO DE PROJETO", projeto, documento);
		
		Paragraph paragraph;
		
		if(projeto.getColaboradores().size() > 0)
			paragraph = addParagrafo("Colaboradores do projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = addParagrafo("Não se tem colaboradores neste o projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		
		addEmptyLine(paragraph, 1);
		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getColaboradores().size(); i++)
		{
			
			Colaborador c = projeto.getColaboradores().get(i);
			paragraph = gerarParaColaborador(c, (i + 1) + ". Colaborador", 1);
			addEmptyLine(paragraph, 1);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0)
			paragraph = addParagrafo("Etapas do Projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = addParagrafo("Não se tem etapas adicionadas neste o projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getEtapas().size(); i++) 
		{
			
			Etapa e = projeto.getEtapas().get(i);
			paragraph = gerarParaEtapa(e, (i + 1) + ". Etapa", 1);
			addEmptyLine(paragraph, 1);
			documento.add(paragraph);
			
			
			if(e.getTarefas().size() > 0) {
				
				paragraph = addParagrafo("Tarefas da "+ (i + 1) + ". Etapa", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
				addEmptyLine(paragraph, 1);
				documento.add(paragraph);
				documento.add(addTabelaTarefas(e.getTarefas()));
			}
			else {
				paragraph = addParagrafo("Não se tem tarefas adicionadas para a "+ (i + 1) + ". Etapa", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
				documento.add(paragraph);
			}
			
		}
		
		
	}
	
	public void conteudoProjetoCompleto(Projeto projeto, Document documento) throws DocumentException
	{
		conteudoProjeto("RELATORIO DE PROJETO", projeto, documento);
		
		Paragraph paragraph;
		
		if(projeto.getColaboradores().size() > 0)
			paragraph = addParagrafo("Colaboradores do projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = addParagrafo("Não se tem colaboradores neste o projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		
		addEmptyLine(paragraph, 1);
		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getColaboradores().size(); i++)
		{
			
			Colaborador c = projeto.getColaboradores().get(i);
			paragraph = gerarParaColaborador(c, (i + 1) + ". Colaborador", 1);
			addEmptyLine(paragraph, 1);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0)
			paragraph = addParagrafo("Etapas do Projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
		else
			paragraph = addParagrafo("Não se tem etapas adicionadas neste projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);

		documento.add(paragraph);
		
		for(int i = 0; i < projeto.getEtapas().size(); i++) 
		{
			Etapa e = projeto.getEtapas().get(i);
			paragraph = gerarParaEtapa(e, (i + 1) + ". Etapa", 1);
			addEmptyLine(paragraph, 1);
			documento.add(paragraph);
			
			
			if(e.getTarefas().size() > 0)
				paragraph = addParagrafo("Tarefas da "+ (i + 1) + ". Etapa", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			else
				paragraph = addParagrafo("Não se tem tarefas adicionadas para "+ (i + 1) + ". Etapa", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			documento.add(paragraph);
			for(int j = 0; j < e.getTarefas().size(); j++) 
			{
				Tarefa t = e.getTarefas().get(j);
				paragraph = gerarParaTarefa(t, addTab(2) + (j + 1) + ". Tarefa", 3);
				addEmptyLine(paragraph, 1);
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
			paragraph = addParagrafo("Colaboradores do Projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			addEmptyLine(paragraph, 1);
			table = addTabelaColaboradores(projeto.getColaboradores());
			paragraph.add(table);
			documento.add(paragraph);
		}else {
			paragraph = addParagrafo("Não ouve colaboradores no projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			addEmptyLine(paragraph, 1);
			documento.add(paragraph);
		}
		
		if(projeto.getEtapas().size() > 0) 
		{
			paragraph = addParagrafo("Etapas do Projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			addEmptyLine(paragraph, 1);
			table = addTabelaEtapas(projeto.getEtapas());
			paragraph.add(table);
			documento.add(paragraph);
		}else { 
			paragraph = addParagrafo("Não se tem Etapas adicionadas neste Projeto", SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_CENTER);
			documento.add(paragraph);
		}
		
	}
	
	public void addConteudoProjeto(Projeto projeto, Document documento) throws DocumentException
	{
		conteudoProjeto("RELATORIO DE PROJETO", projeto, documento);
		
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
	
	
	public void conteudoProjeto(String subTitulo,  Projeto projeto, Document documento) throws DocumentException
	{
		Paragraph paragrafo = new Paragraph();
		
		paragrafo.add(addSubTitulo(subTitulo));
		addEmptyLine(paragrafo, 2);
		paragrafo.add(addTitulo(projeto.getNome()));
		addEmptyLine(paragrafo, 1);
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
	}
	
	
	public Paragraph gerarParaColaborador(Colaborador colaborador, String paragrafo, int tab)
	{
		Paragraph paragraph = addParagrafo(paragrafo, SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_LEFT);
		
		String data = DateUtil.getDateString("HH:mm:ss - dd/MM/yyyy", colaborador.getData_ingresso());
		
		paragraph.add(new Paragraph(addTab(tab) + "Nome: "+ colaborador.getPessoa().getNome()));
		paragraph.add(new Paragraph(addTab(tab) + "Privilégio: "+ colaborador.getPrivilegio()));
		paragraph.add(new Paragraph(addTab(tab) + "CPF: " + colaborador.getPessoa().getCpf()));
		paragraph.add(new Paragraph(addTab(tab) + "Data: "+ data));
		
		return paragraph;
	}
	
	
	public Paragraph gerarParaEtapa(Etapa etapa, String paragrafo, int tab)
	{
		Paragraph paragraph = new Paragraph(" ");

		Image image = null;
		
		try 
		{
			image = getGrafico(etapa, "Porcentagem", 30, 35, true, false);
			image.setAlignment(Element.ALIGN_CENTER);
		} 
		catch (Exception e) {e.printStackTrace();}
		
		
		PdfPTable table = new PdfPTable(2);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - (M_DIR * tab));
		table.setLockedWidth(true);
		
		PdfPCell c0 = new PdfPCell(new Phrase(paragrafo, SUBCATEGORIA_NEGRITO_FONT));
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
		Paragraph paragraph = addParagrafo(paragrafo, SUBCATEGORIA_NEGRITO_FONT, Element.ALIGN_LEFT);
		
		String[] horaData = tarefa.getHorario_tarefa().split("T");
		
		String[] hora = horaData[1].split(":");
		
		if(hora.length < 3)
			hora = new String[]{hora[0], hora[1], "00"};
		
		String data = DateUtil.getDateString("dd/MM/yyyy", DateUtil.getDate(horaData[0]));
		
		paragraph.add(new Paragraph(addTab(tab) + "Nome: "+ tarefa.getNome()));
		paragraph.add(new Paragraph(addTab(tab) + "Descrição: "+ tarefa.getDescricao()));
		paragraph.add(new Paragraph(addTab(tab) + "Estado: "+ ((tarefa.isConcluida())? "Finalizada" : "Não Finalizada")));
		paragraph.add(new Paragraph(addTab(tab) + "Prioridade: "+ tarefa.getPrioridade()));
		paragraph.add(new Paragraph(addTab(tab) + "Horário: "+ hora[0] + ":"+ hora[1] + ":" + hora[2]));
		paragraph.add(new Paragraph(addTab(tab) + "Data: "+ data));
		
		return paragraph;
	}
	
	public PdfPTable addTabelaColaboradores(List<Colaborador> colaboradores)
	{
		PdfPTable table = new PdfPTable(4);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - M_DIR);
		table.setLockedWidth(true);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("CPF", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Data de Ingressão", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Privilégio", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setPadding(10);
		table.addCell(c1);

		
		mudarCor = false;
		colaboradores.forEach(colaborador->{
			
			mudarCor = !mudarCor;
			BaseColor cor;
			if(mudarCor)
				cor = COR_CELULA_1;
			else
				cor = COR_CELULA_2;
			
			PdfPCell celula = new PdfPCell(new Phrase(colaborador.getPessoa().getNome()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(colaborador.getPessoa().getCpf()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase((colaborador.getData_ingresso().toString())));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(colaborador.getPrivilegio()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);
			
		});
		
		return table;
	}
	
	public PdfPTable addTabelaEtapas(List<Etapa> etapas)
	{
		PdfPTable table = new PdfPTable(3);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - M_DIR);
		table.setLockedWidth(true);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Descrição", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Progresso da Etapa", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);

		mudarCor = false;
		etapas.forEach(etapa->{
			
			mudarCor = !mudarCor;
			BaseColor cor;
			if(mudarCor)
				cor = COR_CELULA_1;
			else
				cor = COR_CELULA_2;
			
			PdfPCell celula = new PdfPCell(new Phrase(etapa.getNome()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(etapa.getDescricao()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			Image image = null;
			
			try 
			{
				image = getGrafico(etapa, "", 20, 35, true, false);
				image.setAlignment(Element.ALIGN_CENTER);
			} 
			catch (Exception e) {e.printStackTrace();}
			
			celula = new PdfPCell(image);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setPadding(0);
			table.addCell(celula);
		});
		
		return table;
	}
	
	public PdfPTable addTabelaTarefas(List<Tarefa> tarefas)
	{
		PdfPTable table = new PdfPTable(5);
		table.setTotalWidth(LARGURA_TOTAL - M_ESQ - M_DIR);
		table.setLockedWidth(true);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Nome", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Descrição", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Situação", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Prioridade", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setPadding(10);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Horario", NOME_COLUNA_FONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBorderColor(BaseColor.WHITE);
		c1.setBackgroundColor(COR_FUNDO_NOME_COLUNA);
		c1.setPadding(10);
		table.addCell(c1);

		mudarCor = false;
		tarefas.forEach(tarefa->{
			
			mudarCor = !mudarCor;
			BaseColor cor;
			if(mudarCor)
				cor = COR_CELULA_1;
			else
				cor = COR_CELULA_2;
			
			PdfPCell celula = new PdfPCell(new Phrase(tarefa.getNome()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBackgroundColor(cor);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(tarefa.getDescricao()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase((tarefa.isConcluida())? "Finalizada": "Não Finalizada"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			celula = new PdfPCell(new Phrase(tarefa.getPrioridade()));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
			table.addCell(celula);

			
			String[] horaData = tarefa.getHorario_tarefa().split("T");
			
			String[] hora = horaData[1].split(":");
			
			if(hora.length < 3)
				hora = new String[]{hora[0], hora[1], "00"};
			
			String data = DateUtil.getDateString("dd/MM/yyyy", DateUtil.getDate(horaData[0]));
			String horario = hora[0] + ":" + hora[1] + ":" + hora[2];
			
			celula = new PdfPCell(new Phrase(data + " as " + horario));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.setBorderColor(BaseColor.WHITE);
			celula.setBackgroundColor(cor);
			celula.setPadding(10);
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
	
	public String addTab(int n_tabs) 
	{
		String s = "";
		for (int i = 0; i < n_tabs; i++) {
			s += "    ";
		}
		return s;
	}
	
	
}
