package com.example.myapplication.ui.main.fragments.history

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.HistoryItemBinding
import com.example.myapplication.model.HistoryData
import org.ocpsoft.prettytime.PrettyTime
import java.util.*


class HistoryAdapter(
    private var myData: ArrayList<HistoryData>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    val prettyTime = PrettyTime(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    fun setMyData(myData: ArrayList<HistoryData>) {
        this.myData = myData
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = myData[position]


        holder.binding.name.text = model.driverName
        holder.binding.carBrand.text = model.carBrand
        holder.binding.carPlate.text = model.carPlateAr
        holder.binding.carDesc.text = model.carDescription
        holder.binding.type.text = model.driverType
        holder.binding.action.text = model.statusAction
        holder.binding.lastEntry.text = model.lastEntry
        holder.binding.permission.text = model.historyStatus


        if (model?.historyStatus == "refused")
        {
            holder.binding.permissionImage.setImageResource(R.drawable.ic_permission_denied)
        }
        else
        {
            holder.binding.permissionImage.setImageResource(R.drawable.ic_permission_grant)
        }


        holder.binding.actionsNote.text = model.checkinNote?.ifEmpty {
            model.refuseNote?.ifEmpty {
                model.checkoutNote?.ifEmpty {
                    ""
                }
            }
        }


    }


    override fun getItemCount(): Int {
        return myData.size
    }

    inner class ViewHolder(var binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}