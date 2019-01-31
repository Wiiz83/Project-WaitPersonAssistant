package com.example.wait_person;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Order2 extends Activity {

	EditText want;
	Button add;
	ListView listContent;
	ArrayAdapter<String> adapter;
	ArrayList<String> arrayList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order2);

		Button cancel = (Button) findViewById(R.id.BT_Cancel);
		want = (EditText) findViewById(R.id.ET_want);
		add = (Button) findViewById(R.id.BT_add);

		listContent = (ListView) findViewById(R.id.contentlist);
		arrayList = new ArrayList<String>();

		// exit if user click cancel
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				System.exit(0);

			}
		});

		// Adapter: You need three parameters 'the context, id of the layout (it
		// will be where the data is shown),
		// and the array that contains the data
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_spinner_item, arrayList);
		listContent.setAdapter(adapter);

		add.setOnClickListener(buttonAddOnClickListener);

	}

	Button.OnClickListener buttonAddOnClickListener

	= new Button.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// this line adds the data of your EditText and puts in your array
			arrayList.add(want.getText().toString());
			// next thing you have to do is check if your adapter has changed
			adapter.notifyDataSetChanged();
		}

	};

}