package com.hacsoft.electronica;

import android.os.Bundle;

public class ChartsVideo extends MenuActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setMenuTitle( R.string.charts_video );

		addMenuEntry( "High Definition Multimedia Interface (HDMI)", ChartWebView.class, null, "cables/hdmi.html" );
		go();
	}
}
