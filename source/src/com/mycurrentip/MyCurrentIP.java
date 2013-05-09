package com.mycurrentip;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MyCurrentIP extends Activity {

	private TabHost tabHost;

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
	}
}