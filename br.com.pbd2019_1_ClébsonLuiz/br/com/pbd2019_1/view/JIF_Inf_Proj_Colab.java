package br.com.pbd2019_1.view;

public class JIF_Inf_Proj_Colab extends JInternal_TelaInfoProjeto_Etapas{

	private static final long serialVersionUID = 1L;

	public JIF_Inf_Proj_Colab() 
	{
		TelaProjeto_Etapas tela = getTelaProjeto_Etapas();
		
		tela.getTelaProjeto().getTelaCadastroEdicao().esconderTglbtnBotao();
		
		tela.getTelaProjeto().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		tela.getTelaProjeto().getTelaCadastroEdicao().getBtBotao1().setEnabled(false);
		tela.getTelaProjeto().getTelaCadastroEdicao().setVisible(false);
		tela.getTelaProjeto().getTelaCadastroEdicao().setEnabled(false);
		tela.getTelaColaboradores().getBtAdicionarColaborador().setVisible(false);
		tela.getTelaColaboradores().getBtAdicionarColaborador().setEnabled(false);
		tela.getTelaProjeto().getBotao1().setVisible(false);
		tela.getTelaEtapas().getBtNovaEtapa().setVisible(false);
		tela.getTelaEtapas().getBtNovaEtapa().setEnabled(false);
		
	}
	
}
