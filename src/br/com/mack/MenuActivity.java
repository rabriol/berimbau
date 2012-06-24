package br.com.mack;

import br.com.mack.R;
import br.com.mack.R.id;
import br.com.mack.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuActivity extends Activity implements OnClickListener{
	
	ImageButton imageB1 = null;
    ImageButton imageB2 = null;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menu);
	    
	    imageB1 = (ImageButton)findViewById(R.id.ImageButton1);
	    imageB2 = (ImageButton)findViewById(R.id.ImageButton2);
	    imageB1.setOnClickListener(this);
	    imageB2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (imageB1.getId() == ((ImageButton)v).getId()) {
			Intent it = new Intent(this, SearchActivity.class);
			startActivity(it);
		} else if (imageB2.getId() == ((ImageButton)v).getId()) {
			Intent it = new Intent(this, FavoriteActivity.class);
			startActivity(it);
		}	
	}
}
