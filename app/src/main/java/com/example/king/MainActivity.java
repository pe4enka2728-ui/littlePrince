package com.example.king;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

  private static final int NOTIFY_ID = 1;
  private static final String CHANNEL_ID = "CHANNEL_ID";

  private ConstraintLayout layoutMorning;
  private ImageView imgDay;
  private ImageView imgEvening;
  private ImageView imgNight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    layoutMorning = findViewById(R.id.layoutMorning);
    imgDay = findViewById(R.id.imageDay);
    imgEvening = findViewById(R.id.imageEvening);
    imgNight = findViewById(R.id.imageNight);
  }

  public void btnMorningClick(View view) {
    layoutMorning.setVisibility(View.VISIBLE);
    imgDay.setVisibility(View.INVISIBLE);
    imgEvening.setVisibility(View.INVISIBLE);
    imgNight.setVisibility(View.INVISIBLE);

    showReminderNotification(
            R.drawable.cleaning,
            "Утро",
            "Привести в порядок свою планету"
    );
  }

  public void btnDayClick(View view) {
    layoutMorning.setVisibility(View.INVISIBLE);
    imgDay.setVisibility(View.VISIBLE);
    imgEvening.setVisibility(View.INVISIBLE);
    imgNight.setVisibility(View.INVISIBLE);

    showReminderNotification(
            R.drawable.rose,
            "День",
            "Полить розу"
    );
  }

  public void btnEveningClick(View view) {
    layoutMorning.setVisibility(View.INVISIBLE);
    imgDay.setVisibility(View.INVISIBLE);
    imgEvening.setVisibility(View.VISIBLE);
    imgNight.setVisibility(View.INVISIBLE);

    showReminderNotification(
            R.drawable.home,
            "Вечер",
            "Закрыть розу ширмой"
    );
  }

  public void btnNightClick(View view) {
    layoutMorning.setVisibility(View.INVISIBLE);
    imgDay.setVisibility(View.INVISIBLE);
    imgEvening.setVisibility(View.INVISIBLE);
    imgNight.setVisibility(View.VISIBLE);

    showReminderNotification(
            R.drawable.sunset,
            "Ночь",
            "Полюбоваться звездами"
    );
  }

  private void showReminderNotification(
          int icon,
          String title,
          String text
  ) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) {

      requestPermissions(
              new String[]{Manifest.permission.POST_NOTIFICATIONS},
              100
      );
      return;
    }

    NotificationManager manager =
            (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE
            );

    createChannelIfNeeded(manager);

    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK
    );

    PendingIntent pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
                    | PendingIntent.FLAG_IMMUTABLE
    );

    NotificationCompat.Builder builder =
            new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(false)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

    manager.notify(NOTIFY_ID, builder.build());
  }

  private void createChannelIfNeeded(NotificationManager manager) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(
              CHANNEL_ID,
              "Напоминания",
              NotificationManager.IMPORTANCE_DEFAULT
      );

      manager.createNotificationChannel(channel);
    }
  }
  public void onPrinceClick(View view) {
    Toast myToast1 = Toast.makeText(getApplicationContext(), "Маленький принц", Toast.LENGTH_LONG);
    myToast1.setGravity(Gravity.BOTTOM, 0, 0);
    myToast1.show();
  }
  public void onPlanetClick(View view) {
    Toast myToast = Toast.makeText(getApplicationContext(), "Астероид Б-612, планета на которой живет Маленький принц", Toast.LENGTH_LONG);
    myToast.setGravity(Gravity.BOTTOM, 0, 0);
    myToast.show();
  }
  public void onVolcanoClick(View view) {
    Toast myToast = Toast.makeText(getApplicationContext(), "Потухший вулкан. О нем нужно заботиться", Toast.LENGTH_LONG);
    myToast.setGravity(Gravity.BOTTOM, 0, 0);
    myToast.show();
  }

  public void onRoseClick(View view) {
    Toast myToast = Toast.makeText(getApplicationContext(), "Её нужно поливать, а на ночь укрывать ширмой и колпаком", Toast.LENGTH_LONG);
    myToast.setGravity(Gravity.BOTTOM, 0, 0);
    myToast.show();
  }

  public void onBreakfastClick(View view) {
    Toast myToast = Toast.makeText(getApplicationContext(), "Действующий вулкан, на нем удобно разогревать завтрак.", Toast.LENGTH_LONG);
    myToast.setGravity(Gravity.BOTTOM, 0, 0);
    myToast.show();
  }

}