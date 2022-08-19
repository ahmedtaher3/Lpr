package com.example.myapplication.ui.main.newVisitor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.ActivityActionBinding
import com.example.myapplication.model.ScanCarData
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.hide
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActionActivity : BaseActivity<ActivityActionBinding>() {

    lateinit var binding: ActivityActionBinding
    val viewModel: ActionViewModel by viewModels()
    var action = ""
    var model: ScanCarData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!

        model = intent.getParcelableExtra("DRIVER_DATA")

        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.name.text = model?.driverName
        binding.carBrand.text = model?.carBrand
        binding.carPlate.text = model?.carPlateAr
        binding.type.text = model?.driverType
        binding.permission.text = model?.historyStatus

        if (model?.historyStatus == "refused")
        {
            binding.send.setBackgroundResource(R.drawable.button_disable)
            binding.send.isEnabled = false
            binding.permissionImage.setImageResource(R.drawable.ic_permission_denied)
        }
        else
        {
            binding.permissionImage.setImageResource(R.drawable.ic_permission_grant)

        }


        if (model?.statusAction == "checkout")
        {

            binding.lastEntry.text = model?.checkoutDate?.take(10)
        }
        else  if (model?.statusAction == "checkin")
        {
            binding.lastEntry.text = model?.checkinDate?.take(10)
        }


        binding.actionsLayout.setOnClickListener {


            val dialogBuilder = android.app.AlertDialog.Builder(this)
            val dialogView: View = layoutInflater.inflate(R.layout.action_list, null)
            dialogBuilder.setView(dialogView)


            val checkin = dialogView.findViewById<LinearLayout>(R.id.checkin)
            val checkout = dialogView.findViewById<LinearLayout>(R.id.checkout)
            val cancel = dialogView.findViewById<LinearLayout>(R.id.cancel)

            val alertDialog = dialogBuilder.create()


            if (model?.statusAction == "refused")
            {
                checkin.hide()
                checkout.hide()
            }
            else if (model?.statusAction == "checkin")
            {
                checkin.hide()
            }
            else if (model?.statusAction == "checkout")
            {
                checkout.hide()
            }



            checkin.setOnClickListener(View.OnClickListener {
                action = "checkin"
                binding.action.text = action
                alertDialog.dismiss()

            })

            checkout.setOnClickListener(View.OnClickListener {
                action = "checkout"
                binding.action.text = action

                alertDialog.dismiss()

            })
            cancel.setOnClickListener(
                View.OnClickListener { alertDialog?.dismiss() }
            )

            alertDialog.show()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        }
        binding.send.setOnClickListener {


            val dialogBuilder = android.app.AlertDialog.Builder(this)
            val dialogView: View = layoutInflater.inflate(R.layout.confirmation_dialog, null)
            dialogBuilder.setView(dialogView)

            val cancel = dialogView.findViewById<AppCompatButton>(R.id.cancel)
            val done = dialogView.findViewById<AppCompatButton>(R.id.done)
            val title = dialogView.findViewById<TextView>(R.id.title)
            val desc = dialogView.findViewById<TextView>(R.id.desc)


            done.text = getString(R.string.done)
            cancel.text = getString(R.string.cancel)
            title.text = getString(R.string.confirm_action_dialog_title)
            desc.text = getString(R.string.confirm_action_dialog_desc)

            val alertDialog = dialogBuilder.create()

            done.setOnClickListener(View.OnClickListener {

                alertDialog.dismiss()
                viewModel.sendAction(
                    model?.historyId.toString(),
                    action,
                    binding.notes.text.toString()
                )

            })

            cancel.setOnClickListener(View.OnClickListener {

                alertDialog.dismiss()
            })


            alertDialog.show()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        }

        setState()
    }

    private fun setState() {

        observe(viewModel.sendActionState)
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
                    val response = it.data as BaseResponse<BaseResponse.Data>
                    showToast(response.message)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)


                }
                is Status.Unauthorized ->{
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_action
    }
}