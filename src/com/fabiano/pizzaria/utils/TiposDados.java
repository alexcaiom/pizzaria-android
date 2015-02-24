/**
 * 
 */
package com.fabiano.pizzaria.utils;

/**
 * 
 *
 */
public enum TiposDados {

	TEXTO("text"),
	INTEIRO("int");
	
	String tipo;
	
	TiposDados(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
}
