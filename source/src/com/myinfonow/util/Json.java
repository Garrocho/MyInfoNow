package com.myinfonow.util;

public class Json {
	
	public static class IpExterno{
		
		public String ip;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}
	}
	
	public static class Vazao{
		
		public String vazao;
		
		public Vazao(String vazao) {
			this.vazao = vazao;
		}

		public String getVazao() {
			return vazao;
		}

		public void setVazao(String vazao) {
			this.vazao = vazao;
		}
	}	

}
