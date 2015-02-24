/**
 * 
 */
package com.waiso.pizzaria.orm.dao.interfaces;

import com.waiso.pizzaria.excecoes.SysErr;

/**
 * 
 *
 */
public interface IGenericDAO<T> {

	public T incluir(T orm) throws SysErr;
	public void atualizar(T orm) throws SysErr;
	public void excluir(T orm) throws SysErr;

}
