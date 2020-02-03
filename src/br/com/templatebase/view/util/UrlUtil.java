package br.com.templatebase.view.util;

import java.io.Serializable;

public class UrlUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String PAGINA_LOGIN = "../login.xhtml";

	public static final String PAGINA_SESSAO_EXPIRADA = "./sessaoExpirada.xhtml";

    public String extractPaginaDeDestinoDa(String url){

		String[] partesUrlRequisitada = url.split("\\?");
		partesUrlRequisitada = partesUrlRequisitada[0].split("/");
		String recursoRequisitado = partesUrlRequisitada[partesUrlRequisitada.length - 1];
		recursoRequisitado = recursoRequisitado.split(";")[0];

    	return recursoRequisitado;

    }

}
