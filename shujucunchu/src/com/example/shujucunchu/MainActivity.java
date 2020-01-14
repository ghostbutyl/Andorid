package com.example.shujucunchu;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.app.Activity;
import android.app.AliasActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

        private mydb mdb;
    	private SQLiteDatabase db;
    	private EditText ed1,ed2;
    	private Button btn1,btn2,btn3,btn4;
    	private ListView lv;
    	private Cursor c;
    	private MyAdapter mAdapter;

    	protected void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_main);
    		
    		mdb = new mydb(this);//创建数据库mydb.db
    		db = mdb.getWritableDatabase();
    		ed1 = (EditText) findViewById(R.id.ed1);
    		ed2 = (EditText) findViewById(R.id.ed2);
    		btn1 = (Button) findViewById(R.id.btn1);
    		btn2 = (Button) findViewById(R.id.btn2);
    		btn3 = (Button) findViewById(R.id.btn3);
    		btn4 = (Button) findViewById(R.id.btn4);
    		lv = (ListView) findViewById(R.id.lv);
    		ArrayList<HashMap<String,Object>>list = new ArrayList<HashMap<String,Object>>();
    		
    		c = mydb.getInstance(getApplicationContext()).getWritableDatabase().rawQuery("SELECT id,name,age FROM user", null);
    		mAdapter = new MyAdapter(getApplicationContext(),query(c));
			lv.setAdapter(mAdapter);
    		
    		//增加
    		btn1.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				String name = ed1.getText().toString();
    				String age = ed2.getText().toString();
    				if(name!=""&&age!=""){
    					db.execSQL("insert into user (name,age) values(?,?)", new Object[]{name,Integer.parseInt(age)});
    					ed1.setText("");
    					ed2.setText("");
    					Toast.makeText(MainActivity.this, "添加成功", 1).show();
    					c = db.rawQuery("SELECT id,name,age FROM user", null);
    					mAdapter = new MyAdapter(getApplicationContext(),query(c));
    					lv.setAdapter(mAdapter);
    				}else{
    					Toast.makeText(MainActivity.this, "请输入值", 1).show();
    				}
    			}
    		});
    		
    		//查询
    		btn2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					String name = ed1.getText().toString();
    				String age = ed2.getText().toString();
    				
    				if(!name.equals("") && age.equals("")){
						c = db.rawQuery("select id,name,age from user where name=?", new String[]{name});
    				}
    				else if(name.equals("") && !age.equals("")){
    					c = db.rawQuery("select id,name,age from user where age=?", new String[]{age});
    				}
    				else if(!name.equals("") && !age.equals("")){
    					c = db.rawQuery("select id,name,age from user where name=? and age=?", new String[]{name,age});
    				}
    				else if(name.equals("") && age.equals("")){
    					c = db.rawQuery("select * from user", null);
    				}
					mAdapter = new MyAdapter(getApplicationContext(),query(c));
					lv.setAdapter(mAdapter);
				}
			});
    		
    		//修改
    		btn3.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				String name = ed1.getText().toString();
    				String age = ed2.getText().toString();
    				if(name!=""&&age!=""){
    					db.execSQL("update user set age=? where name=?", new Object[]{Integer.parseInt(age),name});
    					Toast.makeText(MainActivity.this, "修改成功", 1).show();;
    					ed1.setText("");
    					ed2.setText("");
    					c = db.rawQuery("SELECT id,name,age FROM user", null);
    					mAdapter = new MyAdapter(getApplicationContext(),query(c));
    					lv.setAdapter(mAdapter);
    				}else{
    					Toast.makeText(MainActivity.this, "请输入值", 1).show();;
    				}
    			}
    		});
    		
    		//删除
    		btn4.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				if(ed1.getText().toString()!="")
    				{
    					db.execSQL("delete from user where name=?", new Object[]{ed1.getText().toString()});
    					//db.delete("user", whereClause, whereArgs)
    					ed1.setText("");
    					Toast.makeText(MainActivity.this, "删除成功", 1).show();
    					c = db.rawQuery("SELECT id,name,age FROM user", null);
    					mAdapter = new MyAdapter(getApplicationContext(),query(c));
    					lv.setAdapter(mAdapter);
    				}else{
    					Toast.makeText(MainActivity.this, "请输入name", 1).show();
    				}
    			}
    		});
    	
    	}

    public ArrayList<HashMap<String,Object>> query(Cursor c){
    	ArrayList<HashMap<String,Object>>list = new ArrayList<HashMap<String,Object>>();
    	if(c != null && c.getCount()>0){
			while(c.moveToNext()){
				HashMap<String,Object>map = new HashMap<String,Object>();
				map.put("idh", c.getInt(0));
				map.put("name", c.getString(1));
				map.put("age", c.getInt(2));
				list.add(map);
			}
		}
		return list;
    }
    	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_mail, container, false);
            return rootView;
        }
    }

}