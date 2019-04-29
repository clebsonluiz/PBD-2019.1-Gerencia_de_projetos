package br.com.pbd2019_1.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JanelaEscolherCaminhoArquivo extends JFileChooser{

	private static final long serialVersionUID = 1L;
	
	private FileNameExtensionFilter filter1;
	private FileNameExtensionFilter filter2; 
	private FileNameExtensionFilter filter3; 
	
	public JanelaEscolherCaminhoArquivo() {
		
		filter1 = new FileNameExtensionFilter( "Documentos PDF (*.pdf)", "pdf" );
		filter2 = new FileNameExtensionFilter( "Arquivos de Backup (*.backup)", "backup" );
		filter3 = new FileNameExtensionFilter( "Arquivos SQL (*.sql)", "SQL" );
		   
		setMinimumSize(new Dimension(300, 300));
	    setPreferredSize(new Dimension(400, 300));
	}
	
	public int exibirParaPDF(Component c)
	{
		setDialogTitle("Relatorio");
		resetChoosableFileFilters();
		addChoosableFileFilter(filter1);
		return showOpenDialog(c);
	}
	
	public int exibirParaBackup(Component c)
	{
		setDialogTitle("Backup");
		resetChoosableFileFilters();
		addChoosableFileFilter(filter2);
		return showOpenDialog(c);
	}
	
	public int exibirParaSQL(Component c)
	{
		setDialogTitle("SQL");
		resetChoosableFileFilters();
		addChoosableFileFilter(filter3);
		return showOpenDialog(c);
	}
	
}
