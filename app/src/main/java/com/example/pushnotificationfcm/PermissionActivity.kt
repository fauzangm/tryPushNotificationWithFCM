package com.example.pushnotificationfcm


import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.Button
import android.widget.RemoteViews
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.pushnotificationfcm.R
import com.example.pushnotificationfcm.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityPermissionBinding
    private lateinit var requestLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                //main activity
                startActivity(Intent(
                    this@PermissionActivity,
                    MainActivity::class.java
                ))
            }
            else {
                //show error message
                showErrorMessage()
            }
        }

        binding.okayBtn.setOnClickListener {
            Log.e("check","1")
                askForNotificationPermission()

        }
        binding.skipBtn.setOnClickListener {
            showErrorMessage()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermission() {

        Log.e("check","3")
        requestLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun showErrorMessage() {
        Toast.makeText(
            this,
            "Permission is not granted",
            Toast.LENGTH_SHORT
        ).show()
    }
}