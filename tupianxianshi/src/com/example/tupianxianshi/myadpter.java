package com.example.tupianxianshi;

import android.R.string;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myadpter extends BaseAdapter {
private Context context;
private int[] imagid={R.drawable.fd,R.drawable.fd2,R.drawable.fd3,R.drawable.fd4};
private String[] stringid={"Æ¤¿¨Çð","Æ¤¿¨Çð","Æ¤¿¨Çð","ÁúÃ¨"};
	public myadpter( Context context) {
		this.context=context;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagid.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return imagid[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		viewholler v=null;
		if(convertview==null){
			convertview=LayoutInflater.from(context).inflate(R.layout.item,null);
			v=new viewholler();
			v.img=(ImageView) convertview.findViewById(R.id.img1);
			v.tv=(TextView) convertview.findViewById(R.id.tv1);
			convertview.setTag(v);
		}
		else {
			v=(viewholler) convertview.getTag();
		}
		v.img.setImageResource(imagid[position]);
		v.tv.setText(stringid[position]);
		return convertview;
	}

}
