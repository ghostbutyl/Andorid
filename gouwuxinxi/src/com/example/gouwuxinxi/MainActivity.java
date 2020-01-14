package com.example.gouwuxinxi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
private ListView listv;
private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*lv1=(ListView) findViewById(R.id.lv1);
		adapter=new MyAdapter(this);
		lv1.setAdapter(adapter);*/
		listv=(ListView) findViewById(R.id.lv1);
		ArrayList<HashMap<String, Object>>list=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map1=new HashMap<String, Object>();
		HashMap<String, Object>map2=new HashMap<String, Object>();
		HashMap<String, Object>map3=new HashMap<String, Object>();
		HashMap<String, Object>map4=new HashMap<String, Object>();
		
		map1.put("Dno","20172123");
		map1.put("money", 168);
		map1.put("zt", "未发货");
		map1.put("time", "2019-9-9");
		map1.put("cz", "");
		list.add(map1);
		map2.put("Dno", "20172113054");
		map2.put("money", 170);
		map2.put("zt", "已发货");
		map2.put("time","2018-8-8");
		map2.put("cz", "");
		list.add(map2);
		 adapter=new MyAdapter(this,list);
		listv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
