package com.fabiano.pizzaria.utils;

import com.fabiano.pizzaria.abstratas.Classe;
import com.fabiano.pizzaria.abstratas.ClasseActivity;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.provider.Settings;

public class ServicoLocalizacao extends Classe {
	
	private ClasseActivity contexto;
	
	private static ServicoLocalizacao instancia = new ServicoLocalizacao();

	public Location getMinhaLocalizacaoAtual() {
		LocationManager servico = (LocationManager) contexto.getSystemService(contexto.LOCATION_SERVICE);
		Criteria criterios = new Criteria();
		String provedor = servico.getBestProvider(criterios, false);
		
		Location minhaLocalizacao = servico.getLastKnownLocation(provedor);
		boolean localizacaoValida = instancia.isLocalizacaoValida(minhaLocalizacao);
		if (!localizacaoValida) {
			instancia.turnGPSOn();
			minhaLocalizacao = servico.getLastKnownLocation(provedor);
			instancia.turnGPSOff();
		} 
		
		return minhaLocalizacao;
	}
	
	private boolean isLocalizacaoValida (Location localizacao){
		return existe(localizacao);
	}

	private void turnGPSOn(){
	    String provider = Settings.Secure.getString(contexto.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        contexto.sendBroadcast(poke);
	    }
	    
	}

	private void turnGPSOff(){
	    String provider = Settings.Secure.getString(contexto.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(provider.contains("gps")){ //if gps is enabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        contexto.sendBroadcast(poke);
	    }
	}
	
	public static ServicoLocalizacao getInstancia(ClasseActivity contexto){
		instancia = new ServicoLocalizacao();
		instancia.contexto = contexto;
		return instancia;
	}
	
}
