package com.mycurrentip.classes;

import java.sql.Date;
import java.util.Calendar;

public class Data {
	
	public static Date getDataAtual(){
		Calendar calendar = Calendar.getInstance();
		return new Date(calendar.getTimeInMillis());
	}
	
	public static int comparaDataComAtual(Date data) {
		return comparaData(Data.getDataAtual(), data);
	}
	
	/**
	 * @return 0 se as datas forem iguais, -1 se a primeira data for menor que a segunda e 1 se a primeira data for maior que a segunda.
	 */
	public static int comparaData(Date dataPrimeira, Date dataSegunda){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataPrimeira);
		Long milisPrimeira = calendar.getTimeInMillis();
		calendar.setTime(dataSegunda);
		Long milisSegunda = calendar.getTimeInMillis();
		
		return milisPrimeira.compareTo(milisSegunda);
	}
	
	public static Long diferencaDataAtual(Date data){
        int tempoDia = 1000 * 60 * 60 * 24;
        return Data.diferencaData(Data.getDataAtual(), data) / tempoDia;
	}
	
	public static Long diferencaData(Date dataPrimeira, Date dataSegunda){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataPrimeira);
		Long milisPrimeira = calendar.getTimeInMillis();
		calendar.setTime(dataSegunda);
		Long milisSegunda = calendar.getTimeInMillis();
 
        long diferenca = milisPrimeira - milisSegunda;
        return diferenca;
	}
	
	public static Date getUltimoDiaAnoAtual(){
		Calendar calendar = Calendar.getInstance();
		int diaMaximo = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, diaMaximo);
		int mesMaximo = calendar.getActualMaximum(Calendar.MONTH);
		calendar.set(Calendar.MONTH, mesMaximo);
		return new Date(calendar.getTimeInMillis());
	}
	
	public static Date getDataAtualPrimeiroDiaMes(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return new Date(calendar.getTimeInMillis());
	}
	
	public static Date getDataAtualUltimoDiaMes(){
		Calendar calendar = Calendar.getInstance();
		int diaMaximo = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, diaMaximo);
		return new Date(calendar.getTimeInMillis());
	}

	public static String DateToString(Date date, String separador){
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		String stringDia = dia < 10 ? "0" + dia : String.valueOf(dia);
		
		int mes = calendar.get(Calendar.MONTH) + 1;
		String stringMes = mes < 10 ? "0" + mes : String.valueOf(mes);
		
		int ano = calendar.get(Calendar.YEAR);
		
		String dataEmString = stringDia + separador + stringMes + separador +  ano;
		return dataEmString;
	}
	
	public static String DateToString(Date date){
		return DateToString(date, "-");
	}
	
	public static String DateToStringSQL(Date date, String separador){
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		String stringDia = dia < 10 ? "0" + dia : String.valueOf(dia);
		
		int mes = calendar.get(Calendar.MONTH) + 1;
		String stringMes = mes < 10 ? "0" + mes : String.valueOf(mes);
		
		int ano = calendar.get(Calendar.YEAR);
		
		String dataEmString = ano + separador + stringMes + separador +  stringDia;
		return dataEmString;
	}
	
	public static String DateToStringSQL(Date date){
		return DateToStringSQL(date, "-");
	}
	
	public static Date StringToDate(String data){
		return StringToDate(data, '-');
	}
	
	public static Date StringToDate(String data, char separador){
		if (data == null){
			return null;
		}
		int dia = Integer.parseInt(data.substring(0, data.indexOf(separador)));
		int mes = Integer.parseInt(data.substring(data.indexOf(separador)+1, data.lastIndexOf(separador)));
		int ano = Integer.parseInt(data.substring(data.lastIndexOf(separador)+1, data.length()));
		Calendar calendar = Calendar.getInstance();
		calendar.set(ano, mes - 1, dia);
		return new Date(calendar.getTimeInMillis());
	}
	
	public static Date StringSQLToDate(String data){
		return StringSQLToDate(data, '-');
	}
	
	public static Date StringSQLToDate(String data, char separador){
		if (data == null){
			return null;
		}
		int ano = Integer.parseInt(data.substring(0, data.indexOf(separador)));
		int mes = Integer.parseInt(data.substring(data.indexOf(separador)+1, data.lastIndexOf(separador)));
		int dia = Integer.parseInt(data.substring(data.lastIndexOf(separador)+1, data.length()));
		Calendar calendar = Calendar.getInstance();
		calendar.set(ano, mes - 1, dia);
		return new Date(calendar.getTimeInMillis());
	}
	
	public static String criaData(int dia, int mes, int ano){
		return criaData(dia, mes, ano, "/");
	}
	
	public static String criaData(int dia, int mes, int ano, String separador){
		String stringDia = String.valueOf(dia);
		if(dia < 10){
			stringDia = "0" + String.valueOf(dia);
		}
		
		String stringMes = String.valueOf(mes);
		if(mes < 10){
			stringMes = "0" + String.valueOf(mes);
		}
		
		return stringDia + separador + stringMes + separador + String.valueOf(ano);
	}
	
}
