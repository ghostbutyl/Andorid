package com.example.yibutongbu;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class progressBartask extends AsyncTask<Integer, Integer, String> {
	private TextView tv1;
	private ProgressBar pr1;

	public progressBartask(TextView tv1, ProgressBar pr1) {
		this.pr1 = pr1;
		this.tv1 = tv1;
	}

	@Override
	protected void onPostExecute(String result) {
		tv1.setText("�첽ִ�н���" );

	}

	@Override
	protected void onPreExecute() {
		tv1.setText("�첽ִ�п�ʼ");
	}

	// ���߳�
	@Override
	protected void onProgressUpdate(Integer... values) {
		int value = values[0];
		pr1.setProgress(value);

	}

	@Override
	protected String doInBackground(Integer... arg0) {
		netoprate op = new netoprate();
		int i = 0;
		for (i = 0; i <= 100; i+=10) {
			op.nete();
			publishProgress(i);// �ɼ��������ݣ�ͨ���÷�������onProgressupdate
		}
		return i + arg0[0].intValue() + "";
	}

}
