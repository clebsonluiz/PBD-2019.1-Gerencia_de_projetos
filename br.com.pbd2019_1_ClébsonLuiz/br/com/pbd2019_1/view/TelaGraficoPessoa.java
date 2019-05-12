package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import br.com.pbd2019_1.utils.ChartsUtil;
import br.com.pbd2019_1.utils.ViewUtil;

public class TelaGraficoPessoa extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFreeChart graficoPizza;
	
	public TelaGraficoPessoa() {
		setLayout(new BorderLayout());

		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(300, 380));
		graficoPizza = ChartsUtil.getPizza("Desenpenho Geral");
		
		ChartPanel chartPanel = ChartsUtil.getChartPanel(graficoPizza);
		
		graficoPizza.getPlot().setBackgroundPaint(getBackground());
		graficoPizza.setBackgroundPaint(getBackground());
		chartPanel.setBackground(getBackground());
		
		
		add(chartPanel, BorderLayout.CENTER);
	
		JPanel panel = new JPanel(null);
		panel.setMinimumSize(new Dimension(300, 130));
		panel.setPreferredSize(new Dimension(300, 130));
		panel.setBackground(getBackground());
		add(panel, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(getBackground());
		
		
		TitledBorder border = (TitledBorder) ViewUtil.Bordas.criarBordaTitulo("Nº de Tarefas");
		border.setTitleFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);

		panel_2.setBorder(border);
		panel_2.setLayout(null);
		panel_2.setBounds(10, 6, 280, 118);
		panel.add(panel_2);
		
		JLabel label = new JLabel("Total : ");
		label.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		label.setBounds(18, 50, 100, 45);
		panel_2.add(label);
		
		JLabel lblFinalizadas = new JLabel("Finalizadas : ");
		lblFinalizadas.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		lblFinalizadas.setBounds(130, 50, 124, 45);
		panel_2.add(lblFinalizadas);
	}

	public JFreeChart getGraficoPizza() {
		return graficoPizza;
	}
}
