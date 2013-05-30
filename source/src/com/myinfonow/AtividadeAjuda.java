package com.myinfonow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.myinfonow.R;

public class AtividadeAjuda extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajuda);
	}
	
	public void voltar(View componente) {
		finish();
	}
}
