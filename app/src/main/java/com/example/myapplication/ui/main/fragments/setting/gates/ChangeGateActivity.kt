package com.example.myapplication.ui.main.fragments.setting.gates

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.ActivityChangeGateBinding
import com.example.myapplication.model.GatesData
import com.example.myapplication.model.SitesData
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.activateView
import com.example.myapplication.util.extensions.deactivateView
import com.example.myapplication.util.extensions.observe
import com.example.myapplication.util.extensions.toJsonString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeGateActivity : BaseActivity<ActivityChangeGateBinding>() {
    lateinit var binding: ActivityChangeGateBinding
    val viewModel: ChangeGateViewModel by viewModels()


    var sites = ArrayList<SitesData>()
    var sitesString = ArrayList<String>()


    var gates = ArrayList<GatesData>()
    var gatesString = ArrayList<String>()


    var gate = GatesData()
    var site = SitesData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!

        viewModel.getSites()


        binding.chooseSiteSpinner.setAdapter(
            ArrayAdapter(
                this,
                R.layout.dropdown_menu_popup_item,
                sitesString
            )
        )
        binding.chooseSiteSpinner.onItemClickListener =
            OnItemClickListener { parent, arg1, pos, id ->
                binding.chooseGateSpinner.setText("")
                site = sites[pos]
                viewModel.getGates(sites[pos].id!!)
            }


        binding.chooseGateSpinner.setAdapter(
            ArrayAdapter(
                this,
                R.layout.dropdown_menu_popup_item,
                gatesString
            )
        )
        binding.chooseGateSpinner.onItemClickListener =
            OnItemClickListener { parent, arg1, pos, id ->

                gate = gates[pos]

            }


        binding.chooseGateSpinner.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    binding.confirm.deactivateView()
                } else {
                    binding.confirm.activateView()

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.confirm.setOnClickListener {

            dataManager.saveGate(gate.toJsonString())
            dataManager.saveSite(site.toJsonString())
            dataManager.saveIsLogin(true)
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(i)
            this.finish()

        }

        setState()
    }

    private fun setState() {

        observe(viewModel.sitesState)
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
                    val response = it.data as BaseResponse<ArrayList<SitesData>>

                    sites.clear()
                    sitesString.clear()
                    sites = response.data!!
                    for (i in sites) {
                        sitesString.add(i.name!!)
                    }

                    binding.chooseSiteSpinner.setAdapter(
                        ArrayAdapter(
                            this,
                            R.layout.dropdown_menu_popup_item,
                            sitesString
                        )
                    )

                }
            }
        }

        observe(viewModel.gatesState)
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

                    val response = it.data as BaseResponse<ArrayList<GatesData>>


                    if (response.data.isNullOrEmpty())
                    {
                        showToast("No Gates Found")
                    }

                    gates.clear()
                    gatesString.clear()
                    gates = response.data!!
                    for (i in gates) {
                        gatesString.add(i.name!!)
                    }

                    binding.chooseGateSpinner.setAdapter(
                        ArrayAdapter(
                            this,
                            R.layout.dropdown_menu_popup_item,
                            gatesString
                        )
                    )

                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_change_gate
    }
}