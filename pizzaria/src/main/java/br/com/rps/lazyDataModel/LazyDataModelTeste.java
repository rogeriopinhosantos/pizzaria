package br.com.rps.lazyDataModel;

import javax.inject.Inject;

import br.com.rps.entidade.Teste;
import br.com.rps.filtro.TesteFiltro;
import br.com.rps.lib.lazyDataModel.LazyDataModelBase;
import br.com.rps.manager.TesteManager;

public class LazyDataModelTeste extends LazyDataModelBase<Teste, TesteManager, TesteFiltro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public LazyDataModelTeste(TesteManager baseManager, TesteFiltro filtro) {
		super(baseManager, filtro);
		// TODO Auto-generated constructor stub
	}

	
}
