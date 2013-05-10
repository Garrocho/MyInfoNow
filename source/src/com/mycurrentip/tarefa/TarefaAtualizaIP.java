package com.mycurrentip.tarefa;

import android.os.AsyncTask;

import com.mycurrentip.MyCurrentIP;
import com.mycurrentip.util.Utils;


public class TarefaAtualizaIP extends AsyncTask<Boolean, String, String> {

	private MyCurrentIP myCurrentIP;

	public TarefaAtualizaIP(MyCurrentIP myCurrentIP) {
		this.myCurrentIP = myCurrentIP;
	}

	@Override
	protected String doInBackground(Boolean... argv) {
		return Utils.getIPAddress(argv[0]);
	}

	@Override
	protected void onPostExecute(String resposta) {
		myCurrentIP.getCampoTextoIP().setText(resposta);
	}
}