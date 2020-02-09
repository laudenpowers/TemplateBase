package br.com.templatebase.core.service;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.templatebase.core.dao.IDaoRamo;
import br.com.templatebase.core.dao.spring.FactorySpringDao;
import br.com.templatebase.core.domain.Ramo;

public class RamoService {

	private IDaoRamo daoRamo;
	
	public RamoService() {
		this.daoRamo=FactorySpringDao.getInstance().getDaoRamo();
	}
	
	public void salvar(Ramo ramo) {
		Preconditions.checkNotNull(ramo, "Ramo Não Pode Ser Nulo");		
		Preconditions.checkArgument(!Strings.isNullOrEmpty(ramo.getNome()), "Nome Não Informado");
		
		if (ramo.getCodigo()>0) {
			daoRamo.alterar(ramo);			
			
		}else {
			daoRamo.inserir(ramo);			
		}
	}
	
	public List<Ramo> listarPor(String paramRamo){ 
		return daoRamo.listarPor(paramRamo);
	}
	
	public void remover(Ramo ramo) {
		Preconditions.checkNotNull(ramo, "Ramo Não Pode Ser Nulo");
		Preconditions.checkArgument(ramo.getCodigo() > 0, "O código do ramo deve ser maior que zero");
		daoRamo.removerRamoPor(ramo.getCodigo());
	}
	
}
