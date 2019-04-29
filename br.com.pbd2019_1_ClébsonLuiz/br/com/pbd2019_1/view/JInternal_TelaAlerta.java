package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import br.com.pbd2019_1.utils.ViewUtil;

/**
 * Meu Próprio "JDialog"<br>
 * Feito para "bloquear" o acesso das informações quando ocorre um erro
 * 
 * */
public class JInternal_TelaAlerta extends JInternalAbstract {

	private static JInternal_TelaAlerta telaAlerta;
	
	public static JInternal_TelaAlerta getInstance()
	{
		if(telaAlerta == null)
			telaAlerta = new JInternal_TelaAlerta();
		return telaAlerta;
	}
	
	public static void showAlerta(String tituloAlerta, String mensagemAlerta) 
	{
		try
		{
			getInstance();
			telaAlerta.chamarAlerta(tituloAlerta, mensagemAlerta);
		} 
		catch (PropertyVetoException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;

	public static boolean isAtivado;
	
	private JLabel labelTitulo, labelIcone;
	private JTextArea textArea;
	
	private JInternal_TelaAlerta() {
		super("ERRO");
		setIconifiable(false);
		setMaximizable(false);
		setMinimumSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(400, 400));
		setSize(new Dimension(400, 400));
		
		Botao btnOK = new Botao("OK");
		
		btnOK.addActionListener(ActionEvent->{
			fechar();
			setVisible(false);
			});
		
		btnOK.grabFocus();
		btnOK.setPreferredSize(new Dimension(70, 50));
		btnOK.setForeground(Color.WHITE);
		btnOK.setBackground(Color.RED);
		btnOK.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		
		labelTitulo = new JLabel("");
		labelTitulo.setForeground(Color.red);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_GIGANTE_B);
		labelIcone = new JLabel(ViewUtil.Icones.ICONE_WARNING);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		JPanel panelCenter = new JPanel(new BorderLayout());
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);
		panelSouth.add(btnOK);
		panelCenter.add(labelIcone, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panelCenter.add(scrollPane, BorderLayout.SOUTH);
		
		textArea = new JTextArea(2, 100);
		textArea.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO);
		textArea.setForeground(Color.red.darker());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(null);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		scrollPane.setBorder(ViewUtil.Bordas.criarBordaTitulo(""));
		
	}

	@Override
	protected void fechar() {
		isAtivado = false;
	}
	
	public void chamarAlerta(String tituloAlerta, String mensagemAlerta) throws PropertyVetoException
	{
		this.labelTitulo.setText(tituloAlerta);
		this.textArea.setText(mensagemAlerta);
		this.textArea.setToolTipText(mensagemAlerta);
		queroFoco();
	}
	
	@Override
	public void queroFoco() throws PropertyVetoException {
		isAtivado = true;
		setMaximum(true);
		super.queroFoco();
	}
}
