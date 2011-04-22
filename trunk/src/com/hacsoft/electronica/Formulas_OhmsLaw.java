package com.hacsoft.electronica;

import android.os.Bundle;
import android.widget.Toast;

public class Formulas_OhmsLaw extends FormulaActivity
{
	private final int	OPERAND_V	= 0;
	private final int	OPERAND_I	= 1;
	private final int	OPERAND_R	= 2;

	private final int	MODE_SOLVEFORV	= 0;
	private final int	MODE_SOLVEFORI	= 1;
	private final int	MODE_SOLVEFORR	= 2;

	private int		calc_mode;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setFormulaTitle( "Ohm\'s Law" );

		addOperandSelectable( OPERAND_V, "V", "Voltage (Volts)", new SelectionResponder() {

			@Override
			public void onSelection()
			{
				Toast.makeText( Formulas_OhmsLaw.this, "Now solving for VOLTAGE; provide current and resistance.", Toast.LENGTH_SHORT ).show();
				calc_mode = MODE_SOLVEFORV;
			}
		} );
		addOperandSelectable( OPERAND_I, "I", "Current (Amps)", new SelectionResponder() {

			@Override
			public void onSelection()
			{
				Toast.makeText( Formulas_OhmsLaw.this, "Now solving for CURRENT; provide voltage and resistance.", Toast.LENGTH_SHORT ).show();
				calc_mode = MODE_SOLVEFORI;
			}
		} );
		addOperandSelectable( OPERAND_R, "R", "Resistance (Ohms)", new SelectionResponder() {

			@Override
			public void onSelection()
			{
				Toast.makeText( Formulas_OhmsLaw.this, "Now solving for RESISTANCE; provide voltage and current.", Toast.LENGTH_SHORT ).show();
				calc_mode = MODE_SOLVEFORR;
			}
		} );

		setResultOperand( OPERAND_I );

		build();

		calc_mode = MODE_SOLVEFORI;
	}

	@Override
	protected void recalculate()
	{
		float v = getOperandValue( OPERAND_V );
		float i = getOperandValue( OPERAND_I );
		float r = getOperandValue( OPERAND_R );

		try {
			switch ( calc_mode )
			{
				case MODE_SOLVEFORV:
					setResult( i * r );
					break;
				case MODE_SOLVEFORI:
					setResult( v / r );
					break;
				case MODE_SOLVEFORR:
					setResult( v / i );
					break;
				default:
					throw new Exception( "Undefined calculation mode." );
			}
		}
		catch ( Exception e ) {
			setResultUnknown();
		}
	};
}
