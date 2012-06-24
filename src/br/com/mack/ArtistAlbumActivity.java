package br.com.mack;

import java.util.ArrayList;
import java.util.List;

import br.com.mack.adapter.ExpandableArtistAlbumAdapter;
import br.com.mack.entity.ArtistAlbumEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class ArtistAlbumActivity extends Activity {
	private List<ArtistAlbumEntity> listAlbuns;
	private ExpandableListAdapter artistiAlbumAdapter;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artistalbuns);
		
		Intent it = getIntent();
		listAlbuns = (ArrayList<ArtistAlbumEntity>)it.getSerializableExtra("albuns");
		
		artistiAlbumAdapter = new ExpandableArtistAlbumAdapter(this, listAlbuns);
		
		ExpandableListView expList = (ExpandableListView)findViewById(R.id.expandable_list_albuns);
		expList.setAdapter(artistiAlbumAdapter);
		registerForContextMenu(expList);
	}
}
