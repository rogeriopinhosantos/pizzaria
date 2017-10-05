package br.com.rps.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rps.base.BaseController;
import br.com.rps.entidade.Teste;
import br.com.rps.lazyDataModel.LazyDataModelTeste;

@Named(value="testeController")
@ViewScoped
public class TesteController extends BaseController{
	
	private static final long serialVersionUID = 1L;
	
	private List<Teste> listaTeste;
	
	
	@Inject
	private LazyDataModelTeste model;
	
	@PostConstruct
	public void init(){
 
		//RETORNAR AS PESSOAS CADASTRADAS
		//this.listaTeste = managerBase.buscaTodos();
	}

	public List<Teste> getListaTeste() {
		return listaTeste;
	}

	public void setListaTeste(List<Teste> listaTeste) {
		this.listaTeste = listaTeste;
	}

	public LazyDataModelTeste getModel() {
		return model;
	}

	@Override
	public void irPaginaDados() {
		redireciona("cadastroTeste.xhtml");
		
	}

}
