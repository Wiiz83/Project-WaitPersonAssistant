package com.example.wait_person;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button b;
	EditText et, pass;
	TextView tv;
	HttpPost httppost; // allow to send data to a database
	StringBuffer buffer;
	HttpResponse response; // allow to recover data from a database
	HttpClient httpclient; // allow to connect with a server
	List<NameValuePair> nameValuePairs; // a list of value
	ProgressDialog dialog = null;
	String BOX_Login = ""; // variable to put the login string

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		b = (Button) findViewById(R.id.BT_Login);
		Button cancel = (Button) findViewById(R.id.BT_Cancel);
		et = (EditText) findViewById(R.id.ET_username);
		pass = (EditText) findViewById(R.id.ET_password);
		tv = (TextView) findViewById(R.id.tv);

		cancel.setOnClickListener(new View.OnClickListener() { // allow to go to
																// the previous
																// activity
																// (here it's
																// close the
																// app)
			@Override
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});

		b.setOnClickListener(new OnClickListener() { // if the user click on Log
														// In, a progress dialog
														// appaer in order to
														// prevent the user
														// that's loading
			@Override
			public void onClick(View v) {
				dialog = ProgressDialog.show(MainActivity.this, "",
						"Validating user...", true);

				final Timer t = new Timer(); //set a timer for the time to display
				t.schedule(new TimerTask() {
					public void run() {
						dialog.dismiss(); 
						t.cancel();
					}
				}, 6000); // after 6 second (or 6000 miliseconds), the progress dialog will
							// be active

				new Thread(new Runnable() { //to boot the login method to check the login and password
					public void run() {
						login();
					}
				}).start();
			}
		});
	}

	void login() { //to check the login and password
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost("http://yoto-lucasuzan.fr/check.php"); //connect to the script on my server
			nameValuePairs = new ArrayList<NameValuePair>(2); //create the list with 2 places and then add the login and the password into
			nameValuePairs.add(new BasicNameValuePair("username", et.getText().toString().trim())); 
			nameValuePairs.add(new BasicNameValuePair("password", pass.getText().toString().trim()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			// Execute HTTP Post Request
			response = httpclient.execute(httppost);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost,responseHandler);
			System.out.println("Response : " + response);

			if (response.equalsIgnoreCase("﻿User Found")) { //login and password validate
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this, "Login Success",
								Toast.LENGTH_SHORT).show();
					}
				});

				//put the content of the login edittext into a variable
				String login = et.getText().toString().trim();
				// Initialize the launcher
				Intent launcher = new Intent(MainActivity.this, UserPage.class);
				// put the login value into a Box that will be send to the
				// UserPage Activity
				launcher.putExtra(BOX_Login, login);
				// Start the launcher
				startActivityForResult(launcher, 1);


			} else { //login and/or password invalid, show an alert
				showAlert();
			}

		} catch (Exception e) {
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}

	public void showAlert() {
		MainActivity.this.runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("Login Error.");
				builder.setMessage("User not Found.")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}
}