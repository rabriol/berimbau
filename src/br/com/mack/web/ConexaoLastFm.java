package br.com.mack.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

import br.com.mack.util.Config;

public class ConexaoLastFm {

	private StringBuffer pesquisar(ConexaoFactory factory) throws IllegalStateException, IOException {
		if (factory == null) throw new IllegalArgumentException("o argumento factory nao pode ser nulo");
		
		BufferedReader in = null;
		String str;
		StringBuffer buff = new StringBuffer();
		
		//realiza conexao com o lastfm
		HttpResponse response = factory.executeHttpClient();
		
		in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		while ((str = in.readLine()) != null) {
			buff.append(str);
		}
		
		return buff;
	}
	
	private InputStream pesquisarImagem(ConexaoFactory factory) throws IllegalStateException, IOException {
		if (factory == null) throw new IllegalArgumentException("o argumento factory nao pode ser nulo");
		
		//realiza conexao com o lastfm
		HttpResponse response = factory.executeHttpClient();
		
		InputStream input = response.getEntity().getContent();
		
		return input;
	}
	
	public StringBuffer pesquisarArtistaInfo(String param) {
		ConexaoFactory factory = null;
		factory = new ConexaoFactory("http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="+
							param+"&api_key="+Config.LAST_FM_API_KEY+"&format=json");
		
		try {
			StringBuffer retornoLastFM = pesquisar(factory);
			return retornoLastFM;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public StringBuffer pesquisarArtistaEventos(String param) {
		ConexaoFactory factory = null;
		factory = new ConexaoFactory("http://ws.audioscrobbler.com/2.0/?method=artist.getevents&artist="+
							param+"&api_key="+Config.LAST_FM_API_KEY+"&format=json");
		
		try {
			
			StringBuffer retornoLastFM = pesquisar(factory);
			return retornoLastFM;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public StringBuffer pesquisarArtistaAlbuns(String param) {
		ConexaoFactory factory = null;
		factory = new ConexaoFactory("http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist="+
							param+"&api_key="+Config.LAST_FM_API_KEY+"&format=json");
		
		try {
			
			StringBuffer retornoLastFM = pesquisar(factory);
			return retornoLastFM;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream obterConteudoUrlInputStream(String param) {
		ConexaoFactory factory = null;
		factory = new ConexaoFactory(param);
		InputStream input = null;
		
		try {
		
			input = pesquisarImagem(factory);
		
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	public StringBuffer pesquisarFaixaAlbum(String cantorNomeFormatado, String faixaMusicaNomeFormatado) {
		ConexaoFactory factory = null;
		factory = new ConexaoFactory("http://ws.audioscrobbler.com/2.0/?method=album.getinfo&" +
				"api_key="+Config.LAST_FM_API_KEY+"&artist="+cantorNomeFormatado+"&" +
						"album="+faixaMusicaNomeFormatado+"&format=json");
		InputStream input = null;
		try {
			
			StringBuffer retornoLastFM = pesquisar(factory);
			return retornoLastFM;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
