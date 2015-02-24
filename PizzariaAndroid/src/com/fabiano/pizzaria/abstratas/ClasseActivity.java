/**
 * 
 */
package com.fabiano.pizzaria.abstratas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fabiano.pizzaria.CadastroUsuarioActivity;
import com.fabiano.pizzaria.LoginActivity;
import com.fabiano.pizzaria.SplashActivity;
import com.fabiano.pizzaria.controladores.ControladorDeUsuario;
import com.fabiano.pizzaria.excecoes.Erro;
import com.fabiano.pizzaria.excecoes.ErroNegocio;
import com.fabiano.pizzaria.excecoes.SysErr;
import com.fabiano.pizzaria.interfaces.ClasseActivityInterface;
import com.fabiano.pizzaria.orm.modelos.Usuario;
import com.fabiano.pizzaria.utils.Constantes;
import com.fabiano.pizzaria.utils.Dialogos;
import com.fabiano.pizzaria.utils.Sessao;

/**
 * 
 *
 */
public abstract class ClasseActivity extends Activity implements ClasseActivityInterface{

	public final String CLASSE_NOME = getClass().getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		verificaSessao();
	}
	
	protected void mostrarBotaoHome() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	protected Bundle getParametros(){
		return getIntent().getExtras();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		verificaSessao();
	}

	public void irPara(Class tela, Bundle parametros){
		Intent intencao = new Intent(this, tela);
		if(parametros != null && !parametros.isEmpty()){
			intencao.putExtras(parametros);
		}
		startActivity(intencao);
	}
	
	public void irPara(Class tela){
		irPara(tela, null);
	}
	
	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso, Integer duracao) {
		if(duracao == null){
			Toast.makeText(getApplicationContext(), aviso, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), aviso, duracao).show();
		}
	}

	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso) {
		avisar(aviso, null);
	}
	
	private void verificaSessao() {
		if (!Sessao.usuarioLogado()) {
			if (!this.getClass().getSimpleName().equalsIgnoreCase(LoginActivity.class.getSimpleName()) && 
					!this.getClass().getSimpleName().equalsIgnoreCase(CadastroUsuarioActivity.class.getSimpleName()) &&
					!this.getClass().getSimpleName().equalsIgnoreCase(SplashActivity.class.getSimpleName())) {
				irPara(LoginActivity.class);
			}
		}
	}
	
	public void exibirMensagemDeProcessamento(){
		
	}
	
	protected void log(Object textoParaLog) {
		Log.i(CLASSE_NOME, textoParaLog.toString());
	}
	
	public void deslogar(){
		final Usuario usuarioLogado = (Usuario) Sessao.getParametro(Constantes.USUARIO);
		Dialogos.Progresso.exibirDialogoProgresso(this);
		Runnable thread = new Runnable() {
			public void run() {
				try {
					//Este trecho vai demorar
					ControladorDeUsuario.getInstancia(ClasseActivity.this).logout(usuarioLogado.getLogin());
					Dialogos.Progresso.fecharDialogoProgresso();
				} catch (Erro e) {
					if (e instanceof ErroNegocio) {
						Dialogos.Progresso.fecharDialogoProgresso();
					} else if (e instanceof SysErr) {
						Dialogos.Alerta.exibirMensagemErro(e, ClasseActivity.this, null);
						Dialogos.Alerta.fecharDialogo();
					}
				} catch (Exception e) {
					Dialogos.Progresso.fecharDialogoProgresso();
					Dialogos.Alerta.exibirMensagemErro(e, ClasseActivity.this, null);
				}
			}
		};
		
		new Thread(thread).start();
		Sessao.deslogar();
		verificaSessao();
	}
	
	/**
	 * Metodo que verifica se o objeto eh nulo
	 * @param o
	 * @return
	 */
	public static boolean naoExiste(Object o){
		boolean naoExiste = Classe.naoExiste(o);
		return naoExiste;
	}
	
	/**
	 * Metodo que verifica se o objeto nao eh nulo
	 * @param o
	 * @return
	 */
	public static boolean existe(Object o){
		boolean existe = !naoExiste(o);
		return existe;
	}
	
	public void ocultarBarraDeAcoes(){
		getActionBar().hide();
	}
	
	public void exibirMensagemExcecao(Throwable e){
		Dialogos.Alerta.exibirMensagemErro(e, ClasseActivity.this, null);
	}
}
