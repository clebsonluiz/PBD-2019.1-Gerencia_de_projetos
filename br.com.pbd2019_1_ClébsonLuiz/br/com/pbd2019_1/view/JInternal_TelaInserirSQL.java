package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInserirSQL extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInserirSQL telaInserirSQL;
	
	public JInternal_TelaInserirSQL() {
		super("Inserir SQL");
		setMinimumSize(new Dimension(300, 350));
		setPreferredSize(new Dimension(300, 350));
		setSize(getPreferredSize());
		telaInserirSQL = new TelaInserirSQL();
		add(telaInserirSQL, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() 
	{
		telaInserirSQL.getTextArea().setText("");
		telaInserirSQL.getExceptionTextArea().setText("");
	}

	public TelaInserirSQL getTelaInserirSQL() {
		return telaInserirSQL;
	}
	
}
