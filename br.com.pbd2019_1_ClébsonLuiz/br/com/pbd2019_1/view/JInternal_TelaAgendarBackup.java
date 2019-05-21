package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import br.com.pbd2019_1.utils.ViewUtil;

public class JInternal_TelaAgendarBackup extends JInternalAbstract {

	private static final long serialVersionUID = 1L;
	
	private Horario horario;

	private Botao btDefinirHorario;

	private Botao btResetarHorario;
	
	public JInternal_TelaAgendarBackup() {
		super("Agendar Backup");
		setMaximizable(false);
		setIconifiable(false);
		setPreferredSize(new Dimension(100, 100));
		setSize(200, 150);
		horario = new Horario();
		horario.setFonte(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		getContentPane().add(horario, BorderLayout.CENTER);
		
		btDefinirHorario = new Botao("Definir Horario");
		getContentPane().add(btDefinirHorario, BorderLayout.SOUTH);
		
		btResetarHorario = new Botao("Resetar Horario");
		getContentPane().add(btResetarHorario, BorderLayout.NORTH);
	}

	@Override
	protected void fechar() {}

	public Horario getHorario() {
		return horario;
	}

	public Botao getBtDefinirHorario() {
		return btDefinirHorario;
	}

	public Botao getBtResetarHorario() {
		return btResetarHorario;
	}

}
