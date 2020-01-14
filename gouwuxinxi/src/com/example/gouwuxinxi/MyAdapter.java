package com.example.gouwuxinxi;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Text;

import android.R.string;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
private Context context;
ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	public MyAdapter(Context context,ArrayList<HashMap<String, Object>> list) {
		this.context=context;
		this.list=list;
	}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position,View convertView,ViewGroup parent) {
		ViewHoller v=null;
		if(convertView==null){
			v = new ViewHoller();
			convertView = LayoutInflater.from(context).inflate(R.layout.itse, null);
			v.Dno = (TextView) convertView.findViewById(R.id.tv1);
			v.Mount = (TextView) convertView.findViewById(R.id.tv2);
			v.State = (TextView) convertView.findViewById(R.id.tv3);
			v.Time = (TextView) convertView.findViewById(R.id.tv4);
			v.Operate = (TextView) convertView.findViewById(R.id.tv5);
			convertView.setTag(v);
		}else{
			v = (ViewHoller) convertView.getTag();
		}
		v.Dno.setText( list.get(position).get("Dno").toString());
		v.Mount.setText(list.get(position).get("money").toString());
		v.State.setText( list.get(position).get("zt").toString());
		v.Time.setText(list.get(position).get("time").toString());
		v.Operate.setText( list.get(position).get("cz").toString()) ;
		/*v.Dno.setText("201508281043");
		v.Mount.setText("168");
		v.State.setText("ÒÑ·¢»õ");
		v.Time.setText("2015-08-28 10:43");
		v.Operate.setText("");*/
		return convertView;
	}

}
