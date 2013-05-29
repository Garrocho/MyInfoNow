package com.mycurrentip.tarefa;

import java.util.HashMap;

import android.os.AsyncTask;
import android.util.Log;

import com.mycurrentip.MyCurrentIP;
import com.mycurrentip.classes.Data;
import com.mycurrentip.classes.Historico;
import com.mycurrentip.net.ClienteHttp;
import com.mycurrentip.net.Conexao;
import com.mycurrentip.util.Constantes;
import com.mycurrentip.util.Enderecos;
import com.mycurrentip.util.Json.IpExterno;


public class TarefaAtualizaInfo extends AsyncTask<Boolean, String, HashMap<String, String>> {

	private MyCurrentIP myCurrentIP;
	private ClienteHttp clienteHttp;
	private IpExterno ipExterno;

	public TarefaAtualizaInfo(MyCurrentIP myCurrentIP) {
		this.myCurrentIP = myCurrentIP;
	}

	@Override
	protected HashMap<String, String> doInBackground(Boolean... argv) {
		int codResposta = 0;
		int executeCount = 0;
		String ip_externo = "Sem Conexao"; 
		
		HashMap<String, String> enderecos = new HashMap<String, String>();
		enderecos.put(Constantes.IP_LOCAL, Enderecos.getEnderecoIP(argv[0]));
		enderecos.put(Constantes.MAC, Enderecos.getEnderecoMAC());
		
		if (Conexao.verificaConexao(myCurrentIP)){
			clienteHttp = new ClienteHttp(Constantes.URL_JSON_IP_EXTERNO, "GET");
			do {
				executeCount++;
				clienteHttp.executar();
				codResposta = clienteHttp.getStatus();
				Log.d("codresposta", String.valueOf(codResposta));
			} while (executeCount < 5 && codResposta == 408);
	
			if (codResposta == 202) {
				ipExterno = (IpExterno)clienteHttp.obterJson(IpExterno.class);
				ip_externo = ipExterno.getIp();
			}
		}
		enderecos.put(Constantes.IP_EXTERNO, ip_externo);
		return enderecos;
	}

	@Override
	protected void onPostExecute(HashMap<String, String> resposta) {
		String ip_local = resposta.get(Constantes.IP_LOCAL);
		myCurrentIP.getCampoTextoIP().setText(ip_local);
		myCurrentIP.getCampoTextoIPExterno().setText(resposta.get(Constantes.IP_EXTERNO));
		myCurrentIP.getCampoTextoMAC().setText(resposta.get(Constantes.MAC));

		Historico historico = new Historico();
		historico.setIp(ip_local);
		historico.setData_hora(Data.getDataHoraAtual());
		myCurrentIP.getRepoHistorico().insert(historico);
	}
}