package com.hacsoft.electronica;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.hacsoft.android.ANumberPicker;

public class ToolCapCalc extends Activity implements com.hacsoft.android.ANumberPicker.OnChangedListener
{
	ANumberPicker	valuepicker1, valuepicker2, valuepicker3;
	EditText	result;
	String		val1, val2, val3;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.tool_capcalc );

		valuepicker1 = (ANumberPicker) findViewById( R.id.capvalue1 );
		valuepicker2 = (ANumberPicker) findViewById( R.id.capvalue2 );
		valuepicker3 = (ANumberPicker) findViewById( R.id.capvalue3 );

		valuepicker1.setOnChangeListener( this );
		valuepicker2.setOnChangeListener( this );
		valuepicker3.setOnChangeListener( this );

		result = (EditText) findViewById( R.id.result );

		val1 = "0";
		val2 = "0";
		val3 = "0";
	}

	@Override
	public void onChanged(ANumberPicker picker, int oldVal, int newVal)
	{
		val1 = String.valueOf(valuepicker1.getCurrent());
		val2 = String.valueOf(valuepicker2.getCurrent());
		val3 = String.valueOf(valuepicker3.getCurrent());

		updateResults();

	}
	
	private String ztrim(String in)
	{
		return String.valueOf(Float.valueOf( in ));
	}
	
	private void updateResults()
	{
		String res = "";
		
		if ("0".equals( val1 )  && "0".equals( val2 )) res = "";
		else switch(Integer.valueOf(val3)) {
			case 0: res = ztrim(val1 + val2) + " pF"; break;
			case 1: res = ztrim(val1 + val2 + "0")+" pF"; break;
			
			case 2: res = ztrim(val1 + "." + val2) + " nF"; break;
			case 3: res = ztrim(val1 + val2) + " nF"; break;
			case 4: res = ztrim(val1 + val2 + "0")+" nF"; break;
			
			case 5: res = ztrim(val1 + "." +  val2) + " uF"; break;
			case 6: res = ztrim(val1 + val2) + " uF"; break;
			case 7: res = ztrim(val1 + val2 + "0")+" uF"; break;
			
			case 8: res = ztrim(val1 + "." + val2) + " mF"; break;
			case 9: res = ztrim(val1 + val2) + " mF"; break;
		}
		
		result.setText( res );

	}

	/*--------------------------------------------------------------*/
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState( outState );
		outState.putInt( "val1", valuepicker1.getCurrent() );
		outState.putInt( "val2", valuepicker2.getCurrent() );
		outState.putInt( "val3", valuepicker3.getCurrent() );
	}

	/*--------------------------------------------------------------*/
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onRestoreInstanceState( savedInstanceState );
		
		val1 = String.valueOf( savedInstanceState.getInt( "val1" ) );
		val2 = String.valueOf( savedInstanceState.getInt( "val2" ) );
		val3 = String.valueOf( savedInstanceState.getInt( "val3" ) );
		
		valuepicker1.setCurrent( savedInstanceState.getInt( "val1" ) );
		valuepicker2.setCurrent( savedInstanceState.getInt( "val2" ) );
		valuepicker3.setCurrent( savedInstanceState.getInt( "val3" ) ); 
		
		updateResults();
	}	
	
}
