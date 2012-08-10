package com.dlvct.barcode;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class CollectClassify extends TabActivity{
	private TabHost tabHost;
	private Resources resources;
	private LayoutInflater inflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.collect_classify);
		resources = getResources();
		inflater = getLayoutInflater();
		initView();
	}
	
	private void initView(){
		tabHost = getTabHost();
		View item = inflater.inflate(R.layout.collect_left_item, null);
		TextView name = (TextView)item.findViewById(R.id.collect_left_item_text);
		ImageView icon = (ImageView)item.findViewById(R.id.collect_left_item_img);
		
		TabSpec tab1 = tabHost.newTabSpec("tab1");
		name.setText("名片");
		icon.setImageResource(R.drawable.card_history_left);
		tab1.setIndicator(item);
		tab1.setContent(new Intent(CollectClassify.this,CollectTmp.class));
		tabHost.addTab(tab1);
		
		
		item = inflater.inflate(R.layout.collect_left_item, null);
		name = (TextView)item.findViewById(R.id.collect_left_item_text);
		icon = (ImageView)item.findViewById(R.id.collect_left_item_img);
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		name.setText("网站");
		icon.setImageResource(R.drawable.net_history_left);
		tab2.setIndicator(item);
		tab2.setContent(new Intent(CollectClassify.this,CollectTmp.class));
		tabHost.addTab(tab2);
		
		item = inflater.inflate(R.layout.collect_left_item, null);
		name = (TextView)item.findViewById(R.id.collect_left_item_text);
		icon = (ImageView)item.findViewById(R.id.collect_left_item_img);
		TabSpec tab3 = tabHost.newTabSpec("tab2");
		name.setText("商品");
		icon.setImageResource(R.drawable.product_history_left);
		tab3.setIndicator(item);
		tab3.setContent(new Intent(CollectClassify.this,CollectTmp.class));
		tabHost.addTab(tab3);
	}
}
