package com.mycurrentip.tarefa;

import java.util.HashMap;

import android.os.AsyncTask;

import com.mycurrentip.MyCurrentIP;
import com.mycurrentip.classes.Data;
import com.mycurrentip.classes.Historico;
import com.mycurrentip.util.Utils;


public class TarefaAtualizaIP extends AsyncTask<Boolean, String, HashMap<String, String>> {

	private MyCurrentIP myCurrentIP;

	public TarefaAtualizaIP(MyCurrentIP myCurrentIP) {
		this.myCurrentIP = myCurrentIP;
	}

	@Override
	protected HashMap<String, String> doInBackground(Boolean... argv) {
		HashMap<String, String> enderecos = new HashMap<String, String>();
		enderecos.put("IP_LOCAL", Utils.getEnderecoIP(argv[0]));
		enderecos.put("MAC", Utils.getEnderecoMAC());
		return enderecos;
	}

	@Override
	protected void onPostExecute(HashMap<String, String> resposta) {
		String ip_local = resposta.get("IP_LOCAL");
		myCurrentIP.getCampoTextoIP().setText(ip_local);
		myCurrentIP.getCampoTextoMAC().setText(resposta.get("MAC"));

		Historico historico = new Historico();
		historico.setIp(ip_local);
		historico.setData_hora(Data.getDataHoraAtual());
		myCurrentIP.getRepoHistorico().insert(historico);
	}
}