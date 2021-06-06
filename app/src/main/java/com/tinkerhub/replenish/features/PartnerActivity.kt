package com.tinkerhub.replenish.features

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.databinding.ActivityPartnerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import medina.juanantonio.mlqrscanner.library.Scanner
import medina.juanantonio.mlqrscanner.library.common.Constants.BarcodeIntent.BARCODE_RAW_RESULT

@AndroidEntryPoint
class PartnerActivity : AppCompatActivity() {
    
    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 111
        private const val REQUEST_QR = 222
    }
    
    private lateinit var binding: ActivityPartnerBinding
    private val viewModel: PartnerViewModel by viewModels()
    
    private val isCameraPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activity_partner,
            null,
            false
        )
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        
        binding.buttonScanNewQr.setOnClickListener {
            Scanner.startQR(this, REQUEST_QR)
        }
        
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
            viewModel.viewModelScope.launch {
                val rawResult = data?.getStringExtra(BARCODE_RAW_RESULT) ?: ""
    
                displaySuccessView(false)
                viewModel.sendActivityPoints(rawResult)
                displaySuccessView(true)
            }
        }
    }
    
    private fun displaySuccessView(show: Boolean) {
        binding.groupPointsBeingAwarded.isVisible = !show
        binding.groupSuccessView.isVisible = show
    }
}