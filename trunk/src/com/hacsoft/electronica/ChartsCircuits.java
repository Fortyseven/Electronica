package com.hacsoft.electronica;



public class ChartsCircuits extends MenuActivity
{
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		
		setMenuTitle( R.string.charts_circuits );

		// inverting amplifier
		addMenuEntry( R.string.circuits_invamp,		ChartView.class, 	null, 		R.drawable.circuits_invamp);

		// non-inverting amplifier
		addMenuEntry( R.string.circuits_noninvamp,	ChartView.class, 	null, 		R.drawable.circuits_noninvamp);

		go();
	};
}
