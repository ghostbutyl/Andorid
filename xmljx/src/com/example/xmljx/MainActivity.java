package com.example.xmljx;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	// 1.在Assets文件夹中模拟创建XML文件
	// 2.创建对应XML的Bean对象
	// 3.解析
	private Button btn1, btn2, btn3;
	private TextView tv;
	private XmlUtils xmlUtils;
	private List<Student> students;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		tv = (TextView) findViewById(R.id.tv);
		xmlUtils = new XmlUtils();
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 try {
					students = xmlUtils.dom2xml(getResources().getAssets().open("students.xml"));
					  tv.setText(students.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               

			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 try {
					students = xmlUtils.sax2xml(getResources().getAssets().open("students.xml"));
					  tv.setText(students.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               

			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				  try {
					students = xmlUtils.pull2xml(getResources().getAssets().open("students.xml"));
					tv.setText(students.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  

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
