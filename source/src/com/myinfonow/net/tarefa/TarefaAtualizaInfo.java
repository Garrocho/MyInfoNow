package com.myinfonow.net.tarefa;

import java.util.HashMap;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.util.Log;

import com.myinfonow.MyInfoNow;
import com.myinfonow.net.ClienteHttp;
import com.myinfonow.net.Conexao;
import com.myinfonow.util.Constantes;
import com.myinfonow.util.Enderecos;
import com.myinfonow.util.Json.IpExterno;
import com.myinfonow.util.Json.Vazao;


public class TarefaAtualizaInfo extends AsyncTask<Boolean, String, HashMap<String, String>> {

	private MyInfoNow myinfonow;
	private ClienteHttp clienteHttp;
	private IpExterno ipExterno;
	private Vazao vazao;

	public TarefaAtualizaInfo(MyInfoNow myinfonow) {
		this.myinfonow = myinfonow;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		myinfonow.comecouExecucao();
		super.onPreExecute();
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		myinfonow.mostrarMensagem(values[0]);
		super.onProgressUpdate(values);
	}
	
	@Override
	protected HashMap<String, String> doInBackground(Boolean... argv) {
		int codResposta = 0;
		int executeCount = 0;
		String ip_interno = "0.0.0.0";
		String mac = "00:00:00:00:00:00";
		String ip_externo = "0.0.0.0"; 
		String taxa_conexao = "0.0 Mbps";
		
<<<<<<< HEAD:source/src/com/myinfonow/net/tarefa/TarefaAtualizaInfo.java
		publishProgress("Carregando Ip Local...");
=======
>>>>>>> 1bac6d5a06398feec43741296f6234053b9ec660:source/src/com/myinfonow/tarefa/TarefaAtualizaInfo.java
		HashMap<String, String> enderecos = new HashMap<String, String>();
		
<<<<<<< HEAD:source/src/com/myinfonow/net/tarefa/TarefaAtualizaInfo.java
		publishProgress("Ip Local: " + ip_interno + "\nCarregando MAC...");
		mac = Enderecos.getEnderecoMAC();
		enderecos.put(Constantes.MAC, mac);
		publishProgress("Ip Local: " + ip_interno + "\nMac: " + mac + "\nVerificando Conexao...");
		
		if (Conexao.verificaConexao(myinfonow)){
			publishProgress("Ip Local: " + ip_interno + "\nMAC: " + mac + "\nConexao OK\nCarregando Ip Externo...");
			clienteHttp = new ClienteHttp(Constantes.URL_JSON_IP_EXTERNO, "GET");
			do {
				executeCount++;
				clienteHttp.executar();
				codResposta = clienteHttp.getStatus();
				Log.d("codresposta ip externo", String.valueOf(codResposta));
			} while (executeCount < 5 && codResposta == 408);
	
			if (codResposta == 200) {
				ipExterno = (IpExterno)clienteHttp.obterJson(IpExterno.class);
				ip_externo = ipExterno.getIp();
				publishProgress("Ip local: " + ip_interno + "\nMAC: " + mac + "\nIp Externo: " + ip_externo);
=======
		if(myinfonow.getListaCheckBox().get(0).isChecked()){
			publishProgress("Loading...");
			ip_interno = Enderecos.getEnderecoIP(argv[0]);
			enderecos.put(Constantes.IP_LOCAL, ip_interno);
		}
		
		if(myinfonow.getListaCheckBox().get(2).isChecked()){
			publishProgress("Ip local: " + ip_interno + "\nLoading...");
			mac = Enderecos.getEnderecoMAC();
			enderecos.put(Constantes.MAC, mac);
			publishProgress("Ip local: " + ip_interno + "\nMac: " + mac + "\nVerificando Conexao...");
		}
		
		if (Conexao.verificaConexao(myinfonow)){
			if(myinfonow.getListaCheckBox().get(1).isChecked()){
				publishProgress("Ip local: " + ip_interno + "\nMac: " + mac + "\nConexao OK\nLoading... (Ip externo)");
				clienteHttp = new ClienteHttp(Constantes.URL_JSON_IP_EXTERNO, "GET");
				do {
					executeCount++;
					clienteHttp.executar();
					codResposta = clienteHttp.getStatus();
					Log.d("codresposta ip externo", String.valueOf(codResposta));
				} while (executeCount < 5 && codResposta == 408);
		
				if (codResposta == 200) {
					ipExterno = (IpExterno)clienteHttp.obterJson(IpExterno.class);
					ip_externo = ipExterno.getIp();
					publishProgress("Ip local: " + ip_interno + "\nMac: " + mac + "\nIp Externo: " + ip_externo);
				}
				enderecos.put(Constantes.IP_EXTERNO, ip_externo);
>>>>>>> 1bac6d5a06398feec43741296f6234053b9ec660:source/src/com/myinfonow/tarefa/TarefaAtualizaInfo.java
			}
			
<<<<<<< HEAD:source/src/com/myinfonow/net/tarefa/TarefaAtualizaInfo.java
			executeCount = 0;
			publishProgress("Ip Local: " + ip_interno + "\nMAC: " + mac + "\nIp Externo: " + ip_externo + 
					"\nCarregando Taxa de Conexao...");
			clienteHttp = new ClienteHttp(Constantes.URL_TAXA_CONEXAO, "GET");
			do {
				executeCount++;
				clienteHttp.executar();
				codResposta = clienteHttp.getStatus();
				Log.d("codresposta", String.valueOf(codResposta));
			} while (executeCount < 5 && codResposta == 408);
	
			if (codResposta == 200) {
				vazao = new Vazao(clienteHttp.obterHtml(Vazao.class));
				taxa_conexao = vazao.getVazao();
				publishProgress("Ip local: " + ip_interno + "\nMAC: " + mac + "Ip Externo: " + ip_externo + 
						"\nTaxa de Conexao" + taxa_conexao + "Mbps");
			}			
=======
			if(myinfonow.getListaCheckBox().get(3).isChecked()){
				executeCount = 0;
				publishProgress("Ip local: " + ip_interno + "\nMac: " + mac + "\nIp Externo: " + ip_externo + 
						"\nLoading... (Taxa de Conexão )");
				clienteHttp = new ClienteHttp(Constantes.URL_TAXA_CONEXAO, "GET");
				do {
					executeCount++;
					clienteHttp.executar();
					codResposta = clienteHttp.getStatus();
					Log.d("codresposta", String.valueOf(codResposta));
				} while (executeCount < 5 && codResposta == 408);
		
				if (codResposta == 200) {
					vazao = new Vazao(clienteHttp.obterHtml(Vazao.class));
					taxa_conexao = vazao.getVazao();
					publishProgress("Ip local: " + ip_interno + "\nMac: " + mac + "Ip Externo: " + ip_externo + 
							"\nTaxa de Conexao" + taxa_conexao + "Mbps");
				}
				enderecos.put(Constantes.VAZAO, taxa_conexao);
			}
>>>>>>> 1bac6d5a06398feec43741296f6234053b9ec660:source/src/com/myinfonow/tarefa/TarefaAtualizaInfo.java
		}
		return enderecos;
	}

	@Override
	protected void onPostExecute(HashMap<String, String> resposta) {
		myinfonow.terminouExecucao(resposta);
	}
}