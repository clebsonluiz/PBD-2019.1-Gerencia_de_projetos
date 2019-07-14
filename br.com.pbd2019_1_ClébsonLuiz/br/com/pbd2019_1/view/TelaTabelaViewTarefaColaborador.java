package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaTabelaViewTarefaColaborador extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox<String> tipoBuscaCmbx;
	private JComboBox<String> buscaEspecificaCmbx;
	private CampoTexto cmptxtNome;
	private JDateChooser dateChooser;
	private Botao btBuscar;

	public TelaTabelaViewTarefaColaborador() {
		setPreferredSize(new Dimension(600, 400));
		
		setLayout(new BorderLayout(0, 0));
		
		MeuJPanel meuJPanel = new MeuJPanel();
		add(meuJPanel, BorderLayout.NORTH);
		
		tipoBuscaCmbx = new JComboBox<String>();
		tipoBuscaCmbx.setPreferredSize(new Dimension(130, 40));
		tipoBuscaCmbx.setModel(new DefaultComboBoxModel<String>(new String[] {"TODAS", "ESPECIFICAR"}));
		tipoBuscaCmbx.setSelectedIndex(0);
		
		meuJPanel.add(tipoBuscaCmbx);
		
		buscaEspecificaCmbx = new JComboBox<String>();
		buscaEspecificaCmbx.setModel(new DefaultComboBoxModel<String>(new String[] {"TUDO", "N\u00C3O FINALIZADAS", "FINALIZADAS", "ATRASADAS"}));
		buscaEspecificaCmbx.setSelectedIndex(0);
		buscaEspecificaCmbx.setPreferredSize(new Dimension(130, 40));
		meuJPanel.add(buscaEspecificaCmbx);
		
		cmptxtNome = new CampoTexto();
		cmptxtNome.setPreferredSize(new Dimension(100, 40));
		cmptxtNome.setBorder(new LineBorder(Color.GRAY, 1, true));
		cmptxtNome.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtNome.setDescricao("Nome");
		meuJPanel.add(cmptxtNome);
		
		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(100, 40));
		dateChooser.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		dateChooser.getCalendarButton().setPreferredSize(new Dimension(20, 40));
		dateChooser.setBackground(getBackground());
		meuJPanel.add(dateChooser);
		
		btBuscar = new Botao();
		btBuscar.setIcon(ViewUtil.Icones.ICONE_LUPA);
		meuJPanel.add(btBuscar);
		
		table = new JTable();
		JScrollPane jScroll = new JScrollPane(table);
		add(jScroll, BorderLayout.CENTER);
		
		tipoBuscaTodas();
		buscaEspecificaTudo();
		
		tipoBuscaCmbx.addActionListener(ActionEvent->{
			if(tipoBuscaCmbx.getSelectedIndex() == 0)
				tipoBuscaTodas();
			else if(tipoBuscaCmbx.getSelectedIndex() == 1)
				tipoBuscaEspecificar();
		});
		
		buscaEspecificaCmbx.addActionListener(ActionEvent->{
			if(buscaEspecificaCmbx.getSelectedIndex() == 0)
				buscaEspecificaEspecifica();
			else if(buscaEspecificaCmbx.getSelectedIndex() == 1)
				buscaEspecificaEspecifica();
			else if(buscaEspecificaCmbx.getSelectedIndex() == 2)
				buscaEspecificaEspecifica();
			else if(buscaEspecificaCmbx.getSelectedIndex() == 3)
				buscaEspecificaTudo();
		});
	}
	
	void tipoBuscaTodas() 
	{
		buscaEspecificaCmbx.setVisible(false);
		cmptxtNome.setVisible(false);
		dateChooser.setVisible(false);
	}
	
	void tipoBuscaEspecificar()
	{
		buscaEspecificaCmbx.setVisible(true);
		cmptxtNome.setVisible(true);
		if(buscaEspecificaCmbx.getSelectedIndex() == 3)
			dateChooser.setVisible(false);
		else 
			dateChooser.setVisible(true);
	}
	
	void buscaEspecificaTudo() 
	{
		dateChooser.setVisible(false);
	}
	
	void buscaEspecificaEspecifica()
	{
		dateChooser.setVisible(true);
	}

	public JTable getTable() {return table;}
	public JComboBox<String> getTipoBuscaCmbx() {return tipoBuscaCmbx;}
	public JComboBox<String> getBuscaEspecificaCmbx() {return buscaEspecificaCmbx;}
	public CampoTexto getCmptxtNome() {return cmptxtNome;}
	public JDateChooser getDateChooser() {return dateChooser;}
	public Botao getBtBuscar() {return btBuscar;}
	
}
