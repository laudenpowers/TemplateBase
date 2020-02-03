package br.com.templatebase.view.filter;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebFilter(filterName="ControleDeAcessoPorTimeout")
public class ControleDeAcessoPorTimeout implements Serializable, Filter{

	private static final long serialVersionUID = 1L;

	private UrlUtil urlUtil;
	
	@Override
    public void init(FilterConfig filter) throws ServletException{
		this.urlUtil = new UrlUtil();
	}
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, 
    		FilterChain filterChain) throws IOException, ServletException{

    	if (request instanceof HttpServletRequest && response instanceof HttpServletResponse){

            HttpServletRequest requisicao = (HttpServletRequest)request;

            HttpServletResponse resposta = (HttpServletResponse)response;
            
            SessaoDoUsuarioBack sessaoDoUsuario = (SessaoDoUsuarioBack)requisicao
            		.getSession().getAttribute("sessaoDoUsuario");
            
            if (isAcessoAoRecursoValido(requisicao) && isSessaoInvalida(requisicao)){

            	requisicao.getSession();//Renova a sessão para acesso posterior
            	
            	sessaoDoUsuario.setSessaoExpirada(true);
            	
        		String urlDeRetorno = request.getScheme() + "://" 
        				+ request.getServerName() + ":" + request.getLocalPort() 
        				+ "/LancheNet/sessaoExpirada.xhtml";

            	if (isRequisicaoAjax(requisicao)){

        			resposta.setHeader("Cache-Control", "no-cache");
        			resposta.setCharacterEncoding("UTF-8");
        			resposta.setContentType("text/xml");

        			StringBuilder header = new StringBuilder();

        			header.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        			header.append("<partial-response>");

        			header.append("  <redirect url=\"").append(urlDeRetorno).append("\"></redirect>");

        			header.append("</partial-response>");

        			PrintWriter pw = response.getWriter();
        			pw.println(header.toString());
        			pw.flush();

            	}else{
            		resposta.sendRedirect(urlDeRetorno);
            	}

            }else{
            	if (sessaoDoUsuario != null) {
            		sessaoDoUsuario.setSessaoExpirada(false);
            	}
            	filterChain.doFilter(request, response);
            }

    	}else{
        	filterChain.doFilter(request, response);
        }

	}
	
	private boolean isRequisicaoAjax(HttpServletRequest requisicao){
        String facesRequest = requisicao.getHeader("Faces-Request");
        return facesRequest != null && facesRequest.equals("partial/ajax");
    }

    private boolean isAcessoAoRecursoValido(HttpServletRequest requisicao){
        String requestPath = requisicao.getRequestURI();
        String paginaDeDestino = urlUtil.extractPaginaDeDestinoDa(requisicao.getRequestURI());
        return !UrlUtil.PAGINA_SESSAO_EXPIRADA.contains(paginaDeDestino) 
        		&& !requestPath.contains("javax.faces.resource");
    }

    private boolean isSessaoInvalida(HttpServletRequest requisicao){

    	if (requisicao.getRequestedSessionId() != null){

    		String idDaSessaoDaUrl = null;

    		String[] partesDaUrl = requisicao.getRequestURI().split(";jsessionid=");

    		if (partesDaUrl.length > 1){    		
    			idDaSessaoDaUrl = partesDaUrl[1];
    		}

    		if (idDaSessaoDaUrl != null){

    			if (idDaSessaoDaUrl.equals(requisicao.getRequestedSessionId())){
    				return !requisicao.isRequestedSessionIdValid();
    			}

    			return true;

    		}    		

    		return !requisicao.isRequestedSessionIdValid();

    	}

    	return false;

    }    
    
	@Override
    public void destroy(){
    	
    }

}
