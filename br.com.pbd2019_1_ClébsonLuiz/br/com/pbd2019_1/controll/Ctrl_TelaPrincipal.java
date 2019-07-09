package br.com.pbd2019_1.controll;

import java.beans.PropertyVetoException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.exception.ValidacaoException;
import br.com.pbd2019_1.fachada.Fachada;
import br.com.pbd2019_1.utils.UserUtil;
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
				
				if(!telaS.getCmptxtNomebanco().getTexto().trim().equals(SUPER_USER_NOME_BANCO))
					throw new ValidacaoException("Uma das informações inserirdas na tela não condiz com o registrado no software");
				else if(!telaS.getCmptxtNomeusuariobanco().getTexto().trim().equals(SUPER_USER_USUARIO_BANCO))
					throw new ValidacaoException("Uma das informações inserirdas na tela não condiz com o registrado no software");
				else if(!telaS.getCmptxtNumeroentidades().getTexto().trim().equals(SUPER_USER_QT_ENTIDADE_BANCO))
					throw new ValidacaoException("Uma das informações inserirdas na tela não condiz com o registrado no software");
				else if(!telaS.getCmptxtPortabanco().getTexto().trim().equals(SUPER_USER_PORTA_BANCO))
					throw new ValidacaoException("Uma das informações inserirdas na tela não condiz com o registrado no software");
				else if(!telaS.getCmptxtSenhabanco().getTexto().trim().equals(SUPER_USER_SENHA_BANCO))
					throw new ValidacaoException("Uma das informações inserirdas na tela não condiz com o registrado no software");
				
				TelaCadastro_Pessoa telaPessoa = telaPrincipal.getTelaCadastro_Pessoa();
				
				controlador_Cadastro.cadastrarPessoa(telaPessoa, Pessoa.ADMIN_USER, tPessoa, null);
				
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
					
					
					pessoa_Logada = Fachada
							.getInstance()
							.getBoPessoa()
							.buscarUsuarioResetado(login_cpf);
					
					if(pessoa_Logada != null)
					{
						
						String senhaNova = UserUtil.PasswordUtil.sugerirSenha();
						
						
						pessoa_Logada.setReset_senha(false);
						pessoa_Logada.setUser_senha(senhaNova);
						
						Fachada.getInstance().atualizar(pessoa_Logada);
						
						jif_Reset_Senha.getLblSenha().setText(senhaNova);
						jif_Reset_Senha.queroFoco();
						
						type_User_Logado = pessoa_Logada.getUser_type();
						
						if(type_User_Logado.equals(Pessoa.COMUM_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
						else if(type_User_Logado.equals(Pessoa.ADMIN_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
						else if(type_User_Logado.equals(Pessoa.SUPER_USER))
							telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_SUPER);
						
						telaPrincipal.exibirTela(TelaPrincipal.TELA_PRINCIPAL);
						
						telaPrincipal.getTelaLoginSistema().getLblErro().setText("");
					
					}
					else
					{
						pessoa_Logada = Fachada
								.getInstance()
								.getBoPessoa()
								.buscarUsuario(
										login_cpf,
										senha
										);
						
						if(pessoa_Logada != null) 
						{
							type_User_Logado = pessoa_Logada.getUser_type();
							
							if(type_User_Logado.equals(Pessoa.COMUM_USER))
								telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_COMUM);
							else if(type_User_Logado.equals(Pessoa.ADMIN_USER))
								telaPrincipal.getTelaMenu().exibirTelaOpcoes(TelaMenu.USER_ADMIN);
							else if(type_User_Logado.equals(Pessoa.SUPER_USER))
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
				ctrl_PreenchementoTela.exibirJInternalInfoMinhaPessoa(pessoa_Logada);
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
				tLogUpdate.getList().clear();
				tLogUpdate.fireTableDataChanged();
				jInternal_TabelaLogs.queroFoco();
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
				tPessoa.getList().clear();
				tPessoa.fireTableDataChanged();
				jInternal_TabelaPessoas.queroFoco();
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
				jInternal_TelaBackups.queroFoco();
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
				jInternal_TelaInserirSQL.queroFoco();
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
				jInternal_Sobre.queroFoco();
			} 
			catch (PropertyVetoException e) 
			{
				MeuJDialog.exibirAlertaErro(null, "Erro ao exibir tela sobre o projeto", e.getMessage());
			}
		});
		
		telaOpcoes.getBtnSair().addActionListener(controlador_Backup);
	}
	
	
}
