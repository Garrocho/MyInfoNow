package com.mycurrentip.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class DialogoAlerta {
	public static void createDialogOk(Context context, final IDialogOk dialogOk, String titulo, String msg, boolean cancelavel){
		AlertDialog alerta;
		//Cria o gerador do AlertDialog
	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
	    //define o titulo
	    builder.setTitle(titulo);
	    //define a mensagem
	    builder.setMessage(msg);
	    //define um botão como positivo
	    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int arg1) {
	        	if(dialogOk != null){
	        		dialogOk.pressionouDialogOk();
	        	}
	        	else{
	        		dialog.cancel();
	        	}
	        }
	    });
	    builder.setCancelable(cancelavel);
	    //cria o AlertDialog
	    alerta = builder.create();
	    //Exibe
	    alerta.show();
	}
	
	public static interface IDialogOk{
		public void pressionouDialogOk();
	}
}
