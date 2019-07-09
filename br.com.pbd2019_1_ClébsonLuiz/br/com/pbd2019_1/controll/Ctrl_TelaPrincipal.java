package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.utils.UserUtil;
import br.com.pbd2019_1.view.JIF_Reset_Senha;
import br.com.pbd2019_1.view.JInternal_Sobre;
import br.com.pbd2019_1.view.MeuJDialog;
import br.com.pbd2019_1.view.TelaCadastro_Pessoa;
import br.com.pbd2019_1.view.TelaMenu;
import br.com.pbd2019_1.view.TelaOpcoes;
import br.com.pbd2019_1.view.TelaPrincipal;
import br.com.pbd2019_1.view.Tela_CadastroSuperUsuario;

public class Ctrl_TelaPrincipal {

	private Controlador_Principal ctrl_P;

	public Ctrl_TelaPrincipal(Controlador_Principal ctrl_P)
	{
		this.ctrl_P = ctrl_P;
	}
	
	public void adicionarEventos()
	{
		adicionarEventosTelaPrincipal(ctrl_P.getJanelaPrincipal().getTelaPrincipal());
	}
	
	private void adicionarEventosTelaPrincipal(TelaPrincipal telaPrincipal) {
		
		telaPrincipal.getTelaCadastro_Pessoa()
			.getBotao().addActionListener(ActionEvent->{
				try 
				{
					TelaCadastro_Pessoa telaPessoa = telaPrincipal.getTelaCadastro_Pessoa();
					
					ctrl_P.getControlador_Cadastro().cadastrarPessoa(telaPessoa, Pessoa.COMUM_USER, ctrl_P.gettPessoa(), null);
				} 
				catch (ValidacaoException e) 
				{
					MeuJDialog.exibirAlertaErro(null, "Erro ao cadastrar pessoa", e.getMessage());
				}
			});
		
		telaPrincipal.getTelaCadastro_Pessoa()
		.getBtCadastrarComoAdmin().addActionListener(ActionEvent->{
			try 
			{
				TelaCadastro_Pessoa telaPessoa = telaPrincipal.getTelaCadastro_Pessoa();
				
				ctrl_P.getControlador_Cadastro().cadastrarPessoa(telaPessoa, Pessoa.ADMIN_USER, ctrl_P.gettPessoa(), null);
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao cadastrar pessoa", e.getMessage());
			}
		});
		
		telaPrincipal.getTela_CadastroSuperUsuario().getBtMeCadastre().addActionListener((ActionEvent)->
		{
			try 
			{
				Tela_CadastroSuperUsuario telaS = telaPrincipal.getTela_CadastroSuperUsuario();
				
				String msg = "Uma das informações inserirdas na tela não condiz com o registrado no software";
				
				if(!telaS.getCmptxtNomebanco().getTexto().trim().equals(Controlador_Principal.SUPER_USER_NOME_BANCO))
					throw new ValidacaoException(msg);
				else if(!telaS.getCmptxtNomeusuariobanco().getTexto().trim().equals(Controlador_Principal.SUPER_USER_USUARIO_BANCO))
					throw new ValidacaoException(msg);
				else if(!telaS.getCmptxtNumeroentidades().getTexto().trim().equals(Controlador_Principal.SUPER_USER_QT_ENTIDADE_BANCO))
					throw new ValidacaoException(msg);
				else if(!telaS.getCmptxtPortabanco().getTexto().trim().equals(Controlador_Principal.SUPER_USER_PORTA_BANCO))
					throw new ValidacaoException(msg);
				else if(!telaS.getCmptxtSenhabanco().getTexto().trim().equals(Controlador_Principal.SUPER_USER_SENHA_BANCO))
					throw new ValidacaoException(msg);
				
				TelaCadastro_Pessoa telaPessoa = telaPrincipal.getTelaCadastro_Pessoa();
				
				ctrl_P.getControlador_Cadastro().cadastrarPessoa(telaPessoa, Pessoa.ADMIN_USER, ctrl_P.gettPessoa(), null);
				
				telaS.limparCampos();
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao cadastrar pessoa", e.getMessage());
			}
		});
		
		
		telaPrincipal.getTelaLoginSistema()
			.getBtnLogar().addActionListener(ActionEvent->{
				//TODO - Inserir aqui o login
				try 
				{
					String login_cpf = telaPrincipal
							.getTelaLoginSistema()
							.getLoginField()
							.getTexto();
					String senha = telaPrincipal
							.getTelaLoginSistema()
							.getSenhaField()
							.getTexto();
					
					
					ctrl_P.setPessoa_Logada(Fachada
							.getInstance()
							.getBoPessoa()
							.buscarUsuarioResetado(login_cpf));
					
					if(ctrl_P.getPessoa_Logada() != null)
					{
						
						String senhaNova = UserUtil.PasswordUtil.sugerirSenha();
						
						
						ctrl_P.getPessoa_Logada().setReset_senha(false);
						ctrl_P.getPessoa_Logada().setUser_senha(senhaNova);
						
						Fachada.getInstance().atualizar(ctrl_P.getPessoa_Logada());
						
						JIF_Reset_Senha.getInstance().getLblSenha().setText(senhaNova);
						JIF_Reset_Senha.getInstance().queroFoco();
						
						ctrl_P.setType_User_Logado(
								ctrl_P.getPessoa_Logada().getUser_type()
								); 
						
						if(ctrl_P.getType_User_Logado().equals(Pessoa.COMUM_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
						else if(ctrl_P.getType_User_Logado().equals(Pessoa.ADMIN_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
						else if(ctrl_P.getType_User_Logado().equals(Pessoa.SUPER_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_SUPER);
						
						telaPrincipal.exibirTela(TelaPrincipal.TELA_PRINCIPAL);
						
						telaPrincipal.getTelaLoginSistema().getLblErro().setText("");
					
					}
					else
					{
						ctrl_P.setPessoa_Logada(Fachada
								.getInstance()
								.getBoPessoa()
								.buscarUsuario(
										login_cpf,
										senha
										));
						
						if(ctrl_P.getPessoa_Logada() != null) 
						{
							ctrl_P.setType_User_Logado(
									ctrl_P.getPessoa_Logada().getUser_type()
									);
							
							if(ctrl_P.getType_User_Logado().equals(Pessoa.COMUM_USER))
								telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
							else if(ctrl_P.getType_User_Logado().equals(Pessoa.ADMIN_USER))
								telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
							else if(ctrl_P.getType_User_Logado().equals(Pessoa.SUPER_USER))
								telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_SUPER);
							
							telaPrincipal.exibirTela(TelaPrincipal.TELA_PRINCIPAL);
							
							telaPrincipal.getTelaLoginSistema().getLblErro().setText("");
						}
					}
					
					
				} 
				catch (ValidacaoException | NoSuchAlgorithmException | UnsupportedEncodingException e) 
				{
					telaPrincipal.getTelaLoginSistema().getLblErro().setText(e.getMessage());
				} 
				catch (PropertyVetoException e) 
				{
					e.printStackTrace();
				}
				
			});
		
		TelaOpcoes telaOpcoes1 = telaPrincipal.getTelaMenu().getTelaOpcoesComum();
		TelaOpcoes telaOpcoes2 = telaPrincipal.getTelaMenu().getTelaOpcoesAdmin();
		TelaOpcoes telaOpcoes3 = telaPrincipal.getTelaMenu().getTelaOpcoesSuper();
		
		adicionarEventoMenu(telaOpcoes1);
		adicionarEventoMenu(telaOpcoes2);
		adicionarEventoMenu(telaOpcoes3);
	}
	
	private void adicionarEventoMenu(TelaOpcoes telaOpcoes) {
		telaOpcoes.getBtnInfo().addActionListener(ActionEvent->{
			//TODO - Inserir evento info usuario
			//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				ctrl_P.getCtrl_PreenchementoTela().exibirJInternalInfoMinhaPessoa(ctrl_P.getPessoa_Logada());
			} 
			catch (ValidacaoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao Consultar Info de Pessoa", e.getMessage());
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaInfoPessoa", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnLog().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM de ver os logs dos usuarios
			//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try
			{
				ctrl_P.gettLogUpdate().getList().clear();
				ctrl_P.gettLogUpdate().fireTableDataChanged();
				ctrl_P.getjInternal_TabelaLogs().queroFoco();
			}
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao consultar a lista de Logs", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnPessoas().addActionListener(ActionEvent->{
			//TODO - Inserir evento ADM ver pessoas
			//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try
			{
				ctrl_P.gettPessoa().getList().clear();
				ctrl_P.gettPessoa().fireTableDataChanged();
				ctrl_P.getjInternal_TabelaPessoas().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaPessoas", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnBackup().addActionListener(ActionEvent->{
			//TODO - Inserir evento Backups BD
			//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				ctrl_P.getjInternal_TelaBackups().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir TelaBackup", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnSQL().addActionListener(ActionEvent->{
			//TODO - Inserir evento SUPER USUARIO Inserir SQL
			//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado)
			try 
			{
				ctrl_P.getjInternal_TelaInserirSQL().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela de SQL", e.getMessage());
			}
			
		});
		
		telaOpcoes.getBtnSobre().addActionListener(ActionEvent->{
			//if(!JInternal_TelaAlerta.isAtivado && !JInternal_Backup_Efetuando.isAtivado);
			//TODO - Inserir evento Abrir tela Info Projeto
			try 
			{
				JInternal_Sobre.getInstance().queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela sobre o projeto", e.getMessage());
			}
		});
		
		telaOpcoes.getBtnSair().addActionListener(ctrl_P.getControlador_Backup());
	}
	
	
}
