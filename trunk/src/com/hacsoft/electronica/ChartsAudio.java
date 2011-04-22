package com.hacsoft.electronica;

import android.os.Bundle;

public class ChartsAudio extends MenuActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setMenuTitle( R.string.charts_audio );
		addMenuEntry("XLR3",			ChartWebView.class, 	null, 			"cables/xlr3.html");
		go();
	}
}
