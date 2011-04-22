package com.hacsoft.electronica;

import android.os.Bundle;

public class Charts extends MenuActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		
		setMenuTitle( R.string.charts );
		
		addMenuEntry( R.string.charts_components, ChartsComponents.class, R.drawable.charts_ics, null );
		addMenuEntry( R.string.charts_cables, ChartsCables.class, R.drawable.charts_cables, null );
		addMenuEntry( R.string.charts_circuits, ChartsCircuits.class, R.drawable.charts, null );
		addMenuEntry( R.string.charts_computers, ChartsComputers.class, R.drawable.menu_computers, null );
		
		go();
	}
}