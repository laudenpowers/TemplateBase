package br.com.templatebase.view.filter;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.com.templatebase.view.util.UrlUtil;

public class ViewExpiredHandler extends ExceptionHandlerWrapper {
	 
    private ExceptionHandler wrapped;
 
	public ViewExpiredHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException{

    	Iterator<ExceptionQueuedEvent> eventos = getUnhandledExceptionQueuedEvents().iterator();

    	while (eventos.hasNext()){

    		ExceptionQueuedEvent evento = eventos.next();

    		ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)evento.getSource();

    		Throwable excecao = context.getException();

    		if (excecao instanceof ViewExpiredException){

    			try{

    				FacesContext.getCurrentInstance().getExternalContext()
    						.redirect(UrlUtil.PAGINA_SESSAO_EXPIRADA);

				}catch (IOException e){
					e.printStackTrace();
				}finally{
					eventos.remove();
				}

    		}

    	}

        getWrapped().handle();

    }

}