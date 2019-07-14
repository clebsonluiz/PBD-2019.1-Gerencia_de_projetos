package br.com.pbd2019_1.utils;

import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.pbd2019_1.entidade.AbstractEtapa;
import br.com.pbd2019_1.entidade.AbstractTarefa;
import br.com.pbd2019_1.entidade.Colaborador;

public interface RelatorioUtil {

	public class MyFooter extends PdfPageEventHelper {
		Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase header = new Phrase("this is a header", ffont);
			Phrase footer = new Phrase("this is a footer", ffont);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,
					(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
					(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
		}
	}

	public class HeaderFooterPageEvent extends PdfPageEventHelper {

		private PdfTemplate t;
		private Image total;

		public void onOpenDocument(PdfWriter writer, Document document) {
			t = writer.getDirectContent().createTemplate(30, 16);
			try {
				total = Image.getInstance(t);
				total.setRole(PdfName.ARTIFACT);
			} catch (DocumentException de) {
				throw new ExceptionConverter(de);
			}
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			addHeader(writer, document);
			addFooter(writer);
		}

		private void addHeader(PdfWriter writer, Document document) {
			PdfPTable header = new PdfPTable(2);
			try {
				// set defaults
				// Array[] Tamanho das Celulas
				header.setWidths(new int[] { 7, 24 });
				header.setTotalWidth(527);
				header.setLockedWidth(true);
				header.getDefaultCell().setFixedHeight(40);
				header.getDefaultCell().setBorder(Rectangle.BOTTOM);
				header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

				// add image
				java.awt.Image imagem = ViewUtil.Icones.IMAGEM_LOGO_PROJETO_1.getImage();

				Image logo = Image.getInstance(imagem, Color.WHITE);
				logo.scalePercent(30);
				PdfPCell textLogo = new PdfPCell();
				textLogo.setPaddingBottom(10);
				textLogo.setPaddingLeft(10);
				textLogo.setBorder(Rectangle.BOTTOM);
				textLogo.setBorderColor(BaseColor.LIGHT_GRAY);
				textLogo.addElement(logo);
//	            textLogo.addElement(new Phrase("iText PDF Library for Java", new Font(Font.FontFamily.HELVETICA, 7)));
				header.addCell(textLogo);

				// add text
				PdfPCell text = new PdfPCell();
				text.setPaddingBottom(10);
				text.setPaddingLeft(10);
				text.setBorder(Rectangle.BOTTOM);
				text.setBorderColor(BaseColor.LIGHT_GRAY);
				text.setHorizontalAlignment(PdfPCell.ALIGN_TOP);
				text.addElement(new Phrase("Relatorio PDF para PBD - GESTÃO DE PROJETO",
						new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
				text.addElement(new Phrase("Tutorial para o Cabeçalho e Rodapé tirados de https://memorynotfound.com",
						new Font(Font.FontFamily.HELVETICA, 8)));
				header.addCell(text);

				// write content
//	            header.writeSelectedRows(0, -1, 34, 810, writer.getDirectContent());
				header.writeSelectedRows(0, -1, 34, 830, writer.getDirectContent());
			} catch (DocumentException de) {
				throw new ExceptionConverter(de);
			} catch (MalformedURLException e) {
				throw new ExceptionConverter(e);
			} catch (IOException e) {
				throw new ExceptionConverter(e);
			}
		}

		private void addFooter(PdfWriter writer) {
			PdfPTable footer = new PdfPTable(3);
			try {
				// set defaults
				footer.setWidths(new int[] { 24, 3, 1 });
				footer.setTotalWidth(527);
				footer.setLockedWidth(true);
				footer.getDefaultCell().setFixedHeight(40);
				footer.getDefaultCell().setBorder(Rectangle.TOP);
				footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

				// add copyright
//	            footer.addCell(new Phrase("Unidade Acadêmica de Serra Talhada - UFRPE", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GRAY)));

				// add image
				java.awt.Image imagem = ViewUtil.Icones.IMAGEM_LOGO_ITEXT.getImage();

				Image logo = Image.getInstance(imagem, Color.WHITE);
				logo.scalePercent(6);

				PdfPCell textLogo = new PdfPCell();
				textLogo.setPaddingBottom(10);
				textLogo.setPaddingLeft(10);
				textLogo.setBorder(Rectangle.TOP);
				textLogo.setBorderColor(BaseColor.LIGHT_GRAY);
				textLogo.addElement(logo);
				textLogo.addElement(new Phrase("iText PDF Library for Java", new Font(Font.FontFamily.HELVETICA, 7)));
				footer.addCell(textLogo);

				// add current page count
				footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				footer.addCell(new Phrase(String.format("Pág. %d de", writer.getPageNumber()),
						new Font(Font.FontFamily.HELVETICA, 8)));

				// add placeholder for total page count
				PdfPCell totalPageCount = new PdfPCell(total);
				totalPageCount.setBorder(Rectangle.TOP);
				totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
				footer.addCell(totalPageCount);

				// write page
				PdfContentByte canvas = writer.getDirectContent();
				canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
				footer.writeSelectedRows(0, -1, 34, 50, canvas);
				canvas.endMarkedContentSequence();
			} catch (DocumentException | IOException de) {
				throw new ExceptionConverter(de);
			}
		}

		public void onCloseDocument(PdfWriter writer, Document document) {
			int totalLength = String.valueOf(writer.getPageNumber()).length();
			int totalWidth = totalLength * 5;
			ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
					new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
					totalWidth, 6, 0);
		}
	}

