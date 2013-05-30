package com.mycurrentip.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public abstract class Conexao {
	public static boolean verificaConexao(Context contexto) {
		ConnectivityManager cn = (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo infoRede = cn.getActiveNetworkInfo();

		if(infoRede != null && infoRede.isConnected()){
			return true;
		}
		else{
			return false;
		}
	}
}
