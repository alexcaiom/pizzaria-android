package com.fabiano.pizzaria.utils;

import com.fabiano.pizzaria.abstratas.Classe;

public class UtilsMonetario extends Classe {

	public static String formatarPreco(Double preco) {
		return "R$ "+preco;
	}

}
