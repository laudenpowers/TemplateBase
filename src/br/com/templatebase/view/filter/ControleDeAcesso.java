package br.com.templatebase.view.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.templatebase.view.back.SessaoDoUsuarioBack;
import br.com.templatebase.view.util.UrlUtil;

@WebFilter(filterName="ControleDeAcesso")
public class ControleDeAcesso implements Serializable, Filter{

	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig fc) throws ServletException{
		
	}	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException{

		HttpServletRequest requisicao = (HttpServletRequest)request;
		
		HttpServletResponse resposta = (HttpServletResponse)response;
		
		SessaoDoUsuarioBack sessaoDoUsuario = (SessaoDoUsuarioBack)requisicao
        		.getSession().getAttribute("sessaoDoUsuario");
		
		if (!sessaoDoUsuario.isSessaoExpirada()){

			if (sessaoDoUsuario.isLoginRealizado()){
				fc.doFilter(request, response);				
			}else{
				resposta.sendRedirect(UrlUtil.PAGINA_LOGIN);
			}
			
		}else{
			sessaoDoUsuario.setSessaoExpirada(false);
			fc.doFilter(request, response);
		}

	}

	@Override
	public void destroy(){

	}

}
