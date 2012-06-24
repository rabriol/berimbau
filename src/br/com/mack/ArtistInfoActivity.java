package br.com.mack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import br.com.mack.entity.ArtistInfoEntity;

public class ArtistInfoActivity extends Activity {
	private ArtistInfoEntity artistInfo;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.artistinfo);
		
		Intent it = getIntent();
		TextView text = (TextView)findViewById(R.id.text_bio);
		artistInfo = (ArtistInfoEntity)it.getSerializableExtra("info");
		text.setText(artistInfo.getContent());
	}
	
}
