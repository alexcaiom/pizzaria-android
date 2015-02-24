package com.waiso.pizzaria.escutadores;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.waiso.pizzaria.abstratas.Classe;
import com.waiso.pizzaria.abstratas.ClasseActivity;

public class EscutadorLigacaoFim extends PhoneStateListener {
	
	private ClasseActivity contexto;
	
	public EscutadorLigacaoFim(ClasseActivity contexto) {
		this.contexto = contexto;
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		boolean avisar = Classe.existe(contexto);
		if(TelephonyManager.CALL_STATE_RINGING == state && avisar) {
            contexto.avisar("RINGING, number: " + incomingNumber);
        }
        if(TelephonyManager.CALL_STATE_OFFHOOK == state && avisar) {
            //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
        	contexto.avisar("OFFHOOK");
        }
        if(TelephonyManager.CALL_STATE_IDLE == state && avisar) {
            //when this state occurs, and your flag is set, restart your app
        	contexto.avisar("IDLE");
        }
        super.onCallStateChanged(state, incomingNumber);
	}
	
}
