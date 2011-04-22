package com.hacsoft.electronica;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class WhatsNew extends Activity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.whatsnew );
		setTitle( R.string.about_whatsnewtext );
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		((Button) findViewById( R.id.OK )).setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v )
			{
				finish();
			}
		} );
	}	
}
