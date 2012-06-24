package br.com.mack;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.mack.R;
import br.com.mack.R.id;
import br.com.mack.R.layout;
import br.com.mack.entity.ArtistAlbumEntity;
import br.com.mack.entity.ArtistEventoEntity;
import br.com.mack.entity.ArtistInfoEntity;
import br.com.mack.web.ConexaoLastFm;
import br.com.mack.web.LastFMFacade;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity implements OnClickListener{
	
	private Button button_search;
	private EditText texto_busca;
	private ArtistInfoEntity artistInfo;
	private List<ArtistEventoEntity> listEventos = null;
	private List<ArtistAlbumEntity> listAlbuns = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		button_search = (Button)findViewById(R.id.button_search);
		button_search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		texto_busca = (EditText)findViewById(R.id.text_search);
		
		final ProgressDialog progressDialog = ProgressDialog.show(this, "Pesquisando", "Aguarde enquanto a busca Ž realizada...", true);
		Thread backGround = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					artistInfo = LastFMFacade.obterBioArtista(texto_busca.getText().toString());
					listEventos = LastFMFacade.obterEventosArtista(texto_busca.getText().toString());
					listAlbuns = LastFMFacade.obterAlbumArtista(texto_busca.getText().toString());
					runOnUiThread(new Runnable() {                    
		                @Override
		                public void run() {
		                    progressDialog.dismiss();                        
		                }
		            });
					handler.sendEmptyMessage(MODE_PRIVATE);
				} catch (Exception e) {
					//vou colcar aqui um aviso ao usuaio se der erro
					e.printStackTrace();
					
				}
			}
		});
		backGround.start();
		
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Intent it = new Intent(SearchActivity.this, MainPageActivity.class);
			it.putExtra("info", artistInfo);
			it.putExtra("eventos", (ArrayList<ArtistEventoEntity>)listEventos);
			it.putExtra("albuns", (ArrayList<ArtistAlbumEntity>)listAlbuns);
			
			startActivity(it);
		}
	};
}
