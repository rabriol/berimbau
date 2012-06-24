package br.com.mack.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ConexaoFactory {
	
	private String url;
	private HttpClient client;
	private URI uri;
	private HttpGet get;
	
	public ConexaoFactory(String url) {
		this.url = url;
	}
	
	public HttpResponse executeHttpClient() {
		client = new DefaultHttpClient();
		createUri();
		createHttpGet();
		try {
			return client.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private void createUri() {
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void createHttpGet() {
		get = new HttpGet();
		get.setURI(uri);
	}
}
