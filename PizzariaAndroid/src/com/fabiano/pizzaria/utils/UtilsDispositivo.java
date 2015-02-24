package com.fabiano.pizzaria.utils;

import android.provider.Settings.System;

import com.fabiano.pizzaria.abstratas.Classe;
import com.fabiano.pizzaria.abstratas.ClasseActivity;

public class UtilsDispositivo extends Classe {

	private ClasseActivity contexto;
	
	private UtilsDispositivo(){}
	
	public String getIDAndroid(){
		return System.getString(contexto.getContentResolver(), System.ANDROID_ID);
	}
	
	public static UtilsDispositivo getInstancia(ClasseActivity c){
		UtilsDispositivo instancia = new UtilsDispositivo();
		instancia.contexto = c;
		return instancia;
	}
	
}
