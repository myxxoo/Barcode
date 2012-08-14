package com.dlvct.barcode;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class CreateAttribute extends Activity {
	private ListView listView;
	private LayoutInflater inflater;
	private MyAdapter adapter;
	private ArrayList<String> data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_new_attribute);
		initView();
	}

	private void initView() {
		inflater = getLayoutInflater();
		adapter = new MyAdapter();
		data.add("");
		listView = (ListView) findViewById(R.id.create_new_attribute_list);
		listView.setAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(R.string.add);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		data.add("");
		adapter.notifyDataSetChanged();
		return super.onMenuItemSelected(featureId, item);
	}



	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.create_new_attribute_item, null);
			}
			return convertView;
		}
	}

}
