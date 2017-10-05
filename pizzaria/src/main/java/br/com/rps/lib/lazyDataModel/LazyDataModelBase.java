package br.com.rps.lib.lazyDataModel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.rps.lib.entity.IBaseEntity;
import br.com.rps.lib.filtro.FiltroBase;
import br.com.rps.manager.BaseManager;

public abstract class LazyDataModelBase<T extends IBaseEntity, M extends BaseManager<T>, F extends FiltroBase<T>>
		extends LazyDataModel<T> {

	private F filtro;

	private M baseManager;
	
	private List<T> lista;

	public LazyDataModelBase(M baseManager, F filtro) {
		this.filtro = filtro;
		this.baseManager = baseManager;
	}

	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(first, pageSize, multiSortMeta, filters);
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		filtro.setPrimeiroRegistro(first);
		filtro.setQuantidadeRegistros(pageSize);
		filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtro.setPropriedadeOrdenacao(sortField);

		setRowCount(baseManager.quantidadeFiltrados(filtro));

		lista = baseManager.filtrados(filtro);
		return lista;
	}

	@Override
	public int getRowCount() {
		return super.getRowCount();
	}

	public Object getRowKey(T t) {
		return t.getId();
	}

	public T getRowData(String rowKey) {
		if (this.lista == null) {
			return null;
		}
		for (T entidade : this.lista) {
			if (entidade.getId().toString().equals(rowKey)) {
				return entidade;
			}
		}
		return null;
	}
}
