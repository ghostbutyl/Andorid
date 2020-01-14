package com.example.denglujiemain;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {
	private Button btn;
	private TextView tv1, tv2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		btn = (Button) findViewById(R.id.btn1);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		Intent intent = getIntent();
		tv1.setText(intent.getStringExtra("sendinfo1"));
		tv2.setText(intent.getStringExtra("sendinfo2"));
		btn.setOnClickListener(new OnClickListener() {  //确定跳转回送页面
			
			@Override
			public void onClick(View arg0) {
			String rtu="我收到你发来的帐号:"+tv1.getText();
			getIntent().putExtra("returninfo", rtu);
			setResult(1,getIntent());
			finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
