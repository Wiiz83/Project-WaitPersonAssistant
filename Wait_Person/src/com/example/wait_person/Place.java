package com.example.wait_person;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class Place extends Activity {

	protected static final View View = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place);

		Button cancel = (Button) findViewById(R.id.BT_Cancel);
		Button find = (Button) findViewById(R.id.BT_Find);

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

				createNotification(View);

			}
		});

	}

	public void createNotification(View view) {
		// Prepare intent which is triggered if the
		// notification is selected
		Intent intent = new Intent(this, Place2.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 1);

		// Build notification
		Notification noti = new Notification.Builder(this)
				.setContentTitle("TABLE 5 AVAILABLE")
				.setContentText("This Table is Ready.")
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(pIntent)
				.addAction(R.drawable.othertable, "Localize this Table",
						pIntent).build();
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// hide the notification after its selected
		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, noti);

	}

}
