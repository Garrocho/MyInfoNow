package com.mycurrentip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Ajuda extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajuda);
	}
	
	public void voltar(View componente) {
		finish();
	}
}
