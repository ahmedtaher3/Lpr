package com.example.myapplication.ui.main.newVisitor

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.ActivityNewVisitorCameraBinding
import com.example.myapplication.model.ConfirmScanData
import com.example.myapplication.model.ScanCarData
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File


@AndroidEntryPoint
class NewVisitorCameraActivity : BaseActivity<ActivityNewVisitorCameraBinding>() {

    lateinit var binding: ActivityNewVisitorCameraBinding
    private val viewModel: ActionViewModel by viewModels()
    var base64 = ""
    var file: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!

        binding.image.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()
                .compress(1080)
                .maxResultSize(
                    1080,
                    1080
                )
                .start()
        }


        binding.action.setOnClickListener {

            if (file != null) {
                val dialogBuilder = android.app.AlertDialog.Builder(this)
                val dialogView: View = layoutInflater.inflate(R.layout.confirmation_dialog, null)
                dialogBuilder.setView(dialogView)

                val cancel = dialogView.findViewById<AppCompatButton>(R.id.cancel)
                val done = dialogView.findViewById<AppCompatButton>(R.id.done)
                val title = dialogView.findViewById<TextView>(R.id.title)
                val desc = dialogView.findViewById<TextView>(R.id.desc)


                done.text = getString(R.string.done)
                cancel.text = getString(R.string.reshoot)
                title.text = getString(R.string.confirm_image_dialog_title)
                desc.text = getString(R.string.confirm_action_dialog_desc)

                val alertDialog = dialogBuilder.create()

                done.setOnClickListener(View.OnClickListener {

                    alertDialog.dismiss()
                    viewModel.scanCar(file!!)

                })

                cancel.setOnClickListener(View.OnClickListener {

                    alertDialog.dismiss()

                    ImagePicker.with(this)
                        .cameraOnly()
                        .crop()
                        .compress(1080)
                        .maxResultSize(
                            1080,
                            1080
                        )
                        .start()
                })


                alertDialog.show()
                alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }

        }

        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.reportLayout.setOnClickListener {
            startActivity(Intent(this, QRCodeActivity::class.java))
        }

        setState()

    }

    private fun setState() {

        observe(viewModel.scanCarState)
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
                    val response = it.data as BaseResponse<ConfirmScanData>


                    val dialogBuilder = android.app.AlertDialog.Builder(this)
                    val dialogView: View =
                        layoutInflater.inflate(R.layout.confirmation_car_dialog, null)
                    dialogBuilder.setView(dialogView)

                    val palteEn = dialogView.findViewById<EditText>(R.id.palteEn)
                    val palteAr = dialogView.findViewById<EditText>(R.id.palteAr)
                    val done = dialogView.findViewById<AppCompatButton>(R.id.done)
                    val cancel = dialogView.findViewById<AppCompatButton>(R.id.cancel)



                    palteEn.setText(response.data?.plateEn)
                    palteAr.setText(response.data?.plateAr)

                    val alertDialog = dialogBuilder.create()

                    done.setOnClickListener(View.OnClickListener {

                        alertDialog.dismiss()
                        viewModel.confirmCar(palteEn.text.toString() , palteAr.text.toString())

                    })

                    cancel.setOnClickListener(View.OnClickListener {

                        alertDialog.dismiss()

                        ImagePicker.with(this)
                            .cameraOnly()
                            .crop()
                            .compress(1080)
                            .maxResultSize(
                                1080,
                                1080
                            )
                            .start()
                    })


                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
                    alertDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);



                }
                is Status.Unauthorized -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }


        observe(viewModel.confirmCarState)
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


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {


            val resultUri = data?.data!!
            file = File(resultUri.path!!)
            binding.visitor.setImageURI(Uri.fromFile(file))


        }


    }

    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_new_visitor_camera
    }
}