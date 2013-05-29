package com.mycurrentip;

import java.util.HashMap;


public interface IAtualizaInfo{
	public void comecouExecucao();
	public void mostrarMensagem(String msg);
	public void terminouExecucao(HashMap<String, String> enderecos);
}
