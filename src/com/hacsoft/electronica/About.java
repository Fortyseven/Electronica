package com.hacsoft.electronica;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity
{

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.about );
		((Button) findViewById( R.id.OK )).setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v )
			{
				finish();
			}
		} );
	}
}
