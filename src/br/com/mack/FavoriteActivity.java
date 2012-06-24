package br.com.mack;

import br.com.mack.adapter.ListFavoritesAdapter;
import br.com.mack.dao.PersistDao;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

public class FavoriteActivity extends ListActivity {
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.favorites);
		
		PersistDao dao = new PersistDao(this);
		
		String[] resultado = dao.pesquisarTodos();
		ListFavoritesAdapter favoritesAdapter = new ListFavoritesAdapter(this, resultado);
		setListAdapter(favoritesAdapter);
	}
}
