package br.com.pbd2019_1.view;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Fonts.Arial;

public class TelaPessoa extends JPanel {

	private static final long serialVersionUID = 1L;
	private CampoTexto nomeField;
	private CampoTextoFormatado campoFormatadoCPF;
	private JComboBox<String> sexoComboBox;
	private JDateChooser nascimentoDateChooser;
	private CampoTexto loginField;
	private CampoSenha senhaField;
	private JCheckBox exibirSenhaChbx;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private TelaCadastroEdicao telaCadastroEdicao;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	
	public TelaPessoa() {
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(300, 380));
		setLayout(null);
		
		nomeField = new CampoTexto(Arial.ARIAL_MEDIO);
		nomeField.setDescricao("Nome da pessoa");
		nomeField.setBounds(10, 11, 280, 40);
		add(nomeField);
		campoFormatadoCPF = new CampoTextoFormatado(Arial.ARIAL_MEDIO);
		campoFormatadoCPF.setBounds(10, 62, 280, 40);
		campoFormatadoCPF.setToolTipText("Campo do Documento CPF");
		campoFormatadoCPF.setMascara("###.###.###-##");
		add(campoFormatadoCPF);
		
		panel = new JPanel();
		panel.setBorder(
				BorderFactory.createTitledBorder("Sexo e Data de Nascimento")
		);
		panel.setBounds(10, 113, 280, 80);
		add(panel);
		panel.setLayout(null);
		
		sexoComboBox = new JComboBox<String>();
		sexoComboBox.addItem("Masculino");
		sexoComboBox.addItem("Feminino");
		sexoComboBox.setSelectedIndex(0);
		sexoComboBox.setBounds(6, 23, 100, 40);
		panel.add(sexoComboBox);
		
		nascimentoDateChooser = new JDateChooser();
		nascimentoDateChooser.setDate(new Date());
		nascimentoDateChooser.setBounds(118, 23, 156, 40);
		nascimentoDateChooser.setFont(Arial.ARIAL_MEDIO);
		panel.add(nascimentoDateChooser);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		panel_1.setBorder(ViewUtil.Bordas.criarBordaTitulo("Login e Senha",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panel_1.setBounds(86, 205, 204, 129);
		add(panel_1);
		
		loginField = new CampoTexto(Arial.ARIAL_MEDIO);
		loginField.setBorder(ViewUtil.Bordas.criarBordaArredondada(null));
		loginField.setBounds(6, 25, 181, 43);
		loginField.setDescricao("Login");
		panel_1.add(loginField);
		
		senhaField = new CampoSenha();
		senhaField.setFont(Arial.ARIAL_MEDIO);
		senhaField.setDescricao("Senha");
		senhaField.setBorder(ViewUtil.Bordas.criarBordaArredondada(null));
		senhaField.setBounds(6, 72, 140, 43);
		panel_1.add(senhaField);
		
		exibirSenhaChbx = new JCheckBox("Ver");
		exibirSenhaChbx.setBounds(146, 81, 52, 18);
		panel_1.add(exibirSenhaChbx);
		
		exibirSenhaChbx.addItemListener(ItemEvent->{
			if(exibirSenhaChbx.isSelected())
				senhaField.setEchoChar((char)0);
			else
				senhaField.setEchoChar('*');
		});
		
		panel_2 = new JPanel();
		panel_2.setBorder(ViewUtil.Bordas.criarBordaTitulo("Dispon�vel"));
		panel_2.setBounds(10, 205, 75, 129);
		panel_2.setToolTipText("Disponibilidade para participar de outros projetos");
		add(panel_2);
		
		
		
		
		rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.setBounds(6, 24, 45, 38);
		rdbtnSim.setHorizontalTextPosition(SwingConstants.CENTER);
		rdbtnSim.setVerticalTextPosition(SwingConstants.TOP);
		panel_2.add(rdbtnSim);
		
		rdbtnNo = new JRadioButton("N�o");
		rdbtnNo.setHorizontalTextPosition(SwingConstants.CENTER);
		rdbtnNo.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnNo.setSelected(true);
		panel_2.add(rdbtnNo);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSim);
		group.add(rdbtnNo);
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			private static final long serialVersionUID = 1L;

			protected void abilitar() {
				
				getNomeField().setEnabled(true);
				getLoginField().setEnabled(true);
				getSenhaField().setEnabled(true);
				getCampoFormatadoCPF().setEnabled(true);
				getSexoComboBox().setEnabled(true);
				getNascimentoDateChooser().setEnabled(true);
				getRdbtnSim().setEnabled(true);
				getRdbtnNo().setEnabled(true);
				
				super.abilitar();
			}
			
			protected void desabilitar() {
				getNomeField().setEnabled(false);
				getLoginField().setEnabled(false);
				getSenhaField().setEnabled(false);
				getCampoFormatadoCPF().setEnabled(false);
				getSexoComboBox().setEnabled(false);
				getNascimentoDateChooser().setEnabled(false);
				getRdbtnSim().setEnabled(false);
				getRdbtnNo().setEnabled(false);
				
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBounds(6, 336, 288, 38);
		add(telaCadastroEdicao);
		
	}

	public void limparCampos() {
		getNomeField().setText("");
		getLoginField().setText("");
		getSenhaField().setText("");
		getCampoFormatadoCPF().setText("");
		getSexoComboBox().setSelectedIndex(0);
		getNascimentoDateChooser().setDate(DateUtil.getDataAtual());
		getRdbtnSim().setSelected(false);
		getRdbtnNo().setSelected(true);
	}
	public CampoTexto getNomeField() {
		return nomeField;
	}

	public CampoTextoFormatado getCampoFormatadoCPF() {
		return campoFormatadoCPF;
	}

	public JComboBox<String> getSexoComboBox() {
		return sexoComboBox;
	}

	public JDateChooser getNascimentoDateChooser() {
		return nascimentoDateChooser;
	}

	public CampoTexto getLoginField() {
		return loginField;
	}

	public CampoSenha getSenhaField() {
		return senhaField;
	}

	public JCheckBox getExibirSenhaChbx() {
		return exibirSenhaChbx;
	}

	public JRadioButton getRdbtnSim() {
		return rdbtnSim;
	}

	public JRadioButton getRdbtnNo() {
		return rdbtnNo;
	}
	
	public Botao getBotao() {
		return this.telaCadastroEdicao.getBtBotao1();
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
