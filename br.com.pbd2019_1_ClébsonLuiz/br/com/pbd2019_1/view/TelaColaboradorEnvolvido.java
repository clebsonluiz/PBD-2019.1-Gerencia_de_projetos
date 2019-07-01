package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.pbd2019_1.utils.ViewUtil;
import javax.swing.SwingConstants;

public class TelaColaboradorEnvolvido extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private CampoTexto cmptxtResponsavel;
	private CampoTexto cmptxtDatalog;
	private MeuJPanel meuJPanel;
	private Botao btAdicionarColaborador;
	private Botao btRemoverColaborador;
	private MeuJPanel meuJPanel_1;
	private Botao btVerColaborador;

	public TelaColaboradorEnvolvido(String tipo) {
		setLayout(new BorderLayout(10, 10));
		
		JLabel label = new JLabel(tipo);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		add(label, BorderLayout.NORTH);
		MeuJPanel panel = new MeuJPanel(new BorderLayout(10, 10));
		
		add(panel, BorderLayout.CENTER);
		
		meuJPanel = new MeuJPanel();
		meuJPanel.setPreferredSize(new Dimension(390, 50));
		panel.add(meuJPanel, BorderLayout.SOUTH);
		meuJPanel.setLayout(new GridLayout(0, 1, 10, 0));

		btAdicionarColaborador = new Botao();
		
		btAdicionarColaborador.setText("Adicionar Colaborador");
		btAdicionarColaborador.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		meuJPanel.add(btAdicionarColaborador);

		meuJPanel_1 = new MeuJPanel();
		panel.add(meuJPanel_1, BorderLayout.NORTH);
		meuJPanel_1.setPreferredSize(new Dimension(390, 150));
		meuJPanel_1.setMaximumSize(new Dimension(390, 150));
		meuJPanel_1.setLayout(new BorderLayout(5, 5));

		JPanel panel2 = new MeuJPanel();
		meuJPanel_1.add(panel2);

		panel2.setPreferredSize(new Dimension(390, 140));
		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		cmptxtResponsavel = new CampoTexto();
		cmptxtResponsavel.setBackground(panel.getBackground());
		cmptxtResponsavel.setBorder(ViewUtil.Bordas.criarBordaTituloField("CPF Responsável"));
		cmptxtResponsavel.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtResponsavel.setEditable(false);
		panel2.add(cmptxtResponsavel);

		cmptxtDatalog = new CampoTexto();
		cmptxtDatalog.setBackground(panel.getBackground());
		cmptxtDatalog.setBorder(ViewUtil.Bordas.criarBordaTituloField("Data de Ingressão"));
		cmptxtDatalog.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtDatalog.setEditable(false);
		panel2.add(cmptxtDatalog);

		btVerColaborador = new Botao();
		btVerColaborador.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		btVerColaborador.setBackground(new Color(50, 205, 50));
		btVerColaborador.setText("Ver ");
		meuJPanel_1.add(btVerColaborador, BorderLayout.EAST);

		btRemoverColaborador = new Botao();
		btRemoverColaborador.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		btRemoverColaborador.setForeground(Color.WHITE);
		btRemoverColaborador.setBackground(new Color(255, 0, 0));
		meuJPanel_1.add(btRemoverColaborador, BorderLayout.SOUTH);
		btRemoverColaborador.setText("Remover Colaborador");

	}

	public void exibirComColaborador()
	{
		meuJPanel_1.setVisible(true);
		meuJPanel.setVisible(false);
	}
	
	public void exibirSemColaborador()
	{
		meuJPanel_1.setVisible(false);
		meuJPanel.setVisible(true);
	}
	
	public CampoTexto getCmptxtResponsavel() {
		return cmptxtResponsavel;
	}

	public CampoTexto getCmptxtDatalog() {
		return cmptxtDatalog;
	}

	public Botao getBtAdicionarColaborador() {
		return btAdicionarColaborador;
	}

	public Botao getBtRemoverColaborador() {
		return btRemoverColaborador;
	}

	public Botao getBtVerColaborador() {
		return btVerColaborador;
	}

}
