package br.com.mack.adapter;

import br.com.mack.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListFavoritesAdapter extends ArrayAdapter<String>{
	private final Activity context;
	private String[] query;

	public ListFavoritesAdapter(Activity context, String[] query) {
		super(context, R.layout.favorites, query);
		this.context = context;
		this.query = query;
	}

	public Object getGroup(int groupPosition) {
        return query[groupPosition];
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = context.getLayoutInflater();
		View view = inflator.inflate(R.layout.favorites, null);
		TextView textFavoritos = (TextView)view.findViewById(R.id.item_favorite);
        
        textFavoritos.setText(query[position]);
		return view;
	}
}
