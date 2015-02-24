package com.fabiano.pizzaria.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.fabiano.pizzaria.abstratas.Classe;
import com.fabiano.pizzaria.abstratas.ClasseActivity;
import com.fabiano.pizzaria.escutadores.EscutadorLigacaoFim;

public class UtilsTelefone extends Classe {

	private ClasseActivity contexto;
	
	private UtilsTelefone() {}
	
	public void realizarLigacaoTelefonica(String numero){
		String url = "tel:"+numero;
		Intent intencao = new Intent(Intent.ACTION_CALL, Uri.parse(url));
		contexto.startActivity(intencao);
		
		EscutadorLigacaoFim escutador = new EscutadorLigacaoFim(contexto);
		TelephonyManager gerenciador = (TelephonyManager) contexto.getSystemService(Context.TELEPHONY_SERVICE);
		gerenciador.listen(escutador, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	public String getIDDispositivo(){
		return Secure.getString(contexto.getContentResolver(), Secure.ANDROID_ID);
	}

	public static UtilsTelefone getInstancia(ClasseActivity contexto) {
		UtilsTelefone instancia = new UtilsTelefone();
		instancia.contexto = contexto;
		return instancia;
	}

	/*public String getNumeroTelefone() {
		return TelephonyManager.;
	}*/
	
}
