package br.com.templatebase.view.back;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

import br.com.templatebase.core.domain.Pessoa;
import br.com.templatebase.view.util.BundleUtils;

@ManagedBean(name = "sessaoDoUsuario")
@SessionScoped
public class SessaoDoUsuarioBack implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoaLogada;
	
	private boolean isSessaoExpirada;
	
	@PostConstruct
	public void inicializar() {
	
	}
	
	public void deslogar(){
		
		Faces.invalidateSession();
		
		try {
			Faces.redirect("./login.xhtml");
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
		
	}
	
	public String getDescricaoDo(Enum<?> enumObject){
		return new BundleUtils().getDescricaoEnum(enumObject);
	}
	
	public Pessoa getPessoaLogada() {
		return pessoaLogada;
	}

	public void setPessoaLogada(Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}
	
	public boolean isLoginRealizado(){
		return pessoaLogada != null;
	}

	public boolean isSessaoExpirada() {
		return isSessaoExpirada;
	}

	public void setSessaoExpirada(boolean isSessaoExpirada) {
		this.isSessaoExpirada = isSessaoExpirada;
	}

}
