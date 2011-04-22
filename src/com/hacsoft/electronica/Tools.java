package com.hacsoft.electronica;

import android.os.Bundle;

public class Tools extends MenuActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setMenuTitle(R.string.tools);
		
		addMenuEntry(getString(R.string.toolresistorcolorcodes), ToolResistorColorCodes2.class, R.drawable.tool_resistorcolorcode, null);
		addMenuEntry(getString(R.string.toolcapcalc), ToolCapCalc.class, R.drawable.tool_capcalc, null);
		
		go();
	}
}
