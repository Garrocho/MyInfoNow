package com.mycurrentip.classes;

import java.sql.Timestamp;

public class Historico {
	
	private String ip;
	private Timestamp data_hora;
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Timestamp getData_hora() {
		return data_hora;
	}
	
	public void setData_hora(Timestamp data_hora) {
		this.data_hora = data_hora;
	}
	
	public static final class Historicos {
		public static final String IP = "ip";
		public static final String DATA_HORA = "data_hora";
		
		public static final String[] COLUNAS = {IP, DATA_HORA};
	}
}
