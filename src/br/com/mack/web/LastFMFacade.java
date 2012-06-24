package br.com.mack.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.com.mack.ArtistAlbumActivity;
import br.com.mack.entity.ArtistAlbumEntity;
import br.com.mack.entity.ArtistEventoEntity;
import br.com.mack.entity.ArtistInfoEntity;
import br.com.mack.entity.FaixaMusicaEntity;
import br.com.mack.util.Formatter;

public class LastFMFacade {
	private static ArtistInfoEntity artistInfo = null;
	private static ArtistEventoEntity artistEventos = null;
	private static ArtistAlbumEntity artistAlbum = null;
	private static FaixaMusicaEntity faixaMusica = null;
	private static List<ArtistEventoEntity> listEvento = null;
	private static List<ArtistAlbumEntity> listAlbum = null;
	private static List<FaixaMusicaEntity> listFaixaMusica = null;
	private static ConexaoLastFm factory = null;
	
	static {
		factory = new ConexaoLastFm();
	}
	
	public static ArtistInfoEntity obterBioArtista(String param) {
		artistInfo = new ArtistInfoEntity();
		StringBuffer jsonResultado = null;
		JSONObject jsonData = null;
		JSONObject jsonData2 = null;
		JSONObject jsonData3 = null;
		
		try {
			String termoBuscaFormatado = Formatter.prepararTermoBusca(param);
			jsonResultado = factory.pesquisarArtistaInfo(termoBuscaFormatado);
			
			//converte a string json para um objeto navegavel
			jsonData = new JSONObject(jsonResultado.toString());
			jsonData2 = jsonData.getJSONObject("artist");
			jsonData3 = jsonData2.getJSONObject("bio");
			
			String contentFormatado = Formatter.retirarTagsHtmlXml(jsonData3.getString("content"));
			
			artistInfo.setName(jsonData2.getString("name"));
			artistInfo.setUrl(((JSONObject) jsonData2.getJSONArray("image").get(3)).getString("#text"));
			artistInfo.setSummary(jsonData3.getString("summary"));
			artistInfo.setContent(contentFormatado);
			
			return artistInfo;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ArtistEventoEntity> obterEventosArtista(String param) {
		listEvento = new ArrayList<ArtistEventoEntity>();
		StringBuffer jsonResultado = null;
		JSONArray jsonArray = null;
		JSONObject jsonData = null;
		JSONObject jsonData2 = null;
		JSONObject jsonData3 = null;
		
		try {
			String termoBuscaFormatado = Formatter.prepararTermoBusca(param);
			jsonResultado = factory.pesquisarArtistaEventos(termoBuscaFormatado);
			
			//converte a string json para um objeto navegavel
			jsonData = new JSONObject(jsonResultado.toString());
			jsonData = jsonData.getJSONObject("events");
			jsonArray = jsonData.getJSONArray("event");
			
			for (int i = 0 ; i < jsonArray.length() ; i++) {
				artistEventos = new ArtistEventoEntity();
				JSONObject json = (JSONObject)jsonArray.get(i);
				jsonData2 = json.getJSONObject("venue");
				jsonData3 = jsonData2.getJSONObject("location");
				
				artistEventos.setNomeEventos(json.getString("title"));
				artistEventos.setCidade(jsonData3.getString("city"));;
				artistEventos.setPais(jsonData3.getString("country"));
				artistEventos.setRua(jsonData3.getString("street"));
				artistEventos.setCep(jsonData3.getString("postalcode"));
				artistEventos.setGeoLatitude(jsonData3.getJSONObject("geo:point").getString("geo:lat"));
				artistEventos.setGeoLongitude(jsonData3.getJSONObject("geo:point").getString("geo:long"));
				artistEventos.setComecaDia(json.getString("startDate"));
				artistEventos.setSiteEvento(jsonData2.getString("website"));
				listEvento.add(artistEventos);
			}
			
			return listEvento;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ArtistAlbumEntity> obterAlbumArtista(String param) {
		listAlbum = new ArrayList<ArtistAlbumEntity>();
		StringBuffer jsonResultado = null;
		JSONArray jsonArray = null;
		JSONObject jsonData = null;
		JSONObject jsonData2 = null;
		
		try {
			String termoBuscaFormatado = Formatter.prepararTermoBusca(param);
			jsonResultado = factory.pesquisarArtistaAlbuns(termoBuscaFormatado);
			
			jsonData = new JSONObject(jsonResultado.toString());
			jsonData2 = jsonData.getJSONObject("topalbums");
			jsonArray = jsonData2.getJSONArray("album");
			
			for (int i = 0 ; i < jsonArray.length() ; i++) {
				artistAlbum = new ArtistAlbumEntity();
				JSONObject json = (JSONObject)jsonArray.get(i);
				artistAlbum.setName(json.getString("name"));
				artistAlbum.setUrl(((JSONObject)json.getJSONArray("image").get(2)).getString("#text"));
				artistAlbum.setCantorNome(json.getJSONObject("artist").getString("name"));
				listAlbum.add(artistAlbum);
			}
			
			return listAlbum;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap obterImagem(String param) {
		Bitmap bitmap;
		InputStream input;
		
		input = factory.obterConteudoUrlInputStream(param);
		bitmap = BitmapFactory.decodeStream(input);
		
		return bitmap;
	}

	public static List<FaixaMusicaEntity> obterListaDeMusicas(String cantor, String albumNome) {
		listFaixaMusica = new ArrayList<FaixaMusicaEntity>();
		StringBuffer jsonResultado = null;
		JSONArray jsonArray = null;
		JSONObject jsonData = null;
		JSONObject jsonData2 = null;
		
		try {
			String cantorNomeFormatado = Formatter.prepararTermoBusca(cantor);
			String faixaMusicaNomeFormatado = Formatter.prepararTermoBusca(albumNome);
			jsonResultado = factory.pesquisarFaixaAlbum(cantorNomeFormatado, faixaMusicaNomeFormatado);
			
			jsonData = new JSONObject(jsonResultado.toString());
			jsonData2 = jsonData.getJSONObject("album");
			jsonArray = jsonData2.getJSONObject("tracks").getJSONArray("track");
			
			for (int i = 0 ; i < jsonArray.length() ; i++) {
				faixaMusica = new FaixaMusicaEntity();
				JSONObject json = (JSONObject)jsonArray.get(i);
				faixaMusica.setNome(json.getString("name"));
				faixaMusica.setDuracao(json.getString("duration"));
				listFaixaMusica.add(faixaMusica);
			}
			
			return listFaixaMusica;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
