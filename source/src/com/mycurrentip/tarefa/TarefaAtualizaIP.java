package com.mycurrentip.tarefa;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.mycurrentip.MyCurrentIP;


public class TarefaAtualizaIP extends AsyncTask<String, Integer, Integer> {

	private MyCurrentIP myCurrentIP;

	public TarefaAtualizaIP(MyCurrentIP myCurrentIP) {
		this.myCurrentIP = myCurrentIP;
	}

	@Override
	protected void onPreExecute() {
		progressDialog.show();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressDialog.setMessage("Conectando ao servidor em... (" + values[0] + "/5)");
	}

	@Override
	protected Integer doInBackground(String... arg0) {
		
		do {
			executeCount++;
			publishProgress(executeCount);
			clienteHttp.executar();
			codResposta = clienteHttp.getStatus();
		} while (executeCount < 5 && codResposta == 408);
		
		return codResposta;
	}

	@Override
	protected void onPostExecute(Integer codResposta) {
		if(codResposta == 202) {
			progressDialog.setMessage("Atualizando o Produto " + atividadeConsultaPrecos.getProdutoAtual().getNome() + "...");

			Produto produto = jsonProdutos.getProdutos()[0];
			try {
				repositorioProduto.update(produto);
				atividadeConsultaPrecos.mostrarCodRegitroErros("Produto " + produto.getNome() + " Atualizado.");
			}catch (Exception e) {
				atividadeConsultaPrecos.mostrarCodRegitroErros("Não Foi Possível Atualizar o Produto " + produto.getNome());
			}
		}
		else if (codResposta == 204) {
			atividadeConsultaPrecos.mostrarCodRegitroErros("Nada a Atualizar Nesse Produto.");
		}
		else if (codResposta == 402) {
			atividadeConsultaPrecos.mostrarCodRegitroErros("O Vendedor desse Dispositivo Esta Inativo.");
		}
		else if (codResposta == 401) {
			atividadeConsultaPrecos.mostrarCodRegitroErros("Codigo de Registro Invalido.");
		}
		else if (codResposta == 400) {
			atividadeConsultaPrecos.mostrarCodRegitroErros("Id do Aparelho Invalido.");
		}
		else {
			atividadeConsultaPrecos.mostrarCodRegitroErros("Erro ao Conectar no Servidor");
		}
		progressDialog.dismiss();
	}
}