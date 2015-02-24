/**
 * 
 */
package com.waiso.pizzaria.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 */
public class Sessao {

	private static final Map<String, Object> parametros = new HashMap<String, Object>();
	
	public static final Object getParametro(String chave){
		return parametros.get(chave);
	}
	
	public static final void addParametro(String chave, Object valor){
		parametros.put(chave, valor);
	}
	
	public static final boolean temParametro(String chave){
		return parametros.containsKey(chave);
	}
	
	public static final boolean usuarioLogado(){
		return temParametro(Constantes.USUARIO) && getParametro(Constantes.USUARIO) != null;
	}
	
	public static final void deslogar(){
		if (usuarioLogado()) {
			parametros.remove(Constantes.USUARIO);
		}
	}
	
}
