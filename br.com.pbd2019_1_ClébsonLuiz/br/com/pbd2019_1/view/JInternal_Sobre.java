package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.pbd2019_1.utils.ViewUtil;
import javax.swing.border.EmptyBorder;

public class JInternal_Sobre extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	public JInternal_Sobre() {
		super("Sobre");
		setPreferredSize(new Dimension(450, 350));
		setSize(new Dimension(450, 350));
		setIconifiable(false);
		
		JPanel panel = new MeuJPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel(ViewUtil.Icones.IMAGEM_LOGO_UAST);
		panel.add(label, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(panel.getBackground());
		scrollPane.setBorder(null);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		textArea.setBackground(panel.getBackground());
		scrollPane.setViewportView(textArea);
		textArea.setFocusable(false);
		textArea.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		textArea.setText(
						  "\tProjeto desenvolvido para a disciplina de Projeto de Banco de Dados - PBD, "
						+ "utilizando JPA + Hibernate para comunicação e interação com o banco de dados. "
						+ "Projeto este que tem como objetivos de software, ser útil para gestão de projetos "
						+ "na qual deve fornecer as funcionalidades basicas para gerenciar um projeto em andamento. "
						+ "Em objetivo de Projeto de disciplina, este projeto tem como função, cumprir com os requisitos "
						+ "da 1ª Verificação de Aprendizagem (V.A.), 2ª V.A. e até 3ª V.A. Requisitos estes apresentados "
						+ "em mais detalhes expressados no documento do Sistema."
						+ "\n"
						+ "\nBibliotecas Usadas para Execução deste Projeto:"
						+ "\n\t- JPA + Hibernate"
						+ "\n\t- IText PDF"
						+ "\n\t- JCalendar"
						+ "\n\t- JFreeChat"
						+ "\n"
						+ "\nIcones usados neste projeto retirados dos seguintes SITES:"
						+ "\n"
						+ "\n\thttps://www.flaticon.com/"
						+ "\n\tAutores:"
						+ "\n\t- Freepik"
						+ "\n\t- Smashicons"
						+ "\n\t- Flat Icons"
						+ "\n\t- Eucalyp"
						+ "\n\t- Prettycons"
//						+ "\n\t-Pixabay"
						+ "\n"
						+ "\nLogo do IText PDF"
						+ "\nhttps://itextpdf.com"
						+ "\n"
						+ "\nLogo da Universidade Acima"
						+ "\nhttp://ww4.ufrpe.br/uast/oldsite/images/ufrpe-uast-logos-800.png"
						+ "\n"
						+ "\nOutras Imagens apresentadas neste projeto são de domínio público e não precisam ser referenciadas. Como algumas da Area de Trabalho que são do Pixabay"
						+ "\n"
						+ "\n");
		
	}

	public void queroFoco() throws PropertyVetoException {
		setMaximum(true);
		super.queroFoco();
	}
	@Override
	protected void fechar() {}

}
