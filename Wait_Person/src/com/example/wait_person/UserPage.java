package com.example.wait_person;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserPage extends Activity {

	// box to put the string inside and pass to an other class
	String BOX_Login = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userpage);

		// button to display a context menu
		Button btn = (Button) findViewById(R.id.type2);
		registerForContextMenu(btn);

		// create an action bar
		android.app.ActionBar actionBar = getActionBar();

		// enable back to the previous activity (arrow at the top left of the
		// screen)
		actionBar.setDisplayHomeAsUpEnabled(true);

		// put the string into the box
		Intent intent = getIntent();
		String login = String.valueOf(intent.getStringExtra(BOX_Login));

		// display a welcome message with the username
		final TextView welcome = (TextView) findViewById(R.id.TV_Welcome);
		welcome.setText("Welcome  " + login);

		// place 3 image button with icon
		ImageButton place = (ImageButton) findViewById(R.id.IB_place);
		ImageButton order = (ImageButton) findViewById(R.id.IB_order);
		ImageButton pay = (ImageButton) findViewById(R.id.IB_pay);

		// events if button clicked -> new activity launched
		order.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent launcher = new Intent(UserPage.this, Order.class);
				startActivityForResult(launcher, 1);

			};
		});

		place.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent launcher = new Intent(UserPage.this, Place.class);
				startActivityForResult(launcher, 1);

			};
		});

		pay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent launcher = new Intent(UserPage.this, Pay.class);
				startActivityForResult(launcher, 1);

			};
		});

	}

	// event for a special item of the action bar
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_location_found:
			LocationFound();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// launch an activity if item selected
	private void LocationFound() {
		Intent i = new Intent(UserPage.this, Place.class);
		startActivity(i);
	}

	// create the list for the context menu
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select The Action");
		menu.add(0, v.getId(), 0, "Place");// groupId, itemId, order, title
		menu.add(0, v.getId(), 0, "Order");
		menu.add(0, v.getId(), 0, "Pay");

	}

	// event for an item of the context menu : generate a toast and launch a new
	// activity
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Place") {
			Toast.makeText(getApplicationContext(), "Let's Place",
					Toast.LENGTH_LONG).show();
			Intent launcher = new Intent(UserPage.this, Place.class);
			startActivityForResult(launcher, 1);
		} else if (item.getTitle() == "Order") {
			Toast.makeText(getApplicationContext(), "Let's Order",
					Toast.LENGTH_LONG).show();
			Intent launcher = new Intent(UserPage.this, Order.class);
			startActivityForResult(launcher, 1);
		} else if (item.getTitle() == "Pay") {
			Toast.makeText(getApplicationContext(), "Let's Pay",
					Toast.LENGTH_LONG).show();
			Intent launcher = new Intent(UserPage.this, Pay.class);
			startActivityForResult(launcher, 1);
		} else {
			return false;
		}
		return true;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);

		return super.onCreateOptionsMenu(menu);

	}
}