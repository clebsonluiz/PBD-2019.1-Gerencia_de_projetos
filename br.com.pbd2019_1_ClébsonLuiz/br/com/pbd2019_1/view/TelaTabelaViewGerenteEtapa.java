package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaTabelaViewGerenteEtapa extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox<String> tipoBuscaCmbx;
	private CampoTexto cmptxtNome;
	private Botao btBuscar;

	public TelaTabelaViewGerenteEtapa() {

		setPreferredSize(new Dimension(600, 400));

		setLayout(new BorderLayout(0, 0));

		MeuJPanel meuJPanel = new MeuJPanel();
		add(meuJPanel, BorderLayout.NORTH);

		tipoBuscaCmbx = new JComboBox<String>();
		tipoBuscaCmbx.setPreferredSize(new Dimension(130, 40));
		tipoBuscaCmbx.setModel(new DefaultComboBoxModel<String>(new String[] {"TODAS", "ESPECIFICAR"}));
		tipoBuscaCmbx.setSelectedIndex(0);

		meuJPanel.add(tipoBuscaCmbx);

		cmptxtNome = new CampoTexto();
		cmptxtNome.setPreferredSize(new Dimension(100, 40));
		cmptxtNome.setBorder(new LineBorder(Color.GRAY, 1, true));
		cmptxtNome.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtNome.setDescricao("Nome");
		meuJPanel.add(cmptxtNome);

		btBuscar = new Botao();
		btBuscar.setIcon(ViewUtil.Icones.ICONE_LUPA);
		meuJPanel.add(btBuscar);

		table = new JTable();
		JScrollPane jScroll = new JScrollPane(table);
		add(jScroll, BorderLayout.CENTER);

		cmptxtNome.setVisible(false);

		tipoBuscaCmbx.addActionListener(ActionEvent->{
			if(tipoBuscaCmbx.getSelectedIndex() == 0)
				cmptxtNome.setVisible(false);
			else if(tipoBuscaCmbx.getSelectedIndex() == 1)
				cmptxtNome.setVisible(true);
		});

	}
	
	public JTable getTable() {return table;}
	public JComboBox<String> getTipoBuscaCmbx() {return tipoBuscaCmbx;}
	public CampoTexto getCmptxtNome() {return cmptxtNome;}
	public Botao getBtBuscar() {return btBuscar;}

}

