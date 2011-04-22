package com.hacsoft.electronica;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class Keypad extends View
{
	public Keypad(Context context, AttributeSet attrs)
	{
		super( context, attrs );
		// LayoutInflater inf = (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// if (inf != null) {
		// inf.inflate(R.layout.ui_keypad, null, true);
		// }

		// inflate(context, R.layout.ui_keypad, null);
		LayoutInflater.from( context ).inflate( R.layout.ui_keypad, null, true );
	}
}
