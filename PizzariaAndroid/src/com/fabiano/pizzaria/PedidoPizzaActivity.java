package com.fabiano.pizzaria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fabiano.pizzaria.abstratas.ClasseActivity;
import com.fabiano.pizzaria.utils.Constantes;

public class PedidoPizzaActivity extends ClasseActivity{

	private Button btnResumoPedido;
	private String[] atributos = {
			"Alho",
			"Brócolis",
			"Molho",
			"Orégano",
			"Alicci",
			"Bacon",
			"Calabresa",
			"Pimenta",
			"Carne Moida",
			"Milho",
			"Ervilha",
			"Catupiry",
			"Cheddar",
			"Frango",
			"Tomate picado"
	};
	
	private Double preco  = 5.99 ;
	private LinearLayout conteinerListaAtributos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedido_pizza);
		mostrarBotaoHome();
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pedido_pizza, menu);
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
		case R.id.acao_addToPedido:
			Bundle ingredientes = sumarizarIngredientes();
			irPara(PedidoResumoActivity.class, ingredientes);
			return true;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private Bundle sumarizarIngredientes() {
		preco = 5.99;
		StringBuilder textoIngredientes = new StringBuilder();
		for (int posicao = 0; posicao < conteinerListaAtributos.getChildCount(); posicao++) {
			
			StringBuilder ingrediente = new StringBuilder();
			LinearLayout linha = (LinearLayout) conteinerListaAtributos.getChildAt(posicao);
			RelativeLayout superior = (RelativeLayout) linha.getChildAt(0);
			TextView lblNome = (TextView) superior.getChildAt(0);
			
			String textoIngrediente = lblNome.getText().toString();
			if (!textoIngrediente.isEmpty()) {
				if (posicao > 0) {
					textoIngredientes.append(";");
				}
				ingrediente.append(textoIngrediente);
			}
			
			RelativeLayout inferior = (RelativeLayout) linha.getChildAt(1);
			LinearLayout direita = (LinearLayout) inferior.getChildAt(1);
			TextView lblQuantidade = (TextView) direita.getChildAt(1);
			
			if (!textoIngrediente.isEmpty()) {
				ingrediente.append("(").append(lblQuantidade.getText().toString()).append(")");
			}
			
			if (!textoIngrediente.isEmpty()) {
				preco += 2.5;
			}
			
			textoIngredientes.append(ingrediente.toString());
		}
		Bundle ingredientes = new Bundle();
		ingredientes.putString(Constantes.PARAMETRO_INGREDIENTES, textoIngredientes.toString());
		ingredientes.putDouble(Constantes.PARAMETRO_PRECO, preco);
		return ingredientes;
	}

	@Override
	public void carregarTela() {
		btnResumoPedido = (Button) findViewById(R.id.tela_pedido_pizza_btnResumoPedido);
		conteinerListaAtributos = (LinearLayout) findViewById(R.id.pedido_pizza_ingredientes_conteiner);
		
		adicionarItemALista("");
		
		carregarEventos();
	}

	private void adicionarItemALista(String nome) {
		LinearLayout linha = (LinearLayout) getLayoutInflater().inflate(R.layout.item_ingrediente_pizza, null);
		RelativeLayout primeiraLinha  = (RelativeLayout) linha.getChildAt(0);
		TextView lblNome = (TextView) primeiraLinha.getChildAt(0);
		lblNome.setText(nome);
		
		TextView lblTapToOperacao = (TextView) primeiraLinha.getChildAt(1);
		lblTapToOperacao.setText(getString(R.string.label_tap_to_remove));
		retrairItem(linha);
		setEscutadorAdicionarEsconder(lblTapToOperacao);
		linha.setBackgroundResource(R.drawable.item_pizza_fundo_branco);
		
		int indice = conteinerListaAtributos.getChildCount();
		conteinerListaAtributos.addView(linha, indice);
	}

	@Override
	public void carregarEventos() {
		btnResumoPedido.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(PedidoResumoActivity.class);
			}
		});
		
	}
	
	private void setEscutadorAdicionarEsconder(View v){
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView view  = (TextView) v;
				if (view.getText().toString().equals(getString(R.string.label_tap_to_add))) {
					view.setText(getString(R.string.label_tap_to_remove));
					RelativeLayout superior = (RelativeLayout) view.getParent();
					LinearLayout linha = (LinearLayout) superior.getParent();
					linha.setBackgroundResource(R.drawable.item_pizza_fundo);
					novaLinha(linha);
				} else if(view.getText().toString().equals(getString(R.string.label_tap_to_remove))){
					view.setText(getString(R.string.label_tap_to_add));
					removerEsteItemDaLista(v);
				}
			}
		});
	}

	protected void novaLinha(LinearLayout linha) {
		String primeiraOpcao = escolheAPrimeiraOpcao();
		if (!primeiraOpcao.isEmpty()) {
			RelativeLayout superior = (RelativeLayout) linha.getChildAt(0);
			TextView lblNome = (TextView) superior.getChildAt(0);
			lblNome.setText(primeiraOpcao);
		}
		RelativeLayout inferior = (RelativeLayout) linha.getChildAt(1);
		LinearLayout esquerda =  (LinearLayout) inferior.getChildAt(0);
		esquerda.setVisibility(View.VISIBLE);
		LinearLayout direita =  (LinearLayout) inferior.getChildAt(1);
		direita.setVisibility(View.VISIBLE);
		apagaAQuantidade();
		
		boolean podeAdicionarMaisItens = atributos.length > conteinerListaAtributos.getChildCount();
		if (podeAdicionarMaisItens) {
			adicionarItemALista("");
		}
	}

	protected void removerEsteItemDaLista(View v) {
		RelativeLayout layoutPai = (RelativeLayout) v.getParent();
		LinearLayout linha = (LinearLayout) layoutPai.getParent();
		LinearLayout conteiner = (LinearLayout) linha.getParent();
		if (conteiner.getChildCount() > 1) {
			conteiner.removeView(linha);
		} else {
			retrairItem(linha);
		}
	}

	private void retrairItem(LinearLayout linha) {
		RelativeLayout superior = (RelativeLayout) linha.getChildAt(0);
		TextView lblNome = (TextView) superior.getChildAt(0);
		lblNome.setText("");
		TextView tap =  (TextView) superior.getChildAt(1);
		tap.setText(getString(R.string.label_tap_to_add));
		RelativeLayout inferior = (RelativeLayout) linha.getChildAt(1);
		LinearLayout esquerda =  (LinearLayout) inferior.getChildAt(0);
		esquerda.setVisibility(View.INVISIBLE);
		LinearLayout direita =  (LinearLayout) inferior.getChildAt(1);
		direita.setVisibility(View.INVISIBLE);
		apagaAQuantidade();
	}

	private void apagaAQuantidade() {
		// TODO Auto-generated method stub
		
	}

	private String escolheAPrimeiraOpcao() {
		List<String> opcoesDisponiveis = Arrays.asList(atributos);
		List<String> opcoesDisponiveisLista = new ArrayList<String>(opcoesDisponiveis);
		for (String opcao : atributos) {
			boolean jaExcluiu = false;
			for (int posicao = 0; posicao < conteinerListaAtributos.getChildCount(); posicao++) {
				LinearLayout linha = (LinearLayout) conteinerListaAtributos.getChildAt(posicao);
				RelativeLayout superior = (RelativeLayout) linha.getChildAt(0);
				TextView lblNome = (TextView) superior.getChildAt(0);
				boolean opcaoJaExisteNaLista = lblNome.getText().toString().equals(opcao);
				if (opcaoJaExisteNaLista) {
					opcoesDisponiveisLista.remove(lblNome.getText().toString());
					jaExcluiu = true;
					break;
				}
				if (jaExcluiu) {
					break;
				}
			}
			if (jaExcluiu) {
				continue;
			}
		}
		
		return (!opcoesDisponiveisLista.isEmpty()) ? opcoesDisponiveisLista.get(0) : "";
	}
}

