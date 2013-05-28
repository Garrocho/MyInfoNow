package com.mycurrentip.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.annotation.SuppressLint;

public class Utils {

	/**
	 * Obtem o endereço de IP da interface localhost.
	 * @param ipv4  true=return ipv4, false=return ipv6
	 * @return  endereço ou uma string vazia.
	 */
	public static String getEnderecoIP(boolean useIPv4) {
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
				for (InetAddress addr : addrs) {
					if (!addr.isLoopbackAddress()) {
						String sAddr = addr.getHostAddress().toUpperCase();
						boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
						if (useIPv4) {
							if (isIPv4) 
								return sAddr;
						} else {
							if (!isIPv4) {
								int delim = sAddr.indexOf('%');
								return delim<0 ? sAddr : sAddr.substring(0, delim);
							}
						}
					}
				}
			}
		} catch (Exception ex) { }
		return "";
	}

	@SuppressLint("NewApi")
	public static String getEnderecoMAC() {
		HashMap<String, String> enderecos = null;
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			enderecos = new HashMap<String, String>();
			for (NetworkInterface intf : interfaces) {
				byte[] mac = intf.getHardwareAddress();
				if (mac==null) {
					enderecos.put(intf.getName(), "NADA");
					continue;
				}
				StringBuilder buf = new StringBuilder();
				for (int idx=0; idx<mac.length; idx++)
					buf.append(String.format("%02X:", mac[idx]));
				if (buf.length()>0) buf.deleteCharAt(buf.length()-1);
				enderecos.put(intf.getName(), buf.toString());
			}
		} catch (Exception ex) { }
		String end = "NADA";
		if (enderecos.containsKey("wlan0")) {
			end = enderecos.get("wlan0");
		}
		if (enderecos.containsKey("ifb0") && end.equalsIgnoreCase("NADA")) {
			end = enderecos.get("ifb0");
		}
		if (enderecos.containsKey("ifb1") && end.equalsIgnoreCase("NADA")) {
			end = enderecos.get("ifb1");
		}
		if (end.equalsIgnoreCase("NADA") && end.equalsIgnoreCase("NADA"))
			return "NÃO ENCONTRADO";
		return end;
	}

}
