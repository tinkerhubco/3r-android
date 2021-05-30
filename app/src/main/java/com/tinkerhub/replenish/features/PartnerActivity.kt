package com.tinkerhub.replenish.features

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tinkerhub.replenish.databinding.ActivityPartnerBinding
import dagger.hilt.android.AndroidEntryPoint
import medina.juanantonio.mlqrscanner.library.Scanner
import medina.juanantonio.mlqrscanner.library.common.Constants.BarcodeIntent.BARCODE_RAW_RESULT

@AndroidEntryPoint
class PartnerActivity : AppCompatActivity() {
    
    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 111
        private const val REQUEST_QR = 222
    }
    
    private lateinit var binding: ActivityPartnerBinding
    
    private val isCameraPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        if (isCameraPermissionGranted)
            Scanner.startQR(this, REQUEST_QR)
        else
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode != REQUEST_CAMERA_PERMISSION ||
            grantResults.isEmpty() ||
            grantResults.first() != PackageManager.PERMISSION_GRANTED
        ) return
        
        Scanner.startQR(this, REQUEST_QR)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == REQUEST_QR) {
            val rawResult = data?.getStringExtra(BARCODE_RAW_RESULT) ?: ""
            binding.textViewSampleText.text = rawResult
        }
    }
}