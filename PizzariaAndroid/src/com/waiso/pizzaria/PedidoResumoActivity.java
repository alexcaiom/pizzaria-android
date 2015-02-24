package com.waiso.pizzaria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.enums.TipoPedido;
import com.waiso.pizzaria.utils.Constantes;
import com.waiso.pizzaria.utils.UtilsMonetario;
import com.waiso.pizzaria.utils.UtilsTelefone;
import com.waiso.pizzaria.ws.WebService;

public class PedidoResumoActivity extends ClasseActivity {
	
	private Button btnFinalizar;
	private LinearLayout listaCupons;
	private LinearLayout listaProdutos;
	private String ingredientes = "";
	private Double preco = 0.0;
	private TextView lblPrecoTotalPedido;
	
	private ProgressDialog dialogoProgresso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedido_resumo);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pedido_resumo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:
			return true;
		case R.id.acao_editar:
			onBackPressed();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void carregarTela() {
		btnFinalizar = (Button) findViewById(R.id.tela_pedido_resumo_btnFinalizar);
		listaCupons = (LinearLayout) findViewById(R.id.tela_pedido_resumo_listaCupons);
		listaProdutos = (LinearLayout) findViewById(R.id.tela_pedido_resumo_listaProdutos);
		
		Bundle parametros = getParametros();
		if (parametros != null && parametros.containsKey(Constantes.PARAMETRO_INGREDIENTES)) {
			ingredientes = parametros.getString(Constantes.PARAMETRO_INGREDIENTES);
			preco = parametros.getDouble(Constantes.PARAMETRO_PRECO);
		}
		
		lblPrecoTotalPedido = (TextView) findViewById(R.id.tela_pedido_resumo_valorTotal);
		String precoTotal = "Total: " + UtilsMonetario.formatarPreco(preco);
		lblPrecoTotalPedido.setText(precoTotal);
		
		carregarListaCupons();
		carregarListaProdutos();
		
		carregarEventos();
	}

	private void carregarListaCupons() {
		// TODO Auto-generated method stub
		
	}

	private void carregarListaProdutos() {
		RelativeLayout item = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_pedido_produto, null);
		
		TextView lblIngredientes  = (TextView) item.findViewById(R.id.pedido_item_produto_ingredientes);
		lblIngredientes.setText(ingredientes);
		
		TextView lblPreco = (TextView) item.findViewById(R.id.pedido_item_produdo_preco);
		lblPreco.setText(UtilsMonetario.formatarPreco(preco));
		
		listaProdutos.addView(item);
	}

	@Override
	public void carregarEventos() {
		btnFinalizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				AsyncTask<String, String, String> tarefa = new AsyncTask<String, String, String>() {
//					@Override
//					protected void onPreExecute() {
//						dialogoProgresso = new ProgressDialog(PedidoResumoActivity.this);
//						dialogoProgresso.setMessage("Registrando seu pedido");
//						dialogoProgresso.show();
//						super.onPreExecute();
//					}
//					@Override
//					protected String doInBackground(String... params) {
//						String resultado = finalizarPedido();
//						return resultado;
//					}
//					
//					@Override
//					protected void onPostExecute(String result) {
//						dialogoProgresso.dismiss();
						irPara(PerfilPizzariaExibicaoActivity.class);
//						super.onPostExecute(result);
//					}
//				};
				
				
//				try {
//					tarefa.execute("", "").get();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			};
		});
	}
	
	public String finalizarPedido(){
		WebServiceTask tarefa = new WebServiceTask();
		Void nada = null;
		String resultado = ""; 
		try {
			resultado = tarefa.execute(nada).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	 class WebServiceTask extends AsyncTask<Void, Void, String> {
		 @Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		 
		@Override
		protected String doInBackground(Void... dados) {
			List<NameValuePair> parametros = new ArrayList<NameValuePair>();
			
			parametros.add(new BasicNameValuePair("descricao", 	"Pizza - "+ingredientes));
			parametros.add(new BasicNameValuePair("tipo", 		TipoPedido.ENTREGAR.name()));
//			parametros.add(new BasicNameValuePair("data", 		"Pizza - "+ingredientes));
			parametros.add(new BasicNameValuePair("latitude", 	"Pizza - "+ingredientes));
			parametros.add(new BasicNameValuePair("longitude", 	"Pizza - "+ingredientes));
			parametros.add(new BasicNameValuePair("numero", 	"Pizza - "+ingredientes));
//			parametros.add(new BasicNameValuePair("complemento", "Pizza - "+ingredientes));
//			parametros.add(new BasicNameValuePair("nome", 		"Pizza - "+ingredientes));
//			parametros.add(new BasicNameValuePair("identificacao", "Pizza - "+ingredientes));
//			parametros.add(new BasicNameValuePair("celular", 	UtilsTelefone.getInstancia(PedidoResumoActivity.this).getNumeroTelefone()));
			parametros.add(new BasicNameValuePair("imei", 		UtilsTelefone.getInstancia(PedidoResumoActivity.this).getIDDispositivo()));
			
			String url = Constantes.CONEXAO_PROTOCOLO+"://"+Constantes.CONEXAO_LOCAL+":8080"+"/"+Constantes.CONEXAO_CONTEXTO + "/" + "pedido" + "/" + "cadastrar";
			WebService webService = new WebService(url, PedidoResumoActivity.this);
			String resultado = "";
			try {
				resultado = webService.doPost("", parametros);
			} catch (Throwable e) {
				if (existe(e) && existe(e.getMessage())) {
					resultado = e.getMessage();
				}
			}
			
			return resultado;
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			super.onPostExecute(result);
		}
	 }
}


