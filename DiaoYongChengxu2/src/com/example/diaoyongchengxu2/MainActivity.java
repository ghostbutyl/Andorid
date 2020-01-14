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
			// 打开网页
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse(str);
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			// 直接拨打电话
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse("tel:" + str);
				Intent it = new Intent(Intent.ACTION_CALL, uri);
				startActivity(it);

			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			// 短信内容
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
			// 显示地图
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Uri uri = Uri.parse("geo:" + str);// 22.427533,113.377951
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});

		btn5.setOnClickListener(new OnClickListener() {
			// 播放多媒体
			@Override
			public void onClick(View arg0) {
				String str = ed1.getText().toString();
				Intent it = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.parse("file://存储盘/Download10 How You Get The Girl.mp3");
				it.setDataAndType(uri, "audio/mp3");
				startActivity(it);
			}
		});

		btn6.setOnClickListener(new OnClickListener() {
			// 规划路径
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
			// 打开相机
			@Override
			public void onClick(View arg0) {
				Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
				getImage.addCategory(Intent.CATEGORY_OPENABLE);
				getImage.setType(MIME_TYPE_IMAGE_JPEG);
				startActivityForResult(getImage, ACTIVITY_GET_IMAGE);

				// * 指定存储路径为存储盘上，文件名为picture.jpg
				imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
				Log.v("imageFilePath", imageFilePath);
				Uri imageUri = Uri.fromFile(new File(imageFilePath));
				/**
				 * 或者 Uri imageUri = Uri.parse("file:///存储盘/DCIM/picture.jpg");
				 * /在不同的机型上SD卡的路径可能不一样，因此不推荐此种写法
				 **/
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 指定文件存储位置
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

			// 获取屏幕的宽高
			Display currentDisplay = getWindowManager().getDefaultDisplay();

			Point point = new Point();
			currentDisplay.getSize(point);

			// 加载图像的尺寸而不是图像本身
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath, options);
			int widthRatio = (int) Math.ceil(options.outWidth / (float) point.x);
			int heightRatio = (int) Math.ceil(options.outHeight / (float) point.y);

			Log.v("HEIGHTRATIO", "" + heightRatio);
			Log.v("WIDTHRATIO", "" + widthRatio);

			// 如果两个比例都大于1，那么图像的一条边将大于屏幕
			if (heightRatio > 1 && widthRatio > 1) {
				options.inSampleSize = Math.max(heightRatio, widthRatio);
			}

			// 对它进行真正的解码
			options.inJustDecodeBounds = false; // 此处为false，不只是解码
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
