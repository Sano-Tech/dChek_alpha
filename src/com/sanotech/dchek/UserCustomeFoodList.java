package com.sanotech.dchek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sanotech.dchek.adapters.SeparatedListAdapter;

public class UserCustomeFoodList extends Activity{
	  private ListView listHeader;
	  private ListView listItems;
	  private SeparatedListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custome_food_list);
		 ActionBar actionBar = getActionBar();
		 actionBar.setDisplayHomeAsUpEnabled(true);
		 
		 final ArrayAdapter<String> journalEntryAdapter = new ArrayAdapter<String>(this, R.layout.custome_menu_item, new String[]{});

         // AddJournalEntryItem
		 listHeader = (ListView) this.findViewById(R.id.ls_header);
		 listHeader.setAdapter(journalEntryAdapter);
		 listHeader.setOnItemClickListener(new OnItemClickListener()
             {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long duration)
                     {
                         String item = journalEntryAdapter.getItem(position);
                         Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                     }
             });

         List<String> ls1 = new ArrayList<String>();
         ls1.add("Item 1-1 ");
         ls1.add("Item 1-2 ");
         ls1.add("Item 1-3 ");
         ls1.add("Item 1-4 ");
         
         List<String> ls2 = new ArrayList<String>();
         ls2.add("Item 2-21 ");
         ls2.add("Item 2-22 ");
         ls2.add("Item 2-23 ");
         ls2.add("Item 2-24 ");
         HashMap<String, List<String>> detailMap = new HashMap<String, List<String>>();
         detailMap.put("1", ls1);
         detailMap.put("2", ls2);
         
         // Create the ListView Adapter
         adapter = new SeparatedListAdapter(this);
        // ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, R.layout.list_item, notes);

         // Add Sections
         for (String key: detailMap.keySet())
             {
         		ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, R.layout.list_item, detailMap.get(key));
                 adapter.addSection(key, listadapter);
             }

         // Get a reference to the ListView holder
         listItems = (ListView) this.findViewById(R.id.ls_items);

         // Set the adapter on the ListView holder
         listItems.setAdapter(adapter);

         // Listen for Click events
         listItems.setOnItemClickListener(new OnItemClickListener()
             {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long duration)
                     {
                         String item = (String) adapter.getItem(position);
                         Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                     }
             });
	 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custome_food_menu, menu);
 
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
