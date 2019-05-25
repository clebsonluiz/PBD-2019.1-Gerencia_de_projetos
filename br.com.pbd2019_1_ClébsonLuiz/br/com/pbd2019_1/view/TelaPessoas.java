package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaPessoas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Botao btAdicionarPessoa;
	private JPanel panel_1;
	private JComboBox<String> especificarComboBox;
	private CampoTexto nomeCampoTexto;
	private CampoTexto cpfCampoTexto;
	private JComboBox<String> disponivelComboBox;
	private Botao botao;
	private Dimension d1 = new Dimension(500, 50);
	private Dimension d2 = new Dimension(500, 100);
	
	public TelaPessoas() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btAdicionarPessoa = new Botao();
		btAdicionarPessoa.setText("Adicionar Pessoa");
		panel.add(btAdicionarPessoa);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(d1);
		add(panel_1, BorderLayout.NORTH);
		
		especificarComboBox = new JComboBox<String>();
		especificarComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS", "ESPECIFICAR"}));
		especificarComboBox.setSelectedIndex(0);
		panel_1.add(especificarComboBox);
		
		especificarComboBox.setPreferredSize(new Dimension(100, 40));
		
		nomeCampoTexto = new CampoTexto();
		nomeCampoTexto.setDescricao("Nome da Pessoa");
		nomeCampoTexto.setColumns(10);
		panel_1.add(nomeCampoTexto);
		
		nomeCampoTexto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		nomeCampoTexto.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		nomeCampoTexto.setPreferredSize(new Dimension(100, 40));
		
		cpfCampoTexto = new CampoTexto();
		cpfCampoTexto.setDescricao("CPF da Pessoa");
		cpfCampoTexto.setColumns(10);
		panel_1.add(cpfCampoTexto);
		
		cpfCampoTexto.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		cpfCampoTexto.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		cpfCampoTexto.setPreferredSize(new Dimension(100, 40));
		
		disponivelComboBox = new JComboBox<String>();
		disponivelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS", "DISPON\u00CDVEIS", "INDISPON\u00CDVEIS"}));
		disponivelComboBox.setSelectedIndex(0);
		panel_1.add(disponivelComboBox);
		
		disponivelComboBox.setPreferredSize(new Dimension(100, 40));
		
		botao = new Botao();
		botao.setIcon(ViewUtil.Icones.ICONE_LUPA);
		panel_1.add(botao);
		
		nomeCampoTexto.setVisible(false);
		cpfCampoTexto.setVisible(false);
		disponivelComboBox.setVisible(false);
		
		especificarComboBox.addActionListener(ActionEvent->
		{
			if(especificarComboBox.getSelectedIndex() == 0)
			{
				panel_1.setPreferredSize(d1);
				nomeCampoTexto.setVisible(false);
				cpfCampoTexto.setVisible(false);
				disponivelComboBox.setVisible(false);
			}
			else
			{
				panel_1.setPreferredSize(d2);
				nomeCampoTexto.setVisible(true);
				cpfCampoTexto.setVisible(true);
				disponivelComboBox.setVisible(true);
			}
		});
		
	}

	public JTable getTable() {
		return table;
	}

	public Botao getBtAdicionarPessoa() {
		return btAdicionarPessoa;
	}

	public JComboBox<String> getEspecificarComboBox() {
		return especificarComboBox;
	}

	public CampoTexto getNomeCampoTexto() {
		return nomeCampoTexto;
	}

	public CampoTexto getCpfCampoTexto() {
		return cpfCampoTexto;
	}

	public JComboBox<String> getDisponivelComboBox() {
		return disponivelComboBox;
	}

	public Botao getBotao() {
		return botao;
	}
	
}
