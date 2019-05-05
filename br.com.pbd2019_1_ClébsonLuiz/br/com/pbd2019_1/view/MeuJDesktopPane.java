package br.com.pbd2019_1.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;

import br.com.pbd2019_1.utils.ViewUtil;

public class MeuJDesktopPane extends JDesktopPane{
	
	private static final long serialVersionUID = 1L;
	
	private PopUp popUp = new PopUp(new String[] {
			"FUNDO PBD 2019.1", "FUNDO LAMPADA 1", "FUNDO LAMPADA 2"
	});
	
	private Image imagem;

	public MeuJDesktopPane() {
		try 
		{
			imagem = ViewUtil.Icones.getImageDefault().getImage();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(SwingUtilities.isRightMouseButton(e))
				{
					popUp.show(getI(), e.getX(), e.getY());
				}
			}
		});
		
		
		popUp.getMenuItens()[0].addActionListener(ActionEvent->{
			try 
			{
				imagem = ViewUtil.Icones.IMAGEM_FUNDO.getImage();
				repaint();
				ViewUtil.Icones.setImageDefault("1");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		});;
		
		popUp.getMenuItens()[1].addActionListener(ActionEvent->{
			try 
			{
				imagem = ViewUtil.Icones.IMAGEM_FUNDO_LAMPADA_1.getImage();
				repaint();
				ViewUtil.Icones.setImageDefault("2");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		});;
		
		popUp.getMenuItens()[2].addActionListener(ActionEvent->{
			try 
			{
				imagem = ViewUtil.Icones.IMAGEM_FUNDO_LAMPADA_2.getImage();
				repaint();
				ViewUtil.Icones.setImageDefault("3");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		});;
		
		
	}

	MeuJDesktopPane getI(){
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		BufferedImage bi;

		if (imagem != null) {

			Dimension dimension = this.getSize();
			int dWidth = (int) dimension.getWidth();
			int dHeight = (int) dimension.getHeight();

			bi = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_RGB);
			// cria uma versao 2D de graficos para que possamos desenhar a
			// imagem na tela
			Graphics2D g2 = bi.createGraphics();
			// define algumas configurações de interpolação, renderização e
			// antialiasing
			// para melhorar um pouco a qualidade da imagem quando for
			// dimensionada
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// desenha a imagem dentro do BufferedImagem
			g2.drawImage(imagem, 0, 0, dWidth, dHeight, null);
			g2.dispose();
			// finalmente, desenha a imagem já dimensionada no componente
			g.drawImage(bi, 0, 0, null);

		}
	}
}
