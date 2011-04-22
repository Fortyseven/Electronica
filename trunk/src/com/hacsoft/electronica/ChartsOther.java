package com.hacsoft.electronica;

import android.os.Bundle;

public class ChartsOther extends MenuActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setMenuTitle( R.string.other );

		addMenuEntry( "Secure Digital (SD)", ChartView.class, null, "charts_securedigital.html" );
		go();
	}
}
