package com.hacsoft.electronica;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.TextSize;
import android.widget.FrameLayout;

public class ChartWebView extends Activity
{
	class ChartWebViewClient extends WebViewClient
	{
		
		@Override
		public void onPageFinished( WebView view, String url )
		{
			// TODO Auto-generated method stub
			super.onPageFinished( view, url );
			setTitle2( view.getTitle() );
		}
	}
	
	private WebView	i;

	protected void setTitle2(String str)
	{
		super.setTitle( str );
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.chart_webview );

		i = (WebView) (findViewById( R.id.chart_frame ));
		i.getSettings().setJavaScriptEnabled( true );
		i.getSettings().setTextSize( TextSize.NORMAL );
		i.setMapTrackballToArrowKeys( true );
		addContentView( i.getZoomControls(), new FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,Gravity.BOTTOM) );
		
		i.setWebViewClient( new ChartWebViewClient() );
		i.loadUrl( "file:///android_asset/" + getIntent().getExtras().getString( "arg" ) );
		
		i.setOnTouchListener( new OnTouchListener() {
			private int taps = 0;
			private long last_tap = 0;
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					if ( (System.currentTimeMillis() - last_tap) >= 200 ) {
						taps = 0;
					} 
					taps++;
					if (taps > 1) {
						//Toast.makeText( v.getContext(), "ok", Toast.LENGTH_SHORT ).show();
						v.scrollTo( 0,0 );
						taps = 0;
					}
					last_tap = System.currentTimeMillis();
				}
				
				return false;
			}
		});
		
	}
}
