package br.com.mack;

import br.com.mack.R;
import br.com.mack.dao.PersistDao;
import br.com.mack.entity.ArtistInfoEntity;
import br.com.mack.web.LastFMFacade;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainPageActivity extends TabActivity {
	
	private TabHost tabHost;
	private Bitmap bitmap;
	private ArtistInfoEntity artistBio;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final PersistDao dao = new PersistDao(this);
		
		tabHost = getTabHost();
		TabHost.TabSpec spec;
		
		Intent it1 = getIntent();
		
		ImageView image = (ImageView)findViewById(R.id.artist_photo);
		artistBio = (ArtistInfoEntity)it1.getSerializableExtra("info");
		bitmap = LastFMFacade.obterImagem(artistBio.getUrl());
		image.setImageBitmap(bitmap);
		TextView artistName = (TextView)findViewById(R.id.artist_name);
		artistName.setText(artistBio.getName());
		
		Intent intent = new Intent(this, ArtistInfoActivity.class);
		intent.putExtra("info", it1.getSerializableExtra("info"));
		spec = tabHost.newTabSpec("Biografia").setIndicator("biografia").setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ArtistEventActivity.class);
		intent.putExtra("eventos", it1.getSerializableExtra("eventos"));
		spec = tabHost.newTabSpec("Eventos").setIndicator("eventos").setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this, ArtistAlbumActivity.class);
		intent.putExtra("albuns", it1.getSerializableExtra("albuns"));
		spec = tabHost.newTabSpec("Albuns").setIndicator("albuns").setContent(intent);
		tabHost.addTab(spec);
		
		ImageButton imageButton = (ImageButton)findViewById(R.id.favoritos_salvar);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PersistDao.salvar(artistBio.getName());
			}
		});
	}
}
