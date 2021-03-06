/**
 * 
 */
package com.waiso.pizzaria.orm.bo;

import android.content.Context;
import android.util.Log;

import com.waiso.pizzaria.abstratas.Classe;
import com.waiso.pizzaria.excecoes.Erro;
import com.waiso.pizzaria.excecoes.ErroNegocio;
import com.waiso.pizzaria.excecoes.SysErr;
import com.waiso.pizzaria.interfaces.IUsuarioBO;
import com.waiso.pizzaria.orm.modelos.Usuario;
import com.waiso.pizzaria.servicos.remotos.ServicoUsuarioRemoto;

/**
 * 
 *
 */
public class BOUsuario extends Classe implements IUsuarioBO {

	private static BOUsuario instancia;
	private final Context contexto;
	
	public BOUsuario(Context contexto){
		this.contexto = contexto;
	}
	
	/**
	 * Metodo de Autenticacao de Usuario
	 * @param login
	 * @param senha
	 * @return
	 * @throws SysErr 
	 * @throws ErroNegocio 
	 */
	public void autentica(String login, String senha) throws Erro {
		log("Autenticando "+getNomeEntidade());
		ServicoUsuarioRemoto.getInstancia(contexto).logar(login, senha);
	}

	public static BOUsuario getInstancia(Context contexto) {
		if(instancia == null){
			instancia = new BOUsuario(contexto);
		}
		return instancia;
	}

	@Override
	public void inserir(Usuario usuario) throws Erro {
		Log.i(CLASSE_NOME, "Inserindo "+getNomeEntidade());
		ServicoUsuarioRemoto.getInstancia(contexto).cadastrar(usuario);
	}

	@Override
	public void alterar(Usuario usuario) throws SysErr {
		log("Alterando "+getNomeEntidade());
	}

	@Override
	public void excluir(Usuario usuario) throws SysErr {
		log("Excluindo "+getNomeEntidade());
	}
	
	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
}
