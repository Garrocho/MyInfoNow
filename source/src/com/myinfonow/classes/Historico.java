package com.myinfonow.classes;

import java.sql.Timestamp;

public class Historico {
	
	private String ip_local;
	private String ip_externo; 
	private String mac;
	private String vazao;
	private String latitude;
	private String longitude;
	private Timestamp data_hora;
	
	public String getIp_local() {
		return ip_local;
	}

	public void setIp_local(String ip_local) {
		this.ip_local = ip_local;
	}

	public String getIp_externo() {
		return ip_externo;
	}

	public void setIp_externo(String ip_externo) {
		this.ip_externo = ip_externo;
	}

	public Timestamp getData_hora() {
		return data_hora;
	}
	
	public void setData_hora(Timestamp data_hora) {
		this.data_hora = data_hora;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getVazao() {
		return vazao;
	}

	public void setVazao(String vazao) {
		this.vazao = vazao;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public static final class Historicos {
		public static final String IP_LOCAL = "ip_local";
		public static final String IP_EXTERNO = "ip_externo";
		public static final String MAC = "mac";
		public static final String VAZAO = "vazao";
		public static final String latitude = "latitude";
		public static final String longitude = "longitude";
		public static final String DATA_HORA = "data_hora";
		
		public static final String[] COLUNAS = {IP_LOCAL, IP_EXTERNO, MAC, VAZAO, DATA_HORA};
	}
}
