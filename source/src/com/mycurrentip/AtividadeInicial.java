package com.mycurrentip;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AtividadeInicial extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.atividade_inicial);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atividade_inicial, menu);
		return true;
	}

}
