package com.example.denglujiemain;

import android.os.Bundle;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btn;
	private EditText ed1, ed2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn1);
		ed1 = (EditText) findViewById(R.id.ed1);
		ed2 = (EditText) findViewById(R.id.ed2);
		btn.setOnClickListener(new OnClickListener() {  //设置监听按键动作

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				String info1 = ed1.getText().toString();
				String info2 = ed2.getText().toString();
				intent.putExtra("sendinfo1", info1);
				intent.putExtra("sendinfo2", info2);
				startActivityForResult(intent, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(requestCode==1&&resultCode==1) {
			String info=intent.getStringExtra("returninfo");
			ed1.setText(info);
		}
		super.onActivityResult(requestCode, resultCode,intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
