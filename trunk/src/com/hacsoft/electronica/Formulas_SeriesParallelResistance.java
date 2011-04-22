package com.hacsoft.electronica;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;

public class Formulas_SeriesParallelResistance extends Activity
{
	private ArrayAdapter<Float>	res_list;
	private ListView		res_listview;

	private Button			bAddRes;
	private Button			bToggleMode;
	private EditText		eAddRes;
	private EditText		eResult;

	private final int		MODE_SERIES	= 0;
	private final int		MODE_PARALLEL	= 1;

	private int			calc_mode;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.formulas_sp_resistance );

		calc_mode = MODE_PARALLEL;

		res_listview = (ListView) findViewById( R.id.spr_resvalues );
		res_list = new ArrayAdapter<Float>( this, R.layout.ui_sp_resrow, R.id.label );
		res_listview.setAdapter( res_list );
		res_listview.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick( AdapterView<?> av, View v, int pos, long id )
			{
				Toast.makeText( av.getContext(), String.format( getString( R.string.sp_removed ), res_list.getItem( pos ) ), Toast.LENGTH_SHORT ).show();
				res_list.remove( res_list.getItem( pos ) );
				res_listview.requestLayout();
				recalculate();
			}
		} );

		bAddRes = (Button) findViewById( R.id.sp_add );
		bAddRes.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v )
			{
				try {
					Float f = Float.parseFloat( eAddRes.getText().toString() );
					if ( f > 0 ) {
						res_list.add( f );
					}
					else {
						throw new Exception( getString( R.string.sp_err_invalidinput ) );
					}
				}
				catch ( Exception e ) {
					if ( e.getMessage().length() == 0 ) {
						Toast.makeText( v.getContext(), R.string.sp_err_invalidinput, Toast.LENGTH_SHORT ).show();
					}
					else {
						Toast.makeText( v.getContext(), String.format( getString( R.string.error ), e.getMessage() ), Toast.LENGTH_SHORT ).show();
					}
				}
				res_listview.requestLayout();
				eAddRes.setText( "" );
				recalculate();
			}
		} );

		eAddRes = (EditText) findViewById( R.id.sp_add_edittext );
		eResult = (EditText) findViewById( R.id.sp_result );
		eAddRes.setOnEditorActionListener( new OnEditorActionListener() {

			@Override
			public boolean onEditorAction( TextView v, int actionId, KeyEvent event )
			{
				// TODO Auto-generated method stub
				bAddRes.performClick();
				return false;
			}
		} );

		bToggleMode = (Button) findViewById( R.id.sp_toggle );
		bToggleMode.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick( View v )
			{
				switch ( calc_mode )
				{
					case MODE_PARALLEL:
						calc_mode = MODE_SERIES;
						bToggleMode.setText( R.string.sp_series );
						break;
					case MODE_SERIES:
						calc_mode = MODE_PARALLEL;
						bToggleMode.setText( R.string.sp_parallel );
						break;
				}
				recalculate();
			}
		} );
	}

	@Override
	protected void onSaveInstanceState( Bundle outState )
	{
		super.onSaveInstanceState( outState );
		float f[] = new float[res_list.getCount()];

		for ( int c = 0; c < res_list.getCount(); c++ ) {
			f[c] = res_list.getItem( c );
		}

		outState.putFloatArray( "resistorlist", f );
	}

	@Override
	protected void onRestoreInstanceState( Bundle savedInstanceState )
	{
		super.onRestoreInstanceState( savedInstanceState );

		float f[] = savedInstanceState.getFloatArray( "resistorlist" );

		res_list.clear();
		for ( float fi : f ) {
			res_list.add( fi );
		}
	}

	void recalculate()
	{
		float res = 0;

		if ( res_list.getCount() > 0 ) {
			try {
				switch ( calc_mode )
				{
					case MODE_PARALLEL:
						for ( int c = 0; c < res_list.getCount(); c++ ) {
							res += (1 / res_list.getItem( c ));
						}
						if ( res > 0 ) {
							res = 1 / res;
						}
						break;
					case MODE_SERIES:
						for ( int c = 0; c < res_list.getCount(); c++ ) {
							res += res_list.getItem( c );
						}
						break;
				}
				if ( res == 0 ) {
					eResult.setText( "" );
				}
				else {
					eResult.setText( String.valueOf( res ) );
				}
			}
			catch ( Exception e ) {
				Toast.makeText( this, String.format( getString( R.string.error ), e.getMessage() ), Toast.LENGTH_SHORT ).show();
			}
		}
		else {
			eResult.setText( "" );
		}

	}
}
