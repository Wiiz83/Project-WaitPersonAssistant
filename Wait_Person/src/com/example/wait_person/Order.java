package com.example.wait_person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Order extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);

		Button cancel = (Button) findViewById(R.id.BT_Cancel);
		Button find = (Button) findViewById(R.id.BT_Find);
		final Spinner spinner = (Spinner) findViewById(R.id.Sp_Items);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( // create
																				// a
																				// spinner
																				// with
																				// strings
																				// localize
				this, R.array.items, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		final Spinner spinner1 = (Spinner) findViewById(R.id.Sp_Drinks);

		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				this, R.array.drinks, android.R.layout.simple_spinner_item);

		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);

		spinner1.setEnabled(false);

		Switch drink = (Switch) findViewById(R.id.SW_Drink);

		// exit if user click cancel
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				System.exit(0);

			}
		});

		find.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// Initialize the launcher
				Intent launcher = new Intent(Order.this, Order2.class);

				// Start the launcher
				startActivityForResult(launcher, 1);

			}
		});

		// set the switch to OFF
		drink.setChecked(false);
		// attach a listener to check for changes in state

		drink.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					spinner1.setEnabled(true);
					// perform logic
				} else {
					spinner1.setEnabled(false);

				}
			}
		});

		// we put an event for the spinner depending of the item selected in
		// order to change the background
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView adapter, View v, int i,
					long lng) {

				// if first item selected
				if (i == 0) {

				}

				if (i == 1) {

				}

				if (i == 2) {

				}

				if (i == 3) {

				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

	}

}