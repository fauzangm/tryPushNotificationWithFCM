package com.example.pushnotificationfcm.detail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.pushnotificationfcm.createCustomTempFile
import com.example.pushnotificationfcm.databinding.ActivityDetailBinding
import java.io.File


class DetailActivity : AppCompatActivity() {
    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
//                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
        )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),100)
        }
        initUi()
    }

    private fun initUi() {

//        binding.clAbsenWfa.setOnClickListener {
//            startTakePhoto()
//        }
        binding.clAbsenWfh.setOnClickListener {
//            startTakePhoto()
            val bodyDialogFragment = DialogKeteranganFragment()
            supportFragmentManager.let { it1 -> bodyDialogFragment.show(it1, "DialogKeterangan") }
        }
        binding.clAbsenWfo.setOnClickListener {
            startTakePhoto()
        }
        binding.btnSave.setOnClickListener {
            Toast.makeText(this,"Photo Berhasil Dikirim",Toast.LENGTH_LONG).show()
            binding.btnSave.visibility = View.GONE
            binding.imgResult.visibility = View.GONE
        }
    }
    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        launcherIntentCamera.launch(intent)
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.resolveActivity(packageManager)
//        createCustomTempFile(application).also {
//            val photoURI: Uri = FileProvider.getUriForFile(
//                this@DetailActivity,
//                "com.example.pushnotificationfcm.detail",
//                it
//            )
//            Log.e("photoData",it.toString())
//            currentPhotoPath = it.absolutePath
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            launcherIntentCamera.launch(intent)
//        }
    }

    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            try {
                val imageBitmap = it.data?.extras?.get("data") as Bitmap
                binding.btnSave.visibility = View.VISIBLE
                binding.imgResult.visibility = View.VISIBLE
                binding.imgResult.setImageBitmap(imageBitmap)
//                val myFile = File(currentPhotoPath)
////            val myFile = it.data?.getSerializableExtra("picture") as File
//                myFile.let { file ->
//                    binding.imgResult.visibility = View.VISIBLE
//                    binding.btnSave.visibility = View.VISIBLE
//                    binding.imgResult.setImageBitmap(BitmapFactory.decodeFile(file.path))
//                }
            }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(this,"Terjadi Kesalahan Harap Ulangi",Toast.LENGTH_LONG).show()
            }

        }
    }


}