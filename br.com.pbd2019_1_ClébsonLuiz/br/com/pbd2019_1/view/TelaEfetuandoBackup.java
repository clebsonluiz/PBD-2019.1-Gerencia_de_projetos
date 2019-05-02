package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaEfetuandoBackup extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static Color corFundo = Color.white;

	private JTextArea textArea;

	private Botao btnOK_Event_Backup_Normal;

	private Botao btnOK_Event_Deslogar;

	private Botao btnOK_Event_Fechar;
	
	public TelaEfetuandoBackup() {
		setLayout(new BorderLayout(2, 2));
		setBackground(corFundo);
		
		setPreferredSize(new Dimension(400, 300));
		setMinimumSize(getPreferredSize());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(corFundo);
		add(panel_1, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		textArea = new JTextArea();
		textArea.setFocusable(false);
		textArea.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		scrollPane.setBorder(ViewUtil.Bordas.criarBordaTitulo(""));
		
		JPanel panel = new JPanel();
		panel.setBackground(corFundo);
		add(panel, BorderLayout.SOUTH);
		
		btnOK_Event_Backup_Normal = new Botao("OK");
		panel.add(btnOK_Event_Backup_Normal);
		
		Dimension d = new Dimension(70, 50);
		
		btnOK_Event_Backup_Normal.grabFocus();
		btnOK_Event_Backup_Normal.setPreferredSize(d);
		btnOK_Event_Backup_Normal.setForeground(Color.WHITE);
		btnOK_Event_Backup_Normal.setBackground(Color.GREEN.darker());
		btnOK_Event_Backup_Normal.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		
		btnOK_Event_Deslogar = new Botao("OK");
		btnOK_Event_Deslogar.setPreferredSize(d);
		btnOK_Event_Deslogar.setForeground(Color.WHITE);
		btnOK_Event_Deslogar.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		btnOK_Event_Deslogar.setBackground(Color.ORANGE);
		panel.add(btnOK_Event_Deslogar);
		
		btnOK_Event_Fechar = new Botao("OK");
		btnOK_Event_Fechar.setPreferredSize(d);
		btnOK_Event_Fechar.setForeground(Color.WHITE);
		btnOK_Event_Fechar.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		btnOK_Event_Fechar.setBackground(Color.RED);
		panel.add(btnOK_Event_Fechar);
		
		ocultarTodosBotoes();
		
	}
	
	public void ocultarTodosBotoes() 
	{
		btnOK_Event_Backup_Normal.setVisible(false);
		btnOK_Event_Deslogar.setVisible(false);
		btnOK_Event_Fechar.setVisible(false);
	}
	
	public void exibir_BtnOK_Event_Backup_Normal()
	{
		btnOK_Event_Backup_Normal.setVisible(true);
		btnOK_Event_Backup_Normal.grabFocus();
	}
	public void exibir_BtnOK_Event_Deslogar() 
	{
		btnOK_Event_Deslogar.setVisible(true);
		btnOK_Event_Deslogar.grabFocus();
	}
	public void exibir_BtnOK_Event_Fechar()
	{
		btnOK_Event_Fechar.setVisible(true);
		btnOK_Event_Fechar.grabFocus();
	}
	
	public Botao getBtnOK_Event_Backup_Normal() {return btnOK_Event_Backup_Normal;}
	public Botao getBtnOK_Event_Deslogar() {return btnOK_Event_Deslogar;}
	public Botao getBtnOK_Event_Fechar() {return btnOK_Event_Fechar;}

	public void limparCampo() {textArea.setText("");}
	public void adicionarConteudo(String msg) {textArea.append(msg + "\r\n");}

}