	public static interface DocUtil 
	{
		public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
				.ofPattern("HH:mm:ss - dd/MM/yyyy");

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

		// 1 cm = 28.33333333333333 pontos
		public static final float CM_1 = 28.3f;

		// MARGENS do Documento
		public static final float M_ESQ = 3 * CM_1;
		public static final float M_DIR = 2 * CM_1;
		public static final float M_SUP = 3 * CM_1;
		public static final float M_INF = 2 * CM_1;

		public static final float LARGURA_TOTAL = 21 * CM_1;
		public static final float ALTURA_TOTAL = 29.7f * CM_1;

		public static Paragraph addTitulo(String titulo) {
			Anchor anchor = new Anchor(titulo, TITULO_FONT);
			anchor.setName(titulo);
			Paragraph paragraph = new Paragraph(anchor);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			return paragraph;
		}

		public static Paragraph addSubTitulo(String titulo) {
			Anchor anchor = new Anchor(titulo, CATEGORIA_FONT);
			anchor.setName(titulo);
			Paragraph paragraph = new Paragraph(anchor);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			return paragraph;
		}

		public static Paragraph addParagrafo(String conteudo, Font font, int elementAlign) {
			Paragraph paragraph = new Paragraph(conteudo, font);
			paragraph.setAlignment(elementAlign);
			return paragraph;
		}

		public static Image addImagem(java.awt.Image image) throws BadElementException, IOException {
			Image imagem = Image.getInstance(image, Color.white);
			return imagem;
		}

		public static Paragraph linhaDivisoria() {

			Paragraph paragrafo = new Paragraph(
					"______________________________________________________________________________");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			return paragrafo;
		}

		public static void addEmptyLine(Paragraph paragrafo, int numeroLinhas) {
			for (int i = 0; i < numeroLinhas; i++) {
				paragrafo.add(new Paragraph(" "));
			}
		}

		public static String addTab(int n_tabs) {
			String s = "";
			for (int i = 0; i < n_tabs; i++) {
				s += "    ";
			}
			return s;
		}

		public static Image getGrafico(PdfWriter writer, int width, int height, int scale, String explode, Map<String, Double> map, String labelGenerator, String title, java.awt.Font font, Paint[] colors) throws Exception {
			
			JFreeChart chart = ChartsUtil.getPizza(title);
			PiePlot plot = (PiePlot) chart.getPlot();
			DefaultPieDataset dataSet = (DefaultPieDataset) plot.getDataset();
			
			chart.getLegend().setItemFont(font);
			
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator(labelGenerator));
			plot.setLabelFont(font);
			
			plot.setShadowPaint(Color.gray);
			
			plot.setBackgroundPaint(Color.WHITE);
			chart.setBackgroundPaint(Color.white);
			
			map.keySet().forEach(key->
			{
				dataSet.setValue(key, map.get(key).doubleValue());
			});
			
			plot.setExplodePercent(explode, 0.15f);
			
			ChartsUtil.changeColorsPiePlot(plot, colors);
	        
	        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
	        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
	        image.scalePercent(scale);
			image.setAlignment(Element.ALIGN_CENTER);
			return image;
		}
		
