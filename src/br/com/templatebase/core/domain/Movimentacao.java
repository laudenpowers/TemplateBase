package br.com.templatebase.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Preconditions;

public class Movimentacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object guincho;
	
	private List<String> acionamentos;	

	public Movimentacao(Object guincho, List<String> acionamentos) {
		
		Preconditions.checkNotNull(guincho, "Guincho obrigatório");
		
		Preconditions.checkNotNull(acionamentos, "Os acionamentos não podem ser nulos");
		
		
		Preconditions.checkArgument(acionamentos.size() > 0, "É necessário ao menos um acionamento na movimentação");
				
		
		this.acionamentos = acionamentos;

	}

	public List<String> getAcionamentos() {
		return Collections.unmodifiableList(acionamentos);
	}	
	
	public static void main(String[] args) {
		Movimentacao mov = new Movimentacao(new Object(), new ArrayList<String>());
		mov.getAcionamentos().add("A1");
	}

}
