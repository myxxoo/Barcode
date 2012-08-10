package com.dlvct.barcode;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class Barcode extends Activity implements SurfaceHolder.Callback{
	
	private SurfaceView surfaceView;  
    private Camera camera; 
	
    private Button selectPic,inputCode;
    private ImageButton mycard;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initView();
        SurfaceHolder holder = surfaceView.getHolder();  
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
        holder.addCallback(this);
    }
    private void initView(){
    	surfaceView = (SurfaceView)findViewById(R.id.surface_camera);
    	selectPic = (Button)findViewById(R.id.select_pic);
    	inputCode = (Button)findViewById(R.id.input_code);
    	mycard = (ImageButton)findViewById(R.id.mycard);
    	
    	selectPic.setOnClickListener(click);
    	inputCode.setOnClickListener(click);
    	mycard.setOnClickListener(click);
    }

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
//		camera = Camera.open();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	View.OnClickListener click = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.select_pic:
				
				break;
			case R.id.input_code:
				Intent input = new Intent(Barcode.this,InputCode.class);
				startActivity(input);
				break;
			case R.id.mycard:
				Intent mycard = new Intent(Barcode.this,Mycard.class);
				startActivity(mycard);
				break;
			default:
				break;
			}
		}
	};
}