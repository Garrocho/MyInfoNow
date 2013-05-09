package com.mycurrentip;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.mycurrentip.adapter.MenuAdapter;

public class MyCurrentIP extends Activity {

	private TabHost tabHost;
	private GridView gridMenuInicial;
	private String nomesMenus[] = {"Atualizar", "Ajuda", "Sair"};
	private int imagensMenus[]  = {R.drawable.atualizar, R.drawable.ajuda, R.drawable.sair};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_current_ip);

		tabHost = (TabHost)findViewById(R.id.activity_my_current_ip_tab_host);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("IP Atual");
		TabSpec spec2 = tabHost.newTabSpec("IPs Anteriores");

		spec1.setIndicator("IP Atual");
		spec2.setIndicator("IPs Anteriores");

		spec1.setContent(R.id.aba_ip_atual);
		spec2.setContent(R.id.aba_ips_anteriores);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		
		gridMenuInicial = (GridView)findViewById(R.id.activity_my_current_ip_menu);
		gridMenuInicial.setAdapter(new MenuAdapter(this));
	}
	
	public void trataEventoMenu(int posicao) {
		//Intent intent = null;
		switch (posicao) {
			case 0:{
				//intent = new Intent("consulta_cliente");
				break;
			}
			case 1:{
				//intent = new Intent("consulta_pedido");
				break;
			}
			case 2:{
				verificaSaida();
				break;
			}
			default:{
				break;
			}
		}
		//if (intent != null){
			//startActivity(intent);
		//}
	}

	@Override
	public void onBackPressed() {
		verificaSaida();
	}

	public void verificaSaida() {
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Confirmação")
		.setMessage("Deseja Sair do MyCurrentIP?")
		.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MyCurrentIP.this.finish();
			}
		})
		.setNegativeButton("Não", null)
		.show();
	}

	public String[] getNomesMenus() {
		return nomesMenus;
	}

	public void setNomesMenus(String nomesMenus[]) {
		this.nomesMenus = nomesMenus;
	}

	public int[] getImagensMenus() {
		return imagensMenus;
	}

	public void setImagensMenus(int imagensMenus[]) {
		this.imagensMenus = imagensMenus;
	}
}