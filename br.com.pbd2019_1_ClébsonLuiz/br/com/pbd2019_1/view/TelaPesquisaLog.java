package br.com.pbd2019_1.view;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.ViewUtil;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class TelaPesquisaLog extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> especificarComboBox;
	private JComboBox<String> tipoComboBox;
	private JComboBox<String> tabelaComboBox;
	private CampoTexto idTabelaCampoTexto;
	private CampoTexto responsavelCampoTexto;
	private JLabel lblEntre;
	private JDateChooser data1DateChooser;
	private JLabel lblE;
	private JDateChooser data2DateChooser;
	private Botao buscarBtn;

	private static Dimension todos = new Dimension(600 , 50);
	private static Dimension especificar = new Dimension(600 , 100);
	
	
	public TelaPesquisaLog() {

		especificarComboBox = new JComboBox<String>();
		especificarComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Todos", "Especificar"}));
		especificarComboBox.setPreferredSize(new Dimension(100, 40));
		add(especificarComboBox);
		
		tipoComboBox = new JComboBox<String>();
		tipoComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS", "CADASTRO", "ATUALIZAÇÃO", "DELEÇÃO"}));
		tipoComboBox.setPreferredSize(new Dimension(100, 40));
		add(tipoComboBox);
		
		tabelaComboBox = new JComboBox<String>();
		tabelaComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TODAS", "PESSOA", "COLABORADOR", "CONTATO", "CARACTERISTICA", "PROJETO", "ETAPA", "TAREFA"}));
		tabelaComboBox.setPreferredSize(new Dimension(120, 40));
		add(tabelaComboBox);
		
		idTabelaCampoTexto = new CampoTexto();
		idTabelaCampoTexto.setDescricao("Cód. Entidade");
		idTabelaCampoTexto.setColumns(6);
		idTabelaCampoTexto.setPreferredSize(new Dimension(40, 40));
		add(idTabelaCampoTexto);
		
		responsavelCampoTexto = new CampoTexto();
		responsavelCampoTexto.setDescricao("CPF do Responsável");
		responsavelCampoTexto.setColumns(11);
		responsavelCampoTexto.setPreferredSize(new Dimension(120, 40));
		add(responsavelCampoTexto);
		
		lblEntre = new JLabel("Entre");
		add(lblEntre);
		
		data1DateChooser = new JDateChooser();
		data1DateChooser.getCalendarButton().setPreferredSize(new Dimension(30, 20));
		data1DateChooser.setPreferredSize(new Dimension(120, 40));
		add(data1DateChooser);
		
		lblE = new JLabel("e");
		add(lblE);
		
		data2DateChooser = new JDateChooser();
		data2DateChooser.getCalendarButton().setPreferredSize(new Dimension(30, 20));
		data2DateChooser.setPreferredSize(new Dimension(120, 40));
		add(data2DateChooser);
		
		buscarBtn = new Botao();
		buscarBtn.setIcon(ViewUtil.Icones.ICONE_LUPA);
		buscarBtn.setToolTipText("Buscar");
		add(buscarBtn);

		todos();
		
		especificarComboBox.addItemListener((ItemEvent)->
		{
			if(especificarComboBox.getSelectedIndex() == 0)
			{
				setPreferredSize(todos);
				todos();
			}
			else
			{
				setPreferredSize(especificar);
				especificar();
			}
		});
	}
	
	public void todos() 
	{
		tipoComboBox.setVisible(false);
		tabelaComboBox.setVisible(false);
		idTabelaCampoTexto.setVisible(false);
		responsavelCampoTexto.setVisible(false);
		lblEntre.setVisible(false);
		data1DateChooser.setVisible(false);
		lblE.setVisible(false);
		data2DateChooser.setVisible(false);
	}
	
	public void especificar() 
	{
		tipoComboBox.setVisible(true);
		tabelaComboBox.setVisible(true);
		idTabelaCampoTexto.setVisible(true);
		responsavelCampoTexto.setVisible(true);
		lblEntre.setVisible(true);
		data1DateChooser.setVisible(true);
		lblE.setVisible(true);
		data2DateChooser.setVisible(true);
	}

	public JComboBox<String> getEspecificarComboBox() {
		return especificarComboBox;
	}

	public JComboBox<String> getTipoComboBox() {
		return tipoComboBox;
	}

	public JComboBox<String> getTabelaComboBox() {
		return tabelaComboBox;
	}

	public CampoTexto getIdTabelaCampoTexto() {
		return idTabelaCampoTexto;
	}

	public CampoTexto getResponsavelCampoTexto() {
		return responsavelCampoTexto;
	}

	public JDateChooser getData1DateChooser() {
		return data1DateChooser;
	}

	public JDateChooser getData2DateChooser() {
		return data2DateChooser;
	}

	public Botao getBuscarBtn() {
		return buscarBtn;
	}
	
}
