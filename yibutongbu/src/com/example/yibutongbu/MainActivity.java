package com.example.yibutongbu;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	private Button btn;
	private ProgressBar pr;
	private TextView tv;
	Handler handler = new Handler() {

		@SuppressLint("SimpleDateFormat")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				tv.setText("异步开始:"+ simpleDateFormat.toString());
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
		pr = (ProgressBar) findViewById(R.id.pr1);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				progressBartask p1 = new progressBartask(tv, pr);
				p1.execute(1000);
				new Thread() {
					boolean exit = false;

					@Override
					public void run() {
						while (!exit) {
							try {
								Thread.sleep(1000);
								Message msg = new Message();
								msg.what = 1;// 消息(一个整型值)
								handler.sendMessage(msg);// 每隔1秒发送一个msg给handerl
								if (pr.getProgress() >= 100) {
									exit = true;
								}
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
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
