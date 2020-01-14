package com.example.jsonjx;

import java.io.IOException;
import java.io.StringReader;

import com.google.gson.Gson;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.util.JsonReader;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
private Button btn,btn1;
private TextView tv;
//String jsondata="[{\"name\":\"mike\",\"age\":20},{\"name\":\"edson\",\"age\":21}]";
String jsondata="{\"name\":\"mike\",\"age\":\"20\"}";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button) findViewById(R.id.btn);
		tv=(TextView) findViewById(R.id.tv);
		btn.setOnClickListener(new OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
			/*	JsonReader reader=new JsonReader(new StringReader(jsondata));
				try {
					reader.beginArray();
					while(reader.hasNext()){
						reader.beginObject();
						while(reader.hasNext()){
						String tname=reader.nextName();
						if(tname.equals("name")){
							tv.setText(tv.getText()+reader.nextString()+" ");
						}
						else if(tname.equals("age")){
							tv.setText(tv.getText()+" "+reader.nextInt()+" ");
						}
					}
						reader.endObject();
					}
					reader.endArray();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				Gson gson=new Gson();
				user us=gson.fromJson(jsondata, user.class);
				tv.setText(tv.getText()+us.getname()+" "+us.getage()+" ");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
