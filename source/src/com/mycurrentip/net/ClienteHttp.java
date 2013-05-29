package com.mycurrentip.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ClienteHttp {

	private HttpClient cliente;
	private HttpPost post;
	private HttpGet get;
	private String endereco;
	private String tipo;
	private List<NameValuePair> parametros;
	private HttpResponse resposta;

	public ClienteHttp(String endereco, String tipo) {
		this.endereco = endereco;
		this.cliente = new DefaultHttpClient();
		this.tipo = tipo;
		this.parametros = new ArrayList<NameValuePair>();

		if (tipo.equalsIgnoreCase("POST"))
			this.post = new HttpPost(this.endereco);
		else
			this.get = new HttpGet(this.endereco);
	}

	public void addParametro(String codigo, String valor) {
		BasicNameValuePair valores = new BasicNameValuePair(codigo, valor);
		parametros.add(valores);
	}

	public void limparParametros() {
		parametros.removeAll(parametros);
	}

	public void executar() {
		try {
			UrlEncodedFormEntity urlParametros = new UrlEncodedFormEntity(parametros, HTTP.UTF_8);
			if (tipo.equalsIgnoreCase("POST")) {
				post.setEntity(urlParametros);
				resposta = cliente.execute(post);
			}
			else {
				resposta = cliente.execute(get);
			}
		}catch (Exception e) {
			Log.d("cod", e.toString());
			resposta = null;
		}
	}

	public int getStatus() {
		if (resposta != null) {
			return resposta.getStatusLine().getStatusCode();
		}
		return 408;
	}

	public String getTextoResposta() throws ParseException, IOException{
		HttpEntity entity = resposta.getEntity();
		String textoResposta = EntityUtils.toString(entity);
		return textoResposta;
	}

	public InputStream getContent() throws IllegalStateException, IOException {
		return resposta.getEntity().getContent();
	}

	public Object obterJson(Class<?> classe) {

		Object json = null;
		try {
			
			HttpEntity httpEntity = resposta.getEntity();
			if (httpEntity != null) {

				Gson gson = new GsonBuilder().create();			  
				InputStream httpEntityContent = httpEntity.getContent();
				BufferedReader in = new BufferedReader(new InputStreamReader(httpEntityContent));
				json = gson.fromJson(in, classe);
				in.close();
				return json;
			}
		} catch (ClientProtocolException e) {
			Log.d("ClientProtocolException: ", e.toString());
		} catch (IOException e) {
			Log.d("IOException: ", e.toString());
		}
		return json;
	}
}