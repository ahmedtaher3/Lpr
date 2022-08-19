package com.example.myapplication.ui.main.newVisitor


import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.ActivityQRCodeBinding
import com.example.myapplication.model.ScanCarData
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QRCodeActivity : BaseActivity<ActivityQRCodeBinding>() {
    lateinit var binding: ActivityQRCodeBinding

    private lateinit var codeScanner: CodeScanner
    val viewModel: ActionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!


        codeScanner = CodeScanner(this, binding.scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                viewModel.scanQr(it.text)
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }



        setState()
    }


    private fun setState() {


        observe(viewModel.scanQrState)
        {

            when (it) {
                is Status.Loading -> {
                    showDialogLoading()
                }
                is Status.Error -> {
                    hideDialogLoading()
                    showWarningSnackbar(it.message!!)
                }
                is Status.Success<*> -> {
                    hideDialogLoading()
                    val response = it.data as BaseResponse<ScanCarData>
                    val intent = Intent(this, ActionActivity::class.java)
                    intent.putExtra("DRIVER_DATA", response.data)
                    startActivity(intent)


                }
                is Status.Unauthorized -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()




        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {


            codeScanner.startPreview()

        } else {
            requestCameraPermission()
        }


    }


    fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("permission denied")
                .setMessage("ask for permission again")
                .setPositiveButton("ok") { dialog, which ->
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 22)
                }
                .setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }
                .create().show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 22)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            22 -> {


                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {

                    onResume()
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(
                        this@QRCodeActivity,
                        "Permission denied to read your External storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_q_r_code
    }


}