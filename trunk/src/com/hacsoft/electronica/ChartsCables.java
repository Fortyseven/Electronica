package com.hacsoft.electronica;



public class ChartsCables extends MenuActivity
{
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		
		setMenuTitle( R.string.charts_cables );
		
		addMenuEntry( R.string.charts_video,				ChartsVideo.class, 	R.drawable.menu_video,		null );
		addMenuEntry( R.string.charts_audio, 				ChartsAudio.class, 	R.drawable.menu_audio,		null );
		addMenuEntry( R.string.video_games, 				ChartsVideoGames.class, R.drawable.menu_videogames,	null );
		
		addMenuEntry( "Universal Serial Bus (USB)", 			ChartWebView.class, 	null, 				"cables/usb.html" );
		addMenuEntry( "Ethernet (RJ-45)", 				ChartWebView.class, 	null, 				"cables/ethernet.html" );
		addMenuEntry( "RS-232", 					ChartWebView.class, 	null, 				"cables/rs232.html" );
		
		addMenuEntry( "Portable Digital Media Interface (PDMI)", 	ChartWebView.class, 	null, 				"cables/pdmi.html" );
		addMenuEntry( "Apple 30-pin Dock Connector", 			ChartWebView.class, 	null, 				"cables/apple30pin.html" );
		
		go();
	};
}
