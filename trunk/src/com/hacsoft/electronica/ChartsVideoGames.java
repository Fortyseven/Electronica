package com.hacsoft.electronica;

import android.os.Bundle;

public class ChartsVideoGames extends MenuActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setMenuTitle( R.string.video_games );

		//addMenuEntry( "XBox A/V", 		ChartWebView.class, 	null, "charts_xboxav.html" );
		//addMenuEntry( "XBox 360 (Assorted)", 	ChartWebView.class, 	null, "charts_xbox360.html" );
		addMenuEntry( "NES Controller Port", 	ChartView.class, 	null, R.drawable.pinouts_nes );
		go();
	}
}
