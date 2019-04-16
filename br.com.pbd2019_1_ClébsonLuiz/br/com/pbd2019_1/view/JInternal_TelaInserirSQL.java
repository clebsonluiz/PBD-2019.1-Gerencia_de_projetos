package br.com.pbd2019_1.view;

import java.awt.BorderLayout;

public class JInternal_TelaInserirSQL extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInserirSQL telaInserirSQL;
	
	public JInternal_TelaInserirSQL() {
		super("Inserir SQL");
		telaInserirSQL = new TelaInserirSQL();
		add(telaInserirSQL, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {telaInserirSQL.getTextArea().setText("");}

	public TelaInserirSQL getTelaInserirSQL() {
		return telaInserirSQL;
	}
	
}
