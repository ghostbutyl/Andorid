package com.example.shujucunchu;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.conn.scheme.LayeredSocketFactory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	ArrayList<HashMap<String, Object>>list=new ArrayList<HashMap<String,Object>>();

	public MyAdapter(Context context, ArrayList<HashMap<String, Object>>list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoller v;
		if(convertView == null){
			v = new ViewHoller();
			convertView = LayoutInflater.from(context).inflate(R.layout.items, null);
			v.idh = (TextView) convertView.findViewById(R.id.idh);
			v.name = (TextView) convertView.findViewById(R.id.name);
			v.age = (TextView) convertView.findViewById(R.id.age);
			convertView.setTag(v);
		}else{
			v = (ViewHoller) convertView.getTag();
		}
		if(getCount()!=0){
			v.idh.setText(list.get(position).get("idh").toString());
			v.name.setText(list.get(position).get("name").toString());
			v.age.setText(list.get(position).get("age").toString());
		}
		return convertView;
	}

}
