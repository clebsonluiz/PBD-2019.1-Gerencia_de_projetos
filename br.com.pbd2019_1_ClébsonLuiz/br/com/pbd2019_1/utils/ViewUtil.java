package br.com.pbd2019_1.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Image;

import br.com.pbd2019_1.dao.DAOConfigDefault;
import br.com.pbd2019_1.entidade.ConfigDefault;

public interface ViewUtil {
	
	
	public static interface Fonts{
		
		public static interface Times{
			
			public static final Font TIMES_PEQUENO = new Font("Times New Roman", Font.PLAIN, 13);
			public static final Font TIMES_PEQUENO_I = new Font("Times New Roman", Font.ITALIC, 13);
			public static final Font TIMES_PEQUENO_B = new Font("Times New Roman", Font.BOLD, 13);
			public static final Font TIMES_PEQUENO_B_I = new Font("Times New Roman", Font.BOLD+Font.ITALIC, 13);
			
			public static final Font TIMES_TITULO = new Font("Times New Roman", Font.PLAIN, 20);
			public static final Font TIMES_TITULO_I = new Font("Times New Roman", Font.ITALIC, 20);
			public static final Font TIMES_TITULO_B = new Font("Times New Roman", Font.BOLD, 20);
			public static final Font TIMES_TITULO_B_I = new Font("Times New Roman", Font.BOLD+Font.ITALIC, 20);
			
		}
		
		public static interface Arial{
			
			public static final Font ARIAL_PEQUENO = new Font("Arial", Font.PLAIN, 13);
			public static final Font ARIAL_PEQUENO_I = new Font("Arial", Font.ITALIC, 13);
			public static final Font ARIAL_PEQUENO_B = new Font("Arial", Font.BOLD, 13);
			public static final Font ARIAL_PEQUENO_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 13);
			
			public static final Font ARIAL_MEDIO = new Font("Arial", Font.PLAIN, 15);
			public static final Font ARIAL_MEDIO_I = new Font("Arial", Font.ITALIC, 15);
			public static final Font ARIAL_MEDIO_B = new Font("Arial", Font.BOLD, 15);
			public static final Font ARIAL_MEDIO_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 15);
			
