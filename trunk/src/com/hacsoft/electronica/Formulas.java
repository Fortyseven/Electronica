package com.hacsoft.electronica;

import android.os.Bundle;

public class Formulas extends MenuActivity
{
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setMenuTitle( R.string.formulas );

		addMenuEntry( R.string.ohms_title, Formulas_OhmsLaw.class, R.drawable.formula_ohmslaw, null );
		addMenuEntry( R.string.led_title, Formulas_LEDResistance.class, R.drawable.formula_led, null );
		addMenuEntry( R.string.sp_title, Formulas_SeriesParallelResistance.class, R.drawable.spar, null );

		go();
	};

}