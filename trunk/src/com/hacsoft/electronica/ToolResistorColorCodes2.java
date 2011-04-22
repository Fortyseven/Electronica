package com.hacsoft.electronica;

import java.text.DecimalFormat;

import android.app.Activity;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToolResistorColorCodes2 extends Activity
{
	public final static int		RANGE_OHM	= 0;
	public final static int		RANGE_KOHM	= 1;
	public final static int		RANGE_MOHM	= 2;
	public final static int		RANGE_NULL	= 3;

	public static String[]		range_labels;

	public static int		range_mode	= RANGE_OHM;

	private LinearLayout		res_display;
	private TextView[]		res_display_stripe;
	private ResistorColorPicker	res_picker;
	private EditText		res_result;
	private Button			bRange;
	private CharSequence[]		aTolerance;
	private CharSequence[]		aTemperature;

	/*--------------------------------------------------------------*/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.tool_resistorcolorcodes2 );

		range_labels = new String[3];
		range_labels[RANGE_OHM] = "" + getString( R.string.ohm );
		range_labels[RANGE_KOHM] = "K " + getString( R.string.ohm );
		range_labels[RANGE_MOHM] = "M " + getString( R.string.ohm );

		aTolerance = ( getResources().getTextArray( R.array.res_tolerance ) );
		aTemperature = ( getResources().getTextArray( R.array.res_temperature ) );

		( (LinearLayout) findViewById( R.id.operand ) ).setBackgroundResource( R.drawable.grad_result );

		bRange = (Button) findViewById( R.id.res_rangebutton );
		bRange.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				range_mode++;
				if (range_mode == RANGE_NULL)
					range_mode = RANGE_OHM;

				recalculate();
			}
		} );

		res_picker = (ResistorColorPicker) findViewById( R.id.res_picker );
		res_picker.setOnKeypressListener( new ResistorColorPicker.OnKeypressListener() {
			@Override
			public void onKeyPress(View v)
			{
				redrawResistorDisplay();
				recalculate();
			}
		} );

		res_result = (EditText) findViewById( R.id.res_result );

		initResistorDisplay();
		recalculate();
	}

	/*--------------------------------------------------------------*/
	private void initResistorDisplay()
	{
		res_display = (LinearLayout) findViewById( R.id.res_display );
		res_display_stripe = new TextView[6];
		res_display_stripe[0] = (TextView) res_display.findViewById( R.id.res_stripe1 );
		res_display_stripe[1] = (TextView) res_display.findViewById( R.id.res_stripe2 );
		res_display_stripe[2] = (TextView) res_display.findViewById( R.id.res_stripe3 );
		res_display_stripe[3] = (TextView) res_display.findViewById( R.id.res_stripe4 );
		res_display_stripe[4] = (TextView) res_display.findViewById( R.id.res_stripe5 );
		res_display_stripe[5] = (TextView) res_display.findViewById( R.id.res_stripe6 );
	}

	/*--------------------------------------------------------------*/
	private void redrawResistorDisplay()
	{
		int[] cols = res_picker.getColumnValues();

		for (int c = 0; c < ResistorColorPicker.MAX_COLS; c++) {
			res_display_stripe[c].getBackground().mutate();

			if (cols[c] >= 0) {
				int clr = getResources().getColor( ResistorColorPicker.bgColors[cols[c]] );
				res_display_stripe[c].getBackground().setColorFilter( clr, Mode.SRC );
			}
			else {
				// clr = Color.rgb(0xff,0xeb,0xb4);
				res_display_stripe[c].getBackground().setColorFilter( null );
			}

			res_display_stripe[c].invalidate();
		}
	}

	/*--------------------------------------------------------------*/
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState( outState );
		outState.putIntArray( "stripes", res_picker.getColumnValues() );
	}

	/*--------------------------------------------------------------*/
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onRestoreInstanceState( savedInstanceState );
		res_picker.setColumnValues( savedInstanceState.getIntArray( "stripes" ) );
		redrawResistorDisplay();
	}

	/*--------------------------------------------------------------*/
	private void recalculate()
	{
		String valueString;
		int[] cols = res_picker.getColumnValues();
		Double resistance;
		String tolerance = "";

		// String tolerance = aTolerance[0].toString();

		resistance = (double) -1;

		// Do we have at least 3 colors?
		if (( cols[0] >= 0 ) && ( cols[1] >= 0 ) && ( cols[2] >= 0 )) {
			// if (cols[5] >= 0) {
			// valueString = String.valueOf( cols[0] ) +
			// String.valueOf( cols[1] ) + String.valueOf( cols[2] )
			// + String.valueOf( cols[3] );
			// resistance = Double.valueOf( valueString +
			// getMultiplierString( cols[4] ) );
			// // 6th band is tolerance
			// tolerance = aTolerance[cols[5]].toString();
			// }
			// else
			if (cols[4] >= 0) {
				valueString = String.valueOf( cols[0] ) + String.valueOf( cols[1] ) + String.valueOf( cols[2] );
				resistance = Double.valueOf( valueString + getMultiplierString( cols[3] ) );
				// 5th band is tolerance
				tolerance = aTolerance[cols[4]].toString();
				if (cols[5] > -1) {
					tolerance += " " + aTemperature[ cols[ 5 ] ].toString();
				}
			}
			else if (cols[3] >= 0) {
				// Do 4-band
				valueString = String.valueOf( cols[0] ) + String.valueOf( cols[1] );
				resistance = Double.valueOf( valueString + getMultiplierString( cols[2] ) );
				// 4th band is tolerance
				tolerance = aTolerance[cols[3]].toString();

			}
			else {
				// Do 3-band (skipping the tolerance band)
				valueString = String.valueOf( cols[0] ) + String.valueOf( cols[1] );
				resistance = Double.valueOf( valueString + getMultiplierString( cols[2] ) );
			}
		}

		switch (range_mode) {
			case RANGE_KOHM:
				resistance /= 1000;
				break;
			case RANGE_MOHM:
				resistance /= 1000000;
				break;
			default:
				break;
		}

		if (resistance >= 0) {
			String r = DecimalFormat.getInstance().format( resistance );

			if (r.equals( "0" )) {
				// String will be "0" if too small to be
				// converted to pretty numbers; so just give the
				// raw shit.
				res_result.setText( resistance.toString() );
			}
			else {
				res_result.setText( r + " " + tolerance );
			}
		}
		else {
			res_result.setText( "---" );
		}

		bRange.setText( range_labels[range_mode] );

	}

	/*--------------------------------------------------------------*/
	private String getMultiplierString(int value)
	{
		String outstring = "";
		for (int i = 0; i < value; i++) {
			outstring += "0";
		}
		return outstring;
	}
}
