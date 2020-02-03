package br.com.templatebase.view.back;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.templatebase.core.domain.Pessoa;
import br.com.templatebase.core.service.LoginService;

@ManagedBean(name = "login")
@ViewScoped
public class LoginBack implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	private String senha;
	
	private LoginService loginService;
	
	@ManagedProperty(value = "#{sessaoDoUsuario}")
	private SessaoDoUsuarioBack sessaoDoUsuarioBack;
	
	@PostConstruct
	public void inicializar() {
		this.loginService = new LoginService();
	}

	public String logar() {

		try {

			Pessoa pessoaLogada = loginService.realizarLoginPor(getLogin(), getSenha());

			this.sessaoDoUsuarioBack.setPessoaLogada(pessoaLogada);

			return "/pages/inicio?faces-redirect=true";

		}catch (Exception e){
			Messages.addGlobalError(e.getMessage());
		}

		return "";

	}
	
	//Getter's e Setter's	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSessaoDoUsuarioBack(SessaoDoUsuarioBack sessaoDoUsuarioBack) {
		this.sessaoDoUsuarioBack = sessaoDoUsuarioBack;
	}	

}
