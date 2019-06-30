package br.com.pbd2019_1.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.ViewUtil;

public class TelaCadastro_Pessoa extends MeuJPanel{

	private static final long serialVersionUID = 1L;
	private TelaMiniPessoa1 telaMiniPessoa1;
	private TelaMiniPessoa2 telaMiniPessoa2;
	
	public TelaCadastro_Pessoa() {
		setLayout(null);
//		getTelaCadastroEdicao().abilitar();
//		getTelaCadastroEdicao().esconderTglbtnBotao();
		setMinimumSize(new Dimension(510, 370));
		setPreferredSize(new Dimension(510, 370));
		
		telaMiniPessoa1 = new TelaMiniPessoa1();
		telaMiniPessoa1.getTelaCadastroEdicao().setSize(485, 56);
		telaMiniPessoa1.getTelaCadastroEdicao().setLocation(6, 306);
		telaMiniPessoa1.getTelaCadastroEdicao().abilitar();
		telaMiniPessoa1.getTelaCadastroEdicao().esconderTglbtnBotao();
		telaMiniPessoa1.getTelaCadastroEdicao().getBtBotao1().setPreferredSize(new Dimension(100, 40));
		telaMiniPessoa1.getRdbtnNo().setText("N\u00E3o estou dispon\u00EDvel");
		telaMiniPessoa1.getRdbtnSim().setHorizontalAlignment(SwingConstants.CENTER);
		telaMiniPessoa1.getRdbtnSim().setHorizontalTextPosition(SwingConstants.CENTER);
		telaMiniPessoa1.getRdbtnNo().setHorizontalAlignment(SwingConstants.CENTER);
		telaMiniPessoa1.getRdbtnNo().setHorizontalTextPosition(SwingConstants.CENTER);
		
		telaMiniPessoa1.getPanel_1().setSize(185, 141);
		telaMiniPessoa1.getPanel_1().setBorder(ViewUtil.Bordas.criarBordaTitulo("Disponíbilidade para projetos"));
		telaMiniPessoa1.getNascimentoDateChooser().setSize(170, 45);
		telaMiniPessoa1.getSexoComboBox().setSize(170, 40);
		telaMiniPessoa1.getSexoComboBox().setLocation(6, 28);
		telaMiniPessoa1.getNascimentoDateChooser().setLocation(6, 84);
		telaMiniPessoa1.getPanel().setSize(185, 150);
		telaMiniPessoa1.getPanel_1().setLocation(306, 153);
		telaMiniPessoa1.getPanel().setLocation(306, 6);
		telaMiniPessoa1.setBounds(6, 6, 498, 368);
		
		add(telaMiniPessoa1);
		
		telaMiniPessoa2 = new TelaMiniPessoa2();
		telaMiniPessoa2.getExibirSenhaChbx0().setBounds(215, 22, 75, 41);
		((TitledBorder)telaMiniPessoa2.getBorder()).setTitle("Senha e Confirmar senha");;
		telaMiniPessoa2.getPanel().setBounds(6, 6, 300, 140);
		telaMiniPessoa2.getExibirSenhaChbx().setLocation(217, 76);
		telaMiniPessoa2.getSenhaNovaField().setSize(202, 45);
		telaMiniPessoa2.getSenhaNovaField().setLocation(10, 80);
		telaMiniPessoa2.getSenhaAntigaField().setBounds(10, 25, 202, 45);
		telaMiniPessoa2.setBounds(0, 147, 319, 150);
		
		telaMiniPessoa2.getSenhaAntigaField().setDescricao("Senha");
		telaMiniPessoa2.getSenhaNovaField().setDescricao("Confirmar Senha");
		
		telaMiniPessoa1.add(telaMiniPessoa2);
		
		KeyAdapter kListener = new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyPressed(e);
				String str1 = telaMiniPessoa2.getSenhaAntigaField().getTexto();
				String str2 = telaMiniPessoa2.getSenhaNovaField().getTexto();
				
				if(str2.equals(str1) && !str1.trim().equals(""))
				{
					telaMiniPessoa2.getSenhaNovaField().setBackground(Color.green.brighter());
					telaMiniPessoa2.getSenhaNovaField().setForeground(Color.WHITE);
				}
				else if(!str2.equals(str1) && !str1.trim().equals(""))
				{
					telaMiniPessoa2.getSenhaNovaField().setBackground(Color.red.brighter());
					telaMiniPessoa2.getSenhaNovaField().setForeground(Color.WHITE);
				}
				else
				{
					telaMiniPessoa2.getSenhaNovaField().setBackground(Color.white);
					telaMiniPessoa2.getSenhaNovaField().setForeground(Color.black);
				}
			}
		};
		
		telaMiniPessoa2.getSenhaAntigaField().addKeyListener(kListener);
		telaMiniPessoa2.getSenhaNovaField().addKeyListener(kListener);
	}


	public void limparCampos() {
		telaMiniPessoa1.limparCampos();
		telaMiniPessoa2.limparCampos();
	}
	public CampoTexto getNomeField() {
		return telaMiniPessoa1.getNomeField();
	}

	public CampoTextoFormatado getCampoFormatadoCPF() {
		return telaMiniPessoa1.getCampoFormatadoCPF();
	}

	public JComboBox<String> getSexoComboBox() {
		return telaMiniPessoa1.getSexoComboBox();
	}

	public JDateChooser getNascimentoDateChooser() {
		return telaMiniPessoa1.getNascimentoDateChooser();
	}

	public CampoSenha getSenhaField() {
		return telaMiniPessoa2.getSenhaAntigaField();
	}

	public CampoSenha getSenhaComfirmaField() {
		return telaMiniPessoa2.getSenhaNovaField();
	}

	public JCheckBox getExibirSenhaChbx() {
		return telaMiniPessoa2.getExibirSenhaChbx();
	}

	public JRadioButton getRdbtnSim() {
		return telaMiniPessoa1.getRdbtnSim();
	}

	public JRadioButton getRdbtnNo() {
		return telaMiniPessoa1.getRdbtnNo();
	}
	
	public Botao getBotao() {
		return this.telaMiniPessoa1.getTelaCadastroEdicao().getBtBotao1();
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaMiniPessoa1.getTelaCadastroEdicao();
	}

	public JPanel getPanel() {
		return telaMiniPessoa1.getPanel();
	}

	public JPanel getPanel_1() {
		return telaMiniPessoa1.getPanel_1();
	}

	public JPanel getPanel_2() {
		return telaMiniPessoa1.getPanel_2();
	}
	
}
