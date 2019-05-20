package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaInfoLog extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private CampoTexto cmptxtCod;
	private CampoTexto cmptxtTabela;
	private CampoTexto cmptxtResponsavel;
	private CampoTexto cmptxtTipo;
	private CampoTexto cmptxtDatalog;

	public TelaInfoLog() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new MeuJPanel();
		
		panel.setPreferredSize(new Dimension(390, 140));
		
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		cmptxtCod = new CampoTexto();
		cmptxtCod.setBackground(panel.getBackground());
		cmptxtCod.setEditable(false);
		cmptxtCod.setBorder(ViewUtil.Bordas.criarBordaTituloField("Cod."));
		cmptxtCod.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtCod.setBounds(10, 11, 87, 50);
		panel.add(cmptxtCod);
		
		cmptxtTabela = new CampoTexto();
		cmptxtTabela.setEditable(false);
		cmptxtTabela.setBackground(panel.getBackground());
		cmptxtTabela.setBorder(ViewUtil.Bordas.criarBordaTituloField("Tabela"));
		cmptxtTabela.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtTabela.setBounds(109, 11, 140, 50);
		panel.add(cmptxtTabela);
		
		cmptxtResponsavel = new CampoTexto();
		cmptxtTabela.setEditable(false);
		cmptxtResponsavel.setBackground(panel.getBackground());
		cmptxtResponsavel.setBorder(ViewUtil.Bordas.criarBordaTituloField("CPF Responsável"));
		cmptxtResponsavel.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtResponsavel.setBounds(10, 73, 158, 50);
		panel.add(cmptxtResponsavel);
		
		cmptxtTipo = new CampoTexto();
		cmptxtTabela.setEditable(false);
		cmptxtTipo.setBackground(panel.getBackground());
		cmptxtTipo.setBorder(ViewUtil.Bordas.criarBordaTituloField("Tipo Log"));
		cmptxtTipo.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtTipo.setBounds(261, 11, 119, 50);
		panel.add(cmptxtTipo);
		
		cmptxtDatalog = new CampoTexto();
		cmptxtTabela.setEditable(false);
		cmptxtDatalog.setBackground(panel.getBackground());
		cmptxtDatalog.setBorder(ViewUtil.Bordas.criarBordaTituloField("Data do Log"));
		cmptxtDatalog.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cmptxtDatalog.setBounds(180, 73, 200, 50);
		panel.add(cmptxtDatalog);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(panel.getBackground());
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable() {

			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					if(column >= 0){
						//X is your particlur column number
						JComponent jc = (JComponent) c;
						jc.setToolTipText(getValueAt(row, column).toString());
					}
				}
				return c;
			}
		};
		scrollPane.setViewportView(table);
	}

	public JTable getTable() {
		return table;
	}

	public CampoTexto getCmptxtCod() {
		return cmptxtCod;
	}

	public CampoTexto getCmptxtTabela() {
		return cmptxtTabela;
	}

	public CampoTexto getCmptxtResponsavel() {
		return cmptxtResponsavel;
	}

	public CampoTexto getCmptxtTipo() {
		return cmptxtTipo;
	}

	public CampoTexto getCmptxtDatalog() {
		return cmptxtDatalog;
	}
	
}
