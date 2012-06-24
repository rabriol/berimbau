package br.com.mack.util;

public class Formatter {
	
	public static String retirarTagsHtmlXml(String value) {
		value = value.replaceAll("<[^>]*>", "").replaceAll("&quot;", "\"");
		return value;
	}

	public static String prepararTermoBusca(String param) {
		return param.trim().replaceAll(" ", "+");
	}
}
