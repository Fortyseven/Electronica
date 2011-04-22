package com.hacsoft.electronica;

import android.os.Bundle;

public class Formulas_LEDResistance extends FormulaActivity
{
	private final int	OPERAND_VS	= 0;
	private final int	OPERAND_VF	= 1;
	private final int	OPERAND_IF	= 2;
	private final int	OPERAND_R	= 3;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setFormulaTitle( R.string.formulas_ledresistance );

		addOperand( OPERAND_VS, "Vs", getString( R.string.formulas_ledresistance_vs ) );
		addOperand( OPERAND_VF, "Vf", getString( R.string.formulas_ledresistance_vf ) );
		addOperand( OPERAND_IF, "If", getString( R.string.formulas_ledresistance_if ) );

		addOperand( OPERAND_R, "R", "" );

		setResultOperand( OPERAND_R );

		build();
	}

	@Override
	protected void recalculate()
	{
		try {
			// R = (Vs - Vf) / (If/1000)

			float res = (getOperandValue( OPERAND_VS ) - getOperandValue( OPERAND_VF )) / (getOperandValue( OPERAND_IF ) / 1000);
			setResult( res );
		}
		catch ( Exception e ) {
			setResultUnknown();
		}

	};
}
