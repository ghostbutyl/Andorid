package com.example.showsystemtime;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btn;
	private TextView tv;

	Handler handle = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				tv.setText("系统时间:" + msg.obj.toString());
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn);
		tv = (TextView) findViewById(R.id.tv);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new Thread() {

					@Override
					public void run() {
						while (true) {
							Message msg = handle.obtainMessage(1,
									new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
							handle.sendMessage(msg);
						}
					}
				}.start();

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
