package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.pbd2019_1.utils.ViewUtil;

public class Tela_CadastroSuperUsuario extends MeuJPanel {

	private static final long serialVersionUID = 1L;
	private Botao btMeCadastre;
	private Botao btCancelar;
	private CampoTexto cmptxtNomebanco;
	private CampoTexto cmptxtNomeusuariobanco;
	private CampoTexto cmptxtNumeroentidades;
	private CampoTexto cmptxtPortabanco;
	private CampoTexto cmptxtSenhabanco;

	public Tela_CadastroSuperUsuario()
	{
		setMinimumSize(new Dimension(510, 270));
		setPreferredSize(new Dimension(510, 270));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new MeuJPanel();
		add(panel, BorderLayout.SOUTH);
		
		btMeCadastre = new Botao();
		btMeCadastre.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		btMeCadastre.setText("Me Cadastre");
		panel.add(btMeCadastre);
		
		btCancelar = new Botao();
		btCancelar.setFont(ViewUtil.Fonts.Arial.ARIAL_MEDIO_B);
		btCancelar.setText("Cancelar");
		panel.add(btCancelar);
		
		JPanel panel_1 = new MeuJPanel();
		add(panel_1, BorderLayout.NORTH);
		
		JLabel lblMeCadastreComo = new JLabel("Comprove que és um usuario do banco");
		lblMeCadastreComo.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		panel_1.add(lblMeCadastreComo);
		
		JPanel panel_2 = new MeuJPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		cmptxtNomebanco = new CampoTexto();
		cmptxtNomebanco.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.gray));
		cmptxtNomebanco.setDescricao("Nome do Banco");
		cmptxtNomebanco.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		cmptxtNomebanco.setBounds(6, 6, 186, 50);
		panel_2.add(cmptxtNomebanco);
		
		cmptxtNomeusuariobanco = new CampoTexto();
		cmptxtNomeusuariobanco.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.gray));
		cmptxtNomeusuariobanco.setDescricao("Nome do Usuario do Banco");
		cmptxtNomeusuariobanco.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		cmptxtNomeusuariobanco.setBounds(204, 6, 300, 50);
		panel_2.add(cmptxtNomeusuariobanco);
		
		cmptxtNumeroentidades = new CampoTexto();
		cmptxtNumeroentidades.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.gray));
		cmptxtNumeroentidades.setDescricao("Nº de Tabelas Reais");
		cmptxtNumeroentidades.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		cmptxtNumeroentidades.setBounds(6, 68, 300, 50);
		panel_2.add(cmptxtNumeroentidades);
		
		cmptxtPortabanco = new CampoTexto();
		cmptxtPortabanco.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.gray));
		cmptxtPortabanco.setDescricao("Nº. da Porta do Banco");
		cmptxtPortabanco.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		cmptxtPortabanco.setBounds(6, 130, 498, 50);
		panel_2.add(cmptxtPortabanco);
		
		cmptxtSenhabanco = new CampoTexto();
		cmptxtSenhabanco.setBorder(ViewUtil.Bordas.criarBordaArredondada(Color.gray));
		cmptxtSenhabanco.setDescricao("Senha do Banco");
		cmptxtSenhabanco.setFont(ViewUtil.Fonts.Arial.ARIAL_TITULO_B);
		cmptxtSenhabanco.setBounds(318, 68, 186, 50);
		panel_2.add(cmptxtSenhabanco);
	}

	public void limparCampos()
	{
		cmptxtNomebanco.setTexto("");
		cmptxtNomeusuariobanco.setTexto("");
		cmptxtNumeroentidades.setTexto("");
		cmptxtPortabanco.setTexto("");
		cmptxtSenhabanco.setTexto("");
	}
	
	public Botao getBtMeCadastre() {
		return btMeCadastre;
	}

	public Botao getBtCancelar() {
		return btCancelar;
	}

	public CampoTexto getCmptxtNomebanco() {
		return cmptxtNomebanco;
	}

	public CampoTexto getCmptxtNomeusuariobanco() {
		return cmptxtNomeusuariobanco;
	}

	public CampoTexto getCmptxtNumeroentidades() {
		return cmptxtNumeroentidades;
	}

	public CampoTexto getCmptxtPortabanco() {
		return cmptxtPortabanco;
	}

	public CampoTexto getCmptxtSenhabanco() {
		return cmptxtSenhabanco;
	}
	
}
