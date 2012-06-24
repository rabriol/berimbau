package br.com.mack.adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.mack.R;
import br.com.mack.entity.ArtistEventoEntity;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableArtistEventAdapter extends BaseExpandableListAdapter{
	private Context context;
	private List<ArtistEventoEntity> eventos;
	
    public ExpandableArtistEventAdapter(Context context, List<ArtistEventoEntity> eventos) {
    	this.context = context;
    	this.eventos = eventos;
    }
    
    public Object getChild(int groupPosition, int childPosition) {
    	return formatarInfoEvento(eventos.get(groupPosition));
    }

    private Object formatarInfoEvento(ArtistEventoEntity artistEventoEntity) {
		return null;
	}

	public long getChildId(int groupPosition, int childPosition) {
    	return 1L;
    }

    public int getChildrenCount(int groupPosition) {
        return 1;
    }
    
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = null;
        textView.setText("");
        return textView;
    }
    
    @Override
    public Object getGroup(int groupPosition) {
        return eventos.get(groupPosition).getNomeEventos();
    }
    
    public Object getGroupDetail(int groupPosition) {
        return eventos.get(groupPosition).getPais()
        		+ " " +
        		eventos.get(groupPosition).getComecaDia();
    }
    
    @Override
    public int getGroupCount() {
        return eventos.size();
    }
    
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String evento = (String)getGroup(groupPosition);
        String detalhe = (String)getGroupDetail(groupPosition);
    	if (convertView == null) {
        	LayoutInflater inflaInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	convertView = inflaInflater.inflate(R.layout.group_bio, null);
        }
        TextView textEventos = (TextView)convertView.findViewById(R.id.evento_name);
        TextView textDetalhes = (TextView)convertView.findViewById(R.id.evento_detail);
        textEventos.setText((evento.length() > 25) ? evento.substring(0, 25) + "..." : evento);
        textDetalhes.setText(detalhe);
        return convertView;
        
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }

    }