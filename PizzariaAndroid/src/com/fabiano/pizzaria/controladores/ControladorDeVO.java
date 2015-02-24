package com.fabiano.pizzaria.controladores;

import com.fabiano.pizzaria.abstratas.Classe;

public abstract class ControladorDeVO<T> extends Classe{

	abstract void encriptaVO(T o);
	
}
