package com.mycurrentip.tarefa;

import android.os.AsyncTask;

import com.mycurrentip.MyCurrentIP;
import com.mycurrentip.util.Utils;


public class TarefaAtualizaIP extends AsyncTask<String, String, String> {

	private MyCurrentIP myCurrentIP;

	public TarefaAtualizaIP(MyCurrentIP myCurrentIP) {
		this.myCurrentIP = myCurrentIP;
	}

	@Override
	protected String doInBackground(String... argumentos) {
		return Utils.getIPAddress(true); // IPv4
	}

	@Override
	protected void onPostExecute(String resposta) {
		myCurrentIP.getCampoTextoIP().setText(resposta);
	}
}