package com.waiso.pizzaria;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.utils.ServicoLocalizacao;
import com.waiso.pizzaria.utils.UtilsTelefone;
import com.waiso.pizzaria.utils.mapa.TracadorDeRota;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PerfilPizzariaExibicaoActivity extends ClasseActivity {

	private GoogleMap mapa;
	private Button btnPegarPizzaNaPizzaria;
	private Button btnVerPedido;
	private Button btnLigarParaAPizzaria;
	private String telefonePizzaria = "954770100";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_pizzaria_exibicao);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perfil_pizzaria_exibicao, menu);
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
	LatLng minhaPosicao = null;
	LatLng posicaoPizzaria = null;
	@Override
	public void carregarTela() {
		mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.tela_perfil_pizzaria_exibicao_mapa)).getMap();
		mapa.setMyLocationEnabled(true);
		Location myLocation = existe(mapa.getMyLocation()) ? mapa.getMyLocation() : ServicoLocalizacao.getInstancia(this).getMinhaLocalizacaoAtual();
		if (existe(myLocation)) {
			double minhaLatitude = myLocation.getLatitude();
			double minhaLongitude = myLocation.getLongitude();
			 minhaPosicao = new LatLng(minhaLatitude, minhaLongitude);
			adicionarMarcadoresAoMapa(minhaPosicao, "Eu", "Eu_Snippet", null);
		}
		
		double pizzariaLatitude = -23.6475213;
		double pizzariaLongitude = -46.7068607;
		BitmapDescriptor icone = BitmapDescriptorFactory.fromResource(R.drawable.dominos_perfil_logo);
		posicaoPizzaria = new LatLng(pizzariaLatitude, pizzariaLongitude);
		adicionarMarcadoresAoMapa(posicaoPizzaria, "Pizzaria", "Pizzaria", icone);
		
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicaoPizzaria, 40));
		mapa.animateCamera(CameraUpdateFactory.zoomTo(80), 2000, null);
		
		btnPegarPizzaNaPizzaria = (Button) findViewById(R.id.tela_perfil_pizzaria_exibicao_btnPegarPizzaNaPizzaria);
		btnVerPedido = (Button) findViewById(R.id.tela_perfil_pizzaria_exibicao_btnVerStatusPedido);
		btnLigarParaAPizzaria = (Button) findViewById(R.id.tela_perfil_pizzaria_exibicao_btnLigarParaAPizzaria);
		
		carregarEventos();
	}

	private void adicionarMarcadoresAoMapa(LatLng posicao, String descricao, String snippet, BitmapDescriptor icone) {
		MarkerOptions posicaoMarcador = new MarkerOptions().position(posicao);
		posicaoMarcador.title(descricao);
		if (existe(snippet)) {
			posicaoMarcador.snippet(snippet);
		}
		if (existe(icone)) {
			posicaoMarcador.icon(icone);
		}
		mapa.addMarker(posicaoMarcador);
	}

	@Override
	public void carregarEventos() {
		btnPegarPizzaNaPizzaria.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Location myLocation = existe(mapa.getMyLocation()) ? mapa.getMyLocation() : ServicoLocalizacao.getInstancia(PerfilPizzariaExibicaoActivity.this).getMinhaLocalizacaoAtual();
				if (existe(myLocation)) {
					double minhaLatitude = myLocation.getLatitude();
					double minhaLongitude = myLocation.getLongitude();
					 minhaPosicao = new LatLng(minhaLatitude, minhaLongitude);
				}
				
				TracadorDeRota tracador = new TracadorDeRota().de(minhaPosicao).ate(posicaoPizzaria);
				tracador.aplicarRotaAoMapa(mapa);
			}
		});
		
		btnLigarParaAPizzaria.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				UtilsTelefone.getInstancia(PerfilPizzariaExibicaoActivity.this).realizarLigacaoTelefonica(telefonePizzaria);
			}
		});
		
		btnVerPedido.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(PedidoStatusActivity.class);
			}
		});
	}
}
