package com.hacsoft.electronica;



public class ChartsASCII extends MenuActivity
{
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		
		setMenuTitle( R.string.charts_chars );
		
		addMenuEntry( "Standard ASCII", 		ChartWebView.class, 	null, 	"computers/charts_ascii_standard.html" );
		addMenuEntry( "Extended ASCII (IBM)", 		ChartWebView.class, 	null, 	"computers/charts_ascii_extibm.html" );
		addMenuEntry( "Extended ASCII (Windows)", 	ChartWebView.class, 	null, 	"computers/charts_ascii_extwin.html" );
		addMenuEntry( "EBCDIC",				ChartWebView.class, 	null, 	"computers/charts_EBCDIC.html" );				
		go();
	};
}
