package com.waiso.pizzaria;

import android.os.Bundle;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.enums.EnumGraficoTipo;
import com.waiso.pizzaria.gui.GridItemVO;
import com.waiso.pizzaria.gui.adaptadores.AdaptadorMenu;
import com.waiso.pizzaria.utils.Constantes;

public class MenuActivity extends ClasseActivity {

	private AdaptadorMenu adaptador; 
	GridItemVO[] itensPropriedades = 
		{new GridItemVO(R.string.label_pizza,		R.drawable.btn_menu_pizza		, getAcaoClique(PedidoPizzaActivity.class)),
		new GridItemVO(R.string.label_sanduiche,	R.drawable.btn_menu_sanduiche	, getAcaoClique(BackupSettingsActivity.class)),
		new GridItemVO(R.string.label_massas,		R.drawable.btn_menu_massas		, getAcaoCliqueComParametros(GraficosActivity.class)),
		new GridItemVO(R.string.label_drinks,		R.drawable.btn_menu_drinks		, getAcaoClique(BackupSettingsActivity.class)),
		new GridItemVO(R.string.label_sides,		R.drawable.btn_menu_sides		, getAcaoClique(FinancasActivity.class)),
		new GridItemVO(R.string.label_sobremesas,	R.drawable.btn_menu_sobremesas	, getAcaoClique(BackupSettingsActivity.class)),
		};
	
	private Button btnMontarPizza;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Integer id = item.getItemId();
		switch (id) {
		case R.id.action_logout:
			deslogar();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void carregarTela() {
		adaptador = new AdaptadorMenu(this, itensPropriedades);
		GridView g = (GridView) findViewById(R.id.gridMenu);
		g.setAdapter(adaptador);
		registerForContextMenu(g);
		
		btnMontarPizza = (Button) findViewById(R.id.menu_btnMontarPizza);
		
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		btnMontarPizza.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(PedidoPizzaActivity.class);
			}
		});
	}
	
	private OnClickListener getAcaoClique(final Class destino) {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(destino);
			}
		};
	}
	
	private OnClickListener getAcaoCliqueComParametros(final Class destino) {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle parametros = new Bundle();
				parametros.putString(Constantes.PARAMETRO_TIPO_GRAFICO, EnumGraficoTipo.GRAFICO_DE_BARRAS_VERTICAL.getGoogleCode());
				irPara(destino, parametros);
			}
		};
	}


}
