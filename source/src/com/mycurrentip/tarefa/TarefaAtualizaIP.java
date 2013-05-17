package com.mycurrentip.tarefa;

import android.os.AsyncTask;

import com.mycurrentip.MyCurrentIP;
import com.mycurrentip.classes.Data;
import com.mycurrentip.classes.Historico;
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
		
		Historico historico = new Historico();
		historico.setIp(resposta);
		historico.setData_hora(Data.getDataHoraAtual());
		myCurrentIP.getRepoHistorico().insert(historico);
	}
}