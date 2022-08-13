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
import com.example.myapplication.databinding.ActivityActionBinding
import com.example.myapplication.model.ScanCarData

class ActionActivity(private val model: ScanCarData) : BaseActivity<ActivityActionBinding>() {

    lateinit var binding: ActivityActionBinding
    val viewModel: ActionViewModel by viewModels()
    var action = "checkin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!



        binding.name.text = model.driverName
        binding.carBrand.text = model.carBrand
        binding.carPlate.text = model.carPlateAr
        binding.type.text = model.driverType

        binding.actionsLayout.setOnClickListener {


            val dialogBuilder = android.app.AlertDialog.Builder(this)
            val dialogView: View = layoutInflater.inflate(R.layout.action_list, null)
            dialogBuilder.setView(dialogView)


            val checkin = dialogView.findViewById<LinearLayout>(R.id.checkin)
            val checkout = dialogView.findViewById<LinearLayout>(R.id.checkout)
            val cancel = dialogView.findViewById<LinearLayout>(R.id.cancel)

            val alertDialog = dialogBuilder.create()


            checkin.setOnClickListener(View.OnClickListener {
                action = "checkin"
                alertDialog.dismiss()

            })

            checkout.setOnClickListener(View.OnClickListener {
                action = "checkout"
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
                    model.carRequestId.toString(),
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


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_action
    }
}