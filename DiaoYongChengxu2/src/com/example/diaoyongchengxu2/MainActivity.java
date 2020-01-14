package com.example.diaoyongchengxu2;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.app.Activity;
import android.view.Menu;
import java.io.File;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Point;
import android.media.CameraProfile;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
@SuppressLint("NewApi")
public class MainActivity extends Activity {
	protected static final String Camera = null;
	private EditText ed1;
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	final static int CAMERA_RESULT = 0;
	private ImageView img;
	private String imageFilePath;
	public static final String MIME_TYPE_IMAGE_JPEG = "image/*";
	public static final int ACTIVITY_GET_IMAGE = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed1 = (EditText) findViewById(R.id.ed1);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		img = (ImageView) findViewById(R.id.picture);
		btn1.setOnClickListener(new OnClickListener() {
			// ����ҳ
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse(str);
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			// ֱ�Ӳ���绰
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse("tel:" + str);
				Intent it = new Intent(Intent.ACTION_CALL, uri);
				startActivity(it);

			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			// ��������
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse("smsto:10086");
				Intent it = new Intent(Intent.ACTION_SENDTO, uri);
				it.putExtra("sms_body", str);
				startActivity(it);

			}
		});

		btn4.setOnClickListener(new OnClickListener() {
			// ��ʾ��ͼ
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse("geo:" + str);// 22.427533,113.377951
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});

		btn5.setOnClickListener(new OnClickListener() {
			// ���Ŷ�ý��
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Intent it = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.parse("file://�洢��/Download10 How You Get The Girl.mp3");
				it.setDataAndType(uri, "audio/mp3");
				startActivity(it);
			}
		});

		btn6.setOnClickListener(new OnClickListener() {
			// �滮·��
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse(
						"https://map.baidu.com/dir/%E5%B9%BF%E4%B8%9C%E7%90%86%E5%B7%A5%E8%81%8C%E4%B8%9A%E5%AD%A6%E9%99%A2/%E5%9F%8E%E8%BD%A8%E4%B8%AD%E5%B1%B1%E5%8C%97%E7%AB%99/@12617726.120000001,2555055.2800000003,13z?querytype=bt&c=187&sn=2$$$$$$%E5%B9%BF%E4%B8%9C%E7%90%86%E5%B7%A5%E8%81%8C%E4%B8%9A%E5%AD%A6%E9%99%A2$$0$$$$&en=2$$$$$$%E5%9F%8E%E8%BD%A8%E4%B8%AD%E5%B1%B1%E5%8C%97%E7%AB%99-%E5%85%AC%E4%BA%A4%E8%BD%A6%E7%AB%99$$1$$%E4%B8%AD%E5%B1%B1%E5%B8%82%E5%B8%82%E8%BE%96%E5%8C%BA$$&sc=187&ec=187&pn=0&rn=5&exptype=dep&exptime=2019-04-17%2019:42&version=5&da_src=shareurl");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});

		btn7.setOnClickListener(new OnClickListener() {
			// �����
			@Override
			public void onClick(View arg0) {
				Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
				getImage.addCategory(Intent.CATEGORY_OPENABLE);
				getImage.setType(MIME_TYPE_IMAGE_JPEG);
				startActivityForResult(getImage, ACTIVITY_GET_IMAGE);

				// * ָ���洢·��Ϊ�洢���ϣ��ļ���Ϊpicture.jpg
				imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
				Log.v("imageFilePath", imageFilePath);
				Uri imageUri = Uri.fromFile(new File(imageFilePath));
				/**
				 * ���� Uri imageUri = Uri.parse("file:///�洢��/DCIM/picture.jpg");
				 * /�ڲ�ͬ�Ļ�����SD����·�����ܲ�һ������˲��Ƽ�����д��
				 **/
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// ָ���ļ��洢λ��
				startActivityForResult(i, CAMERA_RESULT);

			}
		});
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
//          Bundle extras = data.getExtras();
//          Bitmap bitmap = (Bitmap) extras.get("data");
			img = (ImageView) findViewById(R.id.image);

			// ��ȡ��Ļ�Ŀ��
			Display currentDisplay = getWindowManager().getDefaultDisplay();

			Point point = new Point();
			currentDisplay.getSize(point);

			// ����ͼ��ĳߴ������ͼ����
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath, options);
			int widthRatio = (int) Math.ceil(options.outWidth / (float) point.x);
			int heightRatio = (int) Math.ceil(options.outHeight / (float) point.y);

			Log.v("HEIGHTRATIO", "" + heightRatio);
			Log.v("WIDTHRATIO", "" + widthRatio);

			// �����������������1����ôͼ���һ���߽�������Ļ
			if (heightRatio > 1 && widthRatio > 1) {
				options.inSampleSize = Math.max(heightRatio, widthRatio);
			}

			// �������������Ľ���
			options.inJustDecodeBounds = false; // �˴�Ϊfalse����ֻ�ǽ���
			bitmap = BitmapFactory.decodeFile(imageFilePath, options);

			img.setImageBitmap(bitmap);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
