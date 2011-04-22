package com.hacsoft.electronica;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ChartView extends Activity
{
	private ImageView	i;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.chart_view );

		i = (ImageView) (findViewById( R.id.chart_image ));
		i.setImageResource( getIntent().getExtras().getInt( "arg" ) );
	}
}
