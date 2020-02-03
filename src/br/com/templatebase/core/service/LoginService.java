package br.com.templatebase.core.service;

import java.io.Serializable;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.templatebase.core.domain.Pessoa;

public class LoginService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String LOGIN_VALIDO = "template";
	
	private static final String SENHA_VALIDA = "base";
	
	public Pessoa realizarLoginPor(String login, String senha) {
		
		Preconditions.checkArgument(!Strings.isNullOrEmpty(login), 
				"O login é obrigatório");
		
		Preconditions.checkArgument(!Strings.isNullOrEmpty(senha), 
				"A senha é obrigatória");
		
		if (!LOGIN_VALIDO.equals(login) || !SENHA_VALIDA.equals(senha)) {
			throw new IllegalArgumentException("O login ou a senha são inválidos");
		}
		
		return new Pessoa(1, "Pessoa de Teste", "pessoa.teste@gmail.com");
		
	}

}