		public static PdfPTable addTabelaColaboradores(List<Colaborador> colaboradores)
		{
			PdfPTable table = new PdfPTable(3);
			table.setTotalWidth(DocUtil.LARGURA_TOTAL - DocUtil.M_ESQ - DocUtil.M_DIR);
			table.setLockedWidth(true);
			
			PdfPCell c1 = new PdfPCell(new Phrase("Nome", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("CPF", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Data de Ingressão", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setPadding(10);
			table.addCell(c1);

			boolean mudarCor = false;
			
			for(Colaborador colaborador : colaboradores) {
				
				mudarCor = !mudarCor;
				BaseColor cor;
				if(mudarCor)
					cor = DocUtil.COR_CELULA_1;
				else
					cor = DocUtil.COR_CELULA_2;
				
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

				
			};
			
			return table;
		}
		
		public static Image getGrafico(PdfWriter writer, AbstractEtapa etapa, String titulo, float escala, int sizeFont, boolean simpleLabels, boolean legendVisible) throws Exception {
			
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
			
			int porc_andamento = Math.round(etapa.getPorcentagem());
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
		
		public static <T extends AbstractEtapa> PdfPTable addTabelaEtapas(PdfWriter writer, List<T> etapas)
		{
			PdfPTable table = new PdfPTable(3);
			table.setTotalWidth(DocUtil.LARGURA_TOTAL - DocUtil.M_ESQ - DocUtil.M_DIR);
			table.setLockedWidth(true);
			
			PdfPCell c1 = new PdfPCell(new Phrase("Nome", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase("Descrição", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Progresso da Etapa", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);

			boolean mudarCor = false;
			
			for(AbstractEtapa etapa : etapas)
			{
				mudarCor = !mudarCor;
				BaseColor cor;
				if(mudarCor)
					cor = DocUtil.COR_CELULA_1;
				else
					cor = DocUtil.COR_CELULA_2;
				
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
					image = getGrafico(writer, etapa, "", 20, 35, true, false);
					image.setAlignment(Element.ALIGN_CENTER);
				} 
				catch (Exception e) {e.printStackTrace();}
				
				celula = new PdfPCell(image);
				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
				celula.setBorderColor(BaseColor.WHITE);
				celula.setPadding(0);
				table.addCell(celula);
			}
			
			return table;
		}
		
		public static <T extends AbstractTarefa> PdfPTable addTabelaTarefas(List<T> tarefas) {
			PdfPTable table = new PdfPTable(5);
			table.setTotalWidth(DocUtil.LARGURA_TOTAL - DocUtil.M_ESQ - DocUtil.M_DIR);
			table.setLockedWidth(true);

			PdfPCell c1 = new PdfPCell(new Phrase("Nome", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Descrição", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Situação", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Prioridade", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setPadding(10);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Horario", DocUtil.NOME_COLUNA_FONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorderColor(BaseColor.WHITE);
			c1.setBackgroundColor(DocUtil.COR_FUNDO_NOME_COLUNA);
			c1.setPadding(10);
			table.addCell(c1);

			Boolean mudarCor = false;

			for (T tarefa : tarefas) {
				mudarCor = !mudarCor;

				BaseColor cor;
				if (mudarCor)
					cor = DocUtil.COR_CELULA_1;
				else
					cor = DocUtil.COR_CELULA_2;

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

				String status = (tarefa.isConcluida()) ? "Finalizada" : "Não Finalizada";
					
				if(tarefa.getHorario().isBefore(LocalDateTime.now()) && !tarefa.isConcluida())
					status = "ATRASADA";
				
				celula = new PdfPCell(new Phrase(status));
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

				String[] horaData = tarefa.getHorario().toString().split("T");

				String[] hora = horaData[1].split(":");

				if (hora.length < 3)
					hora = new String[] { hora[0], hora[1], "00" };

				String data = DateUtil.getDateString("dd/MM/yyyy", DateUtil.getDate(horaData[0]));
				String horario = hora[0] + ":" + hora[1] + ":" + hora[2];

				celula = new PdfPCell(new Phrase(data + " as " + horario));
				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
				celula.setBorderColor(BaseColor.WHITE);
				celula.setBackgroundColor(cor);
				celula.setPadding(10);
				table.addCell(celula);
			}

			return table;
		}
	}

}
