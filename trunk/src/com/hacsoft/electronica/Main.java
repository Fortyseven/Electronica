package com.hacsoft.electronica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Main extends MenuActivity
{
	private static String PREFS = "DefaultPreferences";
	private static String PREF_LASTVER = "last_version";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );

		//this.ga.trackPageView( "/Main" );

		setMenuTitle( R.string.main );

		addMenuEntry( R.string.tools, Tools.class, R.drawable.tools, null );
		addMenuEntry( R.string.formulas, Formulas.class, R.drawable.calculator, null );
		addMenuEntry( R.string.charts, Charts.class, R.drawable.charts, null );

		// FIXME: Restore 'about' dialog box
		addMenuEntry( R.string.about, About.class, R.drawable.about, null );

		go();

		showWhatsNew();
		
	};

	public void showWhatsNew()
	{		
		SharedPreferences settings = getSharedPreferences( PREFS, MODE_PRIVATE );
		if (settings.contains( PREF_LASTVER )) {
			if (settings.getString( PREF_LASTVER, null ).contentEquals( getString(R.string.version) ) )  {
				// Already showed the box (version last shown == current version)
				return;
			}
		} 
		
		settings.edit().putString( PREF_LASTVER, getString(R.string.version)).commit();
		startActivity( new Intent().setClassName( "com.hacsoft.electronica", "com.hacsoft.electronica.WhatsNew" ) );
	}
}