package com.hacsoft.electronica;

import android.os.Bundle;

public class ChartsComputers extends MenuActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setMenuTitle( R.string.charts_computers );

		addMenuEntry( R.string.charts_chars, 		ChartsASCII.class, 	R.drawable.charts_ascii, null );
		addMenuEntry( R.string.charts_ps2scancode, 	ChartWebView.class, 	R.drawable.charts_ps2keyboard, "computers/charts_ps2scancodes.html");
		go();
	}
}