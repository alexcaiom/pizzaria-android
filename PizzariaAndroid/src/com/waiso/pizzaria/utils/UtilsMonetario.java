package com.waiso.pizzaria.utils;

import com.waiso.pizzaria.abstratas.Classe;

public class UtilsMonetario extends Classe {

	public static String formatarPreco(Double preco) {
		return "R$ "+preco;
	}

}
