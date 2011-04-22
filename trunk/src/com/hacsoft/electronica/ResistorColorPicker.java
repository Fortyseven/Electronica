package com.hacsoft.electronica;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResistorColorPicker extends LinearLayout
{
	public interface OnKeypressListener
	{
		void onKeyPress( View v );
	};

	final static int		bgColors[]	= {
			R.color.rBlack,
			R.color.rBrown,
			R.color.rRed,
			R.color.rOrange,
			R.color.rYellow,
			R.color.rGreen,
			R.color.rBlue,
			R.color.rViolet,
			R.color.rGrey,
			R.color.rWhite,
			R.color.rGold,
			R.color.rSilver,
							};

	private final int		fgColors[]	= {
			R.color.rWhite, // blk
					// 0
			R.color.rWhite, // bro 1
			R.color.rWhite, // red 2
			R.color.rWhite, // org 3
			R.color.rWhite, // yel 4
			R.color.rWhite, // grn 5
			R.color.rWhite, // blu 6
			R.color.rWhite, // vio 7
			R.color.rWhite, // gry 8
			R.color.rWhite, // whi 9
			R.color.rWhite, // gol 10
			R.color.rWhite, // sil 11
							};
	private final int		fgColorsOn[]	= {
			R.color.rWhite, // 0
			R.color.rWhite, // 1
			R.color.rWhite, // 2
			R.color.rWhite, // 3
			R.color.rBlack, // 4
			R.color.rBlack, // 5
			R.color.rWhite, // 6
			R.color.rWhite, // 7
			R.color.rBlack, // 8
			R.color.rBlack, // 9
			R.color.rBlack, // 10
			R.color.rWhite
							// 11
							// 11
							};

	public static int		MAX_COLS	= 6;
	public static int		MAX_SEGS	= 12;

	private final int		DIM		= 100;

	// private final Context ctx;
	private final View[]		col;
	// private final int seg_id_cache[][];

	private final TextView[][]	segs;

	private final int		col_val[];

	OnKeypressListener		mOnKeypressListener;

	class ResPickerSegData
	{
		int	seg;
		int	col;
	}

	public ResistorColorPicker(Context context)
	{
		this( context, null );
	}

	public ResistorColorPicker(Context context, AttributeSet attrs)
	{
		super( context, attrs );
		// ctx = context;
		this.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );

		// seg_id_cache = new int[MAX_COLS][MAX_SEGS];
		col = new View[MAX_COLS];
		col_val = new int[MAX_COLS];

		segs = new TextView[MAX_COLS][MAX_SEGS];

		mOnKeypressListener = null;

		// Init buttons
		for ( int c = 0; c < MAX_COLS; c++ ) {
			col[c] = View.inflate( context, R.layout.ui_res_picker_segment, null );
			col[c].setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, MAX_COLS ) );
			col_val[c] = -1;
			for ( int seg = 0; seg < 12; seg++ ) {
				// seg_id_cache[c][seg] = ;
				segs[c][seg] = (TextView) col[c].findViewById( (getResources().getIdentifier( "segment" + String.valueOf( seg ), "id", context.getPackageName() )) );

				TextView tv = segs[c][seg];

				// Adjust size to match parent
				tv.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 12 ) );
				// tv.setText(String.valueOf(seg));

				/* Style the buttons per color. */
				tv.setTextColor( getResources().getColor( fgColors[seg] ) );
				Drawable d = getResources().getDrawable( R.drawable.grad_trans );
				d.mutate();
				d.setDither( true );
				d.setColorFilter( getResources().getColor( bgColors[seg] ), Mode.MULTIPLY );
				d.setAlpha( DIM );
				tv.setBackgroundDrawable( d );

				/* Save state data. */
				ResPickerSegData rp = new ResPickerSegData();
				rp.seg = seg;
				rp.col = c;
				tv.setTag( rp );

				/* Add clickability. */
				tv.setOnClickListener( new OnClickListener() {
					@Override
					public void onClick( View v )
					{
						ResPickerSegData rp = (ResPickerSegData) v.getTag();

						// Is this me?
						setColumn( rp.col, rp.seg );

						if ( mOnKeypressListener != null ) {
							mOnKeypressListener.onKeyPress( v );
						}

						update();
					}
				} );
			}
			addView( col[c] );
		}

		segs[0][10].setVisibility( INVISIBLE );
		segs[0][11].setVisibility( INVISIBLE );

		segs[1][10].setVisibility( INVISIBLE );
		segs[1][11].setVisibility( INVISIBLE );

		segs[2][10].setVisibility( INVISIBLE );
		segs[2][11].setVisibility( INVISIBLE );

		col[3].setVisibility( INVISIBLE );
		col[4].setVisibility( INVISIBLE );
		col[5].setVisibility( INVISIBLE );

	}

	private void setColumn( int column, int value )
	{
		if ( col_val[column] == value ) {
			col_val[column] = -1;
			if ( column > 1 ) {
				for ( int c = column; c < MAX_COLS; c++ )
					col_val[c] = -1;
			}
		}
		else {
			col_val[column] = value;
		}
	}

	private void resetColumns()
	{
		for ( int c = 0; c < MAX_COLS; c++ ) {
			for ( int seg = 0; seg < 12; seg++ ) {
				TextView tv = segs[c][seg];

				Drawable d = tv.getBackground();
				d.setAlpha( DIM );
				tv.setBackgroundDrawable( d );
				tv.setTextColor( getResources().getColor( fgColors[seg] ) );
				tv.setText( null );
			}
			if ( c > 2 ) col[c].setVisibility( INVISIBLE );
		}
	}

	protected void update()
	{
		redrawPicker();
	}

	public int[] getColumnValues()
	{
		return col_val;
	}

	public void setColumnValues( int[] values )
	{
		for ( int i = 0; i < MAX_COLS; i++ ) {
			setColumn( i, values[i] );
		}
		redrawPicker();
	}

	protected void setOnKeypressListener( OnKeypressListener listener )
	{
		mOnKeypressListener = listener;
	}

	protected void redrawPicker()
	{
		resetColumns();
		for ( int c = 0; c < MAX_COLS; c++ ) {
			// Set the proper light on this column
			if ( col_val[c] > -1 ) {
				int value = col_val[c];

				col[c].setVisibility( VISIBLE );

				TextView v = segs[c][value];

				Drawable d = v.getBackground();
				d.setAlpha( 255 );
				v.setTextColor( getResources().getColor( fgColorsOn[value] ) );
				v.setText( String.valueOf( value ) );
				v.setBackgroundDrawable( d );

				if ( c > 1 && c < (MAX_COLS - 1) ) {
					col[c + 1].setVisibility( VISIBLE );
				}
			}
		}
	}

}
