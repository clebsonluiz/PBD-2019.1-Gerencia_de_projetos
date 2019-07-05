package br.com.pbd2019_1.view;

public class JIF_Inf_Tarf_Colab extends JInternal_TelaInfoTarefa{

	private static final long serialVersionUID = 1L;

	public JIF_Inf_Tarf_Colab() 
	{
		
		TelaInfoTarefaColaborador tela = getTelaInfoTarefa();
		
		tela.getTelaInfoTarefa().getTelaCadastroEdicao().esconderTglbtnBotao();
		
		tela.getTelaInfoTarefa().getChckbxFinalizada().setEnabled(false);
		
		tela.getTelaInfoTarefa().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		tela.getTelaInfoTarefa().getTelaCadastroEdicao().getBtBotao1().setEnabled(false);
		
		tela.getTelaColaboradorEnvolvido().getBtAdicionarColaborador().setVisible(false);
		tela.getTelaColaboradorEnvolvido().getBtAdicionarColaborador().setEnabled(false);
		tela.getTelaColaboradorEnvolvido().getBtRemoverColaborador().setVisible(false);
		tela.getTelaColaboradorEnvolvido().getBtRemoverColaborador().setEnabled(false);
		
	}
	
}
