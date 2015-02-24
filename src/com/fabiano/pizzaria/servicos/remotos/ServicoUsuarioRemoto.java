/**
 * 
 */
package com.fabiano.pizzaria.servicos.remotos;

import android.content.Context;

import com.fabiano.pizzaria.abstratas.Classe;
import com.fabiano.pizzaria.excecoes.Erro;
import com.fabiano.pizzaria.excecoes.ErroNegocio;
import com.fabiano.pizzaria.excecoes.SysErr;
import com.fabiano.pizzaria.orm.modelos.Usuario;
import com.fabiano.pizzaria.utils.Constantes;
import com.fabiano.pizzaria.utils.Sessao;
import com.fabiano.pizzaria.ws.implementacoes.UsuarioWS;

/**
 * Dell
 *
 */
public class ServicoUsuarioRemoto extends Classe {

	private static ServicoUsuarioRemoto instancia;
	private Context contexto;
	
	public void logar(String login, String senha) throws Erro{
		Usuario usuario = null;
		try {
//			usuario = new Usuario(login, senha, EnumUsuarioAutenticado.SUCESSO.getCodigo());
			usuario = UsuarioWS.getInstancia(contexto).login(login, senha);
		} catch (Erro e){
			throw e;
		}
		Sessao.addParametro(Constantes.USUARIO, usuario);
	}

	public void deslogar(String login) throws Erro {
		UsuarioWS.getInstancia(contexto).logout(login);
	}

	public void cadastrar(Usuario usuario) throws Erro {
		UsuarioWS.getInstancia(contexto).cadastrar(usuario);
	}
	
	public void alterar(Usuario usuario) throws ErroNegocio, SysErr {
		UsuarioWS.getInstancia(contexto).alterar(usuario);
	}

	public void excluir(Usuario usuario) throws ErroNegocio, SysErr {
		UsuarioWS.getInstancia(contexto).excluir(usuario);
	}

	public static ServicoUsuarioRemoto getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoUsuarioRemoto();
			instancia.contexto = contexto;
		}
		return instancia;
	}
}
