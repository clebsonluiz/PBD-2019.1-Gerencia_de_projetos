package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import br.com.pbd2019_1.utils.ViewUtil;
import br.com.pbd2019_1.utils.ViewUtil.Bordas;

public abstract class TelaProjeto extends JPanel {

	private static final long serialVersionUID = 1L;
	private CampoTexto nomeProjetoField;
	private JTextArea descricaoTextArea;
	private JDateChooser dataInicioDateChooser;
	private JDateChooser dataFimDateChooser;

	private TelaCadastroEdicao telaCadastroEdicao;
	
	public TelaProjeto() {
		setMinimumSize(new Dimension(510, 210));
		setPreferredSize(new Dimension(510, 210));
		setLayout(new BorderLayout());
		
		JPanel p = new JPanel(null);
		p.setMinimumSize(new Dimension(510, 175));
		p.setPreferredSize(new Dimension(510, 175));
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 5, 276, 160);
		panel.setBorder(BorderFactory.createTitledBorder("Sobre o projeto"));
		p.add(panel);
		panel.setLayout(null);
		
		nomeProjetoField = new CampoTexto();
		nomeProjetoField.setBorder(ViewUtil.Bordas.criarBordaArredondada(null));
		nomeProjetoField.setDescricao("Nome do Projeto");
		nomeProjetoField.setBounds(6, 21, 264, 42);
		panel.add(nomeProjetoField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 68, 264, 86);
		panel.add(scrollPane);
		
		descricaoTextArea = new JTextArea();
		descricaoTextArea.setWrapStyleWord(true);
		descricaoTextArea.setLineWrap(true);
		scrollPane.setViewportView(descricaoTextArea);
		scrollPane.setBorder(ViewUtil.Bordas.criarBordaTituloField("Descrição"));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("Datas"));
		panel_1.setBounds(286, 5, 215, 160);
		p.add(panel_1);
		panel_1.setLayout(null);
		
		dataInicioDateChooser = new JDateChooser();
		dataInicioDateChooser.setBorder(ViewUtil.Bordas.criarBordaTituloField("Data Inicial"));
		dataInicioDateChooser.setBounds(16, 21, 180, 60);
		panel_1.add(dataInicioDateChooser);
		
		dataFimDateChooser = new JDateChooser();
		dataFimDateChooser.setBorder(Bordas.criarBordaTituloField("Data Final"));
		dataFimDateChooser.setBounds(16, 82, 180, 60);
		panel_1.add(dataFimDateChooser);
		
		telaCadastroEdicao = new TelaCadastroEdicao() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void abilitar() {
				nomeProjetoField.setEnabled(true);
				descricaoTextArea.setEnabled(true);
				dataInicioDateChooser.setEnabled(true);
				dataFimDateChooser.setEnabled(true);
				super.abilitar();
			}
			
			@Override
			protected void desabilitar() {
				nomeProjetoField.setEnabled(false);
				descricaoTextArea.setEnabled(false);
				dataInicioDateChooser.setEnabled(false);
				dataFimDateChooser.setEnabled(false);
				super.desabilitar();
			}
			
		};
		
		add(p, BorderLayout.CENTER);
		adicionarNorth();
	}

	protected void adicionarNorth() {add(telaCadastroEdicao, BorderLayout.NORTH);}
	protected void adicionarSouth() {add(telaCadastroEdicao, BorderLayout.SOUTH);}
	
	public CampoTexto getNomeProjetoField() {
		return nomeProjetoField;
	}

	public JTextArea getDescricaoTextArea() {
		return descricaoTextArea;
	}

	public JDateChooser getDataInicioDateChooser() {
		return dataInicioDateChooser;
	}

	public JDateChooser getDataFimDateChooser() {
		return dataFimDateChooser;
	}

	public TelaCadastroEdicao getTelaCadastroEdicao() {
		return telaCadastroEdicao;
	}
	
	public Botao btnBotao1() {
		return telaCadastroEdicao.getBtBotao1();
	}
}
