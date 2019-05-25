package br.com.pbd2019_1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Bordas;

public class TelaMiniPessoa1 extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> sexoComboBox;
	private JDateChooser nascimentoDateChooser;
	private JRadioButton rdbtnSimEstouDisponvel;
	private JRadioButton rdbtnNoEstouDisponvel;
	private CampoTexto nomeField;
	private CampoTextoFormatado campoFormatadoCPF;
	private TelaCadastroEdicao telaCadastroEdicao;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

	public TelaMiniPessoa1() {
		setLayout(null);
		setPreferredSize(new Dimension(310, 390));
		
		panel = new JPanel();
		panel.setBackground(getBackground());
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("Sexo e Data de Nascimento"));
		panel.setBounds(6, 160, 300, 80);
		add(panel);
		
		sexoComboBox = new JComboBox<String>();
		sexoComboBox.addItem("Masculino");
		sexoComboBox.addItem("Feminino");
		sexoComboBox.setSelectedIndex(0);
		sexoComboBox.setSelectedIndex(0);
		sexoComboBox.setBounds(6, 23, 100, 40);
		panel.add(sexoComboBox);
		
		nascimentoDateChooser = new JDateChooser();
		nascimentoDateChooser.setBackground(getBackground());
		nascimentoDateChooser.setFont(new Font("Arial", Font.PLAIN, 15));
		nascimentoDateChooser.setBounds(118, 23, 170, 40);
		panel.add(nascimentoDateChooser);
		
		panel_1 = new JPanel();
		panel_1.setBackground(getBackground());
		panel_1.setToolTipText("Disponibilidade para participar de outros projetos");
		panel_1.setBorder(Bordas.criarBordaTitulo("Disponíbilidade para colaborar em outros projetos"));
		panel_1.setBounds(6, 241, 300, 100);
		add(panel_1);
		
		rdbtnSimEstouDisponvel = new JRadioButton("Sim, estou dispon\u00EDvel.");
		rdbtnSimEstouDisponvel.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		rdbtnSimEstouDisponvel.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnSimEstouDisponvel.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnSimEstouDisponvel.setHorizontalTextPosition(SwingConstants.RIGHT);
		panel_1.add(rdbtnSimEstouDisponvel);
		
		rdbtnNoEstouDisponvel = new JRadioButton("N\u00E3o, n\u00E3o estou dispon\u00EDvel");
		rdbtnNoEstouDisponvel.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		rdbtnNoEstouDisponvel.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnNoEstouDisponvel.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnNoEstouDisponvel.setSelected(true);
		rdbtnNoEstouDisponvel.setHorizontalTextPosition(SwingConstants.RIGHT);
		panel_1.add(rdbtnNoEstouDisponvel);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSimEstouDisponvel);
		group.add(rdbtnNoEstouDisponvel);
		
		panel_2 = new JPanel();
		panel_2.setBackground(getBackground());
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createTitledBorder("Nome e CPF"));
		panel_2.setBounds(6, 6, 300, 150);
		add(panel_2);
		
		nomeField = new CampoTexto(ViewUtil.Fonts.Arial.ARIAL_TITULO);
		nomeField.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		nomeField.setBounds(10, 30, 280, 50);
		panel_2.add(nomeField);
		nomeField.setDescricao("Nome da pessoa");
		
		campoFormatadoCPF = new CampoTextoFormatado(ViewUtil.Fonts.Arial.ARIAL_TITULO);
		campoFormatadoCPF.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.LIGHT_GRAY));
		campoFormatadoCPF.setBounds(10, 85, 280, 50);
		panel_2.add(campoFormatadoCPF);
		campoFormatadoCPF.setToolTipText("Campo do Documento CPF");
		campoFormatadoCPF.setMascara("###.###.###-##");
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				
				getNomeField().setEnabled(true);
				getCampoFormatadoCPF().setEnabled(true);
				getSexoComboBox().setEnabled(true);
				getNascimentoDateChooser().setEnabled(true);
				getRdbtnSim().setEnabled(true);
				getRdbtnNo().setEnabled(true);
				
				super.abilitar();
			}
			
			protected void desabilitar() {
				getNomeField().setEnabled(false);
				getCampoFormatadoCPF().setEnabled(false);
				getSexoComboBox().setEnabled(false);
				getNascimentoDateChooser().setEnabled(false);
				getRdbtnSim().setEnabled(false);
				getRdbtnNo().setEnabled(false);
				
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBackground(getBackground());
		telaCadastroEdicao.setBounds(6, 346, 300, 38);
		add(telaCadastroEdicao);
	}

	public void limparCampos() {
		getNomeField().setText("");
		getCampoFormatadoCPF().setText("");
		getSexoComboBox().setSelectedIndex(0);
		getNascimentoDateChooser().setDate(DateUtil.getDataAtual());
		getRdbtnSim().setSelected(false);
		getRdbtnNo().setSelected(true);
	}
	
	public Botao getBotao() {
		return this.telaCadastroEdicao.getBtBotao1();
	}
	
	public JComboBox<String> getSexoComboBox() {
		return sexoComboBox;
	}

	public JDateChooser getNascimentoDateChooser() {
		return nascimentoDateChooser;
	}

	public JRadioButton getRdbtnSim() {
		return rdbtnSimEstouDisponvel;
	}

	public JRadioButton getRdbtnNo() {
		return rdbtnNoEstouDisponvel;
	}

	public CampoTexto getNomeField() {
		return nomeField;
	}

	public CampoTextoFormatado getCampoFormatadoCPF() {
		return campoFormatadoCPF;
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}
	
}
