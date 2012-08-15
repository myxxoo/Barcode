package com.dlvct.barcode;

import java.util.ArrayList;
import java.util.Map;

import com.dlvct.utils.db.DataHelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateAttribute extends Activity {
	private LinearLayout listView;
	private Button saveBtn;
	private Spinner spinner;
	private LayoutInflater inflater;
	private ArrayList<String> data = new ArrayList<String>();
	
	private ArrayAdapter<String> spinnerAdapter;
	private ArrayList<Map<String,String>> type = new ArrayList<Map<String,String>>();
	private ArrayList<String> typeList = new ArrayList<String>();
	private String typeId = "1";
	private DataHelper dataHelper;
	private Handler handler;
	
	private final int LOAD_TYPE_FINISH = 10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_new_attribute);
		initView();
		loadType.start();
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case LOAD_TYPE_FINISH:
					spinnerAdapter.notifyDataSetChanged();
					break;

				default:
					break;
				}
			}
		};
	}

	private void initView() {
		inflater = getLayoutInflater();
		dataHelper = new DataHelper(this);
		spinnerAdapter = new ArrayAdapter<String>(CreateAttribute.this,R.layout.spinner_item,typeList);
		
		listView = (LinearLayout) findViewById(R.id.create_new_attribute_list);
		listView.addView(inflater.inflate(R.layout.create_new_item, null), 0);
		saveBtn = (Button)findViewById(R.id.create_new_attribute_save);
		spinner = (Spinner)findViewById(R.id.create_new_attribute_spinner);
		
		spinner.setAdapter(spinnerAdapter);
		
		spinner.setOnItemSelectedListener(adapterItemClick);
		saveBtn.setOnClickListener(click);
	}
	
	
	Thread loadType = new Thread(){
		public void run() {
			type = dataHelper.getType();
			int size = type.size();
			for(int i=0;i<size;i++){
				typeList.add(type.get(i).get("TYPE"));
			}
			typeId = type.get(0).get("ID");
			handler.sendEmptyMessage(LOAD_TYPE_FINISH);
		};
	};
	
	View.OnClickListener click = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.create_new_attribute_save:
				int count = listView.getChildCount();
				for(int i=0;i<count;i++){
					String s = ((EditText)listView.getChildAt(i).findViewById(R.id.create_new_item_text)).getText().toString();
					if(!s.isEmpty()){
						data.add(s);
					}
				}
				if(data.size()==0){
					Toast.makeText(CreateAttribute.this, "你什么都没输入", Toast.LENGTH_SHORT).show();
					return;
				}
				dataHelper.saveAttribute(typeId, data);
				dataHelper.Close();
				Toast.makeText(CreateAttribute.this, "保存成功", Toast.LENGTH_SHORT).show();
				finish();
				break;

			default:
				break;
			}
		}
	};
	
	AdapterView.OnItemSelectedListener adapterItemClick = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			typeId = type.get(position).get("ID");
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(R.string.add);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		listView.addView(inflater.inflate(R.layout.create_new_item, null), listView.getChildCount());
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dataHelper.Close();
	}

}
