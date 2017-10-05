package br.com.rps.base;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.rps.util.BundleUtils;

public abstract class BaseController implements Serializable {

	public abstract void irPaginaDados();
	
	public String buscaRotulo(String chave) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		return BundleUtils.buscaRotulo(chave, context.getViewRoot().getLocale());
	}
	
	public static void redireciona(String pagina)
	  {
	    FacesContext faces = FacesContext.getCurrentInstance();
	    ExternalContext context = faces.getExternalContext();
	    try
	    {
	      context.redirect(pagina);
	    }
	    catch (IOException ex)
	    {
	      ex.printStackTrace();
	    }
	  }
}
