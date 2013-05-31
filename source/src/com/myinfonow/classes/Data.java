package com.myinfonow.classes;

import java.sql.Timestamp;
import java.util.Calendar;

import android.util.Log;

public class Data {
	
	public static Timestamp getDataHoraAtual(){
		Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH)+1;
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);
        Log.w("Data Atual", "Ano: " + ano + " Mês: " + mes + " Dia: " + dia + " Hora: " + hora + " Minutos: " + minuto );
		return new Timestamp(c.getTimeInMillis());
	}
	
}
