package com.hacsoft.electronica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class FormulaActivity extends Activity
{
	class FormulaOperand
	{
		public View			view;
		public String			name;
		public String			long_description;
		public int			id;
		private float			value;
		public SelectionResponder	selection_responder;
		public Boolean			is_result;

		public FormulaOperand()
		{
			selection_responder = null;
			is_result = false;
		}

		public void setValue( float value )
		{
			this.value = value;
			// ((TextView)(view.findViewById(R.id.operand_edittext))).setText(String.valueOf(value));
			this.setValue( String.valueOf( value ) );
		}

		public void setValue( String value )
		{
			((TextView) (view.findViewById( R.id.operand_edittext ))).setText( value );
		}

		void setValueUnknown()
		{
			this.value = 0;
			setValue( "???" );
		}

		public float getValue()
		{
			return value;
		}

		public View getView()
		{
			return view;
		}
	};

	// private Map<String, FormulaOperand> operands;
	private HashMap<Integer, FormulaOperand>	operands;
	private FormulaOperand				result;
	private TableLayout				layout;
	private TextView				title;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		layout = new TableLayout( this );
		layout.setStretchAllColumns( false );
		operands = new HashMap<Integer, FormulaOperand>();
		title = new TextView( this );
		title.setTextAppearance( this, android.R.style.TextAppearance_Large_Inverse );
		title.setBackgroundResource( R.drawable.grad_title );
		layout.addView( title );
		setContentView( layout );
	}

	public void build()
	{
		// TODO: Instead, let's build the layout here instead of
		// piecemeal

	}

	public void setFormulaTitle( String new_title )
	{
		title.setText( new_title );
	}

	public void setFormulaTitle( int resource_id )
	{
		setFormulaTitle( getString( resource_id ) );
	}

	public FormulaOperand addOperand( int id, String name, String long_description, int view_resource )
	{
		final FormulaOperand newOperand = new FormulaOperand();

		newOperand.name = name;
		newOperand.id = id;
		newOperand.long_description = long_description;
		newOperand.view = new TableRow( this );
		// View.inflate(this, R.layout.ui_formulaoperand, layout);

		View.inflate( this, view_resource, (ViewGroup) newOperand.getView() );

		TextView tv = (TextView) (newOperand.view.findViewById( R.id.operand_name ));
		if ( tv != null ) {
			tv.setText( name );
		}
		else {
			Button b = (Button) (newOperand.view.findViewById( R.id.operand_selectionbutton ));
			b.setText( name );
		}

		((TextView) (newOperand.view.findViewById( R.id.operand_long_description ))).setText( long_description );

		newOperand.view.setBackgroundResource( R.drawable.grad_operand );

		((EditText) (newOperand.view.findViewById( R.id.operand_edittext ))).setOnEditorActionListener( new OnEditorActionListener() {

			@Override
			public boolean onEditorAction( TextView v, int actionId, KeyEvent event )
			{
				newOperand.setValue( Float.parseFloat( v.getText().toString() ) );
				recalculate();
				return false;
			}
		} );

		newOperand.setValue( 0 );

		layout.addView( newOperand.view );

		operands.put( id, newOperand );

		return newOperand;
	}

	/*
	 * Default to normal grey operand style. (Label + long label + editbox
	 * only.)
	 */
	public FormulaOperand addOperand( int id, String name, String long_description )
	{
		return addOperand( id, name, long_description, R.layout.ui_formulaoperand );
	}

	public void addOperandSelectable( int id, String name, String long_description, SelectionResponder selresp )
	{
		final FormulaOperand newop = addOperand( id, name, long_description, R.layout.ui_formula_selectable );

		newop.selection_responder = selresp;

		Button b = (Button) newop.view.findViewById( R.id.operand_selectionbutton );

		if ( b != null ) {
			b.setOnClickListener( new OnClickListener() {

				@Override
				public void onClick( View v )
				{
					setResultOperand( newop.id );
					if ( newop.selection_responder != null ) {
						newop.selection_responder.onSelection();
					}
				}
			} );
		}
		;
	}

	protected float getOperandValue( int id )
	{
		return operands.get( id ).getValue();
	}

	protected void addResult( int id, String name, String long_description )
	{
		addOperand( id, name, long_description, R.layout.ui_formularesult );
		setResultOperand( id );
	}

	// When using button style operands, set one as the result field
	protected void setResultOperand( int id )
	{
		// Make me the current selection
		Collection<FormulaOperand> c = operands.values();
		Iterator<FormulaOperand> itr = c.iterator();
		while ( itr.hasNext() ) {
			FormulaOperand fo = itr.next();
			if ( fo.id == id ) {
				fo.view.setBackgroundResource( R.drawable.grad_result );

				Button b = (Button) (fo.view.findViewById( R.id.operand_selectionbutton ));
				if ( b != null ) {
					b.setEnabled( false );
				}

				EditText e = (EditText) (fo.view.findViewById( R.id.operand_edittext ));
				e.setFocusable( false );
				e.setFocusableInTouchMode( false );
				e.setTextColor( Color.GRAY );
				fo.is_result = true;
				result = fo;
			}
			else {
				fo.view.setBackgroundResource( R.drawable.grad_operand );
				Button b = (Button) (fo.view.findViewById( R.id.operand_selectionbutton ));
				if ( b != null ) {
					b.setEnabled( true );
				}
				EditText e = (EditText) (fo.view.findViewById( R.id.operand_edittext ));
				e.setFocusable( true );
				e.setFocusableInTouchMode( true );
				e.setTextColor( Color.BLACK );
				fo.is_result = false;
			}
		}
	}

	protected void setResult( float value )
	{
		result.setValue( value );
	}

	protected void setResultUnknown()
	{
		result.setValueUnknown();
	}

	protected void recalculate()
	{
		;
	}

}
