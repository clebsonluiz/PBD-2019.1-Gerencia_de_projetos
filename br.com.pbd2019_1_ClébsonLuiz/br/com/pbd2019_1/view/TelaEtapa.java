package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import br.com.pbd2019_1.utils.ViewUtil;

public abstract class TelaEtapa extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private CampoTexto nomeEtapaField;
	private JTextArea descricaoTextArea;
	private JProgressBar barraProgressBar;

	private TelaCadastroEdicao telaCadastroEdicao;
	private TelaColaboradorEnvolvido telaColaboradorEnvolvido;
	
	public TelaEtapa(String tabEtapa, String tabColaborador) {
		
		setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(300, 360));
		setPreferredSize(new Dimension(300, 360));
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		JPanel p0 = new JPanel();
		
		
		p0.setBackground(getBackground());
		
		p0.setMinimumSize(new Dimension(300, 320));
		p0.setPreferredSize(new Dimension(300, 320));
		p0.setLayout(new BorderLayout(5, 5));
		
		telaColaboradorEnvolvido = new TelaColaboradorEnvolvido(tabColaborador);
		telaColaboradorEnvolvido.setMinimumSize(p0.getPreferredSize());
		
		
		MeuJPanel panel1 = new MeuJPanel(new GridBagLayout());
		MeuJPanel panel2 = new MeuJPanel(new GridBagLayout());
		
		panel1.add(p0);
		panel2.add(telaColaboradorEnvolvido);
		
		
		tabbedPane.addTab(tabEtapa, panel1);
		tabbedPane.addTab(tabColaborador, panel2);
		
		
		JPanel p = new JPanel(null);
		p.setBackground(getBackground());
		JPanel panel = new JPanel();
		panel.setBackground(getBackground());
		panel.setBorder(BorderFactory.createTitledBorder("Etapa"));
		panel.setBounds(6, 6, 288, 200);
		p.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(ViewUtil.Bordas.criarBordaTituloField("Descrição"));
		scrollPane.setBounds(6, 69, 276, 125);
		panel.add(scrollPane);
		
		descricaoTextArea = new JTextArea();
		descricaoTextArea.setWrapStyleWord(true);
		descricaoTextArea.setLineWrap(true);
		scrollPane.setViewportView(descricaoTextArea);
		
		nomeEtapaField = new CampoTexto();
		nomeEtapaField.setBorder(ViewUtil.Bordas.criarBordaArredondada(null));
		nomeEtapaField.setDescricao("Nome da Etapa");
		nomeEtapaField.setBounds(8, 22, 272, 46);
		panel.add(nomeEtapaField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(ViewUtil.Bordas.criarBordaTitulo("Progresso"));
		panel_1.setToolTipText("Progresso em andamanto da etapa");
		panel_1.setBounds(6, 210, 288, 68);
		panel_1.setBackground(getBackground());
		p.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		barraProgressBar = new JProgressBar();
		barraProgressBar.setStringPainted(true);
		barraProgressBar.setMinimum(0);
		barraProgressBar.setMaximum(100);
		barraProgressBar.setToolTipText("Progresso em andamanto da etapa");
		panel_1.add(barraProgressBar, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(getBackground());
		panel_2.setBounds(6, 218, 288, 60);
		p.add(panel_2);
		
		
		telaCadastroEdicao = new TelaCadastroEdicao() {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void abilitar() {
				nomeEtapaField.setEnabled(true);
				descricaoTextArea.setEnabled(true);
				super.abilitar();
			}
			
			@Override
			protected void desabilitar() {
				nomeEtapaField.setEnabled(false);
				descricaoTextArea.setEnabled(false);
				super.desabilitar();
			}
		};
		telaCadastroEdicao.setBackground(getBackground());
		p0.add(p, BorderLayout.CENTER);
		p0.add(telaCadastroEdicao, BorderLayout.SOUTH);
		
	}

	public void limparCampos() {
		nomeEtapaField.setText("");
		descricaoTextArea.setText("");
	}
	
	public CampoTexto getNomeEtapaField() {
		return nomeEtapaField;
	}

	public JTextArea getDescricaoTextArea() {
		return descricaoTextArea;
	}

	public JProgressBar getBarraProgressBar() {
		return barraProgressBar;
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}
	
	public Botao getBotao1() {
		return telaCadastroEdicao.getBtBotao1();
	}
}
