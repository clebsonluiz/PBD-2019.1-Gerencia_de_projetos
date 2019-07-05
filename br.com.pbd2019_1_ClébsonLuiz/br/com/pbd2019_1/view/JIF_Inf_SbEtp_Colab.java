package br.com.pbd2019_1.view;

public class JIF_Inf_SbEtp_Colab extends JInternal_TelaInfoSubEtapa{

	private static final long serialVersionUID = 1L;

	public JIF_Inf_SbEtp_Colab() 
	{
		TelaInfoSubEtapaSubTarefas tela = getTelaInfoSubEtapaSubTarefas();
		
		TelaInfoSubEtapa telaEtapa = (TelaInfoSubEtapa)tela.getTelaInfoSubEtapa();
		
		telaEtapa.getTelaCadastroEdicao().esconderTglbtnBotao();
		
		telaEtapa.getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		telaEtapa.getTelaCadastroEdicao().getBtBotao1().setEnabled(false);
		
		telaEtapa.getTelaColaboradorEnvolvido().getBtAdicionarColaborador().setVisible(false);
		telaEtapa.getTelaColaboradorEnvolvido().getBtAdicionarColaborador().setEnabled(false);
		telaEtapa.getTelaColaboradorEnvolvido().getBtRemoverColaborador().setVisible(false);
		telaEtapa.getTelaColaboradorEnvolvido().getBtRemoverColaborador().setEnabled(false);
		
		tela.getTelaTarefas().getBtNovaTarefa().setVisible(false);
		tela.getTelaTarefas().getBtNovaTarefa().setEnabled(false);
	}
	
}
