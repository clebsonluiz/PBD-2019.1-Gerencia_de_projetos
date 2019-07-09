package br.com.pbd2019_1.controll;

import br.com.pbd2019_1.dao.DAOResSQL;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.view.JInternal_TelaInserirSQL;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.MeuJFileChooser;
import br.com.pbd2019_1.view.TelaInserirSQL;

public class Controlador_JInternal_SQL {

	private Controlador_Principal controlador_Principal;

	public Controlador_JInternal_SQL(Controlador_Principal controlador_Principal) {
		this.controlador_Principal = controlador_Principal;
	}
	
	public void adicionarEventos()
	{
		adicionarEventoJInternal(controlador_Principal.getjInternal_TelaInserirSQL());
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
			
				controlador_Principal.gettObject().addAll(Fachada.getInstance().inserirSQLGenerica(sql));
				
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
