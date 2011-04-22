package com.hacsoft.electronica;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MenuActivity extends Activity
{
	class MenuEntry
	{
		public String	name;
		public Integer	icon;
		public Class	class_pointer;
		public Integer	dialog_id;
		public Object	argument;

		private Integer	color;

		// public Integer argument;

		public MenuEntry()
		{
			icon = null;
			dialog_id = null;
			class_pointer = null;
			argument = null;
			color = null;
		}

		public void setColor(Integer color)
		{
			this.color = color;
		}
	};

	private ArrayList	entries;
	private TextView	menu_title;

	//public GoogleAnalyticsTracker	ga;
	private String	title;

	
	class MenuEntryAdapter extends BaseAdapter
	{
		private final Context	c;		

		public MenuEntryAdapter(Context context)
		{
			this.c = context;
		}

		@Override
		public int getCount()
		{
			return entries.size();
		}

		@Override
		public Object getItem(int position)
		{
			return entries.get( position );
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View row;
			MenuEntry fe = (MenuEntry) entries.get( position );

			if (convertView == null) {
				row = LayoutInflater.from( c ).inflate( R.layout.ui_menuitem, parent, false );
			}
			else {
				row = convertView;
			}

			TextView tv = (TextView) row.findViewById( R.id.menu_label );
			ImageView iv = (ImageView) row.findViewById( R.id.menu_icon );

			tv.setText( fe.name );

			if (fe.color != null) {
				tv.setTextColor( fe.color );
			}
			else {
				tv.setTextColor( Color.LTGRAY );
			}

			if (fe.icon != null) {
				iv.setImageResource( fe.icon );
				iv.setVisibility( View.VISIBLE );
				tv.setTextColor( Color.GRAY );
			}
			else {
				iv.setImageResource( R.drawable.onepixel );
				iv.setVisibility( View.INVISIBLE );
				tv.setTextColor( Color.WHITE );
			}

			return row;

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		
		//ga = GoogleAnalyticsTracker.getInstance();		
		//ga.start("UA-270278-17", 60, this);
		
		setContentView( R.layout.ui_menuframe );
		//AdManager.setTestDevices( new String[] {
		//		AdManager.TEST_EMULATOR,
		//		"379DDD6C86E7FA0D1E3AB40F312D4D82" } );

		entries = new ArrayList();

	}

	protected MenuEntry addMenuEntry(String name, Class class_ptr)
	{
		MenuEntry fe = new MenuEntry();
		fe.name = name;
		fe.class_pointer = class_ptr;
		entries.add( fe );

		return fe;

	}

	protected MenuEntry addMenuEntry(int name_resource, Class class_ptr, Integer image_resource, Object arg)
	{
		return addMenuEntry( getString( name_resource ), class_ptr, image_resource, arg );
	}

	protected MenuEntry addMenuEntry(String name, Class class_ptr, Integer image_resource, Object arg)
	{
		MenuEntry me = addMenuEntry( name, class_ptr );
		me.icon = image_resource;
		me.argument = arg;

		return me;
	}

	protected MenuEntry addMenuEntry(int name_resource, int resource_id, Integer image_resource, Object arg)
	{
		return addMenuEntry( getString( name_resource ), resource_id, image_resource, arg );
	}

	protected MenuEntry addMenuEntry(String name, int resource_id, Integer image_resource, Object arg)
	{
		MenuEntry me = addMenuEntry( name, null );
		me.icon = image_resource;
		me.class_pointer = null;
		me.dialog_id = resource_id;
		me.argument = arg;

		return me;
	}

	protected void setMenuTitle(String title)
	{
		( (TextView) findViewById( R.id.menu_title ) ).setText( title );
		this.title = title;
	}

	protected void setMenuTitle(int title_resource)
	{
		setMenuTitle( getString( title_resource ) );
	}
	
	protected void go()
	{
		ListView lv = (ListView) findViewById( R.id.menu_listview );
		lv.setAdapter( new MenuEntryAdapter( this ) );

		//Log.i("HSD", this.title);
		
		lv.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				MenuEntry me = (MenuEntry) entries.get( position );
				
				//ga.trackEvent( "Clicks", "Menu Item", "clicked", value )
				//ga.trackPageView("/"+title+"/"+me.name);

				if (me.dialog_id != null) {

					// final Dialog dialog = new
					// Dialog(view.getContext());
					// dialog.setContentView(R.layout.about);
					// //dialog.setTitle("This is my custom dialog box");
					// dialog.setCancelable(true);
					// Button b =
					// (Button)(dialog.findViewById(R.id.OK));
					// b.setOnClickListener(new
					// OnClickListener() {
					//
					// @Override
					// public void onClick(View v) {
					// dialog.dismiss();
					// }
					// });
					// dialog.show();

				}
				else {
					Intent i = new Intent();

					if (me.argument instanceof String) {
						i.putExtra( "arg", (String) me.argument );

					}
					else if (me.argument instanceof Integer) {
						i.putExtra( "arg", (Integer) me.argument );
					}
					i.setClass( view.getContext(), me.class_pointer );
					startActivity( i );
				}
			}

		} );

	}

}