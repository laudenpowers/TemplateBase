package br.com.templatebase.view.back;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.templatebase.core.domain.Motorista;
import br.com.templatebase.core.service.MotoristaService;

@ManagedBean(name = "gerenciarMotoristaBack")
@ViewScoped
public class GerenciarMotoristaBack implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean isEmModoDeCadastro;
	
	private Motorista motorista;
	
	private List<Motorista> motoristas;
	
	private MotoristaService motoristaService;
	
	private String paramNome;
	
	@PostConstruct
	public void inicializar() {
		this.motorista = new Motorista();
		this.motoristas = new ArrayList<Motorista>();
		this.motoristaService = new MotoristaService();				
	}
	
	public void colocarEmModoDeCadastro() {
		this.isEmModoDeCadastro = true;
		this.motorista = new Motorista();
		this.motoristas = new ArrayList<Motorista>();
	}
	
	public void colocarEmModoDeListagem() {
		this.isEmModoDeCadastro = false;
		this.motorista = new Motorista();
		this.motoristas = new ArrayList<Motorista>();
	}
	
	public void colocarEmModoDeEdicao(Motorista motorista) {
		this.isEmModoDeCadastro = true;
		this.motorista = motorista;
		this.motoristas = new ArrayList<Motorista>();
	}
	
	public void salvar() {
		try {
			Motorista motoristaSalvo = motoristaService.salvar(getMotorista());
			this.motorista = motoristaSalvo;
			Messages.addGlobalInfo("Motorista salvo com sucesso");
		}catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void pesquisar() {
		try {		
			this.motoristas = motoristaService.listarPor(getParamNome());
		}catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void remover(Motorista motorista) {
		try {
			Motorista motoristaRemovido = motoristaService.remover(motorista);
			this.getMotoristas().remove(motoristaRemovido);		
			Messages.addGlobalInfo("Motorista removido com sucesso");
		}catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
		
	}

	public boolean isEmModoDeCadastro() {
		return isEmModoDeCadastro;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public String getParamNome() {
		return paramNome;
	}

	public void setParamNome(String paramNome) {
		this.paramNome = paramNome;
	}		

}
