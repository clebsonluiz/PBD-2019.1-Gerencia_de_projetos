package br.com.pbd2019_1.tabelas;

import java.awt.Color;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.view.Botao;

public class TColaborador extends TGenerica<Colaborador>{

	private static final long serialVersionUID = 1L;
	
	private Botao btn = new Botao("Ver", Color.green.brighter().brighter());
	
	public TColaborador() {
		super(new String[]{"Nome","Data ingressão", ""});
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getPessoa().getNome();
		case 1:
			return getList().get(linha).getData_ingresso();
		case 2:
			return btn;
		}
		return null;
	}
	
	public Botao getBtn() {
		return btn;
	}

}
