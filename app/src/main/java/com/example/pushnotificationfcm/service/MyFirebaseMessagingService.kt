package com.example.pushnotificationfcm.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

import com.example.pushnotificationfcm.MainActivity
import com.example.pushnotificationfcm.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channel_id = "notification_channel"
const val channel_Name = "com.example.pushnotificationfcm.service"

private lateinit var notificationManager: NotificationManager
class MyFirebaseMessagingService : FirebaseMessagingService() {


    // Override onNewToken to get new token
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.e("Check message","${ remoteMessage.notification?.title} dan ${remoteMessage.notification?.body}")
        if (remoteMessage.getNotification() != null) {
            showNotification(
                remoteMessage.notification?.title.toString(),
                remoteMessage.notification?.body.toString()
            )
        }
    }

    //problem disini gk mau di custom notifnya
    @SuppressLint("RemoteViewLayout")
    private fun getCustomDesign(
        title: String,
        message: String
    ): RemoteViews {
        Log.e("check RemoteViews","check1")
        val remoteViews = RemoteViews(
            packageName,
            R.layout.item_notificaition
        )
        remoteViews.setTextViewText(R.id.tvTittle, title)
        remoteViews.setTextViewText(R.id.tvBody, message)
        remoteViews.setImageViewResource(
            R.id.imgRun,
            R.drawable.ic_run24
        )
        return remoteViews
    }

    // Method to display the notifications


    fun showNotification(
        title: String,
        message: String
    ) {

        val remoteViews: RemoteViews = RemoteViews(
            packageName,
            R.layout.item_notificaition
        )
        remoteViews.setTextViewText(R.id.tvTittle, title)
        remoteViews.setTextViewText(R.id.tvBody, message)

        Log.e("check","2")
        notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        createNotificationChannel()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val builder = NotificationCompat.Builder(
            applicationContext,
            channel_id,
        )
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_run24)
            .setContentIntent(pendingIntent)
//            .setContent(remoteViews)




        Log.e("check","4")
        notificationManager.notify(0, builder.build())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channel_id,
            channel_Name,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This is dummy description"
        }

        Log.e("check","3")
        notificationManager.createNotificationChannel(channel)
    }
}