			public static final Font ARIAL_TITULO = new Font("Arial", Font.PLAIN, 20);
			public static final Font ARIAL_TITULO_I = new Font("Arial", Font.ITALIC, 20);
			public static final Font ARIAL_TITULO_B = new Font("Arial", Font.BOLD, 20);
			public static final Font ARIAL_TITULO_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 20);
			
			public static final Font ARIAL_TITULO_GIGANTE = new Font("Arial", Font.PLAIN, 26);
			public static final Font ARIAL_TITULO_GIGANTE_I = new Font("Arial", Font.ITALIC, 26);
			public static final Font ARIAL_TITULO_GIGANTE_B = new Font("Arial", Font.BOLD, 26);
			public static final Font ARIAL_TITULO_GIGANTE_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 26);
			
			
		}
		
	} 
	
	
	public static interface Bordas{
		
		/** https://stackoverflow.com/questions/15025092/border-with-rounded-corners-transparency*/
		public static class TextBubbleBorder extends AbstractBorder {

			private static final long serialVersionUID = 1L;

			private Color color;
		    private int thickness = 4;
		    private int radii = 8;
		    private int pointerSize = 7;
		    private Insets insets = null;
		    private BasicStroke stroke = null;
		    private int strokePad;
		    private int pointerPad = 4;
		    private boolean left = true;
		    RenderingHints hints;

		    public TextBubbleBorder(
		            Color color) {
		        this(color, 4, 8, 7);
		    }

		    public TextBubbleBorder(
		            Color color, int thickness, int radii, int pointerSize) {
		        this.thickness = thickness;
		        this.radii = radii;
		        this.pointerSize = pointerSize;
		        this.color = color;

		        stroke = new BasicStroke(thickness);
		        strokePad = thickness / 2;

		        hints = new RenderingHints(
		                RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);

		        int pad = radii + strokePad;
		        int bottomPad = pad + pointerSize + strokePad;
		        insets = new Insets(pad, pad, bottomPad, pad);
		    }

		    public TextBubbleBorder(
		            Color color, int thickness, int radii, int pointerSize, boolean left) {
		        this(color, thickness, radii, pointerSize);
		        this.left = left;
		    }

		    @Override
		    public Insets getBorderInsets(Component c) {
		        return insets;
		    }

		    @Override
		    public Insets getBorderInsets(Component c, Insets insets) {
		        return getBorderInsets(c);
		    }

		    @Override
		    public void paintBorder(
		            Component c,
		            Graphics g,
		            int x, int y,
		            int width, int height) {

		        Graphics2D g2 = (Graphics2D) g;

		        int bottomLineY = height - thickness - pointerSize;

		        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
		                0 + strokePad,
		                0 + strokePad,
		                width - thickness,
		                bottomLineY,
		                radii,
		                radii);

		        Polygon pointer = new Polygon();

		        if (left) {
		            // left point
		            pointer.addPoint(
		                    strokePad + radii + pointerPad,
		                    bottomLineY);
		            // right point
		            pointer.addPoint(
		                    strokePad + radii + pointerPad + pointerSize,
		                    bottomLineY);
		            // bottom point
		            pointer.addPoint(
		                    strokePad + radii + pointerPad + (pointerSize / 2),
		                    height - strokePad);
		        } else {
		            // left point
		            pointer.addPoint(
		                    width - (strokePad + radii + pointerPad),
		                    bottomLineY);
		            // right point
		            pointer.addPoint(
		                    width - (strokePad + radii + pointerPad + pointerSize),
		                    bottomLineY);
		            // bottom point
		            pointer.addPoint(
		                    width - (strokePad + radii + pointerPad + (pointerSize / 2)),
		                    height - strokePad);
		        }

		        Area area = new Area(bubble);
		        area.add(new Area(pointer));

		        g2.setRenderingHints(hints);

		        // Paint the BG color of the parent, everywhere outside the clip
		        // of the text bubble.
		        Component parent  = c.getParent();
		        if (parent!=null) {
		            Color bg = parent.getBackground();
		            Rectangle rect = new Rectangle(0,0,width, height);
		            Area borderRegion = new Area(rect);
		            borderRegion.subtract(area);
		            g2.setClip(borderRegion);
		            g2.setColor(bg);
		            g2.fillRect(0, 0, width, height);
		            g2.setClip(null);
		        }

		        g2.setColor(color);
		        g2.setStroke(stroke);
		        g2.draw(area);
		    }
		}
		
		@SuppressWarnings("serial")
		public static class BordaArredondada extends AbstractBorder{
			private Color color;
		    private int thickness = 4;
		    private int radii = 8;
		    private int pointerSize = 7;
		    private Insets insets = null;
		    private BasicStroke stroke = null;
		    private int strokePad;
		    RenderingHints hints;

		    public BordaArredondada(
		            Color color) {
		        this(color, 1, 8, 7);
		    }

		    public BordaArredondada(
		            Color color, int thickness, int radii, int pointerSize) {
		        this.thickness = thickness;
		        this.radii = radii;
		        this.pointerSize = pointerSize;
		        this.color = color;

		        stroke = new BasicStroke(thickness);
		        strokePad = thickness / 2;

		        hints = new RenderingHints(
		                RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);

		        int pad = radii + strokePad;
		        int bottomPad = pad + pointerSize + strokePad;
		        insets = new Insets(pad, pad, bottomPad, pad);
		    }

		    @Override
		    public Insets getBorderInsets(Component c) {
		        return insets;
		    }

		    @Override
		    public Insets getBorderInsets(Component c, Insets insets) {
		        return getBorderInsets(c);
		    }

		    @Override
		    public void paintBorder(
		            Component c,
		            Graphics g,
		            int x, int y,
		            int width, int height) {

		        Graphics2D g2 = (Graphics2D) g;

		        int bottomLineY = height - thickness - pointerSize;

		        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
		                0 + strokePad,
		                0 + strokePad,
		                width - thickness,
		                bottomLineY,
		                radii,
		                radii);

		        Area area = new Area(bubble);
		        g2.setRenderingHints(hints);
		        // Paint the BG color of the parent, everywhere outside the clip
		        // of the text bubble.
		        Component parent  = c.getParent();
		        if (parent!=null) {
		            Color bg = parent.getBackground();
		            Rectangle rect = new Rectangle(0,0,width, height);
		            Area borderRegion = new Area(rect);
		            borderRegion.subtract(area);
		            g2.setClip(borderRegion);
		            g2.setColor(bg);
		            g2.fillRect(0, 0, width, height);
		            g2.setClip(null);
		        }

		        g2.setColor(color);
		        g2.setStroke(stroke);
		        g2.draw(area);
		        
		        //Font by Stackoverflow
		    }
		}
		
		public static Border criarBordaBalaoDialogo(Color cor)
		{
			return new TextBubbleBorder(cor);
		}
		
		public static Border criarBordaTituloField(String titulo) {
			return BorderFactory.createTitledBorder(
		            BorderFactory.createEtchedBorder(
		                    EtchedBorder.RAISED, Color.GRAY
		                    , Color.DARK_GRAY),
		            titulo,
		            TitledBorder.DEFAULT_JUSTIFICATION, 
		            TitledBorder.CENTER);
		}
		
		public static Border criarBordaTitulo(String title) {
			return BorderFactory.createTitledBorder(
					null,
					title, TitledBorder.CENTER,
					TitledBorder.DEFAULT_POSITION);
		}
		public static Border criarBordaTitulo(
				String title,
				int titledBorderJusification,
				int titledBorderPosition) {
			return BorderFactory.createTitledBorder(
					null,
					title, titledBorderJusification,
					titledBorderPosition);
		}
		
		public static Border criarBordaArredondadaBasic(String titulo) {
			LineBorder roundedLineBorder = new LineBorder(Color.black, 5, true);
			TitledBorder roundedTitledBorder = new TitledBorder(roundedLineBorder, titulo);
			return roundedTitledBorder;
		}
		
		public static Border criarBordaArredondada(Color color) {
			if(color == null)
				return new BordaArredondada(color, 3, 8, 7);
			return new BordaArredondada(color);
		}
		
		
	}
	
	public static interface Icones{
			
			public static ImageIcon getIcon(String nome) {
				String caminho = "br\\com\\pbd2019_1\\imagens\\" + nome +".png";
				ClassLoader classLoader = Image.class.getClassLoader();
				return new ImageIcon(classLoader.getResource(caminho));
			}
			public static ImageIcon getIcon(String nome, String formato) {
				String caminho = "br\\com\\pbd2019_1\\imagens\\" + nome +"."+formato;
				ClassLoader classLoader = Image.class.getClassLoader();
				return new ImageIcon(classLoader.getResource(caminho));
			}
			
			public static ImageIcon getIconImage(String path) {
				ClassLoader classLoader = Image.class.getClassLoader();
				return new ImageIcon(classLoader.getResource(path));
			}
			
			public static ImageIcon getImageDefault() {
				
				ImageIcon image = null;
				
				try {
					ConfigDefault config = DAOConfigDefault.loadConfig();
					
					if(config!=null) 
					{
						if(config.getImagemFundoDefault().equals("1"))
							image = IMAGEM_FUNDO;
						else if(config.getImagemFundoDefault().equals("2"))
							image = IMAGEM_FUNDO_LAMPADA_1;
						else if(config.getImagemFundoDefault().equals("3"))
							image = IMAGEM_FUNDO_LAMPADA_2;
						else if(config.getImagemFundoDefault().equals("4"))
							image = IMAGEM_FUNDO_CAFE;
						else if(config.getImagemFundoDefault().equals("5"))
							image = IMAGEM_FUNDO_COMPUTADOR_CAFE;
						else if(config.getImagemFundoDefault().equals("6"))
							image = IMAGEM_FUNDO_MARTE;
						else if(config.getImagemFundoDefault().equals("7"))
							image = IMAGEM_FUNDO_CIRCUITO;
						else if(config.getImagemFundoDefault().equals("8"))
							image = IMAGEM_FUNDO_FONES;
						else
							image = getIconImage(config.getImagemFundoDefault());
						return image;
					}
					else
						return IMAGEM_FUNDO;
					
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
					return IMAGEM_FUNDO;
				}
			}
			
			public static void setImageDefault(String imagem) 
			{
				DAOConfigDefault.setImagemPadrao(imagem);
			}
			
			//Inserir constantes de Icones aqui ex
			public static final ImageIcon ICONE_CADASTRAR_USUARIO = getIcon("Freepik\\add-user");
			public static final ImageIcon ICONE_LOGAR = getIcon("Freepik\\logIn");
			public static final ImageIcon ICONE_TELA_LOGIN = getIcon("Freepik\\telaLogin");
			public static final ImageIcon ICONE_LOGIN_USUARIO = getIcon("Freepik\\user_login");
			public static final ImageIcon ICONE_SENHA_USUARIO = getIcon("Freepik\\user_senha");
			public static final ImageIcon ICONE_VASSOURA = getIcon("Freepik\\short-broom");
			public static final ImageIcon ICONE_SETA_PLAY = getIcon("Freepik\\play-arrow");
			public static final ImageIcon ICONE_SALVAR_ARQUIVO = getIcon("Freepik\\diskette");
			public static final ImageIcon ICONE_ABRIR_ARQUIVO = getIcon("Freepik\\open-folder-with-document");
			public static final ImageIcon ICONE_LUPA = getIcon("Freepik\\loupe");
			
			public static final ImageIcon ICONE_USER = getIcon("Smashicons\\user");
			public static final ImageIcon ICONE_PEOPLE = getIcon("Freepik\\multiple-users-silhouette");
			public static final ImageIcon ICONE_SQL = getIcon("Freepik\\sql-file-format-symbol (2)");
			public static final ImageIcon ICONE_LOG = getIcon("FlatIcons\\log");
			public static final ImageIcon ICONE_BACKUP = getIcon("Freepik\\databases-symbol");
			public static final ImageIcon ICONE_SOBRE = getIcon("Eucalyp\\computer");
			public static final ImageIcon ICONE_LOGOUT = getIcon("Freepik\\exit");
			
			
			public static final ImageIcon ICONE_WARNING = getIcon("Freepik\\warning");
			public static final ImageIcon ICONE_WARNING_MENOR = getIcon("Freepik\\warning (1)");
			public static final ImageIcon ICONE_WARNING_MENORZINHO = getIcon("Freepik\\warning (2)");
			public static final ImageIcon ICONE_PROBLEM = getIcon("Freepik\\problem (1)");
			public static final ImageIcon ICONE_PROBLEM_MENOR = getIcon("Freepik\\problem");
			public static final ImageIcon ICONE_NOTIFICATION = getIcon("Freepik\\notification (1)");
			
			public static final ImageIcon IMAGEM_CARREGAMENTO = getIcon("Outros\\FundoTelaCarregamento");
			
			public static final ImageIcon IMAGEM_FUNDO = getIcon("Outros\\PhotoFunia", "jpg");
			public static final ImageIcon IMAGEM_FUNDO_LAMPADA_1 = getIcon("Pixabay\\light-bulb-503881_1920", "jpg");
			public static final ImageIcon IMAGEM_FUNDO_LAMPADA_2 = getIcon("Pixabay\\light-bulb-1246043_1920", "jpg");
			
			public static final ImageIcon IMAGEM_FUNDO_CAFE = getIcon("Pixabay\\coffee-1869647_1920", "jpg");
			public static final ImageIcon IMAGEM_FUNDO_COMPUTADOR_CAFE = getIcon("Pixabay\\beverage-3157395_1920", "jpg");
			public static final ImageIcon IMAGEM_FUNDO_MARTE = getIcon("Pixabay\\mars-67522_1920", "jpg");
			public static final ImageIcon IMAGEM_FUNDO_CIRCUITO = getIcon("Pixabay\\board-453758_1920", "jpg");
			public static final ImageIcon IMAGEM_FUNDO_FONES = getIcon("Pixabay\\headphones-690685_1920", "jpg");
			
			
			public static final ImageIcon IMAGEM_LOGO_ITEXT = getIcon("Outros\\iText_placeholder");
			public static final ImageIcon IMAGEM_LOGO_UAST = getIcon("Outros\\ufrpe-uast-logos-200");
			
			public static final ImageIcon IMAGEM_LOGO_PROJETO_1 = getIcon("Outros\\canvas_img", "jpg");
			
			
	}
	
}
