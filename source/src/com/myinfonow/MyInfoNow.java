package com.myinfonow;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.myinfonow.adapter.ListaHistoricoAdapter;
import com.myinfonow.adapter.MenuAdapter;
import com.myinfonow.classes.Data;
import com.myinfonow.classes.Historico;
import com.myinfonow.dao.DatabaseHelper;
import com.myinfonow.dao.repositorios.RepositorioHistorico;
import com.myinfonow.net.Conexao;
import com.myinfonow.tarefa.TarefaAtualizaInfo;
import com.myinfonow.util.Constantes;
import com.myinfonow.util.DialogoAlerta;

public class MyInfoNow extends Activity implements IAtualizaInfo {

	private TabHost tabHost;
	private GridView gridMenuInicial;
	private String nomesMenus[] = {"Atualizar", "Ajuda", "Sair"};
	private int imagensMenus[]  = {R.drawable.atualizar, R.drawable.ajuda, R.drawable.sair};
	private TextView campoTextoIPLocal;
	private TextView campoTextoIPExterno;
	private TextView campoTextoMAC;
	private TextView campoTextoVazao;
	private CheckBox checkBoxIpLocal;
	private CheckBox checkBoxMac;
	private CheckBox checkBoxIpExterno;
	private CheckBox checkBoxVazao;
	private ListView listaHitorico;
	private RepositorioHistorico repoHistorico;
	private ProgressDialog dialogoProcesso;
	private int orientacao = 500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.atividade_my_info_now);

		this.repoHistorico = new RepositorioHistorico(this);

		campoTextoIPLocal = (TextView)findViewById(R.id.aba_minhas_informacoes_campo_texto_ip_local);
		campoTextoIPExterno = (TextView)findViewById(R.id.aba_minhas_informacoes_campo_texto_ip_externo);
		campoTextoMAC = (TextView)findViewById(R.id.aba_minhas_informacoes_campo_texto_mac);
		campoTextoVazao = (TextView)findViewById(R.id.aba_minhas_informacoes_campo_texto_vazao);

		checkBoxIpLocal = (CheckBox) findViewById(R.id.aba_minhas_informacoes_check_box_ip_local);
		checkBoxIpExterno = (CheckBox) findViewById(R.id.aba_minhas_informacoes_check_box_ip_externo);
		checkBoxMac = (CheckBox) findViewById(R.id.aba_minhas_informacoes_check_box_mac);
		checkBoxVazao = (CheckBox) findViewById(R.id.aba_minhas_informacoes_check_box_vazao);

		tabHost = (TabHost)findViewById(R.id.atividade_my_info_now);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("Minhas Informacoes");
		TabSpec spec2 = tabHost.newTabSpec("Historico");

		spec1.setIndicator("Minhas Informacoes");
		spec2.setIndicator("Historico");

		spec1.setContent(R.id.aba_minhas_informacoes);
		spec2.setContent(R.id.aba_historico);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);

		gridMenuInicial = (GridView)findViewById(R.id.aba_minhas_informacoes_menu);
		gridMenuInicial.setAdapter(new MenuAdapter(this));

		listaHitorico = (ListView)findViewById(R.id.aba_historico_lista);

		for(int i=0;i< tabHost.getTabWidget().getChildCount();i++) 
		{ 
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			tv.setTextColor(Color.parseColor("#ffffff"));
		}
		TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title);
		tv.setTextColor(Color.parseColor("#ffffff"));
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		switch(orientacao) {
		case Configuration.ORIENTATION_PORTRAIT:
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		case Configuration.ORIENTATION_LANDSCAPE:
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;           
		}
	}

	@Override
	protected void onResume() {
		atualizaInfo();
		listaHitorico.setAdapter(new ListaHistoricoAdapter(this, repoHistorico.listar()));
		super.onResume();
	}
	
	@SuppressWarnings("deprecation")
	public void atualizaInfo(){
		
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		orientacao = display.getOrientation();
		
		dialogoProcesso = new ProgressDialog(this);
		dialogoProcesso.setMessage("Obtendo informacoes...");
		dialogoProcesso.setCancelable(false);

		if(Conexao.verificaConexao(this)){
			TarefaAtualizaInfo tarefa = new TarefaAtualizaInfo(this);
			tarefa.execute(true); // IPv4
		}
		else{
			DialogoAlerta.createDialogOk(this, null, "Atualizando informacoes", "Sem conexao com a internet", true);
		}
		listaHitorico.setAdapter(new ListaHistoricoAdapter(this, repoHistorico.listar()));
	}

	public void trataEventoMenu(int posicao) {
		Intent intent = null;
		switch (posicao) {
		case 0: {
			atualizaInfo();
			break;
		}
		case 1: {
			intent = new Intent("myinfonow_ajuda");
			break;
		}
		case 2: {
			verificaSaida();
			break;
		}
		default: {
			break;
		}
		}
		if (intent != null){
			startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		verificaSaida();
	}

	public void verificaSaida() {
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Confirmacao")
		.setMessage("Deseja Sair do MyInfoNow?")
		.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MyInfoNow.this.finish();
			}
		})
		.setNegativeButton("Nao", null)
		.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(getClass().getName(), "Fechou o Banco.");
		DatabaseHelper db = DatabaseHelper.getInstance(this);
		if(db.isOpen()){
			db.fechar();
		}
	}

	public String[] getNomesMenus() {
		return nomesMenus;
	}

	public int[] getImagensMenus() {
		return imagensMenus;
	}

	public TextView getCampoTextoIP() {
		return campoTextoIPLocal;
	}
	
	public TextView getCampoTextoIPExterno() {
		return campoTextoIPExterno;
	}

	public TextView getCampoTextoMAC() {
		return campoTextoMAC;
	}

	public RepositorioHistorico getRepoHistorico() {
		return repoHistorico;
	}

	public TextView getCampoTextoVazao() {
		return campoTextoVazao;
	}

	public void setCampoTextoVazao(TextView campoTextoVazao) {
		this.campoTextoVazao = campoTextoVazao;
	}

	@Override
	public void comecouExecucao() {
		// TODO Auto-generated method stub
		dialogoProcesso.show();
		
	}

	@Override
	public void mostrarMensagem(String msg) {
		// TODO Auto-generated method stub
		dialogoProcesso.setMessage(msg);
	}

	@Override
	public void terminouExecucao(HashMap<String, String> enderecos) {
		// TODO Auto-generated method stub
		dialogoProcesso.dismiss();
		String ip_local = enderecos.get(Constantes.IP_LOCAL);
		String ip_externo = enderecos.get(Constantes.IP_EXTERNO);
		String mac = enderecos.get(Constantes.MAC);
		String vazao = enderecos.get(Constantes.VAZAO);
		getCampoTextoIP().setText(ip_local);
		getCampoTextoIPExterno().setText(ip_externo);
		getCampoTextoMAC().setText(mac);
		getCampoTextoVazao().setText(vazao);

		Historico historico = new Historico();
		historico.setIp_local(ip_local);
		historico.setIp_externo(ip_externo);
		historico.setMac(mac);
		historico.setVazao(vazao);
		historico.setData_hora(Data.getDataHoraAtual());
		getRepoHistorico().insert(historico);
	}
}