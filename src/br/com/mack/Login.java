package br.com.mack;

import br.com.mack.R;
import br.com.mack.R.id;
import br.com.mack.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener{
	
	Button botao_login;
	EditText name, password;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        botao_login = (Button)findViewById(R.id.button1);
        botao_login.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		name = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		
		if (name != null) {
			Intent intent = new Intent(this, SearchActivity.class);
			startActivity(intent);
		}
	}   
}