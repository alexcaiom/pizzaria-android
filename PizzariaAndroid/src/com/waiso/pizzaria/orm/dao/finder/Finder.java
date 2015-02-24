package com.waiso.pizzaria.orm.dao.finder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waiso.pizzaria.excecoes.SysErr;
import com.waiso.pizzaria.orm.dao.DAO;

public abstract class Finder<T> extends DAO<T> {

	protected String getNomeEntidade(){
		return CLASSE_NOME.substring(CLASSE_NOME.lastIndexOf("Finder"));
	}
	
	abstract void preencheVO(Cursor c, T o) throws Exception;
	
	protected SQLiteDatabase getBD() throws SysErr{
		return getBD(TIPO_BD_LEITURA);
	}
	
}
