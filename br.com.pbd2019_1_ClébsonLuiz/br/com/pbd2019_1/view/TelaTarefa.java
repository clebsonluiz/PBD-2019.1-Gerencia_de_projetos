package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Bordas;

public abstract class TelaTarefa extends JPanel {

	private static final long serialVersionUID = 1L;
	private CampoTexto nomeTarefaField;
	private JTextArea descricaoTextArea;
	private Horario horario;
	private JDateChooser dateChooser;
	private JCheckBox chckbxFinalizada;
	private JComboBox<String> prioridadeComboBox;
	private TelaCadastroEdicao telaCadastroEdicao;
	
	
	public TelaTarefa() {
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(300, 380));
		setLayout(new BorderLayout());
		
		JPanel p = new JPanel(null);
		
		p.setMinimumSize(new Dimension(300, 350));
		p.setPreferredSize(new Dimension(300, 350));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("Tarefa"));
		panel.setBounds(6, 6, 288, 338);
		p.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(Bordas.criarBordaTituloField("Descrição"));
		scrollPane.setBounds(6, 69, 276, 110);
		panel.add(scrollPane);
		
		descricaoTextArea = new JTextArea();
		descricaoTextArea.setWrapStyleWord(true);
		descricaoTextArea.setLineWrap(true);
		scrollPane.setViewportView(descricaoTextArea);
		
		nomeTarefaField = new CampoTexto();
		nomeTarefaField.setDescricao("Nome da Tarefa");
		nomeTarefaField.setBorder(Bordas.criarBordaArredondada(null));
		nomeTarefaField.setBounds(8, 22, 272, 46);
		panel.add(nomeTarefaField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("Configuração"));
		panel_1.setBounds(6, 191, 276, 141);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		horario = new Horario();
		horario.setBounds(17, 22, 160, 50);
		panel_1.add(horario);
		
		dateChooser = new JDateChooser();
		dateChooser.setDate(DateUtil.getDataAtual());
		dateChooser.setBorder(ViewUtil.Bordas.criarBordaTituloField("Data"));
		dateChooser.setBounds(17, 72, 160, 59);
		panel_1.add(dateChooser);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(ViewUtil.Bordas.criarBordaTitulo("Finalizada"));
		panel_2.setBounds(189, 22, 70, 50);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		chckbxFinalizada = new JCheckBox("     ");
		chckbxFinalizada.setBounds(6, 18, 58, 26);
		chckbxFinalizada.setHorizontalTextPosition(SwingConstants.LEFT);
		panel_2.add(chckbxFinalizada);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(ViewUtil.Bordas.criarBordaTituloField("Prioridade"));
		panel_3.setBounds(180, 72, 88, 59);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		
		prioridadeComboBox = new JComboBox<String>();
		
		prioridadeComboBox.addItem("Baixa");
		prioridadeComboBox.addItem("Media");
		prioridadeComboBox.addItem("Alta");
		prioridadeComboBox.setSelectedIndex(0);
		panel_3.add(prioridadeComboBox, BorderLayout.CENTER);
		
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void abilitar() {
				nomeTarefaField.setEnabled(true);
				descricaoTextArea.setEnabled(true);
				prioridadeComboBox.setEnabled(true);
				chckbxFinalizada.setEnabled(true);
				horario.setEnabled(true);
				dateChooser.setEnabled(true);
				super.abilitar();
			}
			
			@Override
			protected void desabilitar() {
				nomeTarefaField.setEnabled(false);
				descricaoTextArea.setEnabled(false);
				prioridadeComboBox.setEnabled(false);
				chckbxFinalizada.setEnabled(false);
				horario.setEnabled(false);
				dateChooser.setEnabled(true);
				super.desabilitar();
			}
		};
		
		add(p, BorderLayout.CENTER);
		add(telaCadastroEdicao, BorderLayout.SOUTH);
	}
	
	public void limparCampos() {
		nomeTarefaField.setText("");
		descricaoTextArea.setText("");
		prioridadeComboBox.setSelectedIndex(0);
		chckbxFinalizada.setSelected(false);
		horario.setLocalTime("0", "0", "0");
		dateChooser.setDate(DateUtil.getDataAtual());
	}

	public CampoTexto getNomeTarefaField() {
		return nomeTarefaField;
	}

	public JTextArea getDescricaoTextArea() {
		return descricaoTextArea;
	}

	public JCheckBox getChckbxFinalizada() {
		return chckbxFinalizada;
	}

	public Horario getHorario() {
		return horario;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JComboBox<String> getPrioridadeComboBox() {
		return prioridadeComboBox;
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}
	
	public Botao getBotao1() {
		return telaCadastroEdicao.getBtBotao1();
	}
	
	
}
