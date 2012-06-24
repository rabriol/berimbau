package br.com.mack.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.mack.R;
import br.com.mack.entity.ArtistAlbumEntity;
import br.com.mack.entity.FaixaMusicaEntity;
import br.com.mack.web.LastFMFacade;

public class ExpandableArtistAlbumAdapter extends BaseExpandableListAdapter{
	private Context context;
	private List<ArtistAlbumEntity> albuns;
	private Map<Integer, List<FaixaMusicaEntity>> faixas = new HashMap<Integer, List<FaixaMusicaEntity>>();
	
    public ExpandableArtistAlbumAdapter(Context context, List<ArtistAlbumEntity> albuns) {
    	this.context = context;
    	this.albuns = albuns;
    }
    
    public Object getChild(int groupPosition, int childPosition) {
    	if (!faixas.containsKey(groupPosition)) {
    		ArtistAlbumEntity album = (ArtistAlbumEntity)getGroup(groupPosition);
    		List<FaixaMusicaEntity> faixasRetornada = obterLitaDeMusicas(album.getCantorNome(), album.getName());
    		faixas.put(groupPosition, faixasRetornada);
    	}
    	
    	return faixas.get(groupPosition).get(childPosition);
    }

	public long getChildId(int groupPosition, int childPosition) {
    	return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
    	if (!faixas.containsKey(groupPosition)) {
    		ArtistAlbumEntity album = (ArtistAlbumEntity)getGroup(groupPosition);
    		List<FaixaMusicaEntity> faixasRetornada = obterLitaDeMusicas(album.getCantorNome(), album.getName());
    		faixas.put(groupPosition, faixasRetornada);
    	}
    	
    	return faixas.get(groupPosition).size();
    }
    
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    	if (!faixas.containsKey(groupPosition)) {
    		ArtistAlbumEntity album = (ArtistAlbumEntity)getGroup(groupPosition);
    		List<FaixaMusicaEntity> faixasRetornada = obterLitaDeMusicas(album.getCantorNome(), album.getName());
    		faixas.put(groupPosition, faixasRetornada);
    	}
    	
    	if (convertView == null) {
        	LayoutInflater inflaInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	convertView = inflaInflater.inflate(R.layout.rowtrack, null);
        }
        TextView textMusica = (TextView)convertView.findViewById(R.id.track_name);
        TextView textDuracao = (TextView)convertView.findViewById(R.id.duration);
        textMusica.setText((String)((FaixaMusicaEntity)getChild(groupPosition, childPosition)).getNome());
        
        String duracao = (String)((FaixaMusicaEntity)getChild(groupPosition, childPosition)).getDuracao();
        textDuracao.setText(converterTempo(duracao));
        return convertView;
    }
    
    private String converterTempo(String duracao) {
    	Double tempo = Double.parseDouble(duracao);
    	tempo = tempo / 60;
    	
    	String tempTempo = tempo.toString();
    	String tempMinutos = tempTempo.substring(tempTempo.indexOf(".") + 1, tempTempo.length());
    	String tempHoras = tempTempo.substring(0, tempTempo.indexOf("."));
    	
    	String tempMinutos2 = (tempMinutos.length() < 2) ? tempMinutos+"0" : tempMinutos;
    	tempMinutos2 = tempMinutos2.substring(0, 2);
    	
    	Double minutos = Double.parseDouble(tempMinutos2);
    	
    	minutos = 60 * (minutos / 100);
    	String retornoMinuto = minutos.toString().substring(0, minutos.toString().indexOf("."));
    	return tempHoras + ":" + (retornoMinuto.length() < 2 ? "0" + retornoMinuto : retornoMinuto);
    }
    
    @Override
    public Object getGroup(int groupPosition) {
        return albuns.get(groupPosition);
    }
    
    public Object getGroupUrl(int groupPosition) {
        return albuns.get(groupPosition).getUrl();
    }
    
    @Override
    public int getGroupCount() {
        return albuns.size();
    }
    
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String albumNome = (String)((ArtistAlbumEntity)getGroup(groupPosition)).getName();
        
    	if (convertView == null) {
        	LayoutInflater inflaInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	convertView = inflaInflater.inflate(R.layout.groupalbum, null);
        }
    	
        TextView textEventos = (TextView)convertView.findViewById(R.id.album_name);
        textEventos.setText((albumNome.length() > 25) ? albumNome.substring(0, 25) + "..." : albumNome);
        
        AlbumAsync albumAsyn = new AlbumAsync((ImageView)convertView.findViewById(R.id.album_cover));
        albumAsyn.execute((String)getGroupUrl(groupPosition));
        
        return convertView;
        
    }

	public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }

	private List<FaixaMusicaEntity> obterLitaDeMusicas(String cantor, String albumNome) {
		List<FaixaMusicaEntity> listaDeMusicas = null;
		listaDeMusicas = LastFMFacade.obterListaDeMusicas(cantor, albumNome);
		return listaDeMusicas;
	}
	
	public class AlbumAsync extends AsyncTask<String, Void, Bitmap> {
		
		private final ImageView imageView;
		
		public AlbumAsync(ImageView imageView) {
			this.imageView = imageView;
		}
		
		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap bitmap = null;
			for (String url : urls) {
				System.out.println("URL = "+url);
				bitmap = obterImagemDaCapa(url);
			}
			
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			imageView.setImageBitmap(bitmap);
		}
		
		private Bitmap obterImagemDaCapa(String groupUrl) {
			Bitmap bitmap = null;
			bitmap = LastFMFacade.obterImagem(groupUrl);
	    	
			return bitmap;
		}

	}
}