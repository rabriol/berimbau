package br.com.mack.adapter;

import java.util.List;

import br.com.mack.R;
import br.com.mack.entity.ArtistEventoEntity;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListArtistEventAdapter extends ArrayAdapter<ArtistEventoEntity> {
	private final Activity context;
	private List<ArtistEventoEntity> eventos;

	public ListArtistEventAdapter(Activity context, List<ArtistEventoEntity> eventos) {
		super(context, R.layout.group_bio, eventos);
		this.context = context;
		this.eventos = eventos;
	}

	public Object getGroup(int groupPosition) {
        return eventos.get(groupPosition).getNomeEventos();
    }
    
    public Object getGroupDetail(int groupPosition) {
        return eventos.get(groupPosition).getPais()
        		+ " " +
        		eventos.get(groupPosition).getComecaDia();
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = context.getLayoutInflater();
		View view = inflator.inflate(R.layout.group_bio, null);
		TextView textEventos = (TextView)view.findViewById(R.id.evento_name);
        TextView textDetalhes = (TextView)view.findViewById(R.id.evento_detail);
        
        String nomeEvento = ((String)getGroup(position));
        String detalhe = ((String)getGroupDetail(position));
        
        textEventos.setText(nomeEvento.length() > 28 ? nomeEvento.substring(0, 30) + "..." : nomeEvento);
        textDetalhes.setText(detalhe);
        
        view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("VAI 1");
			}
		});
        
		return view;
	}
}
