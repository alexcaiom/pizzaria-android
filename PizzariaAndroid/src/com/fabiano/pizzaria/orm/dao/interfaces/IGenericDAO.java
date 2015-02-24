/**
 * 
 */
package com.fabiano.pizzaria.orm.dao.interfaces;

import com.fabiano.pizzaria.excecoes.SysErr;

/**
 * 
 *
 */
public interface IGenericDAO<T> {

	public T incluir(T orm) throws SysErr;
	public void atualizar(T orm) throws SysErr;
	public void excluir(T orm) throws SysErr;

}
