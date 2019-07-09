package br.com.pbd2019_1.controll;

import javax.swing.JTable;

import br.com.pbd2019_1.dao.DAOResSQL;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.MeuJFileChooser;
import br.com.pbd2019_1.view.TelaInserirSQL;

public class Ctrl_JInternal_SQL {

	private Controlador_Principal ctrl_P;

	public Ctrl_JInternal_SQL(Controlador_Principal ctrl_P) {
		this.ctrl_P = ctrl_P;
	}
	
	public void adicionarEventos()
	{
		JTable tableSQL = ctrl_P.getjInternal_TelaInserirSQL()
				.getTelaInserirSQL().getTable();
		
		tableSQL.setModel(ctrl_P.gettObject());
		
		tableSQL.setRowHeight(30);
		
		adicionarEventoJInternal(ctrl_P.getjInternal_TelaInserirSQL());
	}
	
	private void adicionarEventoJInternal(JInternal_TelaInserirSQL jInternal_TelaInserirSQL) {
		
		TelaInserirSQL telaInserirSQL = jInternal_TelaInserirSQL.getTelaInserirSQL();
		
		telaInserirSQL.getBtnInserir()
		.addActionListener(ActionEvent->{
			//TODO - Inserir SQL
			try {
				String sql = telaInserirSQL.getTextArea().getSelectedText();
			
				if(sql == null)
					sql = telaInserirSQL.getTextArea().getText();
			
				ctrl_P.gettObject().addAll(Fachada.getInstance().inserirSQLGenerica(sql));
				
				telaInserirSQL.getExceptionTextArea().setText("");
			} catch (ValidacaoException e) {
				telaInserirSQL.getExceptionTextArea().setText(e.getMessage());
			}
			
		});

		telaInserirSQL.getBtnLimpar()
		.addActionListener(ActionEvent->{
			//TODO - Botao Limpar
			telaInserirSQL.getTextArea().setText("");
		});

		telaInserirSQL.getBtnSalvar()
		.addActionListener(ActionEvent->{
			//TODO - Botao Salvar
			int op = MeuJFileChooser.getInstance().exibirParaSQL(jInternal_TelaInserirSQL);
			if(op == MeuJFileChooser.APPROVE_OPTION)
			{
				try 
				{
					String caminho = MeuJFileChooser.getInstance().getSelectedFile().getAbsolutePath();
					DAOResSQL.EscreverArquivo(caminho, telaInserirSQL.getTextArea().getText());
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(jInternal_TelaInserirSQL, "Erro", e.getMessage());
				}
			}
		});

		telaInserirSQL.getBtnAbrir()
		.addActionListener(ActionEvent->{
			//TODO - Botao abrir
			int op = MeuJFileChooser.getInstance().exibirParaSQL(jInternal_TelaInserirSQL);
			if(op == MeuJFileChooser.APPROVE_OPTION)
			{
				try 
				{
					String caminho = MeuJFileChooser.getInstance().getSelectedFile().getAbsolutePath();
					String textoSQL = DAOResSQL.LerArquivo(caminho);
					telaInserirSQL.getTextArea().setText(textoSQL);
				}
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(jInternal_TelaInserirSQL, "Erro", e.getMessage());
				}
			}
		});
	}
	
}
