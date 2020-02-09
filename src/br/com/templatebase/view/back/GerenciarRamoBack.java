package br.com.templatebase.view.back;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.templatebase.core.domain.Ramo;
import br.com.templatebase.core.service.RamoService;

@ManagedBean (name="gerenciarRamo")
@ViewScoped
public class GerenciarRamoBack {
	private boolean isModoCadastro;
	private Ramo ramo;
	private String nomeParaBusca;
	private RamoService ramoService;
	private List<Ramo> ramosEncontrados;
	private String subtitulo;
	
	@PostConstruct
	public void inicializar() {
		this.ramo=new Ramo();
		this.ramoService=new RamoService();
		this.isModoCadastro=false;
		this.nomeParaBusca="";
		this.ramosEncontrados = new ArrayList<Ramo>();
		this.subtitulo = "Listar";
	}	
	
	public void colocarEmModoDeCadastro() {
		this.isModoCadastro = true;
		this.ramo = new Ramo();
		this.subtitulo = "Novo";
	}
	
	public void colocarEmModoDePesquisa() {
		this.isModoCadastro = false;
		this.ramo = new Ramo();
		this.subtitulo = "Listar";
	}
	
	public void colocarEmModoDeEdicao(Ramo ramo) {
		this.ramo = ramo;
		this.isModoCadastro = true;
		this.subtitulo = "Edição";
	}
	
	public void remover(Ramo ramo) {
		this.ramoService.remover(ramo);
		this.ramosEncontrados.remove(ramo);
		Messages.addGlobalInfo("Ramo removido com sucesso");
	}
	
	public void salvar() {
		try {
			ramoService.salvar(ramo);	
			this.ramo = new Ramo();
			Messages.addGlobalInfo("Ramo salvo com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void pesquisar(){
		this.ramosEncontrados = ramoService.listarPor(getNomeParaBusca());		
	}
		
	public String getNomeParaBusca() {
		return nomeParaBusca;
	}

	public void setNomeParaBusca(String nomeParaBusca) {
		this.nomeParaBusca = nomeParaBusca;
	}

	public boolean isModoCadastro() {
		return isModoCadastro;
	}

	public Ramo getRamo() {
		return ramo;
	}

	public List<Ramo> getRamosEncontrados() {
		return ramosEncontrados;
	}

	public String getSubtitulo() {
		return subtitulo;
	}
	
}
