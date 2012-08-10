package com.dlvct.barcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CollectTime extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.collect_time);
	}
}
