package com.hacsoft.electronica;

import android.os.Bundle;

public class ChartsComponents extends MenuActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setMenuTitle( R.string.charts_components );

		addMenuEntry( "Arduino", ChartView.class, null, R.drawable.arduino );
		addMenuEntry( "LM 555 Timer", ChartWebView.class, null, "components/555.html" );
		addMenuEntry( "LM 7805 Voltage Regulator", ChartView.class, null, R.drawable.charts_5v );
		go();
	}
}
