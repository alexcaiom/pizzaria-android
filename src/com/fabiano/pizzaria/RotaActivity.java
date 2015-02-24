package com.fabiano.pizzaria;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fabiano.pizzaria.abstratas.ClasseActivity;
import com.fabiano.pizzaria.utils.mapa.TracadorDeRota;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class RotaActivity extends ClasseActivity {

	private GoogleMap mapa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rota);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rota, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	double minhaLatitude = -23.643576;
	double minhaLongitude = -46.7117034;
	LatLng minhaPosicao = new LatLng(minhaLatitude, minhaLongitude);

	double pizzariaLatitude = -23.6475213;
	double pizzariaLongitude = -46.7068607;
	LatLng pizzariaPosicao = new LatLng(pizzariaLatitude, pizzariaLongitude);
	@Override
	public void carregarTela() {
		TracadorDeRota formadorDeRota = new TracadorDeRota();
		mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.rota_mapa)).getMap();
		formadorDeRota.de(minhaPosicao).ate(pizzariaPosicao).aplicarRotaAoMapa(mapa);
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub

	}
}
