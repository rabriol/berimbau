package br.com.mack;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import br.com.mack.adapter.ExpandableArtistEventAdapter;
import br.com.mack.adapter.ListArtistEventAdapter;
import br.com.mack.entity.ArtistEventoEntity;

public class ArtistEventActivity extends ListActivity {
	private List<ArtistEventoEntity> listEventos;
	private ListArtistEventAdapter artistiEventAdapter;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.artistevents);
		
		Intent it = getIntent();
		listEventos = (ArrayList<ArtistEventoEntity>)it.getSerializableExtra("eventos");
		
		ListArtistEventAdapter artistiEventAdapter = new ListArtistEventAdapter(this, listEventos);
		setListAdapter(artistiEventAdapter);

	}
}
