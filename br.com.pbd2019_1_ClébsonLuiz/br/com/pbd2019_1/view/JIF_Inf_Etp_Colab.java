package br.com.pbd2019_1.view;

public class JIF_Inf_Etp_Colab extends JInternal_TelaInfoEtapa{

	private static final long serialVersionUID = 1L;

	public JIF_Inf_Etp_Colab()
	{
		TelaEtapa_Tarefas tela = getTelaEtapa_Tarefas();
		
		TelaInfoEtapa telaEtapa = (TelaInfoEtapa)tela.getTelaEtapa();
		
		telaEtapa.getTelaCadastroEdicao().esconderTglbtnBotao();
		telaEtapa.getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		telaEtapa.getTelaCadastroEdicao().getBtBotao1().setEnabled(false);
		
		telaEtapa.getTelaColaboradorEnvolvido().getBtAdicionarColaborador().setVisible(false);
		telaEtapa.getTelaColaboradorEnvolvido().getBtAdicionarColaborador().setEnabled(false);
		telaEtapa.getTelaColaboradorEnvolvido().getBtRemoverColaborador().setVisible(false);
		telaEtapa.getTelaColaboradorEnvolvido().getBtRemoverColaborador().setEnabled(false);
		
		tela.getTelaSubEtapas().getBtNovaEtapa().setVisible(false);
		tela.getTelaSubEtapas().getBtNovaEtapa().setEnabled(false);
		
		tela.getTelaTarefas().getBtNovaTarefa().setVisible(false);
		tela.getTelaTarefas().getBtNovaTarefa().setEnabled(false);
		
	}
	
	
	
}
