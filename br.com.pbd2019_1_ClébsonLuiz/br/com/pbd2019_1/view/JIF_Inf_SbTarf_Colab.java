package br.com.pbd2019_1.view;

public class JIF_Inf_SbTarf_Colab extends JInternal_TelaInfoSubTarefa{

	private static final long serialVersionUID = 1L;

	public JIF_Inf_SbTarf_Colab()
	{
		TelaInfoSubTarefa tela = getTelaInfoSubTarefa();
		tela.getChckbxFinalizada().setEnabled(false);
		tela.getTelaCadastroEdicao().esconderTglbtnBotao();
		
		tela.getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		tela.getTelaCadastroEdicao().getBtBotao1().setEnabled(false);
		
	}
	
}
