package com.example.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	// ������ر���,��ɳ�ʼ��
	private TextView textView;
	private EditText editsend;
	private Button btnsend;
	private static final String HOST = "10.32.157.135";
	private static final int PORT = 9090;
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String content = "";
	private StringBuilder sb = null;
	// ����һ��handler����,����ˢ�½���
	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				//Bundle bundle = msg.getData();
				sb.append(content);
				textView.setText(sb.toString());
				editsend.setText("");
				//textView.append("server:" + bundle.getString("msg") + "\n");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sb = new StringBuilder();
		
		textView = (TextView) findViewById(R.id.textView);
		editsend = (EditText) findViewById(R.id.editsend);
		btnsend = (Button) findViewById(R.id.btnsend);
		
		// ������һ��ʼ���е�ʱ���ʵ����Socket����,�����˽�������,��ȡ���������
		// ��Ϊ4.0�Ժ��������߳��н����������,������Ҫ���⿪��һ���߳�
		new Thread() {

			public void run() {
				try {
					socket = new Socket(HOST, PORT);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		// Ϊ���Ͱ�ť���õ���¼�
		btnsend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String msg = editsend.getText().toString().trim();
				if (socket.isConnected()) {
					if (!socket.isOutputShutdown()) {
						out.println(msg);
						//if (TextUtils.isEmpty(msg)) {
						//	Toast.makeText(MainActivity.this, "���ݲ���Ϊ��", 1).show();
					//}
				}
					//textView.append("client:" + textView.toString() + "\n");
					textView.setText("");
					
				}
			}
		});
		new Thread() {

			@Override
			public void run() {
				try {
					while (true) {
						if (socket.isConnected()) {
							if (!socket.isInputShutdown()) {
								if ((content = in.readLine()) != null) {
									content += "\n";
									handler.sendEmptyMessage(0x123);
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
