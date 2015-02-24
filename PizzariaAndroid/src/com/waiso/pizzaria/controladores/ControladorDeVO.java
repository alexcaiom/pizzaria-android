package com.waiso.pizzaria.controladores;

import com.waiso.pizzaria.abstratas.Classe;

public abstract class ControladorDeVO<T> extends Classe{

	abstract void encriptaVO(T o);
	
}